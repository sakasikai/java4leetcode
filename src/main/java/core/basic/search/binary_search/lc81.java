package core.basic.search.binary_search;

import utils.BsearchUtil;

/**
 * @author maiqi
 * @title lc81
 * @description TODO
 * @create 2023/7/13 14:52
 */
public class lc81 extends BsearchUtil {
    public static void main(String[] args) {
        lc81 r = new lc81();

        System.out.println(r.search$3(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        System.out.println(r.search$3(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
    }

    public boolean search$3(int[] nums, int target) {
        int mayBeEqVal = nums[0];

        int i = nums.length - 1;
        while (i > 0 && nums[i] == mayBeEqVal) { // 刨除第一个元素(mayBeEqVal)本身
            i--;
        }
        // [mayBeEqVal, ?] [?+1, i]

        // find indexOf$3(?)
        int hiIndex = this.indexOf$3(nums, 0, i, (mVal) -> mVal >= mayBeEqVal, true);

        // find t in [mayBeEqVal, ?]
        int tIndex = -1, it = -1;
        if (target >= mayBeEqVal) {
            it = this.indexOf$3(nums, 0, hiIndex, (mVal) -> mVal <= target, true);

        } else {
            it = this.indexOf$3(nums, hiIndex + 1, i, (mVal) -> mVal <= target, true);
        }
        return it >= 0 && nums[it] == target;
    }

    /**
     * @param a
     * @param l
     * @param r
     * @param match
     * @return int
     * @description: [l, mid] test true ==》l = mid ==》二分最大值
     * @author: maiqi
     * @update: 2023/7/13 15:34
     */


}
