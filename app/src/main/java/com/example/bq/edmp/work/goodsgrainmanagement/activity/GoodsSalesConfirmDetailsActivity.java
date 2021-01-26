package com.example.bq.edmp.work.goodsgrainmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApprovalAdp;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.UsualDialogger;
import com.example.bq.edmp.work.goodsgrainmanagement.adapter.GoodsDetailsListAdp;
import com.example.bq.edmp.work.goodsgrainmanagement.api.GoodsSalesApi;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.EditGoodSalesBean;
import com.example.bq.edmp.work.returnsmanagement.eventbus.CloseActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GoodsSalesConfirmDetailsActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, GoodsSalesConfirmDetailsActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.btn_del)
    TextView mBtnDel;//删除
    @BindView(R.id.btn_submit)
    TextView mBtnSubmit;//确认入库
    @BindView(R.id.approval_recyclerview)
    RecyclerView mApprovalRecyclerView;//审批状态适配器
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;//商品适配器
    @BindView(R.id.tv_returen_goods_order)
    TextView mTvReturenGoodsOrder;//退货单
    @BindView(R.id.tv_stuats)
    TextView tv_stuats;//退货状态
    @BindView(R.id.tv_order_numbern)
    TextView mTvOrderNumbern;//订单号
    @BindView(R.id.tv_company)
    TextView mTvCompany;//公司名称
    @BindView(R.id.tv_name)
    TextView mTvName;//发起人
    @BindView(R.id.tv_start_time)
    TextView mTvStartTime;//发货时间
    @BindView(R.id.tv_end_time)
    TextView mTvEndTime;//退货时间
    @BindView(R.id.tv_audit_number)
    TextView mTvAuditNumber;//审核编号
    @BindView(R.id.tv_audit_stauts)
    TextView mTvAuditStauts;//审核状态
    @BindView(R.id.tv_customer)
    TextView mTvCustomer;//客户
    @BindView(R.id.tv_contacts)
    TextView mTvContacts;//联系人
    @BindView(R.id.tv_phone)
    TextView mTvPhone;//联系方式
    @BindView(R.id.tv_address)
    TextView mTvAddress;//联系地址
    @BindView(R.id.tv_subsidiary_company)
    TextView mTvSubsidiaryCompany;//分子公司名称
    LinearLayout mLyNumber;//审批编号父布局
    @BindView(R.id.ly_state)
    LinearLayout mLyState;//审批状态父布局
    @BindView(R.id.rl_time)
    RelativeLayout mRlTime;//完成时间父布局
    @BindView(R.id.ly_bottom)
    LinearLayout mLyBottom;//底部功能父布局
    @BindView(R.id.rl_chuku)
    RelativeLayout mRlChuKu;//出库父布局
    @BindView(R.id.tv_delivery_time)
    TextView mTvDeliveryTime;//出库时间

    private ApprovalAdp mApprovalAdapter;
    private GoodsDetailsListAdp goodsDetailsListAdp;
    private ILoadingView loading_dialog;
    private String id = "";
    private EditGoodSalesBean returnsGoodsDetailsBean;
    List<EditGoodSalesBean.DataBean.CgOrderItemsBean> cgOrderItemsBeanList = new ArrayList<>();
    private UsualDialogger dialog = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_sales_tracking_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("销售详情");
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            finish();
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        ReturnGoodsDetails();
        List<PayInfoBean> list = new ArrayList<>();
        for(int i=0;i<3;i++){
            PayInfoBean payInfoBean=new PayInfoBean();
            list.add(payInfoBean);
        }
        mApprovalRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mApprovalAdapter = new ApprovalAdp(list);
        mApprovalRecyclerView.setAdapter(mApprovalAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        goodsDetailsListAdp = new GoodsDetailsListAdp(cgOrderItemsBeanList);
        mRecyclerView.setAdapter(goodsDetailsListAdp);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBtnDel.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                if ("确认出库".equals(mBtnSubmit.getText().toString().trim())) {
                    confirmDelivery();
                } else {
                    showInAndOutOfWarehouse();
                }
                break;
        }
    }

    //获取商品粮销售详情
    private void ReturnGoodsDetails() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .getGoodsDetails(id, sign)
                .compose(Transformer.<EditGoodSalesBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<EditGoodSalesBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(EditGoodSalesBean bean) {
                        if (bean.getCode() == 200) {
                            returnsGoodsDetailsBean = bean;
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            finish();
                        }
                    }
                });
    }

    //详情赋值
    private void setData(EditGoodSalesBean.DataBean bean) {
        mTvReturenGoodsOrder.setText("订单号 " + bean.getCode());
        String status = "";
        switch (bean.getStatus()) {
            case 1:
                status = "待提交";
                break;
            case 2:
                status = "待审批";
                break;
            case 3:
                status = "审批拒绝";
                mRlChuKu.setVisibility(View.VISIBLE);
                mTvDeliveryTime.setText("出库时间"+bean.getSubstockTime());
            case 4:
                status = "待出库";
                break;
            case 5:
                status = "待完成";
                mRlChuKu.setVisibility(View.VISIBLE);
                mTvDeliveryTime.setText("出库时间"+bean.getSubstockTime());
                break;
            case 10:
                status = "已完成";
                break;
        }
        tv_stuats.setText(status);
        mTvOrderNumbern.setText("订单号" + bean.getCode());
        mTvCompany.setText(bean.getOrgFullName());
        mTvName.setText(bean.getAddedOperator());
//        mTvStartTime.setText("发货时间" + bean.getSendOutTimes());
        mTvEndTime.setText("提交时间 " + bean.getSubmitTime());
        mTvAuditNumber.setText("20201025112108996");//审核编号
        mTvAuditStauts.setText("待审批");//审核状态
        mTvCustomer.setText(bean.getCustomerName());
        mTvContacts.setText(bean.getContacts());
        mTvPhone.setText(bean.getMobTel());
        mTvAddress.setText(bean.getRegion());
        mTvSubsidiaryCompany.setText(bean.getOrgName());
        //4  5 显示底部按钮
        if (bean.getStatus() == 4 || bean.getStatus() == 5) {
            mLyBottom.setVisibility(View.VISIBLE);
            if (bean.getStatus() == 4) {
                mBtnSubmit.setText("确认出库");
            } else {
                mBtnSubmit.setText("确认完成");
            }
        } else {
            mLyBottom.setVisibility(View.GONE);
        }
        if (bean.getCgOrderItems() != null) {
            cgOrderItemsBeanList.addAll(bean.getCgOrderItems());
            goodsDetailsListAdp.notifyDataSetChanged();
        }
    }

    //确认出库
    private void confirmDelivery() {
        String sign = MD5Util.encode("code=" + returnsGoodsDetailsBean.getData().getCode());
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .confirmDelivery(returnsGoodsDetailsBean.getData().getCode() + "", sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            EventBus.getDefault().post(new CloseActivity());
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //销售确认
    private void salesConfirmation() {
        String sign = MD5Util.encode("code=" + returnsGoodsDetailsBean.getData().getCode());
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .salesConfirmation(returnsGoodsDetailsBean.getData().getCode() + "", sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            EventBus.getDefault().post(new CloseActivity());
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //提示dialog
    public void showInAndOutOfWarehouse() {
        dialog = UsualDialogger.Builder(this)
                .setTitle("友情提示")
                .setMessage("是否确认该商品粮销售订单已完成")
                .setOnConfirmClickListener("确定", new UsualDialogger.onConfirmClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        salesConfirmation();
                    }
                })
                .setOnCancelClickListener("取消", new UsualDialogger.onCancelClickListener() {
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
}