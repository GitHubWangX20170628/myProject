package com.example.demo.pub;

import com.example.demo.model.AyMood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @Decription:生产者（mq）
 * @author wangX
 * @date 2018/7/20 15:01
 *
 */
@Service
public class AyMoodProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    public void sendMessage(Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination,message);
    }
    public void sendMessage(Destination destination, AyMood ayMood){
        jmsMessagingTemplate.convertAndSend(destination,ayMood);
    }
}
