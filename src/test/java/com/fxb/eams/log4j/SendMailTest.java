package com.fxb.eams.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author 方小白 on 2017/9/4 11:03.
 * @description
 */
public class SendMailTest {

    public static void main(String[] args){
        PropertyConfigurator.configure("classpath:/log4j.properties");
        //在后台输出
        Logger logger1 = Logger.getLogger("console");

        logger1.error("测试  发生error级别的错误");

    }

}
