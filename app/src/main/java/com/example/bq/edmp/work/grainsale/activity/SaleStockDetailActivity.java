package com.example.bq.edmp.work.grainsale.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.finishedproduct.adapter.FinishedStockDetailAdp;
import com.example.bq.edmp.work.finishedproduct.api.FinishedProductApi;
import com.example.bq.edmp.work.finishedproduct.bean.FinishedStockDetailBean;
import com.example.bq.edmp.work.grainsale.api.SaleApi;

import butterknife.BindView;

/**
 * 销售库存详情
 */
public class SaleStockDetailActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String warehouseId, String packagingId) {
        Intent intent = new Intent(context, SaleStockDetailActivity.class);
        intent.putExtra(Constant.ID, warehouseId);
        intent.putExtra(Constant.CODE, packagingId);
        context.startActivity(intent);
    }

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_name)
    TextView mTvName;//品种名称
    @BindView(R.id.tv_contractor)
    TextView mTvContractor;//当清库存
    @BindView(R.id.tv_warehouse)
    TextView mTvWarehouse;//仓库
    @BindView(R.id.tv_subsidiary_company)
    TextView mTvSubsidiaryCompany;//子公司

    private FinishedStockDetailAdp mAdapter;
    private String warehouseId = "";
    private String packagingId = "";
    private ILoadingView loading_dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_stock_detail;
    }


    @Override
    protected void initView() {
        txtTabTitle.setText("库存详情");
        warehouseId = getIntent().getStringExtra(Constant.ID);
        packagingId = getIntent().getStringExtra(Constant.CODE);
        if ("".equals(warehouseId) || "".equals(packagingId)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new FinishedStockDetailAdp();
        mRecyclerView.setAdapter(mAdapter);
        getStockDetail();
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

    private void setData(FinishedStockDetailBean.DataBean bean) {
        mTvName.setText(bean.getVarietyName());
        mTvContractor.setText(MoneyUtils.formatMoney(bean.getQty()) + " 吨");
        mTvWarehouse.setText(bean.getWarehouseName());
        mTvSubsidiaryCompany.setText(bean.getOrgName());
        mAdapter.addData(bean.getStockRecords());
    }

    //获取库存详情
    private void getStockDetail() {
        String sign = MD5Util.encode("itemId=" + packagingId + "&warehouseId=" + warehouseId);
        RxHttpUtils.createApi(SaleApi.class)
                .getStockDetail(packagingId, warehouseId, sign)
                .compose(Transformer.<FinishedStockDetailBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<FinishedStockDetailBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(FinishedStockDetailBean bean) {
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
