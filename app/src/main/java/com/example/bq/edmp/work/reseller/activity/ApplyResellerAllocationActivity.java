package com.example.bq.edmp.work.reseller.activity;

import android.content.Intent;
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
import com.example.bq.edmp.work.inventorytransfer.activity.EditFinishedProductAllocationActivity;
import com.example.bq.edmp.work.inventorytransfer.adapter.CompanyListAdp;
import com.example.bq.edmp.work.inventorytransfer.adapter.WarehouseListAdp;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.inventorytransfer.bean.SubsidiaryCompanyBean;
import com.example.bq.edmp.work.inventorytransfer.bean.WareHouseListBean;
import com.example.bq.edmp.work.reseller.api.ResellerApi;

import butterknife.BindView;

/*
 * 申请转商调拨
 * */
public class ApplyResellerAllocationActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.reason_et)
    EditText reason_et;
    @BindView(R.id.allcation_chu_compy_tv)
    TextView allcation_chu_compy_tv;
    @BindView(R.id.allcation_ru_compy_tv)
    TextView allcation_ru_compy_tv;
    @BindView(R.id.allcation_chu_ku_tv)
    TextView allcation_chu_ku_tv;
    @BindView(R.id.allcation_ru_ku_tv)
    TextView allcation_ru_ku_tv;
    @BindView(R.id.next_tv)
    TextView next_tv;

    PopupWindow mTypePopuWindow;
    private LoadingDialog loadingDialog;
    private SubsidiaryCompanyBean subsidiaryCompanyBean;//公司数据源
    private WareHouseListBean wareHouseListBean;//仓库数据源
    private int transferOutCompanyId = 0;//调出公司id
    private int transferCompanyId = 0;//调入公司id
    private int transferOutWarehouseid = 0;//调出仓库id
    private int transferWarehouseid = 0;//调入仓库id

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_reseller_allocation;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(ApplyResellerAllocationActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("申请转商调拨");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        next_tv.setOnClickListener(this);
        allcation_chu_ku_tv.setOnClickListener(this);
        allcation_ru_ku_tv.setOnClickListener(this);
        allcation_chu_compy_tv.setOnClickListener(this);
        allcation_ru_compy_tv.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.allcation_chu_ku_tv://出库
                //调出仓库
                getWarehouseList(0, transferOutCompanyId + "", 2 + "");
                break;
            case R.id.allcation_ru_ku_tv://入库
                //调入仓库
                getWarehouseList(1, transferCompanyId + "", 3 + "");
                break;
            case R.id.allcation_chu_compy_tv://出库公司
                getSubsidiaryCompanyList(0);
                break;
            case R.id.allcation_ru_compy_tv://入库公司
                getSubsidiaryCompanyList(1);
                break;
            case R.id.next_tv://下一步
                String reason = reason_et.getText().toString().trim();
                if (transferOutCompanyId == 0) {
                    ToastUtil.setToast("请选择调出公司");
                    return;
                }
                if (transferOutWarehouseid == 0) {
                    ToastUtil.setToast("请选择调出仓库");
                    return;
                }
                if (transferCompanyId == 0) {
                    ToastUtil.setToast("请选择调入公司");
                    return;
                }
                if (transferWarehouseid == 0) {
                    ToastUtil.setToast("请选择调入仓库");
                    return;
                }
                if ("".equals(reason)) {
                    ToastUtil.setToast("请输入调拨原因");
                    return;
                }
                addAllot();
                break;
        }
    }

    //申请调拨
    private void addAllot() {
        String sign = MD5Util.encode("inOrgId=" + transferCompanyId + "&inWarehouse=" + transferWarehouseid + "&outOrgId=" + transferOutCompanyId + "&outWarehouse=" + transferOutWarehouseid + "&reason=" + reason_et.getText().toString().trim() + "&types=" + 3);
        RxHttpUtils.createApi(ResellerApi.class)
                .addAllot(transferCompanyId + "", transferWarehouseid + "", transferOutCompanyId + "", transferOutWarehouseid + "", reason_et.getText().toString().trim(), 3 + "", sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast(bean.getMsg());
                            Intent intent = new Intent(ApplyResellerAllocationActivity.this, ApplyResellerAllocationTwoActivity.class);
                            intent.putExtra("id",bean.getData());
                            startActivity(intent);
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //获取当前用户是 总公司活子公司信息
    private void getSubsidiaryCompanyList(final int type) {
        RxHttpUtils.createApi(ResellerApi.class)
                .getSubsidiaryCompanyList()
                .compose(Transformer.<SubsidiaryCompanyBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<SubsidiaryCompanyBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SubsidiaryCompanyBean bean) {
                        subsidiaryCompanyBean = bean;
                        if (bean.getCode() == 200) {
                            showSubsidiaryCompanyList(type);
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //公司列表PopuWindow
    private void showSubsidiaryCompanyList(final int type) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        CompanyListAdp companyListAdp = new CompanyListAdp(subsidiaryCompanyBean.getData());
        myRecyclerViewOne.setAdapter(companyListAdp);
        companyListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SubsidiaryCompanyBean.DataBean bean = subsidiaryCompanyBean.getData().get(position);
                if (type == 0) {
                    allcation_chu_compy_tv.setTextColor(getResources().getColor(R.color.color33));
                    allcation_chu_compy_tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    allcation_chu_compy_tv.setText(bean.getName());
                    transferOutCompanyId = bean.getId();
                } else {
                    allcation_ru_compy_tv.setTextColor(getResources().getColor(R.color.color33));
                    allcation_ru_compy_tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    allcation_ru_compy_tv.setText(bean.getName());
                    transferCompanyId = bean.getId();
                }
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

    //获取仓库列表
    private void getWarehouseList(final int clicktype, String orgId, String type) {
        if (clicktype == 0) {
            if (transferOutCompanyId == 0) {
                ToastUtil.setToast("请选择调出公司");
                return;
            }
        } else {
            if (transferCompanyId == 0) {
                ToastUtil.setToast("请选择调入公司");
                return;
            }
        }
        String sign = MD5Util.encode("orgId=" + orgId + "&types=" + type);
        RxHttpUtils.createApi(ResellerApi.class)
                .getWarehouseList(orgId, type, sign)
                .compose(Transformer.<WareHouseListBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<WareHouseListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(WareHouseListBean bean) {
                        wareHouseListBean = bean;
                        if (bean.getCode() == 200) {
                            showWarehouseList(clicktype);
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //仓库列表PopuWindow
    private void showWarehouseList(final int clickType) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        WarehouseListAdp warehouseListAdp = new WarehouseListAdp(wareHouseListBean.getData());
        myRecyclerViewOne.setAdapter(warehouseListAdp);
        warehouseListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WareHouseListBean.DataBean bean = wareHouseListBean.getData().get(position);
                //0调出仓库 1调入仓库
                if (clickType == 0) {
                    allcation_chu_ku_tv.setTextColor(getResources().getColor(R.color.color33));
                    allcation_chu_ku_tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    allcation_chu_ku_tv.setText(bean.getName());
                    transferOutWarehouseid = bean.getId();
                } else {
                    allcation_ru_ku_tv.setTextColor(getResources().getColor(R.color.color33));
                    allcation_ru_ku_tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    allcation_ru_ku_tv.setText(bean.getName());
                    transferWarehouseid = bean.getId();
                }
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(ApplyResellerAllocationActivity.this);
    }
}