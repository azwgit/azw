package com.example.bq.edmp.work.returnsmanagement.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseFragment;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.allocation.adapter.AllocationInListAdapter;
import com.example.bq.edmp.work.allocation.api.AllocationApi;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.inventorytransfer.activity.FinishedProductAllocationDetailsActivity;
import com.example.bq.edmp.work.returnsmanagement.activity.ReturnsGoodsDetailsActivity;
import com.example.bq.edmp.work.returnsmanagement.adapter.ReturnsGoodsActivityListAdp;
import com.example.bq.edmp.work.returnsmanagement.api.ReturnGoodsApi;
import com.example.bq.edmp.work.returnsmanagement.bean.ReturnTrackingListBean;
import com.example.bq.edmp.work.returnsmanagement.eventbus.CloseActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ReturnGoodsListFragment extends BaseFragment {


    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsj)
    TextView wsj;

    private ArrayList<ReturnTrackingListBean.DataBean.RowsBean> rowsBeans;

    private int currentPager = 1;
    private LoadingDialog loading_dialog;
    private ReturnsGoodsActivityListAdp returnsGoodsActivityListAdp;
    private String code = "";//
    private String inOrgId = "";//调入分公司id
    private String outOrgId = "";//调出分公司id
    private int status = 0;  //0全部 2待审批 3审批拒绝 4退货中

    @SuppressLint("ValidFragment")
    public ReturnGoodsListFragment(Integer integer) {
        // Required empty public constructor
        status = integer;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void initView() {
        loading_dialog = new LoadingDialog(getActivity());
        rowsBeans = new ArrayList<>();
        returnsGoodsActivityListAdp = new ReturnsGoodsActivityListAdp(rowsBeans);
        xRecyclerView.setAdapter(returnsGoodsActivityListAdp);
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
        returnsGoodsActivityListAdp.setOnItemClickListener(new ReturnsGoodsActivityListAdp.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, ReturnTrackingListBean.DataBean.RowsBean rowsBean) {
                ReturnsGoodsDetailsActivity.newIntent(getActivity(), rowsBean.getId() + "");
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        gainData();
    }

    //获取调拨中数据
    private void gainData() {
        currentPager = 1;

        //0全部 2待审批 3审批拒绝 4退货中
        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15 + "&status=" + status);

        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .getReturnTrackingList(currentPager, 15, status, sign)
                .compose(Transformer.<ReturnTrackingListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<ReturnTrackingListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ReturnTrackingListBean baseAllocationBeam) {
                        if (baseAllocationBeam.getCode() == 200) {
                            List<ReturnTrackingListBean.DataBean.RowsBean> rows = baseAllocationBeam.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                wsj.setVisibility(View.GONE);
                                xRecyclerView.setVisibility(View.VISIBLE);
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                returnsGoodsActivityListAdp.notifyDataSetChanged();
                            } else {
                                wsj.setVisibility(View.VISIBLE);
                                xRecyclerView.setVisibility(View.GONE);
                                rowsBeans.clear();
                                returnsGoodsActivityListAdp.notifyDataSetChanged();
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
        //0全部 2待审批 3审批拒绝 4退货中
        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15 + "&status=" + status);

        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .getReturnTrackingList(currentPager, 15, status, sign)
                .compose(Transformer.<ReturnTrackingListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<ReturnTrackingListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ReturnTrackingListBean baseAllocationBeam) {
                        if (baseAllocationBeam.getCode() == 200) {
                            List<ReturnTrackingListBean.DataBean.RowsBean> rows = baseAllocationBeam.getData().getRows();
                            rowsBeans.addAll(rows);
                            returnsGoodsActivityListAdp.addMoreData(rows);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getColoseActivity(CloseActivity event) {
        //删除后重新刷新页面
        if(status==3){
            gainData();
        }
    }
}
