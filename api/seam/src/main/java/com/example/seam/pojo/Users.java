package com.example.seam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class Users {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String avatar;
    private String name;
    private String sex;
    private String phone;
    private String address;
    private String idcard;
}
