package com.example.bq.edmp.work.reseller.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.inventorytransfer.activity.UpdateTransferGoodsActivity;
import com.example.bq.edmp.work.inventorytransfer.adapter.CommodityListAdp;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;
import com.example.bq.edmp.work.reseller.adapter.ResellerEdtAdapter;
import com.example.bq.edmp.work.reseller.api.ResellerApi;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 申请转商调拨二
 * */
public class ApplyResellerAllocationTwoActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.reason_tv)
    TextView reason_tv;
    @BindView(R.id.allcation_chu_compy_tv)
    TextView allcation_chu_compy_tv;
    @BindView(R.id.allcation_ru_compy_tv)
    TextView allcation_ru_compy_tv;
    @BindView(R.id.allcation_chu_ku_tv)
    TextView allcation_chu_ku_tv;
    @BindView(R.id.btn_del)
    TextView btn_del;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.allcation_ru_ku_tv)
    TextView allcation_ru_ku_tv;
    @BindView(R.id.add_goods_rl)
    RelativeLayout add_goods_rl;
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;

    private EditFinishedProductAllocationBean editFinishedProductAllocationBean;
    private LoadingDialog loadingDialog;
    private String id;
    private ResellerEdtAdapter mAdapter;
    private ArrayList<EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean> stockAllotItemsBeans;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_reseller_allocation_two;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(ApplyResellerAllocationTwoActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("申请转商调拨");

        id = getIntent().getStringExtra("id");

        stockAllotItemsBeans = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new ResellerEdtAdapter(stockAllotItemsBeans, ApplyResellerAllocationTwoActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ResellerEdtAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int type, int pos, EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean stockAllotItemsBean) {
                if (type == 1) {//删除
                    deleteGoods(stockAllotItemsBean.getInItemId() + "", stockAllotItemsBean.getOutItemId() + "");
                } else if (type == 2) {//编辑
                    etData(stockAllotItemsBean);
                }
            }
        });

    }

    //修改商品
    private void etData(EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean stockAllotItemsBean) {
        Intent intent = new Intent(ApplyResellerAllocationTwoActivity.this, AddResellerCommodityActivity.class);
        intent.putExtra("id", editFinishedProductAllocationBean.getData().getId());
        intent.putExtra("StockAllotItemsBean", stockAllotItemsBean);//
        intent.putExtra("type", 1);
        startActivity(intent);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getProudctAllocationDetails();
    }

    //刪除转商商品
    private void deleteGoods(String inItemId, String outItemId) {
        String sign = MD5Util.encode("inItemId=" + inItemId + "&outItemId=" + outItemId + "&stockAllotId=" + id);
        RxHttpUtils.createApi(ResellerApi.class)
                .deleteGoods(inItemId, outItemId, id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("商品删除成功");
                            getProudctAllocationDetails();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //获取调拨详情
    private void getProudctAllocationDetails() {
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
                        editFinishedProductAllocationBean = bean;
                        if (bean.getCode() == 200) {
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //详情赋值
    private void setData(EditFinishedProductAllocationBean.DataBean bean) {
        reason_tv.setText(bean.getReason());
        allcation_ru_compy_tv.setText(bean.getInOrgName());
        allcation_chu_compy_tv.setText(bean.getOutOrgName());
        allcation_chu_ku_tv.setText(bean.getOutWarehouseName());
        allcation_ru_ku_tv.setText(bean.getInWarehouseName());
        List<EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean> stockAllotItems = bean.getStockAllotItems();
        if (stockAllotItems != null) {
            mRecyclerView.setVisibility(View.VISIBLE);
            stockAllotItemsBeans.clear();
            stockAllotItemsBeans.addAll(bean.getStockAllotItems());
            mAdapter.notifyDataSetChanged();
        } else {
            mRecyclerView.setVisibility(View.GONE);
            stockAllotItemsBeans.clear();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
        add_goods_rl.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.btn_del://删除
                deleteAllot();
                break;
            case R.id.tv_submit://提交
                if (stockAllotItemsBeans.size() == 0) {
                    ToastUtil.setToast("请选择转商商品");
                    break;
                }
                submitAllot();
                break;
            case R.id.add_goods_rl://添加商品
                Intent intent = new Intent(ApplyResellerAllocationTwoActivity.this, AddResellerCommodityActivity.class);
                intent.putExtra("id", editFinishedProductAllocationBean.getData().getId());
                intent.putExtra("type", 0);
                startActivity(intent);
                break;
        }
    }


    //调拨提交
    private void submitAllot() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(ResellerApi.class)
                .submitAllot(id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("提交成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }


    //删除调拨
    private void deleteAllot() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(ResellerApi.class)
                .deleteAllot(id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("删除成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(ApplyResellerAllocationTwoActivity.this);
    }

}