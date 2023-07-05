package playground.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @Title: TestMain
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/7/5 11:13
 */
public class TestMain<T> {
    private T resolver;

    public TestMain(Class<T> clazz) {
        try {
            this.resolver = clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            System.out.println(e);
        }
    }

    public void invokeAll() {
        Objects.requireNonNull(resolver, "err");

        Arrays.stream(resolver.getClass().getDeclaredMethods()).
                filter(m -> Stream.of("main", "Main", "util", "Util").noneMatch(p -> m.getName().contains(p)))
                .forEach(m -> {
                    try {
                        System.out.println("[+] invoking: " + m.getName());
                        m.invoke(resolver);
                        System.out.println();
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public void invokeAll(String str) {
        Objects.requireNonNull(resolver, "err");

        for (Method m : resolver.getClass().getDeclaredMethods()) {
            if (Stream.of("main", "Main", "util", "Util").noneMatch(p -> m.getName().contains(p))) {
                try {
                    System.out.println("[+] invoking:" + m.getName());
                    if (m.getParameterCount() > 0) {
                        m.invoke(resolver, str);
                    } else {
                        m.invoke(resolver);
                    }
                    System.out.println();
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
