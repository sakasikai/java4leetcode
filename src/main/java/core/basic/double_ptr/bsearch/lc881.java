package core.basic.double_ptr.bsearch;

import utils.BsearchUtil;

import java.util.Arrays;

/**
 * @author maiqi
 * @title lc881
 * @description 双指针思路 + 二分的结果判断
 * @create 2023/7/30 17:50
 */
public class lc881 extends BsearchUtil {

    public static void main(String[] args) {
        lc881 r = new lc881();
        int[] in = {1, 5, 3, 5};
        System.out.println(r.numRescueBoats(in, 7));

        in = new int[]{445, 597, 385, 576, 291, 190, 187, 613, 657, 477};
        System.out.println(r.numRescueBoats(in, 1000));

        in = new int[]{3, 5, 3, 4};
        System.out.println(r.numRescueBoats(in, 5));

        in = new int[]{3, 2, 2, 1};
        System.out.println(r.numRescueBoats(in, 3));
    }

    /**
     * @description: <p>
     * 船的数量不限
     * 每艘船可以承载的最大重量为 limit
     * 每艘船最多可同时载两人
     * <p>
     * 承载所有人所需的最小船数
     * </p>
     * <p>
     * 排序 + 贪心 + 双指针
     * ans in (?, peo.len]
     * sort in natural order
     * ... l, ..., r ...
     * p[l] + p[r] > limit ==> r--, handled
     *
     * </p>
     * @update: 2023/7/30 17:51
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length;
        int ans = 0, rUsed = 0;
        int l = 0, r = n - 1;

        while (l <= r) { // l， r表示未用的
            if (l == r) { // 只剩一辆车
                ans++;
                break;
            }

            // l < r
            if (people[l] + people[r] > limit) {
                // ... i1 [... i]
                // r左移到 sum <= limit
                int finalL = l;
                int r1 = indexOfRightBound$3(people, l + 1, r, mv -> people[finalL] + mv <= limit);
                if (people[r1] + people[finalL] <= limit) {
                    ans += r - r1; // r1 [r1+1, ... r]
                    r = r1;
                } else {
                    // l [r1 ... r]
                    ans += r + 1 - r1;
                    r = r1 - 1;
                }
            } else { // sum <= limit
                ans++;
                l++;
                r--;
            }
        }
        return ans;
    }
}
