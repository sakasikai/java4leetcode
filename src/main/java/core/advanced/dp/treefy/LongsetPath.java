package core.advanced.dp.treefy;

/**
 * @author maiqi
 * @title LongsetPath
 * @description 687. 最长同值路径
 * @create 2023/9/2 10:23
 */
public class LongsetPath {

    /*
    f[u] = 以此子树为规模的最长路径，经过u
     */
    int maxPath = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return maxPath;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;

        int l = dfs(root.left),
                r = dfs(root.right);

        int curMaxPath = 0, retVal = 0;

        if (root.left != null && root.left.val == root.val) {
            curMaxPath = l + 1;
            retVal = l + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            curMaxPath += r + 1;
            retVal = Math.max(retVal, r + 1);
        }

        maxPath = Math.max(maxPath, curMaxPath);

        return retVal;
    }
}
