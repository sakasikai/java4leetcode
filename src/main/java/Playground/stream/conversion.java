package Playground.stream;

import Playground.utils.ArrPrint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class conversion {



    /**
     * TODO List<int[]> ==> int[][]
     * details https://blog.csdn.net/bjyh345/article/details/121711826
     *
     * Java 把类对象引用、接口、数组类型(array)当成引用类型T，可以应用到范型中
     * （即基本类型不能作为泛型方法的参数，List<Integer> ==> int[] ❌
     *
     * Java里没有多维数组，int[][]实际上是一个元素是int[]的一维数组，
     * 所以int[][]被当作「引用类型」数组，
     * 即传入toArray(T[] a: int[0][])里的T是int[]
     */
    public static void ListInt_2_IntInt(){
        List<int[]> arr = new ArrayList<>();
        Collections.addAll(arr,
                new int[]{100, 200, 300},
                new int[]{100, 200, 300}); // shape: 2 x 3

        int[][] res = arr.toArray(new int[0][]);

        ArrPrint.print2d(res, "List<int[]> ==> int[][]");
        ArrPrint.defaultln();
    }

    /**
     * TODO List<Integer> ==> int[]
     * Integer integer = Integer.valueOf((int)3);
     * int i = integer.intValue();
     *
     * String s = integer.toString();
     * int i = Integer.parseInt("3");
     */
    public static void ListInteger_2_intArr(){
        Integer[] data = {4, 5, 3, 6, 2, 5, 1};
        List<Integer> arr = Arrays.asList(data);

        int[] res = arr.stream().
                sorted((p, q)->q.compareTo(p)).
                mapToInt(Integer::intValue). // Integer.valueOf(int i) ==> Integer
                toArray();

        ArrPrint.print1d(res, "List<Integer> ==> int[]");
        ArrPrint.defaultln();
    }

    /**
     * TODO int[] <==> Integer[] 双向
     */
    public static void intArr_IntegerArr(){
        System.out.println("TODO int[] <==> Integer[] 双向");
        intArr_2_IntegerArr();
        IntegerArr_2_intArr();
        ArrPrint.defaultln();
    }

    // int[] ==> Integer[]
    public static void intArr_2_IntegerArr(){
        int[] data = {4, 5, 3, 6, 2, 5, 1};
        // use method reference:
         Integer[] res = Arrays.stream(data).boxed().
                 toArray(Integer[]::new);
        //Integer[] res = Arrays.stream(data).boxed().
        //      toArray((s)->new Integer[data.length]);

        // Integer ==> String
        List<Integer> integers = Arrays.asList(res);
        String str = integers.stream().
                map(Object::toString).
                collect(Collectors.joining("| "));
        System.out.println(str);
    }

    // Integer[] ==> int[]
    public static void IntegerArr_2_intArr(){
        Integer[] data = {4, 5, 3, 6, 2, 5, 1};

        int[] ints = Arrays.stream(data).
                sorted().
                mapToInt(Integer::valueOf). // [CurrentStream] ==> IntStream
                toArray();

        System.out.println(Arrays.toString(ints));

        // string join
        List<String> collect = Stream.of(data).
                map(Object::toString).
                collect(Collectors.toList());
        System.out.println(java.lang.String.join("| ", collect));

    }

    /**
     * TODO 三种 steam creation 方法
     * Stream.of(T[] a), IntStream.of(int[] a), LongStream, DoubleStream
     * Arrays.stream(Any[] a)
     * Collections.steam()
     */
    public static void stream_create(){
        int[] data0 = {4, 5, 3, 6, 2, 5, 1}; // 不能用到 Stream.of ❌，要用IntStream
        Integer[] data1 = {4, 5, 3, 6, 2, 5, 1};
        List<Integer> data2 = Arrays.asList(data1);


        System.out.println("三种 steam creation 方法...");
        for(int ii=0; ii<3; ii++)
            switch (ii){
                case 0:
                    System.out.println("for int[]");
                    List<String> collect1 = IntStream.of(data0).boxed().
                            map(i -> i * i).
                            sorted((p, q) -> -p.compareTo(q)).
                            map(Object::toString).
                            collect(Collectors.toList());
                    System.out.println(java.lang.String.join("; ", collect1));
                    break;
                case 1:
                    System.out.println("for Integer[]");
                    List<Integer> collect = Arrays.stream(data1).
                            map(i -> Math.pow(i, 3)).sorted().
                            map(Double::intValue). // Double ==> Integer
                            collect(Collectors.toList());
                    System.out.println(collect);

                    int[] ints = Stream.of(data1).
                            map(i -> Math.pow(i, 2)).
                            sorted().
                            map(Double::intValue).
                            mapToInt(Integer::intValue).toArray();
                    System.out.println(Arrays.toString(ints));

                    break;
                case 2:
                    System.out.println("for List<Integer>[]");
                    String collect2 = data2.stream().map(i -> Math.pow(i, 3)).
                            sorted((p, q) -> -p.compareTo(q)).
                            map(Double::intValue).
                            map(Object::toString).
                            collect(Collectors.joining(" - "));
                    System.out.println(collect2);
                    break;
                default:
            }

        ArrPrint.defaultln();
    }

    /**
     * TODO stream 中间操作
     */
    public static void stream_inter_op(){
        System.out.println("stream 中间操作...");
        Integer[] data = {4, 5, 3, 6, 2, 5, 5, 1, 23, 21, 100};
        int[] ints = Arrays.stream(data).
                sorted((p, q) -> -p.compareTo(q)).
                map(i -> i + 2).
                mapToInt(Integer::intValue).
                distinct().
                filter(i -> i < 40).
                toArray();
        System.out.println(Arrays.toString(ints));

        String collect = IntStream.of(ints).boxed().
                map(Object::toString).
                collect(Collectors.joining(";"));
        System.out.println(collect);

        IntStream.of(ints).boxed().
                skip(2).limit(4). // ==> [2-] ==> [2, 6]
                forEach(i -> System.out.print(i + ","));
        System.out.println("  ==> [2-] ==> [2, 6)");

        List<String> collect1 = IntStream.of(ints).boxed().
                limit(4).skip(2). // ==> [0-4] ==> [2, 4]
                        map(Object::toString).
                collect(Collectors.toList());
        System.out.print(java.lang.String.join(",", collect1));
        System.out.println("  ==> [0, 4) ==> [2, 4)");

        ArrPrint.defaultln();
    }

    /**
     * TODO stream 终结操作
     */
    public static void stream_end(){

        System.out.println("stream 终结...");
        List<Integer> arr = new ArrayList<>();

        // Collections operation
        Collections.addAll(arr, 4, 5, -3, 6, 2, 5, 5, 1, 100, -1);
        arr.sort(Integer::compareTo);

        Collections.swap(arr, 0, arr.size()-1);
        System.out.println(arr);

        Collections.reverse(arr);
        System.out.println(arr);

        List<Integer> collect = Stream.of(-10, -3, -2).collect(Collectors.toList());
        arr.addAll(collect);
        System.out.println(arr);



        // foreach(P)
        // toArray() / toArray(T[] a)
        // collect + Collectors.toList/toMap/toSet //
        //           Collectors.joining(CharSequence cs)
        //           Collectors.groupingBy(val)
        //           ...

        // max()/min()/reduce(P).get()
        Integer v1 = arr.stream().min(Integer::compareTo).get();
        Integer v2 = arr.stream().max(Integer::compareTo).get();
        Integer v3 = arr.stream().reduce(Integer::sum).get();
        Integer v4 = arr.stream().reduce((p, q) -> p * q).get();
        System.out.println(java.lang.String.format(
                "min:%d, max:%d, sum:%d, product:%d", v1, v2, v3, v4));

        // filter.findFirst() ==> Optional<Integer> ==> isPresent()/get()
        Optional<Integer> first = arr.stream().filter(i -> i % 7 == 0).findFirst();
        Optional<Integer> any = arr.stream().filter(i -> i % 7 == 0).findAny();
        System.out.println(first.orElse(-1));

        arr.add(42);
        arr.add(56);

        // filter(P).anyMatch() / count()
        boolean b = arr.stream().anyMatch(i -> i % 7 == 0);
        boolean b1 = arr.stream().noneMatch(i -> i % 7 == 0);
        boolean b2 = arr.stream().allMatch(i -> i % 7 == 0);
        if (b) {
            System.out.print("has 7*n, and ");
            long count = arr.stream().filter(i -> i % 7 == 0).count();
            System.out.println("count " + count);
        } else
            System.out.println("nope");
        System.out.println();

        // groupingBy
        System.out.println(arr + "\n" + "根据boolean分组:");
        Map<Boolean, List<Integer>> hsh1 = arr.stream().
                collect(Collectors.groupingBy(i -> i % 2 == 0));
        for(Boolean gId: hsh1.keySet())
            System.out.println(gId + " ==> " + hsh1.get(gId));
        System.out.println();

        System.out.println(arr + "\n" + "根据对象filed或方法值ret分组:");
        arr.add(1002);
        arr.add(999);
        Map<Integer, List<String>> hsh2 = arr.stream().sorted().map(Object::toString).
                collect(Collectors.groupingBy(String::length));

        hsh2.forEach((k, v) -> System.out.println(k + " ==> " + v));

        // toMap
        System.out.println(arr + "\n" + "Collectors.toMap");
        Map<String, Integer> hsh3 = arr.stream().
                map(Object::toString).
                distinct().
                collect(Collectors.toMap(i -> i, String::length));
        hsh3.forEach((k, v)-> System.out.print(k + ":" + v + " "));
        System.out.println();

        //toSet
        Set<String> st = arr.stream().map(Object::toString).
                collect(Collectors.toSet());
        st.forEach(i -> System.out.print(i + ","));
        System.out.println();
        ArrPrint.defaultln();

        arr.sort((p, q) -> q - p);
        arr.forEach(i -> System.out.print(i + ", "));
    }

    public static void stream_play(){
        int[] data = {4, 5, 3, 6, 2, 5, 1};

        // [I@28d93b30
        System.out.println("int[]不是引用类型，所以打印地址：" + data); // int[] 不是引用类型
        System.out.println("但有Arrays静态toString方法：" + Arrays.toString(data)); // int[] 不是引用类型

        // 4,5,3,6,2,5,1,
        System.out.print("str_list(结尾有逗):");
        Arrays.stream(data).boxed().
                map(i->i + ","). // Integer ==> String
                forEach(System.out::print);
        System.out.println();

        // list:[16, 15, 15, 14, 13, 12, 11]
        List<Integer> lst = Arrays.stream(data).
                map(i -> i + 10).boxed(). // int ==> Integer(reference)
                        sorted((p, q) -> q - p). // descending sort
                        collect(Collectors.toList());
        System.out.println("list:" + lst);

        // join:「16」=>「15」=>「15」=>「14」=>「13」=>「12」=>「11」
        String str = lst.stream().
                map(i ->'「' + i.toString() + '」'). // Integer ==> String
                        collect(Collectors.joining(",")); // join
        System.out.println("join:" + str);
    }


    public static void main(String[] args) {
        boolean skipHistory = false;

        if (skipHistory) {
            stream_end();
            ListInt_2_IntInt();
            ListInteger_2_intArr();

            intArr_IntegerArr();

            stream_create();
            stream_inter_op();
            stream_end();

            // chaos
            stream_play();
        } else { // run
            stream_inter_op();
        }

    }
}