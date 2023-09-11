package Playground.regexp;

import Playground.utils.TestMain;
import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author maiqi
 * @Title: Basic
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/7/4 15:43
 */
public class Basic {
    public static void main(String[] args) {
        String input = "This is a small input string";

        TestMain<Basic> r = new TestMain<>(Basic.class);
        r.invokeAll(input);

    }

    /**
     * @Description: <p>
     * {@link Pattern#compile(String) Pattern.compile(regex)}
     * </p>
     * <p>
     * {@link Pattern#matcher(CharSequence) Pattern.matcher(input)}
     * </p>
     * <p>
     * {@link Matcher#find() flag = matcher.find()}
     * </p>
     * {@link Matcher#start() pos = matcher.start()},
     * {@link Matcher#end() pos = matcher.end()},
     * </p>
     * <p>
     * {@link Matcher#group() slice = matcher.group()}
     * </p>
     * @author: maiqi
     * @date: 2023/7/5 13:56
     **/
    public void match(String input) {
        Pattern pattern = Pattern.compile("\\w+");
        //如果想忽略大小写敏感度，可以使用线面这个语句
        //Pattern pattern = Pattern.compile("\\s+",Patttern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            System.out.print("start index: " + matcher.start());
            System.out.print(" end index: " + matcher.end() + " ");
            System.out.println(matcher.group()); // TODO eq input.substring(start, end)
            Preconditions.checkArgument(input.substring(matcher.start(), matcher.end()).equals(matcher.group()), "err");
        }

    }


    /**
     * @param input
     * @description: <p>
     * {@link Matcher#replaceAll(String) Matcher.replaceAll(delim)}
     * </p>
     * @update: 2023/8/31 12:13
     */
    public void modify(String input) {
        //创建一个新的pattern和matcher以用选项卡替换空格s
        Pattern replaceP = Pattern.compile("\\s+");
        Matcher matcher2 = replaceP.matcher(input);
        System.out.println(matcher2.replaceAll("_"));
    }


    /**
     * @param input
     * @description: <p>
     * {@link String#split(String) string.split(reg)}
     * </p>
     * @author: maiqi
     * @update: 2023/8/31 12:14
     */
    public void split(String input) {
        System.out.println(Arrays.toString(input.split(" ")));
        System.out.println(Arrays.toString(input.split("[ a]")));
        System.out.println(Arrays.toString(input.split("[ ai]")));
    }

}
