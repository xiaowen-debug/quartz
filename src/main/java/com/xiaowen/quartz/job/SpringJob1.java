package com.xiaowen.quartz.job;

import com.xiaowen.quartz.service.HelloSpringService;
import com.xiaowen.quartz.tools.DFUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.StringJoiner;

/**
 * @author xiaowen
 * @create 2021/1/18 22:27
 */
public class SpringJob1 extends QuartzJobBean {

    @Autowired
    private HelloSpringService helloSpringService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        StringJoiner stringJoiner = new StringJoiner(" | ")
                .add("SpringJob1.executeInternal :")
                .add(DFUtil.format(new Date()))
                .add(Thread.currentThread().getName())
                .add(helloSpringService.toString())
                .add(helloSpringService.helloSpring());

        System.out.println(stringJoiner);
    }
}