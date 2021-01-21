package com.example.bq.edmp.work.reseller.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApprovalAdp;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;
import com.example.bq.edmp.work.reseller.adapter.ResellerDetailsAdapter;
import com.example.bq.edmp.work.reseller.api.ResellerApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 转商历史详情
 * */
public class ResellerHistoryDetailsActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.ly_bottom)
    LinearLayout ly_bottom;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_out_order_tiem)
    TextView tv_out_order_tiem;
    @BindView(R.id.tv_int_order_no)
    TextView tv_int_order_no;
    @BindView(R.id.tv_int_order_tiem)
    TextView tv_int_order_tiem;
    @BindView(R.id.tv_stuats)
    TextView tv_stuats;
    @BindView(R.id.tv_company)
    TextView tv_company;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.shenpi_number)
    TextView shenpi_number;
    @BindView(R.id.tv_out_order_no)
    TextView tv_out_order_no;
    @BindView(R.id.shenpi_stats)
    TextView shenpi_stats;
    @BindView(R.id.tv_transfer_out)
    TextView tv_transfer_out;
    @BindView(R.id.tv_transfer_in)
    TextView tv_transfer_in;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.approval_recyclerview)
    RecyclerView approval_recyclerview;
    @BindView(R.id.return_img)
    ImageView return_img;

    private LoadingDialog loadingDialog;
    private String id;
    private ResellerDetailsAdapter resellerDetailsAdapter;
    private ApprovalAdp mApprovalAdapter;
    private ArrayList<EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean> stockAllotItemsBeans;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(ResellerHistoryDetailsActivity.this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_reseller_history_details;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(ResellerHistoryDetailsActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("转商调拨详情");

        id = getIntent().getStringExtra("id");


        stockAllotItemsBeans = new ArrayList<>();
        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        resellerDetailsAdapter = new ResellerDetailsAdapter(stockAllotItemsBeans);
        recycler_view.setAdapter(resellerDetailsAdapter);

        List<PayInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PayInfoBean bean = new PayInfoBean();
            bean.setId(i + "");
            bean.setDesc("啊啊啊啊");
            list.add(bean);
        }
        approval_recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mApprovalAdapter = new ApprovalAdp(list);
        approval_recyclerview.setAdapter(mApprovalAdapter);

    }

    @Override
    protected void initData() {
        getGoodsDetails();
    }

    //获取商品详情
    private void getGoodsDetails() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(ResellerApi.class)
                .getProudctAllocationDetails(id, sign)
                .compose(Transformer.<EditFinishedProductAllocationBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<EditFinishedProductAllocationBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(EditFinishedProductAllocationBean bean) {
                        if (bean.getCode() == 200) {
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            finish();
                        }
                    }
                });
    }

    private void setData(EditFinishedProductAllocationBean.DataBean bean) {
        tv_title.setText("成品调拨：" + bean.getCode());
        tv_company.setText(bean.getOrgName());

        shenpi_stats.setText("已通过");
        tv_stuats.setText("已完成");
        tv_name.setText(bean.getAddedOperator());
        tv_time.setText(bean.getAddedTime());
        tv_transfer_out.setText(bean.getOutOrgName() + "  " + bean.getOutWarehouseName());
        tv_transfer_in.setText(bean.getInOrgName() + "  " + bean.getInWarehouseName());
        tv_content.setText(bean.getReason());
        tv_out_order_no.setText("出库单："+bean.getSubCode() + "");
        tv_out_order_tiem.setText(bean.getOutContirmTime());
        tv_int_order_no.setText("入库单："+bean.getAddCode() + "");
        tv_int_order_tiem.setText(bean.getInContirmTime());


        stockAllotItemsBeans.clear();
        stockAllotItemsBeans.addAll(bean.getStockAllotItems());
        resellerDetailsAdapter.notifyDataSetChanged();


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
}