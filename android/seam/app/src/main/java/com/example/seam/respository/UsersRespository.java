package com.example.seam.respository;

import androidx.lifecycle.MutableLiveData;

import com.example.seam.pojo.Files;
import com.example.seam.pojo.PassWord;
import com.example.seam.pojo.SetFeedBack;
import com.example.seam.pojo.User;
import com.example.seam.pojo.Users;
import com.example.seam.util.retrofit.RetrofitServiceUtil;
import com.example.seam.util.retrofit.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @e-mail:2036573698@qq.com
 */
public class UsersRespository {
    // 网络访问工具
    private RetrofitServiceUtil service = RetrofitUtil.retrofitServiceUtil();

    private static final class UsersRespositoryHolder {
        private static final UsersRespository USERS_RESPOSITORY = new UsersRespository();
    }

    public static UsersRespository getInstance() {
        return UsersRespository.UsersRespositoryHolder.USERS_RESPOSITORY;
    }

    /**
     * 上传文件
     *
     * @param files
     * @param failed
     * @param part
     */
    public void UpLoad(MutableLiveData<Files> files, MutableLiveData<String> failed, MultipartBody.Part part) {
        service.UpLoad(part).enqueue(new Callback<Files>() {
            @Override
            public void onResponse(Call<Files> call, Response<Files> response) {
                files.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Files> call, Throwable t) {
            }
        });
    }

    /**
     * 修改个人信息
     *
     * @param users
     * @param failed
     * @param id
     * @param avatar
     * @param name
     * @param sex
     * @param phone
     * @param address
     * @param idcard
     */
    public void ModifyMsg(MutableLiveData<Users> users, MutableLiveData<String> failed, String id, String avatar, String name, String sex, String phone, String address, String idcard) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        if (avatar != "") {
            map.put("avatar", avatar);
        }
        if (name != "") {
            map.put("name", name);
        }
        if (sex != "") {
            map.put("sex", sex);
        }
        if (phone != "") {
            map.put("phone", phone);
        }
        if (address != "") {
            map.put("address", address);
        }
        if (idcard != "") {
            map.put("idcard", idcard);
        }
        service.ModifyMsg(map).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                users.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
            }
        });
    }

    /**
     * 提交反馈意见
     *
     * @param setfeedback
     * @param failed
     * @param title
     * @param content
     */
    public void setFeedBack(MutableLiveData<SetFeedBack> setfeedback, MutableLiveData<String> failed, String title, String content) {
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("content", content);
        service.SetFeedBack(map).enqueue(new Callback<SetFeedBack>() {
            @Override
            public void onResponse(Call<SetFeedBack> call, Response<SetFeedBack> response) {
                setfeedback.postValue(response.body());
            }

            @Override
            public void onFailure(Call<SetFeedBack> call, Throwable t) {
            }
        });
    }

    /**
     * 修改密码
     *
     * @param passWord
     * @param failed
     * @param oldpassword
     * @param newpassword
     */
    public void setUserByPassword(MutableLiveData<PassWord> passWord, MutableLiveData<String> failed, String oldpassword, String newpassword) {
        Map<String, String> map = new HashMap<>();
        map.put("newpassword", newpassword);
        map.put("oldpassword", oldpassword);
        service.ChangePassword(map).enqueue(new Callback<PassWord>() {
            @Override
            public void onResponse(Call<PassWord> PassWord, Response<PassWord> response) {
                passWord.postValue(response.body());
            }

            @Override
            public void onFailure(Call<PassWord> call, Throwable t) {
            }
        });
    }

    /**
     * 通过username查询user
     *
     * @param user
     * @param failed
     * @param username
     */
    public void setUserByUsername(MutableLiveData<User> user, MutableLiveData<String> failed, String username) {
        service.GetUserUsername(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user.postValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    /**
     * 通过id查询users
     *
     * @param users
     * @param failed
     * @param id
     */
    public void setUsersBuId(MutableLiveData<Users> users, MutableLiveData<String> failed, String id) {
        service.GetUsers(id).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                users.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
            }
        });
    }
}
