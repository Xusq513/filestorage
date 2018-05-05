package com.refutrue.filestorage.controller;

import com.refutrue.filestorage.adapter.Cors;
import com.refutrue.filestorage.domain.SystemModule;
import com.refutrue.filestorage.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xusq
 * @Date: 2018/4/28 17:02
 * @Description:
 *
**/
@RestController
@RequestMapping(value = "/module")
public class ModuleController extends Cors{

    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value="/get")
    public List<SystemModule> getAllData(){
       return moduleService.getAllModule();
    }
}
