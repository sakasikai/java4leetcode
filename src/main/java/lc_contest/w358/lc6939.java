package lc_contest.w358;

import java.util.Arrays;

/**
 * @author maiqi
 * @title lc6939
 * @description TODO
 * @create 2023/8/13 10:36
 */
public class lc6939 {
    public static void main(String[] args) {
        int[] a = {51, 71, 17, 24, 42};
        lc6939 r = new lc6939();
        System.out.println(r.maxSum(a));
    }

    public int maxSum(int[] nums) {
        int[] wins = Arrays.stream(nums).boxed().sorted((p, q) -> {
            int pmd, qmd;
            if ((pmd = maxDigit(p)) != (qmd = maxDigit(q))) {
                return pmd - qmd;
            } else {
                return p - q;
            }
        }).mapToInt(Integer::intValue).toArray();

        int ans = -1, cmd;

        System.out.println(Arrays.toString(wins));
        int lastMaxDigit = -1;
        for (int i = 0; i < wins.length; i++) {
            if ((cmd = maxDigit(wins[i])) != lastMaxDigit) {
                lastMaxDigit = cmd;
                continue;
            } // cmd == lastMaxDigit != -1

            ans = Math.max(ans, wins[i] + wins[i - 1]);
        }

        return ans;
    }

    public int maxDigit(int n) {
        int maxVal = 0;
        while (n > 0) {
            maxVal = Math.max(maxVal, n % 10);
            n /= 10;
        }
        return maxVal;
    }
}
