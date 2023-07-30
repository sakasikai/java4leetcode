package basic.search.binary_search;

import com.google.common.base.Preconditions;
import utils.BsearchUtil;

/**
 * @author maiqi
 * @title lc633
 * @description <p>
 * 双指针 + 二分
 * </p>
 * @create 2023/7/24 21:37
 */
public class lc633 extends BsearchUtil {
    public static void main(String[] args) {
        int p = Integer.MAX_VALUE;
        Preconditions.checkState(Integer.MAX_VALUE == (1L << 31) - 1); // 0_1{31}
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE)); // 0_1111111111111111111111111111111
        Preconditions.checkState(Integer.MIN_VALUE == 1 << 31); // 1_0{31}
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE)); // 1_0000000000000000000000000000000
        System.out.println("int(4 Bytes; 32bits) ranges from [-2^31, -1], [0, 2^31 - 1]");

        lc633 r = new lc633();
        System.out.println(r.judgeSquareSum(p));
        ;
    }

    /**
     * @param c
     * @return boolean
     * @description: <p>
     * 2147483647
     * 2147483600
     * 2147482647 (Integer.MAX_VALUE)
     * </p>
     * @author: maiqi
     * @update: 2023/7/25 10:36
     */
    public boolean judgeSquareSum(int c) {

        long maxVal = (long) Math.sqrt(c);
        if (this.sumEqC(maxVal, maxVal, c)) return true;

        for (long p = 0, q = maxVal; p <= maxVal && q >= p; p++) { // 对等位置 ==》大数用二分找，小数留给枚举
            final long fixedP = p;
            // q >= c 的最小值 OR <c && q不变
            q = this.valueOfLeftBound$3(fixedP, q, midQ -> this.sumLeC(fixedP, midQ, c));

            if (this.sumEqC(p, q, c)) {
                return true;
            } // p++
        }

        return false;
    }

    public boolean sumLeC(long p, long q, long c) {
        return p * p - c + q * q >= 0;
    }

    public boolean sumEqC(long p, long q, long c) {
        return p * p - c + q * q == 0;
    }
}
