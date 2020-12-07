package com.example.bq.edmp.word.purchase;


import android.app.Dialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.PayInfoDetailAct;
import com.example.bq.edmp.activity.apply.activity.EditPayInfoDetailAct;
import com.example.bq.edmp.activity.apply.travel.activity.EditTravelDetailAct;
import com.example.bq.edmp.activity.apply.travel.activity.TravelDetailAct;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.adapter.SubmitListAdapter;
import com.example.bq.edmp.word.bean.SubmitListBean;
import com.example.bq.edmp.word.inventory.InventoryActivity;
import com.example.bq.edmp.word.inventory.adapter.BaseCom_Ck_Adapter;
import com.example.bq.edmp.word.inventory.adapter.PzAdapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.word.purchase.adapter.PruchaseListAdapter;
import com.example.bq.edmp.word.purchase.api.PurchaseListApi;
import com.example.bq.edmp.word.purchase.bean.PurchaseListBean;
import com.example.bq.edmp.work.grainmanagement.activity.AcquisitionDetailAct;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 生产管理   收购记录
 * */
public class PurchaseListActivity extends BaseActivity {

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
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.start_time_tv)
    TextView start_time_tv;
    @BindView(R.id.end_time_tv)
    TextView end_time_tv;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.cz_tv)
    TextView cz_tv;

    private String BEGINTIME = "";
    private String ENDTIME = "";
    private String CODE = "";//单号
    private String FARMERNAME = "";//单号
    private String ORGIDS = "";//公司id
    private String virtual_orgIds = "";//公司id
    private String org_name = "";//公司名字
    private String VARIETYID = "";//品种id
    private int CURRENTPAGER = 1;//当前页面
    private List<PurchaseListBean.RowsBean> rowsBeans;
    private PruchaseListAdapter pruchaseListAdapter;
    private List<SxPzBean.DataBean> data;
    private ArrayList<SxPzBean.DataBean> dataBeans;
    private PzAdapter pzAdapter;
    private ArrayList<CompanyBean.DataBean> companyDataBeans;
    private BaseCom_Ck_Adapter baseCom_ck_adapter;

    @Override
    protected void initData() {
        gainData();
    }


    @Override
    protected void initView() {
        rowsBeans = new ArrayList<>();
        pruchaseListAdapter = new PruchaseListAdapter(rowsBeans);
        xr.setAdapter(pruchaseListAdapter);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(PurchaseListActivity.this));
        xr.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                CURRENTPAGER = 1;
                initData();
                xr.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++CURRENTPAGER;
                initData2();
                xr.loadMoreComplete();
            }
        });
        pruchaseListAdapter.setOnItemClickListener(new PruchaseListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, PurchaseListBean.RowsBean rowsBean) {
                AcquisitionDetailAct.newIntent(getApplicationContext(),rowsBean.getId()+"");
            }
        });


        //筛选  品种适配器
        dataBeans = new ArrayList<>();
        pzAdapter = new PzAdapter(dataBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(PurchaseListActivity.this, 3));
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
                    VARIETYID = dataBean.getId();
                } else {
                    VARIETYID = "";
                }
            }
        });
    }

    private void gainData() {
        CURRENTPAGER = 1;
        String sign = MD5Util.encode("beginTime=" + BEGINTIME + "&code=" + CODE + "&endTime=" + ENDTIME +
                "&farmerName=" + FARMERNAME + "&orgIds=" + ORGIDS + "&page=" + CURRENTPAGER +
                "&pagerow=" + 15 + "&varietyId=" + VARIETYID);

        RxHttpUtils.createApi(PurchaseListApi.class)
                .getPurchaseData(BEGINTIME, CODE, ENDTIME, FARMERNAME, ORGIDS, CURRENTPAGER, 15, VARIETYID, sign)
                .compose(Transformer.<PurchaseListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<PurchaseListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(PurchaseListBean purchaseListBean) {
                        if (purchaseListBean.getRows() != null && purchaseListBean.getRows().size() != 0) {
                            start_time_tv.setHint("请选择开始时间");
                            end_time_tv.setHint("请选择结束时间");
                            start_time_tv.setText("");
                            end_time_tv.setText("");
                            fen_company_tv.setText("");
                            fen_company_tv.setHint("所有分子公司");
                            fen_company_tv.setHintTextColor(getResources().getColor(R.color.color_66000000));
                            ORGIDS = "";
                            VARIETYID = "";
                            org_name = "";
                            org_name = "";

                            rowsBeans.clear();
                            rowsBeans.addAll(purchaseListBean.getRows());
                            pruchaseListAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("beginTime=" + BEGINTIME + "&code=" + CODE + "&endTime=" + ENDTIME +
                "&farmerName=" + FARMERNAME + "&orgIds=" + ORGIDS + "&page=" + CURRENTPAGER +
                "&pagerow=" + 15 + "&varietyId=" + VARIETYID);

        RxHttpUtils.createApi(PurchaseListApi.class)
                .getPurchaseData(BEGINTIME, CODE, ENDTIME, FARMERNAME, ORGIDS, CURRENTPAGER, 15, VARIETYID, sign)
                .compose(Transformer.<PurchaseListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<PurchaseListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(PurchaseListBean purchaseListBean) {
                        if (purchaseListBean.getRows() != null && purchaseListBean.getRows().size() != 0) {
                            rowsBeans.addAll(purchaseListBean.getRows());
                            pruchaseListAdapter.addMoreData(purchaseListBean.getRows());
                        } else {
                            ToastUtil.setToast("暂无数据");
                            xr.setNoMore(true);
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_purchase_list;
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        screen_img.setOnClickListener(this);
        fen_company_rl.setOnClickListener(this);
        cz_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
        start_time_tv.setOnClickListener(this);
        end_time_tv.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.screen_img://筛选按钮
                sxMethodData();
                break;
            case R.id.fen_company_rl://筛选   分公司
                findContentViews2(view);
                break;
            case R.id.cz_tv://重置
                start_time_tv.setHint("请选择开始时间");
                end_time_tv.setHint("请选择结束时间");
                start_time_tv.setText("");
                end_time_tv.setText("");
                fen_company_tv.setHint("所有分子公司");
                fen_company_tv.setHintTextColor(getResources().getColor(R.color.color_66000000));
                ORGIDS = "";
                VARIETYID = "";
                virtual_orgIds = "";
                org_name = "";
                dataBeans.get(0).setSelected(true);
                pzAdapter.notifyDataSetChanged();
                sxMethodData();
                break;
            case R.id.affirm_tv://筛选  确定
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
                gainData();
                break;
            case R.id.start_time_tv://开始时间

                break;
            case R.id.end_time_tv://结束时间

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
                virtual_orgIds = "";
                org_name = "";
                mCameraDialog.dismiss();
            }
        });

        yes_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ORGIDS = virtual_orgIds;
                fen_company_tv.setText(org_name);
                fen_company_tv.setTextColor(getResources().getColor(R.color.text_black));
                mCameraDialog.dismiss();
            }
        });

        //筛选  分公司，仓库适配器
        companyDataBeans = new ArrayList<>();
        baseCom_ck_adapter = new BaseCom_Ck_Adapter(companyDataBeans);
        buttom_rv.setLayoutManager(new LinearLayoutManager(PurchaseListActivity.this));
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
                org_name = dataBean.getName();

            }
        });

        btuomMethod();
    }


    private void btuomMethod() {
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