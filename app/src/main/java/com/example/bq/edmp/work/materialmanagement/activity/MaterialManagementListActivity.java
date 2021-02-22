package com.example.bq.edmp.work.materialmanagement.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.goodsgrainmanagement.activity.AddGoodsSalesActivity;
import com.example.bq.edmp.work.goodsgrainmanagement.activity.EditGoodsSalesActivity;
import com.example.bq.edmp.work.goodsgrainmanagement.adapter.GoodsSalesManagmentListAdapter;
import com.example.bq.edmp.work.goodsgrainmanagement.api.GoodsSalesApi;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.GoodsSalesManagementListBean;
import com.example.bq.edmp.work.materialmanagement.adapter.MaterialManagmentListAdapter;
import com.example.bq.edmp.work.materialmanagement.api.MaterialManagementApi;
import com.example.bq.edmp.work.materialmanagement.bean.MaterialListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 物料管理
 * */
public class MaterialManagementListActivity extends BaseTitleActivity {
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.tv_add)
    TextView mTvAdd;
    private int currentPager = 1;
    private LoadingDialog loading_dialog;
    private MaterialManagmentListAdapter goodsSalesManagmentListAdapter;
    private ArrayList<MaterialListBean.DataBean.RowsBean> rowsBeans;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_material_management_list;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(MaterialManagementListActivity.this);
        loading_dialog = new LoadingDialog(this);
        txtTabTitle.setText("物料采购申请");
        rowsBeans = new ArrayList<>();
        goodsSalesManagmentListAdapter = new MaterialManagmentListAdapter(rowsBeans);
        xRecyclerView.setAdapter(goodsSalesManagmentListAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(MaterialManagementListActivity.this));
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                getData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                initData2();
                xRecyclerView.loadMoreComplete();
            }
        });
        goodsSalesManagmentListAdapter.setOnItemClickListener(new MaterialManagmentListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, MaterialListBean.DataBean.RowsBean rowsBean) {
                EditMaterialActivity.newIntent(getApplicationContext(), "2", rowsBean.getId() + "");
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    protected void getData() {
        currentPager = 1;
        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15);
        RxHttpUtils.createApi(MaterialManagementApi.class)
                .getMaterialManagementList(currentPager, 15, sign)
                .compose(Transformer.<MaterialListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<MaterialListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(MaterialListBean orderTJBean) {
                        if (orderTJBean.getCode() == 200) {
                            List<MaterialListBean.DataBean.RowsBean> rows = orderTJBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {

                                xRecyclerView.setVisibility(View.VISIBLE);
                                wsj.setVisibility(View.GONE);

                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                goodsSalesManagmentListAdapter.notifyDataSetChanged();
                            } else {
                                xRecyclerView.setVisibility(View.GONE);
                                wsj.setVisibility(View.VISIBLE);
                                rowsBeans.clear();
                                goodsSalesManagmentListAdapter.notifyDataSetChanged();
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
        RxHttpUtils.createApi(MaterialManagementApi.class)
                .getMaterialManagementList(currentPager, 15, sign)
                .compose(Transformer.<MaterialListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<MaterialListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(MaterialListBean orderTJBean) {
                        if (orderTJBean.getCode() == 200) {
                            rowsBeans.addAll(orderTJBean.getData().getRows());
                            goodsSalesManagmentListAdapter.addMoreData(orderTJBean.getData().getRows());
                        } else {
                            xRecyclerView.setNoMore(true);
                            ToastUtil.setToast("最后页");
                        }
                    }

                });
    }

    @Override
    protected void initListener() {
        mTvAdd.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add://新增订单
                startActivity(new Intent(getApplicationContext(), MaterialNextActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(MaterialManagementListActivity.this);
    }

}
