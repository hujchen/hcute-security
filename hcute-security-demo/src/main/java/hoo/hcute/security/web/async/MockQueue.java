package hoo.hcute.security.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockQueue {

    private Logger logger = LoggerFactory.getLogger(getClass());
    // 下单的消息
    private String placeOrder;

    // 处理订单完成的消息
    private String completeOder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        new Thread(() -> {
            logger.info("接到下单请求，" + placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOder = placeOrder;
            logger.info("下单请求处理完成，" + placeOrder);
        }).start();

    }

    public String getCompleteOder() {
        return completeOder;
    }

    public void setCompleteOder(String completeOder) {
        this.completeOder = completeOder;
    }
}
