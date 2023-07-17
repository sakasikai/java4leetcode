package PatternDesign.delegate.static_delegate;

import PatternDesign.delegate.Interface.IOrderService;
import PatternDesign.delegate.entity.Order;

/**
 * @author maiqi
 * @Title: OrderServiceStaticProxy
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/7/9 11:14
 */
public class OrderServiceStaticProxy implements IOrderService {
    OrderService target;

    public OrderServiceStaticProxy(OrderService orderService) {
        this.target = orderService;
    }

    @Override
    public Order createOrder() {
        this.before();
        Order ret = target.createOrder();
        this.after(ret);

        return ret;
    }

    public void before() {
        System.out.println("received demand...");
        System.out.println("[+]starting creating Order...");
        System.out.print("===> ");
    }

    public void after(Order order) {
        order.setOrderInfo(String.format("made by proxy[%s]", this.getClass().getSimpleName()));
        System.out.println("[-]done");
    }
}
