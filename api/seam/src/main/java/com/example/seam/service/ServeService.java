package com.example.seam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seam.pojo.Serve;

import java.util.List;

public interface ServeService extends IService<Serve> {

    List<Serve> GetServe();
}
