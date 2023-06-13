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

public class P2Resolve implements P2Constant {

    public static void main(String[] args) {
        List<List<Integer>> medicine = Stream.of(M1, M2, M3, M4, M5, M6, M7).collect(Collectors.toList());

        // 小问1，找最大线性无关组
        P2Resolve r = new P2Resolve();
        r.doGauss(medicine);

        // 小问2，新药1
        List<Integer> newMedicine1 = new ArrayList<>(Arrays.asList(14, 0, 5, 12, 8, 40, 15, 10, 8));
        r.doGauss(medicine, newMedicine1);

        // 小问2，新药2
        List<Integer> newMedicine2 = new ArrayList<>(Arrays.asList(25, 40, 16, 8, 20, 10, 6, 0, 4));
        r.doGauss(medicine, newMedicine2);
    }

    public List<List<Integer>> composeXY(List<List<Integer>> X, List<Integer> y) {
        int x_num = X.size(), feat_dim = X.get(0).size();
        Assert.isTrue(feat_dim == M1.size());
        if (CollectionUtils.isEmpty(y)) {
            y = new ArrayList<>(Collections.nCopies(feat_dim, 0));
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

        for (int i = 0; i < feat_dim; i++) {
            result.get(i).forEach(elm -> System.out.print(elm + " "));
            System.out.println();
        }
        System.out.println();

        return result;
    }

    public void doGauss(List<List<Integer>> X) {
        doGauss(X, null);
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
