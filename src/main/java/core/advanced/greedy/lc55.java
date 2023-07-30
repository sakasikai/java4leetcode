package core.advanced.greedy;

// TODO 贪心没思路
public class lc55 {
    public boolean canJump(int[] a) {
        int n = a.length;

        for(int i=0, border=0; i<n; i++){
            // 连续行走
            if(border < i) return false; // 走不到第i步
            border = Math.max(border, i+a[i]); // 最远边界
        }

        return true;
    }
}
