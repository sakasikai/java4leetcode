package core.basic.queue.heap;

import java.util.PriorityQueue;

// TODO PriorityQueue
public class lc215 {
    public int findKthLargest(int[] a, int k) {
        // 筛选前k大的元素，查询最小的，即是KthLargest
        // 最小堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int v : a) {
            pq.add(v);
            if(pq.size() > k) pq.remove();
        }

        return pq.element();
    }
}
