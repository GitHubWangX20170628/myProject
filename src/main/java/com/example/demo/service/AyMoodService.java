package com.example.demo.service;

import com.example.demo.model.AyMood;

/**
 * @author wangX
 * @Decription:微信说说服务层
 * @date 2018/7/20 14:46
 */
public interface AyMoodService {
    AyMood save(AyMood ayMood);

    String asynSave(AyMood ayMood);
}
