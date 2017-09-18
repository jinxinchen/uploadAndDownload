package com.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by 陈 on 2017/9/17.
 */
@Controller
@RequestMapping("/home")
public class UploadController {
    @RequestMapping("/oneUpload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";
        String filename = file.getOriginalFilename();

        //判断相应路径是否存在，不存在则创建
        File dir = new File(uploadUrl);
        if(!dir.exists()){
            dir.mkdir();
        }

        System.out.println("文件上传到：" + uploadUrl + filename);
        File targetFile = new File(uploadUrl + filename);
        if(!targetFile.exists()){
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //移动上传文件
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("http://127.0.0.1:8080/study2/upload/" + filename);
        return null;
    }

    @RequestMapping("/moreUpload")
    public String moreUpload(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> files = multipartHttpServletRequest.getFileMap();

        String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";

        //判断相应路径是否存在，不存在则创建
        File dir = new File(uploadUrl);
        if(!dir.exists()){
            dir.mkdir();
        }

        List<String> fileList = new ArrayList<String>();
        for(MultipartFile file : files.values()){
            File targetFile = new File(uploadUrl + file.getOriginalFilename());
            if(!targetFile.exists()){
                try {
                    targetFile.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                try {
                    file.transferTo(targetFile);
                    fileList.add("http://localhost:8080/study2/upload/" + file.getOriginalFilename());
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        request.setAttribute("files",fileList);
        System.out.println(files.size());
        return null;
    }
}
