package com.example.bq.edmp.work.marketing.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.PayInfoDetailAct;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.ToastUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CustomerDetailsActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String type) {
        Intent intent = new Intent(context, CustomerDetailsActivity.class);
        intent.putExtra(Constant.TYPE, type);
        context.startActivity(intent);
    }

    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.btn_del)
    TextView mBtnDel;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;//余额
    @BindView(R.id.tv_sales_contract)
    TextView mTvSalesContract;//销售合同
    @BindView(R.id.tv_name)
    TextView mTvName;//客户名称
    @BindView(R.id.tv_distribution_area)
    TextView mTvDistributionArea;//经销区域
    @BindView(R.id.tv_contacts)
    TextView mTvContacts;//联系人
    @BindView(R.id.tv_contact_information)
    TextView mTvContactInformation;//联系方式
    @BindView(R.id.tv_contact_address)
    TextView mTvContactAddress;//联系地址
    @BindView(R.id.tv_remarks)
    TextView mTvRemarks;//备注
    @BindView(R.id.tv_license_number)
    TextView mTvLicenseNumber;//执照编号
    @BindView(R.id.img_photo)
    ImageView ImgPhoto;//执照图片
    @BindView(R.id.ly_bottom)
    LinearLayout mLyBottom;//底部按钮父布局
    private String type = "";

    private List<LocalMedia> picList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("客户详情");
        type = getIntent().getStringExtra(Constant.TYPE);
        if ("".equals(type)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        if ("2".equals(type)) {
            mLyBottom.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTvSubmit.setOnClickListener(this);
        mBtnDel.setOnClickListener(this);
        mTvBalance.setOnClickListener(this);
        mTvSalesContract.setOnClickListener(this);
        ImgPhoto.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                Intent intent = new Intent(getApplicationContext(), EditCustomerDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_balance:
                CustomerAccountActivity.newIntent(getApplicationContext(), "2");
                break;
            case R.id.tv_sales_contract:
                Intent intent2 = new Intent(getApplicationContext(), SalesContractListActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_del:
                ToastUtil.setToast("删除成功");
                break;
            case R.id.img_photo:
                picList = new ArrayList<LocalMedia>();
                LocalMedia localMedia = new LocalMedia();
                localMedia.setPath("");
                picList.add(localMedia);
                PictureSelector.create(CustomerDetailsActivity.this).themeStyle(R.style.picture_QQ_style).openExternalPreview(0, picList);
                break;

        }
    }
}