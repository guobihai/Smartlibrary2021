package com.smart.library.network.interceptor;


import com.smart.library.exception.NoNetworkException;
import com.smart.library.utils.AppContext;
import com.smart.library.utils.NetworkMonitor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @Author guobihai
 * @Created 4/22/19
 * @Editor zrh
 * @Edited 4/22/19
 * @Type
 * @Layout
 * @Api
 */
public class NetworkInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        boolean connected = NetworkMonitor.isContected(AppContext.getAppContext());
        if (connected) {
            return chain.proceed(chain.request());
        } else {
            throw new NoNetworkException();
        }
    }
}
