package com.example.seam.pojo;

/**
 * @e-mail:2036573698@qq.com
 */
public class PassWord {
    private String msg;
    private int count;
    private String code;
    private RowsBean rows;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RowsBean getRows() {
        return rows;
    }

    public void setRows(RowsBean rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        private String oldpassword;
        private String newpassword;

        public String getOldpassword() {
            return oldpassword;
        }

        public void setOldpassword(String oldpassword) {
            this.oldpassword = oldpassword;
        }

        public String getNewpassword() {
            return newpassword;
        }

        public void setNewpassword(String newpassword) {
            this.newpassword = newpassword;
        }
    }
}
