package com.quartz.demo.job;

import com.quartz.demo.service.HelloSpringService;
import com.quartz.demo.test.DFUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.StringJoiner;

public class SpringJob extends QuartzJobBean {

    @Autowired
    private HelloSpringService helloSpringService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String schedulerInstanceId = "default";
        try {
            schedulerInstanceId = context.getScheduler().getMetaData().getSchedulerInstanceId();
        } catch (SchedulerException e) {
        }
        StringJoiner outStr = new StringJoiner(" | ")
                .add("SpringJob.executeInternal")
                .add(DFUtil.format(new Date()).replaceAll("-", ""))
                .add(context.getJobDetail().getKey().getName())
                .add(context.getJobDetail().getJobDataMap().getString("key0"))
                .add(schedulerInstanceId)
                .add(helloSpringService.helloSpring());
        System.out.println(outStr);
    }
}
