package com.example.bq.edmp.work.goodsgrainmanagement.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.bq.edmp.work.goodsgrainmanagement.api.GoodsSalesApi;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.EditGoodSalesBean;
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
    public static void newIntent(Context context, String id, String type) {
        Intent intent = new Intent(context, EditGoodsSalesActivity.class);
        intent.putExtra(Constant.ID, id);
        intent.putExtra(Constant.TYPE, type);
        context.startActivity(intent);
    }

    @BindView(R.id.delete_tv)
    TextView delete_tv;
    @BindView(R.id.save_tv)
    TextView save_tv;
    @BindView(R.id.save_add_tv)
    TextView save_add_tv;
    @BindView(R.id.tv_code)
    TextView mTvCode;//订单号
    @BindView(R.id.tv_name)
    TextView mTvName;//客户姓名
    @BindView(R.id.et_contactname)
    TextView mEtContactName;//联系人
    @BindView(R.id.et_phone)
    TextView mEtPhone;//联系电话
    @BindView(R.id.et_address)
    TextView mEtAddress;//送货地址
    @BindView(R.id.tv_subsidiary_company)
    TextView mTvSubsidiaryCompany;//分子公司
    @BindView(R.id.tv_warehouse)
    TextView mTvWarehouse;//仓库
    @BindView(R.id.add_goods_rl)
    RelativeLayout add_goods_rl;
    @BindView(R.id.tv_distribution_area)
    TextView mTvDistributionArea;//经销区域
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    private LoadingDialog loadingDialog;
    private ArrayList<OrderDetailsBean.DataBean.OrderItemsBean> dataBeans;
    private GoodsListAdp mAdapter;
    private String id = "";//订单id
    private String type = "1";//进入页面类型
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
        loadingDialog = new LoadingDialog(this);
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        id = getIntent().getStringExtra(Constant.ID);
        type = getIntent().getStringExtra(Constant.TYPE);
        if ("".equals(id) || "".equals(type)) {
            ToastUtil.setToast("数据错误");
            finish();
        }
        if ("1".equals(type)) {
            txtTabTitle.setText("新增商品粮销售");
        } else {
            txtTabTitle.setText("修改订单");
        }
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new GoodsListAdp(null);
        mRecyclerView.setAdapter(mAdapter);
        //删除
        mAdapter.setOnItemDelListener(new GoodsListAdp.OnItemDelListener() {
            @Override
            public void onItemDelClick(int position, EditGoodSalesBean.DataBean.CgOrderItemsBean bean) {
                deleteGoods(bean.getItemId() + "");
            }
        });

        //编辑
        mAdapter.setOnItemEditLisenter(new GoodsListAdp.OnItemEditLisenter() {
            @Override
            public void onItemEditClick(int pos, EditGoodSalesBean.DataBean.CgOrderItemsBean bean) {
                findContentViews(bean, 1);
            }
        });

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        getEditGoodsDetails();
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
                submitGoodsSales();
                break;
            case R.id.save_tv://保存
                finish();
                break;
            case R.id.delete_tv://删除
                deleteGoodsSales();
                break;
            case R.id.add_goods_rl://添加商品
                SelectGoodsListActivity.newIntent(getApplicationContext(), id);
                break;
        }
    }


    //商品修改
    private void findContentViews(final EditGoodSalesBean.DataBean.CgOrderItemsBean orderItem, final int pos) {
        mCameraDialog = new Dialog(EditGoodsSalesActivity.this, R.style.my_dialog);
        View root = LayoutInflater.from(EditGoodsSalesActivity.this).inflate(R.layout.select_goods_dialog, null);
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
        zonge_price_tv.setText(FromtUtil.getFromt(orderItem.getQty() * orderItem.getPrice()));
        pinzhong_tv.setText(orderItem.getItemName());
//        danwei_tv.setText("¥" + FromtUtil.getFromt(orderItem.getPrice()) + "/公斤");
        xiaolaing_et.setText(FromtUtil.getFromt(orderItem.getQty()));
        FromtUtil.setEditTextCursorLocation(xiaolaing_et);
        shiji_xiaoliang_et.setText(FromtUtil.getFromt(orderItem.getPrice()));
        FromtUtil.setEditTextCursorLocation(shiji_xiaoliang_et);
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
                    double v = xiaolaing * shijiaxiaolaing;
                    zonge_price_tv.setText(FromtUtil.getFromt(v));
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
                    double v = xiaolaing * shijiaxiaolaing;
                    zonge_price_tv.setText(FromtUtil.getFromt(v));
                } else {
                    zonge_price_tv.setText("0.00");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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

    //商品修改接口调用
    private void gainEdit(EditGoodSalesBean.DataBean.CgOrderItemsBean orderItem, String xiaoliang, String shijixiao) {
        String sign = MD5Util.encode("cgOrderId=" + id
                + "&itemId=" + orderItem.getItemId() + "&price=" + shijixiao + "&qty=" + xiaoliang);
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .updataGoods(id, orderItem.getItemId() + "", shijixiao, xiaoliang, sign)
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
                            getEditGoodsDetails();
                        } else {
                            ToastUtil.setToast("商品修改失败");
                        }
                    }
                });
    }

    //订单提交
    private void submitGoodsSales() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .submitGoodsSales(id, sign)
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
                            ToastUtil.setToast(baseABean.getMsg());
                        }
                    }
                });
    }


    //商品粮销售删除
    private void deleteGoodsSales() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .deleteGoodsSales(id, sign)
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
        String sign = MD5Util.encode("cgOrderId=" + id + "&itemId=" + inItemId);
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .deleteGoods(id, inItemId, sign)
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
                            getEditGoodsDetails();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //新增商品粮详情
    private void getEditGoodsDetails() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .getGoodsDetails(id, sign)
                .compose(Transformer.<EditGoodSalesBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<EditGoodSalesBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(EditGoodSalesBean bean) {
                        if (bean.getCode() == 200) {
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            finish();
                        }
                    }
                });
    }

    //详情赋值
    private void setData(EditGoodSalesBean.DataBean bean) {
        mTvCode.setText("订单号：" + bean.getCode());
        mTvName.setText(bean.getCustomerName());
        mEtContactName.setText(bean.getContacts());
        mEtPhone.setText(bean.getMobTel());
        mEtAddress.setText(bean.getAddress());
        mTvSubsidiaryCompany.setText(bean.getOrgName());
        mTvWarehouse.setText(bean.getWarehouseName());
        mTvDistributionArea.setText(bean.getRegion());
        if (bean.getCgOrderItems() != null) {
            mAdapter.setNewData(bean.getCgOrderItems());
        }
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
