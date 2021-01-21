package com.example.bq.edmp.work.modelfield.fragment;

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
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseFragment;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.modelfield.activity.DemonstrationDetailsActivity;
import com.example.bq.edmp.work.modelfield.activity.DemonstrationListActivity;
import com.example.bq.edmp.work.modelfield.adapter.DemonstrationListAdapter;
import com.example.bq.edmp.work.modelfield.api.DemonstrationApi;
import com.example.bq.edmp.work.modelfield.bean.DemonstrationListBean;
import com.example.bq.edmp.work.order.adapter.GoodslistAdapter;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.GoodsBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.http.Field;

@SuppressLint("ValidFragment")
public class DemonstrationListFragment extends BaseFragment {

    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;

    private int currentPager = 1;
    private String mID = "0";//作物id
    private String mYears = "";//年度
    private String regionId = "";//区域id
    private String varietyId = "";//品种id
    private LoadingDialog loadingDialog;
    private ArrayList<DemonstrationListBean.DataBean.RowsBean> rowsBeans;
    private DemonstrationListAdapter demonstrationListAdapter;

    @SuppressLint("ValidFragment")
    public DemonstrationListFragment(String s) {
        // Required empty public constructor
        mID = s;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_demonstration_list;
    }

    @Override
    protected void initView() {
        loadingDialog = new LoadingDialog(getActivity());

        rowsBeans = new ArrayList<>();
        demonstrationListAdapter = new DemonstrationListAdapter(rowsBeans);
        xRecyclerView.setAdapter(demonstrationListAdapter);
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
        demonstrationListAdapter.setOnItemClickListener(new DemonstrationListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, DemonstrationListBean.DataBean.RowsBean rowsBean) {
                Intent intent = new Intent(getActivity(), DemonstrationDetailsActivity.class);
                intent.putExtra("id", rowsBean.getId());
                startActivity(intent);
            }
        });
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        currentPager = 1;

        String sign = MD5Util.encode("cropId=" + mID + "&page=" + currentPager + "&pagerow=" + 15 + "&regionId=" + regionId + "&varietyId=" + varietyId + "&years=" + mYears);

        RxHttpUtils.createApi(DemonstrationApi.class)
                .getListData(mID, currentPager, 15,regionId,varietyId, mYears, sign)
                .compose(Transformer.<DemonstrationListBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<DemonstrationListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DemonstrationListBean demonstrationListBean) {
                        String code = demonstrationListBean.getCode();
                        if (code.equals("200")) {
                            List<DemonstrationListBean.DataBean.RowsBean> data = demonstrationListBean.getData().getRows();
                            if (data != null && data.size() != 0) {
                                xRecyclerView.setVisibility(View.VISIBLE);
                                wsj.setVisibility(View.GONE);
                                rowsBeans.clear();
                                rowsBeans.addAll(data);
                                demonstrationListAdapter.notifyDataSetChanged();
                            } else {
                                xRecyclerView.setVisibility(View.GONE);
                                wsj.setVisibility(View.VISIBLE);
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
        String sign = MD5Util.encode("cropId=" + mID + "&page=" + currentPager + "&pagerow=" + 15 + "&regionId=" + regionId + "&varietyId=" + varietyId + "&years=" + mYears);


        RxHttpUtils.createApi(DemonstrationApi.class)
                .getListData(mID, currentPager, 15, mYears,regionId,varietyId, sign)
                .compose(Transformer.<DemonstrationListBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<DemonstrationListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DemonstrationListBean demonstrationListBean) {
                        String code = demonstrationListBean.getCode();
                        if (code.equals("200")) {
                            List<DemonstrationListBean.DataBean.RowsBean> data = demonstrationListBean.getData().getRows();
                            rowsBeans.addAll(data);
                            demonstrationListAdapter.addMoreData(data);
                        } else {
                            xRecyclerView.setNoMore(true);
                            ToastUtil.setToast("最后数据");
                        }
                    }
                });
    }

    @Override
    protected void otherViewClick(View view) {

    }
}
