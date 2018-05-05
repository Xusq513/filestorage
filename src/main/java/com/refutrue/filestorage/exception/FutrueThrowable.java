package com.refutrue.filestorage.exception;

/**
 * @Author: xusq
 * @Date: 2018/4/26 17:26
 * @Description: 异常基础类信息接口
 *
**/
public interface FutrueThrowable {

    /**
     *  错误编码
     * @return
     */
    String getErrCode();

    /**
     * 错误信息
     * @return
     */
    String getErrMsg();

    /**
     *  错误详细信息
     * @return
     */
    String getErrDtlMsg();
}
