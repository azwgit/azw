package com.example.bq.edmp.work.grainsale.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.DataUtils;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.inventory.InventoryActivity;
import com.example.bq.edmp.word.inventory.adapter.BaseCom_Ck_Adapter;
import com.example.bq.edmp.word.inventory.adapter.PzAdapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.work.finishedproduct.activity.FinishedStockDetailActivity;
import com.example.bq.edmp.work.grainsale.adapter.SaleStockAdapter;
import com.example.bq.edmp.work.grainsale.api.SaleApi;
import com.example.bq.edmp.work.grainsale.bean.SaleStockBean;
import com.example.bq.edmp.work.grainsale.bean.SaleWarehouseBean;
import com.example.bq.edmp.work.library.activity.CxlibraryActivity;
import com.example.bq.edmp.work.library.adapter.CxLibraryListAdapter;
import com.example.bq.edmp.work.library.bean.CxLibraryBean;
import com.example.bq.edmp.work.reseller.adapter.ResellerPzAdapter;
import com.example.bq.edmp.work.reseller.bean.PzBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import retrofit2.http.Field;

/*
 * 销售库存
 * */
public class SaleStockActivity extends BaseTitleActivity {

    @BindView(R.id.xr)
    XRecyclerView xr;
    @BindView(R.id.wsj)
    TextView wsj;
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
    @BindView(R.id.shaixuan_wsj)
    TextView shaixuan_wsj;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.cz_tv)
    TextView cz_tv;

    private LoadingDialog loadingDialog;
    private ArrayList<SaleStockBean.DataBean.RowsBean> rowsBeans;
    private SaleStockAdapter saleStockAdapter;
    private ResellerPzAdapter resellerPzAdapter;
    private int currentPager = 1;
    private String orgIds = "";//公司id
    private String varietyId = "";//品种id
    private String warehouseId = "";//仓库id

    private ArrayList<CompanyBean.DataBean> companyDataBeans;
    private ArrayList<SxPzBean.DataBean> dataBeans;
    private BaseCom_Ck_Adapter baseCom_ck_adapter;
    private List<SxPzBean.DataBean> data;
    private PzAdapter pzAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sale_stock;
    }

    @Override
    protected void initView() {


        String sign = MD5Util.encode("orgId=" + orgIds + "&page=" + currentPager +
                "&pagerow=" + 15 + "&varietyId=" + varietyId);

        LogUtils.e("你马德望",sign);

        ProApplication.getinstance().addActivity(SaleStockActivity.this);
        txtTabTitle.setText("库存查询");
        ivOperate.setVisibility(View.VISIBLE);
        ivOperate.setBackground(getResources().getDrawable(R.drawable.icon_universal));
        loadingDialog = new LoadingDialog(this);

        //数据
        rowsBeans = new ArrayList<>();
        saleStockAdapter = new SaleStockAdapter(rowsBeans);
        xr.setAdapter(saleStockAdapter);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(SaleStockActivity.this));
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
        saleStockAdapter.setOnItemClickListener(new SaleStockAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, SaleStockBean.DataBean.RowsBean rowsBean) {
                SaleStockDetailActivity.newIntent(getApplicationContext(),rowsBean.getWarehouseId(),rowsBean.getItemId());
            }
        });


        //筛选  品种适配器
        dataBeans = new ArrayList<>();
        pzAdapter = new PzAdapter(dataBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(SaleStockActivity.this, 3));
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


    }

    @Override
    protected void initData() {
        gainData();
    }

    private void gainData() {
        currentPager = 1;


        String sign = MD5Util.encode("itemId=" + varietyId + "&orgId=" + orgIds + "&page=" + currentPager +
                "&pagerow=" + 15 + "&warehouseId=" + warehouseId);

        RxHttpUtils.createApi(SaleApi.class)
                .getStockData(varietyId, orgIds, currentPager, 15, warehouseId, sign)
                .compose(Transformer.<SaleStockBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<SaleStockBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SaleStockBean saleStockBean) {
                        List<SaleStockBean.DataBean.RowsBean> rows = saleStockBean.getData().getRows();
                        if (rows != null && rows.size() != 0) {
                            xr.setVisibility(ViewGroup.VISIBLE);
                            wsj.setVisibility(ViewGroup.GONE);

                            rowsBeans.clear();
                            rowsBeans.addAll(rows);
                            saleStockAdapter.notifyDataSetChanged();
                        } else {
                            xr.setVisibility(ViewGroup.GONE);
                            wsj.setVisibility(ViewGroup.VISIBLE);

                            rowsBeans.clear();
                            saleStockAdapter.notifyDataSetChanged();
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("itemId=" + varietyId + "&orgId=" + orgIds + "&page=" + currentPager +
                "&pagerow=" + 15 + "&warehouseId=" + warehouseId);

        RxHttpUtils.createApi(SaleApi.class)
                .getStockData(varietyId, orgIds, currentPager, 15, warehouseId, sign)
                .compose(Transformer.<SaleStockBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<SaleStockBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SaleStockBean saleStockBean) {
                        List<SaleStockBean.DataBean.RowsBean> rows = saleStockBean.getData().getRows();
                        if (rows != null && rows.size() != 0) {

                            rowsBeans.addAll(rows);
                            saleStockAdapter.addMoreData(rows);
                        } else {
                            ToastUtil.setToast("暂无数据");
                            xr.setNoMore(true);
                        }
                    }
                });
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

    private int type = 1;
    private boolean Fund = false;

    @Override
    protected void initListener() {
        fen_company_rl.setOnClickListener(this);
        cz_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
        ck_rl.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivOperate.setOnClickListener(this);
    }



    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                if (Fund) {
                    orgIds = "";
                    varietyId = "";
                    warehouseId = "";
                    Fund = false;
                    gainData();
                } else {
                    fund();
                }
                break;
            case R.id.img_operate:
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
            case R.id.affirm_tv://确定

                Fund = true;
                gainData();
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
                break;
            case R.id.cz_tv://重置

                fen_company_tv.setHint("所有分子公司");
                fen_company_tv.setText("");
                ck_tv.setHint("所有仓库");
                ck_tv.setText("");
                orgIds = "";
                orgIds = "";
                varietyId = "";
                warehouseId = "";
                warehouseId = "";

                sxMethodData();
                break;
        }
    }

    private TextView wsj_dial;
    private XRecyclerView buttom_rv;

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
                        if (data != null && data.size() != 0) {
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
     * 底部跳框
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
        buttom_rv.setLayoutManager(new LinearLayoutManager(SaleStockActivity.this));
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
                    orgIds = dataBean.getId();
                    fen_company_tv.setText(dataBean.getName());
                } else if (type == 2) {//仓库
                    warehouseId = dataBean.getId();
                    ck_tv.setText(dataBean.getName());
                }
                mCameraDialog.dismiss();
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
                            if (data != null && data.size() != 0) {
                                buttom_rv.setVisibility(ViewGroup.VISIBLE);
                                wsj_dial.setVisibility(ViewGroup.GONE);

                                companyDataBeans.clear();
                                data.get(0).setSelected(true);
                                orgIds = data.get(0).getId();
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
                            if (data != null && data.size() != 0) {
                                buttom_rv.setVisibility(ViewGroup.VISIBLE);
                                wsj_dial.setVisibility(ViewGroup.GONE);

                                companyDataBeans.clear();
                                data.get(0).setSelected(true);
                                warehouseId = data.get(0).getId();
                                ck_tv.setText(data.get(0).getName());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(SaleStockActivity.this);
    }
}
