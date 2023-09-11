package core.basic.hashTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author maiqi
 * @title lc957
 * @description N 天后的牢房
 * <p>
 * cells state card: 2^8 << iteration(1e9)
 * cycle => cache
 * </p>
 * @create 2023/8/12 09:00
 */
public class lc957 {

    public static void main(String[] args) {
        int[] in = {0, 0, 1, 1, 1, 1, 0, 0};
        lc957 r = new lc957();
        int[] ints = r.prisonAfterNDays(in, 8);
        System.out.println("ans:" + Arrays.toString(ints));
    }

    /**
     * @param cells
     * @param n
     * @return int[]
     * @description: 第一个数据可能参与cycle，也可能不参与！！
     * @author: maiqi
     * @update: 2023/8/12 09:47
     */
    public int[] prisonAfterNDays(int[] cells, int n) {
        // day->snapshot for a cycle
        Map<Integer, Integer> shotOfDay = new HashMap<>();
        Set<Integer> shotCache = new HashSet<>();
        int s, cycle = -1;
        n--;
        if (cells[0] == 0 && cells[7] == 0) { // 特例，第一个cell参与周期
            cycle = 0;
            n += 1;
        }
        int[] newCells = new int[8];
        while (!shotCache.contains((s = shotOfCells(cells)))) {
            if (n == cycle) return cells;
            System.out.println(Arrays.toString(cells));
            shotCache.add(s);
            shotOfDay.put(cycle, s);
            // transform cells
            // [0..6) [2..8) 相等为1 => [1..7)
            for (int i = 0; i < 6; i++) {
                newCells[i + 1] = cells[i] == cells[i + 2] ? 1 : 0;
            }
            System.arraycopy(newCells, 0, cells, 0, 8);
            cycle++;
        }
        // n % cycle
        return cellsOfShot(shotOfDay.get(n % cycle));
    }

    public Integer shotOfCells(int[] cells) {
        int s = 0;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == 1) s += 1 << i;
        }
        return s;
    }

    public int[] cellsOfShot(int s) {
        int[] cells = new int[8];
        for (int i = 0; i < 8; i++) {
            cells[i] = s >> i & 1;
        }
        return cells;
    }
}
