package com.refutrue.filestorage.domain;

import java.io.Serializable;

/**
 * @Author: xusq
 * @Date: 2018/4/26 17:20
 * @Description: 组装文件的所有信息
 *
**/
public class FileData extends FileMain implements Serializable {

    private byte[] fileData ;

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
