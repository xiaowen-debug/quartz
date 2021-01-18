package com.xiaowen.quartz.test;

import com.xiaowen.quartz.tools.DFUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.StringJoiner;


/**
 * @author xiaowen
 * @create 2021/1/15 22:22
 */
public class HelloJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {

        StringJoiner stringJoiner = new StringJoiner(" ")
                .add("HelloJob.execute :")
                .add(DFUtil.format(new Date()))
                .add(Thread.currentThread().getName())
                .add(context.getTrigger().getKey().getName());

        System.out.println(stringJoiner);
    }
}