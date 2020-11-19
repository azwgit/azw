package com.example.bq.edmp.activity.apply.travel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.AddPayInfoAct;
import com.example.bq.edmp.activity.apply.ApplyPayAccountSecondAct;
import com.example.bq.edmp.activity.apply.GridImageAdapter;
import com.example.bq.edmp.activity.apply.LocalNewMedia;
import com.example.bq.edmp.activity.apply.PayInfoAdp;
import com.example.bq.edmp.activity.apply.PayInfoDetailAct;
import com.example.bq.edmp.activity.apply.ReimbursementApi;
import com.example.bq.edmp.activity.apply.bean.ApplyPayBean;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.activity.apply.travel.activity.UpdataAddTravelDayInfoAct;
import com.example.bq.edmp.activity.apply.travel.activity.UpdateOtherExpensesAct;
import com.example.bq.edmp.activity.apply.travel.bean.TravelDetailsInfo;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.ActivityUtils;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * description 申请差旅报账第二步
 * Created by ljw on 2020/11/7.
 */
public class ApplyTravelAccountSecondAct extends BaseTitleActivity {

    public static void newIntent(Context context, ApplyPayBean applyPayBean) {
        Intent intent = new Intent(context, ApplyTravelAccountSecondAct.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.ID, applyPayBean);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_add_info)
    LinearLayout mLyAddInfo;
    @BindView(R.id.tv_all_money)
    TextView mTvAllMoney;
    @BindView(R.id.other_recyclerview)
    RecyclerView mOtherRecyclerview;

