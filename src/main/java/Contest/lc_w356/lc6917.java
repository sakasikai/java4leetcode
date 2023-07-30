package Contest.lc_w356;

import java.util.Arrays;

/**
 * @author maiqi
 * @title s
 * @description 满足目标工作时长的员工数目
 * @create 2023/7/30 10:30
 */
public class lc6917 {
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        return (int) Arrays.stream(hours).filter(h -> h >= target).count();
    }
}
