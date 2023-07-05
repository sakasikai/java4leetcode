package playground.deep_copy;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @author maiqi
 * @Title: ArrayCopy
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/7/5 10:11
 */
public class ArrayCopy {
    public static void main(String[] args) {
        ArrayCopy r = new ArrayCopy();

        Arrays.stream(r.getClass().getDeclaredMethods())
                .filter(m -> !(m.getName().contains("main") || m.getName().contains("Util")))
                .forEach(m -> {
                    try {
                        System.out.println("[+]" + m.getName());
                        m.invoke(r);
                        System.out.println();
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    // 1、简单类型（深拷贝）
    private static void copySrc2Src() {
        int[] src = {1, 2, 3, 4, 5};
        System.out.println("src:" + Arrays.toString(src));
        // 把从索引0开始的2个数字复制到索引为3的位置上
        // [1, 2] ==> [1, 2, 3] + [1, 2]
        System.arraycopy(src, 0, src, 3, 2);

        src[0] *= -1;
        System.out.println(Arrays.toString(src));
        System.out.println("DEEP COPY");
    }


    private static void copySrc2Oth() {
        int[] src = {1, 2, 3, 4, 5};
        //将数据的索引1开始的3个数据复制到目标的索引为0的位置上
        int[] other = new int[5];
        // 2, 3, 4 ==> [2, 3, 4] + [0, 0]
        System.arraycopy(src, 1, other, 0, 3);

        System.out.println(Arrays.toString(other));
        changeUtil(src);
        System.out.println(Arrays.toString(src));
        System.out.println(Arrays.toString(other));
        System.out.println("DEEP COPY");
    }

    //如果是类型转换问题，获取整形
    private static void getIntArrFromObjArr() {
        Object[] obj1 = {1, 2, 3, "4", "5"};
        Integer[] obj2 = new Integer[5];

        try {
            System.arraycopy(obj1, 0, obj2, 0, obj1.length);
        } catch (Exception e) {
            System.out.println("transfer exception:" + e);
        }
        System.out.println(Arrays.toString(obj1));
        System.out.println(Arrays.toString(obj2)); // [1, 2, 3, null, null]
    }

    //获取Object数组中的字符串类型数据
    private static void getStrArrFromObjArr() {
        Object[] obj3 = {1, 2, 3, "4", "5"};
        String[] obj4 = new String[5];
        try {
            System.arraycopy(obj3, 2, obj4, 2, 3);
        } catch (Exception e) {
            //transfer exception:java.lang.ArrayStoreException
            System.out.println("transfer exception:" + e);
        }
        System.out.println(Arrays.toString(obj3));
        System.out.println(Arrays.toString(obj4));//[null, null, null, null, null]
    }

    //获取Object数组中的字符串类型数据
    private static void getStrArrFromObjArr2() {
        Object[] obj3 = {1, 2, 3, "4", "5"};
        String[] obj4 = new String[5];
        try {
            System.arraycopy(obj3, 3, obj4, 3, 2);
        } catch (Exception e) {
            System.out.println("transfer exception:" + e);
        }
        System.out.println(Arrays.toString(obj3));
        //[null, null, null, 4, 5]
        System.out.println(Arrays.toString(obj4));
        obj3[3] = "zhang san";
        System.out.println("查看是浅复制还是深复制~~~~~");
        System.out.println(Arrays.toString(obj3));
        System.out.println(Arrays.toString(obj4));
        System.out.println("DEEP COPY");
    }

    // 2、二维数组（浅拷贝）
    private static void test2DArr() {
        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4};
        int[] arr3 = {5, 6};

        int[][] src = new int[][]{arr1, arr2, arr3};

        printUtil("原始模样：", src);
        int[][] dest = new int[3][];
        System.arraycopy(src, 0, dest, 0, 3);

        System.out.println("改变前");
        printUtil("src = ", src);
        printUtil("dest = ", dest);

        //原数组改变后观察新数组是否改变，改变->浅复制，不改变->深复制
        src[0][0] = -1;

        System.out.println("改变后");
        printUtil("src = ", src);
        printUtil("dest = ", dest);


        System.out.println("SHALLOW COPY");
    }

    //二维数组toString()
    private static void printUtil(String string, int[][] arr) {
        System.out.print(string);
        for (int[] a : arr) {
            for (int i : a) {
                System.out.print(i + " ");
            }
            System.out.print(",");
        }
        System.out.println();
    }


    private static void changeUtil(int[] src) {
        for (int i = 0; i < src.length; i++) {
            src[i] *= -1;
        }
    }
}
