package com.smart.textrunapp.java.thread;

/**
 * @author guobihai
 * 创建日期：2020/12/18
 * desc：线程测试
 */
public class ThreadDemo1 {

    public void mainRun() {
        Thread2 thread2 = new Thread2();
        thread2.start();
        new Thread(new Thread1(thread2)).start();
    }


    class Thread1 implements Runnable {
        Thread2 thread2;

        public Thread1(Thread2 thread2) {
            System.out.println("========thread1====");
            this.thread2 = thread2;
        }

        @Override
        public void run() {
            System.out.println("========thread1==is run==" );
            try {
                //在线程A中调用线程B的join()方法，相当于让线程B直接插入线程A方法中运行（线程A阻塞），直至线程B结束，继续线程A；
                if (null != thread2) {
                    thread2.join();
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("========thread1==is run=complete=");
        }
    }

    class Thread2 extends Thread {

        public Thread2() {
            System.out.println("========thread2====");
        }

        @Override
        public void run() {
            System.out.println("========thread2==is run==" + Thread.currentThread());
            try {
                Thread.yield();
                Thread.sleep(1000);
                System.out.println("========thread2==is run=complete=");
            } catch (InterruptedException e) {
                System.out.println("========thread2==is run=complete=");
            }
        }
    }
}
