package com.example.bq.edmp.work.grainmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.grainmanagement.api.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.bean.GrossWeightBean;
import com.example.bq.edmp.work.grainmanagement.bean.TraeBean;

import butterknife.BindView;

public class TraeActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, TraeActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.tv_submit)
    TextView mTvSubmit;//提交按钮
    @BindView(R.id.tv_number)
    TextView mTvNumber;//收购单号
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
    private TraeBean traeBean;
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
                showUsualDialog(mEtTrae.getText().toString().trim());
                break;
        }
    }

    public void showUsualDialog(String weight) {
        dialog = Dialogger.Builder(this)
                .setTitle("友情提示")
                .setMessage("确认当前收购品种皮重为"+weight+"公斤")
                .setOnConfirmClickListener("确定", new Dialogger.onConfirmClickListener() {
                    @Override
                    public void onClick(View view) {
                        addTrae(mEtTrae.getText().toString().trim());
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
    private void setData(TraeBean.DataBean bean){
        mTvNumber.setText("收购单号  "+bean.getCode());
        mTvContractor.setText(bean.getFarmerName());
        mTvVarieties.setText(bean.getVarietyName());
        mTvWarehouse.setText(bean.getWarehouseName());
        mTvGrossWeight.setText(MoneyUtils.formatMoney(bean.getGrossWeight())+" 公斤");
        mEtTrae.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        //设置字符过滤
        mEtTrae.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                //删除“.”后面超过2位后的数据
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        mEtTrae.setText(s);
                        mEtTrae.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    mEtTrae.setText(s);
                    mEtTrae.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        mEtTrae.setText(s.subSequence(0, 1));
                        mEtTrae.setSelection(1);
                        return;
                    }
                }
                //包含. 查看. 前面是否有值
                if(s.toString().trim().contains(".")){
                    String  a=s.toString().trim().substring(0, s.toString().trim().indexOf("."));
                    if(a.length()<=0){
                        s = "0" + s;
                        mEtTrae.setText(s);
                        mEtTrae.setSelection(2);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
    }
    //添加皮重
    private void addTrae(String weight) {
        String sign = MD5Util.encode("code=" + id + "&tareWeight=" + weight);
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .addTrae(id, weight, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        TraeSuccessActivity.newIntent(getApplicationContext(), traeBean.getData().getId()+"");
                        finish();
                    }
                });
    }

    //获取皮重详情
    private void getTraetDetail() {
        String sign = MD5Util.encode("code=" + id);
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .getTraeDetail(id, sign)
                .compose(Transformer.<TraeBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<TraeBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(TraeBean bean) {
                        if (bean.getCode() == 200) {
                            traeBean=bean;
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            finish();
                        }
                    }
                });
    }
}