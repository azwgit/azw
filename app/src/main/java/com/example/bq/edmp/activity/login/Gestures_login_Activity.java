package com.example.bq.edmp.activity.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.utils.SpUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;

/*
* 手势登录页面
* */
public class Gestures_login_Activity extends AppCompatActivity {

    private GestureLockView glv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestures_login_);

        initView();
    }

    private void initView() {
        glv = findViewById(R.id.glv);
        TextView gd_tv = findViewById(R.id.gd_tv);
        gd_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findContentViews2(view);
            }
        });

        glv.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {
                if (TextUtils.isEmpty(result)) {
                    return;
                }
                if (result.length() >= 4) {
                    String shou = (String) SpUtils.get("shou", "");
                    if (shou.equals("")) {
                        SpUtils.put("shou", result);
                        glv.showErrorStatus(600);
                        ToastUtil.setToast("设置密码成功");
                    } else {
                        if (shou.equals(result)) {
                            ToastUtil.setToast("解锁成功");
                            glv.showErrorStatus(600);
                            Vibrator vibrator = (Vibrator)Gestures_login_Activity.this.getSystemService(Gestures_login_Activity.VIBRATOR_SERVICE);
                            vibrator.vibrate(100);
                        } else {
                            ToastUtil.setToast("密码不正确");
                            glv.showErrorStatus(600);
                            Vibrator vibrator = (Vibrator)Gestures_login_Activity.this.getSystemService(Gestures_login_Activity.VIBRATOR_SERVICE);
                            vibrator.vibrate(100);
                        }
                    }
                } else {
                    ToastUtil.setToast("最少选四个");
                    glv.showErrorStatus(600);
                    Vibrator vibrator = (Vibrator)Gestures_login_Activity.this.getSystemService(Gestures_login_Activity.VIBRATOR_SERVICE);
                    vibrator.vibrate(100);
                }

            }
        });
    }

    /*
   * 底部跳框
   * */
    private void findContentViews2(View view) {
        final Dialog mCameraDialog = new Dialog(view.getContext(), R.style.my_dialog);

        View root = LayoutInflater.from(view.getContext()).inflate(R.layout.gestures_dialog, null);

        mCameraDialog.setContentView(root);
        mCameraDialog.setCanceledOnTouchOutside(false);

        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.BottomDialog_Animation); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//      lp.alpha = 9f; // 透明度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();

        TextView passwordlogin_tv = root.findViewById(R.id.passwordlogin_tv);
        TextView authcodelogin_tv = root.findViewById(R.id.authcodelogin_tv);
        TextView sllogin_tv = root.findViewById(R.id.sllogin_tv);
        TextView cancel_tv = root.findViewById(R.id.cancel_tv);

        //密码登录
        passwordlogin_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Gestures_login_Activity.this, LoginActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                mCameraDialog.dismiss();
            }
        });
        //验证码登录
        authcodelogin_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Gestures_login_Activity.this, LoginActivity.class);
                intent.putExtra("type", 0);
                startActivity(intent);
                mCameraDialog.dismiss();
            }
        });
        //刷脸登录
        sllogin_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.setToast("刷脸登录");
                mCameraDialog.dismiss();
            }
        });
        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCameraDialog.dismiss();
            }
        });

    }

}
