package com.example.seam.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.assist.base.BaseViewModel;
import com.example.seam.pojo.Files;
import com.example.seam.pojo.PassWord;
import com.example.seam.pojo.SetFeedBack;
import com.example.seam.pojo.User;
import com.example.seam.pojo.Users;
import com.example.seam.respository.UsersRespository;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @e-mail:2036573698@qq.com
 */
public class Root_Personal_VM extends BaseViewModel {
    public MutableLiveData<Users> usersMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<PassWord> passWordMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<SetFeedBack> setFeedBackMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Files> filesMutableLiveData = new MutableLiveData<>();

    /**
     * 上传文件
     *
     * @param pictPath（文件路径）
     * @param fileName（文件名）
     */
    public void UpLoad(String pictPath, String fileName) {
        // 文件路径
        File file = new File(pictPath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", fileName, requestBody);
        UsersRespository.getInstance().UpLoad(filesMutableLiveData, failed, part);
    }

    /**
     * 修个个人信息
     *
     * @param id
     * @param avatar
     * @param name
     * @param sex
     * @param phone
     * @param address
     * @param idcard
     */
    public void ModifyMsg(String id, String avatar, String name, String sex, String phone, String address, String idcard) {
        UsersRespository.getInstance().ModifyMsg(usersMutableLiveData, failed, id, avatar, name, sex, phone, address, idcard);
    }

    /**
     * 提交反馈意见
     *
     * @param title
     * @param content
     */
    public void setFeedBack(String title, String content) {
        UsersRespository.getInstance().setFeedBack(setFeedBackMutableLiveData, failed, title, content);
    }

    /**
     * 通过username查询user
     *
     * @param username
     */
    public void setUserByUsername(String username) {
        UsersRespository.getInstance().setUserByUsername(userMutableLiveData, failed, username);
    }

    /**
     * 通过id查询users
     *
     * @param id
     */
    public void setUsersBuId(String id) {
        UsersRespository.getInstance().setUsersBuId(usersMutableLiveData, failed, id);
    }

    /**
     * 修改密码
     *
     * @param oldpassword
     * @param newpassword
     */
    public void setUserByPassword(String oldpassword, String newpassword) {
        UsersRespository.getInstance().setUserByPassword(passWordMutableLiveData, failed, oldpassword, newpassword);
    }
}
