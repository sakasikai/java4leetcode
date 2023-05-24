package basic.search.binary_search;

// TODO 二分不画图不清晰
public class lc34 {
    public int[] searchRange(int[] a, int target) {
        int n=a.length, l=0, r=n-1, resl=-1, resr=-1;
        if(n < 1) return new int[]{resl, resr};

        // mid是>=target的最小值
        while (l<r){
            int mid = (l+r) / 2;
            if(a[mid] >= target) r=mid;
            else l=mid+1;
        }
        if(a[r] == target) resl = r;

        l=0;
        r=n-1;
        // mid是<=target的最大值
        while(l<r){
            int mid = (l+r+1) / 2;
            if(a[mid] <= target) l=mid;
            else r=mid-1;
        }
        if(a[r] == target) resr = r;

        // TODO array列表初始化
        return new int[]{resl, resr};
    }
}
