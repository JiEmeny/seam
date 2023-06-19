package com.example.assist.base;

import androidx.viewbinding.ViewBinding;

/**
 * @e-mail:2036573698@qq.com
 */
public abstract class NetworkActivity<VB extends ViewBinding> extends BaseVBActivity<VB> {
    @Override
    protected void initData() {
        onCreate();
        onObserveData();
    }

    protected abstract void onCreate();

    protected abstract void onObserveData();
}
