package advanced.dp.linear;

/**
 * 相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额
 *
 */
public class lc198 {

    // f[i] = max(a[i]+f[i-2], f[i-1])
    public int rob(int[] a) {
        int n = a.length;

        int[] f = new int[n+1];
        f[1] = a[0]; // f[0] = 0
        for(int i=2; i<=n; i++){
            // 不偷 or 偷
            f[i] = Math.max(f[i-1], a[i-1] + f[i-2]);
        }

        return f[n];
    }
}
