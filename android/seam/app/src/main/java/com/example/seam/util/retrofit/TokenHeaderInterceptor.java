package com.example.seam.util.retrofit;

import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加token
 */
public class TokenHeaderInterceptor implements Interceptor {
    public static String AUTHORITY = "Authorization";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String token = App.sp.getString("token", "");
        if (token != null) {
            builder.addHeader("token", token);
        }
        return chain.proceed(builder.build());
    }
}
