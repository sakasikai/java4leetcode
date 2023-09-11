package core.advanced.dp.treefy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author maiqi
 * @title NoBossClub
 * @description acwing 没有上司的舞会
 * @create 2023/9/2 09:22
 */
public class NoBossClub {
    public static void main(String[] args) throws IOException {
        NoBossClub r = new NoBossClub();
        r.doMain();
    }

    private static final int N = (int) (6e3 + 10);

    int[] gains;
    boolean[] isRoot;
    int[] tab, e, ne;
    int uid;
    int[][] f;

    {
        gains = new int[N];
        tab = new int[N];
        e = new int[N];
        ne = new int[N];
        isRoot = new boolean[N];
        f = new int[N][2];
        Arrays.fill(tab, -1);
        Arrays.fill(isRoot, true);
    }

    public void addEdge(int f, int t) {
        e[uid] = t;
        ne[uid] = tab[f];
        tab[f] = uid++;
    }

    public void doMain() throws IOException {
        int n, n_ = 1;
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(r.readLine());
        while (n_ <= n) { // [1..n]
            gains[n_++] = Integer.parseInt(r.readLine());
        }
        n_ = n - 1;
        while (n_-- > 0) {
            int[] eg = Arrays.stream(r.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            addEdge(eg[1], eg[0]);
            isRoot[eg[0]] = false;
        }

        int root = IntStream.range(1, n + 1).filter(e -> isRoot[e]).findFirst().getAsInt();

        dfs(root);

        System.out.println(Math.max(f[root][0], f[root][1]));
    }

    public void dfs(int rt) { // 叶子节点 f[lf][0] = 0, f[lf][1] = gains[lf]

        f[rt][1] = gains[rt];
        for (int i = tab[rt], v; i != -1; i = ne[i]) {
            v = e[i]; // 出边节点，即子节点
            dfs(v); // 先处理好子问题

            f[rt][0] += Math.max(f[v][0], f[v][1]);
            f[rt][1] += f[v][0];
        }
    }

}
