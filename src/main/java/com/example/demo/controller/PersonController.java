package com.example.demo.controller;

import com.example.demo.model.PerMoney;
import com.example.demo.service.PerAmountService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/query")
public class PersonController {
    @Resource
    private PerAmountService perAmountService;
    @RequestMapping("/queryAmount")
    public ModelAndView  queryAmount(ModelAndView mv){
        mv.setViewName("greeting");
        mv.addObject("title","欢迎使用Thymeleaf!");
        return mv;
    }
    @RequestMapping("/count")
    public PerMoney countAmount(String base, String time){
        System.out.println(base);
        Map<String,String>param =new HashMap<>();
        param.put("base",base);
        param.put("time",time);
        PerMoney result =perAmountService.countAmount(param);
        return result;
    }
}
