package lc_contest.w358;

/**
 * @author maiqi
 * @title lc6914
 * @description 翻倍以链表形式表示的数字
 * @create 2023/8/13 11:11
 */
public class lc6914 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new lc6914.ListNode(9), p = head;
        p.next = new lc6914.ListNode(9);
        p = p.next;
        p.next = new lc6914.ListNode(9);


        Solution r = new lc6914.Solution();
        r.debug(r.doubleIt(head));
    }

    static class Solution {
        public void debug(ListNode head) {
            ListNode t = head;
            while (t != null) {
                System.out.print(t.val + "=>");
                t = t.next;
            }
            System.out.println("end");
        }

        public ListNode doubleIt(ListNode head) {
            ListNode tail = reverse(head), p, tl = head;

            int nextAddedVal = 0, tmp;
            p = tail;
            while (p != null) { // p is null
                tmp = p.val * 2 + nextAddedVal;
                p.val = tmp % 10; // 当前位
                nextAddedVal = tmp / 10; // 进位
                tl = p;
                p = p.next;
            } // tail processed

            if (nextAddedVal > 0) { // 消化掉进位
                tl.next = new ListNode(nextAddedVal, null);
            }

            return reverse(tail);
        }

        public ListNode reverse(ListNode head) {
            if (head == null) return null;

            // (head)p->q->...->null
            // p q<-...<-head
            ListNode q;
            if ((q = head.next) == null) return head;

            head.next = null;
            ListNode newHead = reverse(q);
            q.next = head;
            return newHead;
        }

    }
}
