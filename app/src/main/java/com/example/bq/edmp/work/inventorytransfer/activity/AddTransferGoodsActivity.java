package com.example.bq.edmp.work.inventorytransfer.activity;

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
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.inventorytransfer.adapter.AllListPackageAdp;
import com.example.bq.edmp.work.inventorytransfer.adapter.VarietiesAdp;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.inventorytransfer.bean.AllpackageListBean;
import com.example.bq.edmp.work.inventorytransfer.bean.VarittiesListBean;

import butterknife.BindView;

public class AddTransferGoodsActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String type, String id) {
        Intent intent = new Intent(context, AddTransferGoodsActivity.class);
        intent.putExtra(Constant.TYPE, type);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.btn_ok)
    TextView mBtnOk;
    @BindView(R.id.btn_del)
    TextView mBtnDel;
    @BindView(R.id.tv_transfer_number)
    TextView mTvTransferNumber;
    @BindView(R.id.ed_number)
    EditText mEdNumber;

    private ILoadingView loading_dialog;
    private AllpackageListBean allpackageListBean;
    private VarittiesListBean varittiesListBean;
    PopupWindow mTypePopuWindow;
    private String type = "";
    private String id = "";
    private int allpackageId = 0;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBtnOk.setOnClickListener(this);
        mBtnDel.setOnClickListener(this);
        mTvTransferNumber.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("添加调拨商品");
        type = getIntent().getStringExtra(Constant.TYPE);
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(type) || "".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_transfer_goods;
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
            case R.id.tv_transfer_number:
                //1原粮进入 2成品进入
                if ("1".equals(type)) {
                    getVarietiesList();
                } else {
                    getAllpackageList();
                }
                break;
        }

    }

    //获取所有包装列表
    private void getAllpackageList() {
        RxHttpUtils.createApi(AllocationApi.class)
                .getAllpackageList()
                .compose(Transformer.<AllpackageListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<AllpackageListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AllpackageListBean bean) {
                        if (bean.getCode() == 200) {
                            allpackageListBean = bean;
                            showAllpackageList();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }
    //包裝列表PopuWindow
    private void showAllpackageList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        AllListPackageAdp allListPackageAdp = new AllListPackageAdp(allpackageListBean.getData());
        myRecyclerViewOne.setAdapter(allListPackageAdp);
        allListPackageAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AllpackageListBean.DataBean bean = allpackageListBean.getData().get(position);
                mTvTransferNumber.setTextColor(getResources().getColor(R.color.color33));
                mTvTransferNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                mTvTransferNumber.setText(bean.getVarietyPackagingName());
                allpackageId = allpackageListBean.getData().get(position).getId();
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
    //获取所有包装列表
    private void getVarietiesList() {
        RxHttpUtils.createApi(AllocationApi.class)
                .getVarietiesList()
                .compose(Transformer.<VarittiesListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<VarittiesListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(VarittiesListBean bean) {
                        if (bean.getCode() == 200) {
                            varittiesListBean = bean;
                            showVarietiesList();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }
    //品种列表PopuWindow
    private void showVarietiesList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        VarietiesAdp varietiesAdp = new VarietiesAdp(varittiesListBean.getData());
        myRecyclerViewOne.setAdapter(varietiesAdp);
        varietiesAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VarittiesListBean.DataBean bean = varittiesListBean.getData().get(position);
                mTvTransferNumber.setTextColor(getResources().getColor(R.color.color33));
                mTvTransferNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                mTvTransferNumber.setText(bean.getName());
                allpackageId = varittiesListBean.getData().get(position).getId();
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
    private void checkData(){
        if(allpackageId==0){
            ToastUtil.setToast("请选择商品");
            return;
        }
        String number=mEdNumber.getText().toString().trim();
        if("".equals(number)){
            ToastUtil.setToast("请输入调拨数量");
            return;
        }
        addTransferGoods(number);
    }
    //添加调拨商品
    private void addTransferGoods(String number) {
        String sign = MD5Util.encode("inItemId=" + allpackageId + "&qty=" + number+ "&stockAllotId=" + id);
        RxHttpUtils.createApi(AllocationApi.class)
                .addTransferGoods(allpackageId+"",number,id,sign)
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
}