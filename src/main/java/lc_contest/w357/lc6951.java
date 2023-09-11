package lc_contest.w357;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @title lc6951
 * @description 找出最安全路径
 * @create 2023/8/6 11:38
 */
public class lc6951 {

    public static void main(String[] args) {
        String ain = "[[0,0,1],[0,0,0],[0,0,0]]";
        String[] split = ain.split("[\\[,\\]]");
        List<Integer> collect = Arrays.stream(split).filter(i -> !i.isEmpty())
                .map(Integer::valueOf).collect(Collectors.toList());
        int n = (int) Math.sqrt(collect.size());
        List<List<Integer>> lst = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lst.add(collect.subList(i * n, i * n + n));
        }

        lc6951 r = new lc6951();
        System.out.println(r.maximumSafenessFactor(lst));

    }

    /**
     * @param grid
     * @return int
     * @description: <p>
     * 0 0 -> n-1 n-1
     * state of grid cell
     * safe factor = min{单元格 到矩阵中任一小偷所在单元格的 最小 曼哈顿距离}
     * ans = max({sf of cell| cells in path})
     * </p>
     * <p>
     * cellFactors
     * </p>
     * @author: maiqi
     * @update: 2023/8/6 11:38
     */
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) return 0;

        Integer[][] cellFactors = new Integer[n][n];
        Arrays.stream(cellFactors).forEach(ln -> Arrays.fill(ln, Integer.MAX_VALUE));
        Map<String, Boolean> cacheBFS = new HashMap<>();

        Queue<List<Integer>> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    cacheBFS.putIfAbsent(String.format("%d,%d", i, j), Boolean.TRUE);
                    cellFactors[i][j] = 0;
                    q.add(Arrays.asList(i, j)); // 小偷cell
                }
            }
        }

        int[] xv = {1, 0, -1, 0}, yv = {0, 1, 0, -1};
        int x, y, nx, ny;
        while (!q.isEmpty()) {
            List<Integer> nd = q.remove();
            x = nd.get(0);
            y = nd.get(1);
            Integer sf = cellFactors[x][y];

            for (int i = 0; i < 4; i++) {
                nx = x + xv[i];
                ny = y + yv[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n
                        && !cacheBFS.getOrDefault(String.format("%d,%d", nx, ny), Boolean.FALSE)) {
                    cacheBFS.putIfAbsent(String.format("%d,%d", nx, ny), Boolean.TRUE);
                    cellFactors[nx][ny] = sf + 1;
                    q.add(Arrays.asList(nx, ny));
                }
            }
        }

        // TODO 因为可以想四个方向走！动态规划只能向两个方向走, 所以是个最短路问题
        // 1。预处理每个cell的 distToDanger
        // 2。一条路径的安全系数 factorOfPath = min{dist(cell) | cell in path}
        // 3。find max_factorOfPath
        // 4。a valid path: all cell factors >= max_factorOfPath
        int l = 0, r = n + 10;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (canFindPathBiBFS(cellFactors, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    /**
     * @description:
     * <p>
     *     假设答案为 ddd，我们可以把所有 dis[i][j]=d 的格子与其四周 ≥d 的格子
     *     用并查集连起来，在答案为 ddd 的情况下，这些格子之间是可以互相到达的
     * </p>
     * @author: maiqi
     * @param cellFactors
     * @param factorOfPath
     * @return boolean
     * @update: 2023/8/6 18:29
     */


    /**
     * @param cellFactors
     * @param factorOfPath
     * @return boolean
     * @description: 双向BFS
     * @author: maiqi
     * @update: 2023/8/6 19:23
     */
    public boolean canFindPathBiBFS(Integer[][] cellFactors, int factorOfPath) {
        // ensure minFactor of Path >= factorOfPath
        // bfs
        int[] xv = {1, 0, -1, 0}, yv = {0, 1, 0, -1};
        int x, y, nx, ny, n = cellFactors.length;
        if (cellFactors[0][0] < factorOfPath || cellFactors[n - 1][n - 1] < factorOfPath) return false;

        Map<String, Boolean> m1 = new HashMap<>(), m2 = new HashMap<>(),
                m = null, m3 = null;
        m1.put(String.format("%d,%d", 0, 0), Boolean.TRUE);
        m2.put(String.format("%d,%d", n - 1, n - 1), Boolean.TRUE);

        Queue<List<Integer>> q1 = Stream.of(Arrays.asList(0, 0)).collect(Collectors.toCollection(LinkedList::new)),
                q2 = Stream.of(Arrays.asList(n - 1, n - 1)).collect(Collectors.toCollection(LinkedList::new)),
                q = null;


        while (!q1.isEmpty() || !q2.isEmpty()) {
            for (int r = 0; r < 2; r++) { // round
                switch (r) {
                    case 0:
                        q = q1;
                        m = m1;
                        m3 = m2;
                        break;
                    case 1:
                        q = q2;
                        m = m2;
                        m3 = m1;
                        break;
                }

                if (q.isEmpty()) continue;

                List<Integer> nd = q.remove();
                x = nd.get(0);
                y = nd.get(1);

                if (m3.getOrDefault(String.format("%d,%d", x, y), Boolean.FALSE)) return true;

                for (int i = 0; i < 4; i++) {
                    nx = nd.get(0) + xv[i];
                    ny = nd.get(1) + yv[i];

                    if (0 <= nx && nx < n && 0 <= ny && ny < n
                            && !m.getOrDefault(String.format("%d,%d", nx, ny), Boolean.FALSE)
                            && cellFactors[nx][ny] >= factorOfPath) {
                        q.add(Arrays.asList(nx, ny));
                        m.put(String.format("%d,%d", nx, ny), Boolean.TRUE);
                    }
                }
            }
        }

        return false;
    }

    // DFS
    public boolean canFindPath(Integer[][] cellFactors, int x, int y, int factorOfPath,
                               Map<String, Boolean> cacheDFS) {
        // ensure minFactor of Path >= factorOfPath
        if (x == 0 && y == 0) {
            cacheDFS = new HashMap<>();
            if (cellFactors[0][0] < factorOfPath) return false;
            cacheDFS.put(String.format("%d,%d", 0, 0), Boolean.TRUE);
        }
        int nx, ny, n = cellFactors.length;
        int[] xv = {1, 0, -1, 0}, yv = {0, 1, 0, -1};

        // end
        if (x == n - 1 && y == n - 1) return true;

        for (int i = 0; i < 4; i++) {
            nx = x + xv[i];
            ny = y + yv[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < n
                    && !cacheDFS.getOrDefault(String.format("%d,%d", nx, ny), Boolean.FALSE)
                    && cellFactors[nx][ny] >= factorOfPath) {

                cacheDFS.put(String.format("%d,%d", nx, ny), Boolean.TRUE);
                if (canFindPath(cellFactors, nx, ny, factorOfPath, cacheDFS)) {
                    return true;
                }
                // still have chance
            }
        }

        return false;
    }

}
