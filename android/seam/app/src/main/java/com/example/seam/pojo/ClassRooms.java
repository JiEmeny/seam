package com.example.seam.pojo;

import java.util.List;

/**
 * @e-mail:2036573698@qq.com
 */
public class ClassRooms {
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
        private String classrooms;
        private int id;

        public String getClassrooms() {
            return classrooms;
        }

        public void setClassrooms(String classrooms) {
            this.classrooms = classrooms;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
