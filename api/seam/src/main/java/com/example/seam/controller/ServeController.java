package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.example.seam.pojo.Serve;
import com.example.seam.service.ServeService;
import com.example.seam.util.BackJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/JiEmony/serve")
public class ServeController {
    @Autowired
    ServeService serveService;

    /**
     * 获取全部服务
     *
     * @return List<Serve>
     */
    @GetMapping("/get_serve")
    public Object GetServe() {
        List<Serve> serves = serveService.GetServe();
        return JSON.toJSON(new BackJson("查询成功", serves, "200"));

    }
}
