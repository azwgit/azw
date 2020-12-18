package com.example.bq.edmp.work.inventorytransfer.activity;

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
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.inventorytransfer.adapter.CommodityListAdp;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;

import butterknife.BindView;

public class EditFinishedProductAllocationActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String type, String id) {
        Intent intent = new Intent(context, EditFinishedProductAllocationActivity.class);
        intent.putExtra(Constant.TYPE, type);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_add_info)
    LinearLayout mBtnAddInfo;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.btn_del)
    TextView mBtnDel;
    @BindView(R.id.tv_content)
    TextView mTvContent;//调拨原因
    @BindView(R.id.tv_transfer_company)
    TextView mTvTransferCompany;//调入公司
    @BindView(R.id.tv_transfer_out_company)
    TextView mTvTransferOutCompany;//调出公司
    @BindView(R.id.tv_transfer_out_warehouse)
    TextView mTvTransferOutWarehouse;//调出仓库
    @BindView(R.id.tv_transfer_warehouse)
    TextView mTvTransferWarehouse;//调入仓库
    private String type = "";//1原粮进入 2成品进入
    private String id = "";
    private CommodityListAdp mAdapter;
    private ILoadingView loading_dialog;
    private EditFinishedProductAllocationBean editFinishedProductAllocationBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_finished_product_allocation;
    }

    @Override
    protected void initView() {
        type = getIntent().getStringExtra(Constant.TYPE);
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(type) || "".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        if ("1".equals(type)) {
            txtTabTitle.setText("申请原粮调拨");
        } else {
            txtTabTitle.setText("申请成品调拨");
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new CommodityListAdp(null);
        mRecyclerView.setAdapter(mAdapter);
        //删除
        mAdapter.setOnItemDelListener(new CommodityListAdp.OnItemDelListener() {
            @Override
            public void onItemDelClick(int position, EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean bean) {
                deleteGoods(bean.getInItemId() + "");
            }
        });

        //编辑
        mAdapter.setOnItemEditLisenter(new CommodityListAdp.OnItemEditLisenter() {
            @Override
            public void onItemEditClick(int pos, EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean bean) {
                UpdateTransferGoodsActivity.newIntent(getApplicationContext(), bean.getInItemId() + "", id);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getProudctAllocationDetails();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBtnAddInfo.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);
        mBtnDel.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_info:
                AddTransferGoodsActivity.newIntent(getApplicationContext(), type, editFinishedProductAllocationBean.getData().getId() + "");
                break;
            case R.id.tv_submit:
                submitAllot();
                break;
            case R.id.btn_del:
                deleteAllot();
                break;


        }
    }

    //获取调拨详情
    private void getProudctAllocationDetails() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(AllocationApi.class)
                .getProudctAllocationDetails(id, sign)
                .compose(Transformer.<EditFinishedProductAllocationBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<EditFinishedProductAllocationBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(EditFinishedProductAllocationBean bean) {
                        editFinishedProductAllocationBean = bean;
                        if (bean.getCode() == 200) {
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //详情赋值
    private void setData(EditFinishedProductAllocationBean.DataBean bean) {
        mTvContent.setText(bean.getReason());
        mTvTransferCompany.setText(bean.getInOrgName());
        mTvTransferOutCompany.setText(bean.getOutOrgName());
        mTvTransferOutWarehouse.setText(bean.getInWarehouseName());
        mTvTransferWarehouse.setText(bean.getOutWarehouseName());
        if (bean.getStockAllotItems().size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.GONE);
        }
        mAdapter.setNewData(bean.getStockAllotItems());
    }

    //删除调拨
    private void deleteAllot() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(AllocationApi.class)
                .deleteAllot(id, sign)
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

    //调拨提交
    private void submitAllot() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(AllocationApi.class)
                .submitAllot(id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("提交成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //刪除调拨商品
    private void deleteGoods(String inItemId) {
        String sign = MD5Util.encode("inItemId=" + inItemId + "&stockAllotId=" + id);
        RxHttpUtils.createApi(AllocationApi.class)
                .deleteGoods(inItemId, id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("商品删除成功");
                            getProudctAllocationDetails();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }
}