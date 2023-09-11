package core.advanced.dp.prefix_sum;

/**
 * @author maiqi
 * @title lc813
 * @description 813. 最大平均值和的分组
 * @create 2023/9/5 16:19
 */
public class lc813 {
    /*
    f[i][k]
    = max(, f[j][k-1] + aRange[j+1, i]  // 0<=j<i,
     */

    public double largestSumOfAverages(int[] nums, int K) {
        int n = nums.length;
        double[][] f = new double[n + 1][K + 1];
        int[] ps = new int[n + 1];
        System.arraycopy(nums, 0, ps, 1, n);
        for (int i = 1; i < n + 1; i++)
            ps[i] = ps[i - 1] + nums[i - 1];

        for (int i = 1; i <= n; i++) { // 数组规模
            f[i][1] = (double) ps[i] / i; // 不划分
            for (int j = 1; j < i; j++) {
                // 划分子问题 f[0..j] + aRange[j+1..i]
                for (int k = 2; k <= K; k++) {
                    f[i][k] = Math.max(f[i][k], f[j][k - 1] + (double) (ps[i] - ps[j]) / (i - j));
                }
            }
        }
        return f[n][K];
    }
}
