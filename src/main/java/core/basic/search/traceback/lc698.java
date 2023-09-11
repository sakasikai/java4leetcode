package core.basic.search.traceback;

import java.util.Arrays;

/**
 * @author maiqi
 * @title lc698
 * @description 划分为k个相等的子集
 * @create 2023/9/10 19:23
 */
public class lc698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        sum = Arrays.stream(nums).sum();
        if (sum % k != 0) return false;

        sum /= k;
        if (nums[nums.length - 1] > sum) return false;

        maxRound = k;
        return dfs(nums, 0, sum, 0); //
    }

    int state;

    int sum;

    int maxRound;

    public boolean dfs(int[] arr, int u, int target, int round) {
        if (round == maxRound - 1) return true; // 写法稳妥
        if (target == 0) {
            return dfs(arr, 0, sum, round + 1);
        }

        // u < n  ta > 0  rd > 0
        boolean f = true;
        for (int i = u, x, j; i < arr.length; i++) { // [u, end)
            if ((state >> i & 1) == 1) continue;
            if ((x = arr[i]) > target) break;
            // 在搜索 [u, end) 时，对arr[u] dfs 失败了，且 arr[u+1] == arr[u]，则不用处理 u+1
            if ((j = i - 1) >= u && (state >> j & 1) == 0 && arr[j] == arr[i]) continue; // TODO 不理解这个减枝不行

            state |= 1 << i;
            f = dfs(arr, i + 1, target - x, round);
            state &= ~(1 << i);
            if (f) return true;

            if (target == x) break;
        }
        return false;
    }

    public static void main(String[] args) {
        lc698 r = new lc698();
        int[] arr = {1, 1, 1, 1, 2, 2, 2, 2};
        int k = 4;
        System.out.println(r.canPartitionKSubsets(arr, k));
    }
}
