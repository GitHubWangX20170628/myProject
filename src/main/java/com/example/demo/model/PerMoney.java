package com.example.demo.model;

import javax.persistence.Entity;

public class PerMoney {
    private String sumAmout;
    private String getFirstAmount;
    private String getSecondAmout;

    public String getNeedOutAmout() {
        return needOutAmout;
    }

    public void setNeedOutAmout(String needOutAmout) {
        this.needOutAmout = needOutAmout;
    }

    private String needOutAmout;

    public String getSumAmout() {
        return sumAmout;
    }

    public void setSumAmout(String sumAmout) {
        this.sumAmout = sumAmout;
    }

    public String getGetFirstAmount() {
        return getFirstAmount;
    }

    public void setGetFirstAmount(String getFirstAmount) {
        this.getFirstAmount = getFirstAmount;
    }

    public String getGetSecondAmout() {
        return getSecondAmout;
    }

    public void setGetSecondAmout(String getSecondAmout) {
        this.getSecondAmout = getSecondAmout;
    }
}
