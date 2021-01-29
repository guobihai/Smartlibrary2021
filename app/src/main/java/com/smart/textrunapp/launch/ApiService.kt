package com.smart.textrunapp.launch

import com.smart.textrunapp.launch.bean.BaseRusult
import com.smart.textrunapp.launch.bean.SmartBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("article/listproject/0/json")
      fun queryData(): Call<BaseRusult<SmartBean>>

    @GET("article/listproject/0/json")
    suspend fun queryData1(): BaseRusult<SmartBean>

    @GET("toutiao/index")
    suspend fun queryDataKotlin(@Query("type") type: String?, @Query("key") key: String?): BaseRusult<Any>
}