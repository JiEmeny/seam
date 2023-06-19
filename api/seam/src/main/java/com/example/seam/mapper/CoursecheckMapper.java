package com.example.seam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seam.pojo.Coursecheck;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CoursecheckMapper extends BaseMapper<Coursecheck> {
    int add_coursecheck(int curriculummanagementid, int userid, int signed);

    int nodify_coursecheck(int signed, int id);

    List<Coursecheck> look_register(String id);
}
