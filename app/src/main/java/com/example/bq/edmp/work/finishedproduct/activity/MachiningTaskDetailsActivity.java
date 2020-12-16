package com.example.bq.edmp.work.finishedproduct.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.DialoggerFail;
import com.example.bq.edmp.utils.DialoggerOk;
import com.example.bq.edmp.utils.GridItemDecoration;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.UsualDialogger;
import com.example.bq.edmp.work.finishedproduct.adapter.MachiningTaskDetailAdp;
import com.example.bq.edmp.work.finishedproduct.adapter.WarehouseListAdp;
import com.example.bq.edmp.work.finishedproduct.api.FinishedProductApi;
import com.example.bq.edmp.work.finishedproduct.bean.MachiningTaskDetailsBean;
import com.example.bq.edmp.work.finishedproduct.bean.WarehouseListBean;
import com.example.bq.edmp.work.grainmanagement.adapter.ContractorListAdp;
import com.example.bq.edmp.work.grainmanagement.adapter.StockDetailAdp;
import com.example.bq.edmp.work.grainmanagement.api.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.bean.ContractorListBean;
import com.example.bq.edmp.work.grainmanagement.bean.StockDetailBean;

import butterknife.BindView;

public class MachiningTaskDetailsActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String CODE, String id) {
        Intent intent = new Intent(context, MachiningTaskDetailsActivity.class);
        intent.putExtra(Constant.CODE, CODE);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_subsidiary_company)
    TextView mTvSubsidiaryCompany;//分子公司
    @BindView(R.id.tv_number)
    TextView mTvNumber;//订单号
    @BindView(R.id.tv_processing_type)
    TextView mTvProcessingType;//加工类型
    @BindView(R.id.tv_status)
    TextView mTvStatus;//入库类型
    @BindView(R.id.tv_packing)
    TextView mTvPacking;//品种包装
    @BindView(R.id.tv_planned_quantity)
    TextView mTvPlannedQuantity;//计划量
    @BindView(R.id.tv_time)
    TextView mTvTime;//完成日期
    @BindView(R.id.ly_task)
    LinearLayout mLyTask;//任务去人父布局
    @BindView(R.id.tv_receiver)
    TextView mTvReceiver;//任务接受人
    @BindView(R.id.tv_accept_time)
    TextView mTvAcceptTime;//接受日期
    @BindView(R.id.tv_operator)
    TextView mTvOperator;//操作人
    @BindView(R.id.ly_operation_info)
    LinearLayout mLyOperationInfo;//操作相关信息
    @BindView(R.id.tv_completion_date)
    TextView mTvCompletionDate;//操作完成日期
    @BindView(R.id.tv_processing_capacity)
    TextView mTvProcessingCapacity;//实际加工量
    @BindView(R.id.tv_degree_of_completion)
    TextView mTvDegreeOfCompletion;
    @BindView(R.id.ly_one)
    LinearLayout mLyOne;//加工记录
    @BindView(R.id.btn_ok)
    TextView mBtnOk;//任务确认按钮
    @BindView(R.id.btn_del)
    TextView mBtnDel;//任务终止按钮
    @BindView(R.id.btn_submit)
    TextView mBtnSubmit;//加工上报按钮
    @BindView(R.id.ly_bottom)
    LinearLayout mLyBottom;//下方按钮父布局

    private EditText mEtCurrentNumber, mEtProductNumber;//完成量，副产品量
    private TextView mTvRawWarehouse, mTvWarehouse, mTvProductWarehouse;
    private int RawWarehouse = 0, Warehouse = 0, ProductWarehouse = 0;
    private DialoggerOk dialogOK = null;
    private DialoggerFail dialogFail = null;
    private UsualDialogger dialog = null;
    PopupWindow mTypePopuWindow;
    private MachiningTaskDetailAdp mAdapter;
    private ILoadingView loading_dialog;
    private ContractorListBean contractorListBean;//承包人数据源
    private String code = "";
    private String id = "";
    private MachiningTaskDetailsBean machiningTaskDetailsBean = null;
    private WarehouseListBean warehouseListBean = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_machining_task_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("加工单详情");
        id = getIntent().getStringExtra(Constant.ID);
        code = getIntent().getStringExtra(Constant.CODE);
        if ("".equals(id) || "".equals(code)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MachiningTaskDetailAdp();
        mRecyclerView.setAdapter(mAdapter);
        getMachiningTaskDetails();

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {
        mTvSubsidiaryCompany.setOnClickListener(this);
        mBtnOk.setOnClickListener(this);
        mBtnDel.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_subsidiary_company:
                showMachiningTaskReport();
                break;
            case R.id.btn_ok:
                acceptTask();
                break;
            case R.id.btn_del:
                showDeleteDialog();
                break;
            case R.id.btn_submit:
                showMachiningTaskReport();
                break;
        }
    }

    //加工上报PopuWindow
    private void showMachiningTaskReport() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.machining_report, null);
        RelativeLayout mLyView = contentView.findViewById(R.id.ly_view);
        //计划量
        TextView mTvPlanNumber = contentView.findViewById(R.id.tv_plan_number);
        mTvPlanNumber.setText(MoneyUtils.formatMoney(machiningTaskDetailsBean.getData().getPlanQty()) + " 公斤");
        //已完成量
        TextView mTvCompleteNumber = contentView.findViewById(R.id.tv_complete_number);
        mTvCompleteNumber.setText(MoneyUtils.formatMoney(machiningTaskDetailsBean.getData().getFinishedQty()) + " 公斤");
        //加工完成量
        mEtCurrentNumber = contentView.findViewById(R.id.et_current_number);
        //副产品重量
        mEtProductNumber = contentView.findViewById(R.id.et_product_number);
        //原粮仓库
        mTvRawWarehouse = contentView.findViewById(R.id.tv_raw_warehouse);
        mTvRawWarehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWarehouseList("1");
            }
        });
        //加工完成仓库
        mTvWarehouse = contentView.findViewById(R.id.tv_complete_warehouse);
        mTvWarehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWarehouseList("2");
            }

        });
        //副产品仓库
        mTvProductWarehouse = contentView.findViewById(R.id.tv_product_warehouse);
        mTvProductWarehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWarehouseList("4");
            }

        });
        TextView btn_ok = contentView.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkreportTask();
            }
        });
