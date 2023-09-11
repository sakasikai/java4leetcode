package Playground.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @Title: conversion2
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/3/2822:28
 */
public class conversion2 {

    public static void main(String[] args) {
        conversion2 cvr = new conversion2();
//        cvr.stringToCharArrToIntArr();
//        cvr.stringToInArr();
        cvr.charsToArrLst();
    }

    /**
    　* @Description: TODO String ==> String[] ==> Integer[] ==> int[]
    　* @param: []
    　* @return: void
    　* @throws:
    　* @author: maiqi
    　* @date: 2023/3/29 20:52
    　**/
    public void stringToCharArrToIntArr(){
        String line = "21 32 33, 12, 3, 1, -10";
        System.out.println("String: " + line);

        String[] sArr = line.split("[, ]+");

        System.out.println("String[]: " + Arrays.toString(sArr));
        assert sArr.length > 0;

        List<Integer> lst = Stream.of(sArr).
                map(Integer::valueOf).
                collect(Collectors.toList());
        System.out.println("List<Integer>: " + lst);

        int[] ints = Stream.of(sArr).
                map(Integer::valueOf).
                mapToInt(Integer::intValue).toArray();

        System.out.println("int[]: " + Arrays.toString(ints));
    }


    /**
    　* @Description: TODO Scanner + String ==> int[]
    　* @param: []
    　* @return: void
    　* @throws:
    　* @author: maiqi
    　* @date: 2023/3/29 21:08
    　**/
    public void stringToInArr(){
        Scanner sc = new Scanner(System.in);
        String ln;
        while ((ln = sc.nextLine())!=null && !ln.isEmpty()){
            String[] sArr = ln.trim().split(" +");
            System.out.println(Arrays.toString(sArr));
        }
    }

    /**
    　* @Description: TODO
    　* @param: []
    　* @return: void
    　* @throws:
    　* @author: maiqi
    　* @date: 2023/3/29 21:52
    　**/
    public void charsToArrLst(){
        String ln = "abc sda bca hhuad sade  cba aaa bab haxxx hhxxx";
        String[] ss = ln.split(" +");
        List<String> lst = Arrays.stream(ss).collect(Collectors.toList());
        System.out.println(lst);

        String[] cs = ln.split(" *");
        String join = String.join("-", cs);
        System.out.println(join);

        List<String> lst2 = Stream.of(cs).
                filter(o->!o.isEmpty()).
                sorted((p, q)->-1 * p.compareTo(q)). // 倒序
                collect(Collectors.toList());
        System.out.println(lst2);


        String join1 = String.join("", lst2);
        char[] chars = join1.toCharArray();
        System.out.println(Arrays.toString(chars));

        // chars ==> stream and sort(#?/、)
        List<String> lst3 = new ArrayList<>(chars.length);
        for(char c: chars) lst3.add(String.valueOf(c));
//        lst3.forEach(i-> System.out.printf(i + "- "));
        List<String> collect = lst3.stream().sorted((p, q) -> +1 * p.compareTo(q)).
                collect(Collectors.toList());
        System.out.println(collect);

        // pq 不对啊
        PriorityQueue<String> pq = new PriorityQueue<>((p, q)->{
            if(p.length() != q.length()){ // 最优先
                return -(p.length() - q.length()); // 最长的排在左，优先
            }

            if(p.charAt(1) != q.charAt(1)){ // 次优先
                // 第二个字母最小的，
                // 字典序上：x<y
                // 优先级：f(x) > f(y)，最优在比较上应该小，升序排的左部
                // sort[ -1*(f(x) - f(y)) ] 会把
                return Character.compare(p.charAt(1), q.charAt(1));
            }

            return p.compareTo(q); // 最后
        });
        System.out.printf("%d, %d\n", (int)'c', (int)'b');
        pq.addAll(lst);
        System.out.println(pq);
        System.out.println(pq.element());


        System.out.println(lst.stream().sorted((p, q)->{
            if(p.length() != q.length()){ // 最优先
                return -(p.length() - q.length()); // 最长的排在左，优先
            }

            if(p.charAt(1) != q.charAt(1)){ // 次优先
                // 第二个字母最小的，
                // 字典序上：x<y
                // 优先级：f(x) > f(y)，最优在比较上应该小，升序排的左部
                // sort[ -1*(f(x) - f(y)) ] 会把
                return Character.compare(p.charAt(1), q.charAt(1)); // ok!
            }

            return p.compareTo(q); // 最后
        }).collect(Collectors.toList()));
    }


}
