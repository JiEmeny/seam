package com.example.seam.util.retrofit;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.seam.view.ui.Login;

import okhttp3.Interceptor;

public class App extends Application {
    public static SharedPreferences sp;
    public static String uri = "http://10.212.25.100:8080";

    @Override
    public void onCreate() {
        super.onCreate();
        sp = getSharedPreferences(TokenHeaderInterceptor.AUTHORITY, MODE_PRIVATE);
        sp = getSharedPreferences(Login.AUTHORITY, MODE_PRIVATE);
    }
}
