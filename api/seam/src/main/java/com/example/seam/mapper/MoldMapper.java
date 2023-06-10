package com.example.seam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seam.pojo.Mold;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MoldMapper extends BaseMapper<Mold> {
    List<Mold> get_mold(String id);
}
