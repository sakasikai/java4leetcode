package basic.sort;

import java.util.Scanner;

public class lc148 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a, b;

        while(sc.hasNext()){
            a = sc.nextInt();
            b = sc.nextInt();


            System.out.println("a+b:" + (a+b));
        }

    }

    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        // 归并排序 + list
        ListNode tst=head, mid=null;
        int n = 0;
        while(tst!=null) {
            tst = tst.next;
            n ++;
        } // n>=2
        tst = head;
        n = n / 2 -1; // n == 2时，tst指向head，否则无限递归
        while(n-- > 0) tst = tst.next;

        mid = tst.next;
        tst.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(mid);

        // merge，三指针！
        ListNode dummy = new ListNode(-1), cur=dummy, lower=null;
        while(l1!=null || l2!=null){
            if(l1!=null && l2!=null){
                if(l1.val < l2.val) lower = l1;
                else lower = l2;
            }else if(l2 != null) lower =l2;
            else lower = l1;

            cur.next = lower; // link node

            if(l1 == lower) l1 = l1.next;
            else l2 = l2.next;
            cur = cur.next; // mv cur to tail
        }

        return dummy.next;
    }

    private void printAll(ListNode h){
        while (h!=null){
            System.out.print(h.val + " ");
            h = h.next;
        }
        System.out.println();
    }
}
