package playground.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrPrint {

    // intStream 不能做排序控制 or map到其他数据，只能先装箱
    public static void print1d(int[] intervals, String msg){
        System.out.println(msg);

        // Stream.of 不能应用到 int[] 上 ❌，
        // 要用 IntStream, longStream, DoubleStream
        List<String> c1 = IntStream.of(intervals).boxed().
                sorted((p, q)-> q-p). // Integer
                map(Object::toString).collect(Collectors.toList());
        System.out.println(String.join(", ", c1));

        /*
        List<String> c3 = IntStream.of(intervals).
                mapToObj(String::valueOf). // int ==> Object <==> boxed??
                        map(String::valueOf).
                sorted((p, q)-> -p.compareTo(q)).
                collect(Collectors.toList());
        System.out.println(c3);


        List<String> c2 = Arrays.stream(intervals).boxed().
                map(Object::toString). // Integer ==> String
                sorted(String::compareTo). // String compare
                collect(Collectors.toList());
        System.out.println(String.join("| ", c2));
        */

    }

    public static void print2d(int[][] intervals, String msg){

        System.out.println(msg);
        List<String> collect = Stream.of(intervals).
                map(Arrays::toString). // int[] ==> String
                collect(Collectors.toList());

        System.out.println(String.join(", ", collect));
    }

    public static void printLine(String c, int len){
        String[] out = new String[len];
        Arrays.fill(out, c);
        String res = String.join(c, out);
        System.out.println(res);
    }

    public static void defaultln(){
        ArrPrint.printLine("-", 25);
        System.out.println();
    }
}
