package com.xiaowen.quartz.test;

import com.xiaowen.quartz.service.HelloSpringService;
import com.xiaowen.quartz.tools.DFUtil;
import com.xiaowen.quartz.tools.SpringContextUtil;
import org.quartz.*;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.StringJoiner;


/**
 * @author xiaowen
 * @create 2021/1/15 22:22
 */
public class HelloDataJob implements Job {

    public HelloSpringService helloSpringService;

    public void execute(JobExecutionContext context) throws JobExecutionException {

        helloSpringService = (HelloSpringService) SpringContextUtil.applicationContext
                .getBean(StringUtils.uncapitalize(HelloSpringService.class.getSimpleName()));

        JobDetail jobDetail = context.getJobDetail();
        Trigger trigger = context.getTrigger();

        StringJoiner stringJoiner = new StringJoiner(" ")
                .add("HelloJob.execute :")
                .add(DFUtil.format(new Date()))
                .add(Thread.currentThread().getName())
                .add(context.getTrigger().getKey().getName());
        System.out.println(stringJoiner);

        System.out.println(jobDetail.getJobDataMap().get("job-key"));
        System.out.println(trigger.getJobDataMap().get("trigger-key"));

        /**
         * getMergedJobDataMap 若job、trigger有相同的key，则job的value被trigger覆盖
         */
        System.out.println(context.getMergedJobDataMap().get("xiaowen"));

        System.out.println(helloSpringService);
    }
}