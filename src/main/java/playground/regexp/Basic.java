package playground.regexp;

import com.google.common.base.Preconditions;
import playground.utils.TestMain;

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
        String example = "This is a small example string";

        TestMain<Basic> r = new TestMain<>(Basic.class);
        r.invokeAll(example);

    }

    /**
     * 　* @Description: TODO Pattern, Matcher
     * 　* @author: maiqi
     * 　* @date: 2023/7/5 13:56
     *
     **/
    public void match(String example) {
        Pattern pattern = Pattern.compile("\\w+");
        //如果想忽略大小写敏感度，可以使用线面这个语句
        //Pattern pattern = Pattern.compile("\\s+",Patttern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(example);
        while (matcher.find()) {
            System.out.print("start index: " + matcher.start());
            System.out.print(" end index: " + matcher.end() + " ");
            System.out.println(matcher.group()); // TODO eq example.substring(start, end)
            Preconditions.checkArgument(example.substring(matcher.start(), matcher.end()).equals(matcher.group()), "err");
        }

    }


    public void modify(String example) {
        //创建一个新的pattern和matcher以用选项卡替换空格s
        Pattern replaceP = Pattern.compile("\\s+");
        Matcher matcher2 = replaceP.matcher(example);
        System.out.println(matcher2.replaceAll("_"));
    }


    public void split(String example) {
        System.out.println(Arrays.toString(example.split(" ")));
        System.out.println(Arrays.toString(example.split("[ a]")));
        System.out.println(Arrays.toString(example.split("[ ae]")));
    }

}
