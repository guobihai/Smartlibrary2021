package com.smart.library.network.utils;


import com.smart.library.entity.BaseResponse;
import com.smart.library.exception.NoDataException;
import com.smart.library.network.execption.ApiException;
import com.smart.library.observer.GlobalTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author guobihai
 * 创建日期：2020/12/14
 * desc：
 *
 */
public class RxUtils {
    /**
     * 线程调度器
     */
    public static <T> GlobalTransformer<T> getWrapper() {
        return new GlobalTransformer<T>();
    }

    /**
     * 处理请求结果，切换线程
     */
    public static <B extends BaseResponse<T>, T extends Object> ObservableTransformer<B, T> responseTransformer() {
        return upstream -> upstream.flatMap(new ResponseFunction<>());
    }

    private static class ResponseFunction<T> implements Function<BaseResponse<T>, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(BaseResponse<T> response) throws Exception {

            if (response == null) {
                return Observable.error(new ApiException(500, "服务器未返回响应数据"));
            }


            if (response.getStatus().equals("200")) {

                if (response.getData() == null) {
                    return Observable.error(new NoDataException());
                }

                return Observable.just(response.getData());
            } else {
                return Observable.error(new ApiException(Integer.parseInt(response.getStatus()), response.getMsg()));
            }
        }
    }
}
