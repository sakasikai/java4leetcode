package core.hard.linkdeHashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maiqi
 * @title lc460
 * @description LFU 缓存
 * <p>
 * 当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项(with min freq)
 * 当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键
 * <p>
 * map k -> node, LRU；
 * map freq -> List<node> 使用次数最少的，优先被删除
 * freq eqs ==> List<node>中 LRU node 优先被删除
 * maintain
 * access => freq, mru
 *
 * </p>
 * @create 2023/8/11 11:08
 */
public class lc460 {
    public static void main(String[] args) {
        lc460 lfu = new lc460(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lfu.get(1));      // 返回 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lfu.get(2));      // 返回 -1（未找到）
        System.out.println(lfu.get(3));      // 返回 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfu.get(1));      // 返回 -1（未找到）
        System.out.println(lfu.get(3));      // 返回 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lfu.get(4));      // 返回 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3
    }

    class LRUNode {
        int freq;
        Integer key;
        Integer value;
        LRUNode before, after;

        public LRUNode(Integer k, Integer v) {
            before = after = null;
            freq = 1;
            key = k;
            value = v;
        }
    }

    class LFUBlock {
        final int freq;
        LFUBlock prev, next;
        LRUNode mru, lru; // 双指针

        public LFUBlock(int f) {
            mru = lru = null;  // 双指针
            freq = f;
        }
    }

    final int size;

    Map<Integer, LRUNode> nodeMap = new HashMap<>(); // key => node

    Map<Integer, LFUBlock> blockMap = new HashMap<>(); // freq => block{freq, list of node}
    LFUBlock head, tail; // dummy guard

    {
        head = new LFUBlock(-1);
        tail = new LFUBlock(-1);  // dummy guard
        head.next = tail;
        tail.prev = head;
    }

    public lc460(int capacity) {
        size = capacity;
    }

    public int get(int key) {
        // O(1) get node from map
        LRUNode e = nodeMap.get(key);
        if (e == null) return -1;

        // access node,
        // del node from lru_lst(freq), add node into lru_lst(freq + 1)
        LFUBlock nb = detachFRUBlock(e);
        e.freq += 1;
        attachFRUBlock(e, nb);

        return e.value;
    }

    public void put(int key, int newValue) {
        // replace
        // O(1) get node from map & set newVal
        // access node => ending
        LRUNode e = nodeMap.get(key);
        if (e != null) {
            LFUBlock nb = detachFRUBlock(e);

            e.value = newValue;
            e.freq += 1;

            attachFRUBlock(e, nb);
            return;
        }


        // insert
        // may do if overflow
        //      - del lru node from lru_lst(min of freq)
        if (size == nodeMap.size()) {
            // evict lfu-lru
            LRUNode t = getLfuLruNode();
            detachFRUBlock(t);
            nodeMap.remove(t.key);
        }

        // add newLRUNode into lru_lst(freq = 1) ==> ending
        LRUNode p = new LRUNode(key, newValue);
        attachFRUBlock(p, head.next);
        nodeMap.put(key, p);
    }

    LFUBlock detachFRUBlock(LRUNode e) {
        LFUBlock block = blockMap.get(e.freq);
        if (block == null) return null;

        LFUBlock next = block.next;

        // detach
        LRUNode b = e.before, a = e.after;
        if (b == null) {
            block.mru = a;
        } else {
            b.after = a;
        }

        if (a == null) {
            block.lru = b;
        } else {
            a.before = b;
        }

        // del FRU block
        if (block.mru == null) {
            LFUBlock p = block.prev, n = block.next;
            p.next = n;
            n.prev = p;
            blockMap.remove(e.freq);
        }

        // clear e
        e.before = e.after = null;

        return next;
    }

    void attachFRUBlock(LRUNode e, LFUBlock next) {
        // e to lst(freq = e.freq)
        LFUBlock block = blockMap.computeIfAbsent(e.freq, k -> {
            LFUBlock b = new LFUBlock(e.freq), pb = next.prev;
            pb.next = b;
            next.prev = b;
            b.next = next;
            b.prev = pb;
            return b;
        });

        // add e as mru
        LRUNode bm = block.mru;
        if (bm == null) { // no nodes init; mru == lru == null
            block.mru = block.lru = e;
        } else {
            e.after = bm;
            bm.before = e;
            block.mru = e;
        }
    }

    LRUNode getLfuLruNode() {
        LFUBlock b;
        return (b = head.next) != tail ? b.lru : null;
    }
}
