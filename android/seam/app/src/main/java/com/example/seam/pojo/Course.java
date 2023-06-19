package com.example.seam.pojo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.seam.BR;

import java.util.List;

/**
 * @e-mail:2036573698@qq.com
 */
public class Course extends BaseObservable {
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
        private String courses;
        private int id;

        @Bindable
        public String getCourses() {
            return courses;
        }

        public void setCourses(String courses) {
            this.courses = courses;
            notifyPropertyChanged(BR.courses);
        }

        @Bindable
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
            notifyPropertyChanged(BR.id);
        }
    }
}
