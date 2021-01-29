package com.example.bq.edmp.work.grainsale.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.grainmanagement.adapter.WarehouseVarietiesListAdp;
import com.example.bq.edmp.work.grainmanagement.api.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.bean.WarehouseingDetailBean;
import com.example.bq.edmp.work.grainsale.adapter.SaleWarehouseVarietiesDCPZListAdp;
import com.example.bq.edmp.work.grainsale.adapter.SaleWarehouseVarietiesListAdp;
import com.example.bq.edmp.work.grainsale.api.SaleApi;
import com.example.bq.edmp.work.grainsale.bean.SaleWarehouseDetailBean;

import java.util.List;

import butterknife.BindView;

/*
 * 销售入库详情
 * */
public class SaleWarehousingDetailsActivity extends BaseTitleActivity {

    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_contractor)
    TextView mTvContractor;//分子公司名称
    @BindView(R.id.tv_warehouse)
    TextView mTvWarehouse;//入库仓库
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.dcpz_recycler_view)
    RecyclerView dcpz_recycler_view;
    @BindView(R.id.tv_time)
    TextView mTvTime;//入库日期
    @BindView(R.id.tv_transfer_number)
    TextView mTvTransferNumber;//调拨单号
    @BindView(R.id.tv_transfer_warehouse)
    TextView mTvTransferWarehouse;//调出仓库
    @BindView(R.id.tv_transfer_reason)
    TextView mTvTransferReason;//调拨原因


    private LoadingDialog loadingDialog;
    private String id;
    private SaleWarehouseVarietiesListAdp saleWarehouseVarietiesListAdp;
    private SaleWarehouseVarietiesDCPZListAdp saleWarehouseVarietiesDCPZListAdp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sale_warehousing_details;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(SaleWarehousingDetailsActivity.this);
        loadingDialog = new LoadingDialog(this);
        txtTabTitle.setText("入库详情");
        id = getIntent().getStringExtra("id");


        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        saleWarehouseVarietiesListAdp = new SaleWarehouseVarietiesListAdp(null);
        recycler_view.setAdapter(saleWarehouseVarietiesListAdp);

        dcpz_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        saleWarehouseVarietiesDCPZListAdp = new SaleWarehouseVarietiesDCPZListAdp(null);
        dcpz_recycler_view.setAdapter(saleWarehouseVarietiesDCPZListAdp);
    }

    //获取入庫详情
    private void getAcquisitionDetail() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(SaleApi.class)
                .getWareHousingDetail(id, sign)
                .compose(Transformer.<SaleWarehouseDetailBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<SaleWarehouseDetailBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SaleWarehouseDetailBean bean) {
                        if (bean.getCode().equals("200")) {
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            finish();
                        }
                    }
                });
    }

    private void setData(SaleWarehouseDetailBean.DataBean data) {


        String type2 = data.getType2();
        if (type2.equals("1")) {
            tv_status.setText("采购入库");
        } else if (type2.equals(+2)) {
            tv_status.setText("加工入库");
        } else if (type2.equals("3")) {
            tv_status.setText("调拨入库");
        }
        tv_number.setText("入库单号: " + data.getCode());
        mTvContractor.setText(data.getOrgName());
        mTvWarehouse.setText(data.getWarehouseName());
        saleWarehouseVarietiesListAdp.setNewData(data.getStockAddItems());
        saleWarehouseVarietiesDCPZListAdp.setNewData(data.getStockAllots().getStockAllotItems());
        mTvTime.setText(data.getAddedTime());
        mTvTransferNumber.setText(data.getStockAllots().getCode());
        mTvTransferWarehouse.setText(data.getStockAllots().getWarehouseName());
        mTvTransferReason.setText(data.getStockAllots().getRemark());
    }

    @Override
    protected void initData() {
        getAcquisitionDetail();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void otherViewClick(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(SaleWarehousingDetailsActivity.this);
    }
}
