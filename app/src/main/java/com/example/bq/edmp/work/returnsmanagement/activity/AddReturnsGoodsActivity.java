package com.example.bq.edmp.work.returnsmanagement.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;

import butterknife.BindView;

public class AddReturnsGoodsActivity extends BaseTitleActivity {
    @BindView(R.id.rl_customer)
    RelativeLayout mRlCustomer;//选择客户父布局
    @BindView(R.id.rl_packing)
    RelativeLayout mRlPacking;//品种包装父布局
    @BindView(R.id.btn_select)
    TextView mBtnSelect;//查询发货单
    @BindView(R.id.tv_customer)
    TextView mTvCustomer;//选择客户
    @BindView(R.id.tv_packing)
    TextView mTvPacking;//选择品种

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_returns_goods;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("新增退单");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mRlCustomer.setOnClickListener(this);
        mRlPacking.setOnClickListener(this);
        mBtnSelect.setOnClickListener(this);

    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.rl_customer:
                //选择客户父布局
                break;
            case R.id.rl_packing:
                //品种包装父布局
                break;
            case R.id.btn_select:
                //查询发货单
                DeliverGoodsListActivity.newIntent(getApplicationContext());
                break;
        }
    }
}