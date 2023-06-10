package com.example.seam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seam.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository // 持久层框架
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户名和密码进行登录
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    List<User> login(String username, String password);

    /**
     * 获取全部用户
     */
    @Select("select * from user")
    List<User> get_user();

    /**
     * 修改密码
     */
    @Update("update user set password=#{password} where username=#{username}")
    int set_password(String password, String username);

    /**
     * 修改权限
     */
    @Update("update user set type=#{type} where username=#{username}")
    int set_type(String type, String username);

    /**
     * 注册
     */
    @Insert("insert into user values (null,#{username},#{password},#{type})")
    int set_user(String username, String password, int type);

    /**
     * 根据username查询
     */
    @Select("select * from user where username=#{username}")
    List<User> get_username(String username);

    /**
     * 根据username删除用户
     */
    @Delete("delete from user where username=#{username}")
    int userdel(String username);
}
