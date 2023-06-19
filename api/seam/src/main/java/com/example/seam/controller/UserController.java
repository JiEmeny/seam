package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.seam.mapper.UsersMapper;
import com.example.seam.pojo.User;
import com.example.seam.service.UserService;
import com.example.seam.util.BackJson;
import com.example.seam.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin // 解决跨越问题
@RequestMapping("/JiMoney/index")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UsersMapper usersMapper;

    /**
     * 用于登录
     *
     * @param user（username、password）
     * @return Map<String, Object>
     */
    @PostMapping("/login")
    public Object Login(@RequestBody User user) {
        List<User> login = userService.login(user.getUsername(), user.getPassword());
        Map<String, Object> map = new HashMap<>();
        if (login.size()!=0) {
            // 包装token
            String token = TokenUtil.get_token(user);
            List<User> userList = userService.get_username(user.getUsername());
            map.put("token", token);
            map.put("user", userList);
            return JSON.toJSON(new BackJson("登录成功", map, "200"));
        } else {
            return JSON.toJSON(new BackJson("用户名或密码错误", "500"));
        }
    }

    /**
     * 获取全部用户
     *
     * @return List<User>
     */
    @GetMapping("/get_user")
    public Object GetUser() {
        List<User> user = userService.get_user();
        return JSON.toJSON(new BackJson("获取成功", user, user.size(), "200"));
    }

    /**
     * 修改密码
     *
     * @param json（oldpassword、newpassword）
     * @param request（获取token）
     */
    @PutMapping("/set_password")
    public Object SetPassword(@RequestBody JSONObject json, HttpServletRequest request) {
        String token = request.getHeader("token");
        JSONObject jsonObject = new JSONObject();
        int i = 0;
        if (token.length() != 0) {
            Map<String, Object> verify = TokenUtil.verifyToken(token);
            if (!verify.isEmpty() || verify.size() != 0) {
                Object username = verify.get("username");
                Object password = verify.get("password");
                if (Objects.equals(password, json.getString("oldpassword"))) {
                    i = userService.set_password(json.getString("newpassword"), username.toString());
                }
            }
        }
        jsonObject.put("oldpassword", json.getString("oldpassword"));
        jsonObject.put("newpassword", json.getString("newpassword"));
        if (i > 0) {
            return JSON.toJSON(new BackJson("修改成功", jsonObject, "200"));
        } else {
            return JSON.toJSON(new BackJson("修改失败", "500"));
        }
    }

    /**
     * 根据用户名修改用户权限
     *
     * @param json（username）
     */
    @PutMapping("/set_type")
    public Object SetType(@RequestBody JSONObject json) {
        int i = 0;
        i = userService.set_type(json.getString("type"), json.getString("username"));
        if (i != 0) {
            return JSON.toJSON(new BackJson("修改成功", json, "200"));
        } else {
            return JSON.toJSON(new BackJson("修改失败", "500"));
        }
    }

    /**
     * 注册账户
     * user、users表都添加
     *
     * @param json（username、password）
     */
    @PostMapping("/set_user")
    public Object SetUser(@RequestBody JSONObject json) {
        List<User> username = userService.get_username(json.getString("username"));
        if (username.size() == 0 || username.isEmpty()) {
            int i = userService.set_user(json.getString("username"), json.getString("password"), 2);
            if (i != 0) {
                List<User> userList = userService.get_username(json.getString("username"));
                usersMapper.set_users(userList.get(0).getId(), "", "", "", "", "", "");
                return JSON.toJSON(new BackJson("添加成功", json, "200"));
            }
            return JSON.toJSON(new BackJson("添加失败", "500"));
        }
        return JSON.toJSON(new BackJson("该用户名已存在", "500"));
    }

    /**
     * 根据username获取user
     *
     * @param username
     * @return user
     */
    @GetMapping({"/get_user_username/{username}","/get_user_username/","/get_user_username"})
    public Object GetUserByUsername(@PathVariable(value = "username", required = false) String username) {
        List<User> user = userService.get_username(username);
        return JSON.toJSON(new BackJson("200", user, "查询成功"));
    }
}
