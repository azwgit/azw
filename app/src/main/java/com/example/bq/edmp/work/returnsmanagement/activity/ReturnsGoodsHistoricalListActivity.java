package com.example.bq.edmp.work.returnsmanagement.activity;

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
import com.example.bq.edmp.work.marketing.api.CustomerManagementApi;
import com.example.bq.edmp.work.marketing.bean.CustomerAccountListBean;
import com.example.bq.edmp.work.marketingactivities.activity.HistoricalActivitiesDetailsActivity;
import com.example.bq.edmp.work.marketingactivities.adapter.HistoricalActivitiesListAdp;
import com.example.bq.edmp.work.marketingactivities.adapter.MarketingActivityListAdp;
import com.example.bq.edmp.work.marketingactivities.api.MarketingActivitiesApi;
import com.example.bq.edmp.work.marketingactivities.bean.HistoricalListBean;
import com.example.bq.edmp.work.returnsmanagement.adapter.ReturnsGoodsHistoricalListAdp;
import com.example.bq.edmp.work.returnsmanagement.api.ReturnGoodsApi;
import com.example.bq.edmp.work.returnsmanagement.bean.ReturnTrackingListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 历史退单
 * */
public class ReturnsGoodsHistoricalListActivity extends BaseTitleActivity {
    public static void newIntent(Context context) {
        Intent intent = new Intent(context, ReturnsGoodsHistoricalListActivity.class);
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
    private ArrayList<ReturnTrackingListBean.DataBean.RowsBean> rowsBeans;
    private ReturnsGoodsHistoricalListAdp returnsGoodsHistoricalListAdp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_return_goods_historical_list;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("历史退单");
        ProApplication.getinstance().addActivity(this);
        //数据
        rowsBeans = new ArrayList<>();
        returnsGoodsHistoricalListAdp = new ReturnsGoodsHistoricalListAdp(rowsBeans);
        xr.setAdapter(returnsGoodsHistoricalListAdp);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(ReturnsGoodsHistoricalListActivity.this));
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

        returnsGoodsHistoricalListAdp.setOnItemClickListener(new ReturnsGoodsHistoricalListAdp.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, ReturnTrackingListBean.DataBean.RowsBean rowsBean) {
                ReturnsGoodsHistoricalDetailsActivity.newIntent(getApplicationContext(), rowsBean.getId() + "");
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

        String sign = MD5Util.encode("code=" + name + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .getReturnGoodsHistoricalList(name, currentPager, 15, sign)
                .compose(Transformer.<ReturnTrackingListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<ReturnTrackingListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ReturnTrackingListBean machineListBean) {
                        if (machineListBean.getCode() == 200) {
                            wsj.setVisibility(View.GONE);
                            xr.setVisibility(View.VISIBLE);
                            List<ReturnTrackingListBean.DataBean.RowsBean> rows = machineListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                returnsGoodsHistoricalListAdp.notifyDataSetChanged();
                            } else {
                                wsj.setVisibility(View.VISIBLE);
                                xr.setVisibility(View.GONE);
                                rowsBeans.clear();
                                returnsGoodsHistoricalListAdp.notifyDataSetChanged();
                                ToastUtil.setToast("暂无数据");
                            }
                        } else {
                            wsj.setVisibility(View.VISIBLE);
                            xr.setVisibility(View.GONE);
                            rowsBeans.clear();
                            returnsGoodsHistoricalListAdp.notifyDataSetChanged();
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("code=" + name + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .getReturnGoodsHistoricalList(name, currentPager, 15, sign)
                .compose(Transformer.<ReturnTrackingListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<ReturnTrackingListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ReturnTrackingListBean machineListBean) {
                        if (machineListBean.getCode() == 200) {
                            List<ReturnTrackingListBean.DataBean.RowsBean> rows = machineListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.addAll(rows);
                                returnsGoodsHistoricalListAdp.addMoreData(rows);
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