package basic.search.binary_search;

import java.util.function.Predicate;

/**
 * @author maiqi
 * @title lc81
 * @description TODO
 * @create 2023/7/13 14:52
 */
public class lc81 {
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

        // find indexOf(?)
        int hiIndex = this.indexOf(nums, 0, i, (mVal) -> mVal >= mayBeEqVal);

        // find t in [mayBeEqVal, ?]
        int tIndex = -1, it = -1;
        if (target >= mayBeEqVal) {
            it = this.indexOf(nums, 0, hiIndex, (mVal) -> mVal <= target);

        } else {
            it = this.indexOf(nums, hiIndex + 1, i, (mVal) -> mVal <= target);
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
    public int indexOf(int[] a, int l, int r, Predicate<Integer> match) {
        if (l > r) return -1;

        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (match.test(a[mid])) { // match range with mid inclusive
                l = mid;
            } else { // escape range with mid ex-clusive
                r = mid - 1;
            }
        }
        return l;
    }
}
