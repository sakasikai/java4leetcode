package Contest.leetcode356;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * @author maiqi
 * @title lc6900
 * @description 统计完全子数组的数目
 * @create 2023/7/30 10:37
 */
public class lc6900 {
    public static void main(String[] args) {
        int[] in = {1, 3, 1, 2, 2};
        lc6900 r = new lc6900();
        System.out.println(r.countCompleteSubarrays(in));
    }
    /**
     * @description: 整个数组不同元素的数目
     * <p>
     *     双指针
     *     [i j] ws < ts
     *     j++ until ws == ts (else [i, end) not match all false
     *     then i++ if ws < ts ...
     *              else ws == ts, count All i,[j..end)
     * </p>
     * @author: maiqi
     * @param nums
     * @return int
     * @update: 2023/7/30 10:39
     */
    public int countCompleteSubarrays(int[] nums) {
        int n = nums.length;
        int ts = (int) Arrays.stream(nums).boxed().distinct().count();
        // intVal => exits in [i, j]
        // 统计词频法
        HashMap<Integer, Integer> win = new HashMap<>();
        int ans = 0;
        // [i, j] n = j-i+1
        for(int i=0, j=ts-1; i<n && j<n; ){
            if(win.values().stream().noneMatch(v -> v > 0)){
                IntStream.range(i, j-i+1).forEach(k->win.put(nums[k], 1 + win.getOrDefault(nums[k], 0)));
            }

            if(win.values().stream().filter(v->v>0).count() == ts){
                ans += n-j;
                // i++;
            } else {// ws < ts
                if(j == n-1) return ans;

                while(j+1 < n && win.values().stream().filter(v->v>0).count() < ts) {
                    j++;
                    win.put(nums[j], 1 + win.getOrDefault(nums[j], 0));
                } // j+1==n or eq
                if(win.values().stream().filter(v -> v > 0).count() == ts){ // ws == ts, add i, [j, end)
                    ans += n-j;
                } // i++;
            }

            win.computeIfPresent(nums[i], (k, val)->val - 1);
            i++;
        }

        return ans;
    }
}
