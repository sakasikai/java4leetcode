package core.advanced.dp.linear;

import java.util.List;

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s
 */
public class lc139 {
    // O(NM)
    public boolean wordBreak(String s, List<String> wd) {
        int n = s.length();
        boolean[] f = new boolean[n+1];
        f[0] = true;

        for (int i = 1; i <=n; i++) { // 规模，f[0]->"" f[1]->s[0] f[n]->s[0:n]
            for (int j = 0; j < wd.size(); j++) {
                String candi = wd.get(j);
                // str[j, i] 闭区间长度公式 len = i-j+1
                int ii = i+1-candi.length(); // str[ii,i] -> candi，又规模i从1开始算，所以有偏移
                if( ii -1 >= 0 && candi.equals(s.substring(ii-1, i+1-1))){
                    f[i] |= f[ii-1];
                    if(f[i]) break;
                }
            }
        }

        return f[n];
    }
}
