package Playground.stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @Title: Optional
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/4/30 07:20
 */
public class OptionalPlay {

    public static void main(String[] args) throws Exception {

        List<Integer> lst = Stream.of(1, 3, 4).collect(Collectors.toList());

        Optional<List<Integer>> tst = Optional.of(lst);
        tst.ifPresent(l->l.forEach(System.out::println));
        System.out.println("tst.get():" + tst.get());

//        lst = null;

        List<Integer> var0 = Optional.ofNullable(lst).orElseThrow(() -> new Exception("wrong"));

    }

}
