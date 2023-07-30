package core.basic.search.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author maiqi
 * @Title: hj43
 * @ProjectName Java4leetcode
 * @Description:
 * nxm, val{0, 1}, {0, 0} => {n-1, m-1}
 * 最短路径
 *
 * 5 5
 * 0 1 0 0 0
 * 0 1 1 1 0
 * 0 0 0 0 0
 * 0 1 1 1 0
 * 0 0 0 1 0
 *
 *
 * @date 2023/4/2 11:35
 */
public class hj43 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt(), m=sc.nextInt();
        int[][] walls = new int[n][m];
        boolean[][] marked = new boolean[n][m];
        int[] xv = {-1, 0, 1, 0}, yv = {0, -1, 0, 1};

        for(int i=0; i<n; i++)
            for (int j=0; j<m; j++)
                walls[i][j] = sc.nextInt();

        // bfs(queue, state change), marked
        // pathFrom[cur] = pre
        Map<String, int[]> pathFrom = new HashMap(); // TODO int[]不能做key！因为是变量索引，不代表值
        int[] end = {n-1, m-1}, start = {0, 0};

        Queue<int[]> ne = new LinkedList<>();
        ne.add(start);

        while (!ne.isEmpty()){
            int[] cur = ne.remove();
            int x = cur[0], y = cur[1];
            marked[x][y] = true;

            // change state
            for(int i=0; i<4; i++){
                int nx = x+xv[i], ny = y+yv[i];
                if(0<=nx&&nx<n
                    && 0<=ny&&ny<m
                    && walls[nx][ny]!=1
                    && !marked[nx][ny]){
                    // bfs上下左右走，不出界，没墙，没走过
                    int[] state = new int[]{nx, ny};
                    ne.add(state);
                    pathFrom.put(Arrays.toString(state), cur);
                }
            }
        }

        List<int[]> res = new ArrayList<>();
        int[] cur = end;
        while (pathFrom.containsKey(Arrays.toString(cur))){ // pathFrom excludes [start]
            res.add(cur);
            cur = pathFrom.get(Arrays.toString(cur));
        }
        res.add(start);

        Collections.reverse(res);
        res.forEach(i->System.out.printf("(%d,%d)\n", i[0], i[1]));
    }
}
