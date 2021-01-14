package com.smart.textrunapp.java.factory

/**
 * @author guobihai
 * createDate：2020/12/23 11:20
 * desc：抽象工厂方法
 *
 */
abstract class BMWFactory {
    abstract fun buildBMW(): BMW
}


class BMW730 : BMW() {
    override fun run() {
        println(" run 730 bmw")
    }

}


class BMW830 : BMW() {
    override fun run() {
        println(" run 830 bmw")
    }

}

class BMW730Factory : BMWFactory() {

    override fun buildBMW(): BMW {
        println(" build 730 bmw")
        return BMW730()
    }

}

class BMW830Factory : BMWFactory() {
    override fun buildBMW(): BMW {
        println("build 830")
        return BMW830()
    }

}


class BuildBMW() {
    fun build() {
        val bmw730 = BMW730Factory().buildBMW()
        val bmw830 =  BMW830Factory().buildBMW()
        bmw730.run()
        bmw830.run()
    }
}