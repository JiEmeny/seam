package com.example.assist.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 工具类
 *
 * @e-mail:2036573698@qq.com
 */
public class BaseResponse {
    /**
     * 结果码
     */
    @SerializedName("res_code")
    @Expose
    public Integer responseCode;
    /**
     * 返回的错误信息
     */
    @SerializedName("res_error")
    @Expose
    public String responseError;
}
