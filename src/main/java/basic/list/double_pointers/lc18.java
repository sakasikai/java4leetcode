package basic.list.double_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @title lc18
 * @description 四数之和
 * @create 2023/7/15 09:39
 */
public class lc18 {
    /**
     * @param nums
     * @param target
     * @return List<List < Integer>>
     * @description: 排序 + 去重；双指针
     * @author: maiqi
     * @update: 2023/7/15 09:54
     */
    public List<List<Integer>> fourSum$3(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);
        final int N = nums.length;
        for (int i = 0; i < N; i++) {
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < N; j++) {
                if (j - 1 >= i + 1 && nums[j] == nums[j - 1]) continue;
                for (int p = j + 1, q = N - 1; p < q; p++) {
                    if (p - 1 >= j + 1 && nums[p] == nums[p - 1]) continue;

                    while (q > p && sum(nums, i, j, p, q) > (long) target) {
                        q--;
                    }
                    if (q > p && sum(nums, i, j, p, q) == (long) target) {
                        ans.add(Stream.of(nums[i], nums[j], nums[p], nums[q]).collect(Collectors.toList()));
                    }
                }
            }
        }

        return ans;
    }

    public long sum(int[] a, int i, int j, int p, int q) {
        return (long) a[i] + (long) a[j] + (long) a[p] + (long) a[q];
    }

}
