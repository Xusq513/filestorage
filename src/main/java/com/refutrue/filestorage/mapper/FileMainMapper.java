package com.refutrue.filestorage.mapper;

import com.refutrue.filestorage.domain.FileMain;

public interface FileMainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileMain record);

    int insertSelective(FileMain record);

    FileMain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileMain record);

    int updateByPrimaryKeyWithBLOBs(FileMain record);

    int updateByPrimaryKey(FileMain record);
}