package com.example.bq.edmp.work.order.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
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
import com.example.bq.edmp.work.order.adapter.ChoocseCustomerAdapter;
import com.example.bq.edmp.work.order.adapter.GoodslistAdapter;
import com.example.bq.edmp.work.order.adapter.OrderAdapter;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.CustomerBean;
import com.example.bq.edmp.work.order.bean.OrderTJBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 订单管理
 * */
public class Order_GL_Activity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.new_order_tv)
    TextView new_order_tv;


    private int currentPager = 1;
    private LoadingDialog loading_dialog;
    private OrderAdapter orderAdapter;
    private ArrayList<OrderTJBean.DataBean.RowsBean> rowsBeans;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_gl;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(Order_GL_Activity.this);
        loading_dialog = new LoadingDialog(this);
        title_tv.setText("订单管理");


        rowsBeans = new ArrayList<>();
        orderAdapter = new OrderAdapter(rowsBeans);
        xRecyclerView.setAdapter(orderAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(Order_GL_Activity.this));
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
        orderAdapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, OrderTJBean.DataBean.RowsBean rowsBean) {
                Intent intent = new Intent(Order_GL_Activity.this, ModifyOrderActivity.class);
                intent.putExtra("orderid", rowsBean.getId());
                startActivityForResult(intent, 250);
            }
        });
    }


    @Override
    protected void initData() {
        currentPager = 1;

        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(OrderApi.class)
                .getSubmitlist(currentPager, 15, sign)
                .compose(Transformer.<OrderTJBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<OrderTJBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(OrderTJBean orderTJBean) {
                        String code = orderTJBean.getCode();
                        if (code.equals("200")) {
                            List<OrderTJBean.DataBean.RowsBean> rows = orderTJBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {

                                xRecyclerView.setVisibility(View.VISIBLE);
                                wsj.setVisibility(View.GONE);

                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                orderAdapter.notifyDataSetChanged();

                            } else {
                                xRecyclerView.setVisibility(View.GONE);
                                wsj.setVisibility(View.VISIBLE);
                                rowsBeans.clear();
                                orderAdapter.notifyDataSetChanged();
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

        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15);
        RxHttpUtils.createApi(OrderApi.class)
                .getSubmitlist(currentPager, 15, sign)
                .compose(Transformer.<OrderTJBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<OrderTJBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(OrderTJBean orderTJBean) {
                        String code = orderTJBean.getCode();
                        if (code.equals("200")) {
                            rowsBeans.addAll(orderTJBean.getData().getRows());
                            orderAdapter.addMoreData(orderTJBean.getData().getRows());
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
        new_order_tv.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.new_order_tv://新增订单
                Intent intent = new Intent(Order_GL_Activity.this, NewOrderActivity.class);
                startActivityForResult(intent,250);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(Order_GL_Activity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250) {
            initData();
        }
    }
}
