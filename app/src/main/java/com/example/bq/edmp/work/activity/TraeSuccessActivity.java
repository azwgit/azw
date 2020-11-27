package com.example.bq.edmp.work.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;

import butterknife.BindView;

public class TraeSuccessActivity extends BaseTitleActivity {
    @BindView(R.id.tv_printing)
    TextView mTvPrinting;//打印收购小票
    @Override
    protected int getLayoutId() {
        return R.layout.activity_trae_success;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTvPrinting.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.tv_printing:
                Intent intent =new Intent(TraeSuccessActivity.this,PrinterSettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}