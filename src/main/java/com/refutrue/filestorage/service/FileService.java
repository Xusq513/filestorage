package com.refutrue.filestorage.service;

import com.refutrue.filestorage.domain.FileData;
import com.refutrue.filestorage.domain.FileMain;

import java.util.List;
import java.util.Map;

/**
 * @Author: xusq
 * @Date: 2018/4/26 17:17
 * @Description: 文件的基础动作
 *
**/
public interface FileService {

    /**
     * 新增文件夹或者重命名的操作
     * @param fileMain
     * @return
     */
    public FileMain saveFileMsg(FileMain fileMain);

    /**
     * 获取文件夹或者文件
     * @param inMap
     */
    public List<FileMain> getFileListByPid(Map<String,Object> inMap);
}
