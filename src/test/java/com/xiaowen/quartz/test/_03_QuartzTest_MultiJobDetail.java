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
 * 一个Job可以被多个JobDetail引用
 */
public class _03_QuartzTest_MultiJobDetail {

    public static void main(String[] args) {

        try {
            //创建默认调度工厂
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            //创建job
            JobDetail job1 = newJob(HelloJob.class)
                    //标识
                    .withIdentity("job1", "group1")
                    .build();

            //创建job
            JobDetail job2 = newJob(HelloJob.class)
                    //标识
                    .withIdentity("job2", "group1")
                    .build();

            //创建trigger
            Trigger trigger = newTrigger()
                    //标识
                    .withIdentity("trigger1", "group1")
                    //启动
                    .startNow()
                    //启动方式-策略
                    .withSchedule(simpleSchedule()
                            //执行周期4S
                        .withIntervalInSeconds(1)
                            //循环
                        .repeatForever())
                    .build();

            Trigger trigger2 = newTrigger()
                    //标识
                    .withIdentity("trigger2", "group1")
                    //指定执行任务
                    .forJob("job2", "group1")
                    //启动
                    .startNow()
                    //启动方式-策略
                    .withSchedule(simpleSchedule()
                            //执行周期4S
                            .withIntervalInSeconds(3)
                            //循环
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(job1, trigger);

            scheduler.scheduleJob(job2, trigger2);

            TimeUnit.SECONDS.sleep(3);

            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}