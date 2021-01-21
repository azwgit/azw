package com.example.bq.edmp.work.reseller.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.inventorytransfer.adapter.AllListPackageAdp;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.inventorytransfer.bean.AllpackageListBean;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;
import com.example.bq.edmp.work.inventorytransfer.bean.VarittiesListBean;
import com.example.bq.edmp.work.reseller.adapter.PinLiangListPackageAdp;
import com.example.bq.edmp.work.reseller.api.ResellerApi;

import java.io.Serializable;

import butterknife.BindView;

/*
 * 添加转商商品
 * */
public class AddResellerCommodityActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.ed_number)
    EditText ed_number;
    @BindView(R.id.tv_transfer_number)
    TextView tv_transfer_number;
    @BindView(R.id.tv_pinliang_number)
    TextView tv_pinliang_number;
    @BindView(R.id.btn_del)
    TextView btn_del;
    @BindView(R.id.btn_ok)
    TextView btn_ok;

    private AllpackageListBean allpackageListBean;
    PopupWindow mTypePopuWindow;
    private LoadingDialog loadingDialog;
    private int id;
    private int type;
    private int allpackageId = 0;//商品id
    private int pinLiangId = 0;//商品粮id
    private EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean stockAllotItemsBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_reseller_commodity;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(AddResellerCommodityActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("添加转商商品");
        id = getIntent().getIntExtra("id", 0);
        type = getIntent().getIntExtra("type", 0);//0添加  1修改
        stockAllotItemsBean = (EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean) getIntent().getSerializableExtra("StockAllotItemsBean");

        if (stockAllotItemsBean != null && type == 1) {
            //数量
            ed_number.setText(stockAllotItemsBean.getQty() + "");
            //商品
            tv_transfer_number.setTextColor(getResources().getColor(R.color.color33));
            tv_transfer_number.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            tv_transfer_number.setText(stockAllotItemsBean.getOutItemName());
            allpackageId = stockAllotItemsBean.getOutItemId();

            //商品粮
            tv_pinliang_number.setTextColor(getResources().getColor(R.color.color33));
            tv_pinliang_number.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            tv_pinliang_number.setText(stockAllotItemsBean.getInItemName());
            pinLiangId = stockAllotItemsBean.getInItemId();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        tv_transfer_number.setOnClickListener(this);
        tv_pinliang_number.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.tv_transfer_number://商品
                getAllpackageList(1);
                break;
            case R.id.tv_pinliang_number://商品粮
                getAllitemList(2);
                break;
            case R.id.btn_ok://确定
                String trim = ed_number.getText().toString().trim();
                if (trim.equals("")) {
                    ToastUtil.setToast("请输入数量");
                    break;
                }
                if (allpackageId == 0) {
                    ToastUtil.setToast("请选择商品");
                    break;
                }
                if (pinLiangId == 0) {
                    ToastUtil.setToast("请选择商品粮");
                    break;
                }
                if (type == 0) {//添加
                    addTransferGoods(trim);
                } else if (type == 1) {//修改
                    addEtTransferGoods(trim);
                }

                break;
            case R.id.btn_del://取消
                finish();
                break;
        }
    }

    //修改
    private void addEtTransferGoods(String number) {
        String sign = MD5Util.encode("inItemId=" + pinLiangId + "&outItemId=" + allpackageId + "&qty=" + number + "&stockAllotId=" + id);
        RxHttpUtils.createApi(ResellerApi.class)
                .addEtTransferGoods(pinLiangId + "", allpackageId + "", number, id + "", sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("添加成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //添加调拨商品
    private void addTransferGoods(String number) {
        String sign = MD5Util.encode("inItemId=" + pinLiangId + "&outItemId=" + allpackageId + "&qty=" + number + "&stockAllotId=" + id);
        RxHttpUtils.createApi(ResellerApi.class)
                .addTransferGoods(pinLiangId + "", allpackageId + "", number, id + "", sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("添加成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }


    //获取所有商品粮列表
    private void getAllitemList(final int type) {
        RxHttpUtils.createApi(ResellerApi.class)
                .getAllitemList()
                .compose(Transformer.<AllpackageListBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<AllpackageListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AllpackageListBean bean) {
                        if (bean.getCode() == 200) {
                            allpackageListBean = bean;
                            showAllpackageList(type);
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //获取所有包装列表
    private void getAllpackageList(final int type) {
        RxHttpUtils.createApi(ResellerApi.class)
                .getAllpackageList()
                .compose(Transformer.<AllpackageListBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<AllpackageListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AllpackageListBean bean) {
                        if (bean.getCode() == 200) {
                            allpackageListBean = bean;
                            showAllpackageList(type);
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //包裝列表PopuWindow
    private void showAllpackageList(final int type) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        if (type == 1) {
            AllListPackageAdp allListPackageAdp = new AllListPackageAdp(allpackageListBean.getData());
            myRecyclerViewOne.setAdapter(allListPackageAdp);
            allListPackageAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    AllpackageListBean.DataBean bean = allpackageListBean.getData().get(position);
                    tv_transfer_number.setTextColor(getResources().getColor(R.color.color33));
                    tv_transfer_number.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    tv_transfer_number.setText(bean.getVarietyPackagingName());
                    allpackageId = allpackageListBean.getData().get(position).getId();
                    mTypePopuWindow.dismiss();
                }
            });
        } else if (type == 2) {
            PinLiangListPackageAdp pinLiangListPackageAdp = new PinLiangListPackageAdp(allpackageListBean.getData());
            myRecyclerViewOne.setAdapter(pinLiangListPackageAdp);
            pinLiangListPackageAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    AllpackageListBean.DataBean bean = allpackageListBean.getData().get(position);
                    tv_pinliang_number.setTextColor(getResources().getColor(R.color.color33));
                    tv_pinliang_number.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    tv_pinliang_number.setText(bean.getName());
                    pinLiangId = allpackageListBean.getData().get(position).getId();
                    mTypePopuWindow.dismiss();
                }
            });
        }
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
        ProApplication.getinstance().finishActivity(AddResellerCommodityActivity.this);
    }
}