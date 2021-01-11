package com.example.bq.edmp.work.approvalmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApprovalAdp;
import com.example.bq.edmp.activity.apply.LocalNewMedia;
import com.example.bq.edmp.activity.apply.PayInfoAdp;
import com.example.bq.edmp.activity.apply.ReimbursementApi;
import com.example.bq.edmp.activity.apply.bean.ApplyPayBean;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.activity.apply.bean.IntetnCode;
import com.example.bq.edmp.activity.apply.bean.PayReimbursementDetailsInfo;
import com.example.bq.edmp.activity.apply.bean.RevokeApplyBean;
import com.example.bq.edmp.activity.apply.travel.AddTravelDayInfoAct;
import com.example.bq.edmp.activity.apply.travel.OtherExpensesAct;
import com.example.bq.edmp.activity.apply.travel.OtherExpensesAdp;
import com.example.bq.edmp.activity.apply.travel.activity.CopyTravelDetailAct;
import com.example.bq.edmp.activity.apply.travel.activity.UpdataDetailAddTravelDayInfoAct;
import com.example.bq.edmp.activity.apply.travel.activity.UpdateTravelOtherExpensesAct;
import com.example.bq.edmp.activity.apply.travel.adapter.TravelDetailDayInfoAdp;
import com.example.bq.edmp.activity.apply.travel.bean.TravelDetailsInfo;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.ActivityUtils;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.approvalmanagement.adapter.TravelApprovalAdp;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 差旅报账审批
 */
