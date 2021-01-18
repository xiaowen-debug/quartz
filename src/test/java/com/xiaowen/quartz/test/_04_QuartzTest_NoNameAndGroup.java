package com.xiaowen.quartz.test;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author xiaowen
 * @create 2021/1/15 22:06
 *
 * 触发器
 *
 * 系统只有一个定时任务，则Job Trigger标识可以省略
 */
public class _04_QuartzTest_NoNameAndGroup {

    public static void main(String[] args) {

        try {
            //创建默认调度工厂
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            //创建job
            JobDetail job1 = newJob(HelloJob.class)
                    .build();

            //创建trigger
            Trigger trigger = newTrigger()
                    //启动
                    .startNow()
                    //启动方式-策略
                    .withSchedule(simpleSchedule()
                            //执行周期4S
                        .withIntervalInSeconds(1)
                            //循环
                        .repeatForever())
                    .build();

            scheduler.scheduleJob(job1, trigger);

            TimeUnit.SECONDS.sleep(3);

            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}