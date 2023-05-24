package basic.list.double_pointers;

public class lc19 {
    public class ListNode{
        int val;
        lc19.ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, lc19.ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 双指针，p, q=p+n_steps；当q是尾节点，p就是删除节点的父节点
        ListNode dummy=new ListNode(), p, q;
        dummy.next=head;
        p=dummy;
        q=dummy; // 屏蔽了删除头节点的情况！

        while(n-->0) q=q.next;

        while(q.next!=null){ // 双指针
            p=p.next;
            q=q.next;
        }
        p.next = p.next.next; // 删除倒数第N个点

        return dummy.next;
    }
}
