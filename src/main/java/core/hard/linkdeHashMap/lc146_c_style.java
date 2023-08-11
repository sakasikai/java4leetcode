package core.hard.linkdeHashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maiqi
 * @title lc146_c_style
 * @create 2023/8/11 19:03
 */
public class lc146_c_style {

    class LRUNode {
        int key, value;
        LRUNode before, after;

        LRUNode(int k, int v) {
            key = k;
            value = v;
            before = after = null;
        }
    }

    Map<Integer, LRUNode> nodeMap = new HashMap<>();

    LRUNode head, tail;

    int cacheSize;

    public lc146_c_style(int capacity) {
        cacheSize = capacity;
        head = tail = null;
    }

    public int get(int key) {
        LRUNode e;
        int ans = (e = nodeMap.get(key)) == null ? -1 : e.value;

        // access e
        // detach e
        // make e as mru
        detachList(e); // e == null
        attachLast(e);

        return ans;
    }

    public void put(int key, int newVal) {
        LRUNode e;
        // replace
        if (nodeMap.containsKey(key)) { // exists
            e = nodeMap.get(key);
            e.value = newVal;
            detachList(e);
            attachLast(e);
            return;
        }

        // insert
        if (nodeMap.size() == cacheSize) {
            // maybe evict lru
            // detach lru
            e = head;
            nodeMap.remove(e.key);
            detachList(e);
        }

        // new lru_node e
        e = new LRUNode(key, newVal);
        // make as mru
        nodeMap.put(key, e);
        attachLast(e);
    }

    void detachList(LRUNode e) {
        if (e == null) return;

        LRUNode b = e.before, a = e.after;
        if (b == null) {
            head = a;
        } else {
            b.after = a;
        }

        if (a == null) {
            tail = b;
        } else {
            a.before = b;
        }
    }

    void attachLast(LRUNode e) {
        if (e == null) return;

        if (tail == null) {
            head = tail = e;
        } else {
            LRUNode b = tail;
            tail = e;
            e.before = b;
            b.after = e;
        }
    }
}
