package com.example.seam.util.retrofit;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络访问工具类
 *
 * @e-mail:2036573698@qq.com
 */
public class RetrofitUtil {
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new TokenHeaderInterceptor())
            .build();

    public static RetrofitServiceUtil retrofitServiceUtil() {
        RetrofitServiceUtil util = new Retrofit.Builder()
                .baseUrl("http://10.212.25.100:8080/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .client(client)
                .build()
                .create(RetrofitServiceUtil.class);
        return util;
    }
}
