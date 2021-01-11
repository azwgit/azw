package com.example.bq.edmp.work.grainmanagement.activity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.ActivityUtils;
import com.example.bq.edmp.utils.GridItemDecoration;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.grainmanagement.adapter.ContractorListAdp;
import com.example.bq.edmp.work.grainmanagement.adapter.DetectionListAdp;
import com.example.bq.edmp.work.grainmanagement.adapter.VarietiesListAdp;
import com.example.bq.edmp.work.grainmanagement.adapter.WareHouseListAdp;
import com.example.bq.edmp.work.grainmanagement.api.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.bean.ContractorListBean;
import com.example.bq.edmp.work.grainmanagement.bean.TestingBeanList;
import com.example.bq.edmp.work.grainmanagement.bean.VarietiesListBean;
import com.example.bq.edmp.work.grainmanagement.bean.WareHouseListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewAcquisitionsActivity extends BaseTitleActivity implements DetectionListAdp.SaveEditListener {
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.tv_contractor)
    TextView mTvContractor;
    @BindView(R.id.tv_varieties)
    TextView mTvVarieties;
    @BindView(R.id.tv_warehouse)
    TextView mTvWarehouse;
    @BindView(R.id.tv_jiance)
    TextView mTvJiance;

    PopupWindow mTypePopuWindow;
    private DetectionListAdp detectionListAdp;
    private List<TestingBeanList.DataBean.TestPlanItemsBean> testPlanItemsBeans = new ArrayList<TestingBeanList.DataBean.TestPlanItemsBean>();
    private ILoadingView loading_dialog;
    private ContractorListBean contractorListBean;//承包人数据源
    private VarietiesListBean varietiesListBean;//品种数据源
    private WareHouseListBean wareHouseListBean;//仓库数据源
    private int contractorId = 0;//所选承包人id
    private int varietiesId = 0;//所选品种id
    private int warehouseId = 0;//所选仓库id
    private int cropId = 0;//所选品种对应的作物id
    private int farmId = 0;//农场id

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
        mRecyclerView.addItemDecoration(gridItemDecoration);
        detectionListAdp = new DetectionListAdp(testPlanItemsBeans);
        mRecyclerView.setAdapter(detectionListAdp);
    }

    @Override
    protected void initListener() {
        mTvSubmit.setOnClickListener(this);
        mTvContractor.setOnClickListener(this);
        mTvVarieties.setOnClickListener(this);
        mTvWarehouse.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(NewAcquisitionsActivity.this);
        txtTabTitle.setText("新增收购");
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_acquisitions;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                checkData();
                break;
            case R.id.tv_contractor:
                getContractorList();
                break;
            case R.id.tv_varieties:
                getVarietiesList();
                break;
            case R.id.tv_warehouse:
                getWarehouseList();
                break;
        }
    }

    //新增收购验证
    private void checkData() {
        if (contractorId == 0) {
            ToastUtil.setToast("请选择承包人");
            return;
        }
        if (varietiesId == 0) {
            ToastUtil.setToast("请选择品种");
            return;
        }
        if (warehouseId == 0) {
            ToastUtil.setToast("请选择仓库");
            return;
        }
        if (testPlanItemsBeans.size() <= 0) {
            ToastUtil.setToast("当前品种无检测信息");
            return;
        }
        for (int i = 0; i < testPlanItemsBeans.size(); i++) {
            TestingBeanList.DataBean.TestPlanItemsBean testPlanItemsBean = testPlanItemsBeans.get(i);
            String content = testPlanItemsBean.getContent();
            if ("".equals(content) || content == null) {
                if (testPlanItemsBean.getContent().equals("")) {
                    Toast.makeText(getApplicationContext(), "请输入" + testPlanItemsBean.getName(), Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
        addAcquisition(farmId + "", contractorId + "", varietiesId + "", warehouseId + "");
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
                        if (bean.getCode() == 200) {
                            contractorListBean = bean;
                            showContractorList();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
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
                contractorId = contractorListBean.getData().get(position).getId();
                mTvContractor.setText(contractorListBean.getData().get(position).getName());
                farmId = contractorListBean.getData().get(position).getFarmId();
                //清空品种信息
                mTvVarieties.setText("");
                varietiesId = 0;
                //清空仓库信息
                mTvWarehouse.setText("");
                warehouseId = 0;
                //清空作物id
                cropId = 0;
                //清空检测项目
                testPlanItemsBeans.clear();
                detectionListAdp.addData(testPlanItemsBeans);
                //隐藏检测信息
                mTvJiance.setVisibility(View.GONE);
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

    //获取品种列表
    private void getVarietiesList() {
        if (contractorId == 0) {
            ToastUtil.setToast("请先选择承包人");
            return;
        }
        String sign = MD5Util.encode("id=" + contractorId);
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .getVarietiesList(contractorId + "", sign)
                .compose(Transformer.<VarietiesListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<VarietiesListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(VarietiesListBean bean) {
                        if (bean.getCode() == 200) {
                            varietiesListBean = bean;
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
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        VarietiesListAdp varietiesListAdp = new VarietiesListAdp(varietiesListBean.getData());
        myRecyclerViewOne.setAdapter(varietiesListAdp);
        varietiesListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                varietiesId = varietiesListBean.getData().get(position).getId();
                mTvVarieties.setText(varietiesListBean.getData().get(position).getName());
                //清空仓库信息
                mTvWarehouse.setText("");
                warehouseId = 0;
                //获取作物id
                cropId = varietiesListBean.getData().get(position).getCropId();
                //根据品种获取检测信息
                getTestingList();
                //显示检测信息
                mTvJiance.setVisibility(View.VISIBLE);
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
        if (varietiesId == 0) {
            ToastUtil.setToast("请先选择品种");
            return;
        }
        String sign = MD5Util.encode("id=" + varietiesId);
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .getWarehouseList(varietiesId + "", sign)
                .compose(Transformer.<WareHouseListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<WareHouseListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(WareHouseListBean bean) {
                        if (bean.getCode() == 200) {
                            wareHouseListBean = bean;
                            showWareHousingList();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }

                    }
                });
    }

    //仓库列表PopuWindow
    private void showWareHousingList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        WareHouseListAdp wareHouseListAdp = new WareHouseListAdp(wareHouseListBean.getData());
        myRecyclerViewOne.setAdapter(wareHouseListAdp);
        wareHouseListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                warehouseId = wareHouseListBean.getData().get(position).getId();
                mTvWarehouse.setText(wareHouseListBean.getData().get(position).getName());
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

    //获取检测信息
    private void getTestingList() {
        String sign = MD5Util.encode("id=" + cropId);
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .getTestingList(cropId + "", sign)
                .compose(Transformer.<TestingBeanList>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<TestingBeanList>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(TestingBeanList bean) {
                        if (bean.getCode() == 200) {
                            detectionListAdp.setNewData(bean.getData().get(0).getTestPlanItems());
                            testPlanItemsBeans = bean.getData().get(0).getTestPlanItems();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }

                    }
                });
    }

    //新增收购
    private void addAcquisition(String farmId, String contractorId, String varietiesId, String warehouseId) {
        List id = new ArrayList();
        List value = new ArrayList();
        for (int i = 0; i < testPlanItemsBeans.size(); i++) {
            id.add(testPlanItemsBeans.get(i).getId());
            value.add(testPlanItemsBeans.get(i).getContent());
        }
        String sign = MD5Util.encode("farmId=" + farmId + "&farmerId=" + contractorId + "&testPlanItemId=" + testPlanItemsBeans.get(0).getId() + "&testingItemValue=" + testPlanItemsBeans.get(0).getContent() + "&varietyId=" + varietiesId + "&warehouseId=" + warehouseId);
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .addAcquisitions(farmId, contractorId, id, value, varietiesId, warehouseId, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ActivityUtils.getMsg(errorMsg, getApplicationContext());
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            NewAcquisitionsSuccessActivity.newIntent(getApplicationContext(), bean.getData());
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    @Override
    public void SaveEdit(int position, String string) {
        testPlanItemsBeans.get(position).setContent(string);
    }
}