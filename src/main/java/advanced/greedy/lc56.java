package advanced.greedy;

import java.util.*;

public class lc56 {

    // TODO LinkedList<int[]>
    // TODO res.toArray(new int[0][]) -> int[][]
    public int[][] merge(int[][] intervals) {
        // sort + greedy
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // 左端点asc
        int n = intervals.length, right=-1;
        LinkedList<int[]> res = new LinkedList<>(); // new里面类型推导
        for(int i=0; i<n; i++){
            if(right>=intervals[i][0]){
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
