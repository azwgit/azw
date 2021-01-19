package com.example.bq.edmp.work.returnsmanagement.activity;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.inventory.adapter.BaseCom_Ck_Adapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.work.allocation.adapter.AllocationInListAdapter;
import com.example.bq.edmp.work.allocation.api.AllocationApi;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.allocation.fragment.AllocationFragment;
import com.example.bq.edmp.work.returnsmanagement.fragment.ReturnGoodsListFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 退单跟踪
 * */
public class ReturnsGoodsListActivity extends BaseActivity {
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.layout_tab)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    ArrayList<String> tablist = new ArrayList<>();
    ArrayList<Integer> integers = new ArrayList<>();
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_return_goods;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(ReturnsGoodsListActivity.this);
        title_tv.setText("退单跟踪");
        initTabLayout();
    }

    //初始化tablayoout
    private void initTabLayout() {
        tablist.clear();
        tablist.add("全部");
        tablist.add("待审批");
        tablist.add("退货中");
        tablist.add("审批拒绝");

        integers.clear();
        integers.add(0);
        integers.add(2);
        integers.add(4);
        integers.add(3);
    }

    @Override
    protected void initData() {
        tabLayout.removeAllTabs();
        for (int i = 0; i < integers.size(); i++) {
            fragmentList.add(new ReturnGoodsListFragment(integers.get(i)));
            tabLayout.addTab(tabLayout.newTab().setText(tablist.get(i)));
        }
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager(), fragmentList);
        view_pager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(ReturnsGoodsListActivity.this);
    }

    /**
     * 隐藏软键盘
     *
     * @param :上下文环境，一般为Activity实例
     * @param view                 :一般为EditText
     */
    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
