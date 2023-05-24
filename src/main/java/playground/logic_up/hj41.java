package playground.logic_up;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @Title: hj41
 * @ProjectName Java4leetcode
 * @Description:
 * 2
 * 74 185
 * 3 1
 *
 * @date 2023/4/222:11
 */
public class hj41 {
    public static Set<Integer> marked = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] val = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int[] cnt = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();

        dfs(cnt, val, 0, 0);


        System.out.println(marked.size());
    }

    public static void dfs(int[] cnt, int[] val, int u, int cur){
        if(u >= val.length) return;

        marked.add(cur);

        for (int j=u; j<val.length; j++){
            for(int i=0; i<=cnt[j]; i++){
                int candi = cur + val[j]*i;

                marked.add(candi);
                dfs(cnt, val, u+1, candi); // 剪枝

            }
        }
    }
}
