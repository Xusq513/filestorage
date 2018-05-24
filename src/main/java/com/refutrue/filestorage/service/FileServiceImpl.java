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
    public FileMain saveFileMsg(FileMain fileMain) {
        String id = fileMain.getId();
        // 如果为空表示新增
        if(StringUtil.isEmptyOrNull(id)){
            fileMain.setId(UUID.randomUUID().toString());
            fileMain.setCreateTime(new Date());
            fileMain.setUpdateTime(new Date());
            fileMain.setFileType("dir");
            fileMainMapper.insertSelective(fileMain);
        }else{
            fileMain.setUpdateTime(new Date());
            fileMainMapper.updateByPrimaryKeySelective(fileMain);
        }
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
