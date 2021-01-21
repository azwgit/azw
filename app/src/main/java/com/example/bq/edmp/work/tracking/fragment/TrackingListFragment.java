package com.example.bq.edmp.work.tracking.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseFragment;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.marketingactivities.activity.ActivitiesDetailsActivity;
import com.example.bq.edmp.work.tracking.adapter.TrackingListAdapter;
import com.example.bq.edmp.work.tracking.api.TrackingApi;
import com.example.bq.edmp.work.tracking.bean.TrackingListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class TrackingListFragment extends BaseFragment {

    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;

    private int currentPager = 1;
    private String status = "0";
    private ArrayList<TrackingListBean.DataBean.RowsBean> rowsBeans;
    private TrackingListAdapter trackingListAdapter;
    private LoadingDialog loadingDialog;

    @SuppressLint("ValidFragment")
    public TrackingListFragment(String s) {
        // Required empty public constructor
        status = s;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_tracking_list;
    }

    @Override
    protected void initView() {
        loadingDialog = new LoadingDialog(getActivity());

        rowsBeans = new ArrayList<>();
        trackingListAdapter = new TrackingListAdapter(rowsBeans);
        xRecyclerView.setAdapter(trackingListAdapter);
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
        trackingListAdapter.setOnItemClickListener(new TrackingListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, TrackingListBean.DataBean.RowsBean rowsBean) {
                Intent intent = new Intent(getActivity(), ActivitiesDetailsActivity.class);
                intent.putExtra("id", rowsBean.getId());
                startActivityForResult(intent, 250);
            }
        });

    }

    @Override
    protected void initData() {
        currentPager = 1;

        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15 + "&status=" + status);

        RxHttpUtils.createApi(TrackingApi.class)
                .getTrackingListData(currentPager, 15, status, sign)
                .compose(Transformer.<TrackingListBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<TrackingListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(TrackingListBean trackingListBean) {
                        String code = trackingListBean.getCode();
                        if (code.equals("200")) {
                            List<TrackingListBean.DataBean.RowsBean> rows = trackingListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {

                                xRecyclerView.setVisibility(View.VISIBLE);
                                wsj.setVisibility(View.GONE);

                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                trackingListAdapter.notifyDataSetChanged();

                            } else {
                                xRecyclerView.setVisibility(View.GONE);
                                wsj.setVisibility(View.VISIBLE);
                                rowsBeans.clear();
                                trackingListAdapter.notifyDataSetChanged();
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
        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15 + "&status=" + status);

        RxHttpUtils.createApi(TrackingApi.class)
                .getTrackingListData(currentPager, 15, status, sign)
                .compose(Transformer.<TrackingListBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<TrackingListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(TrackingListBean trackingListBean) {
                        String code = trackingListBean.getCode();
                        if (code.equals("200")) {
                            rowsBeans.addAll(trackingListBean.getData().getRows());
                            trackingListAdapter.addMoreData(trackingListBean.getData().getRows());
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250) {
            initData();
        }
    }
}
