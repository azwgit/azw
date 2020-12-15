package com.example.bq.edmp.utils;

import android.content.Context;

import com.example.bq.edmp.login.LoginActivity;

public class ActivityUtils {

    public static void getMsg(String errorMsg, Context context){
        if (errorMsg.equals("HTTP 401 ")) {
            LoginActivity.start(context);
            ToastUtil.setToast("资料过期请重新登录");
        }else{
            ToastUtil.setToast(errorMsg);
        }

    }
}
