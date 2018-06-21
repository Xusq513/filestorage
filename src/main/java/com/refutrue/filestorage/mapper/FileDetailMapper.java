package com.refutrue.filestorage.mapper;

import com.refutrue.filestorage.domain.FileDetail;

public interface FileDetailMapper {
    int deleteByPrimaryKey(String id);
    int insertSelective(FileDetail record);

    String selectFileDetailById(String id);
}