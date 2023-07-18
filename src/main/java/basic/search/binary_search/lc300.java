package basic.search.binary_search;

import java.util.LinkedList;

/**
 * @author maiqi
 * @title lc300
 * @description 子序列(不要求连续 ， 顺序严格)，最长
 * @create 2023/7/18 17:03
 */
public class lc300 extends BsearchUtil {

    public int lengthOfLIS(int[] nums) {
        LinkedList<Integer> is = new LinkedList<>();

        for (int num : nums) {
            if (is.isEmpty() || num > is.getLast()) {
                is.add(num);
            } else if (num < is.getLast()) {
                // 大于 num 的最小值
                int i = this.valueOfLeftBound$3(0, is.size() - 1, mid -> is.get(mid) >= num);
                is.set(i, num);
            }
        }

        return is.size();
    }
}
