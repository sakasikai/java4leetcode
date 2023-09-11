package core.advanced.dp.treefy;

/*
 * @author maiqi
 * @title RobHouse3
 * @description 337. 打家劫舍 III
 * @create 2023/9/2 10:07
 */
public class RobHouse3 {

    public int rob(TreeNode root) {
        int[] f = rob_(root);
        return Math.max(f[0], f[1]);
    }

    public int[] rob_(TreeNode root) {
        if (root == null) return new int[]{0, 0};

        int[] sl = rob_(root.left), sr = rob_(root.right);

        int[] f = new int[2];
        f[0] = Math.max(sl[0], sl[1]) + Math.max(sr[0], sr[1]);
        f[1] = root.val + sl[0] + sr[0];

        return f;
    }


}
