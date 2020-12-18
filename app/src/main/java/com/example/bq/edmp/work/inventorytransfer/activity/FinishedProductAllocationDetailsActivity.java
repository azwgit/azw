package com.example.bq.edmp.work.inventorytransfer.activity;

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
import com.example.bq.edmp.activity.apply.ApprovalAdp;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.grainmanagement.api.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.bean.ContractorListBean;
import com.example.bq.edmp.work.inventorytransfer.adapter.VarietiesNameListAdp;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;
import com.example.bq.edmp.work.inventorytransfer.bean.GoodsDetailsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FinishedProductAllocationDetailsActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, FinishedProductAllocationDetailsActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.approval_recyclerview)
    RecyclerView mApprovalRecyclerView;//审批状态适配器
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;//调拨商品适配器
    @BindView(R.id.tv_title)
    TextView mTvTitle;//调拨单号
    @BindView(R.id.tv_company)
    TextView mTvCompany;//公司名称
    @BindView(R.id.tv_stuats)
    TextView mTvStuats;//调拨单状态
    @BindView(R.id.tv_name)
    TextView mTvName;//操作人
    @BindView(R.id.tv_time)
    TextView mTvTime;//操作時間
    @BindView(R.id.tv_transfer_out)
    TextView mTvTransferOut;//调出信息
    @BindView(R.id.tv_transfer_in)
    TextView mTvTransferIn;//调入信息
    @BindView(R.id.tv_out_order_no)
    TextView mTvDeliveryOrderNo;//出库单号
    @BindView(R.id.tv_in_order_no)
    TextView mTvInOrderNo;//入库单号
    @BindView(R.id.tv_completion_time)
    TextView mTvCompletionTime;//完成时间
    @BindView(R.id.tv_content)
    TextView mTvContent;//调拨原因

    private ILoadingView loading_dialog;
    private String id = "";
    private ContractorListBean contractorListBean;//承包人数据源
    private ApprovalAdp mApprovalAdapter;
    private VarietiesNameListAdp varietiesNameListAdp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_finished_product_allocation_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("成品调拨单详情");
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        List<PayInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PayInfoBean bean = new PayInfoBean();
            bean.setId(i + "");
            bean.setDesc("啊啊啊啊");
            list.add(bean);
        }

        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        varietiesNameListAdp = new VarietiesNameListAdp(null);
        recycler_view.setAdapter(varietiesNameListAdp);
        mApprovalRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mApprovalAdapter = new ApprovalAdp(list);
        mApprovalRecyclerView.setAdapter(mApprovalAdapter);
        getGoodsDetails();
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

    //获取商品详情
    private void getGoodsDetails() {
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
        if ("1".equals(bean.getTypes())) {
            mTvTitle.setText("成品调拨：" + bean.getCode());
        } else {
            mTvTitle.setText("原粮调拨：" + bean.getCode());
        }
        mTvCompany.setText(bean.getOrgName());
        String stuats = "";
        switch (bean.getStatus()) {
            case 2:
                stuats = "待审批";
                break;
            case 3:
                stuats = "审批通过";
                break;
            case 4:
                stuats = "出库中";
                break;
            case 5:
                stuats = "在途";
                mTvDeliveryOrderNo.setVisibility(View.VISIBLE);
                mTvDeliveryOrderNo.setText("出库单："+bean.getSubCode());
                break;
            case 6:
                stuats = "已完成";
                mTvDeliveryOrderNo.setVisibility(View.VISIBLE);
                mTvDeliveryOrderNo.setText("出库单："+bean.getSubCode());
                mTvInOrderNo.setVisibility(View.VISIBLE);
                mTvInOrderNo.setText("入库单："+bean.getAddCode());
                mTvCompletionTime.setVisibility(View.VISIBLE);
                mTvCompletionTime.setText("完成时间："+bean.getInContirmTime());
                break;
        }
        mTvStuats.setText(stuats);
        mTvName.setText(bean.getAddedOperator());
        mTvTime.setText(bean.getAddedTime());
        mTvTransferOut.setText(bean.getOutOrgName() + "  " + bean.getOutWarehouseName());
        mTvTransferIn.setText(bean.getInOrgName() + "  " + bean.getInWarehouseName());
        mTvContent.setText(bean.getReason());
        varietiesNameListAdp.setNewData(bean.getStockAllotItems());
    }
}