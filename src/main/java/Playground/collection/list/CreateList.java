package Playground.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @Title: CreateList
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/6/13 15:47
 */
public class CreateList {
    public static void main(String[] args) {
        CreateList r = new CreateList();

        r.f1();
    }

    /**
     * 　* @Description: <p>
     * 3 elegant ways to create non-static List
     * <p>- stream </p>
     * <p>- Collections.nCopies </p>
     * <p>- Arrays.asList </p>
     * </p>
     * 　* @date: 2023/6/13 15:47
     *
     **/
    public void f1() {
        // add

        // stream
        List<String> collect2 = Stream.of("a", "b", "c", "d").collect(Collectors.toList());
        collect2.add(" non-static List");
        System.out.println(collect2);

        // Collections.nCopies
        List<String> l1 = new ArrayList<>(Collections.nCopies(4, "rep"));
        l1.add(" non-static List");
        System.out.println(l1);

        // Arrays.asList
        ArrayList<String> l2 = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        l2.add(" non-static List");
        System.out.println(l2);
    }
}
