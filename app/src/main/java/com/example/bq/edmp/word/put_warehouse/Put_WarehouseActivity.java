package com.example.bq.edmp.word.put_warehouse;

import android.app.Dialog;
import android.support.annotation.NonNull;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.inventory.InventoryActivity;
import com.example.bq.edmp.word.inventory.adapter.BaseCom_Ck_Adapter;
import com.example.bq.edmp.word.inventory.adapter.InventoryListAdapter;
import com.example.bq.edmp.word.inventory.adapter.PzAdapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.word.put_warehouse.adapter.WarehouseListAdapter;
import com.example.bq.edmp.word.put_warehouse.api.WarehouseApi;
import com.example.bq.edmp.word.put_warehouse.bean.WarehouseListBean;
import com.example.bq.edmp.work.grainmanagement.activity.WarehousingDetailAct;
import com.example.bq.edmp.work.grainmanagement.activity.WarehousingOutDetailAct;
import com.example.bq.edmp.work.grainmanagement.adapter.WareHouseListAdp;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 原粮管理   出入库
 * */
public class Put_WarehouseActivity extends BaseActivity {

    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.screen_img)
    ImageView screen_img;
    @BindView(R.id.xr)
    XRecyclerView xr;
    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fen_company_rl)
    RelativeLayout fen_company_rl;
    @BindView(R.id.fen_company_tv)
    TextView fen_company_tv;
    @BindView(R.id.ck_rl)
    RelativeLayout ck_rl;
    @BindView(R.id.ck_tv)
    TextView ck_tv;
    @BindView(R.id.shai_rv)
    RecyclerView recyclerView;
    @BindView(R.id.start_time_tv)
    TextView start_time_tv;
    @BindView(R.id.end_time_tv)
    TextView end_time_tv;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.cz_tv)
    TextView cz_tv;


    private int currentPager = 1;
    private String orgIds = "";//公司id
    private String org_name = "";//公司名字
    private String virtual_orgIds = "";//公司id
    private String varietyId = "";//品种id
    private String warehouseId = "";//仓库id
    private String warehouse_name = "";//仓库名字
    private String virtual_warehouseId = "";//仓库id
    private String beginTime = "";//开始时间
    private String endTime = "";//结束时间
    private String code = "";//单号
    private int type = 1;
    private ArrayList<WarehouseListBean.RowsBean> rowsBeans;
    private WarehouseListAdapter warehouseListAdapter;
    private ArrayList<CompanyBean.DataBean> companyDataBeans;
    private BaseCom_Ck_Adapter baseCom_ck_adapter;
    private ArrayList<SxPzBean.DataBean> dataBeans;
    private List<SxPzBean.DataBean> data;
    private PzAdapter pzAdapter;
    private int WAREHOUSETYPE;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_put__warehouse;
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        screen_img.setOnClickListener(this);
        start_time_tv.setOnClickListener(this);
        end_time_tv.setOnClickListener(this);
        fen_company_rl.setOnClickListener(this);
        ck_rl.setOnClickListener(this);
        cz_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);

    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.screen_img:
                sxMethodData();
                break;
            case R.id.start_time_tv:

                break;
            case R.id.end_time_tv:

                break;
            case R.id.fen_company_rl:
                type = 1;
                findContentViews2(view);
                break;
            case R.id.ck_rl:
                if (fen_company_tv.getText().toString().equals("") || fen_company_tv.getText().toString().equals("所有分子公司")) {
                    ToastUtil.setToast("请先选择所公司");
                    break;
                }
                type = 2;
                findContentViews2(view);
                break;
            case R.id.cz_tv:
                start_time_tv.setHint("请选择开始时间");
                start_time_tv.setText("");
                end_time_tv.setHint("请选择结束时间");
                end_time_tv.setText("");
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


                break;
            case R.id.affirm_tv:
                if (start_time_tv.getText().toString().equals("")) {
                    ToastUtil.setToast("请选择开始时间");
                    break;
                }
                if (end_time_tv.getText().toString().equals("")) {
                    ToastUtil.setToast("请选择结束时间");
                    break;
                }
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
                            dataBeans.clear();
                            SxPzBean.DataBean dataBean = new SxPzBean.DataBean();
                            dataBean.setSelected(true);
                            dataBean.setId("");
                            dataBean.setName("全部");
                            dataBeans.add(dataBean);
                            dataBeans.addAll(data);
                            pzAdapter.notifyDataSetChanged();
                        } else {

                        }
                    }
                });
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.openDrawer(Gravity.RIGHT);
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

        XRecyclerView buttom_rv = root.findViewById(R.id.buttom_rv);
        TextView no_tv = root.findViewById(R.id.no_tv);
        TextView yes_tv = root.findViewById(R.id.yes_tv);

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
        buttom_rv.setLayoutManager(new LinearLayoutManager(Put_WarehouseActivity.this));
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
                                companyDataBeans.clear();
                                data.get(0).setSelected(true);
                                virtual_orgIds = data.get(0).getId();
                                org_name = data.get(0).getName();
                                companyDataBeans.addAll(data);
                                baseCom_ck_adapter.notifyDataSetChanged();
                            } else {

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
                                companyDataBeans.clear();
                                data.get(0).setSelected(true);
                                virtual_warehouseId = data.get(0).getId();
                                warehouse_name = data.get(0).getName();
                                companyDataBeans.addAll(data);
                                baseCom_ck_adapter.notifyDataSetChanged();
                            } else {

                            }
                        }
                    });
        }
    }


    @Override
    protected void initView() {
        WAREHOUSETYPE = getIntent().getIntExtra("WarehouseType", 0);

        if (WAREHOUSETYPE == 1) {
            title_tv.setText("入库查询");
        } else if (WAREHOUSETYPE == 2) {
            title_tv.setText("出库查询");
        }

        //数据
        rowsBeans = new ArrayList<>();
        warehouseListAdapter = new WarehouseListAdapter(rowsBeans,WAREHOUSETYPE);
        xr.setAdapter(warehouseListAdapter);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(Put_WarehouseActivity.this));
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
        warehouseListAdapter.setOnItemClickListener(new WarehouseListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, WarehouseListBean.RowsBean rowsBean) {
                if (WAREHOUSETYPE == 1) {
                    //入庫詳情
                    WarehousingDetailAct.newIntent(getApplicationContext(),rowsBean.getId()+"");
                }else{
                    //出庫詳情
                    WarehousingOutDetailAct.newIntent(getApplicationContext(),rowsBean.getId()+"");
                }

            }
        });
        //筛选  品种适配器
        dataBeans = new ArrayList<>();
        pzAdapter = new PzAdapter(dataBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(Put_WarehouseActivity.this, 3));
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
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                ToastUtil.setToast("打开");
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                ToastUtil.setToast("关闭");
                start_time_tv.setHint("请选择开始时间");
                start_time_tv.setText("");
                end_time_tv.setHint("请选择结束时间");
                end_time_tv.setText("");
                fen_company_tv.setHint("所有分子公司");
                fen_company_tv.setText("");
                ck_tv.setHint("所有仓库");
                ck_tv.setText("");
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    //分页加载
    private void initData2() {
        if (WAREHOUSETYPE == 1) {
            String sign = MD5Util.encode("beginTime=" + beginTime + "&code=" + code + "&endTime=" + endTime +
                    "&orgIds=" + orgIds + "&page=" + currentPager +
                    "&pagerow=" + 15 + "&varietyId=" + varietyId + "&warehouseId=" + warehouseId);

            RxHttpUtils.createApi(WarehouseApi.class)
                    .getWarehouseListData(beginTime, code, endTime, orgIds, currentPager, 15, varietyId, warehouseId, sign)
                    .compose(Transformer.<WarehouseListBean>switchSchedulers())
                    .subscribe(new NewCommonObserver<WarehouseListBean>() {
                        @Override
                        protected void onError(String errorMsg) {
                            ToastUtil.setToast(errorMsg);
                        }

                        @Override
                        protected void onSuccess(WarehouseListBean warehouseListBean) {
                            List<WarehouseListBean.RowsBean> rows = warehouseListBean.getRows();
                            if (rows.size() != 0 && rows != null) {

                                rowsBeans.addAll(rows);
                                warehouseListAdapter.addMoreData(rows);
                            } else {
                                ToastUtil.setToast("暂无数据");
                                xr.setNoMore(true);
                            }
                        }
                    });
        } else if (WAREHOUSETYPE == 2) {
            String sign = MD5Util.encode("beginTime=" + beginTime + "&code=" + code + "&endTime=" + endTime +
                    "&orgIds=" + orgIds + "&page=" + currentPager +
                    "&pagerow=" + 15 + "&varietyId=" + varietyId + "&warehouseId=" + warehouseId);

            RxHttpUtils.createApi(WarehouseApi.class)
                    .getChuWarehouseListData(beginTime, code, endTime, orgIds, currentPager, 15, varietyId, warehouseId, sign)
                    .compose(Transformer.<WarehouseListBean>switchSchedulers())
                    .subscribe(new NewCommonObserver<WarehouseListBean>() {
                        @Override
                        protected void onError(String errorMsg) {
                            ToastUtil.setToast(errorMsg);
                        }

                        @Override
                        protected void onSuccess(WarehouseListBean warehouseListBean) {
                            List<WarehouseListBean.RowsBean> rows = warehouseListBean.getRows();
                            if (rows.size() != 0 && rows != null) {

                                rowsBeans.addAll(rows);
                                warehouseListAdapter.addMoreData(rows);
                            } else {
                                ToastUtil.setToast("暂无数据");
                                xr.setNoMore(true);
                            }
                        }
                    });
        }
    }

    @Override
    protected void initData() {
        gainData();
    }

    private void gainData() {

        if (WAREHOUSETYPE == 1) {

            currentPager = 1;

            String sign = MD5Util.encode("beginTime=" + beginTime + "&code=" + code + "&endTime=" + endTime +
                    "&orgIds=" + orgIds + "&page=" + currentPager +
                    "&pagerow=" + 15 + "&varietyId=" + varietyId + "&warehouseId=" + warehouseId);

            RxHttpUtils.createApi(WarehouseApi.class)
                    .getWarehouseListData(beginTime, code, endTime, orgIds, currentPager, 15, varietyId, warehouseId, sign)
                    .compose(Transformer.<WarehouseListBean>switchSchedulers())
                    .subscribe(new NewCommonObserver<WarehouseListBean>() {
                        @Override
                        protected void onError(String errorMsg) {
                            ToastUtil.setToast(errorMsg);
                        }

                        @Override
                        protected void onSuccess(WarehouseListBean warehouseListBean) {
                            List<WarehouseListBean.RowsBean> rows = warehouseListBean.getRows();
                            if (rows != null&&rows.size() != 0) {
                                start_time_tv.setHint("请选择开始时间");
                                start_time_tv.setText("");
                                end_time_tv.setHint("请选择结束时间");
                                end_time_tv.setText("");
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

                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                warehouseListAdapter.notifyDataSetChanged();
                            } else {

                            }
                        }
                    });
        } else if (WAREHOUSETYPE == 2) {

            currentPager = 1;

            String sign = MD5Util.encode("beginTime=" + beginTime + "&code=" + code + "&endTime=" + endTime +
                    "&orgIds=" + orgIds + "&page=" + currentPager +
                    "&pagerow=" + 15 + "&varietyId=" + varietyId + "&warehouseId=" + warehouseId);

            RxHttpUtils.createApi(WarehouseApi.class)
                    .getChuWarehouseListData(beginTime, code, endTime, orgIds, currentPager, 15, varietyId, warehouseId, sign)
                    .compose(Transformer.<WarehouseListBean>switchSchedulers())
                    .subscribe(new NewCommonObserver<WarehouseListBean>() {
                        @Override
                        protected void onError(String errorMsg) {
                            ToastUtil.setToast(errorMsg);
                        }

                        @Override
                        protected void onSuccess(WarehouseListBean warehouseListBean) {
                            List<WarehouseListBean.RowsBean> rows = warehouseListBean.getRows();
                            if (rows != null&&rows.size() != 0) {
                                start_time_tv.setHint("请选择开始时间");
                                start_time_tv.setText("");
                                end_time_tv.setHint("请选择结束时间");
                                end_time_tv.setText("");
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

                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                warehouseListAdapter.notifyDataSetChanged();
                            } else {

                            }
                        }
                    });
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
}