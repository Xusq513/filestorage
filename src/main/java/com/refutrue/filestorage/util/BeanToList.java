package com.refutrue.filestorage.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

/**
 * <p/>
 * 页面request请求绑定为List对象实现
 * <p/>
 *
 * @author zhangsf 创建 2012-2-16
 * @version 1.0 Copyright(c) 北京神州数码思特奇信息技术股份有限公司
 */

public class BeanToList<T> {

    private static final Log logger = LogFactory.getLog(BeanToList.class);

    private List<T> JavaBeanList = new ArrayList<T>();

    /**
     * @param request   服务请求
     * @param bindClass 绑定对象类型Class
     * @return List对象
     */
    public List<T> getBindObjectList(HttpServletRequest request, Class<T> bindClass) throws Exception{

        Map<String, String[]> map = new HashMap<String, String[]>();
        List<Integer> countList = new ArrayList<Integer>();

        Field[] fields = bindClass.getDeclaredFields(); // 得到所有属性
        if (fields != null) {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();

                // 从request中取得对应的fieldName
                String[] paraVlaues = request.getParameterValues(fieldName);
                if (paraVlaues != null) {
                    int count = paraVlaues.length;
                    map.put(fieldName, paraVlaues);
                    countList.add(count);
                }
            }
        }
        this.analyzeMap(map, Collections.max(countList), bindClass);

        return this.getJavaBeanList();
    }

    /**
     * 解析Map
     *
     * @param map       map对象
     * @param count     解析参数
     * @param bindClass 绑定Class对象
     */
    public void analyzeMap(Map<String, String[]> map, int count, Class<T> bindClass) throws Exception{

        for (int i = 0; i < count; i++) {
                T InstanceBean = (T) bindClass.newInstance(); // 实例化一个bindClass
                Iterator<Entry<String, String[]>> iter1 = map.entrySet().iterator();
                while (iter1.hasNext()) {
                    Entry<String, String[]> entry1 = (Entry<String, String[]>) iter1.next();
                    String propertyName = (String) entry1.getKey();
                    String propertValue = ((String[]) entry1.getValue())[i].trim();
                    this.setProperty(InstanceBean, propertyName, propertValue);
                }
                JavaBeanList.add(InstanceBean);

        }
    }

    /**
     * java反射 动态set获取的值
     *
     * @param obj          赋值对象
     * @param propertyName 属性名称
     * @param value        属性值
     */
    public void setProperty(Object obj, String propertyName, Object value) throws Exception{

        Method setMethod = null;
        StringBuffer sb = new StringBuffer();
        // 反射得到clazz
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields(); // 得到所有属性

        if (fields != null) {
            // 将传进来的入参和javabean中的入参进行比对 ,如果相同 , 则调用set方法进行赋值
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
                if (propertyName.equals(fieldName)) {
                    Object methodEnd = fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1); // set方法拼装
                    Object methodName = sb.append("set" + methodEnd); // set方法拼装
                        setMethod = clazz.getDeclaredMethod(methodName.toString(),
                                new Class[]{field.getType()}); // 得到相应的set方法
                        Object rValue = this.stringToType(value.toString(), field.getType());
                        setMethod.invoke(obj, new Object[]{rValue}); // 调用set方法将传入的value值保存属性中去
                }
            }
        }
    }

    /**
     * String类型转换
     *
     * @param value 字符串值
     * @param type  转换类型
     * @return obj对象
     */
    private Object stringToType(String value, Class type) throws Exception{
        Object rValue = null;
        if (type.equals(Integer.TYPE) || type.equals(Integer.class)) {
            rValue = Integer.parseInt(value); // String 转为 int 型
        } else if (type.equals(Long.TYPE) || type.equals(Long.class)) {
            rValue = Long.parseLong(value); // String 转为 long 型
        } else if (type.equals(Short.TYPE) || type.equals(Short.class)) {
            rValue = Short.parseShort(value); // String 转为 short 型
        } else if (type.equals(Boolean.TYPE) || type.equals(Boolean.class)) {
            rValue = Boolean.parseBoolean(value); // String 转为 boolean 型
        } else if (type.equals(Float.TYPE) || type.equals(Float.class)) {
            rValue = Float.parseFloat(value); // String 转为 float 型
        } else if (type.equals(Double.TYPE) || type.equals(Double.class)) {
            rValue = Double.parseDouble(value); // String 转为 double 型
        } else if (type.equals(Character.TYPE) || type.equals(Character.class)) {

        } else if (type.equals(Byte.TYPE) || type.equals(Byte.class)) {
            rValue = Byte.parseByte(value); // String 转为 byte 型
        } else if (type.equals(Date.class)) {
            rValue = DateUtil.toDateYmdHms(value);
        } else if (type.equals(java.sql.Date.class)) {
            java.sql.Date sd = new java.sql.Date(1);
            sd.setTime(DateUtil.toDateYmdHms(value).getTime());
            rValue = sd;
        } else {
            rValue = value;
        }
        return rValue;
    }

    /**
     * List对象get方法
     *
     * @return List
     */
    protected List<T> getJavaBeanList() {
        return JavaBeanList;
    }

}
