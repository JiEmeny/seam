package com.example.seam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seam.pojo.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> login(String username, String password);

    List<User> get_user();

    int set_password(String password, String username);

    int set_type(String type, String username);

    int set_user(String username, String password, int type);

    List<User> get_username(String username);

    int userdel(String username);
}
