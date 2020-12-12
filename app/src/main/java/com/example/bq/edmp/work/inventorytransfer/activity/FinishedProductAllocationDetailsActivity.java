package com.example.bq.edmp.work.inventorytransfer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;

public class FinishedProductAllocationDetailsActivity extends BaseTitleActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_finished_product_allocation_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("成品调拨单详情");
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