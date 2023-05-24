package basic.sort.topo_sort;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// TODO 复杂topo
public class lc310 {
    // 最小高度树
    // 返回它们的根节点标签列表
    // 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1) return Stream.of(0).collect(Collectors.toList());

        // graph, idx:0..n-1
        Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
        int[] inDs = new int[n];
        for (int[] e : edges) {
            int p=e[0], q=e[1];
            // p<->q
            if(!graph.containsKey(p))
                graph.put(p, new LinkedList<>());
            if(!graph.containsKey(q))
                graph.put(q, new LinkedList<>());

            graph.get(p).add(q);
            graph.get(q).add(p);

            inDs[p] ++;
            inDs[q] ++;
        }

        // queue + inDs
        Queue<Integer> q = new LinkedList<>();

        // multi-src bfs
        // i -> inDs[i] == 1
        for (int i = 0; i < n; i++) {
            if(inDs[i] == 1)
                q.add(i);
        }

        List<Integer> res = new LinkedList<>();
        int sz=-1, passed=0;
        while ((sz = q.size())>0){
            if(n - passed <= 2) { // cnt(nds_left) >=3 必然形成一层最小高度树，else必然是结果
                res.addAll(q);
                break;
            }

            while (sz-- > 0){ // pass all outer-level nds(assert inD == 1)
                Integer removed = q.remove();
                inDs[removed] --; // inD == 0, mark passed
                passed ++;

                // 删除removed指出的边
                // affect inner q (inDs[to]-- for tos)
                for (Integer ito: graph.get(removed)) { // inner-level nd
                    if(inDs[ito] == 0) continue;  // passed

                    inDs[ito]--;
                    if(inDs[ito] == 1) q.add(ito);
                }
            }
        }

        return res;
    }
}
