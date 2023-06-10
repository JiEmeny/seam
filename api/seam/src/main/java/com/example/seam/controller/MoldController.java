package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.example.seam.mapper.MoldMapper;
import com.example.seam.pojo.Mold;
import com.example.seam.util.BackJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/JiMoney/mold")
public class MoldController {
    @Autowired
    MoldMapper moldMapper;

    /**
     * 查询类型说明
     *
     * @param id
     * @return
     */
    @GetMapping({"/get_mold/{id}", "/get_mold/", "/get_mold"})
    public Object GetMold(@PathVariable(value = "id", required = false) String id) {
        List<Mold> moldList = moldMapper.get_mold(id);
        if (!moldList.isEmpty() || moldList != null) {
            return JSON.toJSON(new BackJson("查询成功", moldList, moldList.size(), "200"));
        }
        return JSON.toJSON(new BackJson("未来查询到该id信息", "500"));
    }
}
