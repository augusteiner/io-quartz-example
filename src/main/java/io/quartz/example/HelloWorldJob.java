package io.quartz.example;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@DisallowConcurrentExecution
public class HelloWorldJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println(fmt.format(LocalDateTime.now()) + ": Inicio da execução do Job...");

        try {
            Thread.sleep(60_000);
        } catch (InterruptedException e) {
            throw new JobExecutionException(e);
        }

        Map data = context.getJobDetail().getJobDataMap();

        System.out.println(fmt.format(LocalDateTime.now()) + ": Hello " + data.get("name") + "!");
    }
}
