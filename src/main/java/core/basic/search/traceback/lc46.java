package core.basic.search.traceback;

import java.util.LinkedList;
import java.util.List;

public class lc46 {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> path = new LinkedList<>();
    public List<List<Integer>> permute(int[] a) {
        dfs(a, 0);
        return res;
    }

    void dfs(int[] a, int state){
        if(path.size() == a.length){
            res.add(new LinkedList<>(path));
            return;
        }

        // TODO 二进制操作， 按位：&与 |或 ~取反，移位：<< >>
        for(int i=0; i<a.length; i++){
            if((state & 1<<i)!=0) continue;  // path中只能有一个a[i]

            // 每次加入一个没有的元素
            path.add(a[i]);
            state |= 1<<i; // i位置1

            dfs(a, state);

            path.remove(path.size() - 1);
            state &= ~(1<<i); // i位置0
        }
    }
}
