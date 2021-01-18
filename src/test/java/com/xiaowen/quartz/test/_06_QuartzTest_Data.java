package com.xiaowen.quartz.test;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author xiaowen
 * @create 2021/1/15 22:06
 *
 * 传值
 */
public class _06_QuartzTest_Data {

    public static void main(String[] args) {

        try {
            //创建默认调度工厂
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            //创建job
            JobDetail job1 = newJob(HelloDataJob.class)
                    .usingJobData("job-key", "job-value")
                    .usingJobData("xiaowen", "xiaowen_job")
                    .withIdentity("job1", "group1")
                    .build();

            //创建trigger
            Trigger trigger = newTrigger()
                    .usingJobData("trigger-key", "trigger-value")
                    .usingJobData("xiaowen", "xiaowen_trigger")
                    .withIdentity("job1", "group1")
                    //启动
                    .startNow()
                    //启动方式-策略
                    .withSchedule(CronScheduleBuilder.cronSchedule("* * * ? * * *")
                    )
                    .build();

            scheduler.scheduleJob(job1, trigger);

            TimeUnit.SECONDS.sleep(1);

            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}