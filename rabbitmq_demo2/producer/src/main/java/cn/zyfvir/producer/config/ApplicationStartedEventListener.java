package cn.zyfvir.producer.config;

import cn.zyfvir.producer.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println("# ApplicationStartedEventListener1");
    }

    @Override
    public int getOrder() {
        return 1;
    }

}