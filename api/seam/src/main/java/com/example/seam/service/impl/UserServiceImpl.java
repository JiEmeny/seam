package com.example.seam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seam.mapper.UserMapper;
import com.example.seam.pojo.User;
import com.example.seam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> login(String username, String password) {
        List<User> login = userMapper.login(username, password);
        return login;
    }

    @Override
    public List<User> get_user() {
        return userMapper.get_user();
    }

    @Override
    public int set_password(String password, String username) {
        return userMapper.set_password(password, username);
    }

    @Override
    public int set_type(String type, String username) {
        return userMapper.set_type(type, username);
    }

    @Override
    public int set_user(String username, String password, int type) {
        return userMapper.set_user(username, password, type);
    }

    @Override
    public List<User> get_username(String username) {
        return userMapper.get_username(username);
    }

    @Override
    public int userdel(String username) {
        return userMapper.userdel(username);
    }
}
