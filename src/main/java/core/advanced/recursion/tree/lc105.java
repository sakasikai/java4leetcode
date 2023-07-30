package core.advanced.recursion.tree;

import java.util.HashMap;
import java.util.Map;

public class lc105 {
    // root_val -> index of inorder_list
    Map<Integer, Integer> hsh = new HashMap<>();

    // preorder -> [mid] + [left] + [right]
    // get mid
    // inorder -> [left] + [mid] + [right]
    // given mid，get left and right
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for (int i = 0; i < n; i++) hsh.put(inorder[i], i);

        return dfs(preorder, inorder, 0, n-1, 0, n-1);
    }

    public TreeNode dfs(int[] preo, int[] ino, int pl, int pr, int il, int ir){
        // [pl, pr], [il, ir]
        int n = pr-pl+1;
        if(n <= 0) return null;
        TreeNode root = new TreeNode(preo[pl]);
        if(n == 1) return root;

        // [il, i-1] i [i+1, ir]
        // i [pl+1, npr] [npr+1, pr]
        // len_left == i-1 - il +1 == npr - (pl+1) +1
        int i = hsh.get(preo[pl]);
        int npr = i-1 - il + pl + 1;

        root.left = dfs(preo, ino, pl+1, npr, il, i-1);
        root.right = dfs(preo, ino, npr+1, pr, i+1, ir);

        return root;
    }
}
