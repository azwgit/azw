package com.example.bq.edmp.work.reseller.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.reseller.adapter.ResellerApplyAdapter;
import com.example.bq.edmp.work.reseller.api.ResellerApi;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 历史转商
 * */
public class ResellerHistoryActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
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


    @Override
    protected int getLayoutId() {
        return R.layout.activity_reseller_history;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(ResellerHistoryActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("历史转商");

        rowsBeans = new ArrayList<>();
        resellerApplyAdapter = new ResellerApplyAdapter(rowsBeans);
        xRecyclerView.setAdapter(resellerApplyAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(ResellerHistoryActivity.this));
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
                Intent intent = new Intent(ResellerHistoryActivity.this, ResellerHistoryDetailsActivity.class);
                intent.putExtra("id",rowsBean.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        gainData();
    }

    //获取列表数据
    private void gainData() {

        currentPager = 1;

        String sign = MD5Util.encode("code=" + mCode + "&page=" + currentPager + "&pagerow=" + 15 + "&types=" + 3);

        RxHttpUtils.createApi(ResellerApi.class)
                .getAllocationCompleteData(mCode, currentPager, 15, 3 + "", sign)
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
        String sign = MD5Util.encode("code=" + mCode + "&page=" + currentPager + "&pagerow=" + 15 + "&types=" + 3);

        RxHttpUtils.createApi(ResellerApi.class)
                .getAllocationCompleteData(mCode, currentPager, 15, 3 + "", sign)
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
        return_img.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(ResellerHistoryActivity.this);
    }
}