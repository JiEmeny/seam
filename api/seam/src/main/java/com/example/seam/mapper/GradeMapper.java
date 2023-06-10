package com.example.seam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seam.pojo.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GradeMapper extends BaseMapper<Grade> {
    int set_grade(String grades);

    int del_grade(int id);

    int modify_grade(int id, String grades);

    List<Grade> get_grade(String grades);
}
