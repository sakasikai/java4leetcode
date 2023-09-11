package core.advanced.window;

import java.util.TreeMap;

/**
 * @author maiqi
 * @title lc1438
 * @description 绝对差不超过限制的最长连续子数组
 * @create 2023/9/5 22:43
 */
public class lc1438 {

    /*
    任意两个元素之间的绝对差 <= limit
    最长连续子数组的 长度
    range[j, i]
     */

    public static void main(String[] args) {
        lc1438 r = new lc1438();
        int[] a = {10, 1, 2, 4, 7, 2};
        r.longestSubarray(a, 5);
    }

    public int longestSubarray(int[] nums, int limit) {
        // fac = Max - Min
        TreeMap<Integer, Integer> val2cnt = new TreeMap<>(); // use Integer::compareTo

        int n = nums.length, maxV, minV, ans = 0;

        val2cnt.put(nums[0], 1);
        ans = 1;
        for (int i = 1, j = 0; i < n; i++) { // 双指针
            // add
            val2cnt.compute(nums[i], (k, v) -> v == null ? 1 : v + 1);

            // pop
            maxV = val2cnt.lastKey();
            minV = val2cnt.firstKey();
            while (j < i && (maxV = val2cnt.lastKey()) - (minV = val2cnt.firstKey()) > limit) {
                val2cnt.compute(nums[j], (k, v) -> v - 1);
                if (val2cnt.get(nums[j]) == 0) {
                    val2cnt.remove(nums[j]);
                }
                j++;
            } // <= limit or j==i

            // check
            if (maxV - minV <= limit) {
                ans = Math.max(ans, i - j + 1);
            }
        }

        return ans;
    }
}
