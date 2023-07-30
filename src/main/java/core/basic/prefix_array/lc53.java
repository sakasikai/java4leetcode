package core.basic.prefix_array;

public class lc53 {
    public int maxSubArray(int[] a) {
        // 前缀和
        int[] s=new int[(int)1e5+10];

        int n = a.length;
        for(int i=1; i<=n; i++)
            s[i] = s[i-1] + a[i - 1];

        // TODO int的最值
        int minv = Integer.MAX_VALUE, res = Integer.MIN_VALUE; // 边界值
        for(int i=0; i<=n; i++){
            if(i>0) res = Math.max(res, s[i] - minv); // 从s[1]开始
            minv = Math.min(minv, s[i]); // 要取到s[0]
        }

        return res;
    }
}
