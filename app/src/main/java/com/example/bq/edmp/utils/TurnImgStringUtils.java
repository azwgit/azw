package com.example.bq.edmp.utils;

/**
 * Created by bq on 2020/11/18.
 * <p>
 * 图片路径斜杠转
 */
public class TurnImgStringUtils {
    public static String isImgPath(String str) {
        String fign = "";
        if (str == null || str.equals("")) {
            fign = "";
        } else {
            fign = str.replace("\\", "/");
        }

        return fign;
    }
}
