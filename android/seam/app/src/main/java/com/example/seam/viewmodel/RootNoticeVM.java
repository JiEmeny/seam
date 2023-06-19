package com.example.seam.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.assist.base.BaseViewModel;
import com.example.seam.pojo.Notice;
import com.example.seam.respository.RootNoticeRespository;

/**
 * @e-mail:2036573698@qq.com
 */
public class RootNoticeVM extends BaseViewModel {
    public MutableLiveData<Notice> noticeMutableLiveData = new MutableLiveData<>();

    /**
     * 根据id删除通知
     *
     * @param id
     */
    public void DelNotice(Integer id) {
        RootNoticeRespository.getInstance().DelNotice(noticeMutableLiveData, failed, id);
    }

    /**
     * 添加通知
     *
     * @param state   查看状态
     * @param title   标题
     * @param content 内容
     * @param userid  发布人用户id
     * @param type    发布类型
     */
    public void AddNotice(int state, String title, String content, int userid, int type) {
        RootNoticeRespository.getInstance().AddNotice(noticeMutableLiveData, failed, state, title, content, userid, type);
    }

    /**
     * 通过id查询通知
     */
    public void My(Integer id) {
        RootNoticeRespository.getInstance().My(noticeMutableLiveData, failed, id);
    }

    /**
     * 查询全部通知
     */
    public void My() {
        RootNoticeRespository.getInstance().My(noticeMutableLiveData, failed);
    }

    /**
     * 通过id修改查看状态
     *
     * @param id
     * @param state
     */
    public void ModifySeeType(Integer id, Integer state) {
        RootNoticeRespository.getInstance().ModifySeeType(noticeMutableLiveData, failed, id, state);
    }
}
