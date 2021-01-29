package com.example.bq.edmp.work.grainsale.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApprovalAdp;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.JSONTool;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.grainsale.adapter.SaleHistoryDetailsAdapter;
import com.example.bq.edmp.work.grainsale.api.SaleApi;
import com.example.bq.edmp.work.grainsale.bean.SaleHistoryBean;
import com.example.bq.edmp.work.grainsale.bean.SaleHistoryDetailsBean;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 销售历史详情
 * */
public class SaleHistoryDetailsActivity extends BaseTitleActivity {

    @BindView(R.id.approval_recyclerview)
    RecyclerView mApprovalRecyclerView;//审批状态适配器
    @BindView(R.id.tijiao_tiem)
    TextView tijiao_tiem;
    @BindView(R.id.shenpi_tiem)
    TextView shenpi_tiem;
    @BindView(R.id.chuku_tiem)
    TextView chuku_tiem;
    @BindView(R.id.wancheng_tiem)
    TextView wancheng_tiem;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_company)
    TextView tv_company;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.compay_name_tv)
    TextView compay_name_tv;
    @BindView(R.id.user_name_tv)
    TextView user_name_tv;
    @BindView(R.id.phone_tv)
    TextView phone_tv;
    @BindView(R.id.shou_address_tv)
    TextView shou_address_tv;
    @BindView(R.id.zi_company_name_tv)
    TextView zi_company_name_tv;
    @BindView(R.id.cangku_tv)
    TextView cangku_tv;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.xiao_jine_tv)
    TextView xiao_jine_tv;
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;


    private LoadingDialog loadingDialog;
    private ApprovalAdp mApprovalAdapter;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sale_history_details;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(SaleHistoryDetailsActivity.this);
        txtTabTitle.setText("销售详情");
        loadingDialog = new LoadingDialog(this);

        id = getIntent().getStringExtra("id");

        List<PayInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PayInfoBean bean = new PayInfoBean();
            bean.setId(i + "");
            bean.setDesc("啊啊啊啊");
            list.add(bean);
        }
        mApprovalRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mApprovalAdapter = new ApprovalAdp(list);
        mApprovalRecyclerView.setAdapter(mApprovalAdapter);
    }

    @Override
    protected void initData() {
        String sign = MD5Util.encode("id=" + id);

        RxHttpUtils.createApi(SaleApi.class)
                .getHistoryDetails(id, sign)
                .compose(Transformer.<SaleHistoryDetailsBean>switchSchedulers())
                .subscribe(new NewCommonObserver<SaleHistoryDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SaleHistoryDetailsBean saleHistoryDetailsBean) {
                        if (saleHistoryDetailsBean.getCode().equals("200")) {
                            SaleHistoryDetailsBean.DataBean data = saleHistoryDetailsBean.getData();
                            tv_title.setText("订单号：" + data.getCode());
                            tijiao_tiem.setText(data.getSubmitTime());
                            shenpi_tiem.setText(data.getApprovedTime());
                            chuku_tiem.setText(data.getSubstockTime());
                            wancheng_tiem.setText(data.getFinishedTime());
                            tv_company.setText(data.getOrgName());
                            tv_name.setText(data.getSubmitOperator());
                            tv_time.setText("提交时间：" + data.getSubmitTime());
                            compay_name_tv.setText(data.getOrgName());
                            user_name_tv.setText(data.getContacts());
                            phone_tv.setText(data.getMobTel());
                            shou_address_tv.setText(data.getAddress());
                            zi_company_name_tv.setText(data.getOrgName());
                            cangku_tv.setText(data.getWarehouseName());
                            xiao_jine_tv.setText("¥" + data.getAmount());

                            List<SaleHistoryDetailsBean.DataBean.CgOrderItemsBean> cgOrderItem = data.getCgOrderItems();
                            if (cgOrderItem != null && cgOrderItem.size() != 0) {
                                wsj.setVisibility(View.GONE);
                                xRecyclerView.setVisibility(View.VISIBLE);

                                SaleHistoryDetailsAdapter saleHistoryDetailsAdapter = new SaleHistoryDetailsAdapter(cgOrderItem);
                                xRecyclerView.setLayoutManager(new LinearLayoutManager(SaleHistoryDetailsActivity.this));
                                xRecyclerView.setPullRefreshEnabled(false);
                                xRecyclerView.setAdapter(saleHistoryDetailsAdapter);
                                saleHistoryDetailsAdapter.notifyDataSetChanged();

                            } else {
                                wsj.setVisibility(View.VISIBLE);
                                xRecyclerView.setVisibility(View.GONE);
                            }

                        } else {
                            ToastUtil.setToast("加载失败,请重新申请");
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
            case R.id.txt_back:
                fund();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(SaleHistoryDetailsActivity.this);
    }
}
