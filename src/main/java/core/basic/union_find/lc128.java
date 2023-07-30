package core.basic.union_find;

import java.util.HashMap;
import java.util.Map;

public class lc128 {
    public int longestConsecutive(int[] nums) {
        // 连续数字为一个集合
        // 并查集 hsh[val] = root
        // 遍历到 val时，把 val-1, val+1 都加到一个集合
        // 同时维护一个集合大小
        Map<Integer, Integer> p = new HashMap<>(), cnt = new HashMap<>();
        for(int x: nums) {
            p.put(x, x);
            cnt.put(x, 1);
        }

        int res = 0;
        for(int i=0; i<nums.length; i++) {
            int curNum = nums[i];
            int pcur, ppre;
            if (p.containsKey(curNum - 1)) {
                pcur = find(p, curNum);
                ppre = find(p, curNum - 1);

                //  pnext = find(p, curNum + 1);
                if(pcur != ppre){
                    p.put(ppre, pcur); // union to pcur
                    int tot =  cnt.get(pcur) + cnt.get(ppre);
                    cnt.put(pcur, tot); // 这些点集合的root是pcur
                }
            } // else do nothing

            res = Math.max(res, cnt.get(find(p, curNum)));
        }

        return res;
    }

    int find(Map<Integer, Integer> p, int x){
        if(p.get(x) != x) p.put(x, find(p, p.get(x)));
        return p.get(x);
    }
}
