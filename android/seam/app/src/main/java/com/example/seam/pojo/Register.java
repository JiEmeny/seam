package com.example.seam.pojo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.chad.library.BR;

import java.util.List;

/**
 * @e-mail:2036573698@qq.com
 */
public class Register extends BaseObservable {
    private int count;
    private String msg;
    private String code;
    private List<RowsBean> rows;

    @Bindable
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        notifyPropertyChanged(BR.count);
    }

    @Bindable
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyPropertyChanged(BR.msg);
    }

    @Bindable
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        notifyPropertyChanged(BR.code);
    }

    @Bindable
    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
        notifyPropertyChanged(BR.rows);
    }

    public static class RowsBean extends BaseObservable {
        private int signed;
        private int id;
        private int curriculummanagementid;
        private int userid;

        @Bindable
        public int getSigned() {
            return signed;
        }

        public void setSigned(int signed) {
            this.signed = signed;
            notifyPropertyChanged(BR.signed);
        }

        @Bindable
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
            notifyPropertyChanged(BR.id);
        }

        @Bindable
        public int getCurriculummanagementid() {
            return curriculummanagementid;
        }

        public void setCurriculummanagementid(int curriculummanagementid) {
            this.curriculummanagementid = curriculummanagementid;
            notifyPropertyChanged(BR.curriculummanagementid);
        }

        @Bindable
        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
            notifyPropertyChanged(BR.userid);
        }
    }
}
