package core.basic.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author maiqi
 * @Title: hj75
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/4/219:31
 */
public class hj75 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String p = sc.nextLine();
        String q = sc.nextLine();

        // list int, from 个位
        List<Integer> pl = Arrays.stream(p.split(""))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        List<Integer> ql = Arrays.stream(q.split(""))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        List<Integer> res = new ArrayList<>();
        int cum = 0;
        for(int i=pl.size()-1, j=ql.size()-1; i>=0 || j>=0 || cum>0; ){
            if(i>=0) cum += pl.get(i --);
            if(j>=0) cum += ql.get(j --);

            res.add(cum % 10);
            cum /= 10;
        }

        Collections.reverse(res);
        String collect = res.stream().map(Object::toString).collect(Collectors.joining(""));
        System.out.println(collect);
    }
}
