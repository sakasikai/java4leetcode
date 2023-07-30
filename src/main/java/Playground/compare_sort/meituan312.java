package Playground.compare_sort;

import Playground.utils.ArrPrint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// details: https://blog.csdn.net/weixin_40509040/article/details/103375440
public class meituan312 {
    public static void main(String[] args) {
        testArraySort();
        System.out.println();
        testListSort();
    }

    /**
     * 可以用 Arrays.sort(int[] arr, Comparator c), 这是专门针对内置类型的
     * 可使用Comparator.comparingInt()
     * lambda expression 类型推导，类似auto关键字
     */
    // TODO Arrays.sort + Comparator + lambda
    public static void testArraySort() {
        int[][] intervals = new int[][]{{1, 6}, {3, 5}, {2, 4}};
        ArrPrint.print2d(intervals, "initial sequence");

        Arrays.sort(intervals, (int[] a, int[] b) -> a[0] - b[0]); // 默认顺序对应 ascending
        ArrPrint.print2d(intervals, "sort(asc) with 1st option");

        // equally ToIntFunction<? super T> keyExtractor
        // keyExtractor.applyAsInt(element)
        Arrays.sort(intervals, Comparator.comparingInt(a -> -a[0]));
        ArrPrint.print2d(intervals, "sort(desc) with 1st option");

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // 反之，descending
        ArrPrint.print2d(intervals, "sort(asc) with 2nd option");

        Arrays.sort(intervals, (a, b) -> b[1] - a[1]); // 反之，descending
        ArrPrint.print2d(intervals, "sort(desc) with 2nd option");
    }

    /**
     * 二维List要用内置的 [list_instance].sort(Comparator c)
     * 不可以用 Arrays.sort(int[] arr, Comparator c)啦！这是针对内置类型的
     *
     * 可使用Comparator.comparingInt()
     * lambda expression 类型推导，类似auto关键字
     */
    // TODO List.sort + Comparator + lambda
    public static void testListSort(){
        List<List<Integer>> arr = new ArrayList<>();
        for(int i=0; i<4; i++){
            arr.add(new ArrayList<>());
            arr.get(i).add(i);
            arr.get(i).add(-(i+2));
        }

        System.out.println("initial sequence");
        System.out.println(arr);

        // lambda expression 类型推导，类似auto关键字
        arr.sort((c1, c2) -> -(c1.get(0) - c2.get(0)));
        System.out.println("sort(desc) using 1st option");
        System.out.println(arr);

        arr.sort(Comparator.comparingInt(c -> -c.get(1)));
        System.out.println("sort(desc) using 2nd option");
        System.out.println(arr);

        arr.sort(Comparator.comparingInt(c -> c.get(1)));
        System.out.println("sort(asc) using 2nd option");
        System.out.println(arr);

        arr.sort((List<Integer> c1, List<Integer> c2) -> c1.get(0) - c2.get(0));
        System.out.println("sort(asc) using 1st option");
        System.out.println(arr);
    }

    /**
     *
     * array 和 collection 混淆 ❌
     * Arrays.sort(arr, (List<Integer> c1, List<Integer> c2) -> {
     *                       return c1.get(0) - c2.get(0);
     *                   });
     */
}
