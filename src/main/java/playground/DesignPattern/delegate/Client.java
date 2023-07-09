package playground.DesignPattern.delegate;

import playground.DesignPattern.delegate.staticDelegate.Order;
import playground.DesignPattern.delegate.staticDelegate.OrderService;
import playground.DesignPattern.delegate.staticDelegate.OrderServiceStaticProxy;

/**
 * @author maiqi
 * @Title: JDKDynamicDelegate
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/7/9 11:04
 */
public class Client {
    public static void main(String[] args) {
        Client c = new Client();
        c.doStaticProxy();
    }

    public void doStaticProxy() {
        OrderServiceStaticProxy p = new OrderServiceStaticProxy(new OrderService());
        // OrderServiceStaticProxy delegate operation for OrderService
        Order order = p.createOrder();
        System.out.println("checking final order:");
        System.out.println(order);
    }
}
