package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.example.seam.mapper.ClassroomsMapper;
import com.example.seam.pojo.Classrooms;
import com.example.seam.util.BackJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/JiMoney/classrooms")
public class ClassroomsController {
    @Autowired
    ClassroomsMapper classroomsMapper;

    /**
     * 添加教室
     *
     * @param classrooms（classrooms）
     */
    @PostMapping("/add_classrooms")
    public Object AddClassrooms(@RequestBody Classrooms classrooms) {
        int i = classroomsMapper.add_classrooms(classrooms.getClassrooms());
        if (i != 0) {
            return JSON.toJSON(new BackJson("添加成功", "200"));
        }
        return JSON.toJSON(new BackJson("添加失败", "500"));
    }

    /**
     * 删除教室
     *
     * @param classrooms（id）
     */
    @DeleteMapping("/del_classrooms")
    public Object DelClassrooms(@RequestBody Classrooms classrooms) {
        int i = classroomsMapper.del_classrooms(classrooms.getId());
        if (i != 0) {
            return JSON.toJSON(new BackJson("删除成功", "200"));
        }
        return JSON.toJSON(new BackJson("删除失败", "500"));
    }

    /**
     * 修改教室
     *
     * @param classrooms（id、classrooms）
     */
    @PutMapping("/modify_classrooms")
    public Object ModifyClassrooms(@RequestBody Classrooms classrooms) {
        int i = classroomsMapper.modify_classrooms(classrooms.getId(), classrooms.getClassrooms());
        if (i != 0) {
            return JSON.toJSON(new BackJson("修改成功", "200"));
        }
        return JSON.toJSON(new BackJson("修改失败", "500"));
    }

    /**
     * 查询教室
     *
     * @param id
     * @return List<Classrooms>
     */
    @GetMapping({"/inquiry_classrooms/{id}", "/inquiry_classrooms/", "/inquiry_classrooms"})
    public Object InquiryClassrooms(@PathVariable(value = "id", required = false) String id) {
        List<Classrooms> classrooms = classroomsMapper.inquiry_classrooms(id);
        if (!classrooms.isEmpty() || classrooms.size() != 0) {
            return JSON.toJSON(new BackJson("查询成功", classrooms, classrooms.size(), "200"));
        }
        return JSON.toJSON(new BackJson("暂未查询到该班级", "500"));
    }
}
