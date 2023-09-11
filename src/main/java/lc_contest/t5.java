package lc_contest;

import java.util.Scanner;

/**
 * @author maiqi
 * @title lc_contest.t5
 * @description TODO
 * @create 2023/9/10 21:11
 */
public class t5 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n, k;
        n = in.nextInt();
        k = in.nextInt();

        int[][] arr = new int[n + 1][k + 1], f = new int[n + 1][k + 1];
        int[] ones = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i][0] = in.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1, x; j <= k; j++) {
                arr[i][j] = (x = arr[i][j - 1]) - Integer.lowestOneBit(x);
                if (arr[i][j] == 0) { // f[i][0] > 0
                    ones[i] = j;
                    break;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int m = 0; m <= k; m++) {
                f[i][m] = Integer.MAX_VALUE;
                for (int j = 0; j <= ones[i] && j <= m; j++) {
                    f[i][m] = Math.min(f[i][m], f[i - 1][m - j] + arr[i][j]); // 减去 j 次 a[i]的 lowbit
                }
            }
        }

        System.out.println(f[n][k]);
    }
    /*
    k = 2
    f[i][k] = min(f[i-1][k] + f[i][0], f[i-1][k-1] + a[i] - lowbit(a[i]), f[i-1][k-2] + ...)
     */


}
