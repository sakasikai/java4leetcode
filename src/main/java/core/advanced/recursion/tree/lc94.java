package core.advanced.recursion.tree;

import java.util.LinkedList;
import java.util.List;

public class lc94 {
    List<Integer> res = new LinkedList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return res;
    }

    public void inorder(TreeNode cur){
        if(cur == null) return;

        inorder(cur.left);
        res.add(cur.val);
        inorder(cur.right);
    }
}
