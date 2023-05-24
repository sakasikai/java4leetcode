package advanced.dp.linear;

/**
 * 子数组 是数组的「连续」 子序列
 *  子序列 sub-sequence，不连续，可以跳跃的意思
 */
public class lc152 {
    // 分三种情况：
    // 两正数乘积
    // 一负一正，必为负数，绝对值越小越好
    // 两负数乘积，两个绝对值越小越好
    // 0
    // f[i] = max(a[i], a[i]*f[i-1], a[i]*g[i-1]), g[i] ...
    // 枚举+线性dp
    public int maxProduct(int[] a) {
        int n = a.length;
        int[] f=new int[n+1], g=new int[n+1];
        int res = a[0];

        f[0] = a[0];
        g[0] = a[0];
        // 枚举右边界，左边界动态决定 [l, r]
        for(int i=1; i<n; i++){ // r
            if (a[i] == 0){ // special case
                f[i] = 0;
                g[i] = 0;
            }else{ // l==i, or define by f[i-1] & g[i-1]
                f[i] = Math.max(a[i], Math.max(f[i-1]*a[i], g[i-1]*a[i]));
                g[i] = Math.min(a[i], Math.min(f[i-1]*a[i], g[i-1]*a[i]));
            }
            res = Math.max(res, f[i]);
        }

        return res;
    }
}
