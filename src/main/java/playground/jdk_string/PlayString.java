package playground.jdk_string;

import playground.utils.TestMain;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @Title: playString
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/7/5 11:08
 */
public class PlayString {

    public static void main(String[] args) {
        TestMain<PlayString> r = new TestMain<>(PlayString.class);

        String example = "on my own way";
        r.invokeAll(example);
    }

    public void concat() {
        String bs = "base", ex = "_extend";
        String ct = bs.concat(ex);

        Stream.of(bs, ex, ct).forEach(System.out::println);
    }

    public void joinSplit(String in) {
        String[] split = in.split("[ w]");
        System.out.println("split: " + Arrays.toString(split));
        String join = String.join("-", split);
        System.out.println("join: " + join);
    }

    public void replace(String in) {
        String s = in.replaceAll("[ w]", "-");
        System.out.println("replaced: " + s);
        String s1 = in.replace(" ", "+");
        System.out.println("replaced: " + s1);
        String s2 = in.replaceFirst(" ", "_");
        System.out.println("replaced: " + s2);
    }


}
