package advanced.dp.linear;

/**
 * @author maiqi
 * @title lc2596
 * @description TODO
 * @create 2023/7/14 10:24
 */
public class lc2596 {
    public boolean checkValidGrid$3(int[][] grid) {
        int n = grid.length;
        int[] xv = new int[]{1, 1, -1, -1, 2, 2, -2, -2};
        int[] yv = new int[]{2, -2, 2, -2, 1, -1, 1, -1};

        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        int nx = x, ny = y, lastVal = 0;
        System.out.printf("==>(%d, %d) %d%n", x, y, grid[x][y]);
        while (true) {
            boolean canGo = false;
            for (int j = 0; j < 8; j++) {
                nx = x + xv[j];
                ny = y + yv[j];
                if (0 <= nx && nx < n && 0 <= ny && ny < n
                        && grid[nx][ny] == grid[x][y] + 1) {
                    // can go
                    canGo = true;
                    // ch state
                    x = nx;
                    y = ny;
                    lastVal = grid[x][y];
                    System.out.printf("==>(%d, %d) %d%n", x, y, grid[x][y]);
                    break;
                }
            }
            if (!canGo) break;
            // else continue
        }

        return lastVal == n * n - 1;
    }
    /*
    [[24,11,22,17,4],
    [21,16,5,12,9],
    [6,23,10,3,18],
    [15,20,1,8,13],
    [0,7,14,19,2]]
     */
}
