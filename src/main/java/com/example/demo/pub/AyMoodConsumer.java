package com.example.demo.pub;

import com.example.demo.model.AyMood;
import com.example.demo.service.AyMoodService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Decription:消费者（mq）
 * @author wangX
 * @date 2018/7/20 15:52
 *
 */
@Component
public class AyMoodConsumer {
    @Resource
    private AyMoodService ayMoodService;
    @JmsListener(destination = "ay.queue")
    public void receiveQueue(String txt){
        System.out.println("用户发表说说【"+txt+"】成功");
    }
    @JmsListener(destination = "ay.queue.asyn.save")
    public void receiveQueue(AyMood ayMood){
        ayMoodService.save(ayMood);
    }
}
