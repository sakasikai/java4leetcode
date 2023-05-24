package basic.list.revrese_list;

import basic.list.ListNode;

public class lc206_iter {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode k = null, p = head, q = null;
        // start  nil <= k .. p => q
        // to     ..  <= k <= p .. q
        // end    ..  <= k .. null
        while(p!=null){
            q = p.next;
            p.next = k;

            k = p;
            p = q;
        } // end: [..., k] p=null, q=null

        return k;
    }
}
