package core.basic.search.binary_search;

import utils.BsearchUtil;

import java.util.Arrays;

// TODO 二分理解不深
public class lc31 extends BsearchUtil {
    // 字典序变大，最小程度变大
    // 从尾巴开始向前看，默认上升，直到峰点B(...AB...)，
    // 这时 (B...是递减的，找出比A大的最小数A‘，替换到A的位置，剩下的升序排（最小字典序
    // 12345  1234653
    // 1235-4 1235-346

    /**
     * @param nums
     * @description: TODO 字典序是：最右元素对 字典序 影响最小，从右往左 逐渐变大
     * @author: maiqi
     * @update: 2023/7/14 09:54
     */
    public void nextPermutation$3(int[] nums) {
        // 1,2,3 ==》1，3，2 ==》 3，2，1
        // [asc...] lo [hi, ...] ==> [asc...] lo' [asc...] where a[lo'] = min{x| x > a[lo]}
        int n = nums.length, lo = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (i - 1 >= 0 && nums[i - 1] < nums[i]) { // [5,1,1]
                lo = i - 1;
                break;
            }
        }

        if (lo == -1) {
            Arrays.sort(nums);
            return;
        }

        // B Search (desc, find_right)
        int x = nums[lo];
        int i = indexOfRightBound$3(nums, lo + 1, nums.length - 1, (mVal) -> mVal > x); // [1,5,1]
        // swap by lo, i
        nums[lo] = nums[i];
        nums[i] = x;
        Arrays.sort(nums, lo + 1, nums.length);
    }

    /**
     * 4321 => max => 1234(影响范围大
     * 1234 => min => 1243(小
     *
     * @param a
     */
    public void nextPermutation(int[] a) {
        int n = a.length;
        if (n == 1) return;

        for(int i=n-1; i>=0; i--){
            if(i-1>=0 && a[i-1]<a[i]){ // i为峰点
                // ..., i-1, i, ...

                int j = bsearch(a, i, n-1);
                // swap
                int tmp=a[i-1];
                a[i-1] = a[j];
                a[j] = tmp;
                // sort [i, ...]
                Arrays.sort(a, i, n);
                break;
            }

            if (i==0) Arrays.sort(a); // 边界
        }
    }

    int bsearch(int[] a, int left, int right){
        int val = a[left-1];
        // 注意！这里 a 是降序的！！ （不要用错模板
        // 另外，a[j] 要严格大于val （测试条件不能取等于号

        while(left < right){
            int mid = (left + right + 1) / 2;
            if(a[mid] > val) left = mid; // > val 可以取到
            else right = mid - 1; // <= val 的不能取到
        }

        return left;
    }
}
