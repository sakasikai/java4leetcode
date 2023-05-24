package advanced.dp.twoD;

public class lc221 {
    public int maximalSquare(char[][] a) {
        // f[i][j]
        int m = a.length, n = a[0].length;
        int[][] f = new int[m+1][n+1]; // 以a[i-1][j-1]为右下角，最大正方，f[i][j]代表正方的边长
        int res = 0;

        for(int i=1; i<=m; i++)
            for(int j=1; j<=n; j++){
                if(a[i-1][j-1] == '1'){
                    // 横向
                    int hori = Math.min(f[i-1][j-1], f[i-1][j]) + 1; // f[i][j-1]影响不到
                    // 纵向
                    int vert = Math.min(f[i-1][j-1], f[i][j-1]) + 1; // f[i-1][j]影响不到
                    // 因为是正方，且两个方向，三个子问题推导出f[i][j]
                    f[i][j] = Math.min(hori, vert);
                    res = Math.max(res, f[i][j]*f[i][j]);
                }
            }

        return res;
    }
}
