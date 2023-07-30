package core.advanced.dp.linear;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author maiqi
 * @Title: hj24
 * @ProjectName Java4leetcode
 * @Description:
 * 已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形
 *
 * 186 186 150 200 160 130 197 200
 * 4
 * 186 200 160 130
 *
 * f[i] 以i为peek，向左最长递增子序列
 *
 * f[i] = 1 or max(f[j]) + 1 if a[j] < a[i]
 *
 * @date 2023/4/221:14
 */
public class hj24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        String[] data = sc.nextLine().split(" ");
        List<Integer> arr = Arrays.stream(data).map(Integer::valueOf).collect(Collectors.toList());
        int[] f = new int[N], g = new int[N];
        int maxLen = 0;
        Arrays.fill(f, 1);
        Arrays.fill(g, 1);

        for(int i=0; i<N; i++){
            for(int j=0; j<i; j++){
                if(arr.get(j) < arr.get(i))
                    f[i] = Math.max(f[i], f[j] + 1);
            }
        }

        for(int i=N-1; i>=0; i--){
            for(int j=N-1; i<j; j--){
                if(arr.get(j) < arr.get(i))
                    g[i] = Math.max(g[i], g[j] + 1);
            }

            maxLen = Math.max(maxLen, g[i] + f[i] - 1);
        }


        System.out.println(N - maxLen);
    }
}
