package com.example.bq.edmp.work.order.activity;

import android.app.Dialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.inventory.adapter.BaseCom_Ck_Adapter;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.work.allocation.fragment.AllocationFragment;
import com.example.bq.edmp.work.detection.DetectionRecordListActivity;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.OrderZuoWuBean;
import com.example.bq.edmp.work.order.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 选择商品
 * */
public class GoodsListActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.layout_tab)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    private LoadingDialog loadingDialog;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private String morderid;
    private String customerId;
    private ArrayList<OrderZuoWuBean.DataBean> dataBeans = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_list;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(GoodsListActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("选择商品");

        morderid = getIntent().getStringExtra("orderid");
        customerId = getIntent().getStringExtra("customerId");

    }

    @Override
    protected void initData() {
        gaginData();
    }

    private void gaginData() {
        RxHttpUtils.createApi(OrderApi.class)
                .getZuoWuData()
                .compose(Transformer.<OrderZuoWuBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<OrderZuoWuBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(OrderZuoWuBean orderZuoWuBean) {
                        String code = orderZuoWuBean.getCode();
                        if (code.equals("200")) {
                            List<OrderZuoWuBean.DataBean> data = orderZuoWuBean.getData();
                            gainFragment(data);
                        } else {
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void gainFragment(List<OrderZuoWuBean.DataBean> data) {


        ArrayList<String> IdString = new ArrayList<>();
        ArrayList<String> TabNameString = new ArrayList<>();
        IdString.clear();
        IdString.add("0");
        TabNameString.clear();
        TabNameString.add("全部");
        if (data != null && data.size() != 0) {
            for (int i = 0; i < data.size(); i++) {
                IdString.add(data.get(i).getId());
                TabNameString.add(data.get(i).getName());
            }
        }

        tabLayout.removeAllTabs();
        if (IdString != null && IdString.size() != 0) {
            for (int i = 0; i < IdString.size(); i++) {
                fragmentList.add(new OrderFragment(IdString.get(i), morderid, customerId));
                tabLayout.addTab(tabLayout.newTab().setText(TabNameString.get(i)));
            }
        }
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(GoodsListActivity.this);
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


}
