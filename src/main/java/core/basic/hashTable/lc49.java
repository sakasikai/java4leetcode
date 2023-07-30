package core.basic.hashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class lc49 {
    public List<List<String>> groupAnagrams(String[] a) {
        Map<String, List<String>> mp = new HashMap<>();

        for(String path: a){
            char[] key_arr = path.toCharArray();
            Arrays.sort(key_arr);
            String key = Arrays.toString(key_arr);

            if(!mp.containsKey(key)){
                mp.put(key, new LinkedList<String>());
            }
            mp.get(key).add(path);
        }

        // hash.values ==> list<list>
        return new ArrayList(mp.values()); // 用构造器创建，不用一个个添加
    }
}
