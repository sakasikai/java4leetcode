package lygrNote.Assertertion;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @Title: y2023m5
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/5/24 14:37
 */
@Slf4j
public class y2023m5 {
    private static final String var0 = null;
    private static final String var1 = "  ";
    private static final String var2 = "ads";
    private static final String ERR = "err!";


    public static void main(String[] args) {
        testObjects();

//        testCollectionUtils();
//        testStringUtils();
//        testObjectUtils();
    }

    public static void testObjects(){
        BiFunction<Integer, Integer, Integer> bf1 = Integer::sum;
        Function<Integer, Integer> f1 = p->p*p*p, f2 = p->p*100;

        // 函数式接口的组合
        Integer pipeRes = Objects.requireNonNull(bf1.andThen(f1).andThen(f2).apply(2, 4));

        System.out.println(pipeRes);
        Assert.isTrue(Math.round((Math.pow((2+4), 3) * 100)) == pipeRes, ERR);
    }

    public static void testCollectionUtils(){
        List<String> collect = null;
        Assert.isTrue(CollectionUtils.isEmpty(collect), ERR);

        collect = new ArrayList<>();
        Assert.isTrue(CollectionUtils.isEmpty(collect), ERR);

        collect = Stream.of(var0, var1, var1, var1, var2).collect(Collectors.toList());
        Assert.isTrue(! CollectionUtils.isEmpty(collect), ERR);
    }

    public static void testStringUtils(){

        long sz1 = Stream.of(var0, var1, var1, var1, var2).filter(StringUtils::isNotBlank).count();
        long sz2 = Stream.of(var0, var1, var2, var2, var2).filter(StringUtils::isBlank).count();
        long sz3 = Stream.of(var0, var1, var2, var2, var2).filter(StringUtils::isEmpty).count();

        Assert.isTrue(sz1 == 1, ERR);
        Assert.isTrue(sz2 == 2, ERR);
        Assert.isTrue(sz3 == 1, ERR);

        String line = "red pipe is  $sa!$ds      s  $$";
        System.out.println(line);


        // 字符中间会产生冗余的空串
        System.out.println(StringUtils.center("*", 70, "*"));
        String[] split = line.split("[$ ]");
        System.out.println(Arrays.toString(split));

        // 需要手动过滤掉空字串""
        List<String> collect1 = Arrays.stream(line.split("[ $]")).filter(s->! s.isEmpty()).collect(Collectors.toList());
        System.out.println(collect1);

        // 自动过滤掉空串
        System.out.println(StringUtils.center("*", 70, "*"));
        String[] split2 = StringUtils.split(line, "$ ");
        System.out.println(Arrays.toString(split2));
    }

    public static void testObjectUtils(){
        Assert.isTrue(! ObjectUtils.allNotNull(var0, var1, var2), ERR); // 全不空
        Assert.isTrue(! ObjectUtils.allNull(var0, var1, var2), ERR); // 全空
        Assert.isTrue(ObjectUtils.anyNotNull(var0, var1, var2), ERR); // 有不空的
        Assert.isTrue(ObjectUtils.anyNull(var0, var1, var2), ERR); // 有空的

        int sz1 = (int) Stream.of(var0, var0, var1, var2).filter(ObjectUtils::isEmpty).count();
        int sz2 = (int) Stream.of(var0, var1, var1, var2).filter(ObjectUtils::isNotEmpty).count();

        Assert.isTrue(sz1 == 2, ERR);
        Assert.isTrue(sz2 == 3, ERR);
    }



}
