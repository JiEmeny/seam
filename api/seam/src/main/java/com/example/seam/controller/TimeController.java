package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.example.seam.mapper.TimeMapper;
import com.example.seam.pojo.Time;
import com.example.seam.util.BackJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/JiMony/time")
public class TimeController {
    @Autowired
    TimeMapper timeMapper;

    /**
     * 查询全部上课时间
     *
     * @return List<Time>
     */
    @GetMapping("/get_time")
    public Object GetTime() {
        List<Time> time = timeMapper.get_time();
        if (time != null || !time.isEmpty()) {
            return JSON.toJSON(new BackJson("查询成功", time, time.size(), "200"));
        }
        return JSON.toJSON(new BackJson("未查询到上课时间", "500"));
    }
}
