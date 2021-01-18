package com.xiaowen.quartz.test;

import org.quartz.*;
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
 * CronTriggerImpl (org.quartz.impl.triggers)
 * DailyTimeIntervalTriggerImpl (org.quartz.impl.triggers)
 * SimpleTriggerImpl (org.quartz.impl.triggers)
 * CalendarIntervalTriggerImpl (org.quartz.impl.triggers)
 */
public class _05_QuartzTest_CronExpression {

    public static void main(String[] args) {

        try {
            //创建默认调度工厂
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            //创建job
            JobDetail job1 = newJob(HelloJob.class)
                    .build();

            /*
            //创建trigger
            Trigger trigger = newTrigger()
                    //启动
                    .startNow()
                    //启动方式-策略
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            //执行周期4S
                        .withIntervalInSeconds(1)
                            //重复执行次数
                        .withRepeatCount(1)
                            //循环
                        //.repeatForever()
                            )
                    .build();
            */

            //创建trigger
            Trigger trigger = newTrigger()
                    //启动
                    .startNow()
                    //启动方式-策略
                    .withSchedule(CronScheduleBuilder.cronSchedule("* * * ? * * *")
                    )
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