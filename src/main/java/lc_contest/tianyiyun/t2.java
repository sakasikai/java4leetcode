package lc_contest.tianyiyun;

import java.util.HashSet;
import java.util.Set;

/**
 * @author maiqi
 * @title t2
 * @description TODO
 * @create 2023/9/14 21:05
 */
public class t2 {
    public static void main(String[] args) {
        String a = "mnmnp", b = "mnpmnp";
        t2 r = new t2();
        System.out.println(r.countSubstring(a, b));
    }

    static final long M = 299, Q = Long.MAX_VALUE;

    public int countSubstring(String a, String b) {

        long[] p = new long[a.length() + 1], q = new long[b.length() + 1];
        for (int i = 1; i <= a.length(); i++) {
            p[i] = ((p[i - 1] * M) % Q + (a.charAt(i - 1))) % Q;
        }

        for (int i = 1; i <= b.length(); i++) {
            q[i] = ((q[i - 1] * M) % Q + (b.charAt(i - 1))) % Q;
        }

        Set<Long> happens = new HashSet<>();
        long val;
        for (int len = 1; len <= Math.min(a.length(), b.length()); len++) {
            for (int i = 0, k; (k = i + len - 1) < a.length(); i++) {
                for (int j = 0, l; (l = j + len - 1) < b.length(); j++) {
                    // [i, k], i+len), [j, l], j+len)
                    if ((val = rangeHash(p, i + 1, k + 1)) == rangeHash(q, j + 1, l + 1) &&
                            !happens.contains(val)) {
                        // System.out.println(a.substring(i, i+len));
                        happens.add(val);
                    }
                }
            }
        }

        return happens.size();
    }

    public static long rangeHash(long[] arr, int i, int j) {
        // [i, j]
        int len = j - i + 1;
        return (arr[j] - (arr[i - 1] * (long) Math.pow(M, len)) % Q) % Q;
    }
}
