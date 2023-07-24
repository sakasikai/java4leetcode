package basic.search.binary_search;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author maiqi
 * @title Bsearch
 * @description TODO
 * @create 2023/7/13 21:58
 */
public class BsearchUtil {

    public int indexOfLeftBound$3(int[] a, int l, int r, Predicate<Integer> match) {
        Objects.requireNonNull(match);
        return indexOf$3(a, l, r, match, false);
    }

    public int indexOfRightBound$3(int[] a, int l, int r, Predicate<Integer> match) {
        Objects.requireNonNull(match);
        return indexOf$3(a, l, r, match, true);
    }



    public int indexOf$3(int[] a, int l, int r, Predicate<Integer> match, boolean findRight) {
        if (l > r) return -1;

        if (findRight) {
            while (l < r) {
                int mid = (l + r + 1) / 2;
                if (match.test(a[mid])) { // match range with mid inclusive
                    l = mid;
                } else { // escape range with mid ex-clusive
                    r = mid - 1;
                }
            }
            return l;
        } else {
            while (l < r) {
                int mid = (l + r) / 2;
                if (match.test(a[mid])) { // match range with mid inclusive
                    r = mid;
                } else { // escape range with mid ex-clusive
                    l = mid + 1;
                }
            }
            return r;
        }
    }

    public int valueOfRightBound$3(int l, int r, Predicate<Integer> match) {
        Objects.requireNonNull(match);
        return valueOf$3(l, r, match, true);
    }

    public int valueOfLeftBound$3(int l, int r, Predicate<Integer> match) {
        Objects.requireNonNull(match);
        return valueOf$3(l, r, match, false);
    }

    public int valueOf$3(int l, int r, Predicate<Integer> match, boolean findRight) {
        if (l > r) return -1;

        if (findRight) {
            while (l < r) {
                int mid = (l + r + 1) / 2;
                if (match.test(mid)) { // match range with mid inclusive
                    l = mid;
                } else { // escape range with mid ex-clusive
                    r = mid - 1;
                }
            }
            return l;
        } else {
            while (l < r) {
                int mid = (l + r) / 2;
                if (match.test(mid)) { // match range with mid inclusive
                    r = mid;
                } else { // escape range with mid ex-clusive
                    l = mid + 1;
                }
            }
            return r;
        }
    }
}
