package com.quartz.demo.test;

import com.quartz.demo.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.TimeUnit;


/**
 * @create 2024/2/7
 * @create 16:51
 */
public class QuartzTest {
    public static void main(String[] args) {

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            JobDetail job = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(5)
                            .repeatForever())
                    .build();

            Trigger trigger2 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(5)
                            .repeatForever())
                    .build();

            System.out.println("线程个数 : "+scheduler.getMetaData().getThreadPoolSize());

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
            // 0 5 10 15 20
//            TimeUnit.SECONDS.sleep(20);
            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
