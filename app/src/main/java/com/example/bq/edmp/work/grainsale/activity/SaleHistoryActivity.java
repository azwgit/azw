package com.example.bq.edmp.work.grainsale.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.inventory.adapter.BaseCom_Ck_Adapter;
import com.example.bq.edmp.word.inventory.adapter.PzAdapter;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.work.baozhuang.BzBean;
import com.example.bq.edmp.work.baozhuang.adapter.BzAdapter;
import com.example.bq.edmp.work.finishedproduct.activity.FinishedWarehousingOutDetailActivity;
import com.example.bq.edmp.work.grainsale.adapter.SaleHistoryAdapter;
import com.example.bq.edmp.work.grainsale.api.SaleApi;
import com.example.bq.edmp.work.grainsale.bean.SaleHistoryBean;
import com.example.bq.edmp.work.library.api.LibraryApi;
import com.example.bq.edmp.work.library.bean.ChuLibraryBean;
import com.example.bq.edmp.work.reseller.activity.ResellerTrackActivity;
import com.example.bq.edmp.work.reseller.adapter.ResellerPzAdapter;
import com.example.bq.edmp.work.reseller.api.ResellerApi;
import com.example.bq.edmp.work.reseller.bean.PzBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.http.Field;

/*
 * 销售历史
 * */
public class SaleHistoryActivity extends BaseTitleActivity {

    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.screen_img)
    ImageView screen_img;
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
    @BindView(R.id.shai_rv)
    RecyclerView recyclerView;
    @BindView(R.id.shaixuan_wsj)
    TextView shaixuan_wsj;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.cz_tv)
    TextView cz_tv;


    private LoadingDialog loadingDialog;

    private int currentPager = 1;
    private String orgIds = "";//公司id
    private String varietyId = "";//品种id
    private String code = "";//单号
    private ArrayList<SaleHistoryBean.DataBean.RowsBean> rowsBeans;


    private ArrayList<CompanyBean.DataBean> companyDataBeans;
    private BaseCom_Ck_Adapter baseCom_ck_adapter;
    private ArrayList<PzBean.DataBean> dataBeans;
    private List<SxPzBean.DataBean> data;
    private PzAdapter pzAdapter;
    private ArrayList<BzBean.DataBean> bzdataBeans;
    private List<BzBean.DataBean> bzdata;
    private BzAdapter bzAdapter;
    private boolean Fund = false;//判断返回顺序
    private TextView wsj_dial;
    private XRecyclerView buttom_rv;
    private SaleHistoryAdapter saleHistoryAdapter;
    private ResellerPzAdapter resellerPzAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_sale_history;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(SaleHistoryActivity.this);
        txtTabTitle.setText("商品粮销售历史");
        loadingDialog = new LoadingDialog(this);


        //数据
        rowsBeans = new ArrayList<>();
        saleHistoryAdapter = new SaleHistoryAdapter(rowsBeans);
        xr.setAdapter(saleHistoryAdapter);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(SaleHistoryActivity.this));
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
        saleHistoryAdapter.setOnItemClickListener(new SaleHistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, SaleHistoryBean.DataBean.RowsBean rowsBean) {
                Intent intent = new Intent(SaleHistoryActivity.this, SaleHistoryDetailsActivity.class);
                intent.putExtra("id", rowsBean.getId());
                startActivityForResult(intent, 250);
            }
        });

        //搜索
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //此处做逻辑处理
                    Fund = true;
                    code = textView.getText().toString();
                    hideKeyboard(search_et);
                    gainData();
                    return true;
                }
                return false;
            }
        });


        //筛选  品种适配器
        dataBeans = new ArrayList<>();
        resellerPzAdapter = new ResellerPzAdapter(dataBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(SaleHistoryActivity.this, 3));
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
        gainData();
    }

    private void gainData() {
        currentPager = 1;

        String sign = MD5Util.encode("code=" + code + "&orgId=" + orgIds + "&page=" + currentPager +
                "&pagerow=" + 15 + "&varietyId=" + varietyId);

        RxHttpUtils.createApi(SaleApi.class)
                .getHistoryData(code, orgIds, currentPager, 15, varietyId, sign)
                .compose(Transformer.<SaleHistoryBean>switchSchedulers())
                .subscribe(new NewCommonObserver<SaleHistoryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SaleHistoryBean saleHistoryBean) {
                        List<SaleHistoryBean.DataBean.RowsBean> rows = saleHistoryBean.getData().getRows();
                        if (rows != null && rows.size() != 0) {
                            xr.setVisibility(ViewGroup.VISIBLE);
                            wsj.setVisibility(ViewGroup.GONE);

                            rowsBeans.clear();
                            rowsBeans.addAll(rows);
                            saleHistoryAdapter.notifyDataSetChanged();
                        } else {
                            xr.setVisibility(ViewGroup.GONE);
                            wsj.setVisibility(ViewGroup.VISIBLE);

                            rowsBeans.clear();
                            saleHistoryAdapter.notifyDataSetChanged();
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("code=" + code + "&orgId=" + orgIds + "&page=" + currentPager +
                "&pagerow=" + 15 + "&varietyId=" + varietyId);
        RxHttpUtils.createApi(SaleApi.class)
                .getHistoryData(code, orgIds, currentPager, 15, varietyId, sign)
                .compose(Transformer.<SaleHistoryBean>switchSchedulers())
                .subscribe(new NewCommonObserver<SaleHistoryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SaleHistoryBean saleHistoryBean) {
                        List<SaleHistoryBean.DataBean.RowsBean> rows = saleHistoryBean.getData().getRows();
                        if (rows != null && rows.size() != 0) {
                            rowsBeans.addAll(rows);
                            saleHistoryAdapter.addMoreData(rows);
                        } else {
                            xr.setNoMore(true);
                        }
                    }
                });
    }

    @Override
    protected void initListener() {
        fen_company_rl.setOnClickListener(this);
        screen_img.setOnClickListener(this);
        cz_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                if (Fund) {
                    Fund = false;
                    code = "";
                    gainData();
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
                orgIds = "";
                sxMethodData();
                break;
            case R.id.affirm_tv:
                if (orgIds.equals("")) {
                    ToastUtil.setToast("请选择公司");
                    break;
                }
                Fund = true;
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
        buttom_rv.setLayoutManager(new LinearLayoutManager(SaleHistoryActivity.this));
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
                orgIds = dataBean.getId();
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(SaleHistoryActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250) {
            gainData();
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
