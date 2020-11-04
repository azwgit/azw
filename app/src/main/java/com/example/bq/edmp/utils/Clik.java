package com.example.bq.edmp.utils;

/**
 * Created by tl on 2018/7/25.
 */

public class Clik {
    private static final int MIN_DELAY_TIME= 300;  // 两次点击间隔不能少于1000ms
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
//            ToastUtil.setToast("请勿重复点击");
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }
}
