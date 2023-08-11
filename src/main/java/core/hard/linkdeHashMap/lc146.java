package core.hard.linkdeHashMap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author maiqi
 * @title lc146
 * @description LRU 缓存
 * @create 2023/8/11 10:34
 */
public class lc146 {
    public static void main(String[] args) {
        lc146 lRUCache = new lc146(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
    }

    int lruCap;
    LinkedHashMap<Integer, Integer> cache;

    public lc146(int capacity) {
        lruCap = capacity;
        cache = new LinkedHashMap<Integer, Integer>(Math.min(16, lruCap), .75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return this.size() > lruCap;
            }
        };
    }

    public int get(int key) {
        Integer v;
        return (v = cache.get(key)) != null ? v : -1;
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}
