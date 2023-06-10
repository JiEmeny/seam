package com.example.seam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("curriculummanagement")
public class Curriculummanagement {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer course;
    private String weeks;
    private Integer ms;
    private Integer userid;
    private Integer classroomid;
    private Integer gradeid;
}
