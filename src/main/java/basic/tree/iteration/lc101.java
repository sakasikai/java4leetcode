package basic.tree.iteration;

import java.util.Stack;

public class lc101 {
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

    public boolean isSymmetric(TreeNode root) {
        TreeNode lt = root.left, rt = root.right;
        Stack<TreeNode> sl = new Stack<>(), sr = new Stack<>();

        while (lt!=null || rt!=null || !sl.empty()){
            while(lt!=null){
                sl.push(lt); // process lt.left first
                lt = lt.left;
            }

            while(rt!=null){
                sr.push(rt); // process rt.left first
                rt = rt.right;
            }

            // 先加入再测试！
            if(sl.size()!=sr.size() || sr.empty()) return false;

            // 排除错误分支
            // process inOrder
            lt = sl.pop();
            rt = sr.pop();

            if(lt.val != rt.val) return false;

            lt = lt.right;
            rt = rt.left;
        }

        return true; // 通过所有错误测试
    }
}
