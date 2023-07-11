package playground.DesignPattern.delegate.dynamic_delegate;

import playground.DesignPattern.delegate.entity.Order;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author maiqi
 * @Title: JDKDynamicHandler
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/7/10 14:19
 */
public class JDKDynamicDelegate implements InvocationHandler {
    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.before();
        Object ret = method.invoke(target, args);
        this.after(ret);

        return ret;
    }

    /**
     * 　* @Description: 动态生成Proxy对象，并不是在静态编译阶段
     * 　* @author: maiqi
     * 　* @date: 2023/7/10 15:59
     *
     **/
    public Object getInstance(Object target) {
        this.target = target;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    public void before() {
        System.out.println("received demand...");
        System.out.println("[+]starting creating Order...");
        System.out.print("===> ");
    }

    public void after(Object order) {
        Order var2 = (Order) order;
        var2.setOrderInfo(String.format("made by proxy[%s]", this.getClass().getSimpleName()));
        System.out.println("[-]done");
    }
}
