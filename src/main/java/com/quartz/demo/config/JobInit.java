package com.quartz.demo.config;

import com.quartz.demo.job.SpringJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JobInit {

    @Autowired
    public Scheduler scheduler;

    @PostConstruct
    public void initjob() throws SchedulerException {
        System.out.println("线程数:" + scheduler.getMetaData().getThreadPoolSize());
        JobDetail detail = JobBuilder.newJob(SpringJob.class).build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                .build();
        scheduler.scheduleJob(detail, trigger);
    }
}
