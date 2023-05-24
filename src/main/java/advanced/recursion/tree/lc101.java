package advanced.recursion.tree;

public class lc101 {
    public boolean isSymmetric(TreeNode root) {
        if(root == null || testSymmetric(root.left, root.right)) return true;
        return false;
    }

    public boolean testSymmetric(TreeNode l, TreeNode r){
        if(l==null && r==null) return true;
        else if(l!=null && r!=null ){
            return l.val == r.val
                    && testSymmetric(l.left, r.right)
                    && testSymmetric(l.right, r.left);
        }

        return false;
    }
}
