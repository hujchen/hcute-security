package hoo.hcute.security.web.async;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;


    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        new Thread( () -> {
            while (true) {
                if (StringUtils.isNotBlank(mockQueue.getCompleteOder())){
                    String orderNumber = mockQueue.getCompleteOder();
                    logger.info("返回订单处理结果：" + orderNumber);
                    // 处理完向浏览器返回结果
                    deferredResultHolder.getMap().get(orderNumber).setResult("placeOrder success");
                    mockQueue.setCompleteOder(null);
                }else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
