package com.example.bq.edmp.work.marketingactivities.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.finished.MachineScanActivity;
import com.example.bq.edmp.work.finished.adapter.DmachineListAdapter;
import com.example.bq.edmp.work.finished.api.MachineApi;
import com.example.bq.edmp.work.finished.bean.MachineListBean;
import com.example.bq.edmp.work.finishedproduct.activity.MachiningTaskDetailsActivity;
import com.example.bq.edmp.work.marketing.api.CustomerManagementApi;
import com.example.bq.edmp.work.marketing.adapter.AccountListAdp;
import com.example.bq.edmp.work.marketing.bean.CustomerAccountListBean;
import com.example.bq.edmp.work.marketingactivities.adapter.MarketingActivityListAdp;
import com.example.bq.edmp.work.marketingactivities.api.MarketingActivitiesApi;
import com.example.bq.edmp.work.marketingactivities.bean.ActivityManagementListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/*
 * 加工任务    待确认
 * */
public class MarketingActivityManagementListActivity extends BaseTitleActivity {
    public static void newIntent(Context context) {
        Intent intent = new Intent(context, MarketingActivityManagementListActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.xr)
    XRecyclerView xr;
    @BindView(R.id.btn_add)
    TextView mBtnAdd;

    private int currentPager = 1;
    private String name = "";
    private ArrayList<ActivityManagementListBean.DataBean.RowsBean> rowsBeans;
    private MarketingActivityListAdp marketingActivityListAdp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_marketing_activity_management_list;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("营销活动管理");
        ProApplication.getinstance().addActivity(this);
        //数据
        rowsBeans = new ArrayList<>();
        marketingActivityListAdp = new MarketingActivityListAdp(rowsBeans);
        xr.setAdapter(marketingActivityListAdp);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(MarketingActivityManagementListActivity.this));
        xr.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                gainData();
                xr.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                initData2();
                xr.loadMoreComplete();
            }
        });

        marketingActivityListAdp.setOnItemClickListener(new MarketingActivityListAdp.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, ActivityManagementListBean.DataBean.RowsBean rowsBean) {
                EditActivitiesActivity.newIntent(getApplicationContext(), rowsBean.getId() + "");
            }
        });

        //搜索
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //此处做逻辑处理
                    hideKeyboard(search_et);
                    name = textView.getText().toString();
                    gainData();
                    return true;
                }
                return false;

            }
        });
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        gainData();
    }

    private void gainData() {
        currentPager = 1;

        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(MarketingActivitiesApi.class)
                .getActivitieNosubmitList(currentPager, 15, sign)
                .compose(Transformer.<ActivityManagementListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<ActivityManagementListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ActivityManagementListBean machineListBean) {
                        if (machineListBean.getCode() == 200) {
                            wsj.setVisibility(View.GONE);
                            xr.setVisibility(View.VISIBLE);
                            List<ActivityManagementListBean.DataBean.RowsBean> rows = machineListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                marketingActivityListAdp.notifyDataSetChanged();
                            } else {
                                wsj.setVisibility(View.VISIBLE);
                                xr.setVisibility(View.GONE);
                                rowsBeans.clear();
                                marketingActivityListAdp.notifyDataSetChanged();
                                ToastUtil.setToast("暂无数据");
                            }
                        } else {
                            wsj.setVisibility(View.VISIBLE);
                            xr.setVisibility(View.GONE);
                            rowsBeans.clear();
                            marketingActivityListAdp.notifyDataSetChanged();
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(MarketingActivitiesApi.class)
                .getActivitieNosubmitList(currentPager, 15, sign)
                .compose(Transformer.<ActivityManagementListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<ActivityManagementListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ActivityManagementListBean machineListBean) {
                        if (machineListBean.getCode() == 200) {
                            List<ActivityManagementListBean.DataBean.RowsBean> rows = machineListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.addAll(rows);
                                marketingActivityListAdp.addMoreData(rows);
                            }
                        } else {
                            xr.setNoMore(true);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    @Override
    protected void initListener() {
        mBtnAdd.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                AddActivitiesActivity.newIntent(getApplicationContext());
                finish();
                break;
        }

    }

    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}