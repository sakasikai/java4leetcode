package JobToMe.Tuanz.round1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author maiqi
 * @title treeColor_3
 * @description TODO
 * @create 2023/9/1 20:46
 */
public class treeColor_3 {
    public static void main(String[] args) throws IOException {
        treeColor_3 r = new treeColor_3();
        r.doMain();
    }

    private static final int N = (int) 2e5 + 10; // 双向图
    boolean[] isRed, touched;
    int[] weights;
    int[][] f; // from red => maxRedCount
    int[] tab, e, ne;
    int uid;


    {
        weights = new int[N];
        f = new int[N][2];
        tab = new int[N];
        e = new int[N];
        ne = new int[N];
        Arrays.fill(tab, -1);
    }

    void addE(int f, int t) {
        e[uid] = t; // 新建节点 uid
        ne[uid] = tab[f]; // 指向头节点
        tab[f] = uid; // 头插法
        uid++; // 自增id
    }

    public void doMain() throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(r.readLine());
        int[] ln = Arrays.stream(r.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        System.arraycopy(ln, 0, weights, 1, n); // 1 based

        int ni = n - 1; // edges
        while (ni-- > 0) {
            int[] e = Arrays.stream(r.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            addE(e[0], e[1]);
            addE(e[1], e[0]);
        }

        dfs(1, -1);

        System.out.println(Math.max(f[1][0], f[1][1]));
    }

    public void dfs(int u, int fa) { // f[lf][0] = f[lf][1] = 0
        int v;

        // u 不染色
        for (int i = tab[u]; i != -1; i = ne[i]) { // tab[u]是指针(根据uid)，一直指到-1，e[uid]则是节点值
            if ((v = e[i]) == fa) continue; // 模拟父子问题的方向
            dfs(v, u);

            f[u][0] += Math.max(f[v][0], f[v][1]); // 所有子节点结果，汇总到u上
        }

        // u 染色，与某个v
        for (int i = tab[u]; i != -1; i = ne[i]) { // tab[u]是指针(根据uid)，一直指到-1，e[uid]则是节点值
            if ((v = e[i]) == fa || !canChange(u, v)) continue;

            f[u][1] = Math.max(f[u][1], f[u][0] - Math.max(f[v][0], f[v][1])
                    + f[v][0] + 2);
            // 其余节点不影响，u,v 将被染色（条件是 u v 都没染色）
            // f[v][0]表示，没被染色呢，其子节点随意
        }

    }

    public boolean canChange(int i, int j) {
        double var0 = weights[i] * weights[j];
        int var1 = (int) Math.sqrt(var0);
        return var1 * var1 == var0;
    }

    /*
    4 8
    5 2
    =  96
    = 2 4 0
    = 3 3 6
     */


}
