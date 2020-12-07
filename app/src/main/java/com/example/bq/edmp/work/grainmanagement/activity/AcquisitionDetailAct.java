package com.example.bq.edmp.work.grainmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.grainmanagement.api.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.adapter.DetailsDetectionListAdp;
import com.example.bq.edmp.work.grainmanagement.bean.AcquisitionBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 收购详情
 */
public class AcquisitionDetailAct extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, AcquisitionDetailAct.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_number)
    TextView mTvNumber;//收购单号
    @BindView(R.id.tv_status)
    TextView mTvStatus;//收购状态
    @BindView(R.id.tv_contractor)
    TextView mTvContractor;//承包人
    @BindView(R.id.tv_varieties)
    TextView mTvVarieties;//品种
    @BindView(R.id.tv_warehouse)
    TextView mTvWarehouse;//仓库
    @BindView(R.id.tv_gross_weight)
    TextView mTvGrossWeight;//毛重
    @BindView(R.id.tv_pi_weight)
    TextView mTvPiWeight;//皮重
    @BindView(R.id.tv_weight)
    TextView mTvWeight;//净重
    @BindView(R.id.tv_date)
    TextView mTvDate;//收购日期
    @BindView(R.id.tv_pinzhongxinxi)
    TextView mTvPinZhongXinXi;//收购品种信息

    private DetailsDetectionListAdp detailsDetectionListAdp;
    private String id="";
    private ILoadingView loading_dialog;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_acquisition_detail;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("收购详情");
        id=getIntent().getStringExtra(Constant.ID);
        if("".equals(id)){
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        detailsDetectionListAdp = new DetailsDetectionListAdp(null);
        mRecyclerView.setAdapter(detailsDetectionListAdp);
        getAcquisitionDetail();
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
    //查询成功赋值
    private void  setData(AcquisitionBean.DataBean bean){
        mTvNumber.setText("收购单号  "+bean.getCode());
        if(bean.getStatus()==1){
            mTvStatus.setText("收购中");
        }else{
            mTvStatus.setText("已完成");
        }
        mTvContractor.setText(bean.getFarmerName());
        mTvVarieties.setText(bean.getVarietyName());
        mTvWarehouse.setText(bean.getWarehouseName());
        mTvGrossWeight.setText(MoneyUtils.formatMoney(bean.getGrossWeight())+" 公斤");
        mTvPiWeight.setText(MoneyUtils.formatMoney(bean.getTareWeight())+" 公斤");
        mTvWeight.setText(MoneyUtils.formatMoney(bean.getNetWeight())+" 公斤");
        mTvDate.setText(bean.getAddedTime());
        if(bean.getTestingItems()==null){
            mTvPinZhongXinXi.setVisibility(View.GONE);
            return ;
        }
        detailsDetectionListAdp.addData(bean.getTestingItems());
    }
    //获取收购详情
    private void getAcquisitionDetail() {
        String sign = MD5Util.encode("id="+id);
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .getAcquisitionDetail(id, sign)
                .compose(Transformer.<AcquisitionBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<AcquisitionBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AcquisitionBean acquisitionBean) {
                        if (acquisitionBean.getCode() == 200) {
                            setData(acquisitionBean.getData());
                        } else {
                            ToastUtil.setToast(acquisitionBean.getMsg());
                            finish();
                        }
                    }
                });
    }
}
