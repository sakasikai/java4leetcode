package Playground.sortedMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author maiqi
 * @title navgable
 * @description TODO
 * @create 2023/9/23 21:03
 */
public class Navgable {

    public static void main(String[] args) {
        Navgable r = new Navgable();
        List<Integer> keys = new ArrayList<>(Arrays.asList(1, 3, 5, 2, 4));
        r.testInt(keys);
        r.testDouble(keys);
    }

    public void testInt(List<Integer> data) {
        NavigableSet<Integer> ns = new TreeSet<>(data);

        System.out.println(ns);
        int val = 3;
        System.out.println(ns.floor(val));
        System.out.println(ns.ceiling(val));
        System.out.println(ns.higher(val));
        System.out.println(ns.lower(val));
    }

    public void testDouble(List<Integer> data) {
        NavigableSet<Double> ns = new TreeSet<>();
        ns.addAll(data.stream()
                .map(Double::valueOf)
                .map(d -> d - d / 10.0).collect(Collectors.toList()));


        System.out.println(ns);
        double val = 3.0;
        System.out.println(ns.floor(val));
        System.out.println(ns.ceiling(val));
        System.out.println(ns.higher(val));
        System.out.println(ns.lower(val));
    }


}
