package com.example.seam.util.retrofit;

import android.app.DownloadManager;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
