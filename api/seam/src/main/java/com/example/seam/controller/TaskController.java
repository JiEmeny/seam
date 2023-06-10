package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.example.seam.mapper.TaskMapper;
import com.example.seam.pojo.Task;
import com.example.seam.util.BackJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/JiMoney/task")
public class TaskController {
    @Autowired
    TaskMapper taskMapper;

    /**
     * 添加作业
     *
     * @param task（title、content、userid、answer、time、timeon、endtime、curriculummanagementid）
     */
    @PostMapping("/add_task")
    public Object AddTask(@RequestBody Task task) {
        int i = taskMapper.add_task(task.getTitle(), task.getContent(), task.getUserid(), task.getAnswer(), task.getTime(), task.getTimeon(), task.getEndtime(), task.getCurriculummanagementid());
        if (i != 0) {
            return JSON.toJSON(new BackJson("添加成功", "task", "200"));
        }
        return JSON.toJSON(new BackJson("添加失败", "500"));
    }

    /**
     * 根据id删除作业
     *
     * @param task（id）
     */
    @DeleteMapping("/del_task")
    public Object DelTask(@RequestBody Task task) {
        int i = taskMapper.del_task(task.getId());
        if (i != 0) {
            return JSON.toJSON(new BackJson("删除成功", "200"));
        }
        return JSON.toJSON(new BackJson("删除失败", "500"));
    }

    /**
     * 根据id修改作业
     *
     * @param task（title、content、answer、time、tomeon、endtime、id）
     */
    @PutMapping("/job_change")
    public Object JobChange(@RequestBody Task task) {
        int i = taskMapper.job_change(task.getTitle(), task.getContent(), task.getAnswer(), task.getTime(), task.getTimeon(), task.getEndtime(), task.getId());
        if (i != 0) {
            return JSON.toJSON(new BackJson("修改成功", "200"));
        }
        return JSON.toJSON(new BackJson("修改失败", "500"));
    }

    /**
     * 查询作业
     *
     * @param id
     * @return List<Task>
     */
    @GetMapping({"/get_task/{id}", "/get_task/", "/get_task"})
    public Object GetTask(@PathVariable(value = "id", required = false) String id) {
        List<Task> task = taskMapper.get_task(id);
        if (task != null || !task.isEmpty()) {
            return JSON.toJSON(new BackJson("查询成功", task, task.size(), "200"));
        }
        return JSON.toJSON(new BackJson("未查询到该通知", "500"));
    }
}
