package Playground.interest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P1Resolve implements P1Constant {

    public static void main(String[] args) {
        P1Resolve client = new P1Resolve();
        List<Integer> nums_end = client.problem1();

        System.out.print("\n区间   :");
        for (int i = 0; i < 6; i += 2) {
            System.out.printf("[%d, %d) ", i, i + 1);
        }
        System.out.print("\n数量   :");
        nums_end.forEach(i -> System.out.print(i + " "));
        System.out.print("\n分布比例:");
        int sum = nums_end.stream().reduce(Integer::sum).get();
        nums_end.forEach(i -> System.out.printf("%.2f ", (double) i / sum));

        System.out.println("\n\n增长率 :");
        Integer old_sum = MARKER_NUMS.stream().reduce(Integer::sum).get(),
                new_sum = nums_end.stream().reduce(Integer::sum).get();
        System.out.printf("原数量：%d, 新数量：%d, 增长率：%.2f%%", old_sum, new_sum, 100 * ((double) new_sum / old_sum - 1));

    }

    public List<Integer> problem1() {
        int y = MARKER_YEAR_BEGIN;
        List<Integer> rangeNum = new ArrayList<>(MARKER_NUMS);

        while (y <= MARKER_YEAR_END) {
            if (y != MARKER_YEAR_BEGIN) {
                rangeNum = passTwoYears(rangeNum);
            } // else

            System.out.printf("(year %d) range nums:%n", y);
            rangeNum.forEach(p -> System.out.print(p + " "));
            System.out.println();
            y += 2;
        }
        return rangeNum;
    }

    public List<Integer> passTwoYears(List<Integer> nums) {
        Integer[] nextNums = new Integer[]{0, 0, 0};
        for (int i = 0; i < 3; i++) { // 所有新生的
            nextNums[0] += (int) (nums.get(i) * BIRTH_RATE.get(i));
        }

        for (int i = 1; i <= 2; i++) {
            int j = i - 1; // nextNums[1], nextNums[2] 的数量由过去两年数量决定
            nextNums[i] = (int) (nums.get(j) * SERVICE_RATE.get(j));
        }

        return Arrays.asList(nextNums);
    }
}
