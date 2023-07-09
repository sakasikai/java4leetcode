package playground.DesignPattern.delegate.staticDelegate;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author maiqi
 * @Title: OrderService
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/7/9 11:13
 */
@NoArgsConstructor
public class OrderService implements IOrderService {
    /**
     * 　* @Description: 真正创建Order的主体，负责生成ID和创建时间
     * 　* @author: maiqi
     * 　* @date: 2023/7/9 11:33
     *
     **/
    @Override
    public Order createOrder() {
        Order order = null;
        try {
            order = Order.class.newInstance();
            order.setId(String.valueOf(UUID.randomUUID().hashCode()));
            order.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("OrderService has created an order[%s]%n", order);
        return order;
    }
}
