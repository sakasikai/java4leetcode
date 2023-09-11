package JobToMe.Tuanz.round1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author maiqi
 * @title strcomp_2
 * @description TODO
 * @create 2023/9/1 18:13
 */
public class strcomp_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(r.readLine());
        char[] s = r.readLine().toCharArray(),
                t = r.readLine().toCharArray();

        // ON => comp
        // swap i, j incr 1 / 2

        int fac = 0;
        Character ch; // p!=q

        Map<Character, Set<Integer>> ch2js = new HashMap<>(), ch2is = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (s[i] == t[i]) {
                fac++;
            } else {
                ch2is.computeIfAbsent(ch = s[i], k -> new HashSet<>())
                        .add(i);
                ch2js.computeIfAbsent(ch = t[i], k -> new HashSet<>())
                        .add(i);
            }
        }

        final int[] incr = {0};

        Set<Character> chars = ch2is.keySet();
        Set<String> st = new HashSet<>();
        for (Character cr : chars) {
            Set<Integer> setIs = ch2is.get(cr), setJs = ch2js.get(cr);

            if (setIs != null && !setIs.isEmpty() && setJs != null && !setJs.isEmpty()) { // ch = s[i] = t[j], find i,j
                incr[0] = 1;

                setIs.forEach(i -> {
                    setJs.forEach(j -> {
                        st.add(String.format("%d.%d", i, j));
                        if (st.contains(String.format("%d.%d", j, i))) {
                            incr[0] = 2;
                        }
                    });
                });
                // find j, i
                if (incr[0] == 2) {
                    break;
                }
            }
        }

        System.out.println(fac + incr[0]);
    }
}
/*
 *
 * case incr 1, ch = s[i] == t[j]; store i => js, j => is
 * case incr 2, ch1 = s[i] == t[j] && ch2 = s[j] == t[i]
 * ch1 == ch2?
 * */