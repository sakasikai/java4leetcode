package core.basic.list;

/**
 * @author maiqi
 * @Title: NC1
 * @ProjectName Java4leetcode
 * @Description: 大数加法
 * @date 2023/4/121:27
 */

import java.util.ArrayList;
import java.util.List;


public class NC1 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算两个数之和
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    public String solve (String s, String t) {
        if(s.length() == 0) return t;
        if(t.length() == 0) return s;

        // write code here
        List<Integer> p = new ArrayList<Integer>(), q = new ArrayList<Integer>();

        // 逆序输入，i=0为小数一端
        int slen = s.length(), tlen = t.length();
        for (int i= slen - 1; i >= 0; i--){
            p.add(s.charAt(i) - '0');
        }
        for (int i= tlen - 1; i >= 0; i--){
            q.add(t.charAt(i) - '0');
        }

        // 从小端升序进位
        List<Integer> res = new ArrayList<>();
        int cum = 0;
        for(int i=0, j=0; i<slen || j<tlen || cum>0; ){
            if(i < slen) cum += p.get(i ++);
            if(j < tlen) cum += q.get(j ++);

            res.add(cum % 10);
            cum /= 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = res.size() - 1; i >= 0 ; i--) {
            sb.append(res.get(i));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        NC1 nc1 = new NC1();
        String solve = nc1.solve("0", "99");
        System.out.println(solve);
    }
}