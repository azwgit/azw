package com.example.bq.edmp.work.order.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseFragment;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.order.activity.ChooseCustomerActivity;
import com.example.bq.edmp.work.order.activity.OrderTrackingActivity;
import com.example.bq.edmp.work.order.activity.Order_Tracking_DetailsActivity;
import com.example.bq.edmp.work.order.adapter.OrderTrackingAdapter;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.OrderTrackingBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.http.Field;

/**
 * 订单跟踪 Fragment
 */
@SuppressLint("ValidFragment")
public class OrderTrackingFragment extends BaseFragment {

    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsj)
    TextView wsj;

    private int mStatus = 0;
    private int currentPager = 1;
    private LoadingDialog loadingDialog;
    private ArrayList<OrderTrackingBean.DataBean.RowsBean> rowsBeans;
    private OrderTrackingAdapter orderTrackingAdapter;

    @SuppressLint("ValidFragment")
    public OrderTrackingFragment(int i) {
        // Required empty public constructor
        mStatus = i;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_order_tracking;
    }

    @Override
    protected void initView() {
        loadingDialog = new LoadingDialog(getActivity());

        rowsBeans = new ArrayList<>();
        orderTrackingAdapter = new OrderTrackingAdapter(rowsBeans);
        xRecyclerView.setAdapter(orderTrackingAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        orderTrackingAdapter.setOnItemClickListener(new OrderTrackingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, OrderTrackingBean.DataBean.RowsBean rowsBean) {
                ToastUtil.setToast(rowsBean.getSaleName());
                Intent intent = new Intent(getActivity(), Order_Tracking_DetailsActivity.class);
                intent.putExtra("ID",rowsBean.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        currentPager = 1;

        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15 + "&status=" + mStatus);

        RxHttpUtils.createApi(OrderApi.class)
                .getTracklist(currentPager, 15, mStatus, sign)
                .compose(Transformer.<OrderTrackingBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<OrderTrackingBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(OrderTrackingBean orderTrackingBean) {
                        String code = orderTrackingBean.getCode();
                        if (code.equals("200")) {
                            List<OrderTrackingBean.DataBean.RowsBean> rows = orderTrackingBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                xRecyclerView.setVisibility(View.VISIBLE);
                                wsj.setVisibility(View.GONE);

                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                orderTrackingAdapter.notifyDataSetChanged();

                            } else {
                                xRecyclerView.setVisibility(View.GONE);
                                wsj.setVisibility(View.VISIBLE);
                                rowsBeans.clear();
                                orderTrackingAdapter.notifyDataSetChanged();
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

        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15 + "&status=" + mStatus);

        RxHttpUtils.createApi(OrderApi.class)
                .getTracklist(currentPager, 15, mStatus, sign)
                .compose(Transformer.<OrderTrackingBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<OrderTrackingBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(OrderTrackingBean orderTrackingBean) {
                        String code = orderTrackingBean.getCode();
                        if (code.equals("200")) {
                            List<OrderTrackingBean.DataBean.RowsBean> rows = orderTrackingBean.getData().getRows();
                            rowsBeans.addAll(rows);
                            orderTrackingAdapter.addMoreData(rows);
                        } else {
                            xRecyclerView.setNoMore(true);
                            ToastUtil.setToast("最后页");
                        }
                    }
                });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void otherViewClick(View view) {

    }
}
