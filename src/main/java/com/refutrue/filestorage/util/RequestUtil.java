package com.refutrue.filestorage.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 页面请求信息获取工具类
 * @author linql 创建 2012-2-25
 * @version 1.0 Copyright(c) 北京神州数码思特奇信息技术股份有限公司
 */
public class RequestUtil {

    /**
     * 日志
     */
    private final Log logger = LogFactory.getLog(RequestUtil.class);

    /**
     * 页面请求对象
     */
    private HttpServletRequest request;

    /**
     * 创建一个新的实例 RequestUtil.
     * @param _request 请求信息
     */
    public RequestUtil(HttpServletRequest _request) {
        this.request = _request;
    }

    /**
     * 获取字符型请求参数
     * @param paramName 参数名称
     * @return 处理结果， NUll时返回空串
     */
    public String getStrParam(String paramName) {
        String str = request.getParameter(paramName);
        // 参数不存在返回空串
        if (StringUtil.isNull(str)) {
            return "";
        } else {
            return StringUtil.xssFilter(str);
        }
    }

    /**
     * 获取整形请求参数
     * <p>
     * 1、从请求域中获取参数信息 <br/>
     * 2、判断值是否存在 <br/>
     * 2.1、值不存在返回0 <br/>
     * 2.2、存在进行转换为整形，数据不合法将抛出{@link NumberFormatException}异常<br/>
     * 2.3、返回转换后的数据
     * @param paramName 参数名称
     * @return 处理结果， NUll时返回0
     */
    public int getIntParam(String paramName) {
        String str = request.getParameter(paramName);
        if (StringUtil.isNull(str)) {
            return 0;
        } else {
            return Integer.valueOf(str);
        }
    }

    /**
     * 获取Long请求参数
     * <p>
     * 1、从请求域中获取参数信息
     * <p>
     * 2、判断值是否存在
     * <p>
     * 2.1、值不存在返回0
     * <p>
     * 2.2、存在进行转换为整形,数据不合法将抛出{@link NumberFormatException}异常
     * <p>
     * 3、返回转换后的数据
     * @param paramName 参数名称
     * @return 处理结果， NUll时返回0,否则返回转换结果
     */
    public long getLongParam(String paramName) {
        String str = request.getParameter(paramName);
        if (StringUtil.isNull(str)) {
            return 0;
        } else {
            return Long.valueOf(str);
        }
    }

    /**
     * 默认日期格式获取绑定对象
     * <p>
     * @param <T> 绑定对象类型
     * @param bindClass 绑定对象类型
     * @return 非NULL的绑定对象
     */
    public <T> T getBindObject(Class<T> bindClass) {
        // 方法开始日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【getBindObject】开始bindClass=" + bindClass);
        }
        T bindObject = (T) BeanUtils.instantiateClass(bindClass);
        // 执行数据绑定
        ServletRequestDataBinder webRequestDataBinder = new ServletRequestDataBinder(bindObject);
        // 默认日期格式绑定
        webRequestDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), true));
        webRequestDataBinder.bind(request);
        // 方法开始结束
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【getBindObject】结束bindObject=" + bindObject);
        }
        // 返回处理结果
        return bindObject;
    }

    /**
     * 获取请求域内的属性键值
     * @param attrKey 属性键值
     * @return 请求域属性
     */
    public Object getAttrObject(String attrKey) {
        return request.getAttribute(attrKey);
    }

    /**
     * 自定义哥特式获取绑定对象
     * <p>
     * @param <T> 绑定对象类型
     * @param bindClass 绑定对象类型
     * @param registerCustomEditor 自定义类型转换
     * @return 非NULL的绑定对象
     */
    @SuppressWarnings("rawtypes")
    public <T> T getBindObject(Class<T> bindClass, Map<Class, PropertyEditor> registerCustomEditor) {
        // 方法开始日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【getBindObject】开始bindClass=" + bindClass);
        }
        T bindObject = (T) BeanUtils.instantiateClass(bindClass);
        // 执行数据绑定
        ServletRequestDataBinder webRequestDataBinder = new ServletRequestDataBinder(bindObject);
        // 默认日期格式绑定
        if (registerCustomEditor != null) {
            Iterator<Class> it = registerCustomEditor.keySet().iterator();
            while (it.hasNext()) {
                Class clazz = it.next();
                PropertyEditor propertyEditor = registerCustomEditor.get(clazz);
                webRequestDataBinder.registerCustomEditor(clazz, propertyEditor);
            }
        }
        webRequestDataBinder.bind(request);
        // 方法开始结束
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【getBindObject】结束bindObject=" + bindObject);
        }
        // 返回处理结果
        return bindObject;
    }

//    /**
//     * 获取绑定List对象
//     * <p>
//     * @param <T>
//     * @param <T> 绑定对象类型
//     * @param bindClass 绑定对象类型
//     * @return 非NULL的绑定对象
//     */
//    public <T> List<T> getBindObjectList(Class<T> bindClass) {
//        // 方法开始日志
//        if (logger.isDebugEnabled()) {
//            logger.debug("调用方法【getBindObjectList】开始bindClass=" + bindClass);
//        }
//        List<T> list = null;
//        BeanToList<T> btl = new BeanToList<T>();
//        list = btl.getBindObjectList(request, bindClass);
//        // 方法开始结束
//        if (logger.isDebugEnabled()) {
//            logger.debug("调用方法【getBindObjectList】结束");
//        }
//        // 返回处理结果
//        return list;
//    }

    /**
     * 解析页面form表单存储成Map
     * @param request 请求信息
     * @return 转换后处理结果 paramMap --- 取值方法 paramMap.get("property");
     */
    public Map<String, Object> getRequestMap(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        Map<String, String[]> reqParamMap = request.getParameterMap();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (reqParamMap != null) {
            Iterator<String> it = reqParamMap.keySet().iterator();
            while (it.hasNext()) {
                String keyName = it.next();
                String[] listValue = reqParamMap.get(keyName);
                if (listValue.length == 1) {
                    paramMap.put(keyName, listValue[0]);
                } else {
                    paramMap.put(keyName, listValue);
                }
            }
        }
        return paramMap;
    }
}
