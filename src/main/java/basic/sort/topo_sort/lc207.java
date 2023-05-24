package basic.sort.topo_sort;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class lc207 {
    static Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
    static int[] inDegree;

    // TODO 复杂topo
    public static boolean canFinish(int N, int[][] revTo) {
        System.out.println(revTo.length + "," +revTo[0].length + ":" + revTo[0][1]);
        Arrays.stream(revTo).sorted((p, q)->p[1]-q[1]).
                forEach(arr-> System.out.println(Arrays.toString(arr)));
        System.out.println();

        inDegree = new int[N];

        for(int[] to2from: revTo){
            int to = to2from[0], from = to2from[1];
            // ne[k] = -1, new lst，add to
            // else, add to
            // TODO hash.getOrDefault
            LinkedList<Integer> outs = graph.getOrDefault(from, new LinkedList<>());
            outs.add(to);

            graph.put(from, outs);
            inDegree[to] ++;
        }

        graph.keySet().stream().filter(k->graph.containsKey(k)).forEach(k -> {
            graph.get(k).forEach(to-> System.out.printf("%d -> %d, ", k, to));
            System.out.println();
        });

        // 判断是否可能完成所有课程的学习
        // bfs
        // add generated starts
//        LinkedList<Integer> collect = IntStream.of(inDegree).boxed().
//                filter(i -> inDegree[i] == 0). // [0, 1, 1, 2, 0] ❌，应该处理索引啊！
//                collect(Collectors.toCollection(LinkedList::new));

        Queue<Integer> q= new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if(inDegree[i] == 0)
                q.add(i);
        }

        int res = 0;
        while (!q.isEmpty()){
            int sz = q.size();
            while (sz-- > 0){
                Integer from = q.remove(); // q中的from点肯定是inD[from] == 0的
                res++;

                if(graph.containsKey(from) || graph.get(from)!=null) { // ❌
                    LinkedList<Integer> outs = graph.get(from);
                    for(int i: outs){
                        inDegree[i] --;
                        if(inDegree[i] == 0)
                            q.add(i);
                    }
                }
            }

        }

        return res == N;
    }

    public static void main(String[] args) {

        int N = 20;

        String in = "[[0,10],[3,18],[5,5],[6,11],[11,14],[13,1],[15,1],[17,4]]";
        // System.out.println(Arrays.toString(in.split("[\\[,\\]]")));
        // [, , 0, 10, , , 3, 18, , , 5, 5, , , 6, 11, , , 11, 14, , , 13, 1, , , 15, 1, , , 17, 4]
        // ],[会产生两个空String，即 18 ,s0,s0, 5

        int[] ints = Arrays.stream(in.split("[\\[,\\]]")).
                filter(i -> i.length()>0 && Character.isDigit(i.charAt(0))). // 不能为空啊！❌
                map(Integer::valueOf).
                mapToInt(Integer::intValue).toArray();

        List<int[]> res = new ArrayList<>();

        for (int i = 0; i < ints.length; i+=2) {
            res.add(new int[]{ ints[i], ints[i+1] });
        }

        res.forEach(arr-> System.out.println(Arrays.toString(arr)));
        System.out.println();

        System.out.println(canFinish(N, res.toArray(new int[res.size()][])));
    }
}
