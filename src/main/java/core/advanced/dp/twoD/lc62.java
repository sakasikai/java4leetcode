package core.advanced.dp.twoD;

public class lc62 {
    public int uniquePaths(int m, int n) {
        // f[i, j] = f[i-1,j] +f[i, j-1]
        // f[0,0]=1

        int[][] f = new int[m+1][n+1];
        f[0][0] = 1;
        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++){
                if(i-1>=0) f[i][j] += f[i-1][j];
                if(j-1>=0) f[i][j] += f[i][j-1];
            }

        return f[m-1][n-1];
    }
}
