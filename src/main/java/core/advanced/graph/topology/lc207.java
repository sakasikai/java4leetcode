package core.advanced.graph.topology;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class lc207 {
    // 邻接表模型
    // 拓扑排序算法
    // 多源BFS
    private static final int N = (int) (1e5) + 10, M = (int) 5e3 + 10;
    int[] h = new int[N], ne = new int[M], e = new int[M]; // h为槽，idx，e[]，ne[]为节点
    int[] ind = new int[N]; // in-degree
    int idx;

    public void add(int from, int to){ // 为 h[from] 链表增加一个节点to，其地址为idx。
        e[idx] = to;
        ne[idx] = h[from];
        h[from] = idx++;
    }

    public boolean canFinish(int n, int[][] preqs) {
        Arrays.fill(h, -1); // -1代表空节点

        for(int[] pr: preqs){
            // 1 -> 0
            add(pr[1], pr[0]);
            ind[pr[0]] ++;
        }

        Queue<Integer> q = new LinkedList<>();

        // multi source
        for(int i=0; i<n; i++){
            if(ind[i] == 0) q.add(i);
        }

        // bfs
        int res = 0; // 可学完的课程数目
        while(!q.isEmpty()){
            int nd = q.remove();
            res ++;
            // 搜索：删除nd，邻接点 in-degree--，能搜索到所有非环
            // 最后判断下 in-degree，Or，记录学完课程

            for (int i = h[nd]; i!=-1; i=ne[i]){
                int j = e[i];
                ind[j] --;
                if(ind[j] == 0) q.add(j);
            }
        }

        return res == n;
    }
}
