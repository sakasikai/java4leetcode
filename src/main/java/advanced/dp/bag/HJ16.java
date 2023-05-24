package advanced.dp.bag;

import java.io.IOException;
import java.util.*;


// v(cost) p(factor) q(0为主，>0为主编号)
// gain = v*p
// 对主件是01背包问题，附件只有主件有才是01背包
public class HJ16 {

    public static void main(String[]args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int money = sc.nextInt();//钱数
        int count = sc.nextInt();//物品数

        int v[] = new int[count + 1];//物品的v p q和附件序号
        int p[] = new int[count + 1];
        int q[] = new int[count + 1];
        int sub1[] = new int[count + 1];
        int sub2[] = new int[count + 1];

        for(int i = 1; i <= count; i++){//第i件物品的属性
            v[i] = sc.nextInt();
            p[i] = sc.nextInt() * v[i];//价值=价格*权重，需要的是p最大
            q[i] = sc.nextInt();
            if (q[i] > 0) {//是附件
                if(sub1[q[i]] == 0)
                    sub1[q[i]] = i;//是附件1
                else
                    sub2[q[i]] = i;//是附件2
            }
        }

        int dp[][] = new int[count +1][money + 1]; //money为啥+1？
        for(int i = 1; i < count + 1; i++) {//两层for循环，动态规划二维表逐列逐行
            int p1 = 0, p2 = 0, p3 = 0;//根据附件数量，分4种情况v[i]、v1、v2、v3
            int v1 = -1, v2 = -1, v3 = -1;//
            if(sub1[i] != 0) {
                v1 = v[i] + v[sub1[i]];
                p1 = p[i] + p[sub1[i]];
            }
            if(sub2[i] != 0) {
                v2 = v[i] + v[sub2[i]];
                p2 = p[i] + p[sub2[i]];
            }
            if(sub1[i] != 0 && sub2[i] != 0) {
                v3 = v1 + v2 - v[i];
                p3 = p1 + p2 - p[i];
            }
            for (int j = 1; j <= money; j++) {
                dp[i][j] = dp[i-1][j];//最大价值最少是这一件不放进去的大小
                if(q[i] == 0) {
                    if(j >= v[i]) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-v[i]] + p[i]);
                    if(v1 != -1 && j >= v1) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-v1] + p1);
                    if(v2 != -1 && j >= v2) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-v2] + p2);
                    if(v3 != -1 && j >= v3) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-v3] + p3);
                }
            }
        }
        System.out.println(dp[count][money]);
    }
}

/**
 * 输入：
1000 5
800 2 0
400 5 1
300 5 1
400 3 0
500 2 0

 2200

 50 5
 20 3 5
 20 3 5
 10 3 0
 10 2 0
 10 1 0

 130
 */