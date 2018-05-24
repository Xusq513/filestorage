package com.refutrue.filestorage.mapper;

import com.refutrue.filestorage.domain.FileMain;

import java.util.List;
import java.util.Map;

public interface FileMainMapper {
    int deleteByPrimaryKey(String id);

    int insert(FileMain record);

    int insertSelective(FileMain record);

    FileMain selectByPrimaryKey(String id);

    List<FileMain> selectByPid(Map<String, Object> inMap);

    int updateByPrimaryKeySelective(FileMain record);

    int updateByPrimaryKeyWithBLOBs(FileMain record);

    int updateByPrimaryKey(FileMain record);
}