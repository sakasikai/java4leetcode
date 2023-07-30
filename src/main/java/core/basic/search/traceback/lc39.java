package core.basic.search.traceback;

import java.util.LinkedList;
import java.util.List;

public class lc39 {
    List<List<Integer>> res = new LinkedList<List<Integer>>();
    LinkedList<Integer> path = new LinkedList<Integer>();

    public List<List<Integer>> combinationSum(int[] a, int target) {
        dfs(a, 0, target);
        return res;
    }

    public void dfs(int[] a, int cur, int target){
        if(target == 0){
            res.add(new LinkedList<>(path));  // 这里不能是path，也没有clone啥的
            return;
        }

        if(cur < a.length){
            if(target-a[cur]>=0){ // 递归的重复选取cur
                path.add(a[cur]);
                dfs(a, cur, target-a[cur]);
                path.removeLast();  // 没有removeLast
            }

            // 跳过cur，遍历下一个
            dfs(a, cur+1, target);
        }
    }
}
