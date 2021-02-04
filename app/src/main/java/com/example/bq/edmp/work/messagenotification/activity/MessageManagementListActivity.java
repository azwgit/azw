package com.example.bq.edmp.work.messagenotification.activity;

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
import com.example.bq.edmp.word.activity.AuditActivity;
import com.example.bq.edmp.work.goodsgrainmanagement.activity.AddGoodsSalesActivity;
import com.example.bq.edmp.work.goodsgrainmanagement.activity.EditGoodsSalesActivity;
import com.example.bq.edmp.work.messagenotification.adapter.MessageManagmentListAdapter;
import com.example.bq.edmp.work.messagenotification.api.MessageNotificationApi;
import com.example.bq.edmp.work.messagenotification.bean.MessageManagementListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 消息通知列表
 * */
public class MessageManagementListActivity extends BaseTitleActivity {
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsj)
    TextView wsj;
    private int currentPager = 1;
    private LoadingDialog loading_dialog;
    private MessageManagmentListAdapter goodsSalesManagmentListAdapter;
    private ArrayList<MessageManagementListBean.DataBean.RowsBean> rowsBeans;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_management_list;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(MessageManagementListActivity.this);
        loading_dialog = new LoadingDialog(this);
        txtTabTitle.setText("消息通知");
        rowsBeans = new ArrayList<>();
        goodsSalesManagmentListAdapter = new MessageManagmentListAdapter(rowsBeans);
        xRecyclerView.setAdapter(goodsSalesManagmentListAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(MessageManagementListActivity.this));
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
        goodsSalesManagmentListAdapter.setOnItemClickListener(new MessageManagmentListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, MessageManagementListBean.DataBean.RowsBean rowsBean) {
                //1系统  2 审批 其他待定
                if (rowsBean.getTypes() == 1) {
                    MessageNotificationDetailsActivity.newIntent(getApplicationContext(), rowsBean.getId() + "");
                } else if (rowsBean.getTypes() == 2) {
                    startActivity(new Intent(getApplicationContext(), AuditActivity.class));
                    finish();
                }
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
        RxHttpUtils.createApi(MessageNotificationApi.class)
                .getMessagetList(currentPager, 15, sign)
                .compose(Transformer.<MessageManagementListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<MessageManagementListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(MessageManagementListBean orderTJBean) {
                        if (orderTJBean.getCode() == 200) {
                            List<MessageManagementListBean.DataBean.RowsBean> rows = orderTJBean.getData().getRows();
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
        RxHttpUtils.createApi(MessageNotificationApi.class)
                .getMessagetList(currentPager, 15, sign)
                .compose(Transformer.<MessageManagementListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<MessageManagementListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(MessageManagementListBean orderTJBean) {
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
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(MessageManagementListActivity.this);
    }

}
