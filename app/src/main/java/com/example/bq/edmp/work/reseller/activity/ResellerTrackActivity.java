package com.example.bq.edmp.work.reseller.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.bq.edmp.word.inventory.adapter.PzAdapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.library.activity.RlibraryActivity;
import com.example.bq.edmp.work.order.activity.GoodsListActivity;
import com.example.bq.edmp.work.order.fragment.OrderFragment;
import com.example.bq.edmp.work.reseller.adapter.ResellerApplyAdapter;
import com.example.bq.edmp.work.reseller.adapter.ResellerPzAdapter;
import com.example.bq.edmp.work.reseller.api.ResellerApi;
import com.example.bq.edmp.work.reseller.bean.PzBean;
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


    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fen_company_rl)
    RelativeLayout fen_company_rl;
    @BindView(R.id.fen_company_tv)
    TextView fen_company_tv;
    @BindView(R.id.shai_rv)
    RecyclerView recyclerView;
    @BindView(R.id.shaixuan_wsj)
    TextView shaixuan_wsj;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.cz_tv)
    TextView cz_tv;
    @BindView(R.id.screen_img)
    ImageView screen_img;

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private LoadingDialog loadingDialog;
    private ResellerApplyAdapter resellerApplyAdapter;
    private int currentPager = 1;
    private ArrayList<BaseAllocationBeam.DataBean.RowsBean> rowsBeans;
    private String mStatus = "10";
    private String mCode = "";
    private boolean Fund = false;
    private ArrayList<PzBean.DataBean> dataBeans;
    private ResellerPzAdapter resellerPzAdapter;
    private String varietyId = "";//品种id
    private String virtual_orgIds = "";//公司id
    private ArrayList<CompanyBean.DataBean> companyDataBeans;
    private BaseCom_Ck_Adapter baseCom_ck_adapter;
    private XRecyclerView buttom_rv;
    private TextView wsj_dial;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(ResellerTrackActivity.this);
    }

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
                    gainData();
                    return true;
                }
                return false;
            }
        });


        //筛选  品种适配器
        dataBeans = new ArrayList<>();
        resellerPzAdapter = new ResellerPzAdapter(dataBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(ResellerTrackActivity.this, 3));
        recyclerView.setAdapter(resellerPzAdapter);
        resellerPzAdapter.setOnItemLeftClckListener(new ResellerPzAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(PzBean.DataBean dataBean, int mPosition) {
                //当点击时显示当前条目的背景和文字的颜色
                for (int i = 0; i < dataBeans.size(); i++) {
                    if (mPosition == i) {
                        dataBeans.get(i).setSelected(true);
                    } else {
                        dataBeans.get(i).setSelected(false);
                    }
                }
                resellerPzAdapter.notifyDataSetChanged();
                if (!dataBean.getVarietyId().equals("") && dataBean.getVarietyId() != null) {
                    varietyId = dataBean.getVarietyId();
                } else {
                    varietyId = "";
                }
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
        screen_img.setOnClickListener(this);
        fen_company_rl.setOnClickListener(this);
        cz_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                if (Fund == true) {
                    tab_ll.setVisibility(View.VISIBLE);
                    code_ll.setVisibility(View.GONE);
                    search_et.setText("");
                    fen_company_tv.setHint("所有分子公司");
                    fen_company_tv.setText("");
                    varietyId = "";
                    virtual_orgIds = "";
                    Fund = false;
                } else {
                    fund();
                }
                break;
            case R.id.screen_img:
                sxMethodData();
                break;
            case R.id.fen_company_rl:
                findContentViews2(view);
                break;
            case R.id.cz_tv:
                fen_company_tv.setText("");
                fen_company_tv.setHint("所有分子公司");
                varietyId = "";
                virtual_orgIds = "";
                sxMethodData();
                break;
            case R.id.affirm_tv:
                if (virtual_orgIds.equals("")) {
                    ToastUtil.setToast("请选择公司");
                    break;
                }
                Fund = true;
                tab_ll.setVisibility(View.GONE);
                code_ll.setVisibility(View.VISIBLE);
                gainData();
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
                break;

        }
    }

    /*
     * 分公司与仓库
     * */
    private void findContentViews2(View view) {
        final Dialog mCameraDialog = new Dialog(view.getContext(), R.style.my_dialog);

        View root = LayoutInflater.from(view.getContext()).inflate(R.layout.sx_buttom_item, null);

        mCameraDialog.setContentView(root);
        mCameraDialog.setCanceledOnTouchOutside(false);

        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.BottomDialog_Animation); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//      lp.alpha = 9f; // 透明度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();

        buttom_rv = root.findViewById(R.id.buttom_rv);
        wsj_dial = root.findViewById(R.id.wsj);


        //筛选  分公司，仓库适配器
        companyDataBeans = new ArrayList<>();
        baseCom_ck_adapter = new BaseCom_Ck_Adapter(companyDataBeans);
        buttom_rv.setLayoutManager(new LinearLayoutManager(ResellerTrackActivity.this));
        buttom_rv.setAdapter(baseCom_ck_adapter);
        baseCom_ck_adapter.setOnItemLeftClckListener(new BaseCom_Ck_Adapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(CompanyBean.DataBean dataBean, int mPosition) {
                //当点击时显示当前条目的背景和文字的颜色
                for (int i = 0; i < companyDataBeans.size(); i++) {
                    if (mPosition == i) {
                        companyDataBeans.get(i).setSelected(true);
                    } else {
                        companyDataBeans.get(i).setSelected(false);
                    }
                }
                baseCom_ck_adapter.notifyDataSetChanged();
                virtual_orgIds = dataBean.getId();
                fen_company_tv.setText(dataBean.getName());
                mCameraDialog.dismiss();
            }
        });

        btuomMethod();
    }

    private void btuomMethod() {

        RxHttpUtils.createApi(ResellerApi.class)
                .getCompanyData()
                .compose(Transformer.<CompanyBean>switchSchedulers())
                .subscribe(new NewCommonObserver<CompanyBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CompanyBean companyBean) {
                        List<CompanyBean.DataBean> data = companyBean.getData();
                        if (data != null && data.size() != 0) {
                            buttom_rv.setVisibility(ViewGroup.VISIBLE);
                            wsj_dial.setVisibility(ViewGroup.GONE);

                            companyDataBeans.clear();
                            data.get(0).setSelected(true);
                            virtual_orgIds = data.get(0).getId();
                            fen_company_tv.setText(data.get(0).getName());
                            companyDataBeans.addAll(data);
                            baseCom_ck_adapter.notifyDataSetChanged();
                        } else {
                            buttom_rv.setVisibility(ViewGroup.GONE);
                            wsj_dial.setVisibility(ViewGroup.VISIBLE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });


    }

    //品种数据
    private void sxMethodData() {
        RxHttpUtils.createApi(ResellerApi.class)
                .getPzData()
                .compose(Transformer.<PzBean>switchSchedulers())
                .subscribe(new NewCommonObserver<PzBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(PzBean sxPzBean) {
                        List<PzBean.DataBean> data = sxPzBean.getData();
                        if (data.size() != 0 && data != null) {
                            recyclerView.setVisibility(ViewGroup.VISIBLE);
                            shaixuan_wsj.setVisibility(ViewGroup.GONE);

                            dataBeans.clear();
                            PzBean.DataBean dataBean = new PzBean.DataBean();
                            dataBean.setSelected(true);
                            dataBean.setVarietyId("");
                            dataBean.setVarietyName("全部");
                            dataBeans.add(dataBean);
                            dataBeans.addAll(data);
                            resellerPzAdapter.notifyDataSetChanged();
                        } else {
                            recyclerView.setVisibility(ViewGroup.GONE);
                            shaixuan_wsj.setVisibility(ViewGroup.VISIBLE);
                        }
                    }
                });
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    //获取列表数据
    private void gainData() {

        currentPager = 1;

        String sign = MD5Util.encode("code=" + mCode + "&inOrgId=" + virtual_orgIds + "&page=" + currentPager + "&pagerow=" + 15 + "&status=" + mStatus + "&varietyId=" + varietyId);

        RxHttpUtils.createApi(ResellerApi.class)
                .getTrackListShai(mCode, virtual_orgIds, currentPager, 15, mStatus, varietyId, sign)
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
        String sign = MD5Util.encode("code=" + mCode + "&inOrgId=" + virtual_orgIds + "&page=" + currentPager + "&pagerow=" + 15 + "&status=" + mStatus + "&varietyId=" + varietyId);

        RxHttpUtils.createApi(ResellerApi.class)
                .getTrackListShai(mCode, virtual_orgIds, currentPager, 15, mStatus, varietyId, sign)
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