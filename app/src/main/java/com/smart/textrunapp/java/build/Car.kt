package com.smart.textrunapp.java.build

/**
 * @author guobihai
 * createDate：2020/12/23 10:58
 * desc：建造者模式
 *
 */
class Car constructor(var engin: String, var chassis: String, var body: String) {
}

class CarBuilder {
    lateinit var engin: String
    lateinit var chassis: String
    lateinit var body: String

    fun addEngin(engin: String): CarBuilder {
        this.engin = engin
        return this
    }

    fun addChassis(chassis: String): CarBuilder {
        this.chassis = chassis
        return this
    }

    fun addBody(body: String): CarBuilder {
        this.body = body
        return this
    }

    fun build(): Car {
        return Car(this.engin, this.chassis, this.body)
    }
}



