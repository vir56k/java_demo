package cn.zyfvir.jobs;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 */
public class HiJob extends QuartzJobBean {
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        String job_url = map.getString("job_url");
        System.out.println("    Hi! :" + jobExecutionContext.getJobDetail().getKey());
        System.out.println("    job_url is :" + job_url);
    }
}
