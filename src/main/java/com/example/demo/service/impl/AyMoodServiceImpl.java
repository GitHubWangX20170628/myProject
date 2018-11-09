package com.example.demo.service.impl;

import com.example.demo.model.AyMood;
import com.example.demo.pub.AyMoodProducer;
import com.example.demo.repository.AyMoodRepository;
import com.example.demo.service.AyMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author wangX
 * @Decription:微信说说服务层
 * @date 2018/7/20 14:49
 */
@Service
public class AyMoodServiceImpl implements AyMoodService {
    //队列
    private static Destination destination = new ActiveMQQueue("ay.queue.asyn.save");
    @Resource
    private AyMoodProducer ayMoodProducer;
    @Resource
    private AyMoodRepository ayMoodRepository;

    @Override
    public AyMood save(AyMood ayMood) {
        return ayMoodRepository.save(ayMood);
    }

    @Override
    public String asynSave(AyMood ayMood) {
        //往队列ay.queue.asyn.save推送消息，消息内容为说说实体
        ayMoodProducer.sendMessage(destination, ayMood);
        return "success";
    }
}
