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
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.marketingactivities.activity.HistoricalActivitiesDetailsActivity;
import com.example.bq.edmp.work.marketingactivities.adapter.HistoricalActivitiesListAdp;
import com.example.bq.edmp.work.marketingactivities.api.MarketingActivitiesApi;
import com.example.bq.edmp.work.marketingactivities.bean.HistoricalListBean;
import com.example.bq.edmp.work.returnsmanagement.adapter.DeliverGoodsActivitiesListAdp;
import com.example.bq.edmp.work.returnsmanagement.api.ReturnGoodsApi;
import com.example.bq.edmp.work.returnsmanagement.bean.DeliverGoodsListBean;
import com.example.bq.edmp.work.returnsmanagement.eventbus.CloseActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 发货列表
 * */
public class DeliverGoodsListActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String customerid, String packingid, String title) {
        Intent intent = new Intent(context, DeliverGoodsListActivity.class);
        intent.putExtra(Constant.ID, customerid);
        intent.putExtra(Constant.TYPE, packingid);
        intent.putExtra(Constant.TITLE, title);
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
    private String customerId = "";
    private String packingId = "";
    private String title = "";
    private ArrayList<DeliverGoodsListBean.DataBean.RowsBean> rowsBeans;
    private DeliverGoodsActivitiesListAdp deliverGoodsActivitiesListAdp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_deliver_goods_list;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("发货列表");
        customerId = getIntent().getStringExtra(Constant.ID);
        packingId = getIntent().getStringExtra(Constant.TYPE);
        title = getIntent().getStringExtra(Constant.TITLE);
        if ("".equals(customerId) || "".equals(packingId)) {
            ToastUtil.setToast("数据出错请重试");
            finish();
            return;
        }
        EventBus.getDefault().register(this);
        search_et.setText(title);
        ProApplication.getinstance().addActivity(this);
        //数据
        rowsBeans = new ArrayList<>();
        deliverGoodsActivitiesListAdp = new DeliverGoodsActivitiesListAdp(rowsBeans);
        xr.setAdapter(deliverGoodsActivitiesListAdp);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(DeliverGoodsListActivity.this));
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

        deliverGoodsActivitiesListAdp.setOnItemClickListener(new DeliverGoodsActivitiesListAdp.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, DeliverGoodsListBean.DataBean.RowsBean rowsBean) {
                ApplyForRefundActivity.newIntent(getApplicationContext(), rowsBean.getOrdersId() + "", rowsBean.getItemId() + "");
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

        String sign = MD5Util.encode("customerId=" + customerId + "&itemId=" + packingId + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .getSendGoodsList(customerId, packingId, currentPager, 15, sign)
                .compose(Transformer.<DeliverGoodsListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<DeliverGoodsListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DeliverGoodsListBean machineListBean) {
                        if (machineListBean.getCode() == 200) {
                            wsj.setVisibility(View.GONE);
                            xr.setVisibility(View.VISIBLE);
                            List<DeliverGoodsListBean.DataBean.RowsBean> rows = machineListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                deliverGoodsActivitiesListAdp.notifyDataSetChanged();
                            } else {
                                wsj.setVisibility(View.VISIBLE);
                                xr.setVisibility(View.GONE);
                                rowsBeans.clear();
                                deliverGoodsActivitiesListAdp.notifyDataSetChanged();
                                ToastUtil.setToast("暂无数据");
                            }
                        } else {
                            wsj.setVisibility(View.VISIBLE);
                            xr.setVisibility(View.GONE);
                            rowsBeans.clear();
                            deliverGoodsActivitiesListAdp.notifyDataSetChanged();
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("customerId=" + customerId + "&itemId=" + packingId + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .getSendGoodsList(customerId, packingId, currentPager, 15, sign)
                .compose(Transformer.<DeliverGoodsListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<DeliverGoodsListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DeliverGoodsListBean machineListBean) {
                        if (machineListBean.getCode() == 200) {
                            List<DeliverGoodsListBean.DataBean.RowsBean> rows = machineListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.addAll(rows);
                                deliverGoodsActivitiesListAdp.addMoreData(rows);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getColoseActivity(CloseActivity event) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}