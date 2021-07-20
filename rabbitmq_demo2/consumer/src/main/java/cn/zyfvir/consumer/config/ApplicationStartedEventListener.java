package cn.zyfvir.consumer.config;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent>, Ordered {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent ev) {
        System.out.println("# 应用启动...");
    }

    @Override
    public int getOrder() {
        return 1;
    }

}