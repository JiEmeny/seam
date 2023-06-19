package com.example.assist.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

/**
 * Application
 *
 * @e-mail:2036573698@qq.com
 */
public class BaseApp extends Application {
    private static ActivityManager activityManager;
    private static BaseApp app;
    private static Context context;
    public static SharedPreferences sp;
    public static String uri = "http://10.212.25.100:8080";

    @Override
    public void onCreate() {
        super.onCreate();
        //声明Activity管理
        activityManager = new ActivityManager();
        context = getApplicationContext();
        app = this;
        sp = getSharedPreferences("token", MODE_PRIVATE);
    }

    public static ActivityManager getActivityManager() {
        return activityManager;
    }

    //内容提供器
    public static Context getContext() {
        return context;
    }

    public static BaseApp getApp() {
        return app;
    }

    /**
     * 设置系统状态栏字体颜色为黑色
     *
     * @param activity
     */
    public static void StatusBarTextColor(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
