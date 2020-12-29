package com.example.bq.edmp.work.order.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
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
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.allocation.AllocationApplyActivity;
import com.example.bq.edmp.work.allocation.adapter.AllocationApplyListAdapter;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.order.adapter.ChoocseCustomerAdapter;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.CustomerBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 选择客户
 * */
public class ChooseCustomerActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsj)
    TextView wsj;

    private int currentPager = 1;
    private String mName = "";//客户名称
    private boolean Fund = false;
    private LoadingDialog loadingDialog;
    private ArrayList<CustomerBean.DataBean.RowsBean> rowsBeans;
    private ChoocseCustomerAdapter choocseCustomerAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_customer;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(ChooseCustomerActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("选择客户");


        rowsBeans = new ArrayList<>();
        choocseCustomerAdapter = new ChoocseCustomerAdapter(rowsBeans);
        xRecyclerView.setAdapter(choocseCustomerAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(ChooseCustomerActivity.this));
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
        choocseCustomerAdapter.setOnItemClickListener(new ChoocseCustomerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, CustomerBean.DataBean.RowsBean rowsBean) {
                Intent intent = new Intent();
                intent.putExtra("rowsbean", rowsBean);
                setResult(350, intent);
                finish();
            }
        });
        //搜索
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //此处做逻辑处理
                    Fund = true;
                    mName = textView.getText().toString();
                    hideKeyboard(search_et);
                    initData();
                    return true;
                }
                return false;
            }
        });


    }

    @Override
    protected void initData() {
        currentPager = 1;

        String sign = MD5Util.encode("name=" + mName + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(OrderApi.class)
                .getCustomerlist(mName, currentPager, 15, sign)
                .compose(Transformer.<CustomerBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<CustomerBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CustomerBean customerBean) {
                        String code = customerBean.getCode();
                        if (code.equals("200")) {
                            List<CustomerBean.DataBean.RowsBean> rows = customerBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                xRecyclerView.setVisibility(View.VISIBLE);
                                wsj.setVisibility(View.GONE);

                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                choocseCustomerAdapter.notifyDataSetChanged();

                            } else {
                                xRecyclerView.setVisibility(View.GONE);
                                wsj.setVisibility(View.VISIBLE);

                                rowsBeans.clear();
                                choocseCustomerAdapter.notifyDataSetChanged();
                                ToastUtil.setToast("暂无数据");
                            }
                        } else {
                            xRecyclerView.setVisibility(View.GONE);
                            wsj.setVisibility(View.VISIBLE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {

        String sign = MD5Util.encode("name=" + mName + "&page=" + currentPager + "&pagerow=" + 15);


        RxHttpUtils.createApi(OrderApi.class)
                .getCustomerlist(mName, currentPager, 15, sign)
                .compose(Transformer.<CustomerBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<CustomerBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CustomerBean customerBean) {
                        String code = customerBean.getCode();
                        if (code.equals("200")) {
                            List<CustomerBean.DataBean.RowsBean> rows = customerBean.getData().getRows();
                            rowsBeans.addAll(rows);
                            choocseCustomerAdapter.addMoreData(rows);
                        } else {
                            xRecyclerView.setNoMore(true);
                            ToastUtil.setToast("最后页");
                        }
                    }
                });
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                if (Fund) {
                    mName = "";
                    search_et.setText("");
                    Fund = false;
                    initData();
                } else {
                    fund();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(ChooseCustomerActivity.this);
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
