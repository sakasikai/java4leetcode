package core.basic.tree.iteration;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class lc94 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {

            this.val = val;

            this.left = left;

            this.right = right;

        }
    }

    // TODO 利用Stack来迭代的遍历树
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stk = new Stack<>();
        List<Integer> res = new LinkedList<>();
        while(root!=null || !stk.empty()){
            while (root!=null){
                stk.push(root); // 优先处理left分支，所以将当前节点保存起来
                root = root.left;
            }

            root = stk.pop(); // 直到走到空节点，这时这一分支的节点都保存在stack中
            res.add(root.val);

            root=root.right; // 处理right分支
        }
        return res;
    }
}
