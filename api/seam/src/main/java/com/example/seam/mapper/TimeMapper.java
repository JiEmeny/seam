package com.example.seam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seam.pojo.Time;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TimeMapper extends BaseMapper<Time> {
    List<Time> get_time();
}
