package com.fxb.eams.modules.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 方小白 on 2017/8/29 21:11.
 * @description
 */
@Component("com.fxb.eams.modules.quartz.job.MyJob")
public class MyJob {

    public void execute() throws JobExecutionException{
        System.out.println("★★★★★★★★★★★"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
