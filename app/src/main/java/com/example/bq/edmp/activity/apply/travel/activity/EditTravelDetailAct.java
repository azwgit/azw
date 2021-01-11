package com.example.bq.edmp.activity.apply.travel.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
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
import com.example.bq.edmp.activity.apply.travel.adapter.TravelDetailDayInfoAdp;
import com.example.bq.edmp.activity.apply.travel.bean.TravelDetailsInfo;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.ActivityUtils;
import com.example.bq.edmp.utils.DataUtils;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.UsualDialogger;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 申请支出报账详情
 */
public class EditTravelDetailAct extends BaseTitleActivity {

    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, EditTravelDetailAct.class);
        intent.putExtra("id", id);
        return intent;
    }

    @BindView(R.id.btn_revoke)
    TextView mBtnRevoke;
    @BindView(R.id.btn_add_info)
    LinearLayout mLyAddInfo;
    @BindView(R.id.dtj_tv)
    TextView mTvState;
    @BindView(R.id.img_status)
    ImageView mImgStatus;
    @BindView(R.id.ly_number)
    LinearLayout mLyNumber;
    @BindView(R.id.btn_del)
    TextView mBtnDel;
    @BindView(R.id.btn_submit)
    TextView mBtnSubmit;
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
    private TravelDetailDayInfoAdp mAdapter;//日程信息适配器
    private OtherExpensesAdp mOtherAdapter;//其他费用适配器
    private ApprovalAdp mApprovalAdapter;//审批流适配器
    private final int CITY_CAR_MONEY_CODE = 1;
    private TravelDetailsInfo dataBean = new TravelDetailsInfo();
    private PayInfoBean item1 = null;
    private PayInfoBean item2 = null;
    private PayInfoBean item3 = null;
    private PayInfoBean item4 = null;
    private PayInfoBean item5 = null;
    private List<PayInfoBean> mList;
    private ILoadingView loading_dialog;
    private String id="";
    private TimePickerView StartTime;//时间选择器
    private UsualDialogger dialog = null;
    @Override
    protected void initData() {
         id = getIntent().getStringExtra("id");
        if ("".equals(id)) {
            ToastUtil.setToast("系统异常");
            finish();
        }
        setStartTime();
        initOtherData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new TravelDetailDayInfoAdp(null, 0);
        mRecyclerView.setAdapter(mAdapter);
        //添加其他费用
        mOtherAdapter.setOnClickLisenter(new OtherExpensesAdp.OnClickLisenter() {
            @Override
            public void onClick(int position, PayInfoBean bean) {
                if (dataBean.getData().getStatus() == 1) {
                    if ("".equals(bean.getIdx())|| null==bean.getIdx()) {
                        //添加其他费用
                        startActivityForResult(OtherExpensesAct.newIntent(EditTravelDetailAct.this, bean.getDesc(), position, dataBean.getData().getId() + ""), CITY_CAR_MONEY_CODE);
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
                PictureSelector.create(EditTravelDetailAct.this).themeStyle(themeId).openExternalPreview(position, selectList);
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
        mApprovalAdapter = new ApprovalAdp(list);
        mApprovalRecyclerView.setAdapter(mApprovalAdapter);


        //点击查看大图
        mAdapter.setOnPicLisenter(new TravelDetailDayInfoAdp.OnPicLisenter() {
            @Override
            public void onPicClick(int themeId, int position, List<LocalMedia> selectList) {
                PictureSelector.create(EditTravelDetailAct.this).themeStyle(themeId).openExternalPreview(position, selectList);
            }
        });
        //删除
        mAdapter.setOnItemDelListener(new TravelDetailDayInfoAdp.OnItemDelListener() {
            @Override
            public void onItemDelClick(int position, TravelDetailsInfo.DataBean.ReimburserTravelingItemsBean bean) {
                deleteApplyPay(bean.getId().getIdx() + "", dataBean.getData().getId() + "", position);
            }
        });
//编辑
        mAdapter.setOnItemEditLisenter(new TravelDetailDayInfoAdp.OnItemEditLisenter() {
            @Override
            public void onItemEditClick(int pos, TravelDetailsInfo.DataBean.ReimburserTravelingItemsBean bean) {
                IntetnCode intetnCode = new IntetnCode();
                intetnCode.setId(dataBean.getData().getId());
                intetnCode.setIdx(bean.getId().getIdx());
                startActivityForResult(UpdataDetailAddTravelDayInfoAct.newIntent(getApplicationContext(), intetnCode), 1);
//                mAdapter.notifyItemChanged(pos);//更新当前这一条数据
//                startActivityForResult(AddPayInfoAct.newIntent(ApplyPayAccountSecondAct.this),CHOOSE_DAY_INFO_CODE);
            }
        });
        mBtnRevoke.setOnClickListener(this);
        mBtnDel.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
        mTvMoney.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        //设置字符过滤
        mTvMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                //删除“.”后面超过2位后的数据
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        mTvMoney.setText(s);
                        mTvMoney.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    mTvMoney.setText(s);
                    mTvMoney.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        mTvMoney.setText(s.subSequence(0, 1));
                        mTvMoney.setSelection(1);
                        return;
                    }
                }
                //包含. 查看. 前面是否有值
                if(s.toString().trim().contains(".")){
                    String  a=s.toString().trim().substring(0, s.toString().trim().indexOf("."));
                    if(a.length()<=0){
                        s = "0" + s;
                        mTvMoney.setText(s);
                        mTvMoney.setSelection(2);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
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
                mBtnDel.setText("保存");
                mBtnSubmit.setText("保存并提交");
                reason = "待提交";
                mTvState.setText("待提交");
                break;
            case 2:
                //待审批状态下
                mBtnDel.setVisibility(View.GONE);
                mBtnSubmit.setVisibility(View.GONE);
                mBtnRevoke.setVisibility(View.VISIBLE);
                reason = "审批中";
                mTvState.setText("审批中");
                break;
            case 3:
                //已审批状态下
                mBtnDel.setVisibility(View.GONE);
                mBtnSubmit.setVisibility(View.GONE);
                reason = "审批通过";
                mTvState.setText("审批通过");
                break;
            case 4:
                //已完成状态下
                mBtnDel.setVisibility(View.GONE);
                mBtnSubmit.setVisibility(View.GONE);
                reason = "已完成";
                mTvState.setVisibility(View.GONE);
                mImgStatus.setVisibility(View.VISIBLE);
                mImgStatus.setImageDrawable(getResources().getDrawable(R.drawable.property_1yiwancheng));
                break;
            case -1:
                reason = "已删除";
                mTvState.setText("已删除");
                break;
            case -2:
                //审批拒绝状态下
                reason = "审批拒绝";
                mBtnSubmit.setText("复制申请单");
                mBtnSubmit.setBackground(getResources().getDrawable(R.drawable.btn_yellow_shape_bg));
                mTvState.setVisibility(View.GONE);
                mImgStatus.setVisibility(View.VISIBLE);
                mImgStatus.setImageDrawable(getResources().getDrawable(R.drawable.property_1yijujue));
                break;
            case -3:
                //已撤销状态下
                mBtnDel.setVisibility(View.GONE);
                mBtnSubmit.setVisibility(View.GONE);
                mBtnRevoke.setVisibility(View.VISIBLE);
                mBtnRevoke.setText("复制申请单");
                reason = "已撤销";
                mTvState.setText("已撤销");
                break;
        }
        mTvTitle.setText(bean.getEmpName() + "提出的" + (bean.getTypes() == 1 ? "开支报销申请" : "差旅报销"));
        mTvCompany.setText(bean.getCompanyName());
        mTvDept.setText(bean.getDeptName());
        if("".equals(mTvMoney.getText().toString().trim())){
            mTvMoney.setText(MoneyUtils.formatMoney(bean.getAdvanceLoan()));
        }
        if("".equals(mTvStauts.getText().toString().trim())){
            mTvStauts.setText(bean.getDatas() + "");
        }
        if("".equals(mTvReason.getText().toString().trim())){
            mTvReason.setText(bean.getTdReason());
        }
        if("".equals(mTvRemark.getText().toString().trim())){
            mTvRemark.setText(bean.getRemark());
        }
        mTvAllMoney.setText("￥" +MoneyUtils.formatMoney(bean.getAmount()));
        mTvAllMoneyOne.setText("￥" +MoneyUtils.formatMoney(bean.getAmount()));
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
                    localNewMedia.setPath(BaseApi.base_img_url + bean.getReimburserItems().get(i).getReimburserItemBills().get(j).getUri());
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
                    localNewMedia.setPath(BaseApi.base_img_url + bean.getReimburserItems().get(i).getReimburserItemBills().get(j).getUri());
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
                    localNewMedia.setPath(BaseApi.base_img_url + bean.getReimburserItems().get(i).getReimburserItemBills().get(j).getUri());
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
                    localNewMedia.setPath(BaseApi.base_img_url + bean.getReimburserItems().get(i).getReimburserItemBills().get(j).getUri());
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
                    localNewMedia.setPath(BaseApi.base_img_url + bean.getReimburserItems().get(i).getReimburserItemBills().get(j).getUri());
                    list.add(localNewMedia);
                }
                mList.get(4).setImg_list(list);
            }
        }
        mOtherAdapter.notifyDataSetChanged();
    }
    //刪除提示dialog
    public void showUsualDialog() {
        dialog = UsualDialogger.Builder(this)
                .setTitle("删除提示")
                .setMessage("删除后此报账申请单将不可恢复，确认要删除？")
                .setOnConfirmClickListener("确定", new UsualDialogger.onConfirmClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteApply(dataBean.getData().getId()+"");
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
    @Override
    protected void initListener() {
        mBtnDel.setOnClickListener(this);
        mLyAddInfo.setOnClickListener(this);
        mTvStauts.setOnClickListener(this);
        ivOperate.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //TODO 根据审核状态显示title
        txtTabTitle.setText("差旅报账修改");
        ivOperate.setVisibility(View.VISIBLE);
        loading_dialog = new LoadingDialog(this);
//        txtTabTitle.setText("详情");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_edit_travel_detail;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_del:
                checkSaveRembursemeng();
                break;
            case R.id.btn_submit:
                checkRembursementInfo();
                break;
            case R.id.btn_revoke:
                //else 走撤销功能
                if ("撤销".equals(mBtnRevoke.getText().toString().trim())) {
                    revokeApply(dataBean.getData().getId() + "");
                } else {
                    coypRemburesement(dataBean.getData().getId() + "");
                }
                break;
            case R.id.btn_add_info:
                PayInfoBean payInfoBean = new PayInfoBean();
                payInfoBean.setClickType(1);
                payInfoBean.setId(dataBean.getData().getId() + "");
                startActivityForResult(AddTravelDayInfoAct.newIntent(this, payInfoBean), 2);
                break;
            case R.id.img_operate:
                showUsualDialog();
                break;
            case R.id.tv_stauts:
                StartTime.show();
                break;
        }
    }
    //报销日期
    private void setStartTime() {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        selectedDate.set(2200, 12, 31);
        //时间选择器 ，自定义布局
        StartTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mTvStauts.setText(DataUtils.getTime(date,"yyyy-MM-dd"));
            }
        })
                .setCancelText("取消")
                .setSubmitText("确认")
                .setContentTextSize(18)
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setBgColor(Color.WHITE)
                .setTitleBgColor(getResources().getColor(R.color.colorF1))
                .setCancelColor(getResources().getColor(R.color.color33))
                .setSubmitColor(getResources().getColor(R.color.appThemeColor))
                .setTextColorCenter(Color.BLACK)
                .setDate(startDate)
