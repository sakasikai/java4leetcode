package basic.double_ptr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lc15 {
    public List<List<Integer>> threeSum(int[] a) {
        // 三元组去重 -> 有序遍历 nlogn
        // 枚举i，固定，两数和 -> 双指针 -> n^2
        // 双指针 -> 枚举j，a[j]升，a[k]降，a[j]+a[k]>sum
        List<List<Integer>> res = new ArrayList<>(); // 二维ArrayList
        Arrays.sort(a);
        int n = a.length;
        for(int i=0; i<n; i++){
            if(i-1>=0 && a[i]==a[i-1]) continue; // 第二次进入时，i和上一个解取相同值，肯定重复了！
            for (int j=i+1, k=n-1; j<k; j++){
                if(j-1>=i+1 && a[j]==a[j-1]) continue;
                while(j<k && a[j]+a[k]>-a[i]) k--;
                if(j<k && a[j]+a[k]==-a[i]) res.add(Arrays.asList(a[i], a[j], a[k]));
            }
        }

        return res;
    }
}
