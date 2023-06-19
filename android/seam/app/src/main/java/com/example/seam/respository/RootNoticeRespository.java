package com.example.seam.respository;

import androidx.lifecycle.MutableLiveData;

import com.example.seam.pojo.Notice;
import com.example.seam.util.retrofit.RetrofitServiceUtil;
import com.example.seam.util.retrofit.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @e-mail:2036573698@qq.com
 */
public class RootNoticeRespository {
    // 网络访问工具
    private RetrofitServiceUtil service = RetrofitUtil.retrofitServiceUtil();

    private static final class RootNoticeRespositoryHolder {
        private static final RootNoticeRespository ROOT_NOTICE_RESPOSITORY = new RootNoticeRespository();
    }

    public static RootNoticeRespository getInstance() {
        return RootNoticeRespository.RootNoticeRespositoryHolder.ROOT_NOTICE_RESPOSITORY;
    }

    /**
     * 根据id删除通知
     *
     * @param notice
     * @param failed
     * @param id
     */
    public void DelNotice(MutableLiveData<Notice> notice, MutableLiveData<String> failed, Integer id) {
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        service.DelNotice(map).enqueue(new Callback<Notice>() {
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                notice.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
            }
        });
    }

    /**
     * 添加通知
     *
     * @param notice
     * @param failed
     * @param state
     * @param title
     * @param content
     * @param userid
     * @param type
     */
    public void AddNotice(MutableLiveData<Notice> notice, MutableLiveData<String> failed, int state, String title, String content, int userid, int type) {
        Map<String, String> map = new HashMap<>();
        map.put("state", String.valueOf(state));
        map.put("title", title);
        map.put("content", content);
        map.put("userid", String.valueOf(userid));
        map.put("type", String.valueOf(type));
        service.AddNotice(map).enqueue(new Callback<Notice>() {
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                notice.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
            }
        });
    }

    /**
     * 根据id修改通知查看状态
     *
     * @param notice
     * @param failed
     * @param id
     * @param state
     */
    public void ModifySeeType(MutableLiveData<Notice> notice, MutableLiveData<String> failed, Integer id, Integer state) {
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        map.put("state", state);
        service.ModifySeeType(map).enqueue(new Callback<Notice>() {
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                notice.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
            }
        });
    }

    /**
     * 查询全部通知
     *
     * @param notice
     * @param failed
     */
    public void My(MutableLiveData<Notice> notice, MutableLiveData<String> failed) {
        service.My().enqueue(new Callback<Notice>() {
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                notice.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
            }
        });
    }

    /**
     * 通过id查询通知
     *
     * @param notice
     * @param failed
     * @param id
     */
    public void My(MutableLiveData<Notice> notice, MutableLiveData<String> failed, Integer id) {
        service.My(id).enqueue(new Callback<Notice>() {
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                notice.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
            }
        });
    }
}
