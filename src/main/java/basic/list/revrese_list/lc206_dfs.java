package basic.list.revrese_list;

import basic.list.ListNode;

// 反转链表
public class lc206_dfs {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        // 至少两个点，保证ne不空

        ListNode ne = head.next; // 可作为尾节点前面的链接节点
        head.next = null; // 断开 head, [ne, ..., tail]；作为新的尾节点

        ListNode res = reverseList(ne); // 反转 [ne, ..., tail]
        ne.next = head; // [tail, ..., ne] + head

        return res;
    }
}
