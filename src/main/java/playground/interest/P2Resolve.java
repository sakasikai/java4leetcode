package playground.interest;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import playground.interest.myMath.GaussHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @Title: P2Resolve
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/6/11 16:46
 */
public class P2Resolve implements P2Constant {

    public static void main(String[] args) {
        List<List<Integer>> medicine = Stream.of(M1, M2, M3, M4, M5, M6, M7).collect(Collectors.toList());

        // 找最大线性无关组
        P2Resolve r = new P2Resolve();
        r.doGauss(medicine);

        List<Integer> newMedicine1 = new ArrayList<>(Arrays.asList(14, 0, 5, 12, 8, 40, 15, 10, 8));
        r.doGauss(medicine, newMedicine1);

        List<Integer> newMedicine2 = new ArrayList<>(Arrays.asList(25, 40, 16, 8, 20, 10, 6, 0, 4));
        r.doGauss(medicine, newMedicine2);

        /**
         　List<List<Integer>> collect = Stream.of(M2, M4, M5).collect(Collectors.toList());

         List<List<Fraction>> collect2 = new ArrayList<>();
         collect.forEach(eq -> {
         collect2.add(eq.stream().map(v -> Fraction.of(v, 1).reduce()).collect(Collectors.toList()));
         });

         collect2.get(0).replaceAll(f->f.mul(Fraction.of(3, 1)));
         List<Integer> integers = collect2.stream().map(vec -> vec.stream().map(Fraction::toInteger).collect(Collectors.toList()))
         .reduce((vec1, vec2) -> {
         for (int j = 0; j < vec1.size(); j++) {
         vec1.set(j, vec1.get(j) + vec2.get(j));
         }
         return vec1;
         }).get();
         System.out.println(integers);
         System.out.println(M6);
         　**/
    }

    /**
     * 　* @Description: AX = y
     *
     **/
    public void doGauss(List<List<Integer>> X) {
        doGauss(X, null);
    }

    public List<List<Integer>> composeXY(List<List<Integer>> X, List<Integer> y) {
        int x_num = X.size(), feat_dim = X.get(0).size();
        Assert.isTrue(feat_dim == M1.size());
        if (CollectionUtils.isEmpty(y)) {
            y = new ArrayList<>();
            y.addAll(Collections.nCopies(feat_dim, 0));
            Collections.fill(y, 0);
        }

        // 转置 组合
        List<List<Integer>> result = new ArrayList<>();
        for (int j = 0; j < feat_dim; j++) { //
            result.add(new ArrayList<>());
            List<Integer> ln = result.get(j);
            for (List<Integer> vec : X) {
                ln.add(vec.get(j));
            }
            ln.add(y.get(j));
        }

//        for (int i = 0; i < feat_dim; i++) {
//            result.get(i).forEach(elm -> System.out.print(elm + " "));
//            System.out.println();
//        }

        return result;
    }

    /**
     * 　* @Description: AX = y
     **/
    public void doGauss(List<List<Integer>> X, List<Integer> y) {
        System.out.println("初等行变换：X | y ====> 倒三角阶梯矩阵");
        String msg = GaussHelper.of(this.composeXY(X, y)).gauss();
        System.out.println(msg + "\n");

    }
}
