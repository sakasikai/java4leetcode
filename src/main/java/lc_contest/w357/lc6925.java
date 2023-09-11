package lc_contest.w357;

/**
 * @author maiqi
 * @title lc6925
 * @description 故障键盘
 * @create 2023/8/6 10:32
 */
public class lc6925 {

    public static void main(String[] args) {
        lc6925 r = new lc6925();
        String in = "poiinter";
        System.out.println(r.finalString(in));
    }

    public String finalString(String s) {
        char[] chars = s.toCharArray();
        final int n = chars.length;
        char[] ans = new char[n];
        for (int r = 0, w = 0; r < n; r++) {
            if (chars[r] == 'i') {
                boolean canReverse = true;
                while (r + 1 < n && chars[r + 1] == 'i') {
                    canReverse = !canReverse;
                    r++;
                }
                if (canReverse) doReverse(ans, w);
            } else {
                ans[w++] = chars[r];
            }
        }

        return String.valueOf(ans).trim();
    }

    public void doReverse(char[] a, int end) {
        char[] buf = new char[a.length];
        System.arraycopy(a, 0, buf, 0, end);
        for (int r = end - 1, w = 0; r >= 0; ) {
            a[w++] = buf[r--];
        }
    }
}
