package core.advanced.dp.treefy;

/**
 * @author maiqi
 * @title TreeNode
 * @description TODO
 * @create 2023/9/2 10:24
 */

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}