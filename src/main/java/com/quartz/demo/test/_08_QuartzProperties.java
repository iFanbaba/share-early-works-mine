package com.quartz.demo.test;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class _08_QuartzProperties {

    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            System.out.println("getSchedulerName "+scheduler.getSchedulerName());
            System.out.println("线程个数 "+ scheduler.getMetaData().getThreadPoolSize());

            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
