package core.basic.list;

public class lc2 {
    public class ListNode {
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

    public ListNode addTwoNumbers$3(ListNode l1, ListNode l2) {
        int nextCarryAdded = 0;
        ListNode tl = null, hd = null;
        while (l1 != null || l2 != null) { // hd nonNull tl nonNull
            if (l1 != null) {
                nextCarryAdded += l1.val;
                l1 = l1.next;
            }
            ;
            if (l2 != null) {
                nextCarryAdded += l2.val;
                l2 = l2.next;
            }

            if (tl == null) { // head
                hd = tl = new ListNode(nextCarryAdded % 10, null);
            } else { // prev
                tl.next = new ListNode(nextCarryAdded % 10, null);
                tl = tl.next;
            }
            nextCarryAdded /= 10;
        }

        if (nextCarryAdded > 0) {
            tl.next = new ListNode(nextCarryAdded, null);
        }
        return hd;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1); // 哨兵
        int curVal = 0;
        ListNode pcur = res; // 遍历指针
        while (l1 != null || l2 != null || curVal != 0) { // 不可以简写
            if (l1 != null) {
                curVal += l1.val; // 引用属性是点！而不是指针
                l1 = l1.next;
            }
            if(l2!=null) {
                curVal += l2.val;
                l2 = l2.next;
            }
            pcur.next = new ListNode(curVal % 10);
            pcur=pcur.next;
            if(curVal!=0) curVal /= 10;
        }

        return res.next;
    }
}
