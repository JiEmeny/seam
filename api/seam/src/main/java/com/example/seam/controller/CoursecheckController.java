package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.example.seam.mapper.CoursecheckMapper;
import com.example.seam.pojo.Coursecheck;
import com.example.seam.util.BackJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/JiMony/coursecheck")
public class CoursecheckController {
    @Autowired
    CoursecheckMapper coursecheckMapper;

    /**
     * 添加签到
     *
     * @param coursecheck（userid、signed）
     */
    @PostMapping("/add_coursecheck")
    public Object AddCoursecheck(@RequestBody Coursecheck coursecheck) {
        int i = coursecheckMapper.add_coursecheck(coursecheck.getCurriculummanagementid(), coursecheck.getUserid(), coursecheck.getSigned());
        if (i != 0) {
            return JSON.toJSON(new BackJson("添加成功", "200"));
        }
        return JSON.toJSON(new BackJson("添加失败", "500"));
    }

    /**
     * 根据id修改签到状态
     *
     * @param coursecheck（signed、id）
     */
    @PutMapping("/nodify_coursecheck")
    public Object NodifyCoursecheck(@RequestBody Coursecheck coursecheck) {
        int i = coursecheckMapper.nodify_coursecheck(coursecheck.getCurriculummanagementid(), coursecheck.getSigned(), coursecheck.getId());
        if (i != 0) {
            return JSON.toJSON(new BackJson("修改成功", "200"));
        }
        return JSON.toJSON(new BackJson("修改失败", "500"));
    }

    /**
     * 查询签到
     *
     * @param id
     * @return List<Coursecheck>
     */
    @GetMapping({"/look_register/{id}", "/look_register/", "/look_register"})
    public Object LookRegister(@PathVariable(value = "id", required = false) String id) {
        List<Coursecheck> coursechecks = coursecheckMapper.look_register(id);
        if (coursechecks.size() != 0 || !coursechecks.isEmpty()) {
            return JSON.toJSON(new BackJson("查询成功", coursechecks, coursechecks.size(), "200"));
        }
        return JSON.toJSON(new BackJson("未查询到该签到", "500"));
    }
}
