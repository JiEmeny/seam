package com.example.seam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seam.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoticeMapper extends BaseMapper<Notice> {
    int add_notice(int state, String title, String content, String time, int userid, int type);

    int modify_see_type(int id, int state);

    int modify_notice_type(int id, int type);

    int del_notice(int id);

    List<Notice> my(String id);
}
