package core.basic.double_ptr.bsearch;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author maiqi
 * @title lc826
 * @description 安排工作以达到最大收益
 * @create 2023/7/31 08:52
 */
public class lc826 {
    public static void main(String[] args) {
        lc826 r = new lc826();

        int[] difficulty = {68, 35, 52, 47, 86};
        int[] profit = {67, 17, 1, 81, 3};
        int[] worker = {92, 10, 85, 84, 82};
        Assert.isTrue(r.maxProfitAssignment(difficulty, profit, worker) == 324, "err");

        difficulty = new int[]{2, 4, 6, 8, 10};
        profit = new int[]{10, 20, 30, 40, 50};
        worker = new int[]{4, 5, 6, 7};
        Assert.isTrue(r.maxProfitAssignment(difficulty, profit, worker) == 100, "err");

        System.out.println("done!");
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int nd = difficulty.length;

        // N logN
        List<int[]> diffProfs = new ArrayList<>(nd);
        IntStream.range(0, nd).forEach(i -> diffProfs.add(new int[]{difficulty[i], profit[i]}));
        List<int[]> sortedDPs = diffProfs.stream().sorted((ints, another) -> ints[0] - another[0]).collect(Collectors.toList());

        // M logM
        Integer[] sortedWorkers = Arrays.stream(worker).boxed().sorted().toArray(Integer[]::new);

        return maxProfitOfDoublePointers(sortedDPs, sortedWorkers);
    }

    /**
     * @param sortedDPs
     * @return int
     * @description: <p>
     * worker cap 也可以排序，i(Diff最大=》最小（DPs规模缩小
     * sorted Diff Prof 排序，j(对应的 单调 缩小范围
     * O(N) + O(M)
     * </p>
     * @author: maiqi
     * @update: 2023/7/31 09:37
     */
    public int maxProfitOfDoublePointers(List<int[]> sortedDPs, Integer[] sortedWorkers) {
        int ans = 0, nj, maxProf = 0, k;

        // M*logN + N
        for (int i = 0, j = 0; i < sortedWorkers.length; i++) {
            // i++ 增长后, worker[i]增大，问题范围 单调增长，
            // 所以只需处理增量问题 [0..i][0..oldJ] => i+1 [oldJ+1(j), ..nj] ..n-1
            int finalI = i;
            nj = findRightOfNextJ(sortedDPs, j, sortedDPs.size() - 1, midDiff -> midDiff <= sortedWorkers[finalI]);

            if (j <= nj && nj < sortedDPs.size()
                    && sortedWorkers[finalI] >= sortedDPs.get(nj)[0]) { // TODO nj可能会越界
                // old..] [j..nj]

                for (k = j; k <= nj; k++) {
                    maxProf = Math.max(maxProf, sortedDPs.get(k)[1]);
                } // TODO stream sublist TTL! (vs double pointers)

                ans += maxProf;
                j = nj + 1;
            } else {
                ans += maxProf;
            }
        }
        return ans;
    }

    public int findRightOfNextJ(List<int[]> sortedDPs, int l, int r, Predicate<Integer> match) {
        // logN
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (match.test(sortedDPs.get(mid)[0])) { // worker subject to difficulty
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
