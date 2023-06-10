package com.example.seam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("time")
public class Time {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String times;
}
