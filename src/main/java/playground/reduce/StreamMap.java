package playground.reduce;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @Title: stream_map
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/5/19 15:45
 */
public class StreamMap {
    @Data
    @AllArgsConstructor
    public class Obj {
        public Integer k;
        public String v;
    }

    public void test(){
        List<Integer> keys = Stream.of(1, 1, 1, 2, 2, 3, 3, 3).collect(Collectors.toList());
        List<String> values = Stream.of(1, 1, 1, 3, 4, 1, 4, 5).map(Object::toString).collect(Collectors.toList());

        int len = keys.size();
        List<Obj> lst = new ArrayList<>();
        for(int i=0; i<len; i++){
            lst.add(new Obj(keys.get(i), values.get(i)));
        }

        System.out.println(lst);

        Map<Integer, String> collect = lst.stream().collect(Collectors.toMap(Obj::getK, Obj::getV, (k, k2) -> k + "," + k2));
        System.out.println(collect);
    }

    public static void main(String[] args) {
        StreamMap streamMap = new StreamMap();
        streamMap.test();
    }
}
