package com.example.seam.util.retrofit;

import com.example.assist.base.BaseApp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 在网络访问中添加token
 *
 * @e-mail:2036573698@qq.com
 */
public class TokenHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String token = BaseApp.sp.getString("token", "");
        if (token != null) {
            builder.addHeader("token", token);
        }
        return chain.proceed(builder.build());
    }
}
