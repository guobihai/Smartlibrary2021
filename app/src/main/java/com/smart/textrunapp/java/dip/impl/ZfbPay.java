package com.smart.textrunapp.java.dip.impl;

import com.smart.textrunapp.java.dip.interfaces.IPay;

public class ZfbPay implements IPay {

    private int money;

    public ZfbPay(int money) {
        this.money = money;
    }

    @Override
    public void pay() {
        System.out.println("======支付宝支付======" + money);

    }
}
