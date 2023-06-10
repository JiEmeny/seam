package com.example.seam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seam.pojo.Curriculummanagement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CurriculummanagementMapper extends BaseMapper<Curriculummanagement> {
    List<Curriculummanagement> sel_course(String id);

    int add_course(int course, String weeks, int ms, int userid, int classroomid, int gradeid);

    int nodify_course(int course, String weeks, int ms, int userid, int classroomid, int gradeid, int id);

    int del_course(int id);
}
