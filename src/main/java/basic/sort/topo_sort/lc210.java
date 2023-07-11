package basic.sort.topo_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.IntStream;


public class lc210 {

    public static void main(String[] args) {
        lc210 r = new lc210();

        String[] ins = ("[[1,0],[2,0],[3,1],[3,2]]\n" + "[[1,0]]\n" + "[]").split("\\n");

        Arrays.stream(ins).forEach(in -> {
            // TODO string正则-转义 用双斜杠，String split要额外过滤空值
            int[] ints = Arrays.stream(in.split("[\\[,\\]]")).filter(s -> !s.isEmpty())
                    .mapToInt(Integer::parseInt).toArray();
            final int nNodes = Arrays.stream(ints).max().orElse(0) + 1;
            int nEdges = ints.length / 2;
            int[][] var2 = new int[nEdges][2];
            for (int i = 0, ii; i < nEdges; ++i) {
                ii = 2 * i;
                var2[i] = new int[]{ints[ii], ints[ii + 1]};
            }

            int[] ans = r.findOrder$3(nNodes, var2);
            System.out.println(Arrays.toString(ans));
            System.out.println("Done\n");
        });
    }

    // 节点编号 0 ==》N-1
    // to2from ==》拉链法表示图 inDegree[N]
    // 拓扑排序，保存结果 ==> queue + bfs + cycle + inDegree[N]
    Map<Integer, Optional<LinkedList<Integer>>> tab = new HashMap<>();

    List<Integer> inDs = new ArrayList<>();

    /**
     * @param N
     * @param loFromHis
     * @return int[]
     * @description: 三周目
     * @author: maiqi
     * @update: 2023/7/11 17:36
     */
    public int[] findOrder$3(int N, int[][] loFromHis) {
        List<Integer> ret = new ArrayList<>();
        inDs.clear();
        inDs.addAll(Collections.nCopies(N, 0));
        tab.clear();

        // 拉链法存储 tab0，入点指向所有出点
        for (int[] loFromHi : loFromHis) {
            int lo = loFromHi[0], hi = loFromHi[1];

            // 优雅！ TODO 处理初始空值，取到并操作
            tab.computeIfAbsent(hi, none -> Optional.of(new LinkedList<>()))
                    .ifPresent(los -> los.add(lo));
            tab.put(hi, tab.get(hi));
            inDs.set(lo, inDs.get(lo) + 1);
        }

        // TODO stream range 创建Queue
        Queue<Integer> q = IntStream.range(0, N).filter(i -> inDs.get(i) == 0).
                collect(LinkedList::new, LinkedList::add, LinkedList::addAll);

        while (!q.isEmpty()) {
            Integer nd = q.remove();
            // 去除nd
            ret.add(nd);
            if (tab.containsKey(nd)) { // 没出现的点，就是null
                tab.get(nd).ifPresent(los -> {
                    // los 是静态拉链，不会改变
                    los.stream().filter(lo -> inDs.get(lo) > 0).forEach(lo -> {
                        // 入度减少
                        inDs.set(lo, inDs.get(lo) - 1);
                        // 只有第一次减少为0的时候，被计算，此后都会被过滤掉
                        if (inDs.get(lo) == 0) {
                            q.add(lo);
                        }
                    });
                });
            }
        }

        return inDs.stream().filter(v -> v == 0).count() == N
                ? ret.stream().mapToInt(Integer::intValue).toArray()
                : new int[]{};
    }


    Map<Integer, LinkedList<Integer>> tab0 = new HashMap<>();
    int[] inDs0;

    public int[] findOrder(int N, int[][] to2from) {
        inDs0 = new int[N];

        for (int[] ints : to2from) {
            int to = ints[0], from = ints[1];

            // ❌ 不建议，因为每回put都会删旧加新
//             LinkedList<Integer> tos = tab0.getOrDefault(from, new LinkedList<>());
//             tos.add(to); // 链接到一个点
//             tab0.put(from, tos);

            if (!tab0.containsKey(from)) {
                tab0.put(from, new LinkedList<>());
            }
            // 流写法 ok
            tab0.get(from).add(to);

            // ❌ 写法不对，getOrDefault不是流写法
            // tab0.getOrDefault(from, new LinkedList<>()).add(to);

            inDs0[to]++;
        }

        // 收集 inD[idx] == 0
        // ❌ tab0只收集 keySet() 中入度为0的点
        // 不包括那些没有边的点
//        Queue<Integer> q = tab0.keySet().stream().
//                filter(nd->inDs0[nd] == 0).
//                collect(Collectors.toCollection(LinkedList::new));
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (inDs0[i] == 0)
                q.add(i); // 入度为0的所有点
        }

        List<Integer> res = new LinkedList<>();
        int cnt = 0; // cnt of nodes to be deleted
        while (!q.isEmpty()) {
            Integer from = q.remove(); // inDs0[from] == 0
            cnt++;
            res.add(from);

            // inDs0[to]--，to 可能会变成 from
            if (tab0.containsKey(from)) {
                LinkedList<Integer> tos = tab0.get(from);
                for (int to : tos) {
                    inDs0[to]--;
                    if (inDs0[to] == 0) q.add(to); // 变成 from，但tab0中可能没有这个key
                }
            }
            // do nothing

        }

        return cnt==N ?
                res.stream().mapToInt(Integer::intValue).toArray():
                new int[]{}; // ❌ 空数组不是引用类型，不能为null，要new一个
    }


}
