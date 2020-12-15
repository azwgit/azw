package com.example.bq.edmp.word.inventory.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseFragment;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.inventory.adapter.InventoryListAdapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.InventoryBean;
import com.example.bq.edmp.work.grainmanagement.activity.StockDetailAct;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class InventoryFragment extends BaseFragment {

    @BindView(R.id.xr)
    XRecyclerView xr;
    @BindView(R.id.wsj)
    TextView wsj;

    private String mId = "";//作物id
    private String orgIds = "";//公司id
    private String varietyId = "";//品种id
    private String warehouseId = "";//仓库id
    private int currentPager = 1;
    private ArrayList<InventoryBean.DataBean.RowsBean> rowsBeans;
    private InventoryListAdapter inventoryListAdapter;


    @SuppressLint("ValidFragment")
    public InventoryFragment(String id) {
        // Required empty public constructor
        mId = id;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_inventory;
    }

    @Override
    protected void initView() {
        rowsBeans = new ArrayList<>();
        inventoryListAdapter = new InventoryListAdapter(rowsBeans);
        xr.setAdapter(inventoryListAdapter);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        inventoryListAdapter.setOnItemClickListener(new InventoryListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, InventoryBean.DataBean.RowsBean rowsBean) {
                StockDetailAct.newIntent(getActivity(), rowsBean.getVarietyId(), rowsBean.getWarehouseId() + "");
            }
        });
    }

    private void initData2() {
        String sign = MD5Util.encode("cropId=" + mId + "&itemId=" + varietyId + "&orgId=" + orgIds +
                "&page=" + currentPager + "&pagerow=" + 15 +
                "&warehouseId=" + warehouseId);

        RxHttpUtils.createApi(InventoryListApi.class)
                .getInventoryData(mId, varietyId, orgIds, currentPager, 15, warehouseId, sign)
                .compose(Transformer.<InventoryBean>switchSchedulers())
                .subscribe(new NewCommonObserver<InventoryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(InventoryBean inventoryBean) {
                        List<InventoryBean.DataBean.RowsBean> rows = inventoryBean.getData().getRows();
                        if (rows != null && rows.size() != 0) {
                            rowsBeans.addAll(rows);
                            inventoryListAdapter.addMoreData(rows);
                        } else {
                            xr.setNoMore(true);
                        }
                    }
                });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        aginDataMethod();
    }

    private void aginDataMethod() {
        currentPager = 1;

        String sign = MD5Util.encode("cropId=" + mId + "&itemId=" + varietyId + "&orgId=" + orgIds +
                "&page=" + currentPager + "&pagerow=" + 15 +
                "&warehouseId=" + warehouseId);

        RxHttpUtils.createApi(InventoryListApi.class)
                .getInventoryData(mId, varietyId, orgIds, currentPager, 15, warehouseId, sign)
                .compose(Transformer.<InventoryBean>switchSchedulers())
                .subscribe(new NewCommonObserver<InventoryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(InventoryBean inventoryBean) {
                        List<InventoryBean.DataBean.RowsBean> rows = inventoryBean.getData().getRows();
                        if (rows != null && rows.size() != 0) {
                            xr.setVisibility(ViewGroup.VISIBLE);
                            wsj.setVisibility(ViewGroup.GONE);
                            rowsBeans.clear();
                            rowsBeans.addAll(rows);
                            inventoryListAdapter.notifyDataSetChanged();
                        } else {
                            xr.setVisibility(ViewGroup.GONE);
                            wsj.setVisibility(ViewGroup.VISIBLE);
                            rowsBeans.clear();
                            inventoryListAdapter.notifyDataSetChanged();
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    @Override
    protected void otherViewClick(View view) {

    }
}
