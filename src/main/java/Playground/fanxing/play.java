package Playground.fanxing;

/**
 * @author maiqi
 * @Title: play
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/4/21 14:16
 */
public class play {

    public static void main(String[] args) {

        Class<String> sc = String.class;
        System.out.println(sc.getName());

        Class<Integer> ic = Integer.class;
        System.out.println(ic);

        Class<?> clazz = String.class;
        System.out.println(clazz);

        Class<?> clazz2 = Double.class;
        System.out.println(clazz2);

        System.out.println(clazz.equals(String.class));
        System.out.println(clazz.equals(Integer.class));
    }
}
