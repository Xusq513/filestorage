package com.refutrue.filestorage.controller;

import com.refutrue.filestorage.adapter.Cors;
import com.refutrue.filestorage.domain.FileMain;
import com.refutrue.filestorage.service.FileService;
import com.refutrue.filestorage.util.RequestUtil;
import com.refutrue.filestorage.util.ResponseMsg;
import com.refutrue.filestorage.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FileController extends Cors {

    @Autowired
    private FileService fileService;

    @GetMapping(value = "/file/{pid}")
    public List<FileMain> getFileListByPid(@PathVariable(name="pid") String pid,HttpServletRequest request){
        if(StringUtils.isEmpty(pid)){
            pid = "root";
        }
        // TODO 获取登录信息的先写死
        HttpSession session = request.getSession();
        String loginId = "admin";
        Map<String,Object> inMap = new HashMap<>();
        inMap.put("parentId",pid);
        inMap.put("loginId",loginId);
        return fileService.getFileListByPid(inMap);
    }

    @PostMapping(value="/file")
    public ResponseMsg saveFileMsg(HttpServletRequest request, HttpServletResponse response){
        ResponseMsg responseMsg = new ResponseMsg();
        // TODO 获取登录信息的先写死
        HttpSession session = request.getSession();
        String loginId = "admin";
        String id = StringUtil.obj2Str(request.getParameter("id")) ;
        String fileName = StringUtil.obj2Str(request.getParameter("fileName"));
        String pid = StringUtil.obj2Str(request.getParameter("pid"));
        if(StringUtil.isEmptyOrNull(pid)){
            pid = "root";
        }
        FileMain fileMain = new FileMain();
        fileMain.setId(id);
        fileMain.setFileName(fileName);
        fileMain.setParentId(pid);
        fileMain.setLoginId(loginId);
        fileMain =  fileService.saveFileMsg(fileMain);
        responseMsg.setData(fileMain);
        return responseMsg;
    }
}
