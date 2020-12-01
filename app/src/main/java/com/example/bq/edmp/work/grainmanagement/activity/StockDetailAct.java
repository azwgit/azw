package com.example.bq.edmp.work.grainmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.grainmanagement.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.adapter.StockDetailAdp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 库存详情
 */
public class StockDetailAct extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, StockDetailAct.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_name)
    TextView mTvName;//品种名称
    @BindView(R.id.tv_contractor)
    TextView mTvContractor;//当清库存
    @BindView(R.id.tv_warehouse)
    TextView mTvWarehouse;//仓库
    @BindView(R.id.tv_subsidiary_company)
    TextView mTvSubsidiaryCompany;//子公司

    private StockDetailAdp mAdapter;
    private String id="";
    private ILoadingView loading_dialog;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_stock_detail;
    }


    @Override
    protected void initView() {
        txtTabTitle.setText("库存详情");
        id=getIntent().getStringExtra(Constant.ID);
        if("".equals(id)){
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new StockDetailAdp();
        mRecyclerView.setAdapter(mAdapter);
        setData();
        getStockDetail();
    }

    private void setData(){
        List<PayInfoBean> mList = new ArrayList<>();
        for (int i= 0;i< 3 ; i++){
            PayInfoBean bean = new PayInfoBean();
            mList.add(bean);
        }
        mAdapter.setNewData(mList);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void otherViewClick(View view) {

    }

    //获取库存详情
    private void getStockDetail() {
        String sign = MD5Util.encode("id="+id);
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .getStockDetail(id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean loginBean) {
                        ToastUtil.setToast(loginBean.getMsg());
                    }
                });
    }
}
