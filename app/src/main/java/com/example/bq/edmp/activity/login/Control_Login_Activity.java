package com.example.bq.edmp.activity.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.SpUtils;

import butterknife.BindView;

/*
* 登录管理
* */
public class Control_Login_Activity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.password_login)
    RelativeLayout password_login;
    @BindView(R.id.swiping_login)
    RelativeLayout swiping_login;
    @BindView(R.id.gesture_login)
    RelativeLayout gesture_login;
    @BindView(R.id.swiping_login_tv)
    TextView swiping_login_tv;
    @BindView(R.id.gesture_login_tv)
    TextView gesture_login_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control__login_);
        initView();
        initMonitor();
    }

    private void initMonitor() {
        password_login.setOnClickListener(this);
        swiping_login.setOnClickListener(this);
        gesture_login.setOnClickListener(this);
    }

    private void initView() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.password_login:

                break;
            case R.id.swiping_login:

                break;
            case R.id.gesture_login:
                String gesture = (String) SpUtils.get("gesture", "");
                if (!gesture.equals("")) {
                    SpUtils.put("loginway", "gesturelogin");
                    swiping_login_tv.setText("未启动");
                    gesture_login_tv.setText("已启动");
                } else {
                    intent = new Intent(Control_Login_Activity.this, Gestures_login_Activity.class);
                    startActivityForResult(intent, 250);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250) {

        }
    }
}
