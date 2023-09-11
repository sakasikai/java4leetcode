package lc_contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author maiqi
 * @title t2
 * @description TODO
 * @create 2023/9/10 20:35
 */
public class t2 {

    public static class Node {
        int val;

    }

    public void del(Node node) {

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();

        while (m-- > 0) {
            int n = in.nextInt();
            int[] as = new int[n], bs = new int[n - 1];

            for (int i = 0; i < n; i++) {
                as[i] = in.nextInt();
            }
            for (int i = 0; i < n - 1; i++) {
                bs[i] = in.nextInt();
            }

            Arrays.sort(as);
            LinkedList<Integer> lst = new LinkedList<>();
            Arrays.stream(as).forEach(lst::add);

            int len, mid, i = 0;
            List<Double> res = new ArrayList<>();
            while ((len = n) >= 0) {
                if (len % 2 == 1) { // [0..2x+1) => [0..x-1) x [x+1,2x]
                    mid = (len - 1) / 2;
                    res.add(Double.valueOf(lst.get(mid)));
                } else { // [0..2x) => [0..x-1)[x,2x)
                    mid = len / 2;
                    res.add((lst.get(mid) + lst.get(mid - 1)) / 2.0);
                }

                lst.remove(bs[i++]);
                n--;
            }
            System.out.println(res);
        }
    }

        /*
           as[] of len n, => double-linked list

           bs[] of len n-1
           map bs values TO node, each time delete a node

           if(node.val < cur midValue)
         */

}
