package com.example.bq.edmp.work.grainmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.ToastUtil;

import butterknife.BindView;

public class NewAcquisitionsSuccessActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, NewAcquisitionsSuccessActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    private String id = "";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_acquisitions_success;
    }

    @Override
    protected void initView() {
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
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
        switch (view.getId()){
            case R.id.tv_submit:
                PrinterSettingActivity.newIntent(getApplicationContext(),"1",id);
                finish();
                break;
        }
    }
}