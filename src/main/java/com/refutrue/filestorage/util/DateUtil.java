package com.refutrue.filestorage.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 * @author zhangsf
 *
 */
public final class DateUtil {

    /**
     * 将日期对象转换为yyyy-MM-dd格式字符串
     *
     * @param date 时间对象
     * @return yyyy-MM-dd格式字符串
     */
    public static String toStringYmdWthH(Date date) {
        return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
    }

    /**
     * 调整时间, 按照需要调整的单位调整时间
     * <p/>
     * <pre>
     * 例如:保留到日期的年change("20120511154440", DateUtil.YEAE, 2);返回：20140511154440<br/>
     * </pre>
     *
     * @param inDate 传入日志
     * @param unit   调整单位 年：1 ;月：2; 周：3; 日：5; 时：10; 分：12;秒：13
     * @param amount 调整数量
     * @return 调整后的日期
     */
    public static Date change(Date inDate, int unit, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inDate);
        calendar.add(unit, amount);
        return calendar.getTime();
    }

    /**
     * 将yyyyMMddHHmmss格式字符串转换为日期对象
     *
     * @param dateStr yyyyMMddHHmmss格式字符串
     * @return 日期对象
     */
    public static Date toDateYmd(String dateStr) {
         Date date = null;
        try {
            SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMdd");
            date = dfm.parse(dateStr);
        } catch (ParseException e) {
//            throw new SystemException("1", 1,
//                    "字符不是有效的【{yyyyMMddHHmmss}】日期格式");
        }
        return date;
    }

    /**
     * 将yyyyMMddHHmmss格式字符串转换为日期对象
     *
     * @param dateStr yyyyMMddHHmmss格式字符串
     * @return 日期对象
     */
    public static Date toDateYm(String dateStr) {
        Date date = null;
        try {
            SimpleDateFormat dfm = new SimpleDateFormat("yyyyMM");
            date = dfm.parse(dateStr);
        } catch (ParseException e) {
//            throw new SystemException("1", 1,
//                    "字符不是有效的【{yyyyMMddHHmmss}】日期格式");
        }
        return date;
    }
    /**
     * 将日期对象转换为yyyy-MM-dd HH:mm:ss格式字符串
     *
     * @param date 时间对象
     * @return yyyy-MM-dd HH:mm:ss格式字符串
     */
    public static String toStringYmdHmsWthH(Date date) {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
    }

    /**
     * 将日期对象转换为yyyyMMdd格式字符串
     *
     * @param date 时间对象
     * @return yyyyMMdd格式字符串
     */
    public static String toStringYmd(Date date) {
        return (new SimpleDateFormat("yyyyMMdd")).format(date);
    }

    /**
     * 将日期对象转换为yyyyMMdd格式字符串
     *
     * @param date 时间对象
     * @return yyyyMMdd格式字符串
     */
    public static String toStringYm(Date date) {
        return (new SimpleDateFormat("yyyyMM")).format(date);
    }

    /**
     * 将yyyyMMddHHmmss格式字符串转换为日期对象
     *
     * @param dateStr yyyyMMddHHmmss格式字符串
     * @return 日期对象
     */
    public static Date toDateYmdHms(String dateStr) throws Exception {
        try {
            SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
            return dfm.parse(dateStr);
        } catch (ParseException e) {
            throw new Exception("字符"+dateStr+"不是有效的【"+"】日期格式");
        }
    }

    /**
     * 获取某个日期段内所有月份的集合
     * @param start 开始日期 格式如：20130904，不可小于6位
     * @param end 结束日期 格式如：20130906，不可小于6位
     * @return List 月份集合
     */
    public static List<String> intervalMonthsList (String start, String end) {
        // 判断入参字符串长度是否大于等于六位
        if (start.length() >= 6 && end.length() >= 6) {
            start = start.substring(0, 4) + "-" + start.substring(4, 6);
            end = end.substring(0, 4) + "-" + end.substring(4, 6);
        } else {
//            System.out.println("日期入参有误，请修改入参日期字符串！");
        }
        String splitSign = "-";
        //判断YYYY-MM时间格式的正则表达式
        String regex = "\\d{4}" + splitSign + "(([0][1-9])|([1][012]))";
        if (!start.matches(regex) || !end.matches(regex)) ;
        List<String> list = new ArrayList<String>();
        //start大于end日期时，互换
        if (start.compareTo(end) > 0) {
            String temp = start;
            start = end;
            end = temp;
        }
        //从最小月份开始
        String temp = start;
        while (temp.compareTo(start) >= 0 && temp.compareTo(end) <= 0) {
            //首先加上最小月份,接着计算下一个月份
            list.add(temp.replace("-", ""));
            String[] arr = temp.split(splitSign);
            int year = Integer.valueOf(arr[0]);
            int month = Integer.valueOf(arr[1]) + 1;
            if (month > 12) {
                month = 1;
                year++;
            }
            //补0操作
            if (month < 10) {//补0操作
                temp=year+splitSign+"0"+month;
            } else {
                temp=year+splitSign+month;
            }
        }
        return list;
    }
}
