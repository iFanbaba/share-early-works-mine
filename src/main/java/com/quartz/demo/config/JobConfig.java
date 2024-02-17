package com.quartz.demo.config;

import com.quartz.demo.job.SpringJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {
//
//    @Bean
//    @QuartzDataSource
//    public DataSource qdataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUsername("");
//        dataSource.setPassword("");
//        dataSource.setUrl("");
//        return dataSource;
//    }

    @Bean
    public JobDetail springJob1Detail() {
        return JobBuilder.newJob(SpringJob.class)
                .withIdentity("springJob1Detail")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger springJob1Trigger() {
        return TriggerBuilder.newTrigger()
                .forJob("springJob1Detail")
                .startNow()
                .build();
    }
}
