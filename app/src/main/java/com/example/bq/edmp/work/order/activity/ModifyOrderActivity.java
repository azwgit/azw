package com.example.bq.edmp.work.order.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.FromtUtil;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.StringUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.order.adapter.GoodsGrayAdapter;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.OrderDetailsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.http.Field;
import retrofit2.http.Path;

/*
 * 修改订单
 * */
public class ModifyOrderActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.code_tv)
    TextView code_tv;
    @BindView(R.id.username_tv)
    TextView username_tv;
    @BindView(R.id.delete_tv)
    TextView delete_tv;
    @BindView(R.id.save_tv)
    TextView save_tv;
    @BindView(R.id.save_add_tv)
    TextView save_add_tv;
    @BindView(R.id.contactname_et)
    EditText contactname_et;
    @BindView(R.id.contact_phone_et)
    EditText contact_phone_et;
    @BindView(R.id.cargo_address_et)
    EditText cargo_address_et;
    @BindView(R.id.add_goods_rl)
    RelativeLayout add_goods_rl;
    @BindView(R.id.rv)
    RecyclerView recyclerView;


    private LoadingDialog loadingDialog;
    private ArrayList<OrderDetailsBean.DataBean.OrderItemsBean> dataBeans;
    private GoodsGrayAdapter goodsGrayAdapter;
    private String orderid;//订单id
    private String customerId;//客户id
    private Dialog mCameraDialog;
    private EditText xiaolaing_et;
    private EditText shiji_xiaoliang_et;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_order;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(ModifyOrderActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("修改订单");

        orderid = getIntent().getStringExtra("orderid");

        dataBeans = new ArrayList<>();
        goodsGrayAdapter = new GoodsGrayAdapter(dataBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(ModifyOrderActivity.this));
        recyclerView.setAdapter(goodsGrayAdapter);
        goodsGrayAdapter.setOnItemClickListener(new GoodsGrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int type, final int pos, final OrderDetailsBean.DataBean.OrderItemsBean orderItem) {
                //0 删除  1  修改
                if (type == 0) {//删除
                    gainDelet(orderItem);
                } else if (type == 1) {//修改
                    findContentViews(orderItem, pos);
                }
            }
        });

    }

    @Override
    protected void initData() {

        String sign = MD5Util.encode("id=" + orderid);

        RxHttpUtils.createApi(OrderApi.class)
                .getShow(orderid, sign)
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
                            List<OrderDetailsBean.DataBean.OrderItemsBean> orderItems = orderDetailsBean.getData().getOrderItems();
                            if (orderItems != null && orderItems.size() != 0) {
                                dataBeans.clear();
                                dataBeans.addAll(orderItems);
                                goodsGrayAdapter.notifyDataSetChanged();
                            } else {
                                dataBeans.clear();
                                goodsGrayAdapter.notifyDataSetChanged();
                            }
                        } else {
                            ToastUtil.setToast("信息有误，请重新请求");
                        }
                    }
                });
    }

    //客户信息赋值
    private void gainData(OrderDetailsBean.DataBean data) {
        code_tv.setText("订单号:" + data.getCode());
        username_tv.setText(data.getCustomerName());
        contactname_et.setText(data.getContacts());
        FromtUtil.setEditTextCursorLocation(contactname_et);
        contact_phone_et.setText(data.getMobTel());
        FromtUtil.setEditTextCursorLocation(contact_phone_et);
        cargo_address_et.setText(data.getAddress());
        FromtUtil.setEditTextCursorLocation(cargo_address_et);
        customerId = data.getCustomerId();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(ModifyOrderActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250) {
            initData();
        }
    }

    //判断方法
    public boolean Judge() {
        if (username_tv.equals("")) {
            ToastUtil.setToast("请输入客户名称");
            return false;
        }
        if (contactname_et.equals("")) {
            ToastUtil.setToast("请输入联系人");
            return false;
        }
        if (contact_phone_et.equals("")) {
            ToastUtil.setToast("请输入联系人电话号");
            return false;
        }
//         else if (!StringUtils.isPhone(contact_phone_et.toString())) {
//            ToastUtil.setToast("请输入正确电话号");
//            return false;
//        }
        if (cargo_address_et.equals("")) {
            ToastUtil.setToast("请输入送货地址");
            return false;
        }
        return true;
    }

    public boolean XiuJudge() {
        if (xiaolaing_et.getText().toString().equals("")) {
            ToastUtil.setToast("请输入销售量");
            return false;
        }
        if (shiji_xiaoliang_et.getText().toString().equals("")) {
            ToastUtil.setToast("请输入实际售价");
            return false;
        }
        return true;
    }

    @Override
    protected void initListener() {
        delete_tv.setOnClickListener(this);
        save_tv.setOnClickListener(this);
        save_add_tv.setOnClickListener(this);
        add_goods_rl.setOnClickListener(this);
        return_img.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.save_add_tv://保存并提交
                if (dataBeans.size() == 0) {
                    ToastUtil.setToast("请选择提交商品");
                    break;
                } else {
                    if (Judge()) {
                        gainSumit();
                    }
                }
                break;
            case R.id.save_tv://保存
                if (Judge()) {
                    gainSvet();
                }
                break;
            case R.id.delete_tv://删除
                OrderDelet();
                break;
            case R.id.add_goods_rl://添加商品
                Intent intent = new Intent(ModifyOrderActivity.this, GoodsListActivity.class);
                intent.putExtra("orderid", orderid);
                intent.putExtra("customerId", customerId);
                startActivityForResult(intent, 250);
                break;
        }
    }

    //删除商品
    private void gainDelet(OrderDetailsBean.DataBean.OrderItemsBean orderItem) {

        String sign = MD5Util.encode("ordersId=" + orderid + "&packagingId=" + orderItem.getId().getPackagingId());

        RxHttpUtils.createApi(OrderApi.class)
                .getDeleteShang(orderid, orderItem.getId().getPackagingId(), sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if (baseABean.getCode() == 200) {
                            initData();
                        } else {
                            ToastUtil.setToast("删除失败");
                        }
                    }
                });
    }

    //订单删除
    private void OrderDelet() {
        String sign = MD5Util.encode("id=" + orderid);

        RxHttpUtils.createApi(OrderApi.class)
                .getDelete(orderid, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if (baseABean.getCode() == 200) {
                            ToastUtil.setToast("订单删除成功");
                            finish();
                        } else {
                            ToastUtil.setToast("订单删除失败");
                        }
                    }
                });
    }

    //商品修改
    private void findContentViews(final OrderDetailsBean.DataBean.OrderItemsBean orderItem, final int pos) {
        mCameraDialog = new Dialog(ModifyOrderActivity.this, R.style.my_dialog);

        View root = LayoutInflater.from(ModifyOrderActivity.this).inflate(R.layout.goodslist_bu_buttom_item, null);

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
        mCameraDialog.show();

        final TextView pinzhong_tv = root.findViewById(R.id.pinzhong_tv);
        TextView danwei_tv = root.findViewById(R.id.danwei_tv);
        xiaolaing_et = root.findViewById(R.id.xiaolaing_et);
        shiji_xiaoliang_et = root.findViewById(R.id.shiji_xiaoliang_et);
        final TextView zonge_price_tv = root.findViewById(R.id.zonge_price_tv);
        final TextView cancel_tv = root.findViewById(R.id.cancel_tv);
        final TextView determine_tv = root.findViewById(R.id.determine_tv);

        zonge_price_tv.setText(FromtUtil.getFromt(orderItem.getSettlement()));
        pinzhong_tv.setText(orderItem.getPackagingName());
        danwei_tv.setText("¥"+FromtUtil.getFromt(orderItem.getPrice())+"/公斤");
        xiaolaing_et.setText(FromtUtil.getFromt(orderItem.getQty()));
        FromtUtil.setEditTextCursorLocation(xiaolaing_et);
        shiji_xiaoliang_et.setText(FromtUtil.getFromt(orderItem.getPrice()));
        FromtUtil.setEditTextCursorLocation(shiji_xiaoliang_et);



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
                    double v = xiaolaing * shijiaxiaolaing;
                    zonge_price_tv.setText(FromtUtil.getFromt(v));
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
                    double v = xiaolaing * shijiaxiaolaing;
                    zonge_price_tv.setText(FromtUtil.getFromt(v));
                } else {
                    zonge_price_tv.setText("0.00");
                }
            }
        });


        //确认
        determine_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (XiuJudge()) {
                    //商品修改
                    hideKeyboard(determine_tv);
                    gainEdit(orderItem, xiaolaing_et.getText().toString(), shiji_xiaoliang_et.getText().toString());
                }
            }
        });


        //取消
        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xiaolaing_et.setText("");
                shiji_xiaoliang_et.setText("");
                hideKeyboard(cancel_tv);
                mCameraDialog.dismiss();
            }
        });


    }

    //商品修改
    private void gainEdit(OrderDetailsBean.DataBean.OrderItemsBean orderItem, String xiaoliang, String shijixiao) {

        String sign = MD5Util.encode("ordersId=" + orderid
                + "&packagingId=" + orderItem.getId().getPackagingId() + "&price=" + shijixiao + "&qty=" + xiaoliang);

        RxHttpUtils.createApi(OrderApi.class)
                .getSaveShang(orderid, orderItem.getId().getPackagingId(), shijixiao, xiaoliang, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if (baseABean.getCode() == 200) {
                            ToastUtil.setToast("商品修改成功");
                            mCameraDialog.dismiss();
                            initData();
                        } else {
                            ToastUtil.setToast("商品修改失败");
                        }
                    }
                });
    }

    //订单提交
    private void gainSumit() {

        String sign = MD5Util.encode("address=" + cargo_address_et.getText().toString() + "&contacts=" + contactname_et.getText().toString()
                + "&customerId=" + customerId + "&id=" + orderid + "&mobTel=" + contact_phone_et.getText().toString());


        RxHttpUtils.createApi(OrderApi.class)
                .getSubmit(cargo_address_et.getText().toString(), contactname_et.getText().toString(), customerId, orderid, contact_phone_et.getText().toString(), sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if (baseABean.getCode() == 200) {
                            ToastUtil.setToast("订单提交成功");
                            finish();
                        } else {
                            ToastUtil.setToast("订单提交失败");
                        }
                    }
                });
    }

    //订单保存
    private void gainSvet() {

        String sign = MD5Util.encode("address=" + cargo_address_et.getText().toString() + "&contacts=" + contactname_et.getText().toString()
                + "&customerId=" + customerId + "&id=" + orderid + "&mobTel=" + contact_phone_et.getText().toString());

        RxHttpUtils.createApi(OrderApi.class)
                .getSave(cargo_address_et.getText().toString(), contactname_et.getText().toString(), customerId, orderid, contact_phone_et.getText().toString(), sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if (baseABean.getCode() == 200) {
                            ToastUtil.setToast("订单保存成功");
                            finish();
                        } else {
                            ToastUtil.setToast("订单保存失败");
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
