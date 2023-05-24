package advanced.dp.twoD;

import java.util.Arrays;

// 编辑距离
public class lc72 {
    int minDistance(String wd1, String wd2) {
        // f[i][j] op_cnt: wd1[0..i] -> wd2[0..j]
        // edge: f[j][0] = f[0][j] = j
        // transfer: wd1[i]==wd[j]
        int m = wd1.length(), n = wd2.length();
        int[][] f = new int[m+1][n+1];

//        for(int i=0; i<=m; i++) Arrays.fill(f[i], Integer.MAX_VALUE);
        for(int[] r: f) Arrays.fill(r, Integer.MAX_VALUE);
        for(int i=0; i<=m; i++) f[i][0]=i;
        for(int j=0; j<=n; j++) f[0][j]=j;

        for(int i=1; i<=m; i++) // 0 is edge
            for (int j = 1; j <= n; j++) {
                if(wd1.charAt(i-1) == wd2.charAt(j-1)){
                    f[i][j] = Math.min(f[i][j], f[i-1][j-1]);
                }
                // replace
                f[i][j] = Math.min(f[i][j], f[i-1][j-1] + 1);
                // insert
                // delete
                f[i][j] = Math.min(f[i][j], Math.min(f[i-1][j], f[i][j-1])+1);
            }

        return f[m][n];
    }
}
