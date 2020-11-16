package com.example.bq.edmp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    //根据输入格式 输出格式化时间
    public static String getTime(Date date,String formatString) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        return format.format(date);
    }
}
