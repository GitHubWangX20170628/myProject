package com.example.demo.mail;

import com.example.demo.model.AyUser;

import java.util.List;

/**
 * @Decription: 发送用户邮件服务
 * @author wangX
 * @date 2018/7/20 9:06
 *
 */
public interface SendJunkMailService {
    boolean sendJunMail(List<AyUser> ayUser);
}
