package core.basic.search.traceback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author maiqi
 * @title lc40
 * @description 40. 组合总和 II
 * @create 2023/9/10 19:05
 */
public class lc40 {

    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);
        dfs(candidates, 0, target);
        return res;
    }

    public void dfs(int[] arr, int u, int sum) {
        if (sum == 0) {
            res.add(new LinkedList<>(path));
            return;
        }

        for (int i = u, j; i < arr.length; i++) {
            if (arr[i] > sum) break; //asc
            if ((j = i - 1) >= u && arr[i] == arr[j]) continue; // 漂亮！！

            path.add(arr[i]);
            dfs(arr, i + 1, sum - arr[i]);
            path.removeLast();
        }
    }
}
