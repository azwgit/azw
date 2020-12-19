package com.example.bq.edmp.work.marketing.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.activity.ApplyPayAccountAct;
import com.example.bq.edmp.activity.apply.travel.activity.ApplyTravelAccountAct;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.DataUtils;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.adapter.AuditChAdapter;
import com.example.bq.edmp.word.adapter.SubmitListAdapter;
import com.example.bq.edmp.word.api.WordListApi;
import com.example.bq.edmp.word.bean.AuditChBean;
import com.example.bq.edmp.word.bean.SubmitListBean;
import com.example.bq.edmp.word.fragment.SubmitFragment;
import com.example.bq.edmp.work.inventorytransfer.activity.EditFinishedProductAllocationActivity;
import com.example.bq.edmp.work.marketing.fragment.CustomerAccountFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/*
 * 报账管理
 * */
public class CustomerAccountActivity extends BaseActivity {
    public static void newIntent(Context context, String type) {
        Intent intent = new Intent(context, CustomerAccountActivity.class);
        intent.putExtra(Constant.TYPE, type);
        context.startActivity(intent);
    }
    @BindView(R.id.layout_tab)
    TabLayout mLayout_tab;
    @BindView(R.id.view_pager)
    ViewPager mView_pager;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.tv_name)
    TextView mTvName;//公司名称
    @BindView(R.id.tv_money)
    TextView mTvMoney;//定金
    @BindView(R.id.tv_balance)
    TextView mTvBalance;//余额

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    ArrayList<String> tablist = new ArrayList<>();
    ArrayList<Integer> integers = new ArrayList<>();
    private String type="";
    @Override
    protected void initData() {
        mLayout_tab.removeAllTabs();
        for (int i = 0; i < integers.size(); i++) {
            fragmentList.add(new CustomerAccountFragment(integers.get(i)));
            mLayout_tab.addTab(mLayout_tab.newTab().setText(tablist.get(i)));
        }
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager(), fragmentList);
        mView_pager.setAdapter(adapter);
        mLayout_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mView_pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mView_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mLayout_tab));

    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mTitleTv.setText("客户账户");
        tablist.clear();
        tablist.add("余额明细");
        tablist.add("定金明细");
        integers.clear();
        integers.add(0);
        integers.add(1);
        type = getIntent().getStringExtra(Constant.TYPE);
        if ("".equals(type)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        if ("2".equals(type)) {
            mTvName.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_account;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.return_img:
                finish();
                break;
        }
    }

    /*
     * adapter
     * */
    public class VPAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> list;

        public VPAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
