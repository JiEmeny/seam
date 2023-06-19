package com.example.seam.pojo;

import java.util.List;

/**
 * @e-mail:2036573698@qq.com
 */
public class Tasks {
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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        private String timeon;
        private Object answer;
        private String endtime;
        private int id;
        private Object time;
        private int curriculummanagementid;
        private String title;
        private int userid;
        private String content;

        public String getTimeon() {
            return timeon;
        }

        public void setTimeon(String timeon) {
            this.timeon = timeon;
        }

        public Object getAnswer() {
            return answer;
        }

        public void setAnswer(Object answer) {
            this.answer = answer;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getTime() {
            return time;
        }

        public void setTime(Object time) {
            this.time = time;
        }

        public int getCurriculummanagementid() {
            return curriculummanagementid;
        }

        public void setCurriculummanagementid(int curriculummanagementid) {
            this.curriculummanagementid = curriculummanagementid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
