package com.fxb.eams.modules.test.web;

import com.fxb.eams.common.utils.SendMailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 方小白 on 2017/8/31 20:13.
 * @description
 */

@Component("com.fxb.eams.modules.test.web.TestTaskSendMail")
public class TestTaskSendMail {

    private Logger logger =LoggerFactory.getLogger("SendMailLog");
    public void sendMail(){
        String toMailAddr = "fangxiaobai123@163.com";
        String subject = "发送邮件";
        String message = "小白<br/>,上天赐我阳光，我定天天向上!";

        SendMailUtil.sendCommonMail(toMailAddr,subject,message);

        logger.info("[定时任务 开始]  发送到"+toMailAddr);


    }

}
