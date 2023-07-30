package core.basic.search.traceback;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author maiqi
 * @title lc473
 * @description 好难啊，剪枝
 * @create 2023/7/22 15:54
 */
public class lc473 {
    public static void main(String[] args) {
//        int[] in = {4,13,1,1,14,15,1,3,13,1,3,5,2,8,12};
        int[] in = {
            9, 38, 1, 76, 41, 19, 3, 97, 48, 5, 83, 15, 51, 99, 95
        };
        lc473 r = new lc473();
        System.out.println(r.makesquare$3(in));;
    }

    int usedFlag = 0;
    int edgeSum;
    List<Integer> stks;
    public boolean makesquare$3(int[] matchsticks) {
        // sort
        // target len
        stks = Arrays.stream(matchsticks).boxed().sorted().collect(Collectors.toList());
        edgeSum = stks.stream().reduce(Integer::sum).orElse(-1);
        if (edgeSum % 4 != 0) return false;
        edgeSum /= 4;

        // traceback
        return doMatch(0, 0,  0);

    }

    public boolean doMatch(int curLen, int round, int pivot){
        if(round == 3){ // 每根火柴棒必须 使用一次, TODO 只需要过3轮即可，最后一轮不用验证，因为总长正确
            return true; // n 个 1 ==> 2^n-1
        }

        if(curLen == edgeSum){
            return doMatch(0, round + 1, 0); // TODO pivot只在同一round中生效
        }
        // current len < edgeSum && round = [0, 4)

        for (int i=pivot; i<stks.size(); i++){
            if ((usedFlag >> i & 1) == 1) continue;
            // 遍历所有可用stick

            int slen = stks.get(i);
            if(slen <= edgeSum - curLen){
                usedFlag |= 1 << i;
                if(doMatch(curLen + slen, round, pivot + 1)){
                    return true;
                }
                usedFlag &= ~(1 << i);
            }
            // slen fails
            if(stks.get(i) == edgeSum - curLen) return false; // TODO slen 失败了，后续所有round都失败，直接停止

            int j = i + 1;
            while(j < stks.size() && (usedFlag >> i & 1) == 0 && stks.get(j).equals(stks.get(i))){
                j ++; // TODO slen匹配失败一次，后续所有slen都失败
            } // end j = n || s[j] != s[i]
            i = j - 1;
        }

        return false;
    }
}
