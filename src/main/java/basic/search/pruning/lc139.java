package basic.search.pruning;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 超时！
public class lc139 {
    private int[] skipped;
    public boolean wordBreak(String s, List<String> wd) {
        skipped = new int[s.length()];
        return search(s, 0, wd);
    }

    // O(2^NM) + 剪枝
    public boolean search(String s, int u, List<String> wd){
        if(u == s.length()) return true;
        if(skipped[u]==-1) return false;

        for (String candi : wd) {
            int j = candi.length() + u -1; // [u, j] inclusive
            if(j < s.length()){
                String range = s.substring(u, j+1);
                if(range.equals(candi) && search(s, j+1, wd))
                    return true;
            }
        }
        skipped[u]=-1; // s[u] 搜索不可行，后面在探测到u时，直接剪枝
        return false;
    }

}
