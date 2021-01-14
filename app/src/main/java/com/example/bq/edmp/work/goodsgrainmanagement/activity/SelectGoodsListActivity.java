package com.example.bq.edmp.work.goodsgrainmanagement.activity;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.FromtUtil;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.goodsgrainmanagement.adapter.SelectGoodslistAdapter;
import com.example.bq.edmp.work.order.adapter.GoodslistAdapter;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.GoodsBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SelectGoodsListActivity extends BaseTitleActivity {
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsj)
    TextView wsj;

    private String mID = "0";
    private String orid = "";
    private TextView pinzhong_tv;
    private TextView danwei_tv;
    private EditText xiaolaing_et;
    private EditText shiji_xiaoliang_et;
    private TextView zonge_price_tv;
    private TextView cancel_tv;
    private TextView determine_tv;
    private Dialog mCameraDialog;
    private GoodsBean.DataBean mdataBean;

    private int currentPager = 1;
    private String mcustomerId = "";
    private ArrayList<GoodsBean.DataBean> rowsBeans;
    private SelectGoodslistAdapter goodslistAdapter;
    private LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_goods_list;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("选择商品");
        loadingDialog = new LoadingDialog(this);
        showGoodsInfo();
        rowsBeans = new ArrayList<>();
        goodslistAdapter = new SelectGoodslistAdapter(rowsBeans);
        xRecyclerView.setAdapter(goodslistAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
        goodslistAdapter.setOnItemClickListener(new SelectGoodslistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, GoodsBean.DataBean dataBean) {
                String customerPrice = String.valueOf(dataBean.getCustomerPrice());
                if (customerPrice.equals("0.0")) {
                    danwei_tv.setText("¥" + FromtUtil.getFromt(dataBean.getPrice()) + "/公斤");
                    shiji_xiaoliang_et.setText(FromtUtil.getFromt(dataBean.getPrice()));
                } else {
                    danwei_tv.setText("¥" + FromtUtil.getFromt(dataBean.getCustomerPrice()) + "/公斤");
                    shiji_xiaoliang_et.setText(FromtUtil.getFromt(dataBean.getCustomerPrice()));
                }
                mdataBean = dataBean;
                pinzhong_tv.setText(dataBean.getVarietyPackagingName());
                mCameraDialog.show();
            }
        });
    }

    @Override
    protected void initData() {
        getData();
    }

    @Override
    protected void initListener() {
        cancel_tv.setOnClickListener(this);
        determine_tv.setOnClickListener(this);
    }


    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_tv://取消
                if (mCameraDialog != null) {
                    xiaolaing_et.setText("");
                    shiji_xiaoliang_et.setText("");
                    mCameraDialog.dismiss();
                }
                break;
            case R.id.determine_tv://确认
                if (xiaolaing_et.getText().toString().equals("")) {
                    ToastUtil.setToast("请输入销售量");
                    break;
                }
                if (shiji_xiaoliang_et.getText().toString().equals("")) {
                    ToastUtil.setToast("请输入实际售价");
                    break;
                }
                addGoods(determine_tv);
                break;
        }
    }

    //加载更多数据
    private void initData2() {
        String sign = MD5Util.encode("customerId=" + mcustomerId + "&id=" + mID);
        RxHttpUtils.createApi(OrderApi.class)
                .getGoodslist(mcustomerId, mID, sign)
                .compose(Transformer.<GoodsBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<GoodsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(GoodsBean goodsBean) {
                        String code = goodsBean.getCode();
                        if (code.equals("200")) {
                            List<GoodsBean.DataBean> data = goodsBean.getData();
                            rowsBeans.addAll(data);
                            goodslistAdapter.addMoreData(data);
                        } else {
                            xRecyclerView.setNoMore(true);
                            ToastUtil.setToast("最后数据");
                        }
                    }
                });
    }

    //加载第一页
    private void getData() {
        currentPager = 1;
        String sign = MD5Util.encode("customerId=" + 1 + "&id=" + 1);
        RxHttpUtils.createApi(OrderApi.class)
                .getGoodslist("1", "1", sign)
                .compose(Transformer.<GoodsBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<GoodsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(GoodsBean goodsBean) {
                        String code = goodsBean.getCode();
                        if (code.equals("200")) {
                            List<GoodsBean.DataBean> data = goodsBean.getData();
                            if (data != null && data.size() != 0) {
                                xRecyclerView.setVisibility(View.VISIBLE);
                                wsj.setVisibility(View.GONE);
                                rowsBeans.clear();
                                rowsBeans.addAll(data);
                                goodslistAdapter.notifyDataSetChanged();
                            } else {
                                xRecyclerView.setVisibility(View.GONE);
                                wsj.setVisibility(View.VISIBLE);
                                ToastUtil.setToast("暂无数据");
                            }

                        } else {
                            xRecyclerView.setVisibility(View.GONE);
                            wsj.setVisibility(View.VISIBLE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    //显示商品信息
    private void showGoodsInfo() {
        mCameraDialog = new Dialog(SelectGoodsListActivity.this, R.style.my_dialog);

        View root = LayoutInflater.from(SelectGoodsListActivity.this).inflate(R.layout.select_goods_dialog, null);

        mCameraDialog.setContentView(root);
        mCameraDialog.setCanceledOnTouchOutside(false);

        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.BottomDialog_Animation); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        lp.height = WindowManager.LayoutParams.MATCH_PARENT; // 高度
        //lp.alpha = 9f; // 透明度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);

        pinzhong_tv = root.findViewById(R.id.pinzhong_tv);
        danwei_tv = root.findViewById(R.id.danwei_tv);
        xiaolaing_et = root.findViewById(R.id.xiaolaing_et);
        shiji_xiaoliang_et = root.findViewById(R.id.shiji_xiaoliang_et);
        zonge_price_tv = root.findViewById(R.id.zonge_price_tv);
        cancel_tv = root.findViewById(R.id.cancel_tv);
        determine_tv = root.findViewById(R.id.determine_tv);


        xiaolaing_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!xiaolaing_et.getText().toString().equals("") && !shiji_xiaoliang_et.getText().toString().equals("")) {
                    double xiaolaing = Double.parseDouble(xiaolaing_et.getText().toString());
                    double shijiaxiaolaing = Double.parseDouble(shiji_xiaoliang_et.getText().toString());
                    zonge_price_tv.setText(FromtUtil.getFromt(xiaolaing * shijiaxiaolaing));
                } else {
                    zonge_price_tv.setText("0.00");
                }
            }
        });
        shiji_xiaoliang_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!xiaolaing_et.getText().toString().equals("") && !shiji_xiaoliang_et.getText().toString().equals("")) {
                    double xiaolaing = Double.parseDouble(xiaolaing_et.getText().toString());
                    double shijiaxiaolaing = Double.parseDouble(shiji_xiaoliang_et.getText().toString());
                    zonge_price_tv.setText(FromtUtil.getFromt(xiaolaing * shijiaxiaolaing));
                } else {
                    zonge_price_tv.setText("0.00");
                }
            }
        });

    }

    //添加商品
    private void addGoods(View view) {
        hideKeyboard(view);
        String sign = MD5Util.encode("ordersId=" + orid + "&packagingId=" + mdataBean.getId()
                + "&price=" + shiji_xiaoliang_et.getText().toString() + "&qty=" + xiaolaing_et.getText().toString());
        RxHttpUtils.createApi(OrderApi.class)
                .getNewsaveGoods(orid, mdataBean.getId(), shiji_xiaoliang_et.getText().toString(), xiaolaing_et.getText().toString(), sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if (baseABean.getCode() == 200) {
                            finish();
                        } else {
                            ToastUtil.setToast("添加失败");
                            xiaolaing_et.setText("");
                            shiji_xiaoliang_et.setText("");
                        }
                    }
                });
    }

    /**
     * 隐藏软键盘
     *
     * @param :上下文环境，一般为Activity实例
     * @param view                 :一般为EditText
     */
    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
