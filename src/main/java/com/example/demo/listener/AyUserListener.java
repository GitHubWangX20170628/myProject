package com.example.demo.listener;

import com.example.demo.model.AyUser;
import com.example.demo.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author wangX
 * @Decription:前端监听器
 * @date 2018/7/17 16:57
 */
//@WebListener
public class AyUserListener implements ServletContextListener {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private AyUserService ayUserService;
    private static final String ALL_USER = "ALL_USER_LIST";
    //需要添加代码
    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //查询数据库所有用户
        List<AyUser> ayUserList = ayUserService.findAll();
        //清楚缓存总的用户数据
        redisTemplate.delete(ALL_USER);
        // 将数据存放到Redis缓存中去
        redisTemplate.opsForList().leftPushAll(ALL_USER, ayUserList);
        //在真是项目中需要注释掉，查询所有的用户数据
        List<AyUser> queryUserList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        //System.out.println("缓存中目前的用户数有：" + queryUserList.size() + "人");
        //System.out.println("ServletContext上下文初始化");
        logger.info("ServletContext 上下文初始化");
        logger.info("缓存中目前的用户数量有：" + queryUserList.size() + "人");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //System.out.println("ServletContext上下文销毁");
        logger.info("ServletContext上下文销毁");
    }
}
