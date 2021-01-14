package com.smart.textrunapp.java.dip.impl;

import com.smart.textrunapp.java.dip.interfaces.IPay;

public class WxPay implements IPay {

    private int money;

    public WxPay(int money) {
        this.money = money;
    }

    @Override
    public void pay() {
        System.out.println("=====w微信支付金额======="+money);
    }
}
