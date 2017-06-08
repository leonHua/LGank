package com.leon.lgank.net;

import com.leon.lgank.common.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 作者：Leon
 * 时间：2017/6/8
 * 描述: 网络请求管理器
 */
public class HttpManager {
    private static HttpManager httpManager;

    private HttpManager() {
    }

    public static HttpManager getInstance() {
        if (httpManager == null) {
            httpManager = new HttpManager();
        }
        return httpManager;
    }

    /**
     * 获取对应的接口服务
     *
     * @return Api
     */
    public Api getApiService() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        return api;
    }
}
