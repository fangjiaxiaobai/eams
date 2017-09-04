package com.fxb.eams.modules.quartz.entity;

import org.quartz.JobDataMap;

import java.util.Date;

/**
 * @create by: wangxiyue on 2017/8/24 9:42.
 * @ClassName:
 * @userfor:
 * @description:
 */
public class JobEntity {
    private String jobName;
    private String jobGroup;
    private String triggerName;
    private String triggerGroup;
    private String cron;
    private Date previousFireTime;
    private Date nextFireTime;
    private String jobStatus;
    private long runTimes;
    private long duration;
    private Date startTime;
    private Date endTime;
    private String jobDemo;
    private String jobClass;  //要执行的任务类
    private String jobMethod; // 任务执行的方法
    private String jobObject;
    private int count;
    private String description;
    private JobDataMap jobDataMap;

    public String getJobName(){
        return jobName;
    }

    public void setJobName(String jobName){
        this.jobName=jobName;
    }

    public String getJobGroup(){
        return jobGroup;
    }

    public void setJobGroup(String jobGroup){
        this.jobGroup=jobGroup;
    }

    public String getTriggerName(){
        return triggerName;
    }

    public void setTriggerName(String triggerName){
        this.triggerName=triggerName;
    }

    public String getTriggerGroup(){
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup){
        this.triggerGroup=triggerGroup;
    }

    public String getCron(){
        return cron;
    }

    public void setCron(String cron){
        this.cron=cron;
    }

    public Date getPreviousFireTime(){
        return previousFireTime;
    }

    public void setPreviousFireTime(Date previousFireTime){
        this.previousFireTime=previousFireTime;
    }

    public Date getNextFireTime(){
        return nextFireTime;
    }

    public void setNextFireTime(Date nextFireTime){
        this.nextFireTime=nextFireTime;
    }

    public String getJobStatus(){
        return jobStatus;
    }

    public void setJobStatus(String jobStatus){
        this.jobStatus=jobStatus;
    }

    public long getRunTimes(){
        return runTimes;
    }

    public void setRunTimes(long runTimes){
        this.runTimes=runTimes;
    }

    public long getDuration(){
        return duration;
    }

    public void setDuration(long duration){
        this.duration=duration;
    }

    public Date getStartTime(){
        return startTime;
    }

    public void setStartTime(Date startTime){
        this.startTime=startTime;
    }

    public Date getEndTime(){
        return endTime;
    }

    public void setEndTime(Date endTime){
        this.endTime=endTime;
    }

    public String getJobDemo(){
        return jobDemo;
    }

    public void setJobDemo(String jobDemo){
        this.jobDemo=jobDemo;
    }

    public String getJobClass(){
        return jobClass;
    }

    public void setJobClass(String jobClass){
        this.jobClass=jobClass;
    }

    public String getJobObject(){
        return jobObject;
    }

    public void setJobObject(String jobObject){
        this.jobObject=jobObject;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count=count;
    }

    public JobDataMap getJobDataMap(){
        return jobDataMap;
    }

    public void setJobDataMap(JobDataMap jobDataMap){
        this.jobDataMap=jobDataMap;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getDescription(){
        return description;
    }

    public void setJobMethod(String jobMethod){
        this.jobMethod=jobMethod;
    }

    public String getJobMethod(){
        return jobMethod;
    }
}
