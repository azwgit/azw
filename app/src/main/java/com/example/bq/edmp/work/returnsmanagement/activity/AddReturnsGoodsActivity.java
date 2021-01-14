package com.example.bq.edmp.work.returnsmanagement.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.marketingactivities.adapter.DepartmengListAdp;
import com.example.bq.edmp.work.marketingactivities.api.MarketingActivitiesApi;
import com.example.bq.edmp.work.marketingactivities.bean.DepartmengListBean;
import com.example.bq.edmp.work.returnsmanagement.adapter.CustomerListAdp;
import com.example.bq.edmp.work.returnsmanagement.adapter.PackingListAdp;
import com.example.bq.edmp.work.returnsmanagement.api.ReturnGoodsApi;
import com.example.bq.edmp.work.returnsmanagement.bean.CustomerListBean;
import com.example.bq.edmp.work.returnsmanagement.bean.PackagingListBean;
import com.example.bq.edmp.work.returnsmanagement.eventbus.CloseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class AddReturnsGoodsActivity extends BaseTitleActivity {
    @BindView(R.id.rl_customer)
    RelativeLayout mRlCustomer;//选择客户父布局
    @BindView(R.id.rl_packing)
    RelativeLayout mRlPacking;//品种包装父布局
    @BindView(R.id.btn_select)
    TextView mBtnSelect;//查询发货单
    @BindView(R.id.tv_customer)
    TextView mTvCustomer;//选择客户
    @BindView(R.id.tv_packing)
    TextView mTvPacking;//选择品种
    private PopupWindow mTypePopuWindow;
    private ILoadingView loading_dialog;
    private int customerId = 0;
    private int packingId = 0;
    private CustomerListBean customerListBean;//客户据源
    private PackagingListBean packagingListBean;//品种包装数据源
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_returns_goods;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("新增退单");
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mRlCustomer.setOnClickListener(this);
        mRlPacking.setOnClickListener(this);
        mBtnSelect.setOnClickListener(this);

    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.rl_customer:
                getCustomerList();
                //选择客户父布局
                break;
            case R.id.rl_packing:
                getPackingList();
                //品种包装父布局
                break;
            case R.id.btn_select:
                //查询发货单
                if (customerId == 0 || packingId == 0) {
                    ToastUtil.setToast("请选择客户或品种包装");
                    return;
                }
                DeliverGoodsListActivity.newIntent(getApplicationContext(), customerId + "", packingId + "", mTvCustomer.getText().toString().trim() + "," + mTvPacking.getText().toString().trim());
                break;
        }
    }

    //获取客户列表
    private void getCustomerList() {
        String sign = MD5Util.encode("type=" + 1);
        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .getCustomerTypeList(1+"",sign)
                .compose(Transformer.<CustomerListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<CustomerListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CustomerListBean bean) {
                        if (bean.getCode() == 200) {
                            customerListBean = bean;
                            showCustomerList();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //客户列表PopuWindow
    private void showCustomerList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        CustomerListAdp contractorListAdp = new CustomerListAdp(customerListBean.getData());
        myRecyclerViewOne.setAdapter(contractorListAdp);
        contractorListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                customerId = customerListBean.getData().get(position).getId();
                mTvCustomer.setText(customerListBean.getData().get(position).getName());
                mTypePopuWindow.dismiss();
            }
        });
        mLyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTypePopuWindow.dismiss();
            }
        });
        mTypePopuWindow = new PopupWindow();
        mTypePopuWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mTypePopuWindow.setContentView(contentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        mTypePopuWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        mTypePopuWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        // 实例化一个ColorDrawable颜色为透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        mTypePopuWindow.setBackgroundDrawable(dw);
        // 设置SelectPicPopupWindow弹出窗体可点击
        mTypePopuWindow.setFocusable(true);
//        backgroundAlpha(0.4f);
        mTypePopuWindow.setOutsideTouchable(true);
        mTypePopuWindow.setClippingEnabled(false);
        mTypePopuWindow.showAtLocation(findViewById(R.id.rl_view), Gravity.BOTTOM, 0, 0);
    }

    //获取包装列表
    private void getPackingList() {
        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .getPackagingList()
                .compose(Transformer.<PackagingListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<PackagingListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(PackagingListBean bean) {
                        if (bean.getCode() == 200) {
                            packagingListBean=bean;
                            showPackingList();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //包装列表PopuWindow
    private void showPackingList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        PackingListAdp contractorListAdp = new PackingListAdp(packagingListBean.getData());
        myRecyclerViewOne.setAdapter(contractorListAdp);
        contractorListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                packingId = packagingListBean.getData().get(position).getId();
                mTvPacking.setText(packagingListBean.getData().get(position).getName());
                mTypePopuWindow.dismiss();
            }
        });
        mLyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTypePopuWindow.dismiss();
            }
        });
        mTypePopuWindow = new PopupWindow();
        mTypePopuWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mTypePopuWindow.setContentView(contentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        mTypePopuWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        mTypePopuWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        // 实例化一个ColorDrawable颜色为透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        mTypePopuWindow.setBackgroundDrawable(dw);
        // 设置SelectPicPopupWindow弹出窗体可点击
        mTypePopuWindow.setFocusable(true);
//        backgroundAlpha(0.4f);
        mTypePopuWindow.setOutsideTouchable(true);
        mTypePopuWindow.setClippingEnabled(false);
        mTypePopuWindow.showAtLocation(findViewById(R.id.rl_view), Gravity.BOTTOM, 0, 0);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getColoseActivity(CloseActivity event) {
        finish();
    }
}