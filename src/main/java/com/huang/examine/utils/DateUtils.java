package com.huang.examine.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: HuangJunHao
 * @Date: 2020/4/19 13:48
 */
public class DateUtils {
    public static Date LocalDateToDate(String localDate){
        try{
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);
        StringBuffer dateTime = new StringBuffer(localDate);
        dateTime.replace(10,11," ");
        Date date = simpleDateFormat.parse(String.valueOf(dateTime));
        return date;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
