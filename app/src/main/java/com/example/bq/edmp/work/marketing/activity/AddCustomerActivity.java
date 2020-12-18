package com.example.bq.edmp.work.marketing.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;

import butterknife.BindView;

public class AddCustomerActivity extends BaseTitleActivity {
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_customer;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("新增客户");
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
                Intent intent = new Intent(getApplicationContext(), CustomerDetailsActivity.class);
                startActivity(intent);
                break;
        }
    }
}