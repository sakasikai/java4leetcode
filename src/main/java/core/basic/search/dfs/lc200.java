package core.basic.search.dfs;

public class lc200 {
    int res = 0, m, n;
    int[] xv = {0, -1, 0, 1}, yv = {1, 0, -1, 0};

    public int lc200(char[][] a) {
        m=a.length;
        n=a[0].length;

        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++){
                if(a[i][j] == '1'){
                    res ++;
                    dfs(a, i, j);
                }
            }

        return res;
    }

    // 染色
    public void dfs(char[][] a, int x, int y){
        // mark a[x][y] and all its nearing "1"
        a[x][y] = '@';
        for(int k=0; k<4; k++){
            int i = x+xv[k], j = y+yv[k];
            if(0<=i && i<m && 0<=j && j<n
                    && a[i][j] == '1') dfs(a, i, j);
        }
    }
}
