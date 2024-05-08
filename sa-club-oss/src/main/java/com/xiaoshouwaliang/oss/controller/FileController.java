package com.xiaoshouwaliang.oss.controller;
import com.xiaoshouwaliang.oss.entity.Result;
import com.xiaoshouwaliang.oss.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/getUrl")
    public String getUrl(String bucketName, String objectName) throws Exception {
        return fileService.getUrl(bucketName, objectName);
    }
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile uploadFile, String bucket, String objectName){
        return Result.ok(fileService.uploadFile(uploadFile,bucket,objectName));
    }
}
