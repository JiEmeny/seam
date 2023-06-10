package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.example.seam.mapper.NoticeMapper;
import com.example.seam.pojo.Notice;
import com.example.seam.util.BackJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/JiMoney/notice")
public class NoticeController {
    @Autowired
    NoticeMapper noticeMapper;

    /**
     * 添加通知
     *
     * @param notice
     */
    @PostMapping("/add_notice")
    public Object AddNotice(@RequestBody Notice notice) {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(System.currentTimeMillis()));
        int i = noticeMapper.add_notice(notice.getState(), notice.getTitle(), notice.getContent(), format, notice.getUserid(), notice.getType());
        if (i != 0) {
            return JSON.toJSON(new BackJson("添加成功", "200"));
        }
        return JSON.toJSON(new BackJson("添加失败", "500"));
    }

    /**
     * 根据id修改查看状态
     *
     * @param notice（id、state）
     */
    @PutMapping("/modify_see_type")
    public Object ModifySeeType(@RequestBody Notice notice) {
        int i = noticeMapper.modify_see_type(notice.getId(), notice.getState());
        if (i != 0) {
            return JSON.toJSON(new BackJson("修改成功", "200"));
        }
        return JSON.toJSON(new BackJson("修改失败", "500"));
    }

    /**
     * 根据id修改通知状态
     *
     * @param notice（id、type）
     */
    @PutMapping("/modify_notice_type")
    public Object ModifyNoticeType(@RequestBody Notice notice) {
        int i = noticeMapper.modify_notice_type(notice.getId(), notice.getType());
        if (i != 0) {
            return JSON.toJSON(new BackJson("修改成功", "200"));
        }
        return JSON.toJSON(new BackJson("修改失败", "500"));
    }

    /**
     * 根据id删除通知
     *
     * @param notice（id）
     */
    @DeleteMapping("/del_notice")
    public Object DelNotice(@RequestBody Notice notice) {
        int i = noticeMapper.del_notice(notice.getId());
        if (i != 0) {
            return JSON.toJSON(new BackJson("删除成功", "200"));
        }
        return JSON.toJSON(new BackJson("删除失败", "500"));
    }

    /**
     * 查询通知（根据id、查询全部）
     *
     * @param id
     * @return List<Notice>
     */
    @GetMapping({"/my/{id}", "/my/", "/my"})
    public Object my(@PathVariable(value = "id", required = false) String id) {
        List<Notice> my = noticeMapper.my(id);
        if (my.size() != 0 || my.isEmpty()) {
            return JSON.toJSON(new BackJson("查询成功", my, my.size(), "200"));
        }
        return JSON.toJSON(new BackJson("未查询到该通知", "500"));
    }
}
