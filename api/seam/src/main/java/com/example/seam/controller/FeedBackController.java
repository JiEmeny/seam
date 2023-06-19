package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seam.mapper.FeedBackMapper;
import com.example.seam.pojo.FeedBack;
import com.example.seam.util.BackJson;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/JiMony/feedback")
public class FeedBackController {
    @Autowired
    FeedBackMapper feedBackMapper;

    /**
     * 查询全部意见反馈
     *
     * @return List<FeedBack>
     */
    @GetMapping("/get_feedback")
    public Object get_feedback() {
        List<FeedBack> feedback = feedBackMapper.get_feedback();
        return JSON.toJSON(new BackJson("查询成功", feedback, feedback.size(), "200"));
    }

    /**
     * 新增意见看反馈
     *
     * @param feedBack
     */
    @PostMapping("/add_feedback")
    public Object add_feedback(@RequestBody FeedBack feedBack) {
        int i = feedBackMapper.add_feedback(feedBack.getTitle(), feedBack.getContent());
        if (i > 0) {
            return JSON.toJSON(new BackJson("添加成功", feedBack, "200"));
        }
        return JSON.toJSON(new BackJson("添加失败", "500"));
    }
}
