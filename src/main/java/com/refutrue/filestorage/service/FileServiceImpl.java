package com.refutrue.filestorage.service;

import com.refutrue.filestorage.domain.FileDetail;
import com.refutrue.filestorage.domain.FileMain;
import com.refutrue.filestorage.mapper.FileDetailMapper;
import com.refutrue.filestorage.mapper.FileMainMapper;
import com.refutrue.filestorage.util.DateUtil;
import com.refutrue.filestorage.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private FileMainMapper fileMainMapper;

    @Autowired
    private FileDetailMapper fileDetailMapper;

    @Override
    public FileMain addDir(FileMain fileMain) throws Exception{
        String id = fileMain.getId();
        // 新增时如果有ID直接抛出异常
        if(StringUtil.isNotEmptyOrNull(id)){
            throw new Exception("新增时ID不为空！");
        }
        fileMain.setId(UUID.randomUUID().toString());
        fileMain.setCreateTime(new Date());
        fileMain.setUpdateTime(new Date());
        fileMain.setFileType("dir");
        fileMainMapper.insertSelective(fileMain);
        return fileMain;
    }

    @Override
    public FileMain modDir(FileMain fileMain) throws Exception{
        String id = fileMain.getId();
        if(StringUtil.isEmptyOrNull(id)){
            throw new Exception("修改文件时ID为空！");
        }
        fileMain.setUpdateTime(new Date());
        fileMainMapper.updateByPrimaryKeySelective(fileMain);
        return fileMain;
    }

    @Override
    public List<FileMain> getFileListByPid(Map<String,Object> inMap) {
        // TODO 这里的地址应该是配置的，先写死
        String nginxUrl = "http://60.205.228.42:8903/";
        List<FileMain> list = fileMainMapper.selectByPid(inMap);
        for(FileMain fileMain : list ){
            if(fileMain.getUpdateTime() != null){
                fileMain.setLastModifyTime(DateUtil.toStringYmdHmsWthH(fileMain.getUpdateTime()));
            }
            // 如果是文件的话，还要查询Detail表
            if(!"dir".equals(fileMain.getFileType())){
                String clientId = fileMain.getClientId();
                if(StringUtil.isNotEmptyOrNull(clientId)){
                   String url = fileDetailMapper.selectFileDetailById(clientId);
                   if(StringUtil.isNotEmptyOrNull(url)){
                       fileMain.setDownloadUrl(nginxUrl + url);
                   }
                }
            }
        }
        return list;
    }

    @Override
    public FileMain saveFileStorage(FileMain fileMain, FileDetail fileDetail) {
        // TODO 这里的地址应该是配置的，先写死
        String nginxUrl = "http://60.205.228.42:8903/";
        fileMain.setId(UUID.randomUUID().toString());
        fileMain.setUpdateTime(new Date());
        fileMain.setCreateTime(new Date());
        fileDetail.setId(UUID.randomUUID().toString());
        fileDetail.setCreateTime(new Date());
        fileMain.setClientId(fileDetail.getId());
        fileMain.setDownloadUrl(nginxUrl + fileDetail.getUrl());
        fileMainMapper.insertSelective(fileMain);
        fileDetailMapper.insertSelective(fileDetail);
        return fileMain;
    }


}
