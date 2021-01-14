package com.smart.textrunapp.api;

import com.smart.library.entity.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public
interface CenterApi {
    /**
     * 退出
     * @return
     */
    @POST("/user/detail/logOut")
    Observable<BaseResponse<Boolean>> logOut(@Body Map map);


    /**
     * 退出
     * @return
     */
    @POST("/user/detail/logOut")
    BaseResponse<Boolean> logOut1(@Body Map map);
}
