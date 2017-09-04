package com.fxb.eams.modules.quartz.job;

import com.fxb.eams.common.utils.SpringContextHolder;
import javafx.application.Application;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author fangxi on 2017/8/31 20:05.
 * @description
 */
public class DetailQuartzJobBean extends QuartzJobBean{

    private String targetObject;
    private String targetMethod;
    private final ApplicationContext applicationContext;

    private static final Logger logger =LoggerFactory.getLogger("QuartzLog");

    public DetailQuartzJobBean(){
        applicationContext = SpringContextHolder.getApplicationContext();
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException{
        Object otargetObject = applicationContext.getBean(targetObject);
        logger.info("定时任务 开始 "+targetObject+"."+targetMethod);

        Method m = null;
        try {
            m = otargetObject.getClass().getMethod(targetMethod,new Class[]{});
            m.invoke(otargetObject,new Object[]{});
            logger.info("定时任务 结束 "+targetObject+"."+targetMethod);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void setTargetMethod(String targetMethod){
        this.targetMethod=targetMethod;
    }

    public void setTargetObject(String targetObject){
        this.targetObject=targetObject;
    }
}
