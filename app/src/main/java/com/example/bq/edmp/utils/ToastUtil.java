package com.example.bq.edmp.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.example.bq.edmp.ProApplication;


/**
 * Created by gaosheng on 2016/12/1.
 * 23:34
 * com.example.gaosheng.myapplication.utils
 */

public class ToastUtil {
    public static Toast toast;

    public static void setToast(String str) {

        if (toast == null) {
            toast = Toast.makeText(ProApplication.getmContext(), str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }
        public static void show(Context context, String text) {
            try {
                if(toast!=null){
                    toast.setText(text);
                }else{
                    toast= Toast.makeText(context, text, Toast.LENGTH_SHORT);
                }
                toast.show();
            } catch (Exception e) {
                //解决在子线程中调用Toast的异常情况处理
                Looper.prepare();
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }

}
