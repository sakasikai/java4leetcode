package basic.search.binary_search;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author maiqi
 * @title lc1870
 * @description TODO
 * @create 2023/7/18 15:35
 */
public class lc1870 extends BsearchUtil {
    public static void main(String[] args) {
        int[] ints = IntStream.of(1, 3, 2).toArray();
        double h = 2.7;
        lc1870 r = new lc1870();

        System.out.println("ans: " + r.minSpeedOnTime(ints, h));
    }

    /**
     * @param dist 距离，按次序乘坐，整点乘坐
     * @param hour 总时间，
     * @return 最小正整数 时速
     * @description: 全部列车的 最小正整数 时速
     * <p>
     *
     * </p>
     * @author: maiqi
     * @update: 2023/7/18 15:35
     */
    public int minSpeedOnTime(int[] dist, double hour) {
        int n = dist.length;
        if ((double) n - 1 + ((double) dist[n - 1] / 1e7) > hour) return -1;
        // speed: [lo .. mid .. hi]
        // time: 耗时越大 <== false [hour] true ==> 耗时越小
        // 递减曲线
        return this.valueOfLeftBound$3(1, (int) 1e7 + 1, mid -> timeCost(dist, mid) <= hour);
    }

    /**
     * @param dist
     * @param v
     * @return double
     * @description: <p>
     * stream skip 起始位置
     * stream limit 最大长度
     * - 顺序敏感
     * - skip.limit [skip, skip + limit)
     * - limit.skip [skip, limit)
     * </p>
     * @author: maiqi
     * @update: 2023/7/18 16:36
     */
    public double timeCost(int[] dist, int v) {
        int n = dist.length;
        double tot = Arrays.stream(dist).boxed()
                .limit(n - 1)
                .map(d -> (double) d / v).map(Math::ceil)
                .reduce(Double::sum).orElse(.0);
        tot += (double) dist[n - 1] / v;
        return tot;
    }

}
