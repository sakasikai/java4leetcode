package Playground.interest;

import Playground.interest.myMath.Fraction;

import java.util.Arrays;
import java.util.List;

public interface P2Constant {
    List<Integer> M1 = Arrays.asList(10, 12, 5, 7, 0, 25, 9, 6, 8);
    List<Integer> M2 = Arrays.asList(2, 0, 3, 9, 1, 5, 4, 5, 2);
    List<Integer> M3 = Arrays.asList(14, 12, 11, 25, 2, 35, 17, 16, 12);
    List<Integer> M4 = Arrays.asList(12, 25, 0, 5, 25, 5, 25, 10, 0);
    List<Integer> M5 = Arrays.asList(20, 35, 5, 15, 5, 35, 2, 10, 0);
    List<Integer> M6 = Arrays.asList(38, 60, 14, 47, 33, 55, 39, 35, 6);
    List<Integer> M7 = Arrays.asList(100, 55, 0, 35, 6, 50, 25, 10, 20);

    Fraction ONE = Fraction.of(1, 1);

    Fraction ZERO = Fraction.of(0, 1);
}
