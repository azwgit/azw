package com.example.bq.edmp.work.materialmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.inventorytransfer.adapter.AllNewListPackageAdp;
import com.example.bq.edmp.work.inventorytransfer.adapter.VarietiesAdp;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.inventorytransfer.bean.AllpackageListBean;
import com.example.bq.edmp.work.inventorytransfer.bean.VarittiesListBean;
import com.example.bq.edmp.work.materialmanagement.adapter.AllPurchaseGoodsAdp;
import com.example.bq.edmp.work.materialmanagement.api.MaterialManagementApi;
import com.example.bq.edmp.work.materialmanagement.bean.AddPurchaseGoodsBean;
import com.example.bq.edmp.work.materialmanagement.bean.EditMaterialBean;
import com.example.bq.edmp.work.materialmanagement.bean.QueryAllitemListBean;
import com.luck.picture.lib.entity.LocalMedia;

import butterknife.BindView;

public class AddPurchaseGoodsActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String type, String id, String code) {
        Intent intent = new Intent(context, AddPurchaseGoodsActivity.class);
        intent.putExtra(Constant.TYPE, type);
        intent.putExtra(Constant.ID, id);
        intent.putExtra(Constant.CODE, code);
        context.startActivity(intent);
    }

    @BindView(R.id.btn_ok)
    TextView mBtnOk;
    @BindView(R.id.btn_del)
    TextView mBtnDel;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.ed_number)
    EditText mEdNumber;
    @BindView(R.id.ed_price)
    EditText mEdPrice;

    private ILoadingView loading_dialog;
    private QueryAllitemListBean allpackageListBean;
    private VarittiesListBean varittiesListBean;
    PopupWindow mTypePopuWindow;
    private String type = "";
    private String id = "";
    private String itemId = "";
    private int purchaseGoodsId = -99;
    private AddPurchaseGoodsBean addPurchaseGoodsBean = null;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBtnOk.setOnClickListener(this);
        mBtnDel.setOnClickListener(this);
        mTvName.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        type = getIntent().getStringExtra(Constant.TYPE);
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(type) || "".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        if ("1".equals(type)) {
            txtTabTitle.setText("添加采购商品");
        } else {
            txtTabTitle.setText("修改采购商品");
            itemId = getIntent().getStringExtra(Constant.CODE);
            mTvName.setTextColor(getResources().getColor(R.color.color33));
            mTvName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            getPurchaseGoodsDetails();
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_purchase_goods;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                checkData();
                break;
            case R.id.btn_del:
                finish();
                break;
            case R.id.tv_name:
                if ("2".equals(type)) {
                    return;
                }
                getQueryAllitem(4);
                break;
        }

    }

    //获取所有物料列表
    private void getQueryAllitem(int categoryFullId) {
        String sign = MD5Util.encode("categoryFullId=" + categoryFullId);
        RxHttpUtils.createApi(MaterialManagementApi.class)
                .getQueryAllitem(categoryFullId + "", sign)
                .compose(Transformer.<QueryAllitemListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<QueryAllitemListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(QueryAllitemListBean bean) {
                        if (bean.getCode() == 200) {
                            allpackageListBean = bean;
                            showAllpackageList();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //物料列表PopuWindow
    private void showAllpackageList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        AllPurchaseGoodsAdp allListPackageAdp = new AllPurchaseGoodsAdp(allpackageListBean.getData());
        myRecyclerViewOne.setAdapter(allListPackageAdp);
        allListPackageAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                QueryAllitemListBean.DataBean bean = allpackageListBean.getData().get(position);
                mTvName.setTextColor(getResources().getColor(R.color.color33));
                mTvName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                mTvName.setText(bean.getName());
                purchaseGoodsId = allpackageListBean.getData().get(position).getId();
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

    //验证是否选择商品和添加重量
    private void checkData() {
        if (purchaseGoodsId == -99) {
            ToastUtil.setToast("请选择采购物料");
            return;
        }
        String number = mEdNumber.getText().toString().trim();
        if ("".equals(number)) {
            ToastUtil.setToast("请输入采购数量");
            return;
        }
        String price = mEdPrice.getText().toString().trim();
        if ("".equals(number)) {
            ToastUtil.setToast("请输入采购价格");
            return;
        }
        //1添加 2修改
        if ("1".equals(type)) {
            addTransferGoods(price, number);
        } else {
            updataPurchaseGoods(price, number);
        }

    }

    //添加采购商品
    private void addTransferGoods(String price, String number) {
        String sign = MD5Util.encode("itemId=" + purchaseGoodsId + "&materialPurchaseId=" + id + "&price=" + price + "&qty=" + number);
        RxHttpUtils.createApi(MaterialManagementApi.class)
                .addPurchaseGoods(purchaseGoodsId + "", id, price, number, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
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

    //修改采购商品
    private void updataPurchaseGoods(String price, String number) {
        String sign = MD5Util.encode("itemId=" + purchaseGoodsId + "&materialPurchaseId=" + id + "&price=" + price + "&qty=" + number);
        RxHttpUtils.createApi(MaterialManagementApi.class)
                .updataPurchaseGoods(purchaseGoodsId + "", id, price, number, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("修改成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //获取采购详情
    private void getPurchaseGoodsDetails() {
        String sign = MD5Util.encode("itemId=" + itemId + "&materialPurchaseId=" + id);
        RxHttpUtils.createApi(MaterialManagementApi.class)
                .getPurchaseGoodsDetails(itemId, id, sign)
                .compose(Transformer.<AddPurchaseGoodsBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<AddPurchaseGoodsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AddPurchaseGoodsBean bean) {
                        addPurchaseGoodsBean = bean;
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
    private void setData(AddPurchaseGoodsBean.DataBean bean) {
        purchaseGoodsId = bean.getId().getItemId();
        mTvName.setText(bean.getItemName());
        mEdNumber.setText(bean.getQty() + "");
        mEdPrice.setText(bean.getPrice() + "");
    }
}