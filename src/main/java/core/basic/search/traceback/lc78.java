package core.basic.search.traceback;

import java.util.LinkedList;
import java.util.List;

public class lc78 {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> subsets(int[] a) {
        dfs(a, 0);
        return res;
    }

    public void dfs(int[] a, int start){
        res.add((List<Integer>) path.clone());
//        res.add(new LinkedList<>(path));

        for(int i=start; i<a.length; i++){ // 按顺序选取子集
            path.add(a[i]);
            dfs(a, i+1);
            path.removeLast();
        }
    }
}
