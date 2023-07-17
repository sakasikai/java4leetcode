package basic.search.binary_search;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maiqi
 * @title lc400
 * @description TODO
 * @create 2023/7/17 13:49
 */
public class lc400 {
    public int findNthDigit(int n) {
        int l = 1, r = 1000000000; // TODO int溢出
        while (l < r) {
            int mid = (l + r) / 2;
            if (n <= getBias(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        // s, num{... nth ...}
        int start = (int) getBias(l - 1);
        // 复杂逻辑 要写好思路
        // l-1 (bias = start) l with bias {... n ...}
        return Integer.parseInt(("" + l).substring((n - start - 1), (n - start - 1) + 1));
    }

    public long getBias(int i) {
        int n = String.valueOf(i).length();
        long bias = 0;
        for (int j = 1; j <= n; j++) {
            if (j != n) {
                bias += this.cachedBase(j);
            } else {
                bias += j * (i - (long) Math.pow(10, j - 1) + 1);
            }
        }
        return bias;
    }

    public long cachedBase(int j) {
        return cache.computeIfAbsent(j, k -> j * 9 * (long) Math.pow(10, k - 1));
    }

    // len -> bias
    Map<Integer, Long> cache = new HashMap<>();
}
