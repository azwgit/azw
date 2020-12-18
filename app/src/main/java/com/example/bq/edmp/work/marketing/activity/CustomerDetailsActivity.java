package com.example.bq.edmp.work.marketing.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.PayInfoDetailAct;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CustomerDetailsActivity extends BaseTitleActivity {
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;//余额
    @BindView(R.id.tv_sales_contract)
    TextView mTvSalesContract;//销售合同
    private List<LocalMedia> picList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("客户详情");
//        picList = new ArrayList<LocalMedia>();
//        LocalMedia localMedia = new LocalMedia();
//        localMedia.setPath("");
//        picList.add(localMedia);
//        PictureSelector.create(CustomerDetailsActivity.this).themeStyle(R.style.picture_QQ_style).openExternalPreview(0, picList);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTvSubmit.setOnClickListener(this);
        mTvBalance.setOnClickListener(this);
        mTvSalesContract.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                Intent intent = new Intent(getApplicationContext(), EditCustomerDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_balance:
                Intent intent1=new Intent(getApplicationContext(),CustomerAccountActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_sales_contract:
                Intent intent2=new Intent(getApplicationContext(),SalesContractListActivity.class);
                startActivity(intent2);
                break;

        }
    }
}