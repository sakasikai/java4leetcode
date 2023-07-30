package core.basic.sort.quick_select;

// TODO 快排，快选
public class lc215 {
    public int findKthLargest(int[] a, int k) {
        return qs(a, 0, a.length-1, k);
    }

    public int qs(int[] a, int l, int r, int k){ // find KthLargest in range [l, r] of a
        if(l==r) return a[l];

        int x = a[(l+r) / 2], p=l-1, q=r+1;
        while(p<q){
            do p++; while (a[p] < x);
            do q--; while (a[q] > x);
            // 不能有等号，因为期望p、q第一次交错就停止，
            // 最后能划分问题为 [l, q] [q+1, r] 两个子区间，且满足对x的大小关系

            if(p < q) {
                int t = a[p];
                a[p] = a[q];
                a[q] = t;
            }
        }
        // [l, q] <= x [q+1, r] >= x
        // 分治
        int rightLen = r-q;
        if(rightLen >= k) return qs(a, q+1, r, k);
        else return qs(a, l, q, k-rightLen);
    }
}
