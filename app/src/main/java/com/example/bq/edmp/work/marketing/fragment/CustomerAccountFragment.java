package com.example.bq.edmp.work.marketing.fragment;


import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.PayInfoDetailAct;
import com.example.bq.edmp.activity.apply.activity.EditPayInfoDetailAct;
import com.example.bq.edmp.activity.apply.travel.activity.EditTravelDetailAct;
import com.example.bq.edmp.activity.apply.travel.activity.TravelDetailAct;
import com.example.bq.edmp.base.BaseFragment;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.adapter.SubmitListAdapter;
import com.example.bq.edmp.word.api.WordListApi;
import com.example.bq.edmp.word.bean.SubmitListBean;
import com.example.bq.edmp.work.marketing.api.CustomerManagementApi;
import com.example.bq.edmp.work.marketing.activity.CustomerAccountActivity;
import com.example.bq.edmp.work.marketing.adapter.AccountDetailsAdp;
import com.example.bq.edmp.work.marketing.bean.CustomerAccountDetails;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class CustomerAccountFragment extends BaseFragment {
    @BindView(R.id.xr)
    XRecyclerView xr;
    @BindView(R.id.wsj)
    TextView wsj;
    private int mInteger = 0;
    private String mId="";
    private int currentPager = 1;
    private AccountDetailsAdp accountDetailsAdp;
    private ArrayList<CustomerAccountDetails.DataBean.AccountRecordsBean> rowsBeans;

    @SuppressLint("ValidFragment")
    public CustomerAccountFragment(Integer integer,String id) {
        // Required empty public constructor
        mInteger = integer;
        mId=id;
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_customer_account;
    }

    @Override
    protected void initView() {
        rowsBeans = new ArrayList<>();
        xr.setPullRefreshEnabled(false);
        xr.setLoadingMoreEnabled(false);
        xr.setLayoutManager(new LinearLayoutManager(getActivity()));
//        xr.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                currentPager = 1;
//                initData();
//                xr.refreshComplete();
//            }
//
//            @Override
//            public void onLoadMore() {
//                ++currentPager;
//                initData2();
//                xr.loadMoreComplete();
//            }
//        });

    }

    @Override
    protected void initData() {
        currentPager = 1;
        final String sign = MD5Util.encode(
                "customerId=" + mId + "&types=" + mInteger);


        RxHttpUtils.createApi(CustomerManagementApi.class)
                .getCustomerAccountDetails(mId, mInteger, sign)
                .compose(Transformer.<CustomerAccountDetails>switchSchedulers())
                .subscribe(new CommonObserver<CustomerAccountDetails>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CustomerAccountDetails submitListBean) {
                        final List<CustomerAccountDetails.DataBean.AccountRecordsBean> rows = submitListBean.getData().getAccountRecords();
                        if (rows != null && rows.size() != 0) {
                            xr.setVisibility(View.VISIBLE);
                            wsj.setVisibility(View.GONE);
                            rowsBeans.clear();
                            rowsBeans.addAll(rows);
                            accountDetailsAdp = new AccountDetailsAdp(rowsBeans);
                            xr.setAdapter(accountDetailsAdp);
                            accountDetailsAdp.setOnItemClickListener(new AccountDetailsAdp.OnItemClickListener() {
                                @Override
                                public void onItemClick(int pos, CustomerAccountDetails.DataBean.AccountRecordsBean rowsBean) {
                                }
                            });

                        } else {
                            xr.setVisibility(View.GONE);
                            wsj.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }

    private void initData2() {

        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 10 + "&status=" + mInteger);

        RxHttpUtils.createApi(WordListApi.class)
                .getSubmitData(currentPager, 10, mInteger, sign)
                .compose(Transformer.<SubmitListBean>switchSchedulers())
                .subscribe(new CommonObserver<SubmitListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SubmitListBean submitListBean) {
                        List<SubmitListBean.RowsBean> rows = submitListBean.getRows();
                        if (rows != null && rows.size() != 0) {
//                            accountDetailsAdp.addMoreData(rows);
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
    protected void otherViewClick(View view) {

    }

}
