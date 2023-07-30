package basic.search.binary_search;

import utils.BsearchUtil;

public class lc34 extends BsearchUtil {

    public int[] searchRange$3(int[] a, int target) {
        int r = indexOf$3(a, 0, a.length - 1, (mVal) -> mVal <= target, true);
        if (r >= 0 && a[r] != target) {
            return new int[]{-1, -1};
        }
        int l = indexOf$3(a, 0, a.length - 1, (mVal) -> target <= mVal, false);

        return new int[]{l, r};
    }

    public int[] searchRange(int[] a, int target) {
        int n = a.length, l = 0, r = n - 1, resl = -1, resr = -1;
        if (n < 1) return new int[]{resl, resr};

        // mid是>=target的最小值
        while (l < r) {
            int mid = (l + r) / 2;
            if (a[mid] >= target) r = mid;
            else l = mid + 1;
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
