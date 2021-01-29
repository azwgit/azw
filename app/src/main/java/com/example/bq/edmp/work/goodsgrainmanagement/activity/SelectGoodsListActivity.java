package com.example.bq.edmp.work.goodsgrainmanagement.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.InputType;
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
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.FromtUtil;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.goodsgrainmanagement.adapter.SelectGoodslistAdapter;
import com.example.bq.edmp.work.goodsgrainmanagement.api.GoodsSalesApi;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.SelecGoodsListBean;
import com.example.bq.edmp.work.order.adapter.GoodslistAdapter;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.GoodsBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SelectGoodsListActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, SelectGoodsListActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsj)
    TextView wsj;
    private TextView pinzhong_tv;
    private TextView danwei_tv;
    private EditText xiaolaing_et;
    private EditText shiji_xiaoliang_et;
    private TextView zonge_price_tv;
    private TextView cancel_tv;
    private TextView determine_tv;
    private Dialog mCameraDialog;
    private SelecGoodsListBean.DataBean mdataBean;
    private String id = "";//订单id
    private int currentPager = 1;
    private ArrayList<SelecGoodsListBean.DataBean> rowsBeans;
    private SelectGoodslistAdapter goodslistAdapter;
    private LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_goods_list;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("选择商品");
        id = getIntent().getStringExtra(Constant.ID);
        if (id == null || "".equals(id)) {
            ToastUtil.setToast("数据错误");
            finish();
        }
        ProApplication.getinstance().addActivity(this);
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
            public void onItemClick(int pos, SelecGoodsListBean.DataBean dataBean) {
                mdataBean = dataBean;
                pinzhong_tv.setText(dataBean.getName());
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
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .getSalesList()
                .compose(Transformer.<SelecGoodsListBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<SelecGoodsListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SelecGoodsListBean goodsBean) {
                        if (goodsBean.getCode() == 200) {
                            List<SelecGoodsListBean.DataBean> data = goodsBean.getData();
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
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .getSalesList()
                .compose(Transformer.<SelecGoodsListBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<SelecGoodsListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SelecGoodsListBean goodsBean) {
                        if (goodsBean.getCode() == 200) {
                            List<SelecGoodsListBean.DataBean> data = goodsBean.getData();
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

        xiaolaing_et.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        xiaolaing_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //删除“.”后面超过2位后的数据
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        xiaolaing_et.setText(s);
                        xiaolaing_et.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    xiaolaing_et.setText(s);
                    xiaolaing_et.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        xiaolaing_et.setText(s.subSequence(0, 1));
                        xiaolaing_et.setSelection(1);
                        return;
                    }
                }
                //包含. 查看. 前面是否有值
                if (s.toString().trim().contains(".")) {
                    String a = s.toString().trim().substring(0, s.toString().trim().indexOf("."));
                    if (a.length() <= 0) {
                        s = "0" + s;
                        xiaolaing_et.setText(s);
                        xiaolaing_et.setSelection(2);
                    }
                }
                if (!xiaolaing_et.getText().toString().equals("") && !shiji_xiaoliang_et.getText().toString().equals("")) {
                    double xiaolaing = Double.parseDouble(xiaolaing_et.getText().toString());
                    double shijiaxiaolaing = Double.parseDouble(shiji_xiaoliang_et.getText().toString());
                    zonge_price_tv.setText(FromtUtil.getFromt(xiaolaing * shijiaxiaolaing));
                } else {
                    zonge_price_tv.setText("0.00");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        shiji_xiaoliang_et.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        shiji_xiaoliang_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //删除“.”后面超过2位后的数据
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        shiji_xiaoliang_et.setText(s);
                        shiji_xiaoliang_et.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    shiji_xiaoliang_et.setText(s);
                    shiji_xiaoliang_et.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        shiji_xiaoliang_et.setText(s.subSequence(0, 1));
                        shiji_xiaoliang_et.setSelection(1);
                        return;
                    }
                }
                //包含. 查看. 前面是否有值
                if (s.toString().trim().contains(".")) {
                    String a = s.toString().trim().substring(0, s.toString().trim().indexOf("."));
                    if (a.length() <= 0) {
                        s = "0" + s;
                        shiji_xiaoliang_et.setText(s);
                        shiji_xiaoliang_et.setSelection(2);
                    }
                }
                if (!xiaolaing_et.getText().toString().equals("") && !shiji_xiaoliang_et.getText().toString().equals("")) {
                    double xiaolaing = Double.parseDouble(xiaolaing_et.getText().toString());
                    double shijiaxiaolaing = Double.parseDouble(shiji_xiaoliang_et.getText().toString());
                    zonge_price_tv.setText(FromtUtil.getFromt(xiaolaing * shijiaxiaolaing));
                } else {
                    zonge_price_tv.setText("0.00");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    //添加商品
    private void addGoods(View view) {
        hideKeyboard(view);
        String sign = MD5Util.encode("cgOrderId=" + id + "&itemId=" + mdataBean.getId()
                + "&price=" + shiji_xiaoliang_et.getText().toString() + "&qty=" + xiaolaing_et.getText().toString());
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .addGoods(id, mdataBean.getId() + "", shiji_xiaoliang_et.getText().toString(), xiaolaing_et.getText().toString(), sign)
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
