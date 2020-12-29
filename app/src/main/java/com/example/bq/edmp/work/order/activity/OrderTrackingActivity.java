package com.example.bq.edmp.work.order.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.work.order.bean.StatusBean;
import com.example.bq.edmp.work.order.fragment.OrderTrackingFragment;
import java.util.ArrayList;
import butterknife.BindView;

/*
 * 订单跟踪
 * */
public class OrderTrackingActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.layout_tab)
    TabLayout layout_tab;
    @BindView(R.id.view_pager)
    ViewPager view_pager;


    //private String[] tabstring = {"全部", "待分配", "待回款", "待发货", "配送中"};
    private String[] tabstring = {"全部", "待分配", "待回款", "备货中","待发货", "部份发货", "在途"};
    //状态： 0 全部3待分配 4待回款 5备货中 6 待发货 7部份发货 8在途
    private int[] idstring = {0, 3, 4, 5, 6, 7, 8};
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_tracking;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(OrderTrackingActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("订单跟踪");

        wsj.setVisibility(View.GONE);

    }

    @Override
    protected void initData() {

/*RxHttpUtils.createApi(OrderApi.class)
                .getStatusData()
                .compose(Transformer.<StatusBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<StatusBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(StatusBean statusBean) {
                        int code = statusBean.getCode();
                        if (code == 200) {
                            ll.setVisibility(View.VISIBLE);
                            wsj.setVisibility(View.GONE);
                            gainTabData(statusBean);

                        } else {
                            ll.setVisibility(View.GONE);
                            wsj.setVisibility(View.VISIBLE);


                        }
                    }
                });*/

        layout_tab.removeAllTabs();
        for (int i = 0; i < tabstring.length; i++) {
            fragmentList.add(new OrderTrackingFragment(idstring[i]));
            layout_tab.addTab(layout_tab.newTab().setText(tabstring[i]));
        }
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager(), fragmentList);
        view_pager.setAdapter(adapter);
        layout_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view_pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layout_tab));

    }

    private void gainTabData(StatusBean statusBean) {

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
        ProApplication.getinstance().finishActivity(OrderTrackingActivity.this);
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
