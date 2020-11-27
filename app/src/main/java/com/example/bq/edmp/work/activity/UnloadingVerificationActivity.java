package com.example.bq.edmp.work.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.Dialogger;
import com.example.bq.edmp.utils.DialoggerFinsh;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.UsualDialogger;
import com.luck.picture.lib.tools.ScreenUtils;

import butterknife.BindView;

public class UnloadingVerificationActivity extends BaseTitleActivity {
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;//提交按钮
    private CountDownTimer timer;
    private long time;

    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, UnloadingVerificationActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }
//    private Handler mHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            // TODO Auto-generated method stub
//            int what = msg.what;
//            if (what == 0) {	//update
//                dialog.setTitle(time+"");
//            }
//        }
//    };

    private String id = "";
    private DialoggerFinsh dialog = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gross_weight;
    }

    @Override
    protected void initView() {
        id = getIntent().getStringExtra(Constant.ID);
        txtTabTitle.setText("卸货验证");
        ProApplication.getinstance().addActivity(UnloadingVerificationActivity.this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTvSubmit.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                showUsualDialog();
                break;
        }

    }

    public void showUsualDialog() {
        dialog = DialoggerFinsh.Builder(this)
                .setTitle("友情提示")
                .setMessage("3秒后自动返回首页")
                .build()
                .shown();
        StartTimer();
    }

    //页面关闭倒计时
    private void StartTimer() {
        /** 倒计时3秒，一次1秒 */
        // TODO Auto-generated method stub
        timer = new CountDownTimer(4 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
//                mHandler.sendEmptyMessage(0);
                dialog.getTvMessage().setText(millisUntilFinished/1000+"秒后自动返回首页");
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();
    }
}