package core.advanced.dp.linear;

import java.util.Arrays;

/**
 * @author maiqi
 * @title lc2008
 * @description 出租车的最大盈利（难啊！）
 * @create 2023/7/15 10:49
 */
public class lc2008 {
    /**
     * @param n
     * @param rides
     * @return long
     * @description: <p>
     * gain for each range(sorted)
     * max total gain?
     * DP f[i] = max(choose rg[i] or skip)
     * </p>
     * @author: maiqi
     * @update: 2023/7/15 10:50
     */
    public long maxTaxiEarnings(int n, int[][] rides) {
        final int m = rides.length;
        Arrays.sort(rides, (p, q) -> {
            if (p[1] != q[1]) {
                return p[1] - q[1];
            } else if (p[2] != q[2]) { // 先按gain排，再按start排
                return p[2] - q[2];
            } else {
                return p[0] - q[0];
            }
        });

        long[] f = new long[n + 1]; // f[end] is max gain for 规模 [1..end]

        // TODO replace map with peek; peek eq forEach ?
        Arrays.stream(rides).forEach(rg -> rg[2] = rg[2] + rg[1] - rg[0]);

        int ed = 0;
        int[] rg;
        for (int i = 1; i <= n && ed < m; i++) {
            while (i < rides[ed][1] && i <= n) {
                f[i] = Math.max(f[i], f[i - 1]);
                i++;
            } // i == rg[1]

            // 同一站点，有多个乘客，选最优的
            f[i] = Math.max(f[i], f[i - 1]);
            while (ed < m && (rg = rides[ed])[1] == i) {
                f[i] = Math.max(f[i], rg[2] + f[rg[0]]);
                ed++;
            }
        }

        return Arrays.stream(f).max().orElse(0L);
    }
}
