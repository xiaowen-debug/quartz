package com.xiaowen.quartz.jobConfig;

import com.xiaowen.quartz.job.SpringJob1;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaowen
 * @create 2021/1/18 22:38
 *
 * springboot集成quartz方式二
 *
 * springboot會自動調度任務
 */
@Configuration
public class JobConfig {

    @Bean
    public JobDetail springJobDetail() {
        return JobBuilder.newJob(SpringJob1.class)
                .withIdentity("springJobDetail")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger springJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob("springJobDetail")
                .startNow()
                .build();
    }
}