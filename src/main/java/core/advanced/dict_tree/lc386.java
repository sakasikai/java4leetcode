package core.advanced.dict_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author maiqi
 * @title lc368
 * @description 386. 字典序排数
 * @create 2023/9/9 10:33
 */
public class lc386 {

    public static void main(String[] args) {
        lc386 r = new lc386();
        System.out.println(r.lexicalOrder(150));
    }

    class Node {
        int val;
        boolean end;
        Node[] ne;

        Node() {
            ne = new Node[10];
        }
    }

    Node root = new Node();

    public void insert(int num) {
        Node u = root;
        for (int i : getSingles(num)) {
            if (u.ne[i] == null) {
                u.ne[i] = new Node();
            }
            u = u.ne[i];
        }
        u.val = num;
        u.end = true;
    }

    public List<Integer> getSingles(int num) { // 1..n
        List<Integer> res = new ArrayList<>();
        while (num > 0) {
            res.add(num % 10);
            num /= 10;
        }
        Collections.reverse(res);
        return res;
    }

    public List<Integer> dfs(Node rt) {
        if (rt == null) return null;
        List<Integer> res = new ArrayList<>();

        if (rt.end) {
            res.add(rt.val);
        }

        for (int i = 0; i <= 9; i++) {
            if (rt.ne[i] != null) {
                res.addAll(dfs(rt.ne[i]));
            }
        }

        return res;
    }

    public List<Integer> lexicalOrder(int n) {
//        IntStream.range(1, n+1).forEach(this::insert);
//        return dfs(root);
        List<Integer> res = new ArrayList<>();
        IntStream.range(1, 9 + 1).forEach(i -> dfsOnDictTree(res, i, n));
        return res;
    }

    public void dfsOnDictTree(List<Integer> res, int u, int limit) {
        if (u <= limit) {
            res.add(u);
            // 10进制下，u会向前进一位，这就是dict tree的顺序
            IntStream.range(0, 9 + 1).
                    forEach(i -> dfsOnDictTree(res, u * 10 + i, limit));
        }
    }
}
