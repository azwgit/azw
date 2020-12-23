package com.example.bq.edmp.work.marketing.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.PayInfoDetailAct;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.mine.activty.Message_Activity;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.TurnImgStringUtils;
import com.example.bq.edmp.utils.UsualDialogger;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;
import com.example.bq.edmp.work.marketing.api.CustomerManagementApi;
import com.example.bq.edmp.work.marketing.bean.CustomerDetailsBean;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CustomerDetailsActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id, String type) {
        Intent intent = new Intent(context, CustomerDetailsActivity.class);
        intent.putExtra(Constant.ID, id);
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
    private String id = "";
    private UsualDialogger dialog = null;
    private List<LocalMedia> picList;
    private ILoadingView loading_dialog;
    private CustomerDetailsBean customerDetailsBean = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("客户详情");
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        id = getIntent().getStringExtra(Constant.ID);
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
    protected void onResume() {
        super.onResume();
        getCustomerDetails();
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
                EditCustomerDetailsActivity.newIntent(getApplicationContext(), id);
                break;
            case R.id.tv_balance:
                CustomerAccountActivity.newIntent(getApplicationContext(), id,"1");
                break;
            case R.id.tv_sales_contract:
                ToastUtil.setToast("功能暂未开通");
//                Intent intent2 = new Intent(getApplicationContext(), SalesContractListActivity.class);
//                startActivity(intent2);
                break;
            case R.id.btn_del:
                if (customerDetailsBean.getData().getBalance() >0) {
                    ToastUtil.setToast("提示“该客户有账户余额，不可删除");
                    return;
                }
                showDeleteDialog();
                break;
            case R.id.img_photo:
                PictureSelector.create(CustomerDetailsActivity.this).themeStyle(R.style.picture_QQ_style).openExternalPreview(0, picList);
                break;

        }
    }

    //刪除提示dialog
    public void showDeleteDialog() {
        dialog = UsualDialogger.Builder(this)
                .setTitle("友情提示")
                .setMessage("确认要删除此用户，删除后将不可恢复")
                .setOnConfirmClickListener("确定", new UsualDialogger.onConfirmClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteCustomer();
                    }
                })
                .setOnCancelClickListener("取消", new UsualDialogger.onCancelClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                })
                .build()
                .shown();
    }

    //获取客户详情
    private void getCustomerDetails() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(CustomerManagementApi.class)
                .getCustomerDetails(id, sign)
                .compose(Transformer.<CustomerDetailsBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<CustomerDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CustomerDetailsBean bean) {
                        if (bean.getCode() == 200) {
                            customerDetailsBean=bean;
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //详情赋值
    private void setData(CustomerDetailsBean.DataBean bean) {
        mTvBalance.setText("￥" + MoneyUtils.formatMoney(bean.getBalance()));
        mTvName.setText(bean.getName());
        mTvDistributionArea.setText(bean.getRegion());
        mTvContacts.setText(bean.getContacts());
        mTvContactInformation.setText(bean.getMobTel());
        mTvContactAddress.setText(bean.getContactAddress());
        mTvRemarks.setText(bean.getRemark());
        mTvLicenseNumber.setText(bean.getBusinessLicenseNumber());
        Glide.with(CustomerDetailsActivity.this)
                .load(BaseApi.marketing_img_url + TurnImgStringUtils.isImgPath(bean.getBusinessLicense()))
                .apply(new RequestOptions()
                        .dontAnimate()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(ImgPhoto);
        picList = new ArrayList<LocalMedia>();
        LocalMedia localMedia = new LocalMedia();
        localMedia.setPath(BaseApi.marketing_img_url + TurnImgStringUtils.isImgPath(bean.getBusinessLicense()));
        picList.add(localMedia);
    }

    //删除客户
    private void deleteCustomer() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(CustomerManagementApi.class)
                .deleteCustomer(id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("删除成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }
}