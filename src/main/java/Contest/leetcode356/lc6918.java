package Contest.leetcode356;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author maiqi
 * @title lc6918
 * @description TODO
 * @create 2023/7/30 11:41
 */
public class lc6918 {
    public static void main(String[] args) {
        lc6918 r = new lc6918();
        System.out.println("==>" + r.minimumString("a", "b", "cba") + "\n");
        System.out.println("==>" + r.minimumString("a", "b", "cac") + "\n");
    }

    // 最长 {abc, acb, ...} 6个
    // if postfix == prefix len -= len(prefix)
    // 字典序最小
    public String minimumString(String a, String b, String c) {
        List<String> arr = Arrays.asList(a, b, c);

        return this.getPerm(3).stream()
                .map(idxLst -> idxLst.stream().map(arr::get))
                .map(strLst -> strLst.reduce(this::combine).orElse(""))
                .sorted((p, q) -> {
                    if (p.length() != q.length()) return p.length() - q.length(); // 长度 升
                    else return p.compareTo(q); // 字典序 升
                })
                .distinct().limit(1)
                .collect(Collectors.toList()).get(0);
    }

    public List<List<Integer>> getPerm(int N) {
        List<List<Integer>> res = new ArrayList<>();

        int stateN = 0;
        for (int i = 0; i < N; i++) {
            stateN |= 1 << i; // add i
            for (int j = 0; j < N; j++) {
                if ((stateN >> j & 1) == 1) continue; // j!=i
                stateN |= 1 << j; // add j
                for (int k = 0; k < N; k++) {
                    if ((stateN >> k & 1) == 1) continue; // k!=(j or i)
                    res.add(Arrays.asList(i, j, k));
                }
                stateN &= ~(1 << j);
            }
            stateN &= ~(1 << i);
        }
        return res;
    }

    /**
     * @description: 找到长度 最短 的字符串，且这三个字符串都是它的 子字符串 。
     * @update: 2023/7/30 17:30
     */
    public String combine(String p, String q) {
        if (q.contains(p)) return q;
        if (p.contains(q)) return p;

        // 不包含，只能前后拼接
        int s = getLargestPrefixLength$BF(p, q);
        return p + q.substring(s);
    }

    public int getLargestPrefixLength$BF(String p, String q) {
        String prefix;
        for (int len = q.length(); len >= 0; len--) {
            prefix = q.substring(0, len); // prefix
            if (p.length() - len >= 0 && p.endsWith(prefix)) {
                return len; // 短路
            } // no match
        }
        return 0;
    }
}
