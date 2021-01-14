package com.example.bq.edmp.work.returnsmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.marketingactivities.api.MarketingActivitiesApi;
import com.example.bq.edmp.work.returnsmanagement.adapter.CustomerListAdp;
import com.example.bq.edmp.work.returnsmanagement.adapter.ProductListAdp;
import com.example.bq.edmp.work.returnsmanagement.adapter.ReturenGoodsTypeAdp;
import com.example.bq.edmp.work.returnsmanagement.api.ReturnGoodsApi;
import com.example.bq.edmp.work.returnsmanagement.bean.CustomerListBean;
import com.example.bq.edmp.work.returnsmanagement.bean.GoodsListBean;
import com.example.bq.edmp.work.returnsmanagement.bean.ReturnsGoodsDetailsBean;
import com.example.bq.edmp.work.returnsmanagement.eventbus.CloseActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EditApplyForRefundActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, EditApplyForRefundActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.btn_submit)
    TextView mBtnSubmit;//提交按钮
    @BindView(R.id.btn_save)
    TextView mBtnSave;//保存按钮
    @BindView(R.id.btn_del)
    TextView mBtnDel;//删除按钮
    @BindView(R.id.tv_order_number)
    TextView mTvOrderNumber;//订单号
    @BindView(R.id.tv_packing)
    TextView mTvPacking;//包装
    @BindView(R.id.tv_money_and_number)
    TextView mTvMoneyAndNumber;//价格及数量
    @BindView(R.id.ed_number)
    EditText mEdNumber;//退货数量
    @BindView(R.id.tv_return_goods_type)
    TextView mTvReturnGoodsType;//退货类型
    @BindView(R.id.ed_money)
    EditText mEdMoney;//销售价格
    @BindView(R.id.tv_customer)
    TextView mTvCustomer;//销售客户
    @BindView(R.id.tv_commodity)
    TextView mTvCommodity;//转商商品
    @BindView(R.id.rl_zhuangshang)
    LinearLayout mRlZhuangShang;//转商布局
    @BindView(R.id.tv_all_money)
    TextView mTvAllMoney;//销售总额
    @BindView(R.id.tv_money_info)
    TextView mTvMoneyInfo;//销售总额或退款金额
    private PopupWindow mTypePopuWindow;
    private String ordersId = "";
    private int saleItemId = 0;//转商商品id
    private int customerid = 0;
    private int returnGoodId = 1;//默认退回仓库  1退回仓库 2 转商销售
    private List<String> returnGoodsTypeList = null;
    private ILoadingView loading_dialog;
    private GoodsListBean goodsListBean;//部门据源
    private CustomerListBean customerListBean;//客户据源
    private ReturnsGoodsDetailsBean returnsGoodsDetailsBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_for_refund;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("申请退单");
        ordersId = getIntent().getStringExtra(Constant.ID);
        if ("".equals(ordersId)) {
            ToastUtil.setToast("数据出错请重试");
            finish();
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        ReturnGoodsDetails();
        returnGoodsTypeList = new ArrayList<String>();
        returnGoodsTypeList.add("退回仓库");
        returnGoodsTypeList.add("转商销售");
        mEdNumber.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        mEdNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            //输入时的调用
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                //删除“.”后面超过2位后的数据
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        mEdNumber.setText(s);
                        mEdNumber.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    mEdMoney.setText(s);
                    mEdMoney.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        mEdNumber.setText(s.subSequence(0, 1));
                        mEdNumber.setSelection(1);
                        return;
                    }
                }
                //包含. 查看. 前面是否有值
                if (s.toString().trim().contains(".")) {
                    String a = s.toString().trim().substring(0, s.toString().trim().indexOf("."));
                    if (a.length() <= 0) {
                        s = "0" + s;
                        mEdNumber.setText(s);
                        mEdNumber.setSelection(2);
                    }
                }
                if (returnGoodId == 1) {
                    if (!"".equals(mEdNumber.getText().toString().trim())) {
                        double money = Double.parseDouble(mEdNumber.getText().toString().trim()) * returnsGoodsDetailsBean.getData().getReturnPrice();
                        mTvAllMoney.setText("￥" + MoneyUtils.formatMoney(Double.parseDouble(money + "")));
                    } else {
                        mTvAllMoney.setText("￥0.00");
                    }
                } else {
                    if (!"".equals(mEdNumber.getText().toString().trim()) && !"".equals(mEdMoney.getText().toString().trim())) {
                        double money = Double.parseDouble(mEdNumber.getText().toString().trim()) * Double.parseDouble(mEdMoney.getText().toString().trim());
                        mTvAllMoney.setText("￥" + MoneyUtils.formatMoney(Double.parseDouble(money + "")));
                    } else {
                        mTvAllMoney.setText("￥0.00");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mEdMoney.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        //设置字符过滤
        mEdMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                //删除“.”后面超过2位后的数据
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        mEdMoney.setText(s);
                        mEdMoney.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    mEdMoney.setText(s);
                    mEdMoney.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        mEdMoney.setText(s.subSequence(0, 1));
                        mEdMoney.setSelection(1);
                        return;
                    }
                }
                //包含. 查看. 前面是否有值
                if (s.toString().trim().contains(".")) {
                    String a = s.toString().trim().substring(0, s.toString().trim().indexOf("."));
                    if (a.length() <= 0) {
                        s = "0" + s;
                        mEdMoney.setText(s);
                        mEdMoney.setSelection(2);
                    }
                }
                if (!"".equals(mEdNumber.getText().toString().trim()) && !"".equals(mEdMoney.getText().toString().trim())) {
                    double money = Double.parseDouble(mEdNumber.getText().toString().trim()) * Double.parseDouble(mEdMoney.getText().toString().trim());
                    mTvAllMoney.setText("￥" + MoneyUtils.formatMoney(Double.parseDouble(money + "")));
                } else {
                    mTvAllMoney.setText("￥0.00");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBtnSubmit.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mBtnDel.setOnClickListener(this);
        mTvCustomer.setOnClickListener(this);
        mTvReturnGoodsType.setOnClickListener(this);
        mTvCommodity.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                checkData(2);
                break;
            case R.id.btn_save:
                checkData(1);
                break;
            case R.id.btn_del:
                deleteReturnGoods();
                break;
            case R.id.tv_customer:
                getCustomerList();
                break;
            case R.id.tv_return_goods_type:
                showReturnGoodsTypeList();
                break;
            case R.id.tv_commodity:
                getProductList();
                break;

        }
    }

    //保存或提交验证
    private void checkData(int type) {
        String number = mEdNumber.getText().toString().trim();
        if ("".equals(number)) {
            ToastUtil.setToast("请输入退货数量");
            return;
        }
        //1 退回仓库 2转商销售
        if (returnGoodId == 1) {
            updataReturnsGoods("", returnsGoodsDetailsBean.getData().getItemId() + "", ordersId, returnsGoodsDetailsBean.getData().getReturnPrice() + "", number, "", "", type + "", returnGoodId + "");
        } else {
            String mEdMoney = mEdNumber.getText().toString().trim();
            if ("".equals(mEdMoney)) {
                ToastUtil.setToast("请输入销售价格");
                return;
            }
            if (customerid == 0) {
                ToastUtil.setToast("请选择销售客户");
                return;
            }
            if (saleItemId == 0) {
                ToastUtil.setToast("请选择转商商品");
                return;
            }
            updataReturnsGoods(customerid + "", returnsGoodsDetailsBean.getData().getItemId() + "", ordersId, returnsGoodsDetailsBean.getData().getReturnPrice() + "", number, saleItemId + "", mEdMoney, type + "", returnGoodId + "");
        }
    }

    //退货申请
    private void updataReturnsGoods(String cgCustomerId, String itemId, String ordersId, String returnPrice, String returnQty, String saleItemId, String salePrice, String type, String types) {
        String sign = MD5Util.encode("cgCustomerId=" + cgCustomerId + "&id=" + returnsGoodsDetailsBean.getData().getId() + "&itemId=" + itemId + "&ordersId=" + ordersId + "&returnPrice=" + returnPrice + "&returnQty=" + returnQty + "&saleItemId=" + saleItemId + "&salePrice=" + salePrice + "&type=" + type + "&types=" + types);
        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .updataReturnsGoods(cgCustomerId, returnsGoodsDetailsBean.getData().getId() + "", itemId, ordersId, returnPrice, returnQty, saleItemId, salePrice, type, types, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            EventBus.getDefault().post(new CloseActivity());
                            ToastUtil.setToast("操作成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //退货类型PopuWindow
    private void showReturnGoodsTypeList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        ReturenGoodsTypeAdp contractorListAdp = new ReturenGoodsTypeAdp(returnGoodsTypeList);
        myRecyclerViewOne.setAdapter(contractorListAdp);
        contractorListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //if 退回仓库操作 else 转商操作
                if (position == 0) {
                    mTvMoneyInfo.setText("退款金额");
                    returnGoodId = 1;
                    mRlZhuangShang.setVisibility(View.GONE);
                    if (!"".equals(mEdNumber.getText().toString().trim())) {
                        double money = Double.parseDouble(mEdNumber.getText().toString().trim()) * returnsGoodsDetailsBean.getData().getReturnPrice();
                        mTvAllMoney.setText("￥" + MoneyUtils.formatMoney(Double.parseDouble(money + "")));
                    } else {
                        mTvAllMoney.setText("￥0.00");
                    }
                } else {
                    mTvMoneyInfo.setText("销售总额");
                    mRlZhuangShang.setVisibility(View.VISIBLE);
                    returnGoodId = 2;
                    if (!"".equals(mEdNumber.getText().toString().trim()) && !"".equals(mEdMoney.getText().toString().trim())) {
                        double money = Double.parseDouble(mEdNumber.getText().toString().trim()) * Double.parseDouble(mEdMoney.getText().toString().trim());
                        mTvAllMoney.setText("￥" + MoneyUtils.formatMoney(Double.parseDouble(money + "")));
                    } else {
                        mTvAllMoney.setText("￥0.00");
                    }
                }
                mTvReturnGoodsType.setText(returnGoodsTypeList.get(position));
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

    //获取客户列表
    private void getCustomerList() {
        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .getCustomerList()
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
                customerid = customerListBean.getData().get(position).getId();
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

    //获取商品列表
    private void getProductList() {
        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .getGoodsList()
                .compose(Transformer.<GoodsListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<GoodsListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(GoodsListBean bean) {
                        if (bean.getCode() == 200) {
                            goodsListBean = bean;
                            showProductList();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //商品列表PopuWindow
    private void showProductList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        ProductListAdp contractorListAdp = new ProductListAdp(goodsListBean.getData());
        myRecyclerViewOne.setAdapter(contractorListAdp);
        contractorListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                saleItemId = goodsListBean.getData().get(position).getId();
                mTvCommodity.setText(goodsListBean.getData().get(position).getName());
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

    //获取退货详情
    private void ReturnGoodsDetails() {
        String sign = MD5Util.encode("id=" + ordersId);
        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .ReturnGoodsDetails(ordersId, sign)
                .compose(Transformer.<ReturnsGoodsDetailsBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<ReturnsGoodsDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ReturnsGoodsDetailsBean bean) {
                        if (bean.getCode() == 200) {
                            returnsGoodsDetailsBean = bean;
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            finish();
                        }
                    }
                });
    }

    //详情赋值
    private void setData(ReturnsGoodsDetailsBean.DataBean bean) {
        mBtnDel.setVisibility(View.VISIBLE);
        mTvOrderNumber.setText("订单号 " + bean.getCode());
        mTvPacking.setText(bean.getVarietyName());
        mTvMoneyAndNumber.setText("单价 ￥ " +MoneyUtils.formatMoney(bean.getReturnPrice())  + "/公斤    " + "订单数量  " + MoneyUtils.formatMoney(bean.getQty()) + "公斤");
        mEdNumber.setText(MoneyUtils.formatMoney(bean.getReturnQty()));
        ordersId = bean.getOrdersId() + "";
        //1 退回仓库 2转商
        if (bean.getTypes() == 1) {
            mTvReturnGoodsType.setText("退回仓库");
            returnGoodId = 1;
            mTvMoneyInfo.setText("退款金额");
//            double money = Double.parseDouble(mEdNumber.getText().toString().trim()) * returnsGoodsDetailsBean.getData().getReturnPrice();
            mTvAllMoney.setText("￥" + MoneyUtils.formatMoney(bean.getAmount()));
        } else {
            mTvReturnGoodsType.setText("转商销售");
            saleItemId = bean.getSalesId();
            customerid = bean.getCustomerId();
            returnGoodId = 2;
            mTvMoneyInfo.setText("销售总额");
            mTvCommodity.setText(bean.getSaleItemName());
            mTvCustomer.setText(bean.getCustomerName());
            mEdMoney.setText(MoneyUtils.formatMoney(bean.getSalePrice()));
            mTvAllMoney.setText("￥" + MoneyUtils.formatMoney(bean.getSalesAmount()));
            mRlZhuangShang.setVisibility(View.VISIBLE);
        }
    }
    //删除退货
    private void deleteReturnGoods() {
        String sign = MD5Util.encode("id=" + returnsGoodsDetailsBean.getData().getId());
        RxHttpUtils.createApi(ReturnGoodsApi.class)
                .deleteReturnGoods(returnsGoodsDetailsBean.getData().getId()+"", sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
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
                        }
                    }
                });
    }
}