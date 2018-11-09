package com.example.demo.quartz;

import com.example.demo.mail.SendJunkMailService;
import com.example.demo.model.AyUser;
import com.example.demo.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author wangX
 * @Decription:定时器类（注解模式）
 * @date 2018/7/19 20:15
 */
@Component
@Configuration
@EnableScheduling
public class SendMailQuartz {
    //日志对象
    private static final Logger logger = LogManager.getLogger(SendMailQuartz.class);
    @Autowired
    private SendJunkMailService sendJunkMailService;
    @Resource
    private AyUserService ayUserService;

    //每五秒执行一次
    @Scheduled(cron = "0 */1 * * * ?")
    public void reportCurrentByCron() {
       /* List<AyUser> userList = ayUserService.findAll();
        if(userList ==null || userList.size()<-0)return;*/
        //sendJunkMailService.sendJunMail(userList);
        //logger.info("定时器运行了！！！！");

    }
}
