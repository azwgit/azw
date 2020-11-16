package com.example.bq.edmp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.login.Gestures_login_Activity;
import com.example.bq.edmp.activity.login.LoginActivity;
import com.example.bq.edmp.utils.SpUtils;
import com.example.bq.edmp.utils.ToastUtil;

/*
* 启动页
* */
public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        final String token = (String) SpUtils.get("UserInfo", "");
        final Boolean shoushi = (Boolean) SpUtils.get("shoushi", false);
        final Boolean shualian = (Boolean) SpUtils.get("shualian", false);

        Integer time = 2000;    //设置等待时间，单位为毫秒
        Handler handler = new Handler();
        //当计时结束时，跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (token != null || !token.equals("")) {
                    if (shoushi) {
                        startActivity(new Intent(StartActivity.this, Gestures_login_Activity.class));
                    } else if (shualian) {
                        ToastUtil.setToast("刷脸登录");
                        startActivity(new Intent(StartActivity.this, MainActivity.class));
                    } else {
                        startActivity(new Intent(StartActivity.this, MainActivity.class));
                    }
                } else {
                    startActivity(new Intent(StartActivity.this, LoginActivity.class));
                }
                StartActivity.this.finish();
            }
        }, time);
    }
}
