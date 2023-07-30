package basic.search.binary_search;

import utils.BsearchUtil;

import java.util.Arrays;

/**
 * @title lc825
 * @description 整数，浮点数 造成bug
 * @create 2023/7/24 10:13
 */
public class lc825 extends BsearchUtil {
    public static void main(String[] args) {
        int[] ages = {49, 110, 13, 39, 45, 104, 9, 114, 86, 72, 13, 24, 10, 77, 103, 85, 9, 21, 66, 25};
        lc825 r = new lc825();
        System.out.println(r.numFriendRequests$3(ages));
    }

    /**
     * @param ages
     * @return int
     * @description: <p>
     * ages[y] > 0.5 * ages[x] + 7 // 单调性 大于t的最小值
     * ages[y] <= ages[x] // 排序后满足
     * ages[y] <= 100 || ages[x] >= 100
     * &&关系
     * </p>
     * @author: maiqi
     * @update: 2023/7/24 10:13
     */
    public int numFriendRequests$3(int[] ages) {
        int[] descAges = Arrays.stream(ages).boxed().sorted((p, q) -> q - p).mapToInt(Integer::intValue).toArray();
        // age[x] >= age[y] x<y
        int cnt = 0, n = descAges.length;
        int lastEqValIdx = -1;
        for (int i = 0; i < n; i++) {
            if (i + 1 < n) {
                // i [i+1, ..., y, ... end) ==>
                final int dt = descAges[i] + 14; // double t TODO 整数，浮点数冲突 造成 bug
                int y = this.indexOfRightBound$3(descAges, i + 1, n - 1, midV -> midV * 2 > dt);
                if (descAges[y] * 2 > dt && i + 1 <= y) {
                    cnt += y - (i + 1) + 1;
                }
            }

            // 相等值
            if (lastEqValIdx == -1 || descAges[lastEqValIdx] != descAges[i]) {
                lastEqValIdx = i;
            } else { // != -1 && val eq
                if (descAges[lastEqValIdx] * 2 > descAges[lastEqValIdx] + 14) { // >=15才能交友
                    cnt += i - lastEqValIdx;
                }
            }
        }

        return cnt;
    }
}
