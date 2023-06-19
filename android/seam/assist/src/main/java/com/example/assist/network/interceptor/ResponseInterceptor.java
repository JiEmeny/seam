package com.example.assist.network.interceptor;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @Description: 响应拦截器
 * @e-mail:2036573698@qq.com
 */
public class ResponseInterceptor implements Interceptor {
    private static final String TAG = ResponseInterceptor.class.getSimpleName();

    /**
     * 拦截
     */
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        long requestTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        Log.i(TAG, "requestSpendTime=" + (System.currentTimeMillis() - requestTime));
        return response;
    }
}
