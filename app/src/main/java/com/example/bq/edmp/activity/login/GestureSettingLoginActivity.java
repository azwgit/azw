package com.example.bq.edmp.activity.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.utils.SpUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;

import butterknife.BindView;

public class GestureSettingLoginActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.glv)
    GestureLockView gestureLockView;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;

    private String ONESTRING = "";
    private boolean TYPE = false;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        title_tv.setText("设置密码");

        gestureLockView.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {
                Vibrator vibrator = (Vibrator) GestureSettingLoginActivity.this.getSystemService(Gestures_login_Activity.VIBRATOR_SERVICE);
                if (TextUtils.isEmpty(result)) {
                    return;
                }
                if (result.length() >= 4) {
                    if (ONESTRING.equals("") || ONESTRING == null) {
                        ONESTRING = result;
                        tv.setVisibility(ViewGroup.VISIBLE);
                        tv.setText("请再次绘制");
                        TYPE = false;
                        gestureLockView.showErrorStatus(600);
                        vibrator.vibrate(100);
                    } else {
                        if (ONESTRING.equals(result)) {
                            tv.setText("确认");
                            TYPE = true;
                            gestureLockView.showErrorStatus(600);
                            showLai();
                        } else {
                            tv.setText("与上一次绘制不一致，请重新绘制");
                            TYPE = false;
                            gestureLockView.showErrorStatus(600);
                            vibrator.vibrate(100);
                        }
                    }
                } else {
                    ToastUtil.setToast("最少选四个");
                    gestureLockView.showErrorStatus(600);
                    vibrator.vibrate(100);
                }


            }
        });
    }

    private void showLai() {
        final AlertDialog alertDialog2 = new AlertDialog.Builder(this)
                .setTitle("温馨提示")
                .setMessage("手势密码设置成功")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SpUtils.put("SHOUSHILOGIN", ONESTRING);
                        Intent intent = new Intent();
                        intent.putExtra("ss", true);
                        setResult(100, intent);
                        fund();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SpUtils.put("SHOUSHILOGIN", "");
                        Intent intent = new Intent();
                        intent.putExtra("ss", false);
                        setResult(100, intent);
                        fund();
                    }
                })
                .create();
        alertDialog2.show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gesture_setting_login;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
        }
    }
}
