package com.example.bq.edmp.work.reseller.activity;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApprovalAdp;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.inventorytransfer.adapter.AllListPackageAdp;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.inventorytransfer.bean.AllpackageListBean;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;
import com.example.bq.edmp.work.reseller.adapter.PinLiangListPackageAdp;
import com.example.bq.edmp.work.reseller.adapter.ResellerDetailsAdapter;
import com.example.bq.edmp.work.reseller.api.ResellerApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 转商处理详情
 * */
public class ResellerHandleDetailsActivity extends BaseActivity {


    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.btn_ok)
    TextView btn_ok;
    @BindView(R.id.tv_title)
    TextView tv_title;
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


    PopupWindow mTypePopuWindow;
    String stuats = "";
    private LoadingDialog loadingDialog;
    private String id;
    private ResellerDetailsAdapter resellerDetailsAdapter;
    private ApprovalAdp mApprovalAdapter;
    private ArrayList<EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean> stockAllotItemsBeans;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_reseller_handle_details;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(ResellerHandleDetailsActivity.this);
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
        RxHttpUtils.createApi(AllocationApi.class)
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
        //3待出库 4待入库
        switch (bean.getStatus()) {
            case 3:
                stuats = "待出库";
                shenpi_stats.setText("已通过");
                btn_ok.setText("确认出库");
                break;
            case 4:
                stuats = "待入库";
                shenpi_stats.setText("已通过");
                btn_ok.setText("确认入库");
                break;
        }
        tv_stuats.setText(stuats);
        tv_name.setText(bean.getAddedOperator());
        tv_time.setText(bean.getAddedTime());
        tv_transfer_out.setText(bean.getOutOrgName() + "  " + bean.getOutWarehouseName());
        tv_transfer_in.setText(bean.getInOrgName() + "  " + bean.getInWarehouseName());
        tv_content.setText(bean.getReason());
        stockAllotItemsBeans.clear();
        stockAllotItemsBeans.addAll(bean.getStockAllotItems());
        resellerDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.btn_ok:
                if (stuats.equals("待出库")) {//出库
                    showAllpackageList("待出库");
                } else if (stuats.equals("待入库")) {//入库
                    showAllpackageList("待入库");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(ResellerHandleDetailsActivity.this);
    }


    //包裝列表PopuWindow
    private void showAllpackageList(final String trim) {
        final AlertDialog.Builder alertDialog7 = new AlertDialog.Builder(this);
        View view1 = View.inflate(this, R.layout.reseller_yse, null);
        TextView titles = view1.findViewById(R.id.titles);
        TextView no_tv = view1.findViewById(R.id.no_tv);
        TextView yes_tv = view1.findViewById(R.id.yes_tv);
        alertDialog7
                .setIcon(R.mipmap.ic_launcher)
                .setView(view1)
                .create();
        final AlertDialog alertDialog = alertDialog7.show();
        if (trim.equals("待出库")) {
            titles.setText("是否确认完成该商品转商调拨申请的出库操作?");
        } else if (trim.equals("待入库")) {
            titles.setText("是否确认完成该商品转商调拨申请的入库操作?");
        }
        no_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        yes_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trim.equals("待出库")) {
                    DaiOutItemData();
                } else if (trim.equals("待入库")) {
                    DaiIntItemData();
                }
                alertDialog.dismiss();
            }
        });
    }

    //确认出库
    private void DaiOutItemData() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(ResellerApi.class)
                .getConfirm(id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            finish();
                        }
                    }
                });
    }
    //确认入库
    private void DaiIntItemData() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(ResellerApi.class)
                .getConfirmIntData(id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            finish();
                        }
                    }
                });
    }
}