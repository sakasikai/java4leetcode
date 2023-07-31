package core.basic.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author maiqi
 * @title lc1471
 * @description 数组中的 k 个最强值
 * @create 2023/7/31 19:42
 */
public class lc1471 {
    public static void main(String[] args) {
        lc1471 r = new lc1471();
        int[] in = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(r.getStrongest(in, 2)));
        in = new int[]{1, 1, 3, 5, 5};
        System.out.println(Arrays.toString(r.getStrongest(in, 2)));
        in = new int[]{6, 7, 11, 7, 6, 8};
        System.out.println(Arrays.toString(r.getStrongest(in, 5)));
        in = new int[]{7, 22, 17, 3};
        System.out.println(Arrays.toString(r.getStrongest(in, 2)));
    }

    /**
     * @description: m 为数组的中位数
     * @update: 2023/7/31 19:44
     */
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int m = arr[(arr.length - 1) / 2];

        List<Integer> res = Arrays.stream(arr).boxed()
                .sorted((p, q) -> -1 * this.doSubtractPQ(p, q, m)) // desc
                .limit(k).collect(Collectors.toList());

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * @description: TODO 判断升降序
     * <p>
     * 对于 Comparator.compare(p, q) --> int
     * (p, q) -> f(p) - f(q) 就是默认 ASC
     * (p, q) -> -[ f(p) - f(q) ] 相反，则是 DESC
     * </p>
     * @update: 2023/7/31 20:04
     */
    public int doSubtractPQ(int p, int q, int m) {
        int pm = Math.abs(p - m), qm = Math.abs(q - m);
        return pm != qm ? pm - qm : p - q;
    }
}
