package com.xiaowen.quartz.test;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * @author xiaowen
 * @create 2021/1/15 22:06
 */
public class _01_QuartzTest {

    public static void main(String[] args) {

        try {
            //创建默认调度工厂
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            //创建job
            JobDetail job = newJob(HelloJob.class)
                    //标识
                    .withIdentity("job1", "group1")
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
                        .withIntervalInSeconds(4)
                            //循环
                        .repeatForever())
                    .build();

            scheduler.scheduleJob(job, trigger);

            TimeUnit.SECONDS.sleep(20);

            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}