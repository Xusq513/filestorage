package com.refutrue.filestorage.service;

import com.refutrue.filestorage.domain.SystemModule;

import java.util.List;

/**
 * @Author: xusq
 * @Date: 2018/4/28 16:54
 * @Description:  模块目录
 *
**/
public interface ModuleService {

    /**
     * 获取全部的目录
    **/
    public List<SystemModule> getAllModule();
}
