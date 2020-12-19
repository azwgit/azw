package com.example.bq.edmp.work.marketing.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;

import butterknife.BindView;

public class EditCustomerDetailsActivity extends BaseTitleActivity {
    @BindView(R.id.tv_name)
    EditText mTvName;//客户名称
    @BindView(R.id.tv_distribution_area)
    TextView mTvDistributionArea;//经销区域
    @BindView(R.id.tv_contacts)
    EditText mTvContacts;//联系人
    @BindView(R.id.tv_contact_information)
    EditText mTvContactInformation;//联系方式
    @BindView(R.id.tv_contact_address)
    EditText mTvContactAddress;//联系地址
    @BindView(R.id.tv_remarks)
    EditText mTvRemarks;//备注
    @BindView(R.id.tv_license_number)
    TextView mTvLicenseNumber;//执照编号

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