package core.basic.search.traceback;

import java.util.LinkedList;
import java.util.List;

/**
 * @author maiqi
 * @title lc842
 * @description 842. 将数组拆分成斐波那契序列
 * @create 2023/9/13 14:43
 */
public class lc842 {

    public static void main(String[] args) {
        lc842 r = new lc842();
        System.out.println(r.splitIntoFibonacci("123456579"));
    }

    public List<Integer> splitIntoFibonacci(String num) {
        dfs(num, 0);
        return path;
    }

    LinkedList<Integer> path = new LinkedList<>();
    int expected = -1;

    String maxVal = String.valueOf(Integer.MAX_VALUE);

    public boolean dfs(String num, int u) {
        if (u >= num.length()) {
            return false;
        }
        String tmp;
        // end check
        if (expected != -1 && num.length() - u == (tmp = String.valueOf(expected)).length()) {
            if (num.substring(u).equals(tmp)) {
                path.add(expected);
                return true;
            } else return false;
        }

        String validNum;
        int preExpected;
        int fv, sv;
        for (int i = u; i < num.length(); i++) { // [u, i], [i+1, end)
            validNum = num.substring(u, i + 1);
            if (validNum.length() > 1 && validNum.startsWith("0")) continue;
            if (validNum.length() > maxVal.length()) continue;
            if (validNum.length() == maxVal.length() && maxVal.compareTo(validNum) < 0) continue;

            if (path.size() == 0) {
                if (num.length() - i - 1 < validNum.length()) continue;

                path.addLast(Integer.valueOf(validNum));
                if (dfs(num, i + 1)) return true; // 不回溯了
                path.removeLast();
            } else {
                preExpected = (fv = path.getLast()) + (sv = Integer.parseInt(validNum));
                if (num.length() - i - 1 < String.valueOf(preExpected).length()) continue;

                if (path.size() == 1) {
                    // fv, ex <= n-(i+1)
                    expected = preExpected;
                    path.addLast(sv);
                    if (dfs(num, i + 1)) return true; // 不回溯了
                    path.removeLast();
                    expected = -1;
                } else if (String.valueOf(expected).equals(validNum)) { // sz >= 2
                    expected = preExpected;
                    path.addLast(sv);
                    if (dfs(num, i + 1)) return true; // 不回溯了
                    path.removeLast();
                    expected = sv;

                    return false; // sv == expected
                }
            }
        }

        return false;
    }

}
