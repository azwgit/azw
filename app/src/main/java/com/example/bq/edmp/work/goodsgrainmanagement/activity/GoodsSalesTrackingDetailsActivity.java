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
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.goodsgrainmanagement.adapter.GoodsDetailsListAdp;
import com.example.bq.edmp.work.returnsmanagement.activity.EditApplyForRefundActivity;
import com.example.bq.edmp.work.returnsmanagement.api.ReturnGoodsApi;
import com.example.bq.edmp.work.returnsmanagement.bean.ReapplyReturnGoods;
import com.example.bq.edmp.work.returnsmanagement.bean.ReturnsGoodsDetailsBean;
import com.example.bq.edmp.work.returnsmanagement.eventbus.CloseActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GoodsSalesTrackingDetailsActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, GoodsSalesTrackingDetailsActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.btn_del)
    TextView mBtnDel;//删除
    @BindView(R.id.btn_submit)
    TextView mBtnSubmit;//重新申请
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
    LinearLayout mLyNumber;//审批编号父布局
    @BindView(R.id.ly_state)
    LinearLayout mLyState;//审批状态父布局
    @BindView(R.id.rl_time)
    RelativeLayout mRlTime;//完成时间父布局
    private ApprovalAdp mApprovalAdapter;
    private GoodsDetailsListAdp goodsDetailsListAdp;
    private ILoadingView loading_dialog;
    private String id = "";
    private ReturnsGoodsDetailsBean returnsGoodsDetailsBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_sales_tracking_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("退货详情");
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
        for (int i = 0; i < 3; i++) {
            PayInfoBean bean = new PayInfoBean();
            bean.setId(i + "");
            bean.setDesc("啊啊啊啊");
            list.add(bean);
        }
        mApprovalRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mApprovalAdapter = new ApprovalAdp(list);
        mApprovalRecyclerView.setAdapter(mApprovalAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        goodsDetailsListAdp = new GoodsDetailsListAdp(list);
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
            case R.id.btn_del:
                deleteReturnGoods();
                break;
            case R.id.btn_submit:
                reapplyReturnGoods();
                break;
        }
    }

    //获取发货详情
    private void ReturnGoodsDetails() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .ReturnGoodsDetails(id, sign)
                .compose(Transformer.<ReturnsGoodsDetailsBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<ReturnsGoodsDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ReturnsGoodsDetailsBean bean) {
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
    private void setData(ReturnsGoodsDetailsBean.DataBean bean) {
        mTvReturenGoodsOrder.setText("退货单 " + bean.getCode());
        String status = "";
        switch (bean.getStatus()) {
            case 2:
                status = "待审批";
                break;
            case 3:
                status = "审批拒绝";
                break;
            case 4:
                status = "退货中";
                break;
        }
        tv_stuats.setText(status);
        mTvOrderNumbern.setText("订单号" + bean.getOrderCode());
        mTvCompany.setText(bean.getOrgName());
        mTvName.setText(bean.getAddedOperator());
        mTvStartTime.setText("发货时间" + bean.getSendOutTimes());
        mTvEndTime.setText("退货时间" + bean.getAddedTime());
        mTvAuditNumber.setText("20201025112108996");//审核编号
        mTvAuditStauts.setText("待审批");//审核状态
        mTvCustomer.setText(bean.getCustomerName());
        mTvContacts.setText(bean.getContacts());
        mTvPhone.setText(bean.getMobTel());
        mTvAddress.setText(bean.getRegion());
    }

    //删除退货
    private void deleteReturnGoods() {
        String sign = MD5Util.encode("id=" + returnsGoodsDetailsBean.getData().getId());
        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .deleteReturnGoods(returnsGoodsDetailsBean.getData().getId() + "", sign)
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

    //重新申请
    private void reapplyReturnGoods() {
        String sign = MD5Util.encode("id=" + returnsGoodsDetailsBean.getData().getId());
        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .reapplyReturnGoods(returnsGoodsDetailsBean.getData().getId() + "", sign)
                .compose(Transformer.<ReapplyReturnGoods>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<ReapplyReturnGoods>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ReapplyReturnGoods bean) {
                        if (bean.getCode() == 200) {
                            EditApplyForRefundActivity.newIntent(getApplicationContext(), bean.getData().getId() + "");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }
}