package com.xiaoshouwaliang.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.xiaoshouwaliang.oss.service.FileService;
import com.xiaoshouwaliang.oss.util.MinioUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-21
 */
@RestController
public class FileController {
    @Resource
    private FileService fileService;
    @GetMapping("/miniotest")
    public List<String> getAllBucket(){
        try {
            return fileService.getAllBucket();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile,String bucket,String objectName){
        return fileService.uploadFile(uploadFile,bucket,objectName);
    }
}
