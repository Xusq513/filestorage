package com.refutrue.filestorage.service;

import com.refutrue.filestorage.domain.FileData;
import com.refutrue.filestorage.domain.FileMain;
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
        List<FileMain> list = fileMainMapper.selectByPid(inMap);
        for(FileMain fileMain : list ){
            if(fileMain.getUpdateTime() != null){
                fileMain.setLastModifyTime(DateUtil.toStringYmdHmsWthH(fileMain.getUpdateTime()));
            }
        }
        return list;
    }
}
