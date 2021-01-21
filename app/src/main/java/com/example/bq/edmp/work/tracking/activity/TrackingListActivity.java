package com.example.bq.edmp.work.tracking.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.work.tracking.fragment.TrackingListFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class TrackingListActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.layout_tab)
    TabLayout layout_tab;
    @BindView(R.id.view_pager)
    ViewPager view_pager;

    private LoadingDialog loadingDialog;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tracking_list;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(TrackingListActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("活动跟踪");

    }


    @Override
    protected void initData() {

        ArrayList<String> IdString = new ArrayList<>();
        ArrayList<String> TabNameString = new ArrayList<>();

        IdString.clear();
        IdString.add("0");
        IdString.add("2");
        IdString.add("3");
        IdString.add("4");
        TabNameString.clear();
        TabNameString.add("全部");
        TabNameString.add("审批中");
        TabNameString.add("审批通过");
        TabNameString.add("审批拒绝");


        layout_tab.removeAllTabs();
        if (IdString != null && IdString.size() != 0) {
            for (int i = 0; i < IdString.size(); i++) {
                fragmentList.add(new TrackingListFragment(IdString.get(i)));
                layout_tab.addTab(layout_tab.newTab().setText(TabNameString.get(i)));
            }
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
        ProApplication.getinstance().finishActivity(TrackingListActivity.this);
    }
}
