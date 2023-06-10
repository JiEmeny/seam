package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.example.seam.mapper.UsersMapper;
import com.example.seam.pojo.User;
import com.example.seam.pojo.Users;
import com.example.seam.service.UserService;
import com.example.seam.util.BackJson;
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
                user.setAvatar("/JiMoney/Pic/" + user.getAvatar());
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
        int modify = usersMapper.modify(users.getId(), users.getAvatar(), users.getName(), users.getSex(), users.getPhone(), users.getAddress(), users.getIdcard());
        if (modify != 0) {
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
