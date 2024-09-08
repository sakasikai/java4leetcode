package lc_contest.tianyiyun;

import java.util.HashSet;
import java.util.Set;

/**
 * @author maiqi
 * @title t1
 * @description TODO
 * @create 2023/9/14 20:29
 */
public class t1 {
    public static void main(String[] args) {
        myCount("asd");
    }

    public static int myCount(String str) {
        // write code here
        // Map<Character, Integer> cnt = new HashMap<>();
        int cnt = 0;
        String target = "aeiou";
        Set<Character> st = new HashSet<>();
        for (char p = 'A'; p != 'Z' + 1; p++) {
            if (target.indexOf(Character.toLowerCase(p)) == -1) {
                st.add(p);
            }
        }
        for (Character ch : target.toCharArray()) st.add(ch);

        System.out.println(st);

        for (Character ch : str.toCharArray()) {
            if (st.contains(ch)) {
                cnt++;
            }
        }

        return cnt;
    }
}
