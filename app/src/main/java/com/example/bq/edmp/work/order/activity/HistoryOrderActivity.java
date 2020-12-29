package com.example.bq.edmp.work.order.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
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
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.DataUtils;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.order.adapter.HistoryOrderAdapter;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.HistoryBean;
import com.example.bq.edmp.work.order.bean.OrderTrackingBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import retrofit2.http.Field;

/*
 * 历史订单
 * */
public class HistoryOrderActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.start_tiem_tv)
    TextView start_tiem_tv;
    @BindView(R.id.end_tiem_tv)
    TextView end_tiem_tv;
    @BindView(R.id.chong_tv)
    TextView chong_tv;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.screen_img)
    ImageView screen_img;
    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;


    private String code = "";
    private int currentPager = 1;
    private boolean Fund = false;
    private LoadingDialog loadingDialog;
    private HistoryOrderAdapter historyOrderAdapter;
    private ArrayList<HistoryBean.DataBean.RowsBean> rowsBeans;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_history_order;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(HistoryOrderActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("历史订单");

        rowsBeans = new ArrayList<>();
        historyOrderAdapter = new HistoryOrderAdapter(rowsBeans);
        xRecyclerView.setAdapter(historyOrderAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(HistoryOrderActivity.this));
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
        historyOrderAdapter.setOnItemClickListener(new HistoryOrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, HistoryBean.DataBean.RowsBean rowsBean) {
                Intent intent = new Intent(HistoryOrderActivity.this, Order_Tracking_DetailsActivity.class);
                intent.putExtra("ID", rowsBean.getId());
                startActivity(intent);
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
                    initData();
                    return true;
                }
                return false;
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
                start_tiem_tv.setText("");
                end_tiem_tv.setText("");
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }


    @Override
    protected void initData() {

        currentPager = 1;

        String sign = MD5Util.encode("condition=" + code + "&endTime=" + end_tiem_tv.getText().toString()
                + "&page=" + currentPager + "&pagerow=" + 15 + "&startTime=" + start_tiem_tv.getText().toString());

        RxHttpUtils.createApi(OrderApi.class)
                .getHistorylist(code, end_tiem_tv.getText().toString(), currentPager, 15, start_tiem_tv.getText().toString(), sign)
                .compose(Transformer.<HistoryBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<HistoryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(HistoryBean historyBean) {
                        String code = historyBean.getCode();
                        if (code.equals("200")) {
                            List<HistoryBean.DataBean.RowsBean> rows = historyBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                xRecyclerView.setVisibility(View.VISIBLE);
                                wsj.setVisibility(View.GONE);

                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                historyOrderAdapter.notifyDataSetChanged();

                            } else {
                                xRecyclerView.setVisibility(View.GONE);
                                wsj.setVisibility(View.VISIBLE);
                                rowsBeans.clear();
                                historyOrderAdapter.notifyDataSetChanged();
                            }
                        } else {
                            xRecyclerView.setVisibility(View.GONE);
                            wsj.setVisibility(View.VISIBLE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }


    private void initData2() {
        String sign = MD5Util.encode("condition=" + code + "&endTime=" + end_tiem_tv.getText().toString()
                + "&page=" + currentPager + "&pagerow=" + 15 + "&startTime=" + start_tiem_tv.getText().toString());

        RxHttpUtils.createApi(OrderApi.class)
                .getHistorylist(code, end_tiem_tv.getText().toString(), currentPager, 15, start_tiem_tv.getText().toString(), sign)
                .compose(Transformer.<HistoryBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<HistoryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(HistoryBean historyBean) {
                        String code = historyBean.getCode();
                        if (code.equals("200")) {
                            List<HistoryBean.DataBean.RowsBean> rows = historyBean.getData().getRows();
                            rowsBeans.addAll(rows);
                            historyOrderAdapter.addMoreData(rows);
                        } else {
                            xRecyclerView.setNoMore(true);
                            ToastUtil.setToast("最后页");
                        }
                    }
                });
    }


    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        screen_img.setOnClickListener(this);
        chong_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
        start_tiem_tv.setOnClickListener(this);
        end_tiem_tv.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                if (Fund) {
                    start_tiem_tv.setText("");
                    end_tiem_tv.setText("");
                    code = "";
                    search_et.setText("");
                    Fund = false;
                    initData();
                } else {
                    fund();
                }
                break;
            case R.id.screen_img:
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.chong_tv:
                start_tiem_tv.setText("");
                end_tiem_tv.setText("");
                break;
            case R.id.affirm_tv:
                if (start_tiem_tv.getText().toString().equals("")) {
                    ToastUtil.setToast("请选择开始时间");
                    break;
                }
                if (end_tiem_tv.getText().toString().equals("")) {
                    ToastUtil.setToast("请选择结束时间");
                    break;
                }
                Fund = true;
                initData();
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
                break;
            case R.id.start_tiem_tv:
                initStartDatePicker(start_tiem_tv);
                break;
            case R.id.end_tiem_tv:
                initStartDatePicker(end_tiem_tv);
                break;
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
        ProApplication.getinstance().finishActivity(HistoryOrderActivity.this);
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


}
