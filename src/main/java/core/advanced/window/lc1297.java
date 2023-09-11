package core.advanced.window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maiqi
 * @title lc1297
 * @description 1297. 子串的最大出现次数
 * @create 2023/9/5 16:48
 */
public class lc1297 {
    /*
     Map<String, Integer> win
     range[j, i] len in [mins, maxs]

     */
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<Character, Integer> win = new HashMap<>();
        Map<String, Integer> candis = new HashMap<>();
        int n = s.length(), wl; // window length
        char ch;

        // 滑动窗口(双指针)，找候选字符串
        for (int i = 0, j = 0; i < n; i++) { // win[j, i]
            while (i < n && (wl = i - j + 1) < minSize) {
                win.compute(ch = s.charAt(i), (c, v) -> v == null ? 1 : v + 1);
                i++;
            } // add; wl >= min

            if (i >= n) continue;
            win.compute(ch = s.charAt(i), (c, v) -> v == null ? 1 : v + 1);

            while (j <= i && ((wl = i - j + 1) > maxSize || wl > minSize || win.size() > maxLetters)) {
                win.compute(ch = s.charAt(j), (c, v) -> v - 1);
                if (win.get(ch) == 0) {
                    win.remove(ch);
                }
                j++;
            } // rm; wl<=max && diff ch <= maxLetters
            // 一直缩减到 minSize
            if (win.size() <= maxLetters && (wl = i - j + 1) == minSize) {
                candis.compute(s.substring(i - wl + 1, i + 1), (k, v) -> v == null ? 1 : v + 1); // [?, i] of len k
            }

        }

        return candis.values().stream()
                .max(Integer::compareTo).orElse(0);
    }

}
