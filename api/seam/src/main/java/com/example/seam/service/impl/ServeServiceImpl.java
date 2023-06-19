package com.example.seam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seam.mapper.ServeMapper;
import com.example.seam.pojo.Serve;
import com.example.seam.service.ServeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServeServiceImpl extends ServiceImpl<ServeMapper, Serve>implements ServeService {
    @Autowired
    ServeMapper serveMapper;
    @Override
    public List<Serve> GetServe() {
        return serveMapper.GetServe();
    }
}
