package com.refutrue.filestorage.domain;

import java.util.Date;

/**
 * @Author: xusq
 * @Date: 2018/4/26 16:17
 * @Description: 基础模型
 *
**/
public class BaseModel {

    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 最近一次记录的修改时间，第一次默认为创建时间
     */
    private Date updateTime;

    
}
