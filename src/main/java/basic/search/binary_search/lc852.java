package basic.search.binary_search;

import utils.BsearchUtil;

/**
 * @author maiqi
 * @title lc852
 * @description 山脉数组的峰顶索引
 * @create 2023/7/24 20:31
 */
public class lc852 extends BsearchUtil {
    /**
     * @param arr
     * @return int
     * @description: 0 [1, ... t-1] t [t+1, ... n-2] n-1
     * [1..t] => arr[mid] > arr[mid-1]
     * @author: maiqi
     * @update: 2023/7/24 20:32
     */
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length, l = 1, r = n - 2;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (arr[mid] > arr[mid - 1]) { // match range with mid inclusive
                l = mid;
            } else { // escape range with mid ex-clusive
                r = mid - 1;
            }
        }
        return l;
    }
}