    @BindView(R.id.btn_save)
    TextView mBtnSave;
    @BindView(R.id.btn_save_submit)
    TextView mBtnSaveSubmit;
    private final int CITY_CAR_MONEY_CODE = 1;
    private final int CHOOSE_DAY_INFO_CODE = 2;
    private PayInfoBean payInfoBean = new PayInfoBean();
    private int themeId;
    private TravelDayInfoAdp mAdapter;
    private OtherExpensesAdp mOtherAdapter;
    private PayInfoBean mTempBean;//其他费用实体
    private ApplyPayBean applyPayBean = null;//接收添加面传过来的信息
    @BindView(R.id.tv_name)
    TextView mTvName;//报账人
    @BindView(R.id.tv_dept)
    TextView mTvDept;//所在部门
    @BindView(R.id.tv_date)
    TextView mTvDate;//报销日期
    @BindView(R.id.tv_reason)
    EditText mTvReason;//出差事由
    @BindView(R.id.tv_money)
    EditText mTvMoney;//预借旅费
    @BindView(R.id.ed_content)
    EditText mEdContent;//报销说明
    @BindView(R.id.ly_time)
    LinearLayout mLyTime;//报销日期父布局
    private ILoadingView loading_dialog;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_apply_travel_account_second;
    }

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        txtTabTitle.setText("申请差旅报账");
        loading_dialog = new LoadingDialog(this);
        themeId = R.style.picture_QQ_style;
    }

    @Override
    protected void initData() {

        applyPayBean = (ApplyPayBean) getIntent().getSerializableExtra(Constant.ID);
        if (null == applyPayBean) {
            ToastUtil.setToast("参数错误");
            finish();
        }
        mTvDate.setText(applyPayBean.getData().getDates());
        mTvReason.setText(applyPayBean.getData().getTdReason());
        mTvMoney.setText(applyPayBean.getData().getAdvanceLoan() + "");
        mTvName.setText(applyPayBean.getData().getEmpName());
        mTvDept.setText(applyPayBean.getData().getDeptName());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new TravelDayInfoAdp(null);
        mRecyclerView.setAdapter(mAdapter);
        initOtherData();
        //添加其他费用
        mOtherAdapter.setOnClickLisenter(new OtherExpensesAdp.OnClickLisenter() {
            @Override
            public void onClick(int position, PayInfoBean bean) {
                mTempBean = bean;
                if(null==bean.getIdx()||"".equals(bean.getIdx())){
                    //添加其他费用
                    startActivityForResult(OtherExpensesAct.newIntent(ApplyTravelAccountSecondAct.this, bean.getDesc(),position,applyPayBean.getData().getId()+""), CITY_CAR_MONEY_CODE);
                }else{
                    //修改其他费用
                    payInfoBean=new PayInfoBean();
                    payInfoBean.setPosition(position);
                    payInfoBean.setIdx(bean.getIdx());
                    payInfoBean.setId(applyPayBean.getData().getId() + "");
                    startActivityForResult(UpdateOtherExpensesAct.newIntent(ApplyTravelAccountSecondAct.this, payInfoBean), CITY_CAR_MONEY_CODE);
                }
            }
        });
        mOtherAdapter.setOnPicLisenter(new PayInfoAdp.OnPicLisenter() {
            @Override
            public void onPicClick(int themeId, int position, List<LocalMedia> selectList) {
                PictureSelector.create(ApplyTravelAccountSecondAct.this).themeStyle(themeId).openExternalPreview(position, selectList);
            }
        });
        //删除
        mAdapter.setOnItemDelListener(new TravelDayInfoAdp.OnItemDelListener() {
            @Override
            public void onItemDelClick(int position, PayInfoBean bean) {
                deleteTraveling(bean.getIdx()+"",bean.getId()+"",position);
            }
        });

        //编辑
        mAdapter.setOnItemEditLisenter(new TravelDayInfoAdp.OnItemEditLisenter() {
            @Override
            public void onItemEditClick(int pos, PayInfoBean bean) {
                payInfoBean=new PayInfoBean();
                payInfoBean.setPosition(pos);
                payInfoBean.setIdx(bean.getIdx());
                payInfoBean.setId(applyPayBean.getData().getId() + "");
                payInfoBean.setClickType(2);
                payInfoBean.setImg_list(bean.getImg_list());
                startActivityForResult(UpdataAddTravelDayInfoAct.newIntent(ApplyTravelAccountSecondAct.this,payInfoBean), CHOOSE_DAY_INFO_CODE);
            }
        });
        //点击查看大图
        mAdapter.setOnPicLisenter(new TravelDayInfoAdp.OnPicLisenter() {
            @Override
            public void onPicClick(int themeId, int position, List<LocalMedia> selectList) {
                PictureSelector.create(ApplyTravelAccountSecondAct.this).themeStyle(themeId).openExternalPreview(position, selectList);
            }
        });
    }

    //初始化其他费用list
    private void initOtherData(){
        List<PayInfoBean> mList = new ArrayList<>();
        PayInfoBean item1 = new PayInfoBean();
        item1.setDesc("市内车费");
        item1.setImg_list(new ArrayList<LocalNewMedia>());
        item1.setPicList(new ArrayList<LocalMedia>());
        mList.add(item1);
        PayInfoBean item2 = new PayInfoBean();
        item2.setDesc("住宿费");
        item2.setImg_list(new ArrayList<LocalNewMedia>());
        item2.setPicList(new ArrayList<LocalMedia>());
        mList.add(item2);
        PayInfoBean item3 = new PayInfoBean();
        item3.setDesc("邮电费");
        item3.setImg_list(new ArrayList<LocalNewMedia>());
        item3.setPicList(new ArrayList<LocalMedia>());
        mList.add(item3);
        PayInfoBean item4 = new PayInfoBean();
        item4.setDesc("办公用品费");
        item4.setImg_list(new ArrayList<LocalNewMedia>());
        item4.setPicList(new ArrayList<LocalMedia>());
        mList.add(item4);
        PayInfoBean item5 = new PayInfoBean();
        item5.setDesc("其他费用");
        item5.setImg_list(new ArrayList<LocalNewMedia>());
        item5.setPicList(new ArrayList<LocalMedia>());
        mList.add(item5);
        mOtherRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mOtherAdapter = new OtherExpensesAdp(mList,1);
        mOtherRecyclerview.setAdapter(mOtherAdapter);
        mOtherRecyclerview.setNestedScrollingEnabled(false);
    }
    @Override
    protected void initListener() {
        mBtnSave.setOnClickListener(this);
        mBtnSaveSubmit.setOnClickListener(this);
        mLyAddInfo.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_info://添加日程信息
                payInfoBean=new PayInfoBean();
                payInfoBean.setClickType(1);
                payInfoBean.setId(applyPayBean.getData().getId()+"");
                startActivityForResult(AddTravelDayInfoAct.newIntent(this,payInfoBean), CHOOSE_DAY_INFO_CODE);
                break;
            case R.id.btn_save://保存
                checkRembursementInfo();
//                startActivity(PayInfoDetailAct.newIntent(this, "1"));
                break;
            case R.id.btn_save_submit://保存并提交
                checkSaveAndComintRembursementInfo();
                break;
        }
    }
    //驗證提交信息
    private void checkRembursementInfo() {
        String time = mTvDate.getText().toString().trim();
        if ("".equals(time)) {
            ToastUtil.setToast("请选择日期");
            return;
        }
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
        String mContent = mEdContent.getText().toString().trim();
//        if ("".equals(mContent)) {
//            ToastUtil.setToast("请输入报销说明");
//            return;
//        }
        if(mReason.length()>16){
            ToastUtil.setToast("报销事由最多只能添加16个字");
            return;
        }
        submitRembursement(mMoeny, time, applyPayBean.getData().getId() + "", mContent, mReason, "2");
    }
    //保存差旅报账
    private void submitRembursement(String mEtMoney, String mTvDate, String id, String remark, String mEtReason, String types) {
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
    //保存并提交驗證提交信息
    private void checkSaveAndComintRembursementInfo() {
        String time = mTvDate.getText().toString().trim();
        if ("".equals(time)) {
            ToastUtil.setToast("请选择日期");
            return;
        }
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
        if(mReason.length()>16){
            ToastUtil.setToast("报销事由最多只能添加16个字");
            return;
        }
        String mContent = mEdContent.getText().toString().trim();
//        if ("".equals(mContent)) {
//            ToastUtil.setToast("请输入报销说明");
//            return;
//        }
        saveAndComitRembursement(mMoeny, time, applyPayBean.getData().getId() + "", mContent, mReason, "2");
    }
    //保存并提交申请支出报账
    private void saveAndComitRembursement(String mEtMoney, String mTvDate, String id, String remark, String mEtReason, String types) {
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
    //删除日程信息
    private void deleteTraveling(String idx,String reimburserId, int position) {
        final int pos=position;
        String sign = MD5Util.encode("idx=" + idx + "&reimburserId="+reimburserId);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .deleteTraveling(idx,reimburserId,sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
                    }
                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if (applyPayBean.getCode() == 200) {
                            mAdapter.remove(pos);
                            mAdapter.notifyItemRemoved(pos);
                            getRembursementDetails(payInfoBean.getId());
                        } else {
                            ToastUtil.setToast(baseABean.getMsg());
                        }
                    }
                });
    }
    //暂时放弃自己计算总额 每次返回此界面调用详情接口取 报销款
    private void getRembursementDetails(String id) {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .getTravelDetails(id, sign)
                .compose(Transformer.<TravelDetailsInfo>switchSchedulers())
                .subscribe(new CommonObserver<TravelDetailsInfo>() {
                    @Override
                    protected void onError(String errorMsg) {
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
                    }

                    @Override
                    protected void onSuccess(TravelDetailsInfo bean) {
                        if (bean.getCode() == 200) {
                            mTvAllMoney.setText(bean.getData().getAmount()+"");
                            if(bean.getData().getReimburserTravelingItems().size()<=0){
                                mRecyclerView.setVisibility(View.GONE);
                            }else{
                                mRecyclerView.setVisibility(View.VISIBLE);
                            }
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
            getRembursementDetails(payInfoBean.getId());
            switch (requestCode) {
                case CITY_CAR_MONEY_CODE:
                    PayInfoBean item = (PayInfoBean) data.getSerializableExtra("payInfo");
                    mTempBean.setMoney(item.getMoney());
                    mTempBean.setImg_list(item.getImg_list());
                    mTempBean.setIdx(item.getIdx());
                    int pos = Integer.parseInt(item.getPosition()+"");
                    mOtherAdapter.notifyItemChanged(pos,mTempBean);
                    break;
                case CHOOSE_DAY_INFO_CODE:
                    payInfoBean = (PayInfoBean) data.getSerializableExtra("payInfo");
                    //有数据才展示列表
                    mRecyclerView.setVisibility(View.VISIBLE);
                    //刷新 必须 优化 点击编辑  因三方库 实体类 序列化问题  应统一修改
                    if (payInfoBean.getClickType() == 1) {
                        mAdapter.addData(payInfoBean);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        mAdapter.remove(payInfoBean.getPosition());
                        mAdapter.notifyItemRemoved(payInfoBean.getPosition());
                        mAdapter.addData(payInfoBean.getPosition(), payInfoBean);
                        mAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }

    }
}
