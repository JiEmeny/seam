package com.example.seam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seam.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskMapper extends BaseMapper<Task> {
    int add_task(String title, String content, int userid, String answer, String time, String timeon, String endtime, int curriculummanagementid);

    int del_task(int id);

    int job_change(String title, String content, String answer, String time, String timeon, String endtime, int id);

    List<Task> get_task(String id);
}
