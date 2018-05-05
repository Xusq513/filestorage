package com.refutrue.filestorage.domain;

import java.io.Serializable;
import java.util.Date;

public class FileDetail implements Serializable {
    private Integer id;

    private String implementClass;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String paramters;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImplementClass() {
        return implementClass;
    }

    public void setImplementClass(String implementClass) {
        this.implementClass = implementClass == null ? null : implementClass.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getParamters() {
        return paramters;
    }

    public void setParamters(String paramters) {
        this.paramters = paramters == null ? null : paramters.trim();
    }
}