package playground.interest.myMath;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import playground.interest.P2Constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GaussHelper implements P2Constant {
    // count of uncertainties
    int n;

    // dimension of uncertainty
    int dim;

    int maxStep;

    // [X | y] (dim = n+1)
    // equations
    List<List<Fraction>> eqs;

    public static GaussHelper of(List<List<Integer>> xy) {
        List<List<Fraction>> eqs2 = new ArrayList<>();
        xy.forEach(eq -> eqs2.add(eq.stream().map(v -> Fraction.of(v, 1).reduce()).collect(Collectors.toList())));

        return builder()
                .n(xy.get(0).size() - 1) // 未知变量个数
                .dim(xy.size())
                .maxStep(0)
                .eqs(eqs2) // 列式
                .build();
    }

    //高斯消元
    public String gauss() {
        for (int col = 0, footstep = 0; col < n; col++) {
            // ①找到此列绝对值最大的一行，忽略之前的台阶
            int test4MaxAbs = footstep;
            for (int i = footstep; i < n; i++)
                if (eqs.get(i).get(col).abs().ge(eqs.get(test4MaxAbs).get(col).abs()))
                    test4MaxAbs = i;
            // 为0 跳过此次操作
            if (eqs.get(test4MaxAbs).get(col).eq(ZERO)) continue;

            // ②将[此列绝对值最大的一行]交换到[r行](确定的消0行数)
            this.exchangeRows(test4MaxAbs, footstep);

            // ③行的第一个数变为1, i=n带上y
            this.multiplyRow(footstep, ONE.div(eqs.get(footstep).get(col)));

            // ④将r之后的每一行(j)的第c列化为0
            for (int i = footstep + 1; i < dim; i++) {
                this.addOthToRow(footstep, i, eqs.get(i).get(col).mul(Fraction.of(-1, 1)));
            }

            // 确定行数(表示消0后最后剩余的行数)+1
            footstep++;
            maxStep = footstep;
        }

        // 操作轮数小于n 不是唯一解(返回1无穷解 返回2无解)
        if (maxStep < dim) {
            for (int i = maxStep; i < dim; i++)
                if (eqs.get(i).get(n).ne(ZERO)) {
                    return analyse(2); // 无解
                }
            return analyse(1); // 无穷多解
        }

        // 有解，方阵
        return analyse(0); // 有解
    }

    public void exchangeRows(int i, int j) {
        if (i != j) Collections.swap(eqs, i, j);
    }

    public void multiplyRow(int i, Fraction factor) {
        eqs.get(i).replaceAll(val -> val.mul(factor));
    }

    // row[i] += row[oth] * factor
    public void addOthToRow(int oth, int i, Fraction factor) {
        for (int j = 0; j < eqs.get(0).size(); j++) {
            Fraction expected = eqs.get(oth).get(j).mul(factor).ADD(eqs.get(i).get(j));
            eqs.get(i).set(j, expected);
        }
    }

    public String analyse(int flag) {
        for (int i = 0; i < dim; i++) {
            eqs.get(i).forEach(elm -> System.out.print(elm.toInteger() + " "));
            System.out.println();
        }

        String hint = null;
        switch (flag) {
            case 0:
                hint = "有解（完美下三角）";
                break;
            case 1:
                hint = "无穷多解（0X = 0）";
                break;
            case 2:
                hint = "无解（0X = Non-Zero-value）";
                break;
        }
        return hint;
    }
}