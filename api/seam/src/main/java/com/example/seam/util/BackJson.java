package com.example.seam.util;

import lombok.Data;

/**
 * 访问访问返回数据
 */
@Data
public class BackJson {
    private String msg;
    private Object rows;
    private int count;
    private String code;

    public BackJson(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public BackJson(String msg, Object rows, String code) {
        this.msg = msg;
        this.rows = rows;
        this.code = code;
    }

    public BackJson(String msg, Object rows, int count, String code) {
        this.msg = msg;
        this.rows = rows;
        this.count = count;
        this.code = code;
    }
}
