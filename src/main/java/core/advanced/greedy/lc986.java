package core.advanced.greedy;

import java.util.ArrayList;
import java.util.List;

public class lc986 {
    public int[][] intervalIntersection(int[][] fl, int[][] sl) {
        List<int[]> res = new ArrayList<>();

        int n = fl.length, m = sl.length;

        for(int i=0, j=0; i<n && j<m;){
            // if相交，删除右端点短的，记录
            // else   依然删除右端点短的
            int[] tmp;
            boolean deli = fl[i][1] < sl[j][1];
            if((tmp = func(fl[i], sl[j]))!=null){ // TODO 先赋值，再判断不空
                res.add(tmp);
            }

            if(deli) i++;
            else j++;
        }

        return res.toArray(new int[0][]);
    }

    public int[] func(int[] c1, int[] c2){
        // TODO 区间重合的四种情况，只需用一个表达式
        if(Math.max(c1[0], c2[0]) <= Math.min(c1[1], c2[1]))
            return new int[]{Math.max(c1[0], c2[0]), Math.min(c1[1], c2[1])};
        else return null;
    }
}
