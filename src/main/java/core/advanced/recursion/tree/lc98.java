package core.advanced.recursion.tree;

// TODO 难想
public class lc98 {
    public boolean isValidBST(TreeNode root) {
        // 找前继，后继，复杂度 O(NM)
        // 带着边界验证，复杂度 O(N)
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean dfs(TreeNode root, long lb, long rb){ // bounds exclusive
        // root.val 要和两个子树的(最小，最大)比较
        // 向下遍历时，对一个node，有时要比较left_bound，有时比较right_bound
        // 用递归，可以避免查找root的前后继，而是不断划分子问题，直到一个节点的时候
        if(root==null) return true;

        // consider root + left + right
        return lb<root.val && root.val<rb
                && dfs(root.left, lb, root.val)
                && dfs(root.right, root.val, rb);
    }
}
