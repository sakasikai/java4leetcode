package basic.sort.topo_sort;

import java.util.*;
import java.util.stream.Collectors;

// TODO 复杂topo
public class lc210 {
    // 节点编号 0 ==》N-1
    // to2from ==》拉链法表示图 inDegree[N]
    // 拓扑排序，保存结果 ==> queue + bfs + cycle + inDegree[N]
    Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
    int[] inDs;

    public int[] findOrder(int N, int[][] to2from) {
        inDs = new int[N];

        for (int[] ints : to2from) {
            int to = ints[0], from = ints[1];

            // ❌ 不建议，因为每回put都会删旧加新
//             LinkedList<Integer> tos = graph.getOrDefault(from, new LinkedList<>());
//             tos.add(to); // 链接到一个点
//             graph.put(from, tos);

            if(!graph.containsKey(from)){
                graph.put(from, new LinkedList<>());
            }
            // 流写法 ok
            graph.get(from).add(to);

            // ❌ 写法不对，getOrDefault不是流写法
            // graph.getOrDefault(from, new LinkedList<>()).add(to);

            inDs[to] ++;
        }

        // 收集 inD[idx] == 0
        // ❌ graph只收集 keySet() 中入度为0的点
        // 不包括那些没有边的点
//        Queue<Integer> q = graph.keySet().stream().
//                filter(nd->inDs[nd] == 0).
//                collect(Collectors.toCollection(LinkedList::new));
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if(inDs[i] == 0)
                q.add(i); // 入度为0的所有点
        }

        List<Integer> res = new LinkedList<>();
        int cnt = 0; // cnt of nodes to be deleted
        while (!q.isEmpty()){
            Integer from = q.remove(); // inDs[from] == 0
            cnt++;
            res.add(from);

            // inDs[to]--，to 可能会变成 from
            if(graph.containsKey(from)){
                LinkedList<Integer> tos = graph.get(from);
                for (int to: tos) {
                    inDs[to] --;
                    if(inDs[to] == 0) q.add(to); // 变成 from，但graph中可能没有这个key
                }
            }
                // do nothing

        }

        return cnt==N ?
                res.stream().mapToInt(Integer::intValue).toArray():
                new int[]{}; // ❌ 空数组不是引用类型，不能为null，要new一个
    }
}
