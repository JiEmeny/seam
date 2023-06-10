package com.example.seam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seam.pojo.Classrooms;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClassroomsMapper extends BaseMapper<Classrooms> {
    int add_classrooms(String classrooms);

    int del_classrooms(int id);

    int modify_classrooms(int id, String classrooms);

    List<Classrooms> inquiry_classrooms(String id);
}
