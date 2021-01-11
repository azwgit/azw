package com.example.bq.edmp.work.returnsmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.ToastUtil;

import butterknife.BindView;

public class ApplyForRefundActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, ApplyForRefundActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }
    @BindView(R.id.btn_submit)
    TextView mBtnSubmit;//提交按钮
    @BindView(R.id.btn_save)
    TextView mBtnSave;//保存按钮
    @BindView(R.id.btn_del)
    TextView mBtnDel;//删除按钮
    private String id = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_for_refund;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("申请退单");
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
        mBtnSubmit.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mBtnDel.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                ReturnsGoodsHistoricalListActivity.newIntent(getApplicationContext());
                break;
            case R.id.btn_save:

                break;
            case R.id.btn_del:

                break;

        }
    }
}