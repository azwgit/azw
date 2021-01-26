package com.example.bq.edmp.work.goodsgrainmanagement.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.goodsgrainmanagement.activity.GoodsSalesConfirmDetailsActivity;
import com.example.bq.edmp.work.goodsgrainmanagement.activity.GoodsSalesTrackingDetailsActivity;
import com.example.bq.edmp.work.goodsgrainmanagement.adapter.GoodsSalesConfirmListAdapter;
import com.example.bq.edmp.work.goodsgrainmanagement.api.GoodsSalesApi;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.GoodsSalesManagementListBean;
import com.example.bq.edmp.work.returnsmanagement.eventbus.CloseActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class GoodsSalesConfirmListFragment extends BaseFragment {


    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsj)
    TextView wsj;

    private ArrayList<GoodsSalesManagementListBean.DataBean.RowsBean> rowsBeans;

    private int currentPager = 1;
    private LoadingDialog loading_dialog;
    private GoodsSalesConfirmListAdapter goodsSalesConfirmListAdapter;
    private int status = 0;  //2待审批 3审批拒绝 4待出库 5待完成

    @SuppressLint("ValidFragment")
    public GoodsSalesConfirmListFragment(Integer integer) {
        // Required empty public constructor
        status = integer;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void initView() {
        loading_dialog = new LoadingDialog(getActivity());
        rowsBeans = new ArrayList<>();
        goodsSalesConfirmListAdapter = new GoodsSalesConfirmListAdapter(rowsBeans);
        xRecyclerView.setAdapter(goodsSalesConfirmListAdapter);
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
        goodsSalesConfirmListAdapter.setOnItemClickListener(new GoodsSalesConfirmListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, GoodsSalesManagementListBean.DataBean.RowsBean rowsBean) {
                GoodsSalesConfirmDetailsActivity.newIntent(getActivity(), rowsBean.getId() + "");
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        gainData();
    }

    //获取调拨中数据
    private void gainData() {
        currentPager = 1;
        String sign = MD5Util.encode("orgId=" + "&page=" + currentPager + "&pagerow=" + 15 + "&status=" + status + "&varietyId=");
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .getGoodsSalesConfirmtList("", currentPager, 15, status, "", sign)
                .compose(Transformer.<GoodsSalesManagementListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<GoodsSalesManagementListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(GoodsSalesManagementListBean orderTJBean) {
                        if (orderTJBean.getCode() == 200) {
                            List<GoodsSalesManagementListBean.DataBean.RowsBean> rows = orderTJBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                if (xRecyclerView != null) {
                                    xRecyclerView.setVisibility(View.VISIBLE);
                                    wsj.setVisibility(View.GONE);
                                    rowsBeans.clear();
                                    rowsBeans.addAll(rows);
                                    goodsSalesConfirmListAdapter.notifyDataSetChanged();
                                }
                            } else {
                                if (xRecyclerView != null) {
                                    xRecyclerView.setVisibility(View.GONE);
                                    wsj.setVisibility(View.VISIBLE);
                                    rowsBeans.clear();
                                    goodsSalesConfirmListAdapter.notifyDataSetChanged();
                                }

                            }
                        } else {
                            if (xRecyclerView != null) {
                                xRecyclerView.setVisibility(View.GONE);
                                wsj.setVisibility(View.VISIBLE);
                            }
//                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    //获取调拨中更多数据
    private void initData2() {
        String sign = MD5Util.encode("orgId=" + "&page=" + currentPager + "&pagerow=" + 15 + "&status=" + status + "&varietyId=");
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .getGoodsSalesConfirmtList("", currentPager, 15, status, "", sign)
                .compose(Transformer.<GoodsSalesManagementListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<GoodsSalesManagementListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(GoodsSalesManagementListBean orderTJBean) {
                        if (orderTJBean.getCode() == 200) {
                            rowsBeans.addAll(orderTJBean.getData().getRows());
                            goodsSalesConfirmListAdapter.addMoreData(orderTJBean.getData().getRows());
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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getColoseActivity(CloseActivity event) {
        gainData();
    }


    public void setValue(String value) {
        ToastUtil.show(getActivity(), value);
    }
}
