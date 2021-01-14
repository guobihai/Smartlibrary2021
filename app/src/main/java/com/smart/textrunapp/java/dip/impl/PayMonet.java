package com.smart.textrunapp.java.dip.impl;

import com.smart.textrunapp.java.dip.interfaces.IPay;
import com.smart.textrunapp.java.dip.interfaces.PayModel;

/**
 * @author guobihai
 * 创建日期：2020/12/14
 * desc：依赖倒置设计原则，
 * 高层模块不应该依赖低层模块，两者都应该依赖其抽象；抽象不应该依赖细节，细节应该依赖抽象
 */
public class PayMonet implements PayModel {
    @Override
    public void payMoney(IPay iPay) {
        iPay.pay();
    }
}
