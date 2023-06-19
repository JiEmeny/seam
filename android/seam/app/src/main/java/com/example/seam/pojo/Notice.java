package com.example.seam.pojo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import java.util.List;

/**
 * @e-mail:2036573698@qq.com
 */
public class Notice extends BaseObservable {
    private int count;
    private String msg;
    private String code;
    private List<RowsBean> rows;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        private int id;
        private int state;
        private String time;
        private int type;
        private String title;
        private int userid;
        private String content;

        @Bindable
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Bindable
        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        @Bindable
        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Bindable
        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Bindable
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Bindable
        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        @Bindable
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
