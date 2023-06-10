package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.example.seam.mapper.CourseMapper;
import com.example.seam.pojo.Course;
import com.example.seam.util.BackJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/JiMony/course")
public class CourseController {
    @Autowired
    CourseMapper courseMapper;

    /**
     * 查询课程
     *
     * @param id
     * @return List<Course>
     */
    @GetMapping({"/get_course/{id}", "/get_course/", "/get_course"})
    public Object GetCourse(@PathVariable(value = "id", required = false) String  id) {
        List<Course> course = courseMapper.get_course(id);
        if (course.size() != 0 || !course.isEmpty()) {
            return JSON.toJSON(new BackJson("查询成功", course, course.size(), "200"));
        }
        return JSON.toJSON(new BackJson("查询失败", "500"));
    }

    /**
     * 添加课程
     *
     * @param course（courses）
     */
    @PostMapping("/set_course")
    public Object SetCourse(@RequestBody Course course) {
        int i = courseMapper.set_course(course.getCourses());
        if (i != 0) {
            return JSON.toJSON(new BackJson("添加成功", course, i, "200"));
        }
        return JSON.toJSON(new BackJson("添加失败", "500"));
    }

    /**
     * 删除课程
     *
     * @param course（id）
     */
    @DeleteMapping("/delete_course")
    public Object DeleteCourse(@RequestBody Course course) {
        int i = courseMapper.delete_course(course.getId());
        if (i != 0) {
            return JSON.toJSON(new BackJson("删除成功", "200"));
        }
        return JSON.toJSON(new BackJson("添加失败", "500"));
    }

    /**
     * 修改课程
     *
     * @param course（id、courses）
     */
    @PutMapping("/modify_course")
    public Object ModifyCourse(@RequestBody Course course) {
        int i = courseMapper.modify_course(course.getId(), course.getCourses());
        if (i != 0) {
            return JSON.toJSON(new BackJson("修改成功", "200"));
        }
        return JSON.toJSON(new BackJson("修改失败", "500"));
    }
}
