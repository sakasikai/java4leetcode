package playground.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @Title: SimpleMainResolver
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/7/5 14:14
 */
public class SimpleMainResolver {
    private final Class<?> clazz;

    private Object resolver;


    public SimpleMainResolver(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void invokeAll() {
        this.invokeAll(null);
    }

    public void invokeAll(String str) {
        try {
            if (Objects.isNull(resolver)) {
                resolver = clazz.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        Arrays.stream(clazz.getDeclaredMethods()).
                filter(m -> Stream.of("main", "Main", "util", "Util").noneMatch(p -> m.getName().contains(p)))
                .forEach(m -> {
                    try {
                        System.out.println("[+] invoking: " + m.getName());

                        if (m.getParameterCount() > 0) {
                            m.invoke(resolver, str);
                        } else {
                            m.invoke(resolver);
                        }

                        System.out.println("[-]");
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });

    }
}
