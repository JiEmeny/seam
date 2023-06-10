package com.example.seam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("coursecheck")
public class Coursecheck {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer curriculummanagementid;
    private Integer userid;
    private Integer signed;
}
