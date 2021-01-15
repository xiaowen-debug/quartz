package com.xiaowen.quartz.first;

import com.xiaowen.quartz.tools.DFUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;


/**
 * @author xiaowen
 * @create 2021/1/15 22:22
 */
public class HelloJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("HelloJob.execute : " + DFUtil.format(new Date()) + "  " + Thread.currentThread().getName());
    }
}