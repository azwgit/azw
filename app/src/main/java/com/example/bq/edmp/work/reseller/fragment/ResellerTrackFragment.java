package com.example.bq.edmp.work.reseller.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseFragment;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.allocation.api.AllocationApi;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.order.adapter.GoodslistAdapter;
import com.example.bq.edmp.work.reseller.activity.ApplyResellerAllocationTwoActivity;
import com.example.bq.edmp.work.reseller.activity.ResellerApplyActivity;
import com.example.bq.edmp.work.reseller.activity.ResellerTrackDetailsActivity;
import com.example.bq.edmp.work.reseller.adapter.ResellerApplyAdapter;
import com.example.bq.edmp.work.reseller.api.ResellerApi;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 转商跟踪
 */
@SuppressLint("ValidFragment")
public class ResellerTrackFragment extends BaseFragment {

    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsju)
    TextView wsj;


    private LoadingDialog loadingDialog;
    private ResellerApplyAdapter resellerApplyAdapter;
    private int currentPager = 1;
    private ArrayList<BaseAllocationBeam.DataBean.RowsBean> rowsBeans;
    private String mStatus = "10";
    private String mCode = "";

    @SuppressLint("ValidFragment")
    public ResellerTrackFragment(String status) {
        mStatus = status;
        // Required empty public constructor
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_reseller_track;
    }

    @Override
    protected void initView() {

        loadingDialog = new LoadingDialog(getActivity());

        rowsBeans = new ArrayList<>();
        resellerApplyAdapter = new ResellerApplyAdapter(rowsBeans);
        xRecyclerView.setAdapter(resellerApplyAdapter);
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

        resellerApplyAdapter.setOnItemLeftClckListener(new ResellerApplyAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(BaseAllocationBeam.DataBean.RowsBean rowsBean, int mPosition) {
                Intent intent = new Intent(getActivity(), ResellerTrackDetailsActivity.class);
                intent.putExtra("id",rowsBean.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        gainData();
    }

    //获取列表数据
    private void gainData() {

        currentPager = 1;

        String sign = MD5Util.encode("code=" + mCode + "&page=" + currentPager + "&pagerow=" + 15 + "&status=" + mStatus);

        RxHttpUtils.createApi(ResellerApi.class)
                .getTrackList(mCode, currentPager, 15, mStatus, sign)
                .compose(Transformer.<BaseAllocationBeam>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseAllocationBeam>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseAllocationBeam baseAllocationBeam) {
                        String code = baseAllocationBeam.getCode();
                        if (code.equals("200")) {
                            List<BaseAllocationBeam.DataBean.RowsBean> rows = baseAllocationBeam.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                wsj.setVisibility(ViewGroup.GONE);
                                xRecyclerView.setVisibility(ViewGroup.VISIBLE);
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                resellerApplyAdapter.notifyDataSetChanged();
                            } else {
                                wsj.setVisibility(ViewGroup.VISIBLE);
                                xRecyclerView.setVisibility(ViewGroup.GONE);
                                rowsBeans.clear();
                                resellerApplyAdapter.notifyDataSetChanged();
                                ToastUtil.setToast("暂无数据");
                            }
                        } else {
                            wsj.setVisibility(ViewGroup.VISIBLE);
                            xRecyclerView.setVisibility(ViewGroup.GONE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    //获取列表更多数据
    private void initData2() {
        String sign = MD5Util.encode("code=" + mCode + "&page=" + currentPager + "&pagerow=" + 15 + "&status=" + mStatus);

        RxHttpUtils.createApi(ResellerApi.class)
                .getTrackList(mCode, currentPager, 15, mStatus, sign)
                .compose(Transformer.<BaseAllocationBeam>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseAllocationBeam>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseAllocationBeam baseAllocationBeam) {
                        String code = baseAllocationBeam.getCode();
                        if (code.equals("200")) {
                            List<BaseAllocationBeam.DataBean.RowsBean> rows = baseAllocationBeam.getData().getRows();
                            rowsBeans.addAll(rows);
                            resellerApplyAdapter.addMoreData(rows);
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