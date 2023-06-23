package playground.javaUtil;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author maiqi
 * @Title: dxStrCollUtils
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/6/13 15:58
 */
public class dxStrCollUtils {
    public static void main(String[] args) {
        dxStrCollUtils r = new dxStrCollUtils();

        r.uStringUtils();
        // r.uCollectionUtils();
    }

    /**
     * 　* @Description: org.apache.commons.lang3.StringUtils
     * 　* @date: 2023/6/13 16:00
     *
     **/
    public void uStringUtils() {
        String[] strings = Collections.nCopies(5, "let").toArray(new String[0]);

        String joinStr = StringUtils.join(strings, " * ");
        System.out.println(joinStr);

        joinStr = StringUtils.center(joinStr, 40, '+');
        System.out.println(joinStr);

        if (StringUtils.isNotEmpty(joinStr) && StringUtils.isNotBlank(joinStr)) {
            String[] smallSplit = StringUtils.split(joinStr, " ");
            System.out.println(Arrays.toString(smallSplit));

            String[] bigSplit = StringUtils.split(joinStr, "*");
            System.out.println(Arrays.toString(bigSplit));

            String[] bigSplit2 = StringUtils.split(joinStr, "* ");
            System.out.println(Arrays.toString(bigSplit2));

            String[] bigSplit3 = StringUtils.split(joinStr, "*+ ");
            System.out.println(Arrays.toString(bigSplit3));
        }
    }

    public void uCollectionUtils() {
        List<String> lst = new ArrayList<>();

        if (CollectionUtils.isEmpty(lst)) {
            CollectionUtils.addAll(lst, Collections.nCopies(5, "let"));
        }

        System.out.println(lst);
    }

    public void uObjectUtils() {
        Optional<String> opt = Optional.ofNullable("sad");
        Objects.requireNonNull(opt);

        String s = opt.filter(v -> v.startsWith("s")).map(v -> v + " sda").orElse("non");

        System.out.println(!Objects.isNull(s));
    }
}
