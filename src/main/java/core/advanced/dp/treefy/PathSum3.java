package core.advanced.dp.treefy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maiqi
 * @title PathSum3
 * @description 437. 路径总和 III
 * @create 2023/9/2 10:38
 */
public class PathSum3 {

    public static void main(String[] args) {
        PathSum3.Prefix_Tree r = new PathSum3.Prefix_Tree();
    }

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;

        int ret = dfs(root, targetSum);
        ret += pathSum(root.left, targetSum);
        ret += pathSum(root.right, targetSum);

        return ret;
    }

    public int dfs(TreeNode root, long targetSum) {
        // 以root为 路径起点，往下走，有多少条路径
        if (root == null) return 0;

        int sl = dfs(root.left, targetSum - root.val),
                sr = dfs(root.right, targetSum - root.val);

        int retVal = root.val == targetSum ? 1 : 0; // 找到终点？
        return retVal + sl + sr;
    }

    /*
    枚举检查所有root，
    根据子树，来计算
    f[u] = sumL, sumR


    dfs从Root出发搜索，
    汇总所有root结果
     */


    public static class Prefix_Tree {

        public int prefixSum(TreeNode root, int targetSum) {
            prefix.put(0L, 1);
            return dfs(root, 0, targetSum);

        }

        Map<Long, Integer> prefix = new HashMap<>();


        public int dfs(TreeNode u, long cum, int targetSum) {
            if (u == null) return 0;

            long cum4u = cum + u.val; // flag, root, ..., u
            int ret = prefix.getOrDefault(cum4u - targetSum, 0);

            prefix.compute(cum4u, (k, v) -> v == null ? 1 : v + 1); // 增加 prefix[cum4u] 的计数
            ret += dfs(u.left, cum4u, targetSum);
            ret += dfs(u.right, cum4u, targetSum);
            prefix.computeIfPresent(cum4u, (k, v) -> v - 1); // 回溯，处理其他分支

            return ret;
        }

        /*
            前缀和
            targetSum = prefix[cur] - ??
            ?? used for hashmap
         */

    }
}
