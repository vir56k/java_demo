package cn.zyfvir.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 转配：构建一个 jobDetail 包裹 job，并创建一个 trigger 触发器关联到 这个 jobDetail。
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        JobDetail jobDetail = JobBuilder.newJob(HiJob.class)
                .withIdentity("myJob1", "myJobGroup1")
                //JobDataMap可以给任务execute传递参数
                .usingJobData("job_url", "http://www.baidu.com")
                .storeDurably()
                .build();
        return jobDetail;
    }

    @Bean
    public Trigger trigger() {
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("myTrigger1", "myTriggerGroup1")
                .usingJobData("job_trigger_param", "job_trigger_param1")
                .startNow()
                //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? "))
                .build();
        return trigger;
    }
}
