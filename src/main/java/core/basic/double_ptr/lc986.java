package core.basic.double_ptr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maiqi
 * @title lc986
 * @description 区间列表的交集
 * @create 2023/7/30 21:53
 */
public class lc986 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int n = firstList.length, m = secondList.length;
        if (n == 0 || m == 0) {
            return new int[][]{};
        }

        int[][] lst = merge(firstList, secondList);
        List<int[]> res = new ArrayList<>();

        // i, j 双指针
        for (int i = 0, j = -1; i < n + m; i++) {
            if (j == -1) { // first in
                j = lst[i][1];
                continue;
            }

            // i < i'
            // check j and j'
            if (lst[i][0] <= j) {
                res.add(new int[]{lst[i][0], Math.min(lst[i][1], j)});
                j = Math.max(j, lst[i][1]);
            } else { // 不相交
                j = lst[i][1];
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    int[][] merge(int[][] p, int[][] q) {
        int n = p.length, m = q.length;
        int[][] res = new int[n + m][];
        for (int i = 0, j = 0, k = 0; i < n || j < m; ) {
            if (i >= n) {
                res[k++] = q[j++];
            } else if (j >= m) {
                res[k++] = p[i++];
            } else if (p[i][0] < q[j][0]) {
                res[k++] = p[i++];
            } else {
                res[k++] = q[j++];
            }
        }

        return res;
    }
}
