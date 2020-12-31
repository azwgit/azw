package com.example.bq.edmp.work.marketingactivities.activity;

import android.Manifest;
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

import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApprovalAdp;
import com.example.bq.edmp.activity.apply.GridImageAdapter;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.marketingactivities.adapter.EnclosureAdapter;
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


    @Override
    protected int getLayoutId() {
        return R.layout.activity_historical_activities_details;
    }


    @Override
    protected void initView() {
        txtTabTitle.setText("活动详情");
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
                startActivity(new Intent(getApplicationContext(), ActivityEnclosureActivity.class));
                //活动附件
                break;
            case R.id.tv_activity_site:
                startActivity(new Intent(getApplicationContext(), ActivitySiteActivity.class));
                //活动现场
                break;
        }
    }
}