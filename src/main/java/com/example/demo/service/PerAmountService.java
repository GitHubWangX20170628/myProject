package com.example.demo.service;

import com.example.demo.model.PerMoney;

import java.util.Map;

public interface PerAmountService {
    PerMoney countAmount(Map<String,String> param);
}
