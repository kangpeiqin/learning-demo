import com.example.RabbitDemoApp;
import com.example.constant.DelayConstant;
import com.example.enums.OrderStatusEnum;
import com.example.enums.PayStatusEnum;
import com.example.model.Order;
import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author kpq
 * @date 2024/8/20 17:37
 */
@SpringBootTest(classes = RabbitDemoApp.class)
@Slf4j
public class OrderTest {

    @Resource
    private OrderService orderService;

    @Test
    public void createOrder() {
        Date curDate = new Date();
        Order myOrder = new Order().setOrderId(String.valueOf(Math.round(Math.random()*1000)))
                .setName("北京-杭州").setOrderTime(curDate)
                .setOrderStatus(OrderStatusEnum.UNPAID.getStatus())
                .setPayStatus(PayStatusEnum.UNPAID.getStatus());
        Order testOrder = new Order().setOrderId(String.valueOf(Math.round(Math.random()*1000)))
                .setName("北京-深圳").setOrderTime(curDate)
                .setOrderStatus(OrderStatusEnum.UNPAID.getStatus())
                .setPayStatus(PayStatusEnum.UNPAID.getStatus());
        orderService.sendOrderMessage(myOrder, myOrder.getOrderId(), DelayConstant.ORDER_DELAY_EXCHANGE, DelayConstant.ORDER_DELAY_ROUTING_KEY);
        orderService.sendOrderMessage(testOrder, testOrder.getOrderId(), DelayConstant.ORDER_DELAY_EXCHANGE, DelayConstant.ORDER_DELAY_ROUTING_KEY);
    }

}
