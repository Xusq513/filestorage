package com.refutrue.filestorage.mapper;

import com.refutrue.filestorage.domain.SystemModule;

import java.util.List;

public interface SystemModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemModule record);

    int insertSelective(SystemModule record);

    SystemModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemModule record);

    int updateByPrimaryKey(SystemModule record);

    List<SystemModule> selectAllData();
}