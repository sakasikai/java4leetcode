package core.basic.sort.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author maiqi
 * @Title: hj27
 * @ProjectName Java4leetcode
 * @Description:
 * 兄弟单词f, 顺序不同，长度一定，不能相同
 * n words（重复）
 * f(x, k) in words（字典序 第k个）
 * m
 * target
 *
 * 3 abc bca cab abc 1
 * 2
 * bca
 *
 * 6 cab ad abcd cba abc bca abc 1
 * 3
 * bca
 *
 * @date 2023/4/210:28
 */
public class hj27 {
    /*
    　* @Description: hash to collect, sort by priority
    　* @param: [args]
    　* @return: void
    　* @throws:
    　* @author: maiqi
    　* @date: 2023/4/2 10:35
    　**/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] data = sc.nextLine().trim().split(" ");
        // parse
        // n, [n...] x, k
        int n = Integer.parseInt(data[0]), k = Integer.parseInt(data[data.length-1]);
        String x = data[data.length - 2];

        // collect target
        List<String> candis = new ArrayList<>();
        for (int i=1+0; i<1+n; i++){
            if(check(x, data[i])){
                candis.add(data[i]);
            }
        }

        // m
        System.out.println(candis.size());

        // find k
        List<String> collect = candis.stream()
                .sorted()
                .skip(k-1).limit(1).collect(Collectors.toList());
        if(!collect.isEmpty())
            System.out.println(collect.get(0));
    }

    public static Boolean check(String x, String candi){
        if(x.length() == candi.length() && !x.equals(candi)){
            String collect1 = Arrays.stream(x.split("")).sorted().collect(Collectors.joining());
            String collect2 = Arrays.stream(candi.split("")).sorted().collect(Collectors.joining());
            return collect1.equals(collect2);
        }

        return false;
    }
}
