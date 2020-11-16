package com.example.bq.edmp.activity.apply.travel.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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
import com.example.bq.edmp.activity.MainActivity;
import com.example.bq.edmp.activity.apply.ApplyPayAccountSecondAct;
import com.example.bq.edmp.activity.apply.ReimbursementApi;
import com.example.bq.edmp.activity.apply.bean.ApplyPayBean;
import com.example.bq.edmp.activity.apply.travel.ApplyTravelAccountSecondAct;
import com.example.bq.edmp.activity.login.LoginApi;
import com.example.bq.edmp.activity.login.UserInfoBean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.LoginBean;
import com.example.bq.edmp.utils.DataUtils;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.SpUtils;
import com.example.bq.edmp.utils.ToastUtil;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;

/**
 * description 申请支出报账
 */
public class ApplyTravelAccountAct extends BaseTitleActivity {

    @BindView(R.id.tv_name)
    TextView mTvName;//名称
    @BindView(R.id.tv_dept)
    TextView mTvDept;//部门
    @BindView(R.id.tv_date)
    TextView mTvDate;//报销日期
    @BindView(R.id.et_reason)
    EditText mEtReason;//出差事由
    @BindView(R.id.et_money)
    EditText mEtMoney;//预借旅费
    @BindView(R.id.btn_save)
    TextView mBtnSave;//保存
    @BindView(R.id.ly_time)
    LinearLayout mLyTime;//选择时间
    private ILoadingView loading_dialog;
    private TimePickerView StartTime;//时间选择器
    @Override
    protected void initData() {
        setStartTime();
        getUserInfo();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        txtTabTitle.setText("申请差旅报账");
        loading_dialog = new LoadingDialog(this);
        mBtnSave.setOnClickListener(this);
        mLyTime.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_apply_pay_account;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.btn_save:
//                Intent intent=new Intent(getApplicationContext(), ApplyPayAccountSecondAct.class);
//                startActivity(intent);
                checkRembursementInfo();
                break;
            case R.id.ly_time:
                StartTime.show();
                break;

        }
    }
    //驗證提交信息
    private void checkRembursementInfo(){
        String time=mTvDate.getText().toString().trim();
        if("".equals(time)){
            ToastUtil.setToast("请选择日期");
            return;
        }
        String mReason=mEtReason.getText().toString().trim();
        if("".equals(mReason)){
            ToastUtil.setToast("请输入出差事由");
            return;
        }
        String mMoeny=mEtMoney.getText().toString().trim();
        if("".equals(mMoeny)){
            ToastUtil.setToast("请输入预借旅费金额");
            return;
        }
        if(mReason.length()>16){
            ToastUtil.setToast("报销事由最多只能添加16个字");
            return;
        }
        submitRembursement( mMoeny,  time, mReason, "2");
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
                mTvDate.setText(DataUtils.getTime(date,"yyyy-MM-dd"));
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
    //差旅报销報銷保存
    private void submitRembursement(String mEtMoney, String mTvDate,String mEtReason,String types ) {
        String sign = MD5Util.encode("advanceLoan=" + mEtMoney + "&dates=" +mTvDate+ "&tdReason="+ mEtReason+"&types="+types);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .submitReimburser(mEtMoney, mTvDate, mEtReason,types,sign)
                .compose(Transformer.<ApplyPayBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<ApplyPayBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }
                    @Override
                    protected void onSuccess(ApplyPayBean applyPayBean) {
                        if(applyPayBean.getCode()==200){
                            //跳转填写差旅报账信息页面
                            finish();
                            ApplyTravelAccountSecondAct.newIntent(getApplicationContext(),applyPayBean);
                        }else{
                            ToastUtil.setToast(applyPayBean.getMsg());
                        }
                    }
                });
    }
    //获取个人信息
    private void getUserInfo() {
        RxHttpUtils.createApi(ReimbursementApi.class)
                .getUserInfo()
                .compose(Transformer.<UserInfoBean>switchSchedulers())
                .subscribe(new CommonObserver<UserInfoBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(UserInfoBean bean) {
                        mTvName.setText(bean.getData().getName());
                        mTvDept.setText(bean.getData().getOrgName());
                    }
                });
    }
}
