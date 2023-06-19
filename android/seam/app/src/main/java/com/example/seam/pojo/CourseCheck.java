package com.example.seam.pojo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.chad.library.BR;

import java.util.List;

/**
 * @e-mail:2036573698@qq.com
 */
public class CourseCheck extends BaseObservable {
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
        notifyPropertyChanged(BR.msg);
        this.msg = msg;
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
        private int gradeid;
        private String weeks;
        private int ms;
        private int course;
        private int classroomid;
        private int id;
        private int userid;

        @Bindable
        public int getGradeid() {
            return gradeid;
        }

        public void setGradeid(int gradeid) {
            this.gradeid = gradeid;
            notifyPropertyChanged(BR.gradeid);
        }

        @Bindable
        public String getWeeks() {
            return weeks;
        }

        public void setWeeks(String weeks) {
            this.weeks = weeks;
            notifyPropertyChanged(BR.weeks);
        }

        @Bindable
        public int getMs() {
            return ms;
        }

        public void setMs(int ms) {
            this.ms = ms;
            notifyPropertyChanged(BR.ms);
        }

        @Bindable
        public int getCourse() {
            return course;
        }

        public void setCourse(int course) {
            this.course = course;
            notifyPropertyChanged(BR.course);
        }

        @Bindable
        public int getClassroomid() {
            return classroomid;
        }

        public void setClassroomid(int classroomid) {
            this.classroomid = classroomid;
            notifyPropertyChanged(BR.classroomid);
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
        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
            notifyPropertyChanged(BR.userid);
        }
    }
}
