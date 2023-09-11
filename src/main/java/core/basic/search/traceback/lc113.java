package core.basic.search.traceback;

import core.advanced.recursion.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author maiqi
 * @title lc113
 * @description 路径总和 II
 * @create 2023/9/10 17:34
 */
public class lc113 {

    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return res;
    }

    public void dfs(TreeNode root, int sum) {
        if (root == null) return;

        path.addLast(root.val);
        if (root.left == null && root.right == null) {
            // 叶子节点
            if (sum == root.val) {
                res.add(new LinkedList<>(path));
            }

            path.removeLast();
            return;
        }


        dfs(root.left, sum - root.val);
        dfs(root.right, sum - root.val);
        path.removeLast();
    }
}
