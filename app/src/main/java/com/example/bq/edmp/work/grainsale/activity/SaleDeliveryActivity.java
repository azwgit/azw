package com.example.bq.edmp.work.grainsale.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
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
import com.example.bq.edmp.word.put_warehouse.adapter.ChuKuListAdapter;
import com.example.bq.edmp.word.put_warehouse.bean.ChuKuWarehouseListBean;
import com.example.bq.edmp.work.grainmanagement.activity.WarehousingOutDetailAct;
import com.example.bq.edmp.work.grainsale.adapter.SaleDeliveryAdapter;
import com.example.bq.edmp.work.grainsale.api.SaleApi;
import com.example.bq.edmp.work.grainsale.bean.SaleDeliveryBean;
import com.example.bq.edmp.work.grainsale.bean.SaleWarehouseBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/*
 * 销售出库
 * */
public class SaleDeliveryActivity extends BaseTitleActivity {

    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.screen_img)
    ImageView screen_img;
    @BindView(R.id.xr)
    XRecyclerView xr;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fen_company_rl)
    RelativeLayout fen_company_rl;
    @BindView(R.id.fen_company_tv)
    TextView fen_company_tv;
    @BindView(R.id.ck_rl)
    RelativeLayout ck_rl;
    @BindView(R.id.ck_tv)
    TextView ck_tv;
    @BindView(R.id.shai_rv)
    RecyclerView recyclerView;
    @BindView(R.id.shaixuan_wsj)
    TextView shaixuan_wsj;
    @BindView(R.id.start_time_tv)
    TextView start_time_tv;
    @BindView(R.id.end_time_tv)
    TextView end_time_tv;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.cz_tv)
    TextView cz_tv;

    private LoadingDialog loadingDialog;
    private int currentPager = 1;
    private String orgIds = "";//公司id
    private String varietyId = "";//品种id
    private String warehouseId = "";//仓库id
    private String beginTime = "";//开始时间
    private String endTime = "";//结束时间
    private String code = "";//单号
    private SaleDeliveryAdapter saleDeliveryAdapter;
    private ArrayList<SaleDeliveryBean.DataBean.RowsBean> rowsBeans;
    private ArrayList<CompanyBean.DataBean> companyDataBeans;
    private ArrayList<SxPzBean.DataBean> dataBeans;
    private BaseCom_Ck_Adapter baseCom_ck_adapter;
    private List<SxPzBean.DataBean> data;
    private PzAdapter pzAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sale_delivery;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(SaleDeliveryActivity.this);
        txtTabTitle.setText("出库记录");
        loadingDialog = new LoadingDialog(this);

        //出库数据
        rowsBeans = new ArrayList<>();
        saleDeliveryAdapter = new SaleDeliveryAdapter(rowsBeans);
        xr.setAdapter(saleDeliveryAdapter);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(SaleDeliveryActivity.this));
        xr.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                initData();
                xr.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                initData2();
                xr.loadMoreComplete();
            }
        });
        saleDeliveryAdapter.setOnItemClickListener(new SaleDeliveryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, SaleDeliveryBean.DataBean.RowsBean rowsBean) {
                Intent intent = new Intent(SaleDeliveryActivity.this, SaleDeliveryDetailsActivity.class);
                intent.putExtra("id", rowsBean.getId());
                startActivityForResult(intent, 250);
            }
        });

        //搜索
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //此处做逻辑处理
                    Fund = true;
                    code = textView.getText().toString();
                    hideKeyboard(search_et);
                    gainData();
                    return true;
                }
                return false;
            }
        });

        //筛选  品种适配器
        dataBeans = new ArrayList<>();
        pzAdapter = new PzAdapter(dataBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(SaleDeliveryActivity.this, 3));
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

    }

    @Override
    protected void initData() {
        gainData();
    }


    private void gainData() {
        currentPager = 1;

        String sign = MD5Util.encode("beginTime=" + beginTime + "&code=" + code + "&endTime=" + endTime +
                "&orgId=" + orgIds + "&page=" + currentPager +
                "&pagerow=" + 15 + "&varietyId=" + varietyId + "&warehouseId=" + warehouseId);

        RxHttpUtils.createApi(SaleApi.class)
                .getChuWarehouseListData(beginTime, code, endTime, orgIds, currentPager, 15, varietyId, warehouseId, sign)
                .compose(Transformer.<SaleDeliveryBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<SaleDeliveryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SaleDeliveryBean saleDeliveryBean) {
                        List<SaleDeliveryBean.DataBean.RowsBean> rows = saleDeliveryBean.getData().getRows();
                        if (rows != null && rows.size() != 0) {
                            xr.setVisibility(ViewGroup.VISIBLE);
                            wsj.setVisibility(ViewGroup.GONE);

                            rowsBeans.clear();
                            rowsBeans.addAll(rows);
                            saleDeliveryAdapter.notifyDataSetChanged();
                        } else {
                            xr.setVisibility(ViewGroup.GONE);
                            wsj.setVisibility(ViewGroup.VISIBLE);

                            rowsBeans.clear();
                            saleDeliveryAdapter.notifyDataSetChanged();
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("beginTime=" + beginTime + "&code=" + code + "&endTime=" + endTime +
                "&orgId=" + orgIds + "&page=" + currentPager +
                "&pagerow=" + 15 + "&varietyId=" + varietyId + "&warehouseId=" + warehouseId);

        RxHttpUtils.createApi(SaleApi.class)
                .getChuWarehouseListData(beginTime, code, endTime, orgIds, currentPager, 15, varietyId, warehouseId, sign)
                .compose(Transformer.<SaleDeliveryBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<SaleDeliveryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SaleDeliveryBean saleDeliveryBean) {
                        List<SaleDeliveryBean.DataBean.RowsBean> rows = saleDeliveryBean.getData().getRows();
                        if (rows != null && rows.size() != 0) {

                            rowsBeans.addAll(rows);
                            saleDeliveryAdapter.addMoreData(rows);
                        } else {
                            ToastUtil.setToast("暂无数据");
                            xr.setNoMore(true);
                        }
                    }
                });
    }

    /**
     * 隐藏软键盘
     *
     * @param :上下文环境，一般为Activity实例
     * @param view                 :一般为EditText
     */
    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private int type = 1;
    private boolean Fund = false;

    @Override
    protected void initListener() {
        fen_company_rl.setOnClickListener(this);
        screen_img.setOnClickListener(this);
        cz_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
        start_time_tv.setOnClickListener(this);
        end_time_tv.setOnClickListener(this);
        ck_rl.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                if (Fund) {
                    search_et.setText("");
                    orgIds = "";
                    varietyId = "";
                    warehouseId = "";
                    beginTime = "";
                    endTime = "";
                    code = "";
                    Fund = false;
                    gainData();
                } else {
                    fund();
                }
                break;
            case R.id.screen_img:
                sxMethodData();
                break;
            case R.id.start_time_tv:
                initStartDatePicker(start_time_tv);
                break;
            case R.id.end_time_tv:
                initStartDatePicker(end_time_tv);
                break;
            case R.id.fen_company_rl://分公司
                type = 1;
                findContentViews2(view);
                break;
            case R.id.ck_rl://仓库
                if (fen_company_tv.getText().toString().equals("") || fen_company_tv.getText().toString().equals("所有分子公司")) {
                    ToastUtil.setToast("请先选择所公司");
                    break;
                }
                type = 2;
                findContentViews2(view);
                break;
            case R.id.affirm_tv://确定
                if (start_time_tv.getText().toString().equals("")) {
                    ToastUtil.setToast("请选择开始时间");
                    break;
                }
                if (end_time_tv.getText().toString().equals("")) {
                    ToastUtil.setToast("请选择结束时间");
                    break;
                }

                beginTime = start_time_tv.getText().toString();
                endTime = end_time_tv.getText().toString();
                Fund = true;
                gainData();
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
                break;
            case R.id.cz_tv://重置
                start_time_tv.setHint("请选择开始时间");
                start_time_tv.setText("");
                end_time_tv.setHint("请选择结束时间");
                end_time_tv.setText("");
                fen_company_tv.setHint("所有分子公司");
                fen_company_tv.setText("");
                ck_tv.setHint("所有仓库");
                ck_tv.setText("");
                orgIds = "";
                orgIds = "";
                varietyId = "";
                warehouseId = "";
                warehouseId = "";
                beginTime = "";
                endTime = "";
                code = "";
                sxMethodData();
                break;
        }
    }

    private TextView wsj_dial;
    private XRecyclerView buttom_rv;

    private void sxMethodData() {
        RxHttpUtils.createApi(InventoryListApi.class)
                .getPzData()
                .compose(Transformer.<SxPzBean>switchSchedulers())
                .subscribe(new NewCommonObserver<SxPzBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SxPzBean sxPzBean) {
                        data = sxPzBean.getData();
                        if (data != null && data.size() != 0) {
                            recyclerView.setVisibility(ViewGroup.VISIBLE);
                            shaixuan_wsj.setVisibility(ViewGroup.GONE);
                            dataBeans.clear();
                            SxPzBean.DataBean dataBean = new SxPzBean.DataBean();
                            dataBean.setSelected(true);
                            dataBean.setId("");
                            dataBean.setName("全部");
                            dataBeans.add(dataBean);
                            dataBeans.addAll(data);
                            pzAdapter.notifyDataSetChanged();
                        } else {
                            recyclerView.setVisibility(ViewGroup.GONE);
                            shaixuan_wsj.setVisibility(ViewGroup.VISIBLE);
                        }
                    }
                });
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    /*
     * 底部跳框
     * */
    private void findContentViews2(View view) {
        final Dialog mCameraDialog = new Dialog(view.getContext(), R.style.my_dialog);

        View root = LayoutInflater.from(view.getContext()).inflate(R.layout.sx_buttom_item, null);

        mCameraDialog.setContentView(root);
        mCameraDialog.setCanceledOnTouchOutside(false);

        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.BottomDialog_Animation); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//      lp.alpha = 9f; // 透明度
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
        buttom_rv.setLayoutManager(new LinearLayoutManager(SaleDeliveryActivity.this));
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

                if (type == 1) {//分公司
                    orgIds = dataBean.getId();
                    fen_company_tv.setText(dataBean.getName());
                } else if (type == 2) {//仓库
                    warehouseId = dataBean.getId();
                    ck_tv.setText(dataBean.getName());
                }
                mCameraDialog.dismiss();
            }
        });

        btuomMethod();
    }

    private void btuomMethod() {
        if (type == 1) {
            RxHttpUtils.createApi(InventoryListApi.class)
                    .getCompanyData()
                    .compose(Transformer.<CompanyBean>switchSchedulers())
                    .subscribe(new NewCommonObserver<CompanyBean>() {
                        @Override
                        protected void onError(String errorMsg) {
                            ToastUtil.setToast(errorMsg);
                        }

                        @Override
                        protected void onSuccess(CompanyBean companyBean) {
                            List<CompanyBean.DataBean> data = companyBean.getData();
                            if (data != null && data.size() != 0) {
                                buttom_rv.setVisibility(ViewGroup.VISIBLE);
                                wsj_dial.setVisibility(ViewGroup.GONE);

                                companyDataBeans.clear();
                                data.get(0).setSelected(true);
                                orgIds = data.get(0).getId();
                                fen_company_tv.setText(data.get(0).getName());
                                companyDataBeans.addAll(data);
                                baseCom_ck_adapter.notifyDataSetChanged();
                            } else {
                                buttom_rv.setVisibility(ViewGroup.GONE);
                                wsj_dial.setVisibility(ViewGroup.VISIBLE);
                                ToastUtil.setToast("暂无数据");
                            }
                        }
                    });
        } else {
            RxHttpUtils.createApi(InventoryListApi.class)
                    .getCkData()
                    .compose(Transformer.<CompanyBean>switchSchedulers())
                    .subscribe(new NewCommonObserver<CompanyBean>() {
                        @Override
                        protected void onError(String errorMsg) {
                            ToastUtil.setToast(errorMsg);
                        }

                        @Override
                        protected void onSuccess(CompanyBean companyBean) {
                            List<CompanyBean.DataBean> data = companyBean.getData();
                            if (data != null && data.size() != 0) {
                                buttom_rv.setVisibility(ViewGroup.VISIBLE);
                                wsj_dial.setVisibility(ViewGroup.GONE);

                                companyDataBeans.clear();
                                data.get(0).setSelected(true);
                                warehouseId = data.get(0).getId();
                                ck_tv.setText(data.get(0).getName());
                                companyDataBeans.addAll(data);
                                baseCom_ck_adapter.notifyDataSetChanged();
                            } else {
                                buttom_rv.setVisibility(ViewGroup.GONE);
                                wsj_dial.setVisibility(ViewGroup.VISIBLE);
                                ToastUtil.setToast("暂无数据");
                            }
                        }
                    });
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(SaleDeliveryActivity.this);
    }
}
