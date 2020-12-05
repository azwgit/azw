package com.example.bq.edmp.work.finishedproduct.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.DialoggerFail;
import com.example.bq.edmp.utils.DialoggerOk;
import com.example.bq.edmp.utils.GridItemDecoration;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.UsualDialogger;
import com.example.bq.edmp.work.finishedproduct.adapter.MachiningTaskDetailAdp;
import com.example.bq.edmp.work.grainmanagement.adapter.ContractorListAdp;
import com.example.bq.edmp.work.grainmanagement.adapter.StockDetailAdp;
import com.example.bq.edmp.work.grainmanagement.api.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.bean.ContractorListBean;
import com.example.bq.edmp.work.grainmanagement.bean.StockDetailBean;

import butterknife.BindView;

public class MachiningTaskDetailsActivity extends BaseTitleActivity {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_contractor)
    TextView tv_button;
    private TextView tvWarehouse;//加工上报选择仓库
    private DialoggerOk dialogOK = null;
    private DialoggerFail dialogFail = null;
    private UsualDialogger dialog = null;
    PopupWindow mTypePopuWindow;
    private MachiningTaskDetailAdp mAdapter;
    private ILoadingView loading_dialog;
    private ContractorListBean contractorListBean;//承包人数据源
    @Override
    protected int getLayoutId() {
        return R.layout.activity_machining_task_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("加工单详情");
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MachiningTaskDetailAdp();
        mRecyclerView.setAdapter(mAdapter);
        getStockDetail();

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {
        tv_button.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_contractor:
                showMachiningTaskReport();
                break;
        }
    }

    //加工上报PopuWindow
    private void showMachiningTaskReport() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.machining_report, null);
        RelativeLayout mLyView = contentView.findViewById(R.id.ly_view);
        tvWarehouse= contentView.findViewById(R.id.tv_warehouse);
        tvWarehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContractorList();
            }
        });
//        mLyView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTypePopuWindow.dismiss();
//            }
//        });
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

    //确认成功
    public void showOkDialog() {
        dialogOK = DialoggerOk.Builder(this)
                .setTitle("确认成功")
                .setMessage("")
                .build()
                .shown();
    }

    //确认失败
    public void showFailDialog() {
        dialogFail = DialoggerFail.Builder(this)
                .setTitle("确认失败")
                .setMessage("")
                .build()
                .shown();
    }

    //刪除提示dialog
    public void showDeleteDialog() {
        dialog = UsualDialogger.Builder(this)
                .setTitle("友情提示")
                .setMessage("确认要中止该计划单的加工计划任务吗？确认后任务单将不可恢复。")
                .setOnConfirmClickListener("确定", new UsualDialogger.onConfirmClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                })
                .setOnCancelClickListener("取消", new UsualDialogger.onCancelClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                })
                .build()
                .shown();
    }

    //获取库存详情
    private void getStockDetail() {
        String sign = MD5Util.encode("varietyId=" + "1" + "&warehouseId=" + "1");
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .getStockDetail("1", "1", sign)
                .compose(Transformer.<StockDetailBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<StockDetailBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(StockDetailBean bean) {
                        if (bean.getCode() == 200) {
                            mAdapter.addData(bean.getData().getStockRecords());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            finish();
                        }
                    }
                });
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
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        ContractorListAdp contractorListAdp = new ContractorListAdp(contractorListBean.getData());
        myRecyclerViewOne.setAdapter(contractorListAdp);
        contractorListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                tvWarehouse.setText(contractorListBean.getData().get(position).getName());
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