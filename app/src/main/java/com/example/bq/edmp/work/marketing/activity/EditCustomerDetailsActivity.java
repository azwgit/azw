package com.example.bq.edmp.work.marketing.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;

public class EditCustomerDetailsActivity extends BaseTitleActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_customer_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("修改");
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
}