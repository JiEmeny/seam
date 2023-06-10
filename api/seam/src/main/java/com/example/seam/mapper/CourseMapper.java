package com.example.seam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seam.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CourseMapper extends BaseMapper<Course> {
    List<Course> get_course(String  id);

    int set_course(String courses);

    int delete_course(int id);

    int modify_course(int id, String courses);
}
