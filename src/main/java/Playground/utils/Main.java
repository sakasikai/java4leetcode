package Playground.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main{


    public static void main(String[] args){
        List<Character> a = Stream.of('|', '=', '&').sorted().collect(Collectors.toList());
        System.out.println(a);

        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());
        int[] arr = s2intArr(sc.nextLine());
        int M = Integer.parseInt(sc.nextLine());
        int[] ls = s2intArr(sc.nextLine());
        int[] rs = s2intArr(sc.nextLine());
        char[] ops = sc.nextLine().toCharArray();
        int[] Xs = s2intArr(sc.nextLine());


//        int[] reduceOps = new int[N];
        List<int[]> ranges = new ArrayList<>();
        for(int i=0; i<M; i++)
            ranges.add(new int[]{(int)ops[i], ls[i], rs[i], Xs[i] , i});

        int and = '&', or = '|', assign='=';
        ranges.sort((p, q)->{
            // & = |
            // 希望能按照 op{=, &, |} 排序！

           if(p[0] == assign || q[0] == assign){
                if(p[0] != assign)
                    return 1; // p > q
                else if(q[0] != assign)
                    return -1; // p < q

                return p[1] - q[1]; // p[0]失效
           }

           return p[1] - q[1]; // by left
       });



//       for(int i=0; i<M; i++){
//           int[] ints = ranges.get(i);
//           if(ints[0] == assign){
//
//           }else if(ints[0] == )
//       }


       ranges.forEach(i-> System.out.println(Arrays.toString(i)));

    }

    // 差分
    // [l, r] => 前缀和 s[r] - s[l-1] = sum([l, r])
    // s[l] += x ==> [l, ...] += x
    // s[r] -= x ==> [r, ...] -= x
    // [l, r] +=x ==> s[l]+=x && s[r] -= x
    // init [0...end] == 0 add(i,i) forEach
    //

    public static int[] s2intArr(String in){
        return Arrays.stream(in.trim().split(" ")).
                mapToInt(Integer::valueOf).toArray();
    }

}

/**
4
5 4 7 4
4
1 2 3 2
4 3 4 2
=|&=
8 3 6 2

 */