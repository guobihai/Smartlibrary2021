package com.smart.library.network.interceptor;



import android.text.TextUtils;

import com.smart.library.account.AccountManager;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * token拦截器，用于请求时添加token请求头，以及保存token响应头
 *
 * @Author guobihai
 * @Created 4/20/19
 * @Editor zrh
 * @Edited 5/11/19
 * @Type
 * @Layout
 * @Api
 */
public class TokenInterceptor implements Interceptor {


    //tokneROM
    private static final String TOKEN = "token";
    private static final String FROM = "from";

    @SuppressWarnings("NullableProblems")
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();

        // 如果本地存在token，则添加token请求头
        if (AccountManager.getToken() != null) {
            requestBuilder.header(TOKEN, AccountManager.getToken());
        }


        if(!TextUtils.isEmpty(AccountManager.getToken())){
            requestBuilder.header(TOKEN,AccountManager.getToken());
            requestBuilder.header(FROM, "android");
        }


        // 如果响应头中存在token则更新本地token
        Response response = chain.proceed(requestBuilder.build());
        Headers headers = response.headers();
        String token = headers.get(TOKEN);
        if (token != null) {
            AccountManager.saveToken(token);
        }
        return response;
    }
}
