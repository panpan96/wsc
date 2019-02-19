package com.youngor.wsc.common;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

    //将时间转换为指定格式
    public static String format(Date date, String format){

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