//        mLyView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTypePopuWindow.dismiss();
//            }
//        });
        mTypePopuWindow = new PopupWindow();
        mTypePopuWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
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
//        mTypePopuWindow.setClippingEnabled(false);
        mTypePopuWindow.showAtLocation(findViewById(R.id.rl_view), Gravity.BOTTOM, 0, 0);
    }
    //验证上报内容
    private void  checkreportTask(){
        if(RawWarehouse==0){
            ToastUtil.setToast("请选择原粮库");
            return;
        }
        if("".equals(mEtCurrentNumber.getText().toString().trim())){
            ToastUtil.setToast("请输入加工完成重量");
            return;
        }
        if(Warehouse==0){
            ToastUtil.setToast("请选择加工完成存入仓库");
            return;
        }
        if("".equals(mEtCurrentNumber.getText().toString().trim())){
            ToastUtil.setToast("请输入副产品重量");
            return;
        }
        if(ProductWarehouse==0){
            ToastUtil.setToast("请选择副产品存入仓库");
            return;
        }
        reportTask(code,Warehouse+"",mEtCurrentNumber.getText().toString().trim(),ProductWarehouse+"",mEtProductNumber.getText().toString().trim(),RawWarehouse+"");
    }
    //加工上报
    private void reportTask(String code, String cpwarehouseId, String finishedQty, String fswarehouseId, String productWeight, String ylwarehouseId) {
        String sign = MD5Util.encode("code=" + code + "&cpwarehouseId=" + cpwarehouseId + "&finishedQty=" + finishedQty + "&fswarehouseId=" + fswarehouseId + "&productWeight=" + productWeight + "&ylwarehouseId=" + ylwarehouseId);
        RxHttpUtils.createApi(FinishedProductApi.class)
                .ReporTask(code, cpwarehouseId, finishedQty, fswarehouseId, productWeight, ylwarehouseId, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        mTypePopuWindow.dismiss();
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("上报成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            showFailDialog();
                            finish();
                        }
                    }
                });
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
                        stopTask();
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
    private void getMachiningTaskDetails() {
        String sign = MD5Util.encode("code=" + code + "&packagingId=" + id);
        RxHttpUtils.createApi(FinishedProductApi.class)
                .getMachiningTaskDetails(code, id, sign)
                .compose(Transformer.<MachiningTaskDetailsBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<MachiningTaskDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(MachiningTaskDetailsBean bean) {
                        machiningTaskDetailsBean = bean;
                        if (bean.getCode() == 200) {
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            finish();
                        }
                    }
                });
    }

    //页面赋值
    private void setData(MachiningTaskDetailsBean.DataBean bean) {
        mTvSubsidiaryCompany.setText(bean.getOrgName());
        mTvNumber.setText("计划单号  " + bean.getCode());
        switch (bean.getTypes()) {
            case 1:
                mTvProcessingType.setText("备货加工");
                break;
            case 2:
                mTvProcessingType.setText("接单加工");
                break;
        }
        switch (bean.getStatus()) {
            case 1:
                mTvStatus.setText("待确认");
                mLyTask.setVisibility(View.GONE);
                mBtnOk.setVisibility(View.VISIBLE);
                mBtnDel.setVisibility(View.GONE);
                mBtnSubmit.setVisibility(View.GONE);
                mLyOne.setVisibility(View.GONE);
                break;
            case 2:
                mTvStatus.setText("加工中");
                mLyTask.setVisibility(View.VISIBLE);
                mTvReceiver.setText(bean.getAcceptedOperator());
                mTvAcceptTime.setText(bean.getAcceptedTime());
//                mTvDegreeOfCompletion.setText(bean.getPercentage());
                mBtnOk.setVisibility(View.GONE);
                mBtnDel.setVisibility(View.VISIBLE);
                mBtnSubmit.setVisibility(View.VISIBLE);
                mLyOne.setVisibility(View.VISIBLE);
                break;
            case 3:
                mTvReceiver.setText(bean.getAcceptedOperator());
                mTvAcceptTime.setText(bean.getAcceptedTime());
                if(bean.getStockAdds().size()>0){
                    mTvOperator.setText(bean.getStockAdds().get(0).getAddedOperator());
                }
                mTvCompletionDate.setText(bean.getFinishedTime());
                mTvProcessingCapacity.setText(MoneyUtils.formatMoney(bean.getFinishedQty()) + " 公斤");
//                mTvDegreeOfCompletion.setText(bean.getPercentage());
                mTvStatus.setText("已完成");
                mLyTask.setVisibility(View.VISIBLE);
                mTvReceiver.setVisibility(View.VISIBLE);
                mBtnOk.setVisibility(View.GONE);
                mBtnDel.setVisibility(View.GONE);
                mBtnSubmit.setVisibility(View.GONE);
                mLyOne.setVisibility(View.VISIBLE);
                mLyBottom.setVisibility(View.GONE);
                mLyOperationInfo.setVisibility(View.VISIBLE);
                break;
        }
        mTvPacking.setText(bean.getVarietyPackagingName());
        mTvPlannedQuantity.setText(MoneyUtils.formatMoney(bean.getPlanQty()) + " 公斤");
        mTvTime.setText(bean.getPlanFinishTime());
        mAdapter.addData(bean.getStockAdds());
    }

    //获取仓库列表
    private void getWarehouseList(final String type) {
        String sign = MD5Util.encode("types=" + type);
        RxHttpUtils.createApi(FinishedProductApi.class)
                .getWarehouseList(type, sign)
                .compose(Transformer.<WarehouseListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<WarehouseListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(WarehouseListBean bean) {
                        warehouseListBean = bean;
                        if (bean.getCode() == 200) {
                            shoWarehouseList(type);
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //仓库PopuWindow
    private void shoWarehouseList(final String type) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        WarehouseListAdp warehouseListAdp = new WarehouseListAdp(warehouseListBean.getData());
        myRecyclerViewOne.setAdapter(warehouseListAdp);
        warehouseListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WarehouseListBean.DataBean bean = warehouseListBean.getData().get(position);
                if ("1".equals(type)) {
                    mTvRawWarehouse.setText(bean.getName());
                    RawWarehouse = bean.getId();
                } else if ("2".equals(type)) {
                    mTvWarehouse.setText(bean.getName());
                    Warehouse = bean.getId();
                } else {
                    mTvProductWarehouse.setText(bean.getName());
                    ProductWarehouse = bean.getId();
                }
                mTypePopuWindow.dismiss();
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

    //终止任務
    private void stopTask() {
        String sign = MD5Util.encode("code=" + code);
        RxHttpUtils.createApi(FinishedProductApi.class)
                .stopTask(code, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //接受任務
    private void acceptTask() {
        String sign = MD5Util.encode("code=" + code);
        RxHttpUtils.createApi(FinishedProductApi.class)
                .acceptTask(code, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            showOkDialog();
                            finish();
                        } else {
                            showFailDialog();
                            finish();
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }
}