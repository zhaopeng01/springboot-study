package com.zyc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 图片上传
 *
 * @author zhaopeng
 * @email zp152527@163.com
 */


@Controller
@RequestMapping("/uploads")
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @GetMapping
    public String index() {
        return "index";
    }

    /**
     * @Description: 单文件
     * @author zhaopeng
     * @email zp152527@163.com
     */
    @PostMapping("/uploadSing")
    @ResponseBody
    public Object uploadSing(@RequestParam("file") MultipartFile file) throws IOException {
        file.transferTo(new File("E:\\idea_workspace\\springboot-study\\" + file.getOriginalFilename()));
        return "SUCCESS";
    }

    /**
     * @Description: 多文件
     * @author zhaopeng
     * @email zp152527@163.com
     */
    @PostMapping("/uploads")
    @ResponseBody
    public Object uploads(@RequestParam("file") MultipartFile[] files) throws IOException {
        if (files == null || files.length == 0) {
            return null;
        }
        for (MultipartFile file : files) {
            file.transferTo(new File("E:\\idea_workspace\\springboot-study\\" + file.getOriginalFilename()));
        }
        return "SUCCESS";
    }
}