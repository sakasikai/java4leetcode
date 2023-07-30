package core.hard.design;

import java.util.HashMap;
import java.util.Map;

public class lc146_lru {
    private class dlNode{
        int key, val;
        dlNode pre, next;
        public dlNode(){
            pre = null;
            next = null;
        }

        public dlNode(int val){
            this();
            this.val = val;
        }

        public dlNode(int key, int val){
            this();
            this.key = key;
            this.val = val;
        }
    }

    Map<Integer, dlNode> hsh = new HashMap<>();
    dlNode head = new dlNode(-1), tail = new dlNode(-1);  // double-link list
    int capacity;  // 上限

    public lc146_lru(int capacity) {
        // hash: k -> Node of double-link list
        // double-link list -> LRU order
        // sz = hsh.size()
        // del lru 时，不要忘了在hsh中删除lru
        head.next = tail;
        tail.pre = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        int res;
        if(hsh.containsKey(key)){
            dlNode nd = hsh.get(key);

            res = nd.val;
            del(nd); // mv nd to head of dll -> del nd, add nd to head
            add(nd);
        }else res = -1;

        return res;
    }

    public void put(int key, int value) {
        dlNode nd = null;
        if(hsh.containsKey(key)){ // 1. only update node
            nd = hsh.get(key);

            nd.val = value; // cover with value
            del(nd); // mv nd to head of dll
            add(nd);
        }else{ // 2. new nd
            if(hsh.size() >= capacity){ // overflow
                // del lruItem
                dlNode lru = tail.pre;
                hsh.remove(lru.key);
                del(lru);
            }

            // add nd to head of dll, update hsh
            nd = new dlNode(key, value);
            hsh.put(key, nd);
            add(nd);
        }
    }

    // insert nd to head.next
    private void add(dlNode nd){
        // head nd oldHead ..
        dlNode oldHead = head.next;

        nd.next = oldHead;
        nd.pre = head;
        oldHead.pre = nd;
        head.next = nd;
    }

    // take nd apart from list
    private void del(dlNode nd){
        // pre nd next
        nd.pre.next = nd.next;
        nd.next.pre = nd.pre;

        nd.next = null;
        nd.pre = null;
    }
}
