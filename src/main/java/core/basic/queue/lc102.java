package core.basic.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class lc102 {
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

    // TODO LinkedList实现Queue
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // 模拟FIFO的顺序
        Queue<TreeNode> q = new LinkedList<>(); // Queue的实现类
        if(root!=null) q.add(root); // edge

        while (!q.isEmpty()){
            int sz = q.size();
            List<Integer> line = new ArrayList<>();
            while(sz-- > 0){
                TreeNode cur = q.remove();
                line.add(cur.val);

                if(cur.left!=null) q.add(cur.left);
                if(cur.right!=null) q.add(cur.right);
            }

            res.add(line);
        }

        return res;
    }
}
