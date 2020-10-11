package io.quartz.example;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SchedulerException {
        Properties prop = new Properties();
        prop.setProperty("org.quartz.threadPool.threadCount", "20");

        SchedulerFactory schedulerFactory = new StdSchedulerFactory(prop);
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = JobBuilder.newJob(HelloWorldJob.class)
                .withIdentity("helloWorldJob", "group1")
                .usingJobData("name", "World")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("helloWorldTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                // .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
                .build();

        scheduler.scheduleJob(job, trigger);
        // scheduler.scheduleJob(job, trigger);

        scheduler.start();
    }
}
