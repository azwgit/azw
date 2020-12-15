package com.example.bq.edmp.word.inventory;

import android.app.Dialog;
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
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.inventory.adapter.BaseCom_Ck_Adapter;
import com.example.bq.edmp.word.inventory.adapter.InventoryListAdapter;
import com.example.bq.edmp.word.inventory.adapter.PzAdapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.word.inventory.bean.InventoryBean;
import com.example.bq.edmp.word.inventory.bean.InventoryTabBean;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.word.inventory.fragment.InventoryFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 库存查询
 * */
public class InventoryActivity extends BaseActivity {

    @BindView(R.id.layout_tab)
    TabLayout mLayout_tab;
    @BindView(R.id.view_pager)
    ViewPager mView_pager;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.screen_img)
    ImageView screen_img;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.cz_tv)
    TextView cz_tv;
    @BindView(R.id.fen_company_tv)
    TextView fen_company_tv;
    @BindView(R.id.ck_tv)
    TextView ck_tv;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;
    @BindView(R.id.fen_company_rl)
    RelativeLayout fen_company_rl;
    @BindView(R.id.ck_rl)
    RelativeLayout ck_rl;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.top_ll)
    LinearLayout top_ll;
    @BindView(R.id.xr)
    XRecyclerView xr;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.shaixuan_wsj)
    TextView shaixuan_wsj;


    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private ArrayList<String> idlist = new ArrayList<>();
    private ArrayList<String> namelist = new ArrayList<>();
    private VPAdapter adapter;
    private ArrayList<SxPzBean.DataBean> dataBeans;
    private PzAdapter pzAdapter;

    private String mId = "";//作物id
    private String orgIds = "";//公司id
    private String org_name = "";//公司名字
    private String virtual_orgIds = "";//公司id
    private String varietyId = "";//品种id
    private String warehouseId = "";//仓库id
    private String warehouse_name = "";//仓库名字
    private String virtual_warehouseId = "";//仓库id
    private int type = 1;
    private List<SxPzBean.DataBean> data;
    private ArrayList<CompanyBean.DataBean> companyDataBeans;
    private BaseCom_Ck_Adapter baseCom_ck_adapter;
    private boolean Fund = false;//判断返回顺序

    private int currentPager = 1;
    private ArrayList<InventoryBean.DataBean.RowsBean> rowsBeans;
    private InventoryListAdapter inventoryListAdapter;
    private TextView wsj_dial;
    private XRecyclerView buttom_rv;

    @Override
    protected void initData() {
        initDataMethod();
    }

    private void initDataMethod() {
        RxHttpUtils.createApi(InventoryListApi.class)
                .getInventoryTabData()
                .compose(Transformer.<InventoryTabBean>switchSchedulers())
                .subscribe(new NewCommonObserver<InventoryTabBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(InventoryTabBean inventoryTabBean) {
                        if (inventoryTabBean.getCode().equals("200")) {
                            List<InventoryTabBean.DataBean> data = inventoryTabBean.getData();
                            idlist.clear();
                            namelist.clear();
                            idlist.add("0");
                            namelist.add("全部");

                            for (int i = 0; i < data.size(); i++) {
                                idlist.add(data.get(i).getId());
                                namelist.add(data.get(i).getName());
                            }
                            mLayout_tab.removeAllTabs();
                            for (int i = 0; i < idlist.size(); i++) {
                                fragmentList.add(new InventoryFragment(idlist.get(i)));
                                mLayout_tab.addTab(mLayout_tab.newTab().setText(namelist.get(i)));
                            }
                            dataMethod(data);
                        } else {
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void dataMethod(List<InventoryTabBean.DataBean> data) {
        adapter = new VPAdapter(getSupportFragmentManager(), fragmentList);
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
        linterHistoryConfirm.setOnClickListener(this);
        drawerLayout.setOnClickListener(this);
        screen_img.setOnClickListener(this);
        cz_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
        fen_company_rl.setOnClickListener(this);
        ck_rl.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        title_tv.setText("库存查询");

        //筛选  品种适配器
        dataBeans = new ArrayList<>();
        pzAdapter = new PzAdapter(dataBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(InventoryActivity.this, 3));
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


        //筛选数据
        rowsBeans = new ArrayList<>();
        inventoryListAdapter = new InventoryListAdapter(rowsBeans);
        xr.setAdapter(inventoryListAdapter);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(InventoryActivity.this));
        xr.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                initData();
                xr.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                initData2();
                xr.loadMoreComplete();
            }
        });

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                fen_company_tv.setHint("所有分子公司");
                fen_company_tv.setText("");
                ck_tv.setHint("所有仓库");
                ck_tv.setText("");
                virtual_orgIds = "";
                orgIds = "";
                org_name = "";
                varietyId = "";
                virtual_orgIds = "";
                virtual_warehouseId = "";
                warehouse_name = "";
                warehouseId = "";
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void initData2() {
        String sign = MD5Util.encode("cropId=" + mId + "&itemId=" + varietyId + "&orgId=" + orgIds +
                "&page=" + currentPager + "&pagerow=" + 15 +
                "&warehouseId=" + warehouseId);
        RxHttpUtils.createApi(InventoryListApi.class)
                .getInventoryData(mId, varietyId, orgIds, currentPager, 15, warehouseId, sign)
                .compose(Transformer.<InventoryBean>switchSchedulers())
                .subscribe(new NewCommonObserver<InventoryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(InventoryBean inventoryBean) {
                        List<InventoryBean.DataBean.RowsBean> rows = inventoryBean.getData().getRows();
                        if (rows != null && rows.size() != 0) {
                            rowsBeans.addAll(rows);
                            inventoryListAdapter.addMoreData(rows);
                        } else {
                            xr.setNoMore(true);
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inventory;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                int visibility = top_ll.getVisibility();
                if (visibility != 0) {
                    top_ll.setVisibility(ViewGroup.VISIBLE);
                    rl.setVisibility(ViewGroup.GONE);
                    initDataMethod();
                } else {
                    fund();
                }
                break;
            case R.id.screen_img:
                mView_pager.setCurrentItem(0);
                sxMethodData();
                break;
            case R.id.fen_company_rl://分公司
                type = 1;
                findContentViews2(view);
                break;
            case R.id.ck_rl://仓库
                if (fen_company_tv.getText().toString().equals("") || fen_company_tv.getText().toString().equals("所有分子公司")) {
                    ToastUtil.setToast("请先选择所公司");
                    break;
                }
                type = 2;
                findContentViews2(view);
                break;
            case R.id.cz_tv://重置
                fen_company_tv.setText("");
                fen_company_tv.setHint("所有分子公司");
                ck_tv.setText("");
                ck_tv.setHint("所有仓库");
                orgIds = "";
                warehouseId = "";
                varietyId = "";
                org_name = "";
                warehouse_name = "";
                break;
            case R.id.affirm_tv://筛选  确定
                rl.setVisibility(ViewGroup.VISIBLE);
                top_ll.setVisibility(ViewGroup.GONE);
                aginDataMethod();
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
                break;
        }
    }

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
                            shaixuan_wsj.setVisibility(ViewGroup.GONE);


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
                            shaixuan_wsj.setVisibility(ViewGroup.VISIBLE);
                        }
                    }
                });
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.openDrawer(Gravity.RIGHT);
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

    //手机返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                //当菜单栏是可见的，则关闭
                drawerLayout.closeDrawer(linterHistoryConfirm);
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /*
     * 底部跳框
     * */
    private void findContentViews2(View view) {
        final Dialog mCameraDialog = new Dialog(view.getContext(), R.style.my_dialog);

        View root = LayoutInflater.from(view.getContext()).inflate(R.layout.inventory_sx_buttom_item, null);

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
        TextView no_tv = root.findViewById(R.id.no_tv);
        TextView yes_tv = root.findViewById(R.id.yes_tv);
        wsj_dial = root.findViewById(R.id.wsj);

        no_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {//分公司
                    virtual_orgIds = "";
                    org_name = "";
                } else if (type == 2) {//仓库
                    virtual_warehouseId = "";
                    warehouse_name = "";
                }
                mCameraDialog.dismiss();
            }
        });

        yes_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {//分公司
                    orgIds = virtual_orgIds;
                    fen_company_tv.setText(org_name);
                } else if (type == 2) {//仓库
                    warehouseId = virtual_warehouseId;
                    ck_tv.setText(warehouse_name);
                }
                mCameraDialog.dismiss();
            }
        });

        //筛选  分公司，仓库适配器
        companyDataBeans = new ArrayList<>();
        baseCom_ck_adapter = new BaseCom_Ck_Adapter(companyDataBeans);
        buttom_rv.setLayoutManager(new LinearLayoutManager(InventoryActivity.this));
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

                if (type == 1) {//分公司
                    virtual_orgIds = dataBean.getId();
                    org_name = dataBean.getName();
                } else if (type == 2) {//仓库
                    virtual_warehouseId = dataBean.getId();
                    warehouse_name = dataBean.getName();
                }

            }
        });

        btuomMethod();
    }

    //分公司
    private void btuomMethod() {
        if (type == 1) {
            RxHttpUtils.createApi(InventoryListApi.class)
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
                            if (data.size() != 0 && data != null) {
                                buttom_rv.setVisibility(ViewGroup.VISIBLE);
                                wsj_dial.setVisibility(ViewGroup.GONE);

                                companyDataBeans.clear();
                                data.get(0).setSelected(true);
                                virtual_orgIds = data.get(0).getId();
                                org_name = data.get(0).getName();
                                companyDataBeans.addAll(data);
                                baseCom_ck_adapter.notifyDataSetChanged();
                            } else {
                                buttom_rv.setVisibility(ViewGroup.GONE);
                                wsj_dial.setVisibility(ViewGroup.VISIBLE);
                                ToastUtil.setToast("暂无数据");
                            }
                        }
                    });
        } else {
            RxHttpUtils.createApi(InventoryListApi.class)
                    .getCkData()
                    .compose(Transformer.<CompanyBean>switchSchedulers())
                    .subscribe(new NewCommonObserver<CompanyBean>() {
                        @Override
                        protected void onError(String errorMsg) {
                            ToastUtil.setToast(errorMsg);
                        }

                        @Override
                        protected void onSuccess(CompanyBean companyBean) {
                            List<CompanyBean.DataBean> data = companyBean.getData();
                            if (data.size() != 0 && data != null) {
                                buttom_rv.setVisibility(ViewGroup.VISIBLE);
                                wsj_dial.setVisibility(ViewGroup.GONE);
                                companyDataBeans.clear();
                                data.get(0).setSelected(true);
                                virtual_warehouseId = data.get(0).getId();
                                warehouse_name = data.get(0).getName();
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
    }


    private void aginDataMethod() {
        currentPager = 1;
        String sign = MD5Util.encode("cropId=" + mId + "&itemId=" + varietyId + "&orgId=" + orgIds +
                "&page=" + currentPager + "&pagerow=" + 15 +
                "&warehouseId=" + warehouseId);
        ;
        RxHttpUtils.createApi(InventoryListApi.class)
                .getInventoryData(mId, varietyId, orgIds, currentPager, 15, warehouseId, sign)
                .compose(Transformer.<InventoryBean>switchSchedulers())
                .subscribe(new NewCommonObserver<InventoryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(InventoryBean inventoryBean) {
                        List<InventoryBean.DataBean.RowsBean> rows = inventoryBean.getData().getRows();
                        if (rows.size() != 0 && rows != null) {
                            xr.setVisibility(ViewGroup.VISIBLE);
                            wsj.setVisibility(ViewGroup.GONE);
                            fen_company_tv.setText("");
                            fen_company_tv.setHint("所有分子公司");
                            ck_tv.setText("");
                            ck_tv.setHint("所有仓库");
                            orgIds = "";
                            warehouseId = "";
                            varietyId = "";
                            org_name = "";
                            warehouse_name = "";

                            rowsBeans.clear();
                            rowsBeans.addAll(rows);
                            inventoryListAdapter.notifyDataSetChanged();
                        } else {
                            xr.setVisibility(ViewGroup.GONE);
                            wsj.setVisibility(ViewGroup.VISIBLE);

                            rowsBeans.clear();
                            inventoryListAdapter.notifyDataSetChanged();
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }
}
