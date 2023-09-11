package core.advanced.dict_tree;

import java.util.Arrays;

/**
 * @author maiqi
 * @title lc421
 * @description 421. 数组中两个数的最大异或值
 * <p>
 * 以二进制形式插入num
 * </p>
 * @create 2023/9/9 11:29
 */
public class lc421 {
    class Node {
        Node[] ne;
        boolean end;

        Node() {
            ne = new Node[2];
        }
    }

    Node root = new Node();

    public void insert(int n) {
        Node u = root;
        for (int i = start, j; i >= 0; i--) {
            j = n >> i & 1;
            if (u.ne[j] == null) {
                u.ne[j] = new Node();
            }
            u = u.ne[j];
        }
        u.end = true;
    }

    public int find(int num) {
        // 从高位向低位遍历，不断缩小搜索范围
        int maxXOR = 0;
        Node u = root;
        for (int i = start, j, k; i >= 0; i--) {
            j = num >> i & 1;
            // k = ~j & 1; // 1是mask
            k = j ^ 1;
            if (u.ne[k] != null) { // i位 有异或
                u = u.ne[k];
                maxXOR |= 1 << i;
            } else { // i位 没有异或
                u = u.ne[j];
            }
        }
        return maxXOR;
    }

    int start = 31;

    /**
     * @param nums
     * @return int
     * @description: <p>
     * {@link Integer#numberOfLeadingZeros}
     * </p>
     * @author: maiqi
     * @update: 2023/9/9 12:58
     */
    public int findMaximumXOR(int[] nums) {
        int maxV = Arrays.stream(nums).max().orElse(Integer.MAX_VALUE);
        start = 31 - Integer.numberOfLeadingZeros(maxV);

        Arrays.stream(nums).forEach(this::insert);

        int ans = 0;
        for (int v : nums) {
            ans = Math.max(ans, find(v));
        }

        return ans;
    }


    public void reset() {
        root = new Node();
        start = 31;
    }

    public static void main(String[] args) {
        String s = "sa", p = "sa", q = "s" + "a";

        String t = new String("sa"), t2 = new String("sa");

        System.out.println(s == p);
        System.out.println(s == q);
        System.out.println(s.equals(t));
        System.out.println(s != t);
        System.out.println(s == t.intern());

        System.out.println(t != t2);

        String[] a1 = {s, p}, a2 = {s, p};

        System.out.println(a1 != a2);
    }
}
