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
import com.example.bq.edmp.work.inventorytransfer.adapter.CompanyListAdp;
import com.example.bq.edmp.work.inventorytransfer.adapter.WarehouseListAdp;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.inventorytransfer.bean.SubsidiaryCompanyBean;
import com.example.bq.edmp.work.inventorytransfer.bean.WareHouseListBean;

import butterknife.BindView;

public class AddFinishedProductAllocationActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String type,String id) {
        Intent intent = new Intent(context, AddFinishedProductAllocationActivity.class);
        intent.putExtra(Constant.TYPE, type);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.ed_content)
    EditText mEdContent;//调拨原因
    @BindView(R.id.tv_transfer_company)
    TextView mTvTransferCompany;//调入公司
    @BindView(R.id.tv_transfer_out_company)
    TextView mTvTransferOutCompany;//调出公司
    @BindView(R.id.tv_transfer_out_warehouse)
    TextView mTvTransferOutWarehouse;//调出仓库
    @BindView(R.id.tv_transfer_warehouse)
    TextView mTvTransferWarehouse;//调入仓库
    private ILoadingView loading_dialog;
    private String type = "";//1原粮进入 2成品进入
    private String id = "";
    PopupWindow mTypePopuWindow;
    private SubsidiaryCompanyBean subsidiaryCompanyBean;//公司数据源
    private WareHouseListBean wareHouseListBean;//仓库数据源
    private int transferOutCompanyId = 0;//调出公司id
    private int transferCompanyId = 0;//调入公司id
    private int transferOutWarehouseid=0;//调出仓库id
    private int transferWarehouseid=0;//调入仓库id
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_finished_product_allocation;
    }

    @Override
    protected void initView() {
        type = getIntent().getStringExtra(Constant.TYPE);
        id= getIntent().getStringExtra(Constant.ID);
        if ("".equals(type)|| "".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        if ("1".equals(type)) {
            txtTabTitle.setText("申请原粮调拨");
        } else {
            txtTabTitle.setText("申请成品调拨");
        }
        type = "2";
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTvSubmit.setOnClickListener(this);
        mTvTransferOutWarehouse.setOnClickListener(this);
        mTvTransferCompany.setOnClickListener(this);
        mTvTransferOutCompany.setOnClickListener(this);
        mTvTransferWarehouse.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
//                checkData();
                EditFinishedProductAllocationActivity.newIntent(getApplicationContext(),type,"3");
                break;
            case R.id.tv_transfer_out_company:
                //调出公司
                getSubsidiaryCompanyList(0);
                break;
            case R.id.tv_transfer_out_warehouse:
                //调出仓库
                getWarehouseList(0, transferOutCompanyId + "");
                break;
            case R.id.tv_transfer_company:
                //调入公司
                getSubsidiaryCompanyList(1);
                break;
            case R.id.tv_transfer_warehouse:
                //调入仓库
                getWarehouseList(1, transferCompanyId + "");
                break;
        }
    }

    //获取仓库列表
    private void getWarehouseList(final int clicktype, String orgId) {
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
        RxHttpUtils.createApi(AllocationApi.class)
                .getWarehouseList(orgId, type, sign)
                .compose(Transformer.<WareHouseListBean>switchSchedulers(loading_dialog))
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
                WareHouseListBean.DataBean bean=wareHouseListBean.getData().get(position);
                //0调出仓库 1调入仓库
                if (clickType == 0) {
                    mTvTransferOutWarehouse.setTextColor(getResources().getColor(R.color.color33));
                    mTvTransferOutWarehouse.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    mTvTransferOutWarehouse.setText(bean.getName());
                    transferOutWarehouseid=bean.getId();
                } else {
                    mTvTransferWarehouse.setTextColor(getResources().getColor(R.color.color33));
                    mTvTransferWarehouse.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    mTvTransferWarehouse.setText(bean.getName());
                    transferWarehouseid=bean.getId();
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

    //获取当前用户是 总公司活子公司信息
    private void getSubsidiaryCompanyList(final int type) {
        RxHttpUtils.createApi(AllocationApi.class)
                .getSubsidiaryCompanyList()
                .compose(Transformer.<SubsidiaryCompanyBean>switchSchedulers(loading_dialog))
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
                    mTvTransferOutCompany.setTextColor(getResources().getColor(R.color.color33));
                    mTvTransferOutCompany.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    mTvTransferOutCompany.setText(bean.getName());
                    transferOutCompanyId = bean.getId();
                } else {
                    mTvTransferCompany.setTextColor(getResources().getColor(R.color.color33));
                    mTvTransferCompany.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    mTvTransferCompany.setText(bean.getName());
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
    //验证提交数据
    private void checkData(){
        if(transferOutCompanyId==0){
            ToastUtil.setToast("请选择调出公司");
            return;
        }
        if(transferOutWarehouseid==0){
            ToastUtil.setToast("请选择调出仓库");
            return;
        }
        if(transferCompanyId==0){
            ToastUtil.setToast("请选择调入公司");
            return;
        }
        if(transferWarehouseid==0){
            ToastUtil.setToast("请选择调入仓库");
            return;
        }
        String content=mEdContent.getText().toString().trim();
        if("".equals(content)){
            ToastUtil.setToast("请输入调拨原因");
            return;
        }
        addAllot();
    }
    //申请调拨
    private void addAllot() {
        String sign = MD5Util.encode("inOrgId=" + transferCompanyId + "&inWarehouse=" + transferWarehouseid+ "&outOrgId=" + transferOutCompanyId+ "&outWarehouse=" + transferOutWarehouseid+ "&reason=" + mEdContent.getText().toString().trim()+"&types=" + type);
        RxHttpUtils.createApi(AllocationApi.class)
                .addAllot(transferCompanyId+"",transferWarehouseid+"",transferOutCompanyId+"",transferOutWarehouseid+"",mEdContent.getText().toString().trim(),type,sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }
                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            EditFinishedProductAllocationActivity.newIntent(getApplicationContext(),type,bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }
}