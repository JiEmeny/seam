package com.example.seam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seam.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UsersMapper extends BaseMapper<Users> {
    List<Users> get_users(String id);

    int set_users(int id, String avatar, String name, String sex, String phone, String address, String idcard);

    int modify_avatar(@Param("id") int id, @Param("avatar") String avatar);

    int modify_name(@Param("id") int id, @Param("name") String name);

    int modify_sex(@Param("id") int id, @Param("sex") String sex);

    int modify_phone(@Param("id") int id, @Param("phone") String phone);

    int modify_address(@Param("id") int id, @Param("address") String address);

    int modify_idcard(@Param("id") int id, @Param("idcard") String idcard);

    int deleteUserInfo(int id);
}
