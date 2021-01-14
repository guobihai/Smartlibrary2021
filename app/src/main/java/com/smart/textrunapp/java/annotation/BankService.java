package com.smart.textrunapp.java.annotation;

import java.lang.reflect.Method;

/**
 * @author guobihai
 * 创建日期：2020/12/17
 * desc：注解例子
 */
public class BankService {

    @BankTransferMoney(maxMoney = 18000)
    public static void TransferMoney(double money) {
        System.out.println(processAnnotationMoney(money));
    }

    private static String processAnnotationMoney(double money) {
        try {
            Method tranferMoney = BankService.class.getDeclaredMethod("TransferMoney", double.class);
            boolean annotationPresent = tranferMoney.isAnnotationPresent(BankTransferMoney.class);
            if (annotationPresent) {
                BankTransferMoney annotation = tranferMoney.getAnnotation(BankTransferMoney.class);
                double lmoney = annotation.maxMoney();
                if (money > lmoney) {
                    return "转账金额大于限额，转账失败";
                } else {
                    return "转账金额为：" + money + " 转账成功";
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return "转账失败";
    }

}
