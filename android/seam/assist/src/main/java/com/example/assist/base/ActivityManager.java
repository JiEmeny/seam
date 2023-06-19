package com.example.assist.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类
 *
 * @e-mail:2036573698@qq.com
 */
public class ActivityManager {
    // 保存所有创建的Activity
    private final List<Activity> allActivities = new ArrayList<>();

    /**
     * 添加Activity到管理器
     */
    public void addActivity(Activity activity) {
        if (activity != null) {
            allActivities.add(activity);
        }
    }

    /**
     * 关闭所有Activity
     */
    public void finishAll() {
        for (Activity activity : allActivities) {
            activity.finish();
        }
    }
}
