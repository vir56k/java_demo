package cn.zyfvir;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        try {
            // 装载一个 调度器
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            // 创建一个 job 作业
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                    .withIdentity("job1", "group1")
                    .build();
            // 创建触发器
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("triger1", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder         // 创建执行日程，这里是间隔4秒且始终循环
                            .simpleSchedule()
                            .withIntervalInSeconds(4)
                            .repeatForever())
                    .build();
            // 将 作业 加入到调度器中
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        // 为了让程序不终止
        Thread.sleep(60000);
    }

    public static class MyJob implements Job {

        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            System.out.println("Run ..." + now);
        }
    }
}
