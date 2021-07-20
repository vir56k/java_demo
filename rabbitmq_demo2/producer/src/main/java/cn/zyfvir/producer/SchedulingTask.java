package cn.zyfvir.producer;

import cn.zyfvir.producer.config.RabbitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
@Configuration
@EnableScheduling
public class SchedulingTask {
    @Autowired
    private BusinessService businessService;

    private AtomicInteger atomicInteger = new AtomicInteger(1);


    // 定时任务，3秒一次
    @Scheduled(fixedRate = 3000)
    public void scheduledSendMessage() {
        System.out.println("# scheduledSendMessage");
        int i = atomicInteger.incrementAndGet();
        long millis = System.currentTimeMillis();
        String msg = String.format("msg_%s_%s", i, millis);
        businessService.sendMessage(msg);
    }
}