//                .setRangDate(startDate, selectedDate)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .build();
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
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
                    }

                    @Override
                    protected void onSuccess(TravelDetailsInfo bean) {
                        if (bean.getCode() == 200) {
                            dataBean = bean;
                            if(bean.getData().getReimburserTravelingItems().size()<=0){
                                mRecyclerView.setVisibility(View.GONE);
                            }else{
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

    //删除开支选项
    private void deleteApplyPay(String idx, String reimburserId, int position) {
        final int pos = position;
        String sign = MD5Util.encode("idx=" + idx + "&reimburserId=" + reimburserId);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .deleteTraveling(idx, reimburserId, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if (baseABean.getCode() == 200) {
                            mAdapter.remove(pos);
                            mAdapter.notifyItemRemoved(pos);
                            if(dataBean.getData().getReimburserTravelingItems().size()<=0){
                                mRecyclerView.setVisibility(View.GONE);
                            }
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
                .compose(Transformer.<PayReimbursementDetailsInfo>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<PayReimbursementDetailsInfo>() {
                    @Override
                    protected void onError(String errorMsg) {
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
                    }

                    @Override
                    protected void onSuccess(PayReimbursementDetailsInfo bean) {
                        if (bean.getCode() == 200) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
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
//        if ("".equals(mContent)) {
//            ToastUtil.setToast("请输入报销说明");
//            return;
//        }
        submitRembursement(mMoeny, mTvStauts.getText().toString().trim(), dataBean.getData().getId() + "", mContent, mReason, dataBean.getData().getTypes() + "");
    }
    private void checkSaveRembursemeng(){
        String mReason = mTvReason.getText().toString().trim();
        if ("".equals(mReason)) {
            ToastUtil.setToast("请输入出差事由");
            return;
        }
        String mMoeny = mTvMoney.getText().toString().trim();
//        if ("".equals(mMoeny)) {
//            ToastUtil.setToast("请输入预借旅费金额");
//            return;
//        }
        String dates=mTvStauts.getText().toString().trim();
        String mContent = mTvRemark.getText().toString().trim();
        saveRembursement(mMoeny,dates , dataBean.getData().getId() + "", mContent, mReason, dataBean.getData().getTypes() + "");
    }
    //保存申请支出报账
    private void saveRembursement(String mEtMoney, String mTvDate, String id, String remark, String mEtReason, String types) {
        String sign = MD5Util.encode("advanceLoan=" + mEtMoney + "&dates=" + mTvDate + "&id=" + id + "&remark=" + remark + "&tdReason=" + mEtReason + "&types=" + types);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .upDataReimburser(mEtMoney, mTvDate, id, remark, mEtReason, types, sign)
                .compose(Transformer.<ApplyPayBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<ApplyPayBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
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
    //保存并提交差旅报账
    private void submitRembursement(String mEtMoney, String mTvDate, String id, String remark, String mEtReason, String types) {
        String sign = MD5Util.encode("advanceLoan=" + mEtMoney + "&dates=" + mTvDate + "&id=" + id + "&remark=" + remark + "&tdReason=" + mEtReason + "&types=" + types);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .saveAndComintReimburser(mEtMoney, mTvDate, id, remark, mEtReason, types, sign)
                .compose(Transformer.<ApplyPayBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<ApplyPayBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
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
                .compose(Transformer.<RevokeApplyBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<RevokeApplyBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
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
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("复制成功");
                            CopyTravelDetailAct.newIntent(getApplicationContext(), bean.getData() + "");
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
