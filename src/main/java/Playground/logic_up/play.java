package Playground.logic_up;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @Title: play
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/4/411:37
 */
public class play {
    public static void main(String[] args) {
        play p = new play();
        p.comp();
    }

    public void comp(){
        List<Integer> lst = Stream.of(7, 3, 4, 5, 0, 1).collect(Collectors.toList());

        System.out.println("src");
        lst.forEach(System.out::print);

        System.out.println("\nreverse");
        lst.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::print);

        System.out.println("\nnatural");
        lst.stream()
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::print);

    }

    public void string_eq(){
        String ori = "sad";

        String s1 = ori.substring(0);
        String s2 = String.copyValueOf(ori.toCharArray(), 0, ori.length());

        System.out.println(s1 == ori);
        System.out.println(s1.equals(ori));
        System.out.println(s1.hashCode() == ori.hashCode());

        System.out.println(s2 == ori);
        System.out.println(s2.equals(ori));
        System.out.println(s2.hashCode() == ori.hashCode());
    }
}
