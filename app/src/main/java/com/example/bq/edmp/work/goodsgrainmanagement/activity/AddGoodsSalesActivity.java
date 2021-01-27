package com.example.bq.edmp.work.goodsgrainmanagement.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.GsonUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.FromtUtil;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.phoneUtils;
import com.example.bq.edmp.work.goodsgrainmanagement.api.GoodsSalesApi;
import com.example.bq.edmp.work.inventorytransfer.adapter.CompanyListAdp;
import com.example.bq.edmp.work.inventorytransfer.adapter.WarehouseListAdp;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.inventorytransfer.bean.SubsidiaryCompanyBean;
import com.example.bq.edmp.work.inventorytransfer.bean.WareHouseListBean;
import com.example.bq.edmp.work.marketing.api.CustomerManagementApi;
import com.example.bq.edmp.work.marketing.bean.CityBean;
import com.example.bq.edmp.work.marketing.bean.CityModel;
import com.example.bq.edmp.work.order.activity.ChooseCustomerActivity;
import com.example.bq.edmp.work.order.activity.ModifyOrderActivity;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.CustomerBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 新增订单
 * */
public class AddGoodsSalesActivity extends BaseTitleActivity {
    @BindView(R.id.tv_name)
    TextView mTvName;//客户姓名
    @BindView(R.id.choose_user_tv)
    TextView choose_user_tv;//选择客户
    @BindView(R.id.next_tv)
    TextView next_tv;//下一步
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
    @BindView(R.id.tv_distribution_area)
    TextView mTvDistributionArea;//销售区域
    private String regionId = "";//销售区域id
    private String customerId = "";//客户id
    private int CompanyId = 0;//公司id
    private int Warehouseid = 0;//仓库id
    PopupWindow mTypePopuWindow;
    private ILoadingView loading_dialog;
    private SubsidiaryCompanyBean subsidiaryCompanyBean;//公司数据源
    private WareHouseListBean wareHouseListBean;//仓库数据源

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_goods_sales;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("新增商品粮销售");
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        next_tv.setOnClickListener(this);
        choose_user_tv.setOnClickListener(this);
        mTvSubsidiaryCompany.setOnClickListener(this);
        mTvWarehouse.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.next_tv:
                checkData();
                break;
            case R.id.choose_user_tv://选择客户
                Intent intent1 = new Intent(AddGoodsSalesActivity.this, ChooseCustomerActivity.class);
                startActivityForResult(intent1, 250);
                break;
            case R.id.tv_subsidiary_company:
                getSubsidiaryCompanyList();
                break;
            case R.id.tv_warehouse:
                getWarehouseList();
                break;
        }
    }

    //下一步
    private void NextData(String address, String contacts, String mobTel) {
        String sign = MD5Util.encode("address=" + address + "&contacts=" + contacts
                + "&customerId=" + customerId + "&mobTel=" + mobTel + "&orgId=" + CompanyId + "&region=" + regionId + "&warehouseId=" + Warehouseid);
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .addGoodsSales(mEtAddress.getText().toString(), mEtContactName.getText().toString(), customerId, mEtPhone.getText().toString(), CompanyId + "", regionId + "", Warehouseid + "", sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if (baseABean.getCode() == 200) {
                            EditGoodsSalesActivity.newIntent(getApplicationContext(), baseABean.getData(), "1");
                            finish();
                        } else {
                            ToastUtil.setToast("提交失败，请重新提交");
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(AddGoodsSalesActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250 && resultCode == 350) {
            CustomerBean.DataBean.RowsBean rowsbean = (CustomerBean.DataBean.RowsBean) data.getSerializableExtra("rowsbean");
            if (rowsbean != null) {
                mTvName.setText(rowsbean.getName());
                mEtContactName.setText(rowsbean.getContacts());
                FromtUtil.setEditTextCursorLocation(mEtContactName);
                mEtPhone.setText(rowsbean.getMobTel());
                FromtUtil.setEditTextCursorLocation(mEtPhone);
//                mEtAddress.setText(rowsbean.getRegion());
//                FromtUtil.setEditTextCursorLocation(mEtAddress);
                customerId = rowsbean.getId();//客户id
                mTvDistributionArea.setText(rowsbean.getRegion());
                regionId = rowsbean.getRegionId();//区域id
            }
        }
    }

    public void checkData() {
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
        NextData(address, contactName, phone);
    }

    //公司列表
    private void getSubsidiaryCompanyList() {
        RxHttpUtils.createApi(GoodsSalesApi.class)
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
                            showSubsidiaryCompanyList();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //公司列表PopuWindow
    private void showSubsidiaryCompanyList() {
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
                mTvSubsidiaryCompany.setTextColor(getResources().getColor(R.color.color33));
//                mTvSubsidiaryCompany.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                mTvSubsidiaryCompany.setText(bean.getName());
                CompanyId = bean.getId();
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
    private void getWarehouseList() {
        if (CompanyId == 0) {
            ToastUtil.setToast("请选择公司");
            return;
        }
        String sign = MD5Util.encode("orgId=" + CompanyId + "&types=3");
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .getWarehouseList(CompanyId + "", "3", sign)
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
                            showWarehouseList();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //仓库列表PopuWindow
    private void showWarehouseList() {
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
                mTvWarehouse.setTextColor(getResources().getColor(R.color.color33));
//                    mTvTransferOutWarehouse.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                mTvWarehouse.setText(bean.getName());
                Warehouseid = bean.getId();
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

}
