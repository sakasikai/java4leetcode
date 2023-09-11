package core.basic.search.traceback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author maiqi
 * @title lc92
 * @description 子集 II
 * @create 2023/9/10 17:17
 */
public class lc92 {

    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    int state;


    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);
        dfs(nums, 0);
        return res;
    }

    /**
     * @param arr
     * @param start
     * @description: <p>
     * search edge = N*(N-1)*...*2*1
     * 每个level都要返回
     * </p>
     * @author: maiqi
     * @update: 2023/9/10 17:26
     */
    public void dfs(int[] arr, int start) {
        // path一直在动态变化，且最终会清空，
        // 不能直接其ref加入到res，应该加数据快照
        res.add(new LinkedList<>(path));

        for (int i = start; i < arr.length; i++) {
            if ((state >> i & 1) == 1) continue;

            path.addLast(arr[i]);
            state |= 1 << i;
            dfs(arr, i + 1);
            state &= ~(1 << i);
            path.removeLast();

            int j = i + 1;
            while (j < arr.length && arr[j] == arr[i]) j++;
            i = j - 1; // i+1-1 = i
        }
    }
}
