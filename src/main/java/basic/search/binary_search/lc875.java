package basic.search.binary_search;

import utils.BsearchUtil;

import java.util.Arrays;

/**
 * @author maiqi
 * @title lc875
 * @description 爱吃香蕉的珂珂
 * @create 2023/7/24 20:40
 */
public class lc875 extends BsearchUtil {
    /**
     * @param piles
     * @param h
     * @return int
     * @description: <p>
     * 给定k，h，判断是否可以吃完。k越大，越能吃完，所以二分最小的k
     * Given pileVal k, timeCost =
     * </p>
     * @author: maiqi
     * @update: 2023/7/24 20:42
     */
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = (int) 1e9 + 1;
        return this.valueOfLeftBound$3(l, r, midK -> canEatAll(piles, midK, h));
    }

    public boolean canEatAll(int[] piles, int k, int h) {
        return Arrays.stream(piles).boxed()
                .map(pileVal -> Double.valueOf(Math.ceil((double) pileVal / k)).intValue())
                .reduce(Integer::sum).orElse(h + 1) <= h;
    }
}
