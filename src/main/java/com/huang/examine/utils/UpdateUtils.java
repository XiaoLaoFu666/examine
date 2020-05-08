package com.huang.examine.utils;
/**
 * @Author: HuangJunHao
 * @Date: 2020/4/20 12:43
 */
public class UpdateUtils {

    public static String getExamNumber(){
        Long currentTime = System.currentTimeMillis();
        return String.valueOf(currentTime);
    }


}
