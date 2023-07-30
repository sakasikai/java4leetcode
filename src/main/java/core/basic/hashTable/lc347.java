package core.basic.hashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO hsh.getOrDefault(key, d_val)
public class lc347 {
    public static int[] topKFrequent(int[] arr, int k) {
        Map<Integer, Integer> v2cnt = new HashMap<>();
        for (int v : arr) {
            v2cnt.put(v, v2cnt.getOrDefault(v, 0) + 1);
        } // val ==> cnt/freq

        int n = arr.length;

        // sum(freq) <= n
        // 0 <= cnt <= n, val?
        int[] cnt2vcnt = new int[n + 1];
        Arrays.fill(cnt2vcnt, 0);

        // TODO hsh.forEach((k, v)->P)
        v2cnt.forEach((val, cnt) -> {
            cnt2vcnt[cnt] ++; // 同一个 freq 对应的不同key的个数
        });

        int t=0, threshold = n;
        for(; threshold>=1 && t<k; threshold--) { // 从 n 遍历到 1
            if (cnt2vcnt[threshold] != 0) {
                t += cnt2vcnt[threshold] ;
                // 找到vcnt个key，共枚举[0..k-1]次，
                // 最后对应的threshold就是第k高的频次
            }
            if(t == k) break;
        }

        List<Integer> res = new ArrayList<>();
        // TODO lambda 引用外部变量时，要在最近的地方定义一次，int finalThreshold = threshold;
        int finalThreshold = threshold;
        v2cnt.forEach((val, cnt) -> {
            if(cnt >= finalThreshold)
                res.add(val);
        });

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,1,-1,2,-1,2,3};
        System.out.println(Arrays.toString(topKFrequent(arr, 2)));
    }
}
