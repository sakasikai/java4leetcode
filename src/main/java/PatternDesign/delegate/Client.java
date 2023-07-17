package PatternDesign.delegate;

import PatternDesign.delegate.Interface.IOrderService;
import PatternDesign.delegate.dynamic_delegate.CGLibDynamicDelegate;
import PatternDesign.delegate.dynamic_delegate.JDKDynamicDelegate;
import PatternDesign.delegate.entity.Order;
import PatternDesign.delegate.static_delegate.OrderService;
import PatternDesign.delegate.static_delegate.OrderServiceStaticProxy;
import playground.utils.SimpleMainResolver;

/**
 * @author maiqi
 * @Title: JDKDynamicDelegate
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/7/9 11:04
 */
public class Client {
    public static void main(String[] args) {
        SimpleMainResolver r = new SimpleMainResolver(Client.class);
        r.invokeAll();
    }

    public void doStaticProxy() {
        IOrderService p = new OrderServiceStaticProxy(new OrderService());
        Order order = p.createOrder();
        System.out.println("checking generated Order:");
        System.out.println(order);
    }

    public void doJDKDynamicProxy() {
        IOrderService p = ((IOrderService) new JDKDynamicDelegate().getInstance(new OrderService()));
        Order order = p.createOrder();
        System.out.println("checking generated Order:");
        System.out.println(order);
    }

    public void doCGLibDynamicProxy() {
        IOrderService p = null;
        try {
            p = ((IOrderService) new CGLibDynamicDelegate().getInstance(OrderService.class));
            Order order = p.createOrder();
            System.out.println("checking generated Order:");
            System.out.println(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
