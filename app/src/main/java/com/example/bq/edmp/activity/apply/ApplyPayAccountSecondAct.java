package com.example.bq.edmp.activity.apply;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.example.bq.edmp.activity.apply.activity.UpdatePayInfoAct;
import com.example.bq.edmp.activity.apply.bean.ApplyPayBean;
import com.example.bq.edmp.activity.apply.travel.bean.TravelDetailsInfo;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.ActivityUtils;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.DataUtils;
import com.example.bq.edmp.utils.GridItemDecoration;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.DoubleUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * description 申请支出报账第二步
 * Created by ljw on 2020/11/7.
 */
public class ApplyPayAccountSecondAct extends BaseTitleActivity {
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_add_info)
    LinearLayout mLyAddInfo;
    @BindView(R.id.tv_all_money)
    TextView mTvAllMoney;
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

    @BindView(R.id.btn_save)
    TextView mBtnSave;
    @BindView(R.id.btn_save_submit)
    TextView mBtnSaveSubmit;
    private final int CHOOSE_DAY_INFO_CODE = 1;
    private PayInfoAdp mAdapter;
    private PayInfoBean payInfoBean = new PayInfoBean();
    private ApplyPayBean applyPayBean = null;//接收添加面传过来的信息
    private TimePickerView StartTime;//时间选择器
    private int themeId;
    private ILoadingView loading_dialog;
    public static void newIntent(Context context, ApplyPayBean applyPayBean) {
        Intent intent = new Intent(context, ApplyPayAccountSecondAct.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.ID, applyPayBean);
        intent.putExtras(bundle);
        context.startActivity(intent);
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
        mTvMoney.setText(MoneyUtils.formatMoney(applyPayBean.getData().getAdvanceLoan()));
        mTvName.setText(applyPayBean.getData().getEmpName());
        mTvDept.setText(applyPayBean.getData().getDeptName());
        //时间控件初始化
        setStartTime();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
//        mRecyclerView.addItemDecoration(gridItemDecoration);
        mAdapter = new PayInfoAdp(null);
        mRecyclerView.setAdapter(mAdapter);

        //删除
        mAdapter.setOnItemDelListener(new PayInfoAdp.OnItemDelListener() {
            @Override
            public void onItemDelClick(int position, PayInfoBean bean) {
                deleteApplyPay(bean.getIdx(),applyPayBean.getData().getId()+"",position);
            }
        });

        //编辑
        mAdapter.setOnItemEditLisenter(new PayInfoAdp.OnItemEditLisenter() {
            @Override
            public void onItemEditClick(int pos, PayInfoBean bean) {
                payInfoBean=new PayInfoBean();
                payInfoBean.setPosition(pos);
                payInfoBean.setIdx(bean.getIdx());
                payInfoBean.setId(applyPayBean.getData().getId() + "");
                payInfoBean.setClickType(2);
                payInfoBean.setImg_list(bean.getImg_list());
                startActivityForResult(UpdatePayInfoAct.newIntent(getApplicationContext(), payInfoBean), CHOOSE_DAY_INFO_CODE);
//                mAdapter.notifyItemChanged(pos);//更新当前这一条数据
//                startActivityForResult(AddPayInfoAct.newIntent(ApplyPayAccountSecondAct.this),CHOOSE_DAY_INFO_CODE);
            }
        });
        //点击查看大图
        mAdapter.setOnPicLisenter(new PayInfoAdp.OnPicLisenter() {
            @Override
            public void onPicClick(int themeId, int position, List<LocalMedia> selectList) {
                PictureSelector.create(ApplyPayAccountSecondAct.this).themeStyle(themeId).openExternalPreview(position, selectList);
            }
        });
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

    @Override
    protected void initListener() {
        mBtnSave.setOnClickListener(this);
        mBtnSaveSubmit.setOnClickListener(this);
        mLyAddInfo.setOnClickListener(this);
        mLyTime.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        txtTabTitle.setText("申请支出报账");
        loading_dialog = new LoadingDialog(this);
        themeId = R.style.picture_QQ_style;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_apply_pay_account_second;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_info://添加日程信息
                payInfoBean=new PayInfoBean();
                payInfoBean.setClickType(1);
                payInfoBean.setId(applyPayBean.getData().getId() + "");
                startActivityForResult(AddPayInfoAct.newIntent(this, payInfoBean), CHOOSE_DAY_INFO_CODE);
                break;
            case R.id.btn_save://保存
//                startActivity(PayInfoDetailAct.newIntent(this,"1"));
                checkRembursementInfo();
                break;
            case R.id.btn_save_submit://保存并提交
                checkSaveAndComintRembursementInfo();
                break;
            case R.id.ly_time:
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
                mTvDate.setText(DataUtils.getTime(date, "yyyy-MM-dd"));
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
                .setRangDate(startDate, selectedDate)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .build();
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
        submitRembursement(mMoeny, time, applyPayBean.getData().getId() + "", mContent, mReason, "1");
    }

    //保存申请支出报账
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
        String mContent = mEdContent.getText().toString().trim();
//        if ("".equals(mContent)) {
//            ToastUtil.setToast("请输入报销说明");
//            return;
//        }
        saveAndComitRembursement(mMoeny, time, applyPayBean.getData().getId() + "", mContent, mReason, "1");
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
                            ToastUtil.setToast("请添加开支项");
                        }
                    }
                });
    }
    //删除开支选项
    private void deleteApplyPay(String idx,String reimburserId, int position) {
        final int pos=position;
        String sign = MD5Util.encode("idx=" + idx + "&reimburserId="+reimburserId);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .deleteApplyPay(idx,reimburserId,sign)
                .compose(Transformer.<ApplyPayBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<ApplyPayBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
                    }

                    @Override
                    protected void onSuccess(ApplyPayBean applyPayBean) {
                        if (applyPayBean.getCode() == 200) {
                            mAdapter.remove(pos);
                            mAdapter.notifyItemRemoved(pos);
                            getRembursementDetails(payInfoBean.getId());
                        } else {
                            ToastUtil.setToast(applyPayBean.getMsg());
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
                            mTvAllMoney.setText(MoneyUtils.formatMoney(bean.getData().getAmount()));
                            if(bean.getData().getReimburserItems().size()<=0){
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
        switch (requestCode) {
            case CHOOSE_DAY_INFO_CODE:
                if (resultCode == RESULT_OK) {
                    getRembursementDetails(payInfoBean.getId());
                    payInfoBean = (PayInfoBean) data.getSerializableExtra("payInfo");
                    //有数据才展示列表
                    mRecyclerView.setVisibility(View.VISIBLE);
                    if (payInfoBean.getClickType() == 1) {
                        mAdapter.addData(payInfoBean);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        mAdapter.remove(payInfoBean.getPosition());
                        mAdapter.notifyItemRemoved(payInfoBean.getPosition());
                        mAdapter.addData(payInfoBean.getPosition(), payInfoBean);
                        mAdapter.notifyDataSetChanged();
                        //mAdapter.notifyItemChanged(payInfoBean.getPosition(), payInfoBean);
                    }
                }
                break;
        }
    }
}
