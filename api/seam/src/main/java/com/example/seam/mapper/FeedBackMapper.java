package com.example.seam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seam.pojo.FeedBack;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedBackMapper extends BaseMapper<FeedBack> {
    List<FeedBack> get_feedback();

    int add_feedback(String title, String context);
}
