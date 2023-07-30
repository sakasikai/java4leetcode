package core.advanced.greedy;

import java.util.ArrayList;
import java.util.List;

// TODO 区间模拟难
// 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
// 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

/**
 * new_range 有三种可能，考虑中间的可能
 * 相交情况，包含多个 ==》一点不相交
 */
public class lc57 {
    public int[][] insert(int[][] rgs, int[] rg) {
        List<int[]> res = new ArrayList<>();

        int l = rg[0], r = rg[1], pivot = -1; // pivot指向最后处理的rgs[i]
        boolean isIntersect = false;

        for (int i = 0; i<rgs.length && rgs[i][0] <= l; ) {

            if(rgs[i][1] >= l){ // 左边第一个rgs[i]相交
                res.add(rgs[i]);
                pivot = i;

                isIntersect = true;
                res.get(res.size()-1)[1] = Math.max(rgs[i][1], r); // update
                break;
            }

            // 没相交
            res.add(rgs[i]);
            pivot = i++;
        }

        if(!isIntersect){ // 没有相交，rg在前/在中间/在后面
            res.add(rg); // 要额外加上rg
        }

        // 相交了，rg在前/在中间/在后面
        for (int i = pivot+1; i < rgs.length; i++) {
            if(rgs[i][0] <= res.get(res.size()-1)[1]){
                // 延长
                res.get(res.size()-1)[1] = Math.max(rgs[i][1], r);
            }else res.add(rgs[i]);
        }

        return res.toArray(new int[res.size()][]);
    }
}

/**
 * [[1,3],[6,9]]
 * [2,5]
 * [[1,2],[3,5],[6,7],[8,10],[12,16]]
 * [4,8]
 * []
 * [5,7]
 * [[1,5]]
 * [6,8]
 * [[1,5]]
 * [0,3]
 */
