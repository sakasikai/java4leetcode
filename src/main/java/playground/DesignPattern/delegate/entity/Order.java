package playground.DesignPattern.delegate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author maiqi
 * @Title: Order
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/7/9 11:11
 */
@Data
@NoArgsConstructor
public class Order {
    private String orderInfo;
    private String createTime;
    private String id;

    @Override
    public String toString() {
        return "Order{" +
                "orderInfo='" + orderInfo + '\'' +
                ", createTime='" + createTime + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
