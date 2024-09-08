package core.advanced.dict_tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author maiqi
 * @title lc692
 * @description 692. 前K个高频单词
 * @create 2023/9/13 12:32
 */

public class lc692 {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> word2cnt = new HashMap<>();
        for (String wd : words) {
            word2cnt.compute(wd, (w, cnt) -> cnt == null ? 1 : cnt + 1);
        }

        return Arrays.stream(words).distinct().sorted((p, q) -> {
            int cp, cq;
            if ((cp = word2cnt.get(p)) != (cq = word2cnt.get(q))) {
                return -(cp - cq); // freq desc
            }
            return p.compareTo(q); // alpha asc
        }).limit(k).collect(Collectors.toList());
    }
}
