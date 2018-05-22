package com.refutrue.filestorage.service;

import com.refutrue.filestorage.domain.FileData;

/**
 * @Author: xusq
 * @Date: 2018/4/26 17:17
 * @Description: 文件的基础动作
 *
**/
public interface FileService {

    /**
     * 新增文件或者文件夹的操作
     * @param fileData
     */
    public void addFile(FileData fileData);

    /**
     * 修改文件夹或者文件的基本信息,包含文件夹的移动
     * @param fileData
     */
    public void modFile(FileData fileData);

    /**
     * 删除文件夹或者文件
     * @param fileData
     */
    public void delFile(FileData fileData);

    /**
     * 获取文件夹或者文件
     * @param fileData
     */
}
