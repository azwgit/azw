package com.example.bq.edmp.work.reseller.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.order.activity.GoodsListActivity;
import com.example.bq.edmp.work.order.fragment.OrderFragment;
import com.example.bq.edmp.work.reseller.adapter.ResellerApplyAdapter;
import com.example.bq.edmp.work.reseller.api.ResellerApi;
import com.example.bq.edmp.work.reseller.fragment.ResellerTrackFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/*
 * 转商跟踪
 * */

public class ResellerTrackActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.layout_tab)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.tab_ll)
    LinearLayout tab_ll;
    @BindView(R.id.code_ll)
    LinearLayout code_ll;
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsju)
    TextView wsj;

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private LoadingDialog loadingDialog;
    private ResellerApplyAdapter resellerApplyAdapter;
    private int currentPager = 1;
    private ArrayList<BaseAllocationBeam.DataBean.RowsBean> rowsBeans;
    private String mStatus = "10";
    private String mCode = "";
    private boolean Fund = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reseller_track;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(ResellerTrackActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("转商跟踪");


        rowsBeans = new ArrayList<>();
        resellerApplyAdapter = new ResellerApplyAdapter(rowsBeans);
        xRecyclerView.setAdapter(resellerApplyAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(ResellerTrackActivity.this));
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                initData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                initData2();
                xRecyclerView.loadMoreComplete();
            }
        });

        resellerApplyAdapter.setOnItemLeftClckListener(new ResellerApplyAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(BaseAllocationBeam.DataBean.RowsBean rowsBean, int mPosition) {
                Intent intent = new Intent(ResellerTrackActivity.this, ResellerTrackDetailsActivity.class);
                intent.putExtra("id", rowsBean.getId());
                startActivity(intent);
            }
        });


        //搜索
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //此处做逻辑处理
                    Fund = true;
                    mCode = textView.getText().toString();
                    hideKeyboard(search_et);
                    tab_ll.setVisibility(View.GONE);
                    code_ll.setVisibility(View.VISIBLE);
                    Fund = true;
                    gainData();
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void initData() {
        ArrayList<String> IdString = new ArrayList<>();
        ArrayList<String> TabNameString = new ArrayList<>();
        //默认全部 10 ，2审批中 3待出库 4待入库 -2 审批拒绝
        IdString.clear();
        IdString.add("10");
        IdString.add("2");
        IdString.add("3");
        IdString.add("4");
        IdString.add("-2");

        TabNameString.clear();
        TabNameString.add("全部");
        TabNameString.add("审批中");
        TabNameString.add("待出库");
        TabNameString.add("待入库");
        TabNameString.add("审批拒绝");

        tabLayout.removeAllTabs();
        if (IdString != null && IdString.size() != 0) {
            for (int i = 0; i < IdString.size(); i++) {
                fragmentList.add(new ResellerTrackFragment(IdString.get(i)));
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
                if (Fund == true) {
                    tab_ll.setVisibility(View.VISIBLE);
                    code_ll.setVisibility(View.GONE);
                    search_et.setText("");
                    Fund = false;
                } else {
                    fund();
                }
                break;

        }
    }

    //获取列表数据
    private void gainData() {

        currentPager = 1;

        String sign = MD5Util.encode("code=" + mCode + "&page=" + currentPager + "&pagerow=" + 15 + "&status=" + mStatus);

        RxHttpUtils.createApi(ResellerApi.class)
                .getTrackList(mCode, currentPager, 15, mStatus, sign)
                .compose(Transformer.<BaseAllocationBeam>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseAllocationBeam>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseAllocationBeam baseAllocationBeam) {
                        String code = baseAllocationBeam.getCode();
                        if (code.equals("200")) {
                            List<BaseAllocationBeam.DataBean.RowsBean> rows = baseAllocationBeam.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                wsj.setVisibility(ViewGroup.GONE);
                                xRecyclerView.setVisibility(ViewGroup.VISIBLE);
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                resellerApplyAdapter.notifyDataSetChanged();
                            } else {
                                wsj.setVisibility(ViewGroup.VISIBLE);
                                xRecyclerView.setVisibility(ViewGroup.GONE);
                                rowsBeans.clear();
                                resellerApplyAdapter.notifyDataSetChanged();
                                ToastUtil.setToast("暂无数据");
                            }
                        } else {
                            wsj.setVisibility(ViewGroup.VISIBLE);
                            xRecyclerView.setVisibility(ViewGroup.GONE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    //获取列表更多数据
    private void initData2() {
        String sign = MD5Util.encode("code=" + mCode + "&page=" + currentPager + "&pagerow=" + 15 + "&status=" + mStatus);

        RxHttpUtils.createApi(ResellerApi.class)
                .getTrackList(mCode, currentPager, 15, mStatus, sign)
                .compose(Transformer.<BaseAllocationBeam>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseAllocationBeam>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseAllocationBeam baseAllocationBeam) {
                        String code = baseAllocationBeam.getCode();
                        if (code.equals("200")) {
                            List<BaseAllocationBeam.DataBean.RowsBean> rows = baseAllocationBeam.getData().getRows();
                            rowsBeans.addAll(rows);
                            resellerApplyAdapter.addMoreData(rows);
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
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(ResellerTrackActivity.this);
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


}