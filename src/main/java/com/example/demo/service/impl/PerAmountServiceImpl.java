package com.example.demo.service.impl;

import com.example.demo.model.PerMoney;
import com.example.demo.service.PerAmountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
@Service
public class PerAmountServiceImpl implements PerAmountService {
    @Override
    public PerMoney countAmount(Map<String, String> param) {
        BigDecimal base=new BigDecimal(param.get("base"));
        BigDecimal addFee=new BigDecimal(param.get("time"));

        double wdxy=6200*0.08*0.85;
        double jbf=(6200/21.75/8)*addFee.doubleValue();
        double gjj=1860*0.05+8;
        double yl=3279*0.08;
        double ylb=3279*0.02;
        double sy=3279*0.005;
        //double fg=gjj+yl+ylb+sy;
        double fg=101+yl+ylb+sy;
        double basefront=base.doubleValue()+wdxy+150+jbf;
        double personM=(base.doubleValue()+wdxy+150+jbf-fg-5000)*0.03;
        double lastM=basefront-fg-personM;
        double second=base.doubleValue()*0.15;
        double first=lastM-second;
        System.out.println("jcgz:"+base.toString());
        System.out.println("wdxy:"+wdxy);
        System.out.println("jbf:"+jbf);
        System.out.println("餐补:"+150);
        System.out.println("五险一金:"+fg);
        System.out.println("个人税："+personM);
        System.out.println("请假："+0);
        System.out.println("税前："+basefront);
        System.out.println("到手："+lastM);
        System.out.println("first:"+first);
        System.out.println("second:"+second);
        PerMoney result =new PerMoney();
        result.setSumAmout(String.valueOf(basefront));
        result.setGetFirstAmount(String.valueOf(first));
        result.setGetSecondAmout(String.valueOf(second));
        result.setNeedOutAmout(String.valueOf(fg+personM));
        return result;
    }

    public static void main(String[] args) {
        Map<String,String>paam=new HashMap<>();
        paam.put("base","7200");
        paam.put("time","49.86");
        PerAmountService service=new PerAmountServiceImpl();
        PerMoney perMoney=service.countAmount(paam);
    }
}
