package lc_contest;

public class t1 {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 返回节点权值1个数比0的个数多一的路径数
     *
     * @param root TreeNode类 权值为0和1的二叉树根节点
     * @return int整型
     */
    public int pathNumber(TreeNode root) {
        // write code here
        dfs(root, 0);
        return res;
    }

    int res = 0;

    public void dfs(TreeNode root, int bias) {
        if (root == null) return;

//        System.out.println(root.val + "[" + bias);

        if (root.left == null && root.right == null) {
            if (bias == 1) res++;
            return;
        }


        dfs(root.left, root.val == 1 ? bias + 1 : bias - 1);
        dfs(root.right, root.val == 1 ? bias + 1 : bias - 1);
    }
}