package core.basic.double_ptr.sliding_window;

import utils.BsearchUtil;

import java.util.Arrays;

/**
 * @author maiqi
 * @title lc1498
 * @description 满足条件的子序列数目
 * @create 2023/7/31 20:09
 */
public class lc1498 extends BsearchUtil {

    static final int MOD = (int) (1e9 + 7);

    int[] pow2Mods = new int[((int) 1e5) + 10];

    /**
     * @param nums   子序列
     * @param target
     * @return int
     * @description: <p>
     * 1 <= nums[i] <= 106 正数
     * if sorted:
     * - 子序列不关心顺序，只关心最值，所以可排序
     * - 子序列 < 子区间
     * - 传递性
     * minV 必取，其他都可选可不选
     * [minV..maxV] sz => x^(sz-1)
     * - 子区间 容易 枚举 N^2
     * -
     * <p>
     * - 简化 ... t] [t'...
     * - t+minVal<=target
     * - t'.. 必然大于 target
     * - l,r 双指针得到若干最大区间
     *
     * </p>
     * @author: maiqi
     * @update: 2023/7/31 20:38
     */
    public int numSubseq(int[] nums, int target) {
        cachedCnt(nums.length);
        Arrays.sort(nums);
        // min + max <= target
        // N*logN
        int cj, ans = 0; // candidate of j
        for (int i = 0, j = nums.length - 1; i < nums.length && nums[i] * 2 <= target; i++) {
            int finalI = i;
            cj = this.indexOfRightBound$3(nums, i + 1, j, maxJVal -> nums[finalI] + maxJVal <= target);
            if (i + 1 <= cj && cj <= j && nums[cj] + nums[finalI] <= target) {
                // [i .. cj]
                ans = (ans + cachedCnt(cj - i)) % MOD;
                j = cj;
            } else {
                ans = (ans + 1) % MOD; // i++
            }
        }
        return ans;
    }

    public int cachedCnt(int szMinus1) {
        if (pow2Mods[szMinus1] != 0) return pow2Mods[szMinus1];

        // lazy compute 2^(sz-1)
        pow2Mods[0] = 1;
        for (int i = 1; i <= szMinus1; i++) {
            pow2Mods[i] = (2 * pow2Mods[i - 1]) % MOD;
        }

        return pow2Mods[szMinus1];
    }
}
