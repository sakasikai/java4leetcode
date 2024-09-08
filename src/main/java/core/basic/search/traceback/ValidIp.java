package core.basic.search.traceback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author maiqi
 * @title ValidIp
 * @description TODO
 * @create 2023/10/12 14:34
 */
public class ValidIp {

    public static List<String> ans;
    public static LinkedList<String> path;

    static {
        ans = new ArrayList<>();
        path = new LinkedList<>();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nums = sc.nextLine().trim();

        if (nums.length() <= 4 * 3) {
            dfs(nums, 0, 0);
        }

        System.out.println(ans);
    }

    /**
     * @param nums
     * @param u      待处理的字符范围 nums[u..end)
     * @param blocks [0..3]
     * @description: <p>
     * 25525511135
     * 0000
     * 101023
     * </p>
     * @author: maiqi
     * @update: 2023/10/12 14:38
     */
    public static void dfs(String nums, int u, int blocks) {
        if (blocks == 4) {
            if (u == nums.length()) {
                ans.add(String.join(".", path)); // 4 blocks，
            }
            return;
        }

        String blk = "";
        for (int i = u; i < nums.length(); i++) { // block candidate: [u..i]
            if (isValidIpBlock(blk = nums.substring(u, i + 1))) {
                path.addLast(blk);
                dfs(nums, i + 1, blocks + 1);
                path.removeLast();
            } else {
                return;
            }
        }

    }

    public static boolean isValidIpBlock(String block) {
        if (block.length() > 3) return false;
        if (block.length() >= 2 && block.charAt(0) == '0') return false;
        int val = Integer.parseInt(block);
        return 0 <= val && val <= 255;
    }
}
