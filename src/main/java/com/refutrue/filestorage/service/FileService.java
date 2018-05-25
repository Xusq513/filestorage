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
     * 新增文件夹
     * @param fileMain
     * @return
     */
    public FileMain addDir (FileMain fileMain) throws Exception;

    /**
     * 修改文件夹
     */
    public FileMain modDir(FileMain fileMain) throws Exception;

    /**
     * 获取文件夹下所有文件夹和文件
     * @param inMap
     */
    public List<FileMain> getFileListByPid(Map<String,Object> inMap);
}
