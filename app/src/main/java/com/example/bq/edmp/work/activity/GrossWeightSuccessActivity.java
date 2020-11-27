package com.example.bq.edmp.work.activity;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;

import butterknife.BindView;

public class GrossWeightSuccessActivity extends BaseTitleActivity {
    private CountDownTimer timer;
    @BindView(R.id.tv_time)
    TextView mTvTime;//倒计时按钮

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gross_weight_success;
    }

    @Override
    protected void initView() {
        StartTimer();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void otherViewClick(View view) {

    }
    //页面关闭倒计时
    private void StartTimer() {
        /** 倒计时3秒，一次1秒 */
        // TODO Auto-generated method stub
        timer = new CountDownTimer(3 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                mTvTime.setText(millisUntilFinished / 1000+"秒后自动返回首页");
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();
    }
}