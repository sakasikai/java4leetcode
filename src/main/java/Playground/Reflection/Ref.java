package Playground.Reflection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maiqi
 * @title ref
 * @description TODO
 * @create 2023/10/16 21:16
 */
public class Ref {

    public static void main(String[] args) throws ClassNotFoundException {
        Ref r = new Ref();
        r.t();
    }

    /**
     * @description: <p>
     * {@link Class#getDeclaredClasses()} 返回内部定义类or接口
     * </p>
     * <p>
     * {@link Class#forName(String)} 根据类的全路径名，得到Class对象
     * </p>
     * @author: maiqi
     * @update: 2023/10/16 21:16
     */
    public void t() throws ClassNotFoundException {
        // It returns the Class object with the given class name
        List<String> data = Stream.of("java.util.ArrayList", "java.lang.String").collect(Collectors.toList());

        List<? extends Class<?>> cls = data.stream().map(cl -> {
            try {
                return Class.forName(cl);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        // It returns an array of Classes
        // that denotes the private, protected, public and default
        // classes of the class t
        for (Class<?> cl : cls) {
            System.out.printf("[%s] declared Classes: %n", cl.getSimpleName());
            Class<?>[] retCls = cl.getDeclaredClasses();
            Arrays.stream(retCls).forEach(retCl -> System.out.println(retCl.getName()));
        }


    }
}
