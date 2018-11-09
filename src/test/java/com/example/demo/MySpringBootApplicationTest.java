package com.example.demo;

import com.example.demo.model.AyMood;
import com.example.demo.model.AyUser;
import com.example.demo.pub.AyMoodProducer;
import com.example.demo.repository.AyMoodRepository;
import com.example.demo.service.AyMoodService;
import com.example.demo.service.AyUserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringBootApplicationTest {
    Logger logger = LogManager.getLogger(this.getClass());
    @Resource
    private AyUserService ayUserService;
    @Resource
    private AyMoodRepository ayMoodRepository;
    @Resource
    private AyMoodProducer ayMoodProducer;
    @Resource
    private AyMoodService ayMoodService;
    @Test
    public void testRepository() {
        //查詢所有數據
        List<AyUser> userList = ayUserService.findAll();
        System.out.println("findAll():" + userList.size());
        //通過name查詢數據
        List<AyUser> userList1 = ayUserService.findByName("啊毅");
        System.out.println("findByName():" + userList1.size());
        Assert.isTrue("啊毅".equals(userList1.get(0).getName()), "data error!");
        //通過name模糊查詢數據
        List<AyUser> userList2 = ayUserService.findByNameLike("%毅%");
        System.out.println("findByNameLike():" + userList2.size());
        Assert.isTrue("啊毅".equals(userList2.get(0).getName()), "data error!");
        //通過id列表查詢數據
        List<String> ids = new ArrayList<String>();
        ids.add("1");
        ids.add("2");
        List<AyUser> userList3 = ayUserService.findByIdIn(ids);
        System.out.println("findByIdIn():" + userList3.size());
        //分頁查詢數據
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<AyUser> userList4 = ayUserService.findAll(pageRequest);
        System.out.println("page findAll():" + userList4.getTotalPages() + "/" + userList4.getSize());
        //新增數據
        AyUser ayUser = new AyUser();
        ayUser.setId("4");
        ayUser.setName("啊华");
        ayUser.setPassword("123456");
        ayUserService.save(ayUser);
        //刪除數據
        ayUserService.delte("3");

    }

    @Test
    public void testTransaction() {
        AyUser ayUser = new AyUser();
        ayUser.setId("4");
        ayUser.setName("啊华");
        ayUser.setPassword("123456");
        ayUserService.save(ayUser);
    }

    @Test
    public void testLog4J() {
        ayUserService.delte("4");
        logger.info("delete success!!!!");
    }

    @Test
    public void testMybatis() {
        //AyUser ayUser = ayUserService.findByNameAndPassword("啊兰", "123456");
        AyUser ayUser = ayUserService.findByUserName("啊毅");
        logger.info(ayUser.getId() + ayUser.getName());
    }

    @Test
    public void testAyMood() {
        AyMood ayMood = new AyMood();
        ayMood.setId("1");
        ayMood.setUserId("1");
        ayMood.setPraiseNum(0);
        ayMood.setContent("这是我的第一条微信说说！！！！");
        ayMood.setPublishTime(new Date());
        AyMood mood=ayMoodRepository.save(ayMood);

    }
    @Test
    public  void testMQ(){
        Destination destination=new ActiveMQQueue("ay.queue");
        ayMoodProducer.sendMessage(destination,"hello,mq!!!");
    }
    @Test
    public  void testActiveMQAynSave(){
        AyMood ayMood=new AyMood();
        ayMood.setId("2");
        ayMood.setUserId("2");
        ayMood.setPraiseNum(0);
        ayMood.setContent("这是我的第一条微信说说！！！！");
        ayMood.setPublishTime(new Date());
        String msg=ayMoodService.asynSave(ayMood);
        System.out.println("异步发表说说："+msg);
    }
    @Test
    public  void testAsync(){
        long startTime=System.currentTimeMillis();
        System.out.println("第一次查询所有用户！");
        List<AyUser> ayUserList=ayUserService.findAll();
        System.out.println("第二次查询所有用户！");
        List<AyUser> ayUserList2=ayUserService.findAll();
        System.out.println("第三次查询所有用户！");
        List<AyUser> ayUserList3=ayUserService.findAll();
        long endTime=System.currentTimeMillis();
        System.out.println("总共耗时："+(endTime-startTime)+"毫秒");

    }

    @Test
    public  void testAsync2() throws Exception{
        long startTime=System.currentTimeMillis();
        System.out.println("第一次查询所有用户！");
        Future<List<AyUser>> ayUserList=ayUserService.findAsynAll();
        System.out.println("第二次查询所有用户！");
        Future<List<AyUser>> ayUserList2=ayUserService.findAsynAll();
        System.out.println("第三次查询所有用户！");
        Future<List<AyUser>> ayUserList3=ayUserService.findAsynAll();
        while (true){
            if(ayUserList.isDone() && ayUserList2.isDone() && ayUserList3.isDone()){
                break;
            }else{
                Thread.sleep(10);
            }
        }
        long endTime=System.currentTimeMillis();
        System.out.println("总共耗时："+(endTime-startTime)+"毫秒");
    }
}
