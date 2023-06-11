package playground.EarnMoney;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * @author maiqi
 * @Title: LAConstant
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/6/11 12:20
 */
public interface LAConstant {

    Integer MARKER_YEAR_BEGIN = 1990, MARKER_YEAR_END = 1998;

    List<Integer> MARKER_NUMS = IntStream.of(160, 320, 80).boxed().collect(Collectors.toList());

    List<Double> BIRTH_RATE = DoubleStream.of(0, 4, 3).boxed().collect(Collectors.toList());

    List<Double> SERVICE_RATE = DoubleStream.of(0.5, 0.25, 0).boxed().collect(Collectors.toList());


}
