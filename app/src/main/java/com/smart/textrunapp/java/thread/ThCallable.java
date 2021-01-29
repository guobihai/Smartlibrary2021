package com.smart.textrunapp.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author guobihai
 * 创建日期：2020/12/14
 * desc：带返回值的线程执行结果
 */
public class ThCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        System.out.println("=====ThCallable====");
        return "hello callable";
    }
}
