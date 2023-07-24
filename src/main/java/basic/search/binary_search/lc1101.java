package basic.search.binary_search;

/**
 * @author maiqi
 * @title lc1101
 * @description 在 D 天内送达包裹的能力
 * @create 2023/7/24 20:59
 */
public class lc1101 extends BsearchUtil {

    /**
     * @param weights
     * @param days
     * @return int
     * @description: 最低运载能力
     * @author: maiqi
     * @update: 2023/7/24 20:59
     */
    public int shipWithinDays(int[] weights, int days) {
        int l = 1, r = (int) 5e4 * 500 + 10;
        return this.valueOfLeftBound$3(l, r, midCap -> canCarry(weights, midCap, days));
    }

    public boolean canCarry(int[] weights, int loadCapacity, int days) {
        int actualLoad = 0;
        days--; // 至少一天时间
        for (int w : weights) {
            if (w > loadCapacity) return false;
            // w <= loadCapacity

            if (actualLoad + w <= loadCapacity) {
                actualLoad += w; // w算到一天内
            } else {
                days -= 1; // 另算一天
                actualLoad = w;
            }

            if (days < 0) return false;
        }

        return days >= 0;
    }
}
