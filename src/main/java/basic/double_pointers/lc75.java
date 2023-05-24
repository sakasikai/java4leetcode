package basic.double_pointers;

public class lc75 {
    public void sortColors(int[] a) {
        // 双指针
        int n = a.length;
        for(int i=0, k=0, j=n-1; k<=j;){ // ikj 为待放置012的指针，
            if(a[k] == 0) swap(a, i++, k++);
            else if(a[k] == 2) swap(a, k, j--);
            else if(a[k] == 1) k++;
        }
    }

    public void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
