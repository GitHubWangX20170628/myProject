package com.example.demo.controller;

import com.example.demo.error.BusinessException;
import com.example.demo.model.AyUser;
import com.example.demo.service.AyUserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ayUser")
public class AyUserController {
    @Resource
    private AyUserService ayUserService;
    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<AyUser>ayUsers=ayUserService.findAll();
        model.addAttribute("users",ayUsers);
        throw  new BusinessException("业务异常");
    }
    @RequestMapping("/test")
    public String test(Model model) {
        //查询数据库所有用户
        List<AyUser> ayUser = ayUserService.findAll();
        model.addAttribute("users", ayUser);
        return "ayUser";

    }
    @RequestMapping("/findByNameAndPassworRestry")
    public String findByNameAndPassworRetry(Model model){
        AyUser ayUser=ayUserService.findByNameAndPassworRetry("啊毅","123456");
        model.addAttribute("users",ayUser);
        return "success";
    }


}
