package core.basic.double_ptr;

import java.util.Arrays;

/**
 * @author maiqi
 * @title lc1877
 * @description TODO
 * @create 2023/8/4 08:29
 */
public class lc1877 {
    /**
     * @param nums
     * @return int
     * @description: <p>
     * 数对 => 数对和
     * 数对数组
     * 最大数对和
     * <p>
     * 将 nums 中的元素分成 n / 2 个数对, st
     * - 最大数对和 的值 最小
     * - 返回最小的 最大数对和
     * </p>
     * <p>
     * sort
     * - nums asc
     * - [miv.. ]n/4, [..mav]n/4
     * - 数组内只有两个数的情况是平凡的。我们可以考虑数组中只有四个数
     * (x1..4) <=  (x1, x4) (x2, x3) 的拆分方法对应的最大数对和一定是最小的。
     *
     * </p>
     * @author: maiqi
     * @update: 2023/8/4 08:33
     */
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, maxBound = -1;
        for (int l = 0, r = n - 1; l < r; l++, r--) {
            maxBound = Math.max(nums[l] + nums[r], maxBound);
        }
        return maxBound;
    }
}
