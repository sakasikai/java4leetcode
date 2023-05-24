package basic.search.traceback;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class lc22 {
    // TODO StringBuilder
    StringBuilder sb = new StringBuilder();
    List<String> res = new ArrayList<>();
    int n;

    public List<String> generateParenthesis(int nn) {
        n=nn;
        search(0, 0);
        return res;
    }

    // 剪枝
    // 各n个，共2n个
    // num(right) <= num(left) <= n，才可以放右扩
    // 0<= bias(left-right) <= n-num(right)
    public void search(int bias, int numRight){
        if(bias == 0 && numRight==n){
            res.add(sb.toString());
            return;
        }

        // 两个搜索路径
        if(bias+1 <= n-numRight){
            sb.append('(');
            search(bias+1, numRight);
            sb.deleteCharAt(sb.length()-1);
        }

        if(numRight+1 <= n && bias-1>=0){
            sb.append(')');
            search(bias-1, numRight+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
