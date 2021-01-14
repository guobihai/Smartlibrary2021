package com.smart.textrunapp


import com.smart.textrunapp.java.annotation.BankService
import com.smart.textrunapp.java.build.CarBuilder
import com.smart.textrunapp.java.dip.impl.BankPay
import com.smart.textrunapp.java.dip.impl.PayMonet
import com.smart.textrunapp.java.dip.impl.WxPay
import com.smart.textrunapp.java.dip.impl.ZfbPay
import com.smart.textrunapp.java.factory.BuildBMW
import com.smart.textrunapp.java.thread.ThCallable
import com.smart.textrunapp.java.thread.ThreadDemo1
import com.smart.textrunapp.utils.Text
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.FutureTask

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun textAdd() {
        Text.textSub()
    }


    @Test
    fun textDIP() {
        val pay = PayMonet()
        pay.payMoney(WxPay(100))
        pay.payMoney(ZfbPay(30))
        pay.payMoney(BankPay(60))
    }

    @Test
    fun threadText() {
        val futureTask = FutureTask<String>(ThCallable())
        Thread(futureTask).start()
        print("====结果：==${futureTask.get()}")
    }

    @Test
    fun textAnnitation() {
        BankService.TransferMoney(16000.0)
    }

    @Test
    fun textThread() {
        val th = ThreadDemo1()
        th.mainRun()
    }


    @Test
    fun builder() {
        val car = CarBuilder()
            .addBody("复合材料")
            .addChassis("镁合金")
            .addEngin("v12")
            .build()
        print(car.body)
        print(car.engin)
        print(car.chassis)
    }

    @Test
    fun buildFactory(){
       val  buildBMW = BuildBMW()
        buildBMW.build()
    }
}