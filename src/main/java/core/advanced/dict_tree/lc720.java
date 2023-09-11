package core.advanced.dict_tree;

import java.util.Arrays;

/**
 * @author maiqi
 * @title lc720
 * @description TODO
 * @create 2023/9/8 22:06
 */
public class lc720 {

    public static void main(String[] args) {
        String[] words = {"w", "wo", "wor", "worl", "world"};
        lc720 r = new lc720();
        System.out.println(r.longestWord(words));
    }

    public class Node { // node of dict tree
        boolean end;

        Node[] ne;

        public Node() {
            ne = new Node[26];
        }

    }

    Node root = new Node();

    public void insert(String s) {
        Node u = root;
        for (char ch : s.toCharArray()) {
            int i = ch - 'a';
            if (u.ne[i] == null) {
                u.ne[i] = new Node();
            }
            u = u.ne[i];
        }
        u.end = true;
    }

    public boolean hasPrefix(String s) {
        Node u = root;
        for (char ch : s.toCharArray()) {
            int i = ch - 'a';
            if (u.ne[i] == null) {
                return false;
            }
            u = u.ne[i];
            if (!u.end) {
                return false;
            }
        }
        return true;
    }

    public String longestWord(String[] words) {
        Arrays.sort(words, (p, q) -> {
            if (p.length() != q.length()) {
                return -(p.length() - q.length()); // len desc
            }
            return p.compareTo(q); // alpha asc
        });

        Arrays.stream(words).forEach(this::insert);

        String res = "";
        for (String wd : words) {
            if (hasPrefix(wd)) {
                res = wd;
                break;
            }
            insert(wd); // dt
        }

        return res;
    }

}
