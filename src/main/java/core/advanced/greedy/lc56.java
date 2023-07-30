package core.advanced.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class lc56 {

    // TODO res.toArray(new int[N][]) -> int[][]
    public int[][] merge$3(int[][] intervals) {
        List<int[]> ret = new ArrayList<>();

        Arrays.sort(intervals, (p, q) -> {
            if (p[0] == q[0]) {
                return p[1] - q[1];
            }
            return p[0] - q[0];
        });

        int lastRight = Integer.MIN_VALUE;
        for (int[] aRange : intervals) {
            if (lastRight < aRange[0]) {
                // new
                lastRight = aRange[1];
                ret.add(aRange);
            } else {
                // combine
                ret.get(ret.size() - 1)[1] = (lastRight = Math.max(lastRight, aRange[1]));
            }

        }

        // List<int[]> convert to int[][]
        return ret.toArray(new int[ret.size()][]);
    }

    public int[][] merge(int[][] intervals) {
        // sort + greedy
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // 左端点asc
        int n = intervals.length, right = -1;
        LinkedList<int[]> res = new LinkedList<>(); // new里面类型推导
        for (int i = 0; i < n; i++) {
            if (right >= intervals[i][0]) {
                right = Math.max(right, intervals[i][1]);
                res.getLast()[1] = right;
            }else{
                right = intervals[i][1];
                res.add(intervals[i]); // param: int[]
            }
        }

        return res.toArray(new int[0][]); // param: T[] a
    }
}
