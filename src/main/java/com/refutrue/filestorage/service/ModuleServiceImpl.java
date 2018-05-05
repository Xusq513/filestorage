package com.refutrue.filestorage.service;

import com.refutrue.filestorage.domain.SystemModule;
import com.refutrue.filestorage.mapper.SystemModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "moduleService")
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private SystemModuleMapper systemModuleMapper;

    @Override
    public List<SystemModule> getAllModule() {
        return systemModuleMapper.selectAllData();
    }
}
