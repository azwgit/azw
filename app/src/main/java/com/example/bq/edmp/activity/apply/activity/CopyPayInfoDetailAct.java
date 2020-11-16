package com.example.bq.edmp.activity.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApprovalAdp;
import com.example.bq.edmp.activity.apply.ReimbursementApi;
import com.example.bq.edmp.activity.apply.adapter.DetailsPayInfoAdp;
import com.example.bq.edmp.activity.apply.bean.ApplyPayBean;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.activity.apply.bean.IntetnCode;
import com.example.bq.edmp.activity.apply.bean.PayReimbursementDetailsInfo;
import com.example.bq.edmp.activity.apply.bean.RevokeApplyBean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * copy申请支出报账详情
 */
public class CopyPayInfoDetailAct extends BaseTitleActivity {

    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, CopyPayInfoDetailAct.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
        return intent;
    }

    @BindView(R.id.btn_revoke)
    TextView mBtnRevoke;
    @BindView(R.id.btn_del)
    TextView mBtnDel;
    @BindView(R.id.btn_submit)
    TextView mBtnSubmit;
    @BindView(R.id.info_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.approval_recyclerview)
    RecyclerView mApprovalRecyclerView;
    @BindView(R.id.ly_approval)
    LinearLayout mLyApproval;
    @BindView(R.id.tv_title)
    TextView mTvTitle;//标题
    @BindView(R.id.tv_company)
    TextView mTvCompany;//公司名称
    @BindView(R.id.tv_dept)
    TextView mTvDept;//部门名称
    @BindView(R.id.tv_stauts)
    TextView mTvStauts;//审批状态
    @BindView(R.id.tv_reason)
    EditText mTvReason;//报销事由
    @BindView(R.id.tv_remark)
    EditText mTvRemark;//报销说明
    @BindView(R.id.tv_money)
    EditText mTvMoney;//预借款
    @BindView(R.id.tv_all_money)
    TextView mTvAllMoney;//报销总额


    private DetailsPayInfoAdp mAdapter;
    private ApprovalAdp mApprovalAdapter;
    private PayReimbursementDetailsInfo dataBean = new PayReimbursementDetailsInfo();

    @Override
    protected void initData() {
        String id = getIntent().getStringExtra("id");
        if ("".equals(id)) {
            ToastUtil.setToast("系统异常");
            finish();
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new DetailsPayInfoAdp(null, 0);
        mRecyclerView.setAdapter(mAdapter);
        getRembursementDetails(id);
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


        //点击查看大图
        mAdapter.setOnPicLisenter(new DetailsPayInfoAdp.OnPicLisenter() {
            @Override
            public void onPicClick(int themeId, int position, List<LocalMedia> selectList) {
                PictureSelector.create(CopyPayInfoDetailAct.this).themeStyle(themeId).openExternalPreview(position, selectList);
            }
        });
        //删除
        mAdapter.setOnItemDelListener(new DetailsPayInfoAdp.OnItemDelListener() {
            @Override
            public void onItemDelClick(int position, PayReimbursementDetailsInfo.DataBean.ReimburserItemsBean bean) {
                deleteApplyPay(bean.getId().getIdx() + "", dataBean.getData().getId() + "", position);
            }
        });
//编辑
        mAdapter.setOnItemEditLisenter(new DetailsPayInfoAdp.OnItemEditLisenter() {
            @Override
            public void onItemEditClick(int pos, PayReimbursementDetailsInfo.DataBean.ReimburserItemsBean bean) {
                IntetnCode intetnCode = new IntetnCode();
                intetnCode.setId(dataBean.getData().getId());
                intetnCode.setIdx(bean.getId().getIdx());
                startActivityForResult(UpdateDetailsPayInfoAct.newIntent(getApplicationContext(), intetnCode), 1);
//                mAdapter.notifyItemChanged(pos);//更新当前这一条数据
//                startActivityForResult(AddPayInfoAct.newIntent(ApplyPayAccountSecondAct.this),CHOOSE_DAY_INFO_CODE);
            }
        });
        mBtnRevoke.setOnClickListener(this);
        mBtnDel.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
    }

    private void setPayDetails(PayReimbursementDetailsInfo.DataBean bean) {
        if (bean.getStatus() == 1) {
            mLyApproval.setVisibility(View.GONE);
        } else {
            mTvReason.setEnabled(false);
            mTvRemark.setEnabled(false);
            mTvMoney.setEnabled(false);
        }
        switch (bean.getStatus()) {
            case 1:
                //待提交状态下
                mBtnDel.setText("删除");
                mBtnSubmit.setText("提交");
                break;
            case 2:
                //待审批状态下
                mBtnDel.setVisibility(View.GONE);
                mBtnSubmit.setVisibility(View.GONE);
                mBtnRevoke.setVisibility(View.VISIBLE);
                break;
            case 3:
                //已审批状态下
                mBtnDel.setVisibility(View.GONE);
                mBtnSubmit.setVisibility(View.GONE);
                break;
            case 4:
                //已完成状态下
                mBtnDel.setVisibility(View.GONE);
                mBtnSubmit.setVisibility(View.GONE);
                break;
            case -1:
                break;
            case -2:
                //审批拒绝状态下
                mBtnSubmit.setText("复制申请单");
                break;
            case -3:
                //已撤销状态下
                mBtnDel.setVisibility(View.GONE);
                mBtnSubmit.setVisibility(View.GONE);
                mBtnRevoke.setVisibility(View.VISIBLE);
                mBtnRevoke.setText("复制申请单");
                break;

        }
        mTvTitle.setText(bean.getEmpName() + "提出的" + (bean.getTypes() == 1 ? "开支报销申请" : "差旅报销"));
        mTvCompany.setText(bean.getDeptName());
        mTvDept.setText(bean.getDeptName());
        mTvMoney.setText(bean.getAdvanceLoan() + "");
        String reason = "";
        switch (bean.getStatus()) {
            case 1:
                reason = "待提交";
                break;
            case 2:
                reason = "审批中";
                break;
            case 3:
                reason = "审批通过";
                break;
            case 4:
                reason = "已完成";
                break;
            case -1:
                reason = "已删除";
                break;
            case -2:
                reason = "审批拒绝";
                break;
        }
        mTvStauts.setText(reason);
        mTvReason.setText(bean.getTdReason());
//        mTvRemark.setText(bean.getRemark());
        mTvAllMoney.setText("￥" + bean.getAmount());
        mTvRemark.setText(bean.getRemark());
        mAdapter.setNewData(bean.getReimburserItems());
        mAdapter.setShowicon(bean.getStatus());
    }

    @Override
    protected void initListener() {
        mBtnDel.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //TODO 根据审核状态显示title
        txtTabTitle.setText("支出报账申请");
//        txtTabTitle.setText("详情");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_pay_info_detail;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_del:
                deleteApply(dataBean.getData().getId() + "");
                break;
            case R.id.btn_submit:
                //elsea  走复制功能
                if ("提交".equals(mBtnSubmit.getText().toString().trim())) {
                    checkRembursementInfo();
                }

                break;
            case R.id.btn_revoke:
                //else 走撤销功能
                if ("撤销".equals(mBtnSubmit.getText().toString().trim())) {
                    revokeApply(dataBean.getData().getId() + "");
                } else {

                }

                break;
        }
    }

    //費用报销详情
    private void getRembursementDetails(String id) {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .getPayReimbursementDetails(id, sign)
                .compose(Transformer.<PayReimbursementDetailsInfo>switchSchedulers())
                .subscribe(new CommonObserver<PayReimbursementDetailsInfo>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(PayReimbursementDetailsInfo bean) {
                        if (bean.getCode() == 200) {
                            dataBean = bean;
                            //查询支出报账详情
                            setPayDetails(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //删除开支选项
    private void deleteApplyPay(String idx, String reimburserId, int position) {
        final int pos = position;
        String sign = MD5Util.encode("idx=" + idx + "&reimburserId=" + reimburserId);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .deleteApplyPay(idx, reimburserId, sign)
                .compose(Transformer.<ApplyPayBean>switchSchedulers())
                .subscribe(new CommonObserver<ApplyPayBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ApplyPayBean applyPayBean) {
                        if (applyPayBean.getCode() == 200) {
                            mAdapter.remove(pos);
                            mAdapter.notifyItemRemoved(pos);
                        } else {
                            ToastUtil.setToast("请添加开支项");
                        }
                    }
                });
    }

    //删除当前报账
    private void deleteApply(String id) {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .deleteApply(id, sign)
                .compose(Transformer.<PayReimbursementDetailsInfo>switchSchedulers())
                .subscribe(new CommonObserver<PayReimbursementDetailsInfo>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(PayReimbursementDetailsInfo bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("删除报账成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //驗證提交信息
    private void checkRembursementInfo() {
        String mReason = mTvReason.getText().toString().trim();
        if ("".equals(mReason)) {
            ToastUtil.setToast("请输入出差事由");
            return;
        }
        String mMoeny = mTvMoney.getText().toString().trim();
        if ("".equals(mMoeny)) {
            ToastUtil.setToast("请输入预借旅费金额");
            return;
        }
        String mContent = mTvRemark.getText().toString().trim();
        if ("".equals(mContent)) {
            ToastUtil.setToast("请输入报销说明");
            return;
        }
        submitRembursement(mMoeny, dataBean.getData().getDatas(), dataBean.getData().getId() + "", mContent, mReason, dataBean.getData().getTypes() + "");
    }

    //保存申请支出报账
    private void submitRembursement(String mEtMoney, String mTvDate, String id, String remark, String mEtReason, String types) {
        String sign = MD5Util.encode("advanceLoan=" + mEtMoney + "&dates=" + mTvDate + "&id=" + id + "&remark=" + remark + "&tdReason=" + mEtReason + "&types=" + types);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .upDataReimburser(mEtMoney, mTvDate, id, remark, mEtReason, types, sign)
                .compose(Transformer.<ApplyPayBean>switchSchedulers())
                .subscribe(new CommonObserver<ApplyPayBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ApplyPayBean applyPayBean) {
                        if (applyPayBean.getCode() == 200) {
                            //关闭当前页面到列表页面
                            finish();
                        } else {
                            ToastUtil.setToast(applyPayBean.getMsg());
                        }
                    }
                });
    }

    //撤销当前申请
    private void revokeApply(String id) {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .revokeApply(id, sign)
                .compose(Transformer.<RevokeApplyBean>switchSchedulers())
                .subscribe(new CommonObserver<RevokeApplyBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(RevokeApplyBean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("撤销成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }
    //复制当前申请
    private void coypRemburesement(String id) {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .copyReimburser(id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers())
                .subscribe(new CommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("复制成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            getRembursementDetails(dataBean.getData().getId() + "");
        }
    }
}