package lygrNote.Assertertion;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.List;
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
        testStringUtils();
        testObjectUtils();
    }

    public static void testStringUtils(){


        long sz1 = Stream.of(var0, var1, var1, var1, var2).filter(StringUtils::isNotBlank).count();
        long sz2 = Stream.of(var0, var1, var2, var2, var2).filter(StringUtils::isBlank).count();
        long sz3 = Stream.of(var0, var1, var2, var2, var2).filter(StringUtils::isEmpty).count();

        Assert.isTrue(sz1 == 1, ERR);
        Assert.isTrue(sz2 == 2, ERR);
        Assert.isTrue(sz3 == 1, ERR);

        log.info(StringUtils.center("*", 70, "*"));

        String line = "red pipe is  $sa!$ds  $$";
        String[] split = line.split("$");
    }

    public static void testObjectUtils(){
        Assert.isTrue(! ObjectUtils.allNotNull(var0, var1, var2, ERR)); // 全不空
        Assert.isTrue(! ObjectUtils.allNull(var0, var1, var2, ERR)); // 全空
        Assert.isTrue(ObjectUtils.anyNotNull(var0, var1, var2, ERR)); // 有不空的
        Assert.isTrue(ObjectUtils.anyNull(var0, var1, var2, ERR)); // 有空的

        int sz1 = (int) Stream.of(var0, var0, var1, var2).filter(ObjectUtils::isEmpty).count();
        int sz2 = (int) Stream.of(var0, var1, var1, var2).filter(ObjectUtils::isNotEmpty).count();

        Assert.isTrue(sz1 == 2, ERR);
        Assert.isTrue(sz2 == 3, ERR);
    }



}
