package lc_contest.w357;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @title lc6953
 * @description 判断是否能拆分数组
 * @create 2023/8/6 10:57
 */
public class lc6953 {
    public static void main(String[] args) {
        lc6953 r = new lc6953();
        List<Integer> nums = Stream.of(2, 1, 3).collect(Collectors.toList());
        int m = 5;
        System.out.println("ans " + r.canSplitArray(nums, m));
    }

    Map<String, Boolean> cachedRange = new HashMap<>();

    /**
     * @param nums
     * @param m
     * @return boolean
     * @description: <p>
     * nums{n}; m
     * break to n [?]{1}（dfs
     * break cons:
     * - sub len == 1
     * - sum sub >= m （前缀和
     * </p>
     * @author: maiqi
     * @update: 2023/8/6 11:00
     */
    public boolean canSplitArray(List<Integer> nums, int m) {
        int[] pSums = new int[nums.size() + 1];
        for (int i = 1; i < pSums.length; i++) {
            pSums[i] = pSums[i - 1] + nums.get(i - 1);
        }
        // [i, j] => psum =>
        return dfs(nums, 0, nums.size() - 1, m, (i, j) -> pSums[++j] - pSums[++i - 1]);
    }

    public boolean dfs(List<Integer> lst, int l, int r, int m, BiFunction<Integer, Integer, Integer> sumOfSubArr) {
        if (r - l + 1 <= 2) return true;
        if (cachedRange.getOrDefault(String.format("%d+%d", l, r), Boolean.FALSE)) return true;

        int lLen, rLen, lSum, rSum;
        for (int i = l; i <= r - 1; i++) {
            // [l,i] [i+1,r]
            lLen = i - l + 1; // >=1
            rLen = r - i; // >=1
            lSum = sumOfSubArr.apply(l, i);
            rSum = sumOfSubArr.apply(i + 1, r);
            if ((lLen == 1 || lSum >= m) && (rLen == 1 || rSum >= m)) {
                if (dfs(lst, l, i, m, sumOfSubArr) && dfs(lst, i + 1, r, m, sumOfSubArr)) {
                    return cachedRange.computeIfAbsent(String.format("%d+%d", l, r), k -> Boolean.TRUE);
                }
            }
        }

        return cachedRange.computeIfAbsent(String.format("%d+%d", l, r), k -> Boolean.FALSE);
    }
}
