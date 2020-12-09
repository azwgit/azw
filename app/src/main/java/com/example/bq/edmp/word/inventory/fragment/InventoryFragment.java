package com.example.bq.edmp.word.inventory.fragment;

import android.annotation.SuppressLint;
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
import com.allen.library.utils.JsonUtil;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.PayInfoDetailAct;
import com.example.bq.edmp.activity.apply.activity.EditPayInfoDetailAct;
import com.example.bq.edmp.activity.apply.travel.activity.EditTravelDetailAct;
import com.example.bq.edmp.activity.apply.travel.activity.TravelDetailAct;
import com.example.bq.edmp.base.BaseFragment;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.adapter.SubmitListAdapter;
import com.example.bq.edmp.word.bean.SubmitListBean;
import com.example.bq.edmp.word.inventory.adapter.InventoryListAdapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.InventoryBean;
import com.example.bq.edmp.work.grainmanagement.activity.StockDetailAct;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.http.Field;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class InventoryFragment extends BaseFragment {

    @BindView(R.id.xr)
    XRecyclerView xr;

    private String mId = "";//作物id
    private String orgIds = "";//公司id
    private String varietyId = "";//品种id
    private String warehouseId = "";//仓库id
    private int currentPager = 1;
    private ArrayList<InventoryBean.RowsBean> rowsBeans;
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
            public void onItemClick(int pos, InventoryBean.RowsBean rowsBean) {
                StockDetailAct.newIntent(getActivity(),rowsBean.getWarehouseId()+"",rowsBean.getItemId());
            }
        });
    }

    private void initData2() {
        String sign = MD5Util.encode("cropId=" + mId + "&orgIds=" + orgIds +
                "&page=" + currentPager + "&pagerow=" + 15 +
                "&varietyId=" + varietyId + "&warehouseId=" + warehouseId);

        RxHttpUtils.createApi(InventoryListApi.class)
                .getInventoryData(mId, orgIds, currentPager, 15, varietyId, warehouseId, sign)
                .compose(Transformer.<InventoryBean>switchSchedulers())
                .subscribe(new NewCommonObserver<InventoryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(InventoryBean inventoryBean) {
                        List<InventoryBean.RowsBean> rows = inventoryBean.getRows();
                        if (rows.size() != 0 && rows != null) {
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
        String sign = MD5Util.encode("cropId=" + mId + "&orgIds=" + orgIds +
                "&page=" + currentPager + "&pagerow=" + 15 +
                "&varietyId=" + varietyId + "&warehouseId=" + warehouseId);

        RxHttpUtils.createApi(InventoryListApi.class)
                .getInventoryData(mId, orgIds, currentPager, 15, varietyId, warehouseId, sign)
                .compose(Transformer.<InventoryBean>switchSchedulers())
                .subscribe(new NewCommonObserver<InventoryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(InventoryBean inventoryBean) {
                        List<InventoryBean.RowsBean> rows = inventoryBean.getRows();
                        if (rows.size() != 0 && rows != null) {
                            rowsBeans.clear();
                            rowsBeans.addAll(rows);
                            inventoryListAdapter.notifyDataSetChanged();
                        } else {

                        }
                    }
                });
    }

    @Override
    protected void otherViewClick(View view) {

    }
}
