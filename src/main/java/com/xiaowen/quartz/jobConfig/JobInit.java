package com.xiaowen.quartz.jobConfig;

import com.xiaowen.quartz.job.SpringJob1;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;

/**
 * @author xiaowen
 * @create 2021/1/18 22:30
 *
 * springboot集成quartz方式一
 */
//@Component
public class JobInit {

    @Autowired
    public Scheduler scheduler;

    /**
     *  @PostConstruct修饰的方法会在服务器加载Servle的时候运行，
     *  并且只会被服务器执行一次。
     *  PostConstruct在构造函数之后执行
     */
    @PostConstruct
    public void initJob() throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(SpringJob1.class)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

}