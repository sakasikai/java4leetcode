package basic.list.revrese_list;

import basic.list.ListNode;

public class lc234 {
    public boolean isPalindrome(ListNode head) {
        // 找到中点，断开，前半段翻转
        // 偶数，奇数，寻找切分点
        // 得到两个候选链表

        int n = 0;
        ListNode p = head, q = null;
        while(p!=null){
            n++;
            p = p.next;
        }

        // find cut, [..., p] cut... [q, ...]
        p = head; // rebase
        if(n == 1) return true;
        else if(n % 2 == 0) {
            n = n/2;
            while (--n > 0) p = p.next;
            q = p.next;
        }
        else {
            n = (n-1)/2;
            while (--n > 0) p = p.next;
            q = p.next.next;
        }
        p.next = null; // 断开

        p = head; // rebase
        q = reverse(q); // reverse

        // test
        boolean equal = true;
        while (p!=null && q!=null){
            if(p.val == q.val){
                p = p.next;
                q = q.next;
            }else{
                equal = false;
                break;
            }
        }

        return equal && p==null && q == null;
    }

    public ListNode reverse(ListNode head){
        if(head == null || head.next == null) return head;
        // at least two nodes

        ListNode k=null, p=head, q=p.next;
        // k=null, [p=head, ...] ==> [..., k=head], p=null
        while(p!=null){
            q = p.next; // 存储下一节点
            p.next = k;
            k = p;
            p = q;
        }

        return k;
    }
}
