package com.example.bq.edmp.work.marketingactivities.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApprovalAdp;
import com.example.bq.edmp.activity.apply.GridImageAdapter;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.marketingactivities.adapter.EnclosureAdapter;
import com.example.bq.edmp.work.marketingactivities.api.MarketingActivitiesApi;
import com.example.bq.edmp.work.marketingactivities.bean.MarketingActivitiesDetailsBean;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HistoricalActivitiesDetailsActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, HistoricalActivitiesDetailsActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.tv_enclosure)
    TextView mTvEnclosure;//活动附件
    @BindView(R.id.tv_activity_site)
    TextView mTvActivitySite;//活动现场
    @BindView(R.id.tv_title)
    TextView mTvTitle;//活动名称
    @BindView(R.id.tv_company)
    TextView mTvCompany;//公司名称
    @BindView(R.id.tv_person)
    TextView mTvPerson;//活动负责人
    @BindView(R.id.tv_start_time)
    TextView mTvStartTime;//活动开始时间
    @BindView(R.id.tv_end_time)
    TextView mTvEndTime;//完成时间
    @BindView(R.id.tv_money)
    TextView mTvMoney;//预算经费
    @BindView(R.id.tv_person_in_charge)
    TextView mTvPersonInCharge;//实际负责人
    @BindView(R.id.tv_start_end_time)
    TextView mTvStartEndTime;//活动时间
    @BindView(R.id.tv_address)
    TextView mTvAddress;//活动地址
    @BindView(R.id.tv_active_customers)
    TextView mTvActiveCustomers;//活动客户
    @BindView(R.id.tv_purpose)
    TextView mTvPurpose;//活动目的
    private ILoadingView loading_dialog;
    private String id = "";
    private MarketingActivitiesDetailsBean marketingActivitiesDetailsBean = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_historical_activities_details;
    }


    @Override
    protected void initView() {
        txtTabTitle.setText("活动详情");
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        getMarketingActivitiesDetails("");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTvEnclosure.setOnClickListener(this);
        mTvActivitySite.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_enclosure:
                ActivityEnclosureActivity.newIntent(getApplicationContext(), id);
                //活动附件
                break;
            case R.id.tv_activity_site:
                ActivitySiteActivity.newIntent(getApplicationContext(), id);
                //活动现场
                break;
        }
    }

    //获取活动详情
    private void getMarketingActivitiesDetails(String type) {
        String sign = MD5Util.encode("id=" + id + "&type=" + type);
        RxHttpUtils.createApi(MarketingActivitiesApi.class)
                .getActivitieDetails(id, type, sign)
                .compose(Transformer.<MarketingActivitiesDetailsBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<MarketingActivitiesDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(MarketingActivitiesDetailsBean bean) {
                        marketingActivitiesDetailsBean = bean;
                        if (bean.getCode() == 200) {
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //详情赋值
    private void setData(MarketingActivitiesDetailsBean.DataBean bean) {
        mTvTitle.setText(bean.getName());
        mTvCompany.setText(bean.getOrgName());
        mTvPerson.setText(bean.getAddedOperator());
        mTvStartTime.setText(bean.getStartTime());
        mTvEndTime.setText(bean.getFinishedTime());
        mTvMoney.setText("￥" + MoneyUtils.formatMoney(bean.getAdvanceLoan()));
        mTvPersonInCharge.setText(bean.getResponsiblePeople());
        mTvStartEndTime.setText(bean.getStartTime() + " 至 " + bean.getEndTime());
        mTvAddress.setText(bean.getRegionName() + "  " + bean.getAddress());
        mTvActiveCustomers.setText(bean.getCustomerName());
        mTvPurpose.setText(bean.getPurpose());
    }
}