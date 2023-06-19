package com.example.assist.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @e-mail:2036573698@qq.com
 */
public class BaseViewModel extends ViewModel {
    public MutableLiveData<String> failed = new MutableLiveData<>();
}
