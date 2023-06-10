package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.example.seam.mapper.CurriculummanagementMapper;
import com.example.seam.pojo.Curriculummanagement;
import com.example.seam.util.BackJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/JiMony/curriculummanagement")
public class CurriculummanagementController {
    @Autowired
    CurriculummanagementMapper curriculummanagementMapper;

    /**
     * 查询课程
     *
     * @param id
     * @return List<Curriculummanagement>
     */
    @GetMapping({"/sel_course/{id}", "/sel_course/", "/sel_course"})
    public Object SelCourse(@PathVariable(value = "id", required = false) String id) {
        List<Curriculummanagement> curriculummanagements = curriculummanagementMapper.sel_course(id);
        if (curriculummanagements.size() != 0 || !curriculummanagements.isEmpty()) {
            return JSON.toJSON(new BackJson("查询成功", curriculummanagements, curriculummanagements.size(), "200"));
        }
        return JSON.toJSON(new BackJson("查询成功", "500"));
    }

    /**
     * 添加课程
     *
     * @param c（course、weeks、ms、userid、classroomid、gradeid）
     */
    @PostMapping("/add_course")
    public Object AddCourse(@RequestBody Curriculummanagement c) {
        int i = curriculummanagementMapper.add_course(c.getCourse(), c.getWeeks(), c.getMs(),
                c.getUserid(), c.getClassroomid(), c.getGradeid());
        if (i != 0) {
            return JSON.toJSON(new BackJson("添加成功", "200"));
        }
        return JSON.toJSON(new BackJson("添加失败", "500"));
    }

    /**
     * 修改课程
     *
     * @param c（course、weeks、ms、userid、classroomid、gradeid、id）
     */
    @PutMapping("/nodify_course")
    public Object NodifyCourse(@RequestBody Curriculummanagement c) {
        int i = curriculummanagementMapper.nodify_course(c.getCourse(), c.getWeeks(),
                c.getMs(), c.getUserid(), c.getClassroomid(), c.getGradeid(), c.getId());
        if (i != 0) {
            return JSON.toJSON(new BackJson("修改成功", "200"));
        }
        return JSON.toJSON(new BackJson("修改失败", "500"));
    }

    /**
     * 删除课程
     *
     * @param c（id）
     */
    @DeleteMapping("/del_course")
    public Object DelCourse(@RequestBody Curriculummanagement c) {
        int i = curriculummanagementMapper.del_course(c.getId());
        if (i != 0) {
            return JSON.toJSON(new BackJson("删除成功", "200"));
        }
        return JSON.toJSON(new BackJson("删除失败", "500"));
    }
}
