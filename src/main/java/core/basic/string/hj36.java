package core.basic.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author maiqi
 * @Title: 黄金6
 * @ProjectName Java4leetcode
 * @Description:
 *
 * TRAILBLAZERS
 * TRAILBZES
 * A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 * T R A I L B Z E S C D F G H J K M N O P Q U V W X Y
 * secret key and content ==> encrypted text
 * alphabet map
 *
 * nihao
 * ni
 * @date 2023/4/213:41
 */
public class hj36 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ln = sc.nextLine();
        String src = sc.nextLine();

        // a-z 26

        // prefix
        List<String> prefix = new ArrayList<>();
        for (Character v: ln.toCharArray()){
            if(!prefix.contains(String.valueOf(v)))
                prefix.add(String.valueOf(v));
        }

        // a-z map; [0, 26)
        List<String> func = new ArrayList<>(prefix);
        for(Character c='a'; c<='z'; c++){
            if(!prefix.contains(String.valueOf(c)))
                func.add(String.valueOf(c));
        }


        StringBuilder sb = new StringBuilder();
        for (Character c: src.toCharArray()){
            sb.append(func.get(c-'a'));
        }

        System.out.println(sb);
    }

}
