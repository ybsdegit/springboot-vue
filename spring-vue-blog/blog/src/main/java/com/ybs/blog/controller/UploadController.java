package com.ybs.blog.controller;

import com.ybs.blog.utils.Result;
import com.ybs.blog.utils.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * UploadController
 *
 * @author Paulson
 * @date 2020/3/28 0:00
 */

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping("/uploadImage")
    public Result<String> uploadImage(MultipartFile file){
        String url = uploadService.uploadImage(file);
        return new Result<>("上传成功", url);
    }
}
