package JobToMe.Tuanz.round1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author maiqi
 * @title order
 * @description 正实数判断输入，输出2位数格式化
 * @create 2023/9/1 17:31
 */
public class order_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(r.readLine()), i = n;
        double p, q, pTot = 0, qTot = 0, ans;
        while (i-- > 0) {
            double[] ds = Arrays.stream(r.readLine().split(" "))
                    .mapToDouble(Double::valueOf).toArray();
            p = ds[0];
            q = ds[1];

            if (p <= 0 || q <= 0 || p < q) {
                System.out.println("error");
                return;
            }

            pTot += p;
            qTot += q;
        }
        // x, y
        double[] ds = Arrays.stream(r.readLine().split(" "))
                .mapToDouble(Double::valueOf).toArray();
        p = ds[0];
        q = ds[1];
        if (p <= 0 || q <= 0 || p < q) {
            System.out.println("error");
            return;
        }

        ans = qTot;
        if (pTot >= p) ans = Math.min(ans, pTot - q);
        System.out.printf("%.2f", ans);
    }

}
/*
*
*
10
5.44 0.75
3.66 0.43
8.53 4.2
6.82 0.53
8.4 6.6
9.19 7.56
4.24 0.48
6.98 6.9
6.4 4.68
0.68 0.37
16 9
*
* */
