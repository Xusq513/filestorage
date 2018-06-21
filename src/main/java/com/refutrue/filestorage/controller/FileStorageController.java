package com.refutrue.filestorage.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.refutrue.filestorage.adapter.Cors;
import com.refutrue.filestorage.domain.FileDetail;
import com.refutrue.filestorage.domain.FileMain;
import com.refutrue.filestorage.service.FileService;
import com.refutrue.filestorage.util.ResponseMsg;
import com.refutrue.filestorage.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@RestController
public class FileStorageController extends Cors {

    @Autowired
    public FastFileStorageClient fastFileStorageClient;

    @Autowired
    public FileService fileService;

    @PostMapping(value="fileStorage")
    public ResponseMsg uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseMsg responseMsg = new ResponseMsg();
        String pid = StringUtil.obj2Str(request.getParameter("pid"));
        // TODO LoginId先写死
        String loginId = "admin";
        String fileName = file.getOriginalFilename();
        long fileSize =  file.getSize();
        InputStream inputStream = file.getInputStream();
        StorePath storePath = fastFileStorageClient.uploadFile(inputStream,fileSize,fileName,null);
        FileMain fileMain = new FileMain();
        fileMain.setLoginId(loginId);
        fileMain.setFileName(fileName);
        fileMain.setParentId(pid);
        fileMain.setFileSize(fileSize);
        if(fileName.indexOf(".") != -1){
            String[] array = fileName.split("\\.");
            fileMain.setFileType(array[array.length -1]);
        }
        fileMain.setCreateUser(loginId);
        FileDetail fileDetail = new FileDetail();
        fileDetail.setUrl(storePath.getFullPath());
        fileMain = fileService.saveFileStorage(fileMain,fileDetail);
        responseMsg.setData(fileMain);
        return responseMsg;
    }

}
