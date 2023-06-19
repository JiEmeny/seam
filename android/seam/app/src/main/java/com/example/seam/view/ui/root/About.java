package com.example.seam.view.ui.root;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.assist.base.NetworkActivity;
import com.example.assist.network.interceptor.RequestInterceptor;
import com.example.seam.databinding.AboutBinding;

/**
 * 关于（软件名称、版本）
 *
 * @e-mail:2036573698@qq.com
 */
public class About extends NetworkActivity<AboutBinding> {
    @Override
    protected void onCreate() {
    }

    @Override
    protected void onObserveData() {
        binding.toolbar.back.setOnClickListener(v -> {
            finish();
        });
        binding.toolbar.header.setText("关于");
        binding.name.setText("软件名称：" + packageName(About.this));
        binding.code.setText("版本号：" + packageCode(About.this));
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return code（版本号）
     */
    public static int packageCode(Context context) {
        PackageManager manager = context.getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 获取版本名称
     *
     * @param context
     * @return name（版本名称）
     */
    public static String packageName(Context context) {
        PackageManager manager = context.getPackageManager();
        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }
}
