package com.example.seam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seam.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UsersMapper extends BaseMapper<Users> {
    List<Users> get_users(String id);

    int set_users(int id, String avatar, String name, String sex, String phone, String address, String idcard);

    int modify(int id, String avatar, String name, String sex, String phone, String address, String idcard);

    int deleteUserInfo(int id);
}
