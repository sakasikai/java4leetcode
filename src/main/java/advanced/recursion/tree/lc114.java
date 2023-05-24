package advanced.recursion.tree;

/**
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同
 *
 */
public class lc114 {
    public void flatten(TreeNode root) {
        recur(root);
    }

    public TreeNode recur(TreeNode root){ // 返回尾节点
        if(root == null) return null;

        TreeNode l = recur(root.left), r = recur(root.right);
        if(l != null){ // 有左支
            l.right = root.right;
            root.right = root.left;
            root.left = null;
        }else{
            // do nothing
        }

        // edge 判断
        if(r!=null) return r;
        else if(l!=null) return l;
        else return root; // 返回是最右边的节点
    }
}
