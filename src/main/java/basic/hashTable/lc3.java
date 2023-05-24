package basic.hashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
 */
public class lc3 {
    public int lengthOfLongestSubstring(String s) {
        // hash: char -> latest_index(可能落在窗口外边
        // double pointer ==> sliding window
        Map<Character, Integer> h = new HashMap<>();
        int res = 0;
        for (int i=0, j=0; j<s.length(); j++){
            // 以j为右端点，i为左端点，满足要求的最长子串
            char c = s.charAt(j);
            if(!h.containsKey(c)) // 首次无脑存
                h.put(c, j);
            else { // 窗口中重复了，有两个，一个在h[c]，一个在j处
                i = Math.max(h.get(c) + 1, i); // 缩小窗口
                h.put(c, j); // 更新index
            }

            res = Math.max(res, j-i+1);
        }

        return res;
    }
}
