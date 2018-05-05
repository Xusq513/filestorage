package com.refutrue.filestorage.mapper;

import com.refutrue.filestorage.domain.FileDetail;

public interface FileDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileDetail record);

    int insertSelective(FileDetail record);

    FileDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileDetail record);

    int updateByPrimaryKeyWithBLOBs(FileDetail record);

    int updateByPrimaryKey(FileDetail record);
}