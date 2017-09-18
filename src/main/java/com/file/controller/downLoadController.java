package com.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by 陈 on 2017/9/17.
 */
@Controller

public class downLoadController {
    @RequestMapping("/downLoad")
    public String downLoad(String fileName, HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        String ctxPath = request.getSession().getServletContext().getRealPath("/") + "upload/";
        String downLoadPath = ctxPath + fileName;
        System.out.println(downLoadPath);

        try {
            long fileLength = new java.io.File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
                bos.write(buff, 0, bytesRead);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
