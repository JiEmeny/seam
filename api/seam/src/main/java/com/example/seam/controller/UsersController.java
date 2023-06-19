package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.example.seam.mapper.UsersMapper;
import com.example.seam.pojo.User;
import com.example.seam.pojo.Users;
import com.example.seam.service.UserService;
import com.example.seam.util.BackJson;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/JiMoney/users")
public class UsersController {
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    UserService userService;

    /**
     * 查询用户信息
     *
     * @param id
     * @return List<Users>
     */
    @GetMapping({"/get_users/{id}", "/get_users", "/get_users/"})
    public Object GetUsers(@PathVariable(value = "id", required = false) String id) {
        List<Users> users = usersMapper.get_users(id);
        for (Users user : users) {
            if (!user.getAvatar().isEmpty()) {
                user.setAvatar(user.getAvatar());
            }
        }
        return JSON.toJSON(new BackJson("查询成功", users, users.size(), "200"));
    }

    /**
     * 根据id修改用户信息
     *
     * @param users
     * @return
     */
    @PutMapping("/modify")
    public Object Modify(@RequestBody Users users) {
        int i1 = 10, i2 = 10, i3 = 10, i4 = 10, i5 = 10, i6 = 10;
        if (users.getAvatar() != null) {
            i1 = usersMapper.modify_avatar(users.getId(), users.getAvatar());
        }
        if (users.getName() != null) {
            i2 = usersMapper.modify_name(users.getId(), users.getName());
        }
        if (users.getSex() != null) {
            i3 = usersMapper.modify_sex(users.getId(), users.getSex());
        }
        if (users.getPhone() != null) {
            i4 = usersMapper.modify_phone(users.getId(), users.getPhone());
        }
        if (users.getAddress() != null) {
            i5 = usersMapper.modify_address(users.getId(), users.getAddress());
        }
        if (users.getIdcard() != null) {
            i6 = usersMapper.modify_idcard(users.getId(), users.getIdcard());
        }
        if (i1 != 0 || i2 != 0 || i3 != 0 || i4 != 0 || i5 != 0 || i6 != 0) {
            return JSON.toJSON(new BackJson("修改成功", users, "200"));
        }
        return JSON.toJSON(new BackJson("修改失败", "500"));
    }

    /**
     * 删除用户根据username
     * user、users表都进行删除
     *
     * @param user（username）
     */
    @DeleteMapping("/userdel")
    public Object Userdel(@RequestBody User user) {
        List<User> username = userService.get_username(user.getUsername());
        if (username != null || !username.isEmpty()) {
            int i = usersMapper.deleteUserInfo(username.get(0).getId());
            int userdel = userService.userdel(user.getUsername());
            if (userdel != 0 && i != 0) {
                return JSON.toJSON(new BackJson("删除成功", "200"));
            }
        }
        return JSON.toJSON(new BackJson("删除失败", "500"));
    }
}
