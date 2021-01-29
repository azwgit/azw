package com.example.bq.edmp.work.marketing.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.finished.api.MachineApi;
import com.example.bq.edmp.work.finished.bean.MachineListBean;
import com.example.bq.edmp.work.marketing.adapter.AccountListAdp;
import com.example.bq.edmp.work.marketing.adapter.SalesContractListAdp;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 销售合同
 * */
public class SalesContractListActivity extends BaseTitleActivity {
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.xr)
    XRecyclerView xr;
    private int currentPager = 1;
    private String code = "";
    private ArrayList<MachineListBean.DataBean.RowsBean> rowsBeans;
    private SalesContractListAdp salesContractListAdp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sales_contract_list;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("销售合同");
        ProApplication.getinstance().addActivity(this);
        //数据
        rowsBeans = new ArrayList<>();
        salesContractListAdp = new SalesContractListAdp(rowsBeans);
        xr.setAdapter(salesContractListAdp);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(SalesContractListActivity.this));
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

        salesContractListAdp.setOnItemClickListener(new SalesContractListAdp.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, MachineListBean.DataBean.RowsBean rowsBean) {
                CustomerAccountActivity.newIntent(getApplicationContext(),"","2");
            }
        });

        //搜索
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //此处做逻辑处理
                    hideKeyboard(search_et);
                    code = textView.getText().toString();
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

        String sign = MD5Util.encode("code=" + code + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(MachineApi.class)
                .getDmachineData(code, currentPager, 15, sign)
                .compose(Transformer.<MachineListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<MachineListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(MachineListBean machineListBean) {
                        if (machineListBean.getCode().equals("200")) {
                            wsj.setVisibility(View.GONE);
                            xr.setVisibility(View.VISIBLE);
                            List<MachineListBean.DataBean.RowsBean> rows = machineListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                salesContractListAdp.notifyDataSetChanged();
                            }else {
                                wsj.setVisibility(View.VISIBLE);
                                xr.setVisibility(View.GONE);

                                rowsBeans.clear();
                                salesContractListAdp.notifyDataSetChanged();

                                ToastUtil.setToast("暂无数据");
                            }
                        } else {
                            wsj.setVisibility(View.VISIBLE);
                            xr.setVisibility(View.GONE);

                            rowsBeans.clear();
                            salesContractListAdp.notifyDataSetChanged();

                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("code=" + code + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(MachineApi.class)
                .getDmachineData(code, currentPager, 15, sign)
                .compose(Transformer.<MachineListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<MachineListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(MachineListBean machineListBean) {
                        if (machineListBean.getCode().equals("200")) {
                            List<MachineListBean.DataBean.RowsBean> rows = machineListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.addAll(rows);
                                salesContractListAdp.addMoreData(rows);
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