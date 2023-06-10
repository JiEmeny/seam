package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.example.seam.mapper.GradeMapper;
import com.example.seam.pojo.Grade;
import com.example.seam.util.BackJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/JiMoney/grade")
public class GradeController {
    @Autowired
    GradeMapper gradeMapper;

    /**
     * 添加班级
     *
     * @param grade（grades）
     */
    @PostMapping("/set_grade")
    public Object SetGrade(@RequestBody Grade grade) {
        List<Grade> list = gradeMapper.get_grade(grade.getGrades());
        if (list.size() == 0 || list.isEmpty()) {
            int i = gradeMapper.set_grade(grade.getGrades());
            if (i != 0) {
                return JSON.toJSON(new BackJson("添加成功", "200"));
            }
        }
        return JSON.toJSON(new BackJson("添加失败", "500"));
    }

    /**
     * 删除班级
     *
     * @param grade（id）
     */
    @DeleteMapping("/del_grade")
    public Object DelGrade(@RequestBody Grade grade) {
        int i = gradeMapper.del_grade(grade.getId());
        if (i != 0) {
            return JSON.toJSON(new BackJson("删除成功", "200"));
        }
        return JSON.toJSON(new BackJson("删除失败", "500"));
    }

    /**
     * 修改班级
     *
     * @param grade（id、grades）
     */
    @PutMapping("/modify_grade")
    public Object ModifyGrade(@RequestBody Grade grade) {
        int i = gradeMapper.modify_grade(grade.getId(), grade.getGrades());
        if (i != 0) {
            return JSON.toJSON(new BackJson("修改成功", "200"));
        }
        return JSON.toJSON(new BackJson("修改失败", "500"));
    }

    /**
     * 查询班级
     *
     * @return List<Grade>
     */
    @GetMapping({"/get_grade/{grades}", "/get_grade/", "/get_grade"})
    public Object GetGrade(@PathVariable(value = "grades", required = false) String grades) {
        List<Grade> grade = gradeMapper.get_grade(grades);
        if (!grade.isEmpty() || grade.size() != 0) {
            return JSON.toJSON(new BackJson("查询成功", grade, grade.size(), "200"));
        }
        return JSON.toJSON(new BackJson("查询失败", "500"));
    }
}
