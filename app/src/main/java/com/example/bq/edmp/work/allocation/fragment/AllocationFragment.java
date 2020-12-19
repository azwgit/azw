package com.example.bq.edmp.work.allocation.fragment;

import android.annotation.SuppressLint;
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
import com.example.bq.edmp.work.allocation.AllocationApplyActivity;
import com.example.bq.edmp.work.allocation.adapter.AllocationApplyListAdapter;
import com.example.bq.edmp.work.allocation.adapter.AllocationInListAdapter;
import com.example.bq.edmp.work.allocation.api.AllocationApi;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.inventorytransfer.activity.FinishedProductAllocationDetailsActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.http.Field;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class AllocationFragment extends BaseFragment {


    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsj)
    TextView wsj;

    private ArrayList<BaseAllocationBeam.DataBean.RowsBean> rowsBeans;

    private int currentPager = 1;
    private LoadingDialog loading_dialog;
    private AllocationInListAdapter allocationInListAdapter;
    private String code = "";//
    private String inOrgId = "";//调入分公司id
    private String outOrgId = "";//调出分公司id
    private int status = 0;//状态：0全部 4出库中 5在途	number	默认0

    @SuppressLint("ValidFragment")
    public AllocationFragment(Integer integer) {
        // Required empty public constructor
        status = integer;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_allocation;
    }

    @Override
    protected void initView() {
        loading_dialog = new LoadingDialog(getActivity());

        rowsBeans = new ArrayList<>();
        allocationInListAdapter = new AllocationInListAdapter(rowsBeans);
        xRecyclerView.setAdapter(allocationInListAdapter);
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
        allocationInListAdapter.setOnItemClickListener(new AllocationInListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, BaseAllocationBeam.DataBean.RowsBean rowsBean) {
                FinishedProductAllocationDetailsActivity.newIntent(getActivity(),rowsBean.getId());
            }
        });
    }

    @Override
    protected void initData() {
        gainData();
    }

    //获取调拨中数据
    private void gainData() {
        currentPager = 1;

        //状态：0全部 4出库中 5在途	number	默认0
        String sign = MD5Util.encode("code=" + code + "&inOrgId=" + inOrgId + "&outOrgId=" + outOrgId
                + "&page=" + currentPager + "&pagerow=" + 15 + "&status=" + status);

        RxHttpUtils.createApi(AllocationApi.class)
                .getAllocationInData(code, inOrgId, outOrgId, currentPager, 15, status, sign)
                .compose(Transformer.<BaseAllocationBeam>switchSchedulers(loading_dialog))
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
                                wsj.setVisibility(View.GONE);
                                xRecyclerView.setVisibility(View.VISIBLE);
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                allocationInListAdapter.notifyDataSetChanged();
                            } else {
                                wsj.setVisibility(View.VISIBLE);
                                xRecyclerView.setVisibility(View.GONE);
                                rowsBeans.clear();
                                allocationInListAdapter.notifyDataSetChanged();
                                ToastUtil.setToast("暂无数据");
                            }
                        } else {
                            wsj.setVisibility(View.VISIBLE);
                            xRecyclerView.setVisibility(View.GONE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    //获取调拨中更多数据
    private void initData2() {
        //状态：0全部 4出库中 5在途	number	默认0
        String sign = MD5Util.encode("code=" + code + "&inOrgId=" + inOrgId + "&outOrgId=" + outOrgId
                + "&page=" + currentPager + "&pagerow=" + 15 + "&status=" + status);

        RxHttpUtils.createApi(AllocationApi.class)
                .getAllocationInData(code, inOrgId, outOrgId, currentPager, 15, status, sign)
                .compose(Transformer.<BaseAllocationBeam>switchSchedulers(loading_dialog))
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
                            allocationInListAdapter.addMoreData(rows);
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
