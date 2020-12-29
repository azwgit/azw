package com.example.bq.edmp.work.order.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.order.adapter.TrackingAdapter;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.OrderDetailsBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 订单详情页
 * */
public class Order_Tracking_DetailsActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    //提交
    @BindView(R.id.tijiao_tv)
    TextView tijiao_tv;
    @BindView(R.id.tijiao_tiem)
    TextView tijiao_tiem;
    @BindView(R.id.tijiao_img)
    ImageView tijiao_img;
    @BindView(R.id.tijiao_right)
    View tijiao_right;
    //分配
    @BindView(R.id.fenpei_tv)
    TextView fenpei_tv;
    @BindView(R.id.fenpei_tiem)
    TextView fenpei_tiem;
    @BindView(R.id.fenpei_img)
    ImageView fenpei_img;
    @BindView(R.id.fenpei_right)
    View fenpei_right;
    @BindView(R.id.fenpei_left)
    View fenpei_left;
    //回款
    @BindView(R.id.huikuan_tv)
    TextView huikuan_tv;
    @BindView(R.id.huikuan_tiem)
    TextView huikuan_tiem;
    @BindView(R.id.huikuan_img)
    ImageView huikuan_img;
    @BindView(R.id.huikuan_right)
    View huikuan_right;
    @BindView(R.id.huikuan_left)
    View huikuan_left;
    //发货
    @BindView(R.id.fahuo_tv)
    TextView fahuo_tv;
    @BindView(R.id.fahuo_tiem)
    TextView fahuo_tiem;
    @BindView(R.id.fahuo_img)
    ImageView fahuo_img;
    @BindView(R.id.fahuo_right)
    View fahuo_right;
    @BindView(R.id.fahuo_left)
    View fahuo_left;
    //完成
    @BindView(R.id.wancheng_tv)
    TextView wancheng_tv;
    @BindView(R.id.wancheng_tiem)
    TextView wancheng_tiem;
    @BindView(R.id.wancheng_img)
    ImageView wancheng_img;
    @BindView(R.id.wancheng_left)
    View wancheng_left;

    @BindView(R.id.code_tv)
    TextView code_tv;
    @BindView(R.id.price_tv)
    TextView price_tv;
    @BindView(R.id.position_name_tv)
    TextView position_name_tv;
    @BindView(R.id.compay_name_tv)
    TextView compay_name_tv;
    @BindView(R.id.user_name_tv)
    TextView user_name_tv;
    @BindView(R.id.phone_tv)
    TextView phone_tv;
    @BindView(R.id.shou_address_tv)
    TextView shou_address_tv;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    private LoadingDialog loadingDialog;
    private String id;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_tracking_details;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(Order_Tracking_DetailsActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("订单详情");

        id = getIntent().getStringExtra("ID");


    }


    @Override
    protected void initData() {

        String sign = MD5Util.encode("id=" + id);

        RxHttpUtils.createApi(OrderApi.class)
                .getShow(id, sign)
                .compose(Transformer.<OrderDetailsBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<OrderDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(OrderDetailsBean orderDetailsBean) {
                        String code = orderDetailsBean.getCode();
                        if (code.equals("200")) {

                            gainData(orderDetailsBean.getData());

                        } else {

                        }
                    }
                });
    }

    private void gainData(OrderDetailsBean.DataBean data) {

        code_tv.setText(data.getCode());
        compay_name_tv.setText(data.getCustomerName());
        user_name_tv.setText(data.getContacts());
        phone_tv.setText(data.getMobTel());
        shou_address_tv.setText(data.getAddress());

        List<OrderDetailsBean.DataBean.OrderItemsBean> orderItems = data.getOrderItems();
        if (orderItems != null && orderItems.size() != 0) {
            wsj.setVisibility(View.GONE);
            xRecyclerView.setVisibility(View.VISIBLE);

            TrackingAdapter trackingAdapter = new TrackingAdapter(orderItems);
            xRecyclerView.setLayoutManager(new LinearLayoutManager(Order_Tracking_DetailsActivity.this));
            xRecyclerView.setAdapter(trackingAdapter);
            trackingAdapter.notifyDataSetChanged();

        } else {
            wsj.setVisibility(View.VISIBLE);
            xRecyclerView.setVisibility(View.GONE);
        }

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

    public void mJudge(String string) {
        if (string.equals("1")) {//提交
            //分配
            fenpei_img.setAlpha((float) 0.6);
            fenpei_tv.setAlpha((float) 0.6);
            fenpei_right.setAlpha((float) 0.6);
            fenpei_left.setAlpha((float) 0.6);
            fenpei_tiem.setVisibility(View.INVISIBLE);
            //回款
            huikuan_img.setAlpha((float) 0.6);
            huikuan_tv.setAlpha((float) 0.6);
            huikuan_right.setAlpha((float) 0.6);
            huikuan_left.setAlpha((float) 0.6);
            huikuan_tiem.setVisibility(View.INVISIBLE);
            //发货
            fahuo_img.setAlpha((float) 0.6);
            fahuo_tv.setAlpha((float) 0.6);
            fahuo_right.setAlpha((float) 0.6);
            fahuo_left.setAlpha((float) 0.6);
            fahuo_tiem.setVisibility(View.INVISIBLE);
            //完成
            wancheng_img.setAlpha((float) 0.6);
            wancheng_tv.setAlpha((float) 0.6);
            wancheng_left.setAlpha((float) 0.6);
            wancheng_tiem.setVisibility(View.INVISIBLE);
        } else if (string.equals("2")) {//分配
            //回款
            huikuan_img.setAlpha((float) 0.6);
            huikuan_tv.setAlpha((float) 0.6);
            huikuan_right.setAlpha((float) 0.6);
            huikuan_left.setAlpha((float) 0.6);
            huikuan_tiem.setVisibility(View.INVISIBLE);
            //发货
            fahuo_img.setAlpha((float) 0.6);
            fahuo_tv.setAlpha((float) 0.6);
            fahuo_right.setAlpha((float) 0.6);
            fahuo_left.setAlpha((float) 0.6);
            fahuo_tiem.setVisibility(View.INVISIBLE);
            //完成
            wancheng_img.setAlpha((float) 0.6);
            wancheng_tv.setAlpha((float) 0.6);
            wancheng_left.setAlpha((float) 0.6);
            wancheng_tiem.setVisibility(View.INVISIBLE);
        } else if (string.equals("3")) {//回款
            //发货
            fahuo_img.setAlpha((float) 0.6);
            fahuo_tv.setAlpha((float) 0.6);
            fahuo_right.setAlpha((float) 0.6);
            fahuo_left.setAlpha((float) 0.6);
            fahuo_tiem.setVisibility(View.INVISIBLE);
            //完成
            wancheng_img.setAlpha((float) 0.6);
            wancheng_tv.setAlpha((float) 0.6);
            wancheng_left.setAlpha((float) 0.6);
            wancheng_tiem.setVisibility(View.INVISIBLE);
        } else if (string.equals("4")) {//发货
            //完成
            wancheng_img.setAlpha((float) 0.6);
            wancheng_tv.setAlpha((float) 0.6);
            wancheng_left.setAlpha((float) 0.6);
            wancheng_tiem.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(Order_Tracking_DetailsActivity.this);
    }
}
