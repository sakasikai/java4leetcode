package core.basic.search.traceback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author maiqi
 * @title lc216
 * @description 216. 组合总和 III
 * @create 2023/9/10 19:14
 */
public class lc216 {

    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(1, k, n);
        return res;
    }

    public void dfs(int u, int size, int target) {
        if (path.size() == size && target == 0) {
            res.add(new LinkedList<>(path));
            return;
        }

        for (int i = u; i <= 9; i++) {
            if (i > target) return;

            if (path.size() < size && i <= target) {
                path.add(i);
                dfs(i + 1, size, target - i);
                path.removeLast();
            }
        }
    }


}
