package core.basic.hashTable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author maiqi
 * @Title: Hj26
 * @ProjectName Java4leetcode
 * @Description: TODO Character.func family
 * @date 2023/4/121:40
 */
public class Hj26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别

        Map<Character, List<Boolean>> order = new HashMap<>();
        String ln;
        StringBuilder sb = new StringBuilder();
        ln = sc.nextLine();

        for (Character v: ln.toCharArray()){
            if(Character.isAlphabetic(v)){
                char k = Character.toLowerCase(v); // 只用小写字母做key
                if(!order.containsKey(k))
                    order.put(k, new LinkedList<>());
                order.get(k).add(Character.isUpperCase(v));
            }
        }

        Character p = 'a';
        int j = 0;
        for (Character v: ln.toCharArray()) {
            if(Character.isAlphabetic(v)){
                while(!order.containsKey(p) || order.get(p).size() == j) {
                    if(p == 'z') break;
                    p ++;
                    j = 0;
                } // 没有p，j都取完了，移到下一行

                sb.append(order.get(p).get(j)? Character.toUpperCase(p): p);
                j++;
            }else{
                sb.append(v);
            }
        }

        System.out.println(sb);
        sb.setLength(0);
    }


}
