package com.example.seam.controller;

import com.alibaba.fastjson.JSON;
import com.example.seam.mapper.FileMapper;
import com.example.seam.pojo.File;
import com.example.seam.util.BackJson;
import com.example.seam.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/JiMony/file")
public class FileController {
    @Autowired
    FileMapper fileMapper;
    @Value("${web.upload-path}")
    private String path;

    /**
     * 文件上传通用接口
     *
     * @param file
     * @return fileName（文件名）
     */
    @PostMapping("/postfile")
    @ResponseBody
    public Object PostFile(@RequestBody MultipartFile file) {
        File f = new File();
        String fileName = "";
        if (file != null) {
            fileName = FileUtil.upload(file, path, file.getOriginalFilename());
            if (fileName != null) {
                f.setFiles(fileName);
            }
        }
        int i = fileMapper.set_file("/JiMoney/Pic/" + f.getFiles());
        if (i != 0) {
            fileName = "/JiMoney/Pic/" + fileName;
            return JSON.toJSON(new BackJson("上传成功", fileName, "200"));
        }
        return JSON.toJSON(new BackJson("上传失败", "500"));
    }

    /**
     * 通用查找文件
     *
     * @param id
     * @return List<File>
     */
    @GetMapping({"/getfile/{id}", "/getfile/", "/getfile"})
    public Object GetFile(@PathVariable(value = "id", required = false) String id) {
        List<File> f = fileMapper.get_file(id);
        if (f.size() != 0 || !f.isEmpty()) {
            return JSON.toJSON(new BackJson("查询成功", f, f.size(), "200"));
        }
        return JSON.toJSON(new BackJson("未查询到该文件", "500"));
    }
}
