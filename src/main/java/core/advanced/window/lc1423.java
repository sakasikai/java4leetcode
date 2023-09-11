package core.advanced.window;

/**
 * @author maiqi
 * @title lc1423
 * @description 1423. 可获得的最大点数
 * @create 2023/9/6 08:21
 */
public class lc1423 {
    public static void main(String[] args) {
        lc1423 r = new lc1423();
        int[] in = {9, 7, 7, 9, 7, 7, 9};
        System.out.println(r.maxScore(in, 7));
    }

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] p = new int[n + 1];
        for (int i = 1, j; i <= n; i++)
            p[i] = p[i - 1] + cardPoints[j = i - 1];

        // range[j, i] of max(1, n-k)
        // find min sum
        int minV = Integer.MAX_VALUE, wl, len = n - k;
        if (len == 0) return p[n];
        for (int i = 0, j = 0; i < n; i++, j++) {
            // add
            while ((wl = i - j + 1) < len) i++;
            // wl == len
            // rg [j, i]
            minV = Math.min(minV, p[i + 1] - p[j]);
        }

        return p[n] - minV;
    }
}
