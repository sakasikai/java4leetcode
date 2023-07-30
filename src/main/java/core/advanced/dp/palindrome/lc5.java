package core.advanced.dp.palindrome;

public class lc5 {
    public String longestPalindrome(String s) {
        // f[i, j] && max(j-i+1) ==> s(i, j)
        // f[i, j]指示规模为ij的子串，是否是个回文

        int maxLen = 0, ri=-1, n=s.length();
        // TODO 二维数组
        boolean[][] f = new boolean[n][n]; // 二维数组写法！默认赋值 false

        for(int len = 1; len<=n; len++)
            for(int i=0; i+len-1<n; i++){
                int j=i+len-1;
                if(s.charAt(i) == s.charAt(j)){
                    if(len <= 2) f[i][j] = true;
                    else f[i][j] = f[i+1][j-1];
                }

                if(f[i][j] && j-i+1 > maxLen){
                    maxLen = j-i+1;
                    ri = i;
                }
            }

        return s.substring(ri, ri+maxLen); // ⚠️ 第二个参数是 exclusive end
    }
}
