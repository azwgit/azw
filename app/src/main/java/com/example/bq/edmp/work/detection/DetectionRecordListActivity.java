package com.example.bq.edmp.work.detection;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.DataUtils;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.inventory.adapter.BaseCom_Ck_Adapter;
import com.example.bq.edmp.word.inventory.adapter.PzAdapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.word.put_warehouse.Put_WarehouseActivity;
import com.example.bq.edmp.work.allocation.AllocationCompleteActivity;
import com.example.bq.edmp.work.allocation.adapter.AuditChAdapter2;
import com.example.bq.edmp.work.allocation.bean.AuditChBean2;
import com.example.bq.edmp.work.detection.adapter.DetectionContractorListAdp;
import com.example.bq.edmp.work.detection.adapter.DetectionRecordListAdapter;
import com.example.bq.edmp.work.detection.api.DetectionApi;
import com.example.bq.edmp.work.detection.bean.DetectionRecorListBean;
import com.example.bq.edmp.work.detection.bean.DetectonLxBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/*
 * 检测记录
 * */
public class DetectionRecordListActivity extends BaseActivity {

    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.shaixuan_tv)
    TextView shaixuan_tv;
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.return_img)
    ImageView return_img;

    //筛选
    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fen_rl)
    RelativeLayout fen_rl;
    @BindView(R.id.fen_tv)
    TextView fen_tv;
    @BindView(R.id.jianlx_rl)
    RelativeLayout jianlx_rl;
    @BindView(R.id.jianlx_tv)
    TextView jianlx_tv;
    @BindView(R.id.start_tiem_tv)
    TextView start_tiem_tv;
    @BindView(R.id.end_tiem_tv)
    TextView end_tiem_tv;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.chong_tv)
    TextView chong_tv;
    @BindView(R.id.shaixuan_wsj)
    TextView shaixuan_wsj;
    @BindView(R.id.pz_rv)
    RecyclerView recyclerView;


    PopupWindow mTypePopuWindow;
    private TextView wsj_dial;
    private XRecyclerView buttom_rv;
    private DetectonLxBean mdetectonLxBean;//类型数据源
    private ArrayList<CompanyBean.DataBean> companyDataBeans;
    private BaseCom_Ck_Adapter baseCom_ck_adapter;
    private LoadingDialog loading_dialog;
    private ArrayList<SxPzBean.DataBean> dataBeans;
    private PzAdapter pzAdapter;
    private List<SxPzBean.DataBean> data;
    private boolean Fund = false;

    private int currentPager = 1;
    private String orgIds = "";//公司id
    private String varietyId = "";//品种id
    private String testPlanId = "";//检测类型查询
    private ArrayList<DetectionRecorListBean.DataBean.RowsBean> rowsBeans;
    private DetectionRecordListAdapter detectionRecordListAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_detection_record_list;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(DetectionRecordListActivity.this);
        loading_dialog = new LoadingDialog(this);

        rowsBeans = new ArrayList<>();
        detectionRecordListAdapter = new DetectionRecordListAdapter(rowsBeans);
        xRecyclerView.setAdapter(detectionRecordListAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(DetectionRecordListActivity.this));
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                initData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                initData2();
                xRecyclerView.loadMoreComplete();
            }
        });
        detectionRecordListAdapter.setOnItemClickListener(new DetectionRecordListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, DetectionRecorListBean.DataBean.RowsBean rowsBean) {
                Intent intent = new Intent(DetectionRecordListActivity.this, DetectionParticularsActivity.class);
                intent.putExtra("ID", rowsBean.getId());
                startActivityForResult(intent, 250);
            }
        });

        //筛选  品种适配器
        dataBeans = new ArrayList<>();
        pzAdapter = new PzAdapter(dataBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(DetectionRecordListActivity.this, 3));
        recyclerView.setAdapter(pzAdapter);
        pzAdapter.setOnItemLeftClckListener(new PzAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(SxPzBean.DataBean dataBean, int mPosition) {
                //当点击时显示当前条目的背景和文字的颜色
                for (int i = 0; i < dataBeans.size(); i++) {
                    if (mPosition == i) {
                        dataBeans.get(i).setSelected(true);
                    } else {
                        dataBeans.get(i).setSelected(false);
                    }
                }
                pzAdapter.notifyDataSetChanged();
                if (!dataBean.getId().equals("") && dataBean.getId() != null) {
                    varietyId = dataBean.getId();
                } else {
                    varietyId = "";
                }
            }
        });


        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                orgIds = "";
                varietyId = "";
                testPlanId = "";
                start_tiem_tv.setText("");
                end_tiem_tv.setText("");
                fen_tv.setText("");
                jianlx_tv.setText("");
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    protected void initData() {
        gaginData();
    }

    //获取检测记录数据
    private void gaginData() {
        currentPager = 1;

        String sign = MD5Util.encode("endTime=" + end_tiem_tv.getText().toString() + "&orgId=" + orgIds + "&page=" + currentPager + "&pagerow=" + 15
                + "&startTime=" + start_tiem_tv.getText().toString() + "&testPlanId=" + testPlanId + "&varietyId=" + varietyId);

        RxHttpUtils.createApi(DetectionApi.class)
                .getDataList(end_tiem_tv.getText().toString(), orgIds, currentPager, 15, start_tiem_tv.getText().toString(), testPlanId, varietyId, sign)
                .compose(Transformer.<DetectionRecorListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<DetectionRecorListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DetectionRecorListBean detectionRecorListBean) {
                        String code = detectionRecorListBean.getCode();
                        if (code.equals("200")) {
                            List<DetectionRecorListBean.DataBean.RowsBean> rows = detectionRecorListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                xRecyclerView.setVisibility(ViewGroup.VISIBLE);
                                wsj.setVisibility(ViewGroup.GONE);

                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                detectionRecordListAdapter.notifyDataSetChanged();

                            } else {
                                rowsBeans.clear();
                                detectionRecordListAdapter.notifyDataSetChanged();
                                xRecyclerView.setVisibility(ViewGroup.GONE);
                                wsj.setVisibility(ViewGroup.VISIBLE);
                            }
                        } else {
                            rowsBeans.clear();
                            detectionRecordListAdapter.notifyDataSetChanged();
                            xRecyclerView.setVisibility(ViewGroup.GONE);
                            wsj.setVisibility(ViewGroup.VISIBLE);
                        }
                    }
                });
    }

    //加载更多
    private void initData2() {
        String sign = MD5Util.encode("endTime=" + end_tiem_tv.getText().toString() + "&orgId=" + orgIds + "&page=" + currentPager + "&pagerow=" + 15
                + "&startTime=" + start_tiem_tv.getText().toString() + "&testPlanId=" + testPlanId + "&varietyId=" + varietyId);

        RxHttpUtils.createApi(DetectionApi.class)
                .getDataList(end_tiem_tv.getText().toString(), orgIds, currentPager, 15, start_tiem_tv.getText().toString(), testPlanId, varietyId, sign)
                .compose(Transformer.<DetectionRecorListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<DetectionRecorListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DetectionRecorListBean detectionRecorListBean) {
                        String code = detectionRecorListBean.getCode();
                        if (code.equals("200")) {
                            List<DetectionRecorListBean.DataBean.RowsBean> rows = detectionRecorListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.addAll(rows);
                                detectionRecordListAdapter.addMoreData(rows);
                            }
                        } else {
                            xRecyclerView.setNoMore(true);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }


    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        shaixuan_tv.setOnClickListener(this);
        start_tiem_tv.setOnClickListener(this);
        end_tiem_tv.setOnClickListener(this);
        fen_rl.setOnClickListener(this);
        jianlx_rl.setOnClickListener(this);
        chong_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                if (Fund) {
                    coler();
                    gaginData();
                } else {
                    fund();
                }
                break;
            case R.id.shaixuan_tv:
                sxMethodData();
                break;
            case R.id.start_tiem_tv:
                initStartDatePicker(start_tiem_tv);
                break;
            case R.id.end_tiem_tv:
                initStartDatePicker(end_tiem_tv);
                break;
            case R.id.fen_rl:
                findContentViews(view);
                break;
            case R.id.jianlx_rl:
                getContractor_lx_List();
                break;
            case R.id.chong_tv:
                coler();
                sxMethodData();
                break;
            case R.id.affirm_tv:
                if (isOutTes()) {
                    Fund = true;
                    gaginData();
                    if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                        //当菜单栏是可见的，则关闭
                        drawerLayout.closeDrawer(linterHistoryConfirm);
                    }
                }
                break;

        }
    }

    //获取类型数据
    private void getContractor_lx_List() {
        RxHttpUtils.createApi(DetectionApi.class)
                .getContractorList()
                .compose(Transformer.<DetectonLxBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<DetectonLxBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DetectonLxBean detectonLxBean) {
                        mdetectonLxBean = detectonLxBean;
                        showContractorList();
                    }
                });
    }

    //类型列表PopuWindow
    private void showContractorList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        DetectionContractorListAdp detectionContractorListAdp = new DetectionContractorListAdp(mdetectonLxBean.getData());
        myRecyclerViewOne.setAdapter(detectionContractorListAdp);
        detectionContractorListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                testPlanId = mdetectonLxBean.getData().get(position).getId();
                jianlx_tv.setText(mdetectonLxBean.getData().get(position).getTestName());
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
        mTypePopuWindow.showAtLocation(findViewById(R.id.jianlx_rl), Gravity.BOTTOM, 0, 0);
    }

    /*
     * 分公司
     * */
    private void findContentViews(View view) {
        final Dialog mCameraDialog = new Dialog(view.getContext(), R.style.my_dialog);

        View root = LayoutInflater.from(view.getContext()).inflate(R.layout.allocation_sx_buttom_item, null);

        mCameraDialog.setContentView(root);
        mCameraDialog.setCanceledOnTouchOutside(true);

        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.BottomDialog_Animation); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        //lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
        //lp.alpha = 9f; // 透明度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();

        buttom_rv = root.findViewById(R.id.buttom_rv);
        wsj_dial = root.findViewById(R.id.wsj);

        //筛选  分公司，仓库适配器
        companyDataBeans = new ArrayList<>();
        baseCom_ck_adapter = new BaseCom_Ck_Adapter(companyDataBeans);
        buttom_rv.setLayoutManager(new LinearLayoutManager(DetectionRecordListActivity.this));
        buttom_rv.setAdapter(baseCom_ck_adapter);
        baseCom_ck_adapter.setOnItemLeftClckListener(new BaseCom_Ck_Adapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(CompanyBean.DataBean dataBean, int mPosition) {
                //当点击时显示当前条目的背景和文字的颜色
                for (int i = 0; i < companyDataBeans.size(); i++) {
                    if (mPosition == i) {
                        companyDataBeans.get(i).setSelected(true);
                    } else {
                        companyDataBeans.get(i).setSelected(false);
                    }
                }
                baseCom_ck_adapter.notifyDataSetChanged();
                orgIds = dataBean.getId();
                fen_tv.setText(dataBean.getName());
                mCameraDialog.dismiss();
            }
        });

        btuomMethod();
    }

    //分公司
    private void btuomMethod() {

        RxHttpUtils.createApi(InventoryListApi.class)
                .getCompanyData()
                .compose(Transformer.<CompanyBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<CompanyBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CompanyBean companyBean) {
                        List<CompanyBean.DataBean> data = companyBean.getData();
                        if (data != null && data.size() != 0) {
                            buttom_rv.setVisibility(View.VISIBLE);
                            wsj_dial.setVisibility(View.GONE);

                            companyDataBeans.clear();
                            companyDataBeans.addAll(data);
                            baseCom_ck_adapter.notifyDataSetChanged();
                        } else {
                            buttom_rv.setVisibility(View.GONE);
                            wsj_dial.setVisibility(View.VISIBLE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }


    //品种
    private void sxMethodData() {
        RxHttpUtils.createApi(InventoryListApi.class)
                .getPzData()
                .compose(Transformer.<SxPzBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<SxPzBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SxPzBean sxPzBean) {
                        data = sxPzBean.getData();
                        if (data != null && data.size() != 0) {
                            recyclerView.setVisibility(View.VISIBLE);
                            shaixuan_wsj.setVisibility(View.GONE);
                            dataBeans.clear();
                            SxPzBean.DataBean dataBean = new SxPzBean.DataBean();
                            dataBean.setSelected(true);
                            dataBean.setId("");
                            dataBean.setName("全部");
                            dataBeans.add(dataBean);
                            dataBeans.addAll(data);
                            pzAdapter.notifyDataSetChanged();
                        } else {
                            recyclerView.setVisibility(View.GONE);
                            shaixuan_wsj.setVisibility(View.VISIBLE);
                        }
                    }
                });
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(DetectionRecordListActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250) {
            gaginData();
        }
    }

    //选择时间
    private void initStartDatePicker(final TextView view) {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2019, 0, 1);
        selectedDate.set(2200, 12, 31);
        //时间选择器 ，自定义布局
        TimePickerView StartTime;
        new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                view.setText(DataUtils.getTime(date, "yyyy-MM-dd"));
            }
        })
                .setCancelText("取消")
                .setSubmitText("确认")
                .setContentTextSize(18)
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setBgColor(Color.WHITE)
                .setTitleBgColor(getResources().getColor(R.color.colorF1))
                .setCancelColor(getResources().getColor(R.color.color33))
                .setSubmitColor(getResources().getColor(R.color.appThemeColor))
                .setTextColorCenter(Color.BLACK)
                .setDate(Calendar.getInstance())
                .setRangDate(startDate, selectedDate)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .build()
                .show();

    }


    public void coler() {
        orgIds = "";
        varietyId = "";
        testPlanId = "";
        start_tiem_tv.setText("");
        end_tiem_tv.setText("");
        fen_tv.setText("");
        jianlx_tv.setText("");
        Fund = false;
    }

    public boolean isOutTes() {
        if (start_tiem_tv.getText().toString().equals("")) {
            ToastUtil.setToast("请选择开始时间");
            return false;
        }
        if (end_tiem_tv.getText().toString().equals("")) {
            ToastUtil.setToast("请选择结束时间");
            return false;
        }
        if (fen_tv.getText().toString().equals("")) {
            ToastUtil.setToast("请选择分公司");
            return false;
        }
        if (jianlx_tv.getText().toString().equals("")) {
            ToastUtil.setToast("请选择检测类型");
            return false;
        }
        return true;
    }
}
