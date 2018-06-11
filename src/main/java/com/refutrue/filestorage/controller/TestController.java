package com.refutrue.filestorage.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class TestController {
    @Autowired
    public FastFileStorageClient fastFileStorageClient;

    @RequestMapping("upload.do")
    @ResponseBody
    public String uploadImg() throws IOException {


        File file = new File("d://data.png");
        try (
                FileInputStream inputStream = new FileInputStream(file);
        ) {
            String fileName = file.getName();


            String strs = fileName.substring(fileName.lastIndexOf(".") + 1);
            ;
            if (!StringUtils.hasText(strs)) {
                return "fail";
            }
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(inputStream, file.length(), strs, null);
            System.out.println("path------" + storePath.getFullPath());
        }


        return "success";
    }

    @RequestMapping("delete.do")
    @ResponseBody
    public String deleteImg() {
        fastFileStorageClient.deleteFile("group1/M00/00/00/wKiJylksOiiAUWV3AABNeUASSJQ019_150x150.png");
        return "success";
    }


}
