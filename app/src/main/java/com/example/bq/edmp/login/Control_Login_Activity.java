package com.example.bq.edmp.login;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.mine.activty.ModificationPasswordActivity;
import com.example.bq.edmp.utils.SpUtils;

import butterknife.BindView;

/*
 * 登录管理
 * */
public class Control_Login_Activity extends BaseActivity {

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
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;


    private boolean SHOUSHI = false;
    private boolean SHUALIAN = false;


    /*
     * 手势登录 SHOUSHILOGIN   刷脸登录  SHUALIAN
     * */

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        password_login.setOnClickListener(this);
        swiping_login.setOnClickListener(this);
        gesture_login.setOnClickListener(this);
        return_img.setOnClickListener(this);
    }


    @Override
    protected void initView() {
        title_tv.setText("登录管理");
        Boolean shoushi = (Boolean) SpUtils.get("shoushi", false);
        Boolean shualian = (Boolean) SpUtils.get("shualian", false);
        if (shoushi) {
            gesture_login_tv.setText("已启动");
            swiping_login_tv.setText("未启动");
        } else if (shualian) {
            gesture_login_tv.setText("未启动");
            swiping_login_tv.setText("已启动");
        } else {
            gesture_login_tv.setText("未启动");
            swiping_login_tv.setText("未启动");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_control__login;
    }

    @Override
    protected void otherViewClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.password_login://密码登录
                startActivity(new Intent(Control_Login_Activity.this, ModificationPasswordActivity.class));
                break;
            case R.id.swiping_login://刷脸登录
                intent = new Intent(Control_Login_Activity.this, GestureoneActivity.class);
                intent.putExtra("text_one", "使用刷脸登录");
                intent.putExtra("text_two", "修改刷脸密码");
                intent.putExtra("text_title", "设置刷脸密码");
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.gesture_login://手势密码
                intent = new Intent(Control_Login_Activity.this, GestureoneActivity.class);
                intent.putExtra("text_one", "使用手势密码登录");
                intent.putExtra("text_two", "修改手势密码");
                intent.putExtra("text_title", "设置手势密码");
                intent.putExtra("type", 2);
                startActivity(intent);

//                String shoushilogin = (String) SpUtils.get("SHOUSHILOGIN", "");
//                if (shoushilogin.equals("") || shoushilogin == null) {
//                    intent = new Intent(Control_Login_Activity.this, GestureSettingLoginActivity.class);
//                    startActivityForResult(intent, 250);
//                } else {
//                    if (SHOUSHI) {
//                        SHOUSHI = false;
//                        SHUALIAN = false;
//                        gesture_login_tv.setText("未启动");
//                        swiping_login_tv.setText("未启动");
//                        SpUtils.put("shoushi", false);
//                        SpUtils.put("shualian", false);
//                    } else {
//                        SHOUSHI = true;
//                        SHUALIAN = false;
//                        gesture_login_tv.setText("已启动");
//                        swiping_login_tv.setText("未启动");
//                        SpUtils.put("shoushi", true);
//                        SpUtils.put("shualian", false);
//                    }
//                }
//                intent = new Intent(Control_Login_Activity.this, Gestures_login_Activity.class);
//                startActivityForResult(intent, 250);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250 && resultCode == 100) {
            boolean ss = data.getBooleanExtra("ss", false);
            if (ss) {
                SHOUSHI = true;
                SHUALIAN = false;
                gesture_login_tv.setText("已启动");
                swiping_login_tv.setText("未启动");
                SpUtils.put("shoushi", true);
                SpUtils.put("shualian", false);
            } else {
                SHOUSHI = false;
                SHUALIAN = false;
                gesture_login_tv.setText("未启动");
                swiping_login_tv.setText("未启动");
                SpUtils.put("shoushi", false);
                SpUtils.put("shualian", false);
            }
        }
    }
}
