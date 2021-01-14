package com.smart.library.network;

import com.smart.library.BuildConfig;
import com.smart.library.network.converter.GsonConverterFactory;
import com.smart.library.network.converter.NobodyConverterFactory;
import com.smart.library.network.interceptor.HttpLoggingInterceptor2;
import com.smart.library.network.interceptor.NetworkInterceptor;
import com.smart.library.network.interceptor.TokenInterceptor;
import com.smart.library.network.ssl.ApiServer;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 网络框架管理类,在Appaction中初始化
 *
 * @author guobihai
 * @date 2019/03/14
 */
public class RetrofitManager {


    private static RetrofitManager mInstance;
    private static Retrofit retrofit;
    private static Retrofit tokenRetrofit;
    private static Retrofit ossRetrofit;
    private static volatile ApiServer request = null;
    private static  boolean isNeedToken = false;

    private String host;

    public String getHost() {
        return host;
    }

    public static RetrofitManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitManager.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitManager();
                }
            }
        }
        return mInstance;
    }


    /**
     * 初始化必要对象和参数
     */
    public void init(String host) {

        this.host = host;

        // 初始化Retrofit
        retrofit = new Retrofit.Builder()
                .client(getOkhttpClient())
                .baseUrl(host)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(NobodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        configOssClient(host);
        //携带Token的请求
        configTokenClic(host);
    }

    /**
     * 初始化携带token的请求
     * @param host
     */
    private void configTokenClic(String host) {
        tokenRetrofit = new Retrofit.Builder()
                .client(getTOKenOkhttpClient())
                .baseUrl(host)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(NobodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 配置oss上传文件的网络请求
     */
    private void configOssClient(String host) {
        OkHttpClient.Builder ossBuilder = getOkHttpBuilder()
                .connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES);
        ossRetrofit = new Retrofit.Builder()
                .client(ossBuilder.build())
                .baseUrl(host)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(NobodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    public static ApiServer getRequest() {
        if (request == null) {
            synchronized (ApiServer.class) {
                request = retrofit.create(ApiServer.class);
            }
        }
        return request;
    }


    /**
     * 构建指定的API服务
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T create(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }


    public static <T> T createToken(Class<T> serviceClass) {
        return tokenRetrofit.create(serviceClass);
    }

    /**
     * 配置okhttp
     */
    public static OkHttpClient getOkhttpClient() {
        // 初始化okhttp
        return getOkHttpBuilder().build();

    }

    public static OkHttpClient getTOKenOkhttpClient() {
        // 初始化okhttp
        return getTokenOkHttpBuilder().build();

    }

    /**
     * 获取okhttp配置类
     *
     * @return
     */
    public static OkHttpClient.Builder getOkHttpBuilder() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            // set your desired log level
            HttpLoggingInterceptor2 logging = new HttpLoggingInterceptor2();
            logging.setLevel(HttpLoggingInterceptor2.Level.BODY);
            httpClientBuilder.addNetworkInterceptor(logging);
        } else {
            httpClientBuilder.proxy(Proxy.NO_PROXY); // 禁止使用代理网络
        }

        // 配置ssl
//        SSLFactory.SSLParams sslParams = SSLFactory.getSslSocketFactory();
//        httpClientBuilder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);

        // 校验域名
        httpClientBuilder.hostnameVerifier((hostname, session) -> true);

        // 处理无网络异常
        httpClientBuilder.addInterceptor(new NetworkInterceptor());

        return httpClientBuilder;
    }



    public static OkHttpClient.Builder getTokenOkHttpBuilder() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        // 动态token true
        httpClientBuilder.addInterceptor(new TokenInterceptor());


        if (BuildConfig.DEBUG) {
            // set your desired log level
            HttpLoggingInterceptor2 logging = new HttpLoggingInterceptor2();
            logging.setLevel(HttpLoggingInterceptor2.Level.BODY);
            httpClientBuilder.addNetworkInterceptor(logging);
        } else {
            httpClientBuilder.proxy(Proxy.NO_PROXY); // 禁止使用代理网络
        }

        // 校验域名
        httpClientBuilder.hostnameVerifier((hostname, session) -> true);

        // 处理无网络异常
        httpClientBuilder.addInterceptor(new NetworkInterceptor());

        return httpClientBuilder;
    }

}
