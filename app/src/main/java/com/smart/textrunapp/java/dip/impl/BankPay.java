package com.smart.textrunapp.java.dip.impl;

import com.smart.textrunapp.java.dip.interfaces.IPay;

public
class BankPay implements IPay {
    private int money;

    public BankPay(int money) {
        this.money = money;
    }

    @Override
    public void pay() {
        System.out.println("========银行卡付款======"+money);
    }
}
