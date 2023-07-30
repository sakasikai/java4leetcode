package core.basic.search.binary_search;

// TODO 理解二分的好题目！
public class lc33 {
    public int search(int[] a, int target) {
        // k..n-1; 0..k-1 两段升序
        // 寻找 j 这个分界点，然后再在0..j; j+1..n-1里找
        int n=a.length;
        // TODO 三目表达式
        if (n==1) return target == a[0] ? 0 : -1; // 三目表达式！

        int l=0, r=n-1;
        while(l<r){
            int mid = (l+r+1) / 2;
            if(a[mid] >= a[0]) l=mid; // 往右找
            else r=mid-1; // 往左找
        }// find j
        int j=r;

        int k1 = bsearch(a, 0, j, target);
        int k2 = bsearch(a, j+1, n-1, target);

        if(k1!=-1) return k1;
        else if(k2!=-1) return k2;
        else return -1;
    }

    int bsearch(int[] a, int l, int r, int t){
        // asc
        while(l < r){
            int mid = (l+r) / 2;
            if(a[mid] >= t) r=mid;
            else l=mid+1;
        }

        if(a[r] == t) return r;
        else return -1;
    }
}
