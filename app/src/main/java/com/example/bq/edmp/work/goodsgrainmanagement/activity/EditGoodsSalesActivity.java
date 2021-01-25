package com.example.bq.edmp.work.goodsgrainmanagement.activity;

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
import com.allen.library.interfaces.ILoadingView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.FromtUtil;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.phoneUtils;
import com.example.bq.edmp.work.goodsgrainmanagement.adapter.GoodsListAdp;
import com.example.bq.edmp.work.inventorytransfer.activity.UpdateTransferGoodsActivity;
import com.example.bq.edmp.work.inventorytransfer.adapter.CommodityListAdp;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;
import com.example.bq.edmp.work.order.activity.GoodsListActivity;
import com.example.bq.edmp.work.order.adapter.GoodsGrayAdapter;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.OrderDetailsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 修改订单
 * */
public class EditGoodsSalesActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id ) {
        Intent intent = new Intent(context, EditGoodsSalesActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.delete_tv)
    TextView delete_tv;
    @BindView(R.id.save_tv)
    TextView save_tv;
    @BindView(R.id.save_add_tv)
    TextView save_add_tv;
    @BindView(R.id.tv_name)
    TextView mTvName;//客户姓名
    @BindView(R.id.et_contactname)
    EditText mEtContactName;//联系人
    @BindView(R.id.et_phone)
    EditText mEtPhone;//联系电话
    @BindView(R.id.et_address)
    EditText mEtAddress;//送货地址
    @BindView(R.id.tv_subsidiary_company)
    TextView mTvSubsidiaryCompany;//分子公司
    @BindView(R.id.tv_warehouse)
    TextView mTvWarehouse;//仓库
    @BindView(R.id.add_goods_rl)
    RelativeLayout add_goods_rl;
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    private LoadingDialog loadingDialog;
    private ArrayList<OrderDetailsBean.DataBean.OrderItemsBean> dataBeans;
    private GoodsListAdp mAdapter;
    private String id = "208";//订单id
    private String customerId;//客户id
    private Dialog mCameraDialog;
    private EditText xiaolaing_et;
    private EditText shiji_xiaoliang_et;
    private int CompanyId = 0;//公司id
    private int Warehouseid = 0;//仓库id
    private ILoadingView loading_dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_goods_sales;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("修改订单");
        loadingDialog = new LoadingDialog(this);
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        id = getIntent().getStringExtra("orderid");
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new GoodsListAdp(null);
        mRecyclerView.setAdapter(mAdapter);
        //删除
        mAdapter.setOnItemDelListener(new GoodsListAdp.OnItemDelListener() {
            @Override
            public void onItemDelClick(int position, EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean bean) {
                deleteGoods(bean.getInItemId() + "");
            }
        });

        //编辑
        mAdapter.setOnItemEditLisenter(new GoodsListAdp.OnItemEditLisenter() {
            @Override
            public void onItemEditClick(int pos, EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean bean) {
                findContentViews(null, 1);
            }
        });

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        getProudctAllocationDetails();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(EditGoodsSalesActivity.this);
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
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.save_add_tv://保存并提交
                checkData(1);
                break;
            case R.id.save_tv://保存
                break;
            case R.id.delete_tv://删除
                deleteGoods("1");
                break;
            case R.id.add_goods_rl://添加商品
                startActivity(new Intent(getApplicationContext(), SelectGoodsListActivity.class));
                break;
        }
    }


    //商品修改
    private void findContentViews(final OrderDetailsBean.DataBean.OrderItemsBean orderItem, final int pos) {
        mCameraDialog = new Dialog(EditGoodsSalesActivity.this, R.style.my_dialog);

        View root = LayoutInflater.from(EditGoodsSalesActivity.this).inflate(R.layout.goodslist_bu_buttom_item, null);

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
        danwei_tv.setText("¥" + FromtUtil.getFromt(orderItem.getPrice()) + "/公斤");
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

    public void checkData(int type) {
        String name = mTvName.getText().toString().trim();
        if ("".equals(name)) {
            ToastUtil.setToast("请选择客户");
            return;
        }
        String contactName = mEtContactName.getText().toString().trim();
        if ("".equals(contactName)) {
            ToastUtil.setToast("请输入联系人");
            return;
        }
        String phone = mEtPhone.getText().toString().trim();
        if ("".equals(phone)) {
            ToastUtil.setToast("请输入联系人电话号");
            return;
        }
        String address = mEtAddress.getText().toString().trim();
        if ("".equals(address)) {
            ToastUtil.setToast("请输入送货地址");
            return;
        }
        if (CompanyId == 0) {
            ToastUtil.setToast("请选择公司");
            return;
        }
        if (Warehouseid == 0) {
            ToastUtil.setToast("请选择仓库");
            return;
        }
        if (!phoneUtils.isMobileNO(mEtPhone.getText().toString())) {
            ToastUtil.setToast("请输入正确的手机号");
            return;
        }
    }

    //商品修改
    private void gainEdit(OrderDetailsBean.DataBean.OrderItemsBean orderItem, String xiaoliang, String shijixiao) {

        String sign = MD5Util.encode("ordersId=" + id
                + "&packagingId=" + orderItem.getId().getPackagingId() + "&price=" + shijixiao + "&qty=" + xiaoliang);

        RxHttpUtils.createApi(OrderApi.class)
                .getSaveShang(id, orderItem.getId().getPackagingId(), shijixiao, xiaoliang, sign)
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
                            getProudctAllocationDetails();
                        } else {
                            ToastUtil.setToast("商品修改失败");
                        }
                    }
                });
    }

    //订单提交
    private void gainSumit() {
        String sign = MD5Util.encode("address=");
        RxHttpUtils.createApi(OrderApi.class)
                .getSubmit("", "", customerId, id, "", sign)
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
        String sign = MD5Util.encode("address=");
        RxHttpUtils.createApi(OrderApi.class)
                .getSave("", "", customerId, id, "", sign)
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

    //删除调拨
    private void deleteAllot() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(AllocationApi.class)
                .deleteAllot(id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
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

    //刪除调拨商品
    private void deleteGoods(String inItemId) {
        String sign = MD5Util.encode("inItemId=" + inItemId + "&stockAllotId=" + id);
        RxHttpUtils.createApi(AllocationApi.class)
                .deleteGoods(inItemId, id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
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
        String sign = MD5Util.encode("id=" + 208);
        RxHttpUtils.createApi(AllocationApi.class)
                .getProudctAllocationDetails("208", sign)
                .compose(Transformer.<EditFinishedProductAllocationBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<EditFinishedProductAllocationBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(EditFinishedProductAllocationBean bean) {
                        if (bean.getCode() == 200) {
                            mAdapter.setNewData(bean.getData().getStockAllotItems());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
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
