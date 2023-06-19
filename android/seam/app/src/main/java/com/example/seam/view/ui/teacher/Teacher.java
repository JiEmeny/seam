package com.example.seam.view.ui.teacher;

import android.view.KeyEvent;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.assist.base.NetworkActivity;
import com.example.seam.R;
import com.example.seam.WeatherApp;
import com.example.seam.databinding.TeacherBinding;

/**
 * Teacher主界面
 *
 * @e-mail:2036573698@qq.com
 */
public class Teacher extends NetworkActivity<TeacherBinding> {
    // 获取NavController
    private NavController navController;

    @Override
    protected void onCreate() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    @Override
    protected void onObserveData() {
        // NavigationUI通过setupWithNavController将底部导航栏和导航控制器进行绑定
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
        binding.bottomNavigation.setItemActiveIndicatorEnabled(false);
    }

    /**
     * 退出程序
     *
     * @param keyCode The value in event.getKeyCode().
     * @param event   Description of the key event.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long timeMillis = 0;
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - timeMillis) > 2000) {
                Toast.makeText(mContext, "再一次将退出软件", Toast.LENGTH_SHORT).show();
                timeMillis = System.currentTimeMillis();
            } else {
                WeatherApp.getActivityManager().finishAll();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
