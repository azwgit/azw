package com.example.bq.edmp.work.shipments;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.inventory.adapter.BaseCom_Ck_Adapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.work.shipments.adapter.DshipmentsListAdapter;
import com.example.bq.edmp.work.shipments.adapter.UserNameListAdapter;
import com.example.bq.edmp.work.shipments.api.ShipmentsApi;
import com.example.bq.edmp.work.shipments.bean.DshipmentsListBean;
import com.example.bq.edmp.work.shipments.bean.UserNameListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 发货管理    待发货
 * */
public class DshipmentsActivity extends BaseActivity {

    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.search_et)
    TextView search_et;
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
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.cz_tv)
    TextView cz_tv;

    private String orgIds = "";//公司id
    private String virtual_orgIds = "";//公司id
    private String org_name = "";//公司名字
    private String customerId = "";//客户id
    private String customerName = "";//客户名字
    private int currentPager = 1;

    private XRecyclerView buttom_rv;
    private TextView wsj_dial;
    private ArrayList<CompanyBean.DataBean> companyDataBeans;
    private BaseCom_Ck_Adapter baseCom_ck_adapter;
    private boolean Fund = false;
    private ArrayList<DshipmentsListBean.DataBean.RowsBean> rowsBeans;
    private DshipmentsListAdapter dshipmentsListAdapter;
    private RecyclerView user_rv;
    private TextView user_wsj;
    private ArrayList<UserNameListBean.DataBean> userNameAtaBeans;
    private UserNameListAdapter userNameListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dshipments;
    }

    @Override
    protected void initView() {
        title_tv.setText("待发货");

        //数据
        rowsBeans = new ArrayList<>();
        dshipmentsListAdapter = new DshipmentsListAdapter(rowsBeans);
        xr.setAdapter(dshipmentsListAdapter);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(DshipmentsActivity.this));
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

        dshipmentsListAdapter.setOnItemClickListener(new DshipmentsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, DshipmentsListBean.DataBean.RowsBean rowsBean) {
                ToastUtil.setToast(rowsBean.getId());
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
                orgIds = "";
                org_name = "";
                virtual_orgIds = "";
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


    }

    @Override
    protected void initData() {
        gainData();
    }

    private void gainData() {
        currentPager = 1;
        String sign = MD5Util.encode("customerId=" + customerId +
                "&orgId=" + orgIds + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(ShipmentsApi.class)
                .getDshipmentsData(customerId, orgIds, currentPager, 15, sign)
                .compose(Transformer.<DshipmentsListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<DshipmentsListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DshipmentsListBean dshipmentsListBean) {
                        if (dshipmentsListBean.getCode().equals("200")) {
                            List<DshipmentsListBean.DataBean.RowsBean> rows = dshipmentsListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                orgIds = "";
                                org_name = "";
                                virtual_orgIds = "";
                                customerId = "";
                                xr.setVisibility(ViewGroup.VISIBLE);
                                wsj.setVisibility(ViewGroup.GONE);

                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                dshipmentsListAdapter.notifyDataSetChanged();
                            } else {
                                xr.setVisibility(ViewGroup.GONE);
                                wsj.setVisibility(ViewGroup.VISIBLE);

                                rowsBeans.clear();
                                dshipmentsListAdapter.notifyDataSetChanged();
                                ToastUtil.setToast("暂无数据");
                            }
                        } else {
                            orgIds = "";
                            org_name = "";
                            virtual_orgIds = "";
                            customerId = "";
                            xr.setVisibility(ViewGroup.GONE);
                            wsj.setVisibility(ViewGroup.VISIBLE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("customerId=" + customerId +
                "&orgId=" + orgIds + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(ShipmentsApi.class)
                .getDshipmentsData(customerId, orgIds, currentPager, 15, sign)
                .compose(Transformer.<DshipmentsListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<DshipmentsListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DshipmentsListBean dshipmentsListBean) {
                        if (dshipmentsListBean.getCode().equals("200")) {
                            List<DshipmentsListBean.DataBean.RowsBean> rows = dshipmentsListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.addAll(rows);
                                dshipmentsListAdapter.addMoreData(rows);
                            } else {
                                xr.setNoMore(true);
                            }
                        } else {
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        screen_img.setOnClickListener(this);
        fen_company_rl.setOnClickListener(this);
        cz_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
        search_et.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                if (Fund) {
                    search_et.setText("");
                    search_et.setHint("请输入客户查找");
                    orgIds = "";
                    org_name = "";
                    virtual_orgIds = "";
                    Fund = false;
                    gainData();
                } else if (!search_et.getText().toString().equals("")){
                    search_et.setText("");
                    search_et.setHint("请输入客户查找");
                    orgIds = "";
                    org_name = "";
                    virtual_orgIds = "";
                    customerId = "";
                    Fund = false;
                    gainData();
                }else {
                    fund();
                }
                break;
            case R.id.screen_img:
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.fen_company_rl:
                findContentViews2(view);
                break;
            case R.id.cz_tv:
                fen_company_tv.setText("");
                fen_company_tv.setHint("所有分子公司");
                org_name = "";
                orgIds = "";
                virtual_orgIds = "";
                break;
            case R.id.affirm_tv:
                if (fen_company_tv.getText().toString().equals("")) {
                    ToastUtil.setToast("请选择分公司");
                    break;
                }
                Fund = true;
                gainData();
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
                break;
            case R.id.search_et:
                findUserViews(view);
                break;
        }
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
                virtual_orgIds = "";
                org_name = "";
                mCameraDialog.dismiss();
            }
        });

        yes_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orgIds = virtual_orgIds;
                fen_company_tv.setText(org_name);
                fen_company_tv.setTextColor(getResources().getColor(R.color.text_black));
                mCameraDialog.dismiss();
            }
        });

        //筛选  分公司
        companyDataBeans = new ArrayList<>();
        baseCom_ck_adapter = new BaseCom_Ck_Adapter(companyDataBeans);
        buttom_rv.setLayoutManager(new LinearLayoutManager(DshipmentsActivity.this));
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

    /**
     * 隐藏软键盘
     *
     * @param context :上下文环境，一般为Activity实例
     * @param view    :一般为EditText
     */
    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /*
     * 客户底部跳框
     * */
    private void findUserViews(View view) {
        final Dialog mCameraDialog = new Dialog(view.getContext(), R.style.my_dialog);

        View root = LayoutInflater.from(view.getContext()).inflate(R.layout.user_buttom_item, null);

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

        final EditText user_search_et = root.findViewById(R.id.search_et);
        final TextView no_tv = root.findViewById(R.id.no_tv);
        user_rv = root.findViewById(R.id.user_rv);
        user_wsj = root.findViewById(R.id.user_wsj);

        no_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(no_tv);
                mCameraDialog.dismiss();
            }
        });

        user_search_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        userNameAtaBeans = new ArrayList<>();
        userNameListAdapter = new UserNameListAdapter(userNameAtaBeans);
        user_rv.setLayoutManager(new LinearLayoutManager(DshipmentsActivity.this));
        user_rv.setAdapter(userNameListAdapter);
        userNameListAdapter.setOnItemClickListener(new UserNameListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, UserNameListBean.DataBean dataBean,View view) {
                //当点击时显示当前条目的背景和文字的颜色
                for (int i = 0; i < userNameAtaBeans.size(); i++) {
                    if (pos == i) {
                        userNameAtaBeans.get(i).setSelected(true);
                    } else {
                        userNameAtaBeans.get(i).setSelected(false);
                    }
                }
                userNameListAdapter.notifyDataSetChanged();
                customerId = dataBean.getId();
                search_et.setText(dataBean.getName());
                hideKeyboard(view);
                mCameraDialog.dismiss();
                gainData();
            }
        });

        gainUserNameData();

        //搜索
        user_search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //此处做逻辑处理
                    customerName = textView.getText().toString();
                    hideKeyboard(user_search_et);
                    gainUserNameData();
                    return true;
                }
                return false;
            }
        });

        user_search_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().equals("")){
                    gainUserNameData();
                }
            }
        });

    }

    private void gainUserNameData() {
        String sign = MD5Util.encode("name=" + customerName);

        RxHttpUtils.createApi(ShipmentsApi.class)
                .getUserNameData(customerName, sign)
                .compose(Transformer.<UserNameListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<UserNameListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(UserNameListBean userNameListBean) {
                        if (userNameListBean.getCode().equals("200")) {
                            user_rv.setVisibility(ViewGroup.VISIBLE);
                            user_wsj.setVisibility(ViewGroup.GONE);
                            customerName = "";

                            List<UserNameListBean.DataBean> data = userNameListBean.getData();
                            userNameAtaBeans.clear();
                            userNameAtaBeans.addAll(data);
                            userNameListAdapter.notifyDataSetChanged();
                        } else {
                            user_rv.setVisibility(ViewGroup.GONE);
                            user_wsj.setVisibility(ViewGroup.VISIBLE);
                            userNameAtaBeans.clear();
                            userNameListAdapter.notifyDataSetChanged();
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }


}