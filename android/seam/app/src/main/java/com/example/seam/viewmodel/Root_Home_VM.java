package com.example.seam.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.assist.base.BaseViewModel;
import com.example.seam.pojo.Serve;
import com.example.seam.respository.RootHomeRespository;

/**
 * @e-mail:2036573698@qq.com
 */
public class Root_Home_VM extends BaseViewModel {
    public MutableLiveData<Serve> serveMutableLiveData = new MutableLiveData<>();

    /**
     * 获取全部服务
     */
    public void GetServe() {
        RootHomeRespository.getInstance().GetServe(serveMutableLiveData, failed);
    }
}
