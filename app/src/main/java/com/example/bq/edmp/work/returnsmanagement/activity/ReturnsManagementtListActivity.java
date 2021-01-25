package com.example.bq.edmp.work.returnsmanagement.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.marketingactivities.activity.AddActivitiesActivity;
import com.example.bq.edmp.work.marketingactivities.activity.EditActivitiesActivity;
import com.example.bq.edmp.work.marketingactivities.adapter.MarketingActivityListAdp;
import com.example.bq.edmp.work.marketingactivities.api.MarketingActivitiesApi;
import com.example.bq.edmp.work.marketingactivities.bean.ActivityManagementListBean;
import com.example.bq.edmp.work.returnsmanagement.adapter.ReturnManagementActivityListAdp;
import com.example.bq.edmp.work.returnsmanagement.api.ReturnGoodsApi;
import com.example.bq.edmp.work.returnsmanagement.bean.ReturnsManagementListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/*
 * 退货管理
 * */
public class ReturnsManagementtListActivity extends BaseTitleActivity {
    public static void newIntent(Context context) {
        Intent intent = new Intent(context, ReturnsManagementtListActivity.class);
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
    private ArrayList<ReturnsManagementListBean.DataBean.RowsBean> rowsBeans;
    private ReturnManagementActivityListAdp returnManagementActivityListAdp;
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.returns_management_list;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("退货管理");
        ProApplication.getinstance().addActivity(this);
        //数据
        rowsBeans = new ArrayList<>();
        returnManagementActivityListAdp = new ReturnManagementActivityListAdp(rowsBeans);
        xr.setAdapter(returnManagementActivityListAdp);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(ReturnsManagementtListActivity.this));
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

        returnManagementActivityListAdp.setOnItemClickListener(new ReturnManagementActivityListAdp.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, ReturnsManagementListBean.DataBean.RowsBean rowsBean) {
                EditApplyForRefundActivity.newIntent(getApplicationContext(), rowsBean.getId() + "");
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
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gainData();
    }

    private void gainData() {
        currentPager = 1;
        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15);
        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .getReturnGoodsManagentList(currentPager, 15, sign)
                .compose(Transformer.<ReturnsManagementListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<ReturnsManagementListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ReturnsManagementListBean machineListBean) {
                        if (machineListBean.getCode() == 200) {
                            wsj.setVisibility(View.GONE);
                            xr.setVisibility(View.VISIBLE);
                            List<ReturnsManagementListBean.DataBean.RowsBean> rows = machineListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                returnManagementActivityListAdp.notifyDataSetChanged();
                            } else {
                                wsj.setVisibility(View.VISIBLE);
                                xr.setVisibility(View.GONE);
                                rowsBeans.clear();
                                returnManagementActivityListAdp.notifyDataSetChanged();
                                ToastUtil.setToast("暂无数据");
                            }
                        } else {
                            wsj.setVisibility(View.VISIBLE);
                            xr.setVisibility(View.GONE);
                            rowsBeans.clear();
                            returnManagementActivityListAdp.notifyDataSetChanged();
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .getReturnGoodsManagentList(currentPager, 15, sign)
                .compose(Transformer.<ReturnsManagementListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<ReturnsManagementListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ReturnsManagementListBean machineListBean) {
                        if (machineListBean.getCode() == 200) {
                            List<ReturnsManagementListBean.DataBean.RowsBean> rows = machineListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.addAll(rows);
                                returnManagementActivityListAdp.addMoreData(rows);
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
                startActivity(new Intent(getApplicationContext(), AddReturnsGoodsActivity.class));
                break;
        }

    }

    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}