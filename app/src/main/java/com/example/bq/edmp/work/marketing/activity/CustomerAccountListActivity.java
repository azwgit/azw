package com.example.bq.edmp.work.marketing.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.finished.MachineScanActivity;
import com.example.bq.edmp.work.finished.adapter.DmachineListAdapter;
import com.example.bq.edmp.work.finished.api.MachineApi;
import com.example.bq.edmp.work.finished.bean.MachineListBean;
import com.example.bq.edmp.work.finishedproduct.activity.MachiningTaskDetailsActivity;
import com.example.bq.edmp.work.marketing.api.CustomerManagementApi;
import com.example.bq.edmp.work.marketing.adapter.AccountListAdp;
import com.example.bq.edmp.work.marketing.bean.CustomerAccountListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/*
 * 客户账户
 * */
public class CustomerAccountListActivity extends BaseTitleActivity {
    public static void newIntent(Context context) {
        Intent intent = new Intent(context, CustomerAccountListActivity.class);
        context.startActivity(intent);
    }
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.xr)
    XRecyclerView xr;
    private int currentPager = 1;
    private String name = "";
    private ArrayList<CustomerAccountListBean.DataBean.RowsBean> rowsBeans;
    private AccountListAdp accountListAdp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_account_list;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("客户账户");
        ProApplication.getinstance().addActivity(this);
        //数据
        rowsBeans = new ArrayList<>();
        accountListAdp = new AccountListAdp(rowsBeans);
        xr.setAdapter(accountListAdp);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(CustomerAccountListActivity.this));
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

        accountListAdp.setOnItemClickListener(new AccountListAdp.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, CustomerAccountListBean.DataBean.RowsBean rowsBean) {
                CustomerAccountActivity.newIntent(getApplicationContext(),rowsBean.getCustomerId()+"","2");
            }
        });

        //搜索
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //此处做逻辑处理
                    hideKeyboard(search_et);
                    name = textView.getText().toString();
                    gainData();
                    return true;
                }
                return false;

            }
        });
    }


    @Override
    protected void initData() {
        gainData();
    }

    private void gainData() {
        currentPager = 1;

        String sign = MD5Util.encode("name=" + name + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(CustomerManagementApi.class)
                .getCustomerAccountList(name, currentPager, 15, sign)
                .compose(Transformer.<CustomerAccountListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<CustomerAccountListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CustomerAccountListBean machineListBean) {
                        if (machineListBean.getCode()==200) {
                            wsj.setVisibility(View.GONE);
                            xr.setVisibility(View.VISIBLE);
                            List<CustomerAccountListBean.DataBean.RowsBean> rows = machineListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                accountListAdp.notifyDataSetChanged();
                            }else {
                                wsj.setVisibility(View.VISIBLE);
                                xr.setVisibility(View.GONE);
                                rowsBeans.clear();
                                accountListAdp.notifyDataSetChanged();
                                ToastUtil.setToast("暂无数据");
                            }
                        } else {
                            wsj.setVisibility(View.VISIBLE);
                            xr.setVisibility(View.GONE);
                            rowsBeans.clear();
                            accountListAdp.notifyDataSetChanged();
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("name=" + name + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(CustomerManagementApi.class)
                .getCustomerAccountList(name, currentPager, 15, sign)
                .compose(Transformer.<CustomerAccountListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<CustomerAccountListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CustomerAccountListBean machineListBean) {
                        if (machineListBean.getCode()==200) {
                            List<CustomerAccountListBean.DataBean.RowsBean> rows = machineListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.addAll(rows);
                                accountListAdp.addMoreData(rows);
                            }
                        } else {
                            xr.setNoMore(true);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {

        }

    }

    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}