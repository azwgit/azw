package com.example.bq.edmp.work.grainsale.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
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
import com.example.bq.edmp.work.grainsale.adapter.SaleWarehouseVarietiesDCPZListAdp;
import com.example.bq.edmp.work.grainsale.adapter.SaleWarehouseVarietiesListAdp;
import com.example.bq.edmp.work.grainsale.api.SaleApi;
import com.example.bq.edmp.work.grainsale.bean.SaleWarehouseDetailBean;

import butterknife.BindView;

/*
 * 销售出库详情
 * */
public class SaleDeliveryDetailsActivity extends BaseTitleActivity {

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
    @BindView(R.id.tv_time)
    TextView mTvTime;//入库日期
    @BindView(R.id.tv_transfer_number)
    TextView mTvTransferNumber;//调拨单号
    @BindView(R.id.tv_transfer_warehouse)
    TextView mTvTransferWarehouse;//调出仓库
    @BindView(R.id.tv_transfer_reason)
    TextView mTvTransferReason;//调拨原因
    @BindView(R.id.ly_number)
    LinearLayout ly_number;//
    @BindView(R.id.ly_two)
    LinearLayout ly_two;//
    @BindView(R.id.tv_task_number)
    TextView tv_task_number;//


    private LoadingDialog loadingDialog;
    private String id;
    private SaleWarehouseVarietiesListAdp saleWarehouseVarietiesListAdp;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_sale_delivery_details;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(SaleDeliveryDetailsActivity.this);
        loadingDialog = new LoadingDialog(this);
        txtTabTitle.setText("出库详情");
        id = getIntent().getStringExtra("id");


        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        saleWarehouseVarietiesListAdp = new SaleWarehouseVarietiesListAdp(null);
        recycler_view.setAdapter(saleWarehouseVarietiesListAdp);

    }

    //获取入庫详情
    private void getAcquisitionDetail() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(SaleApi.class)
                .getDeliveryDetail(id, sign)
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
            tv_status.setText("加工出库");
        } else if (type2.equals("2")) {
            tv_status.setText("销售出库");
            ly_number.setVisibility(View.VISIBLE);
            tv_task_number.setText(data.getOrdersCode());
        } else if (type2.equals("3")) {
            tv_status.setText("调拨出库");
            ly_two.setVisibility(View.VISIBLE);
            mTvTransferNumber.setText(data.getStockAllots().getCode());
            mTvTransferWarehouse.setText(data.getStockAllots().getWarehouseName());
            mTvTransferReason.setText(data.getStockAllots().getRemark());
        }
        tv_number.setText("出库单号: " + data.getCode());
        mTvContractor.setText(data.getOrgName());
        mTvWarehouse.setText(data.getWarehouseName());
        saleWarehouseVarietiesListAdp.setNewData(data.getStockAddItems());
        mTvTime.setText(data.getAddedTime());
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
        ProApplication.getinstance().finishActivity(SaleDeliveryDetailsActivity.this);
    }
}
