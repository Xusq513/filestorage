package com.refutrue.filestorage.exception;

import java.util.Map;

public class FutrueException extends Exception implements FutrueThrowable{

    
    /**
     * 模块代码
     */
    private int modelCode;

    /**
     * 错误编码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 异常参数（针对集团错误编码转换而加）
     */
    private Map errParaMap;

    /**
     * 错误编码
     *
     * @return
     */
    @Override
    public String getErrCode() {
        return null;
    }

    /**
     * 错误信息
     *
     * @return
     */
    @Override
    public String getErrMsg() {
        return null;
    }

    /**
     * 错误详细信息
     *
     * @return
     */
    @Override
    public String getErrDtlMsg() {
        return null;
    }
}
