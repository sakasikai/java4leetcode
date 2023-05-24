package advanced.dp.linear;

/**
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 */
public class lc96 {
    public int numTrees(int n) {
        // 二叉搜索树，中序遍历升序
        // f[n]: root + f[left] + f[right]，所以分成两个子树后，连续递增性质不变，仍是子问题
        // f[n] = iterate(root; f[left]*f[right])
        int[] f = new int[n+1];
        f[0] = 0;
        f[1] = 1;

        for(int i=2; i<=n; i++) // i为规模，问题从小到大，才能利用子问题
            for(int left=0; left<=i-1; left++) { // 这里的范围是考虑到root还有俩子树为0的情况
                int right = i - 1 - left;
                f[i] += Math.max(1, f[left]) * Math.max(1, f[right]); // edge: left = right = 0
            }

        return f[n];
    }
}
