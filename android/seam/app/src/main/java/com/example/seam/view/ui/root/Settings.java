package com.example.seam.view.ui.root;

import android.content.Intent;
import android.content.SharedPreferences;

import com.example.assist.base.BaseActivity;
import com.example.assist.base.BaseApp;
import com.example.assist.base.NetworkActivity;
import com.example.seam.WeatherApp;
import com.example.seam.databinding.SettingsBinding;
import com.example.seam.view.ui.Login;

/**
 * 设置
 *
 * @e-mail:2036573698@qq.com
 */
public class Settings extends NetworkActivity<SettingsBinding> {
    @Override
    protected void onCreate() {
    }

    @Override
    protected void onObserveData() {
        binding.toolbar.back.setOnClickListener(v -> {
            finish();
        });
        binding.toolbar.header.setText("设置");
        // 退出软件
        binding.exit.setOnClickListener(v -> {
            BaseActivity.exitTheProgram();
        });
        // 退出登录
        binding.logout.setOnClickListener(v -> {
            // 将sp中储存的数据进行清空，并跳转到登录界面
            SharedPreferences.Editor edit = BaseApp.sp.edit();
            edit.clear();
            edit.commit();
            startActivity(new Intent(Settings.this, Login.class));
        });
        // 关于软件
        binding.about.setOnClickListener(v -> {
            startActivity(new Intent(Settings.this, About.class));
        });
    }
}
