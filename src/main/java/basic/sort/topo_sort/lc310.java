package basic.sort.topo_sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// TODO 复杂topo
public class lc310 {
    // 最小高度树
    // 返回它们的根节点标签列表
    // 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量

    public static void main(String[] args) {
        lc310 r = new lc310();
        System.out.println(r.findMinHeightTrees$3(3, new int[][]{{0, 1}, {0, 2}}));
    }

    /**
     * @param N
     * @param edges
     * @return List<Integer> Roots
     * @description: 节点编号；无向边；BFS（Queue，inDegree）；inDegree(leaf) >= 1;
     * @author: maiqi
     * @update: 2023/7/11 21:14
     */
    public List<Integer> findMinHeightTrees$3(int N, int[][] edges) {
        List<Integer> ret = new ArrayList<>();
        Map<Integer, Optional<List<Integer>>> tabs = new HashMap<>();
        List<Integer> inDs = new ArrayList<>(Collections.nCopies(N, 0));

        if (N <= 2) {
            return IntStream.range(0, N).boxed().collect(Collectors.toList());
        }

        for (int[] e : edges) {
            int p = e[0], q = e[1];
            tabs.computeIfAbsent(p, non -> Optional.of(new LinkedList<>())).ifPresent(outs -> {
                outs.add(q);
                inDs.set(q, inDs.get(q) + 1);
            });
            tabs.computeIfAbsent(q, non -> Optional.of(new LinkedList<>())).ifPresent(outs -> {
                outs.add(p);
                inDs.set(p, inDs.get(p) + 1);
            });
        }

        // q是搜索范围
        Queue<Integer> q = IntStream.range(0, N).filter(i -> inDs.get(i) == 1)
                .boxed().collect(Collectors.toCollection(LinkedList::new));

        int ndLeft = N;
        while (!q.isEmpty()) {
            int sz = q.size(); // 一层一层搜索

            if (ndLeft <= 2) { // 所有节点数目是{3,}的话，肯定形成树，还能reduce
                ret.addAll(q);
                break;
            }

            ndLeft -= sz;
            while (--sz >= 0) {
                Integer t = q.remove();
                if (tabs.containsKey(t)) {
                    tabs.get(t).ifPresent(innerNds -> innerNds.stream()
                            .filter(nd -> inDs.get(nd) > 0).forEach(nd -> {
                                inDs.set(nd, inDs.get(nd) - 1);
                                if (inDs.get(nd) == 1) {
                                    q.add(nd);
                                }
                            }));
                }
            }
        }

        return ret;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Stream.of(0).collect(Collectors.toList());

        // graph, idx:0..n-1
        Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
        int[] inDs = new int[n];
        for (int[] e : edges) {
            int p = e[0], q = e[1];
            // p<->q
            if (!graph.containsKey(p))
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
