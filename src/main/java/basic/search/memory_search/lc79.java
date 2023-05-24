package basic.search.memory_search;

public class lc79 {
    int m, n;
    int[] xv = {-1, 0, 1, 0}, yv = {0, -1, 0, 1};

    public boolean exist(char[][] a, String wd) {
        m = a.length;
        n = a[0].length;

        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++){
                if(dfs(a, i, j, wd, 0))
                    return true;
            }

        return false; // all chances gone
    }

    // TODO 二进制标志不能标记坐标(x, y)
    // n+m个标记位 不能 表示 m*n 个状态！！
    public boolean dfs(char[][] a, int x, int y, String wd, int u){
        if(a[x][y] != wd.charAt(u)) return false;
        if(u==wd.length()-1) return true;

//      state |= 1<<x | 1<<(7+y); // mark (x,y) in state
        char tmp = a[x][y];
        a[x][y] ='@';
        boolean res = false;
        for(int i=0; i<4; i++){
            int nx = x+xv[i], ny = y + yv[i];
//          boolean oldLand = (state>>nx&1) == 1 && (state>>(8+ny)&1) == 1;
            if(0<=nx && nx<m && 0<=ny && ny<n && a[nx][ny]!='@')
                if(dfs(a, nx, ny, wd, u+1)) {
                    res = true;
                    break;
                }
        }

        a[x][y]=tmp;
        return res;
    }
}
