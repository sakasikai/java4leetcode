package core.advanced.dp.twoD;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maiqi
 * @title lc1027
 * @description 最长等差数列
 * @create 2023/7/22 17:47
 */
public class lc1027 {

    /**
     * @param nums
     * @return int
     * @description: <p>
     * for 0..i
     * f[i] = max{f[j] + 1 | j<i && bias of f[j] == a[i] - a[j]}
     * </p>
     * <p>
     * 对所有bias做动态规划
     * 从所有bias中找出最大的长度
     * </p>
     * @author: maiqi
     * @update: 2023/7/22 17:48
     */
    public int longestArithSeqLength(int[] nums) {
        int ans = 0;
        Map<Integer, Map<Integer, Integer>> fIdxBias = new HashMap<>();
        Map<Integer, Boolean> biasCache = new HashMap<>();
        int bias = 0;
        for (int i = 0; i < nums.length; i++) {
            biasCache.clear();
            for (int j = i - 1; j >= 0; j--) {
                if (biasCache.computeIfAbsent(bias = nums[i] - nums[j], k -> Boolean.TRUE)) {
                    // f[i][bias] = f[j][bias] + 1
                    fIdxBias.computeIfAbsent(j, k -> new HashMap<>()).putIfAbsent(bias, 1);
                    final int aLen = fIdxBias.get(j).get(bias) + 1;
                    fIdxBias.computeIfAbsent(i, k -> new HashMap<>()).putIfAbsent(bias, aLen);
                    ans = Math.max(ans, aLen);
                }
            }
        }
        return ans;
    }
}
