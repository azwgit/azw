package com.example.bq.edmp.work.finishedproduct.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
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
import com.example.bq.edmp.work.finishedproduct.adapter.FinishedWarehouseVarietiesListAdp;
import com.example.bq.edmp.work.finishedproduct.adapter.FinishedWarehouseVarietiesOutListAdp;
import com.example.bq.edmp.work.finishedproduct.api.FinishedProductApi;
import com.example.bq.edmp.work.finishedproduct.bean.FinishedWarehousingBean;
import com.example.bq.edmp.work.grainmanagement.api.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.adapter.WareHousingDetailsDetectionListAdp;
import com.example.bq.edmp.work.grainmanagement.bean.WarehouseingDetailBean;

import butterknife.BindView;

/**
 * 入库详情
 */
public class FinishedWarehousingDetailActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, FinishedWarehousingDetailActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.ly_two)
    LinearLayout mLyTwo;//调拨信息父布局
    @BindView(R.id.tv_number)
    TextView mTvNumber;//收购单号
    @BindView(R.id.tv_status)
    TextView mTvStatus;//入库类型
    @BindView(R.id.tv_contractor)
    TextView mTvContractor;//分子公司名称
    @BindView(R.id.tv_warehouse)
    TextView mTvWarehouse;//入库仓库
//    @BindView(R.id.tv_varieties)
//    TextView mTvVarieties;//品种
//    @BindView(R.id.tv_gross_weight)
//    TextView mTvGrossWeight;//入库量
    @BindView(R.id.tv_time)
    TextView mTvTime;//入库日期
    @BindView(R.id.tv_transfer_warehouse)
    TextView mTvTransferWarehouse;//调出仓库
    @BindView(R.id.tv_transfer_reason)
    TextView mTvTransferReason;//调拨原因
    @BindView(R.id.tv_transfer_number)
    TextView mTvTransferNumber;//调拨单号
    @BindView(R.id.tv_task_number)
    TextView mTvTaskNumber;//任务单号
    @BindView(R.id.ly_number)
    LinearLayout mLyNumber;//任务单号父布局
    private String id="";
    private ILoadingView loading_dialog;
    private FinishedWarehouseVarietiesListAdp finishedWarehouseVarietiesListAdp;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_finished_warehousing_detail;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("入库详情");
        id=getIntent().getStringExtra(Constant.ID);
        if("".equals(id)){
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        finishedWarehouseVarietiesListAdp = new FinishedWarehouseVarietiesListAdp(null);
        recycler_view.setAdapter(finishedWarehouseVarietiesListAdp);
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
    private void setData(FinishedWarehousingBean.DataBean bean){
        mTvNumber.setText("入库单号  "+bean.getCode());
        String type="";
        switch (bean.getType2()){
            case 1:
                type="采购入库";
                break;
            case 2:
                type="加工入库";
                mLyNumber.setVisibility(View.VISIBLE);
                mTvTaskNumber.setText(bean.getProcessCode()+"");
                break;
            case 3:
                type="调拨入库";
                mLyTwo.setVisibility(View.VISIBLE);
                mTvTransferWarehouse.setText(bean.getStockAllots().getWarehouseName());
                mTvTransferReason.setText(bean.getStockAllots().getReason());
                mTvTransferNumber.setText(bean.getStockAllots().getCode());
                break;
        }
        mTvStatus.setText(type);
        mTvContractor.setText(bean.getOrgName());
        mTvWarehouse.setText(bean.getWarehouseName());
//        mTvVarieties.setText(bean.getVarietyName());
//        mTvGrossWeight.setText(MoneyUtils.formatMoney(bean.getAddQty())+" 公斤");
        mTvTime.setText(bean.getAddedTime());
        finishedWarehouseVarietiesListAdp.setNewData(bean.getStockAddItems());


    }
    //获取入庫详情
    private void getAcquisitionDetail() {
        String sign = MD5Util.encode("id="+id);
        RxHttpUtils.createApi(FinishedProductApi.class)
                .getWareHousingDetail(id, sign)
                .compose(Transformer.<FinishedWarehousingBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<FinishedWarehousingBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(FinishedWarehousingBean bean) {
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
