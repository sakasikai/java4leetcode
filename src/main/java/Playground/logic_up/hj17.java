package Playground.logic_up;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @Title: hj17
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/4/1217:08
 */
public class hj17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        List<String> ins = Arrays.stream(sc.nextLine().split(";"))
                .filter(s -> s!=null && (s.length() == 2 || s.length() == 3))
                .filter(s -> pass(s))
                .collect(Collectors.toList());

        List<String> dir = Stream.of("A", "D", "W", "S").collect(Collectors.toList());
        int[] dx = {-1, 1, 0, 0}, dy = {0, 0, 1, -1};


        int[] loc = {0, 0};
        for (String in: ins) { // 注意 while 处理多个 case
            String op = String.valueOf(in.charAt(0));
            Integer val = Integer.valueOf(in.substring(1));

            int i = dir.indexOf(op);

            loc[0] += dx[i] * val;
            loc[1] += dy[i] * val;
        }

        System.out.println(String.format("%d,%d", loc[0], loc[1]));
    }

    public static boolean pass(String s){
        if(s == null || s.length() < 2 || s.length() > 3) return false;
        String op = String.valueOf(s.charAt(0));
        String val = s.substring(1);
        Stream<String> ops = Stream.of("A", "D", "W", "S");

        for(int i=0; i<val.length(); i++){
            if(!Character.isDigit(val.charAt(i))
                    || i == 1 && val.charAt(0) == '0')
                return false;
        }

        return ops.anyMatch(p->p.equals(op));
    }
}