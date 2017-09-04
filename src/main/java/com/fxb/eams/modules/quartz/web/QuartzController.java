package com.fxb.eams.modules.quartz.web;

import com.fxb.eams.common.config.Global;
import com.fxb.eams.modules.quartz.entity.JobEntity;
import com.fxb.eams.modules.quartz.service.QuartzService;
import com.google.common.collect.Lists;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * @author 方小白 on 2017/8/29 10:57.
 * @description
 */

@Controller
@RequestMapping("${adminPath}/sys/quartz/")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @Autowired
    private Scheduler scheduler;

    /**
     * 展示Job信息
     * @param jobEntity
     * @param model
     * @return
     */
    @RequestMapping({"list", ""})
    @RequiresPermissions("sys:quartz:view")
    public String list(JobEntity jobEntity, Model model){

        List <JobEntity> jobInfos=this.getJobInfos();
        model.addAttribute("jobInfos", jobInfos);
        model.addAttribute("jobEntity",jobEntity);
        return "modules/sys/quartz/jobList";
    }

    /**
     * 获取所有job信息
     * @return
     */
    private List <JobEntity> getJobInfos(){
        try {
            List <JobEntity> jobInfos=Lists.newArrayList();
            List <String> triggerGroupNames=scheduler.getTriggerGroupNames();
            for (String triggerGroupName : triggerGroupNames) {
                Set <TriggerKey> triggerKeySet=scheduler.getTriggerKeys(GroupMatcher.triggerGroupContains(triggerGroupName));
                for (TriggerKey triggerKey : triggerKeySet) {
                    Trigger trigger=scheduler.getTrigger(triggerKey);
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger=(CronTrigger) trigger;
                        JobKey jobKey=cronTrigger.getJobKey();
                        JobDetail jobDetail=scheduler.getJobDetail(jobKey);
                        JobEntity jobEntity=new JobEntity();
                        jobEntity.setJobName(jobKey.getName());
                        jobEntity.setJobGroup(jobKey.getGroup());
                        jobEntity.setTriggerName(triggerKey.getName());
                        jobEntity.setTriggerGroup(triggerKey.getGroup());
                        jobEntity.setCron(cronTrigger.getCronExpression());
                        jobEntity.setNextFireTime(cronTrigger.getNextFireTime());
                        jobEntity.setPreviousFireTime(cronTrigger.getPreviousFireTime());
                        jobEntity.setStartTime(cronTrigger.getStartTime());
                        jobEntity.setEndTime(cronTrigger.getEndTime());
                        jobEntity.setJobClass(jobDetail.getJobClass().getCanonicalName());
                        Trigger.TriggerState triggerState=scheduler.getTriggerState(cronTrigger.getKey());
                        jobEntity.setJobStatus(triggerState.name());

                        JobDataMap map=jobDetail.getJobDataMap();
                        if (null != map && 0 != map.size()) {
                            jobEntity.setCount(Integer.parseInt((String) map.get("count")));
                            jobEntity.setJobDataMap(map);
                        } else {
                            jobEntity.setJobDataMap(map);
                        }
                        jobInfos.add(jobEntity);
                    }
                }
            }
            return jobInfos;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 跳转到添加任务的页面。
     * @param jobEntity
     * @param model
     * @return
     */
    @RequestMapping("form")
    public String form(JobEntity jobEntity, Model model){
        if(com.fxb.eams.common.utils.StringUtils.isNotBlank(jobEntity.getJobName())){
            //修复紧急bug
        }
        model.addAttribute("jobEntity",jobEntity);
        return "modules/sys/quartz/jobForm";
    }

    /**
     * 保存任务，
     * @param jobEntity
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("save")
    public String save(JobEntity jobEntity, RedirectAttributes redirectAttributes){
        try {
            quartzService.save(jobEntity);
        } catch (ClassNotFoundException e) {
            redirectAttributes.addAttribute("message", "添加定时任务失败");
        } catch (SchedulerException e) {
            redirectAttributes.addAttribute("message", "添加定时任务失败");
        }
        return "redirect:" + Global.getAdminPath() + "/sys/quartz/?repage";
    }

    /**
     * 暂停任务
     *
     * @param jobEntity
     */
    @ResponseBody
    @RequestMapping("pauseJob")
    public String puaseJob(JobEntity jobEntity){
        JSONObject json = new JSONObject();

        try {
            scheduler.pauseJob(JobKey.jobKey(jobEntity.getJobName(), jobEntity.getJobGroup()));
            json.put("status","success");

        } catch (SchedulerException e) {
            json.put("status","error");
        }
        return json.toString();
    }

    /**
     * 重新调度任务
     *
     * @param jobEntity
     */
    @RequestMapping("resumeJob")
    @ResponseBody
    public String resumeJob(JobEntity jobEntity){
        JSONObject jsonObject=new JSONObject();
        try {
            if (StringUtils.isEmpty(jobEntity.getJobName()) || StringUtils.isEmpty(jobEntity.getJobGroup())) {
                jsonObject.put("status", "wrong");
            } else {
                quartzService.resumeJob(jobEntity);
                jsonObject.put("status", "success");
            }
            scheduler.resumeJob(JobKey.jobKey(jobEntity.getJobName(), jobEntity.getJobGroup()));
        } catch (SchedulerException e) {
            e.printStackTrace();
            jsonObject.put("status", "wrong");
        }
        return jsonObject.toString();
    }

    /**
     * 删除任务
     * @param jobEntity
     * @return
     */
    @RequestMapping("deleteJob")
    @ResponseBody
    public String deleteJob(JobEntity jobEntity){
        JSONObject json=new JSONObject();
        if (StringUtils.isEmpty(jobEntity.getJobName()) || StringUtils.isEmpty(jobEntity.getJobGroup()) || StringUtils.isEmpty(jobEntity.getTriggerName()) || StringUtils.isEmpty(jobEntity.getTriggerGroup())) {
            json.put("status", "wrong");
        } else {
            quartzService.removeJob(jobEntity);
            json.put("status", "success");
        }
        return json.toString();
    }

    /**
     *  判断任务类是否存在，
     * @param clazz
     * @return json字符串
     */
    @ResponseBody
    @RequestMapping("jobClassIsExist")
    public String jobClassIsExist(String clazz){
        JSONObject json = new JSONObject();
        if(com.fxb.eams.common.utils.StringUtils.isNotBlank(clazz)) try {
            Class <?> cla=Class.forName(clazz);
            json.put("status", "success");

            Method[] methods = null;
            if (null != cla) {
                methods=cla.getDeclaredMethods();
            }

            JSONArray jsonArray = new JSONArray();
            for (Method method : methods) {
                jsonArray.put(method.getName());
            }
            json.put("methods",jsonArray);

        } catch (ClassNotFoundException e) {
            json.put("status", "error");
        }
        else{
            json.put("status","error");
        }
        return json.toString();
    }

}
