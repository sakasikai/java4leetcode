package Playground;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author maiqi
 * @title tst
 * @description false 应该用滑窗，没想好思路
 * @create 2023/8/3 10:08
 */
public class tst {
    /**
     * @param cities
     * @param n
     * @param k
     * @return int
     * @description: <p>
     * city[i] x(cost), y(gain)
     * seek set{city}
     * for any i, j in {city}, rely abs(cost[i] - cost[j]) < k
     * </p>
     * <p>
     * sort by cost then
     * </p>
     * @author: maiqi
     * @update: 2023/8/3 10:10
     */
    public int solve(List<int[]> cities, int n, int k) {
        List<int[]> sortedCs = cities.stream().sorted((p, q) -> {
            if (p[0] != q[0]) {
                return p[0] - q[0]; // cost asc
            } else {
                return q[0] - p[0]; // gain desc，贪心
            }
        }).collect(Collectors.toList());

        Iterator<int[]> it = sortedCs.iterator();
        int lastV = -1, cur;
        while (it.hasNext()) {
            if ((cur = it.next()[0]) == lastV) {
                it.remove(); // cost eq, gain desc
            } else {
                lastV = cur;
            }
        }

        // f[i] = MAX{f[i-1], {gain[i] + f{j} | cost[j] satisfy}}
        int[] f = new int[sortedCs.size()];
        for (int i = 0; i < sortedCs.size(); i++) {
            f[i] = Math.max(f[i], f[i - 1]);
            int c1 = cities.get(i)[0];
            for (int j = i - 1; j >= 0; j--) {
                if (c1 - cities.get(j)[0] < k) {
                    f[i] = Math.max(f[i], cities.get(j)[1] + f[j]);
                }
            }
            // find right val in [0, i-1]
            // cost asc
            // rely c1 - ? < k
        }

        return f[sortedCs.size() - 1];
    }
}
