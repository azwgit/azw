package com.example.bq.edmp.work.inventorytransfer.activity;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.grainmanagement.activity.WarehousingDetailAct;
import com.example.bq.edmp.work.grainmanagement.adapter.ContractorListAdp;
import com.example.bq.edmp.work.grainmanagement.api.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.bean.ContractorListBean;

import butterknife.BindView;

public class AddFinishedProductAllocationActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String type) {
        Intent intent = new Intent(context, AddFinishedProductAllocationActivity.class);
        intent.putExtra(Constant.TYPE, type);
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
    private String type="";
    private String id="";
    PopupWindow mTypePopuWindow;
    private ContractorListBean contractorListBean;//承包人数据源
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_finished_product_allocation;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("申请成品调拨");
        type=getIntent().getStringExtra(Constant.TYPE);
        if("".equals(type)){
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        if("2".equals(type)){
            //子公司不可选择公司
            mTvTransferOutCompany.setTextColor(getResources().getColor(R.color.color33));
            mTvTransferOutCompany.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            mTvTransferOutCompany.setEnabled(false);
            mTvTransferCompany.setTextColor(getResources().getColor(R.color.color33));
            mTvTransferCompany.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            mTvTransferCompany.setEnabled(false);
        }
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
        switch (view.getId()){
            case R.id.tv_submit:
                Intent intent=new Intent(getApplicationContext(),EditFinishedProductAllocationActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_transfer_out_company:
                //调出公司
                getContractorList();
                break;
            case R.id.tv_transfer_out_warehouse:
                //调出仓库
                getContractorList();
                break;
            case R.id.tv_transfer_company:
                //调入公司
                getContractorList();
                break;
            case R.id.tv_transfer_warehouse:
                //调入仓库
                getContractorList();
                break;
        }
    }
    //获取承包人列表
    private void getContractorList() {
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .getContractorList()
                .compose(Transformer.<ContractorListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<ContractorListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ContractorListBean bean) {
                        contractorListBean = bean;
                        showContractorList();
                    }
                });
    }
    //承包人列表PopuWindow
    private void showContractorList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        ContractorListAdp contractorListAdp = new ContractorListAdp(contractorListBean.getData());
        myRecyclerViewOne.setAdapter(contractorListAdp);
        contractorListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTvTransferOutWarehouse.setText(contractorListBean.getData().get(position).getName());
                mTvTransferOutWarehouse.setTextColor(getResources().getColor(R.color.color33));
                mTvTransferOutWarehouse.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
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