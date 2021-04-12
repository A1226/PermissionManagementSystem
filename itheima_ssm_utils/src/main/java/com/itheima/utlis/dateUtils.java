package com.itheima.utlis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 转换日期工具类
 */
public class dateUtils {

    /**
     * 日期转字符串
     * @param date
     * @param putt
     * @return
     */
    public static String date2String(Date date,String putt){
        SimpleDateFormat format = new SimpleDateFormat();
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转日期
     * @param str
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String str,Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date parse = sdf.parse(str);
        return parse;
    }

}
