package com.example.bq.edmp.work.goodsgrainmanagement.activity;

import android.content.Context;
import android.content.Intent;
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
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import com.example.bq.edmp.word.inventory.adapter.PzAdapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.work.allocation.AllocationCompleteActivity;
import com.example.bq.edmp.work.allocation.adapter.AuditChAdapter2;
import com.example.bq.edmp.work.allocation.bean.AuditChBean2;
import com.example.bq.edmp.work.goodsgrainmanagement.adapter.GoodsSalesConfirmListAdapter;
import com.example.bq.edmp.work.goodsgrainmanagement.fragment.GoodsSalesTrackingListFragment;
import com.example.bq.edmp.work.library.activity.RlibraryActivity;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.OrderTJBean;
import com.example.bq.edmp.work.returnsmanagement.fragment.ReturnGoodsListFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 退单跟踪
 * */
public class GoodsSalesTrackingListActivity extends BaseActivity {
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.top_ll)
    LinearLayout top_ll;
    @BindView(R.id.layout_tab)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    //筛选
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.screen_img)
    ImageView screen_img;
    @BindView(R.id.shai_rv)
    RecyclerView recyclerView;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.chong_tv)
    TextView chong_tv;
    ArrayList<String> tablist = new ArrayList<>();
    ArrayList<Integer> integers = new ArrayList<>();
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private PzAdapter pzAdapter;
    private ArrayList<SxPzBean.DataBean> dataBeans;
    private List<SxPzBean.DataBean> data;
    private String varietyId = "";//品种id
    private int currentPager = 1;
    private GoodsSalesConfirmListAdapter goodsSalesConfirmListAdapter;
    private ArrayList<OrderTJBean.DataBean.RowsBean> rowsBeans;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_sales_tracking_list;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(GoodsSalesTrackingListActivity.this);
        title_tv.setText("商品粮销售跟踪");
        initTabLayout();
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        //筛选  品种适配器
        dataBeans = new ArrayList<>();
        pzAdapter = new PzAdapter(dataBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(GoodsSalesTrackingListActivity.this, 3));
        recyclerView.setAdapter(pzAdapter);
        pzAdapter.setOnItemLeftClckListener(new PzAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(SxPzBean.DataBean dataBean, int mPosition) {
                //当点击时显示当前条目的背景和文字的颜色
                for (int i = 0; i < dataBeans.size(); i++) {
                    if (mPosition == i) {
                        dataBeans.get(i).setSelected(true);
                    } else {
                        dataBeans.get(i).setSelected(false);
                    }
                }
                pzAdapter.notifyDataSetChanged();
                if (!dataBean.getId().equals("") && dataBean.getId() != null) {
                    varietyId = dataBean.getId();
                } else {
                    varietyId = "";
                }
            }
        });
        rowsBeans = new ArrayList<>();
        goodsSalesConfirmListAdapter = new GoodsSalesConfirmListAdapter(rowsBeans);
        xRecyclerView.setAdapter(goodsSalesConfirmListAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                gainData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                initData2();
                xRecyclerView.loadMoreComplete();
            }
        });
        goodsSalesConfirmListAdapter.setOnItemClickListener(new GoodsSalesConfirmListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, OrderTJBean.DataBean.RowsBean rowsBean) {
                GoodsSalesTrackingDetailsActivity.newIntent(getApplicationContext(), rowsBean.getId() + "");
            }
        });
    }

    //初始化tablayoout
    private void initTabLayout() {
        tablist.clear();
        tablist.add("全部");
        tablist.add("审批中");
        tablist.add("待出库");
        tablist.add("待完成");
        tablist.add("审批拒绝");
        integers.clear();
        integers.add(0);
        integers.add(2);
        integers.add(4);
        integers.add(3);
        integers.add(5);
    }

    @Override
    protected void initData() {
        tabLayout.removeAllTabs();
        for (int i = 0; i < integers.size(); i++) {
            fragmentList.add(new GoodsSalesTrackingListFragment(integers.get(i)));
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
        screen_img.setOnClickListener(this);
        chong_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                int visibility = top_ll.getVisibility();
                if (visibility != 0) {
                    top_ll.setVisibility(ViewGroup.VISIBLE);
                    rl.setVisibility(ViewGroup.GONE);
                } else {
                    fund();
                }
                break;
            case R.id.screen_img:
                sxMethodData();
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.chong_tv://筛选 重置
                sxMethodData();
                ToastUtil.setToast("已重置");
                break;
            case R.id.affirm_tv://筛选   确定
                rl.setVisibility(ViewGroup.VISIBLE);
                top_ll.setVisibility(ViewGroup.GONE);
                gainData();
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
                break;
        }
    }

    //品种数据
    private void sxMethodData() {
        RxHttpUtils.createApi(InventoryListApi.class)
                .getPzData()
                .compose(Transformer.<SxPzBean>switchSchedulers())
                .subscribe(new NewCommonObserver<SxPzBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SxPzBean sxPzBean) {
                        data = sxPzBean.getData();
                        if (data.size() != 0 && data != null) {
                            recyclerView.setVisibility(ViewGroup.VISIBLE);
//                            shaixuan_wsj.setVisibility(ViewGroup.GONE);
                            dataBeans.clear();
                            SxPzBean.DataBean dataBean = new SxPzBean.DataBean();
                            dataBean.setSelected(true);
                            dataBean.setId("");
                            dataBean.setName("全部");
                            dataBeans.add(dataBean);
                            dataBeans.addAll(data);
                            pzAdapter.notifyDataSetChanged();
                        } else {
                            recyclerView.setVisibility(ViewGroup.GONE);
//                            shaixuan_wsj.setVisibility(ViewGroup.VISIBLE);
                        }
                    }
                });
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(GoodsSalesTrackingListActivity.this);
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

    //获取列表数据
    private void gainData() {
        currentPager = 1;
        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15);
        RxHttpUtils.createApi(OrderApi.class)
                .getSubmitlist(currentPager, 15, sign)
                .compose(Transformer.<OrderTJBean>switchSchedulers())
                .subscribe(new NewCommonObserver<OrderTJBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(OrderTJBean orderTJBean) {
                        String code = orderTJBean.getCode();
                        if (code.equals("200")) {
                            List<OrderTJBean.DataBean.RowsBean> rows = orderTJBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                xRecyclerView.setVisibility(View.VISIBLE);
                                wsj.setVisibility(View.GONE);
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                goodsSalesConfirmListAdapter.notifyDataSetChanged();
                            } else {
                                xRecyclerView.setVisibility(View.GONE);
                                wsj.setVisibility(View.VISIBLE);
                                rowsBeans.clear();
                                goodsSalesConfirmListAdapter.notifyDataSetChanged();
                            }
                        } else {
                            xRecyclerView.setVisibility(View.GONE);
                            wsj.setVisibility(View.VISIBLE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    //获取列表更多数据
    private void initData2() {
        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15);
        RxHttpUtils.createApi(OrderApi.class)
                .getSubmitlist(currentPager, 15, sign)
                .compose(Transformer.<OrderTJBean>switchSchedulers())
                .subscribe(new NewCommonObserver<OrderTJBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(OrderTJBean orderTJBean) {
                        String code = orderTJBean.getCode();
                        if (code.equals("200")) {
                            rowsBeans.addAll(orderTJBean.getData().getRows());
                            goodsSalesConfirmListAdapter.addMoreData(orderTJBean.getData().getRows());
                        } else {
                            xRecyclerView.setNoMore(true);
                            ToastUtil.setToast("最后页");
                        }
                    }

                });
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
    protected void onResume() {
        super.onResume();
        gainData();
    }
}
