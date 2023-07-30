package core.advanced.math;

public class lc142 {
    // ref: https://www.acwing.com/solution/content/241/
    // 快慢指针 first meet：x + n*(y+z) + y == 2*(x + m*(y+z) + y)
    // (n-m)*(y+z) == x + y
    // x = (n-m-1)*y + (n-m-1)*z + z = (n-m-1)*(y+z) + z
    // y+z == cycle
    // so：x % cycle == z % cycle
    // 即，在 first meet 点绕环走x步会到入口
    // 同时，head到入口距离就是x
    // 如果双指针，from head, from 第一次相遇点走，相遇点就是x
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head, res = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;

            if(fast == slow) { // first meet
                while(fast != res){ // res from head, fast from y
                    res = res.next;
                    fast = fast.next;
                }
                return res; // res locates x
            }
        }

        return null;
    }
}
