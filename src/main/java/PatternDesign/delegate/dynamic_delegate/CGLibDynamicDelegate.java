package PatternDesign.delegate.dynamic_delegate;

import PatternDesign.delegate.entity.Order;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author maiqi
 * @Title: CGLibDynamicDelegate
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/7/10 16:05
 */
public class CGLibDynamicDelegate implements MethodInterceptor {

    public Object getInstance(Class<?> clazz) throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object ret = methodProxy.invokeSuper(o, objects);
        after(ret);

        return ret;
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
