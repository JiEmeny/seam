package com.example.seam.respository;

import androidx.lifecycle.MutableLiveData;

import com.example.seam.pojo.Serve;
import com.example.seam.util.retrofit.RetrofitServiceUtil;
import com.example.seam.util.retrofit.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @e-mail:2036573698@qq.com
 */
public class RootHomeRespository {
    // 网络访问工具
    private RetrofitServiceUtil service = RetrofitUtil.retrofitServiceUtil();

    public static final class RootHomeRespositoryHolder {
        private static final RootHomeRespository ROOT_HOME_RESPOSITORY = new RootHomeRespository();
    }

    public static RootHomeRespository getInstance() {
        return RootHomeRespository.RootHomeRespositoryHolder.ROOT_HOME_RESPOSITORY;
    }

    /**
     * 获取全部服务
     *
     * @param serve
     * @param failed
     */
    public void GetServe(MutableLiveData<Serve> serve, MutableLiveData<String> failed) {
        service.GetServe().enqueue(new Callback<Serve>() {
            @Override
            public void onResponse(Call<Serve> call, Response<Serve> response) {
                serve.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Serve> call, Throwable t) {
            }
        });
    }
}
