package com.example.seam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("classrooms")
public class Classrooms {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String classrooms;
}
