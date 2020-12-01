package com.example.bq.edmp.work.grainmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.Dialogger;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.grainmanagement.RawGrainManagementApi;

import butterknife.BindView;

public class TraeActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, TraeActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.tv_submit)
    TextView mTvSubmit;//提交按钮
    @BindView(R.id.et_trae)
    EditText mEtTrae;//重量
    @BindView(R.id.tv_contractor)
    TextView mTvContractor;//承包人
    @BindView(R.id.tv_varieties)
    TextView mTvVarieties;//品种
    @BindView(R.id.tv_warehouse)
    TextView mTvWarehouse;//仓库
    @BindView(R.id.tv_gross_weight)
    TextView mTvGrossWeight;//毛重

    private Dialogger dialog = null;
    private String id = "";
    private ILoadingView loading_dialog;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTvSubmit.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        txtTabTitle.setText("称重皮重");
        loading_dialog = new LoadingDialog(this);
        ProApplication.getinstance().addActivity(TraeActivity.this);
        getTraetDetail();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_trae;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                if ("".equals(mEtTrae.getText().toString().trim())) {
                    ToastUtil.setToast("请填写重量");
                    return;
                }
                showUsualDialog();
                break;
        }
    }

    public void showUsualDialog() {
        dialog = Dialogger.Builder(this)
                .setTitle("友情提示")
                .setMessage("确认当前收购品种毛重为1880公斤")
                .setOnConfirmClickListener("确定", new Dialogger.onConfirmClickListener() {
                    @Override
                    public void onClick(View view) {
                        TraeSuccessActivity.newIntent(getApplicationContext(), id);
                        finish();
//                        addGrossWeight(mEtTrae.getText().toString().trim());
                    }
                })
                .setOnCancelClickListener("取消", new Dialogger.onCancelClickListener() {
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

    //添加皮重
    private void addGrossWeight(String weight) {
        String sign = MD5Util.encode("id=" + id + "&tareWeight=" + weight);
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .addTrae(id, weight, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean loginBean) {
                        ToastUtil.setToast(loginBean.getMsg());
                    }
                });
    }

    //获取皮重详情
    private void getTraetDetail() {
        String sign = MD5Util.encode("id" + id);
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .getTraeDetail(id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean loginBean) {
                        ToastUtil.setToast(loginBean.getMsg());
                    }
                });
    }
}