public class TravelApprovalActivity extends BaseTitleActivity {

    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, TravelApprovalActivity.class);
        intent.putExtra("id", id);
        return intent;
    }
    @BindView(R.id.btn_add_info)
    LinearLayout mLyAddInfo;
    @BindView(R.id.dtj_tv)
    TextView mTvState;
    @BindView(R.id.img_status)
    ImageView mImgStatus;
    @BindView(R.id.ly_number)
    LinearLayout mLyNumber;
    @BindView(R.id.info_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.other_recyclerview)
    RecyclerView mOtherRecyclerview;
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
    @BindView(R.id.ly_one)
    LinearLayout mLyOne;//报销总额ONE
    @BindView(R.id.ly_two)
    LinearLayout mLyTwo;//报销总额TWO
    @BindView(R.id.tv_all_money_one)
    TextView mTvAllMoneyOne;//报销总额
    @BindView(R.id.ly_remark)
    LinearLayout mLyRemark;//报销说明父布局
    @BindView(R.id.ly_disparity)
    LinearLayout mLyDisparity;//应退补金额父布局
    @BindView(R.id.tv_disparity)
    TextView mTvDisparity;//应退补金额
    @BindView(R.id.btn_refuse)
    TextView mBtnRefuse;//拒绝
    @BindView(R.id.tv_agree)
    TextView mTvAgree;//同意
    private TravelDetailDayInfoAdp mAdapter;//日程信息适配器
    private OtherExpensesAdp mOtherAdapter;//其他费用适配器
    private TravelApprovalAdp mApprovalAdapter;//审批流适配器
    private final int CITY_CAR_MONEY_CODE = 1;
    private TravelDetailsInfo dataBean = new TravelDetailsInfo();
    private PayInfoBean item1 = null;
    private PayInfoBean item2 = null;
    private PayInfoBean item3 = null;
    private PayInfoBean item4 = null;
    private PayInfoBean item5 = null;
    private List<PayInfoBean> mList;
    private ILoadingView loading_dialog;
    private String id = "";
    PopupWindow mTypePopuWindow;
    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        if ("".equals(id)) {
            ToastUtil.setToast("系统异常");
            finish();
        }
        initOtherData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new TravelDetailDayInfoAdp(null, 0);
        mRecyclerView.setAdapter(mAdapter);
        //添加其他费用
        mOtherAdapter.setOnClickLisenter(new OtherExpensesAdp.OnClickLisenter() {
            @Override
            public void onClick(int position, PayInfoBean bean) {
                if (dataBean.getData().getStatus() == 1) {
                    if ("".equals(bean.getIdx()) || null == bean.getIdx()) {
                        //添加其他费用
                        startActivityForResult(OtherExpensesAct.newIntent(TravelApprovalActivity.this, bean.getDesc(), position, dataBean.getData().getId() + ""), CITY_CAR_MONEY_CODE);
                    } else {
                        //修改其他费用
                        IntetnCode intetnCode = new IntetnCode();
                        intetnCode.setId(dataBean.getData().getId());
                        intetnCode.setIdx(Integer.parseInt(bean.getIdx()));
                        startActivityForResult(UpdateTravelOtherExpensesAct.newIntent(getApplicationContext(), intetnCode), 1);
                    }
                }
            }
        });
        mOtherAdapter.setOnPicLisenter(new PayInfoAdp.OnPicLisenter() {
            @Override
            public void onPicClick(int themeId, int position, List<LocalMedia> selectList) {
                PictureSelector.create(TravelApprovalActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
            }
        });
        getRembursementDetails(id);
        List<PayInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PayInfoBean bean = new PayInfoBean();
            bean.setId(i + "");
            bean.setDesc("啊啊啊啊");
            list.add(bean);
        }
        mApprovalRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mApprovalAdapter = new TravelApprovalAdp(list);
        mApprovalRecyclerView.setAdapter(mApprovalAdapter);


        //点击查看大图
        mAdapter.setOnPicLisenter(new TravelDetailDayInfoAdp.OnPicLisenter() {
            @Override
            public void onPicClick(int themeId, int position, List<LocalMedia> selectList) {
                PictureSelector.create(TravelApprovalActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
            }
        });
    }

    private void setPayDetails(TravelDetailsInfo.DataBean bean) {
        if (bean.getStatus() == 1) {
            mLyApproval.setVisibility(View.GONE);
            mLyNumber.setVisibility(View.GONE);
            mLyAddInfo.setVisibility(View.VISIBLE);
            mAdapter.setShowicon(1);
            mOtherAdapter.setShowicon(1);
            mLyOne.setVisibility(View.VISIBLE);
            mLyTwo.setVisibility(View.GONE);
        } else {
            mLyOne.setVisibility(View.GONE);
            mLyTwo.setVisibility(View.VISIBLE);
            mTvReason.setEnabled(false);
            mTvRemark.setEnabled(false);
            mTvMoney.setEnabled(false);
        }
        String reason = "";
        switch (bean.getStatus()) {
            case 1:
                //待提交状态下
                reason = "待提交";
                mTvState.setText("待提交");
                break;
            case 2:
                //待审批状态下
                reason = "审批中";
                mTvState.setText("审批中");
                break;
            case 3:
                //已审批状态下
                reason = "审批通过";
                mTvState.setText("审批通过");
                break;
            case 4:
                //已完成状态下
                reason = "已完成";
                mTvState.setVisibility(View.GONE);
                mImgStatus.setVisibility(View.VISIBLE);
                mImgStatus.setImageDrawable(getResources().getDrawable(R.drawable.property_1yiwancheng));
                mLyDisparity.setVisibility(View.VISIBLE);
                break;
            case -1:
                reason = "已删除";
                mTvState.setText("已删除");
                break;
            case -2:
                //审批拒绝状态下
                reason = "审批拒绝";
                mTvState.setVisibility(View.GONE);
                mImgStatus.setVisibility(View.VISIBLE);
                mImgStatus.setImageDrawable(getResources().getDrawable(R.drawable.property_1yijujue));
                break;
            case -3:
                //已撤销状态下
                reason = "已撤销";
                mTvState.setText("已撤销");
                break;
        }
        mTvTitle.setText(bean.getEmpName() + "提出的" + (bean.getTypes() == 1 ? "开支报销申请" : "差旅报销"));
        mTvCompany.setText(bean.getCompanyName());
        mTvDept.setText(bean.getDeptName());
        mTvMoney.setText(MoneyUtils.formatMoney(bean.getAdvanceLoan()));
        mTvStauts.setText(reason);
        mTvReason.setText(bean.getTdReason());
        mTvAllMoney.setText("￥" + MoneyUtils.formatMoney(bean.getAmount()));
        mTvAllMoneyOne.setText("￥" + MoneyUtils.formatMoney(bean.getAmount()));
        mTvDisparity.setText("￥" + MoneyUtils.formatMoney(bean.getDisparity()));
        if ("".equals(bean.getRemark()) || null == bean.getRemark()) {
            mLyRemark.setVisibility(View.GONE);
        } else {
            mTvRemark.setText(bean.getRemark());
        }
        mAdapter.setNewData(bean.getReimburserTravelingItems());
        initOtherDatas(bean);
//        mAdapter.setShowicon(bean.getStatus());
    }

    //初始化其他费用list
    private void initOtherData() {
        mList = new ArrayList<>();
        item1 = new PayInfoBean();
        item1.setDesc("市内车费");
        item1.setMoney("0.00");
        item1.setImg_list(new ArrayList<LocalNewMedia>());
        item1.setPicList(new ArrayList<LocalMedia>());
        mList.add(item1);
        item2 = new PayInfoBean();
        item2.setDesc("住宿费");
        item2.setMoney("0.00");
        item2.setImg_list(new ArrayList<LocalNewMedia>());
        item2.setPicList(new ArrayList<LocalMedia>());
        mList.add(item2);
        item3 = new PayInfoBean();
        item3.setDesc("邮电费");
        item3.setMoney("0.00");
        item3.setImg_list(new ArrayList<LocalNewMedia>());
        item3.setPicList(new ArrayList<LocalMedia>());
        mList.add(item3);
        item4 = new PayInfoBean();
        item4.setDesc("办公用品费");
        item4.setMoney("0.00");
        item4.setImg_list(new ArrayList<LocalNewMedia>());
        item4.setPicList(new ArrayList<LocalMedia>());
        mList.add(item4);
        item5 = new PayInfoBean();
        item5.setDesc("其他");
        item5.setMoney("0.00");
        item5.setImg_list(new ArrayList<LocalNewMedia>());
        item5.setPicList(new ArrayList<LocalMedia>());
        mList.add(item5);
        mOtherRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mOtherAdapter = new OtherExpensesAdp(mList, 0);
        mOtherRecyclerview.setAdapter(mOtherAdapter);
        mOtherRecyclerview.setNestedScrollingEnabled(false);
    }

    //初始化其他费用list
    private void initOtherDatas(TravelDetailsInfo.DataBean bean) {
        for (int i = 0; i < bean.getReimburserItems().size(); i++) {
            if ("市内车费".equals(bean.getReimburserItems().get(i).getName())) {
                TravelDetailsInfo.DataBean.ReimburserItemsBean bean1 = bean.getReimburserItems().get(i);
                mList.get(0).setIdx(bean1.getId().getIdx() + "");
                mList.get(0).setDesc(bean1.getName());
                mList.get(0).setMoney(bean1.getAmount() + "");
                List<LocalNewMedia> list = new ArrayList<LocalNewMedia>();
                for (int j = 0; j < bean.getReimburserItems().get(i).getReimburserItemBills().size(); j++) {
                    LocalNewMedia localNewMedia = new LocalNewMedia();
                    localNewMedia.setPath(bean.getReimburserItems().get(i).getReimburserItemBills().get(j).getUri());
                    list.add(localNewMedia);
                }
                mList.get(0).setImg_list(list);
            }
        }
        for (int i = 0; i < bean.getReimburserItems().size(); i++) {
            if ("住宿费".equals(bean.getReimburserItems().get(i).getName())) {
                TravelDetailsInfo.DataBean.ReimburserItemsBean bean1 = bean.getReimburserItems().get(i);
                mList.get(1).setIdx(bean1.getId().getIdx() + "");
                mList.get(1).setDesc(bean1.getName());
                mList.get(1).setMoney(bean1.getAmount() + "");
                List<LocalNewMedia> list = new ArrayList<LocalNewMedia>();
                for (int j = 0; j < bean.getReimburserItems().get(i).getReimburserItemBills().size(); j++) {
                    LocalNewMedia localNewMedia = new LocalNewMedia();
                    localNewMedia.setPath(bean.getReimburserItems().get(i).getReimburserItemBills().get(j).getUri());
                    list.add(localNewMedia);
                }
                mList.get(1).setImg_list(list);
            }
        }
        for (int i = 0; i < bean.getReimburserItems().size(); i++) {
            if ("邮电费".equals(bean.getReimburserItems().get(i).getName())) {
                TravelDetailsInfo.DataBean.ReimburserItemsBean bean1 = bean.getReimburserItems().get(i);
                mList.get(2).setIdx(bean1.getId().getIdx() + "");
                mList.get(2).setDesc(bean1.getName());
                mList.get(2).setMoney(bean1.getAmount() + "");
                List<LocalNewMedia> list = new ArrayList<LocalNewMedia>();
                for (int j = 0; j < bean.getReimburserItems().get(i).getReimburserItemBills().size(); j++) {
                    LocalNewMedia localNewMedia = new LocalNewMedia();
                    localNewMedia.setPath(bean.getReimburserItems().get(i).getReimburserItemBills().get(j).getUri());
                    list.add(localNewMedia);
                }
                mList.get(2).setImg_list(list);
            }
        }
        for (int i = 0; i < bean.getReimburserItems().size(); i++) {
            if ("办公用品费".equals(bean.getReimburserItems().get(i).getName())) {
                TravelDetailsInfo.DataBean.ReimburserItemsBean bean1 = bean.getReimburserItems().get(i);
                mList.get(3).setIdx(bean1.getId().getIdx() + "");
                mList.get(3).setDesc(bean1.getName());
                mList.get(3).setMoney(bean1.getAmount() + "");
                List<LocalNewMedia> list = new ArrayList<LocalNewMedia>();
                for (int j = 0; j < bean.getReimburserItems().get(i).getReimburserItemBills().size(); j++) {
                    LocalNewMedia localNewMedia = new LocalNewMedia();
                    localNewMedia.setPath(bean.getReimburserItems().get(i).getReimburserItemBills().get(j).getUri());
                    list.add(localNewMedia);
                }
                mList.get(3).setImg_list(list);
            }
        }
        for (int i = 0; i < bean.getReimburserItems().size(); i++) {
            if ("其他".equals(bean.getReimburserItems().get(i).getName())) {
                TravelDetailsInfo.DataBean.ReimburserItemsBean bean1 = bean.getReimburserItems().get(i);
                mList.get(4).setIdx(bean1.getId().getIdx() + "");
                mList.get(4).setDesc(bean1.getName());
                mList.get(4).setMoney(bean1.getAmount() + "");
                List<LocalNewMedia> list = new ArrayList<LocalNewMedia>();
                for (int j = 0; j < bean.getReimburserItems().get(i).getReimburserItemBills().size(); j++) {
                    LocalNewMedia localNewMedia = new LocalNewMedia();
                    localNewMedia.setPath(bean.getReimburserItems().get(i).getReimburserItemBills().get(j).getUri());
                    list.add(localNewMedia);
                }
                mList.get(4).setImg_list(list);
            }
        }
        mOtherAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        mBtnRefuse.setOnClickListener(this);
        mTvAgree.setOnClickListener(this);
        mLyAddInfo.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //TODO 根据审核状态显示title
        txtTabTitle.setText("详情");
        loading_dialog = new LoadingDialog(this);
//        txtTabTitle.setText("详情");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_travel_approval;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_refuse:
                ToastUtil.setToast("已拒绝");
                break;
            case R.id.tv_agree:
                ToastUtil.setToast("已同意");
                break;
        }
    }

    //費用报销详情
    private void getRembursementDetails(String id) {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .getTravelDetails(id, sign)
                .compose(Transformer.<TravelDetailsInfo>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<TravelDetailsInfo>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ActivityUtils.getMsg(errorMsg, getApplicationContext());
                        ;
                    }

                    @Override
                    protected void onSuccess(TravelDetailsInfo bean) {
                        if (bean.getCode() == 200) {
                            dataBean = bean;
                            if (bean.getData().getReimburserTravelingItems().size() <= 0) {
                                mRecyclerView.setVisibility(View.GONE);
                            } else {
                                mRecyclerView.setVisibility(View.VISIBLE);
                            }
                            //查询差旅报账详情
                            setPayDetails(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }
    //审批意见PopuWindow
    private void showMachiningTaskReport() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.allocation_approval, null);
        TextView btn_ok = contentView.findViewById(R.id.btn_ok);
        final EditText remark = contentView.findViewById(R.id.et_remark);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.setToast(remark.getText().toString().trim());
            }
        });
//        mLyView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTypePopuWindow.dismiss();
//            }
//        });
        mTypePopuWindow = new PopupWindow();
        mTypePopuWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mTypePopuWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mTypePopuWindow.setContentView(contentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        mTypePopuWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        mTypePopuWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        // 实例化一个ColorDrawable颜色为透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        mTypePopuWindow.setBackgroundDrawable(dw);
        // 设置SelectPicPopupWindow弹出窗体可点击
        mTypePopuWindow.setFocusable(true);
//        backgroundAlpha(0.4f);
        mTypePopuWindow.setOutsideTouchable(true);
//        mTypePopuWindow.setClippingEnabled(false);
        mTypePopuWindow.showAtLocation(findViewById(R.id.rl_view), Gravity.BOTTOM, 0, 0);
    }

}
