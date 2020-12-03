package com.example.bq.edmp.work.grainmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.DialoggerFinsh;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.grainmanagement.api.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.bean.UnloadingVerificationBean;

import butterknife.BindView;

public class UnloadingVerificationActivity extends BaseTitleActivity {
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;//提交按钮
    @BindView(R.id.tv_contractor)
    TextView mTvContractor;//承包人
    @BindView(R.id.tv_varieties)
    TextView mTvVarieties;//品种
    @BindView(R.id.tv_warehouse)
    TextView mTvWarehouse;//仓库
    @BindView(R.id.tv_gross_weight)
    TextView mTvGrossWeight;//毛重


    private CountDownTimer timer;
    private long time;

    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, UnloadingVerificationActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    private String id = "";
    private DialoggerFinsh dialog = null;
    private ILoadingView loading_dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_unloading_verification_weight;
    }

    @Override
    protected void initView() {
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(id)) {
            ToastUtil.setToast("参数错误");
            finish();
        }
        txtTabTitle.setText("卸货验证");
        loading_dialog = new LoadingDialog(this);
        ProApplication.getinstance().addActivity(UnloadingVerificationActivity.this);
        getUnloadingVerification();
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
                finish();
                break;
        }

    }

    public void showUsualDialog() {
        dialog = DialoggerFinsh.Builder(this)
                .setTitle("未称重！")
                .setMessage("3秒后自动返回首页")
                .build()
                .shown();
        StartTimer();
    }

    //页面关闭倒计时
    private void StartTimer() {
        /** 倒计时3秒，一次1秒 */
        // TODO Auto-generated method stub
        timer = new CountDownTimer(4 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
//                mHandler.sendEmptyMessage(0);
                dialog.getTvMessage().setText(millisUntilFinished / 1000 + "秒后自动返回首页");
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();
    }

    private void setData(UnloadingVerificationBean.DataBean bean) {
        mTvContractor.setText(bean.getFarmerName());
        mTvVarieties.setText(bean.getVarietyName());
        mTvWarehouse.setText(bean.getWarehouseName());
        mTvGrossWeight.setText(MoneyUtils.formatMoney(bean.getGrossWeight()) + " 公斤");
        if(bean.getGrossWeight()==0){
            showUsualDialog();
        }
    }

    //获取卸货信息
    private void getUnloadingVerification() {
        String sign = MD5Util.encode("code=" + id);
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .getUnloadingVerificationInfo(id, sign)
                .compose(Transformer.<UnloadingVerificationBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<UnloadingVerificationBean>() {
                    @Override
                    protected void onError(String errorMsg) {

                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(UnloadingVerificationBean bean) {
                        if (bean.getCode() == 200) {
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            finish();
                        }
                    }
                });
    }
}