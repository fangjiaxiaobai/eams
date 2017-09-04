package com.fxb.eams.modules.quartz.job;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @create by: 方小白 on 2017/8/24 9:19.
 * @ClassName:
 * @description:
 *         任务类。
 */
public class QuartzJob implements org.quartz.Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException{
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"★★★★★★★★★★★");
    }
}
