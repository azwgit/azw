package com.example.bq.edmp.work.inventorytransfer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.base.BaseTitleActivity;

public class AddTransferGoodsActivity extends BaseTitleActivity {


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        txtTabTitle.setText("添加商品");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_transfer_goods;
    }

    @Override
    protected void otherViewClick(View view) {

    }
}