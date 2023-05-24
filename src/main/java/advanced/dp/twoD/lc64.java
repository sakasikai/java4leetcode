package advanced.dp.twoD;

import java.util.Arrays;

public class lc64 {
    public static void main(String[] args) {
        // 给二维数组初始化
        int[][] f = new int[2][2];
        for(int[] arr: f){
            Arrays.fill(arr, 3);
        }
        System.out.println(f[0][0] + " " + f[0][1] + " " + f[1][0] + " " + f[1][1]);
    }


    public int minPathSum(int[][] a) {
        int m=a.length, n=a[0].length;
        int[][] f = new int[m+1][n+1];
        for(int[] line: f) Arrays.fill(line, Integer.MAX_VALUE);

        f[0][0] = a[0][0];
        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++){
                if(i-1>=0) f[i][j] = Math.min(f[i][j], a[i][j] + f[i-1][j]);
                if(j-1>=0) f[i][j] = Math.min(f[i][j], a[i][j] + f[i][j-1]);
            }

        return f[m-1][n-1];
    }
}
