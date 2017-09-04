package com.fxb.eams.modules.quartz.service;

import com.fxb.eams.modules.quartz.entity.JobEntity;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * @author wangxiyue on 2017/8/29 10:58.
 * @description
 */

@Service
public class QuartzService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobDetail jobDetail;


    /**
     * 保存定时任务
     * @param jobEntity
     * @throws ClassNotFoundException
     * @throws SchedulerException
     */
    public void save(JobEntity jobEntity) throws ClassNotFoundException, SchedulerException{

        try {
            TriggerKey triggerKey = new TriggerKey(jobEntity.getTriggerName(),jobEntity.getTriggerGroup());
            CronTriggerImpl cronTrigger=new CronTriggerImpl();
            cronTrigger.setKey(triggerKey);

            //设置cron表达式
            cronTrigger.setCronExpression(jobEntity.getCron());
            cronTrigger.setDescription(jobEntity.getDescription());

            JobDataMap map = new JobDataMap();
            map.put("targetObject",jobEntity.getJobClass());
            map.put("targetMethod",jobEntity.getJobMethod());
            cronTrigger.setJobDataMap(map);

            //设置定时任务的job
            cronTrigger.setJobName(jobDetail.getKey().getName());
            cronTrigger.setJobGroup(jobDetail.getKey().getGroup());

            cronTrigger.setName(jobEntity.getTriggerName());
            cronTrigger.setGroup(jobEntity.getTriggerGroup());
            if(scheduler.checkExists(triggerKey)){
                scheduler.rescheduleJob(triggerKey,cronTrigger);
            }else{
                // 不存在就添加
                scheduler.addJob(jobDetail,true);
                scheduler.scheduleJob(cronTrigger);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    /**
     *  关闭调度
     */
    public void shutdownSchedule(){
        try {
            if (scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启调度
     */
    public void startSchedule(){
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重新调度任务
     * @param jobEntity
     * @throws SchedulerException
     */
    public void resumeJob(JobEntity jobEntity) throws SchedulerException{
        scheduler.resumeJob(JobKey.jobKey(jobEntity.getJobName(),jobEntity.getJobGroup()));
    }

    /**
     * 移除触发器
     * @param jobEntity
     */
    public void removeJob(JobEntity jobEntity){
        try {
            // 停止触发器
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobEntity.getTriggerName(),jobEntity.getTriggerGroup()));
            // 移除触发器
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobEntity.getTriggerName(),jobEntity.getTriggerGroup()));
            // 删除触发器
            scheduler.deleteJob(JobKey.jobKey(jobEntity.getJobName(),jobEntity.getJobGroup()));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
