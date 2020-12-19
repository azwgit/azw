package com.example.bq.edmp.work.allocation;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
import android.widget.LinearLayout;
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
import com.example.bq.edmp.word.activity.SubmitActivity;
import com.example.bq.edmp.word.adapter.AuditChAdapter;
import com.example.bq.edmp.word.bean.AuditChBean;
import com.example.bq.edmp.word.inventory.adapter.BaseCom_Ck_Adapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.work.allocation.adapter.AllocationApprovalListAdapter;
import com.example.bq.edmp.work.allocation.adapter.AllocationCompleteListAdapter;
import com.example.bq.edmp.work.allocation.adapter.AuditChAdapter2;
import com.example.bq.edmp.work.allocation.api.AllocationApi;
import com.example.bq.edmp.work.allocation.bean.AuditChBean2;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.inventorytransfer.activity.FinishedProductAllocationDetailsActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/*
 * 调拨   已完成
 * */
public class AllocationCompleteActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.screen_img)
    ImageView screen_img;
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsj)
    TextView wsj;

    //筛选
    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.chu_rl)
    RelativeLayout chu_rl;
    @BindView(R.id.chu_tv)
    TextView chu_tv;
    @BindView(R.id.ru_rl)
    RelativeLayout ru_rl;
    @BindView(R.id.ru_tv)
    TextView ru_tv;
    @BindView(R.id.start_tiem_tv)
    TextView start_tiem_tv;
    @BindView(R.id.end_tiem_tv)
    TextView end_tiem_tv;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.chong_tv)
    TextView chong_tv;
    @BindView(R.id.dblx_rv)
    RecyclerView recyclerView;

    private TextView wsj_dial;
    private XRecyclerView buttom_rv;
    private ArrayList<AuditChBean2.DataBean> data;
    private ArrayList<AuditChBean2.DataBean> data2;
    private AuditChAdapter2 auditChAdapter;
    ArrayList<String> chnamelist = new ArrayList<>();
    private ArrayList<CompanyBean.DataBean> companyDataBeans;
    private BaseCom_Ck_Adapter baseCom_ck_adapter;
    private LoadingDialog loading_dialog;
    private AllocationCompleteListAdapter allocationCompleteListAdapter;
    private ArrayList<BaseAllocationBeam.DataBean.RowsBean> rowsBeans;


    private int currentPager = 1;
    private String code = "";//调拨单号
    private String inOrgId = "";//调入分公司id
    private String outOrgId = "";//调出分公司id
    private String types = "";//调拨类型：1原粮调拨 2成品调拨 3转商调拨
    private boolean Fund = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_allocation_complete;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(AllocationCompleteActivity.this);
        title_tv.setText("已完成");
        loading_dialog = new LoadingDialog(this);

        rowsBeans = new ArrayList<>();
        allocationCompleteListAdapter = new AllocationCompleteListAdapter(rowsBeans);
        xRecyclerView.setAdapter(allocationCompleteListAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(AllocationCompleteActivity.this));
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
        allocationCompleteListAdapter.setOnItemClickListener(new AllocationCompleteListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, BaseAllocationBeam.DataBean.RowsBean rowsBean) {
                FinishedProductAllocationDetailsActivity.newIntent(getApplicationContext(),rowsBean.getId());
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

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                inOrgId = "";
                outOrgId = "";
                code = "";
                chu_tv.setText("");
                ru_tv.setText("");
                start_tiem_tv.setText("");//开始时间
                end_tiem_tv.setText("");//结束时间
                types = "";
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        data2 = new ArrayList<>();
        auditChAdapter = new AuditChAdapter2(data2);
        recyclerView.setLayoutManager(new GridLayoutManager(AllocationCompleteActivity.this, 2));
        recyclerView.setAdapter(auditChAdapter);

        //点击左侧实现右侧商品信息展示
        auditChAdapter.setOnItemLeftClckListener(new AuditChAdapter2.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(AuditChBean2.DataBean dataBean, int position) {
                //当点击时显示当前条目的背景和文字的颜色
                for (int i = 0; i < data.size(); i++) {
                    if (position == i) {
                        data.get(i).setSelected(true);
                    } else {
                        data.get(i).setSelected(false);
                    }
                }
                auditChAdapter.notifyDataSetChanged();
                //调拨类型
                types = dataBean.getId()+"";
            }
        });
    }

    @Override
    protected void initData() {
        gainData();
    }

    //获取已完成数据
    private void gainData() {
        currentPager = 1;

        String s = start_tiem_tv.getText().toString();
        String s2 = end_tiem_tv.getText().toString();

        String sign = MD5Util.encode("beginTime=" + s + "&code=" + code + "&endTime=" + s2 + "&inOrgId=" + inOrgId
                + "&outOrgId=" + outOrgId + "&page=" + currentPager + "&pagerow=" + 15 + "&types=" + types);

        RxHttpUtils.createApi(AllocationApi.class)
                .getAllocationCompleteData(s, code, s2, inOrgId, outOrgId, currentPager, 15, types, sign)
                .compose(Transformer.<BaseAllocationBeam>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseAllocationBeam>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseAllocationBeam baseAllocationBeam) {
                        String code = baseAllocationBeam.getCode();
                        if (code.equals("200")) {
                            List<BaseAllocationBeam.DataBean.RowsBean> rows = baseAllocationBeam.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                wsj.setVisibility(ViewGroup.GONE);
                                xRecyclerView.setVisibility(ViewGroup.VISIBLE);
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                allocationCompleteListAdapter.notifyDataSetChanged();
                            } else {
                                wsj.setVisibility(ViewGroup.VISIBLE);
                                xRecyclerView.setVisibility(ViewGroup.GONE);
                                rowsBeans.clear();
                                allocationCompleteListAdapter.notifyDataSetChanged();
                                ToastUtil.setToast("暂无数据");
                            }

                        } else {
                            wsj.setVisibility(ViewGroup.VISIBLE);
                            xRecyclerView.setVisibility(ViewGroup.GONE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    //获取已完成更多数据
    private void initData2() {

        String sign = MD5Util.encode("beginTime=" + start_tiem_tv.getText().toString() + "&code=" + code + "&endTime=" + end_tiem_tv.getText().toString() + "&inOrgId=" + inOrgId
                + "&outOrgId=" + outOrgId + "&page=" + currentPager + "&pagerow=" + 15 + "&types=" + types);

        RxHttpUtils.createApi(AllocationApi.class)
                .getAllocationCompleteData(start_tiem_tv.getText().toString(), code, end_tiem_tv.getText().toString(), inOrgId, outOrgId, currentPager, 15, types, sign)
                .compose(Transformer.<BaseAllocationBeam>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseAllocationBeam>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseAllocationBeam baseAllocationBeam) {
                        String code = baseAllocationBeam.getCode();
                        if (code.equals("200")) {
                            List<BaseAllocationBeam.DataBean.RowsBean> rows = baseAllocationBeam.getData().getRows();
                            rowsBeans.addAll(rows);
                            allocationCompleteListAdapter.addMoreData(rows);
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
        ru_rl.setOnClickListener(this);
        chu_rl.setOnClickListener(this);
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
                    CLOER();
                    Fund = false;
                    gainData();
                } else {
                    fund();
                }
                break;
            case R.id.screen_img:
                screen();
                break;
            case R.id.chu_rl://调出
                findContentViews(view, 1);
                break;
            case R.id.ru_rl://调入
                findContentViews(view, 2);
                break;
            case R.id.start_tiem_tv://开始时间
                initStartDatePicker(start_tiem_tv);
                break;
            case R.id.end_tiem_tv://结束时间
                initStartDatePicker(end_tiem_tv);
                break;
            case R.id.chong_tv://筛选   重置
                CLOER();
                break;
            case R.id.affirm_tv://筛选   确定
                if (inOrgId.equals("")) {
                    ToastUtil.setToast("请选择调入分公司");
                    break;
                }
                if (outOrgId.equals("")) {
                    ToastUtil.setToast("请选择调出分公司");
                    break;
                }
                if (start_tiem_tv.getText().toString().equals("")) {
                    ToastUtil.setToast("请选择开始时间");
                    break;
                }
                if (end_tiem_tv.getText().toString().equals("")) {
                    ToastUtil.setToast("请选择结束时间");
                    break;
                }
                Fund = true;
                gainData();
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
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

    //筛选按钮
    private void screen() {
        //调拨类型：1原粮调拨 2成品调拨 3转商调拨
        chnamelist.clear();
        chnamelist.add("全部");//0
        chnamelist.add("原粮调拨");//1
        chnamelist.add("成品调拨");//2
        chnamelist.add("转商调拨");//3

        data = new ArrayList<>();
        data.clear();
        for (int i = 0; i < chnamelist.size(); i++) {
            AuditChBean2.DataBean dataBean1 = new AuditChBean2.DataBean();
            dataBean1.setId(i+"");
            dataBean1.setName(chnamelist.get(i));
            data.add(dataBean1);
        }

        //默认第一个被选中
        data.get(0).setSelected(true);

        data2.clear();
        data2.addAll(data);
        auditChAdapter.notifyDataSetChanged();

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    /*
     * 调出入
     * */
    private void findContentViews(View view, final int type) {
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
        buttom_rv.setLayoutManager(new LinearLayoutManager(AllocationCompleteActivity.this));
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
                if (type == 1) {//调出
                    outOrgId = dataBean.getId();
                    chu_tv.setText(dataBean.getName());
                } else if (type == 2) {//调入
                    inOrgId = dataBean.getId();
                    ru_tv.setText(dataBean.getName());
                }
                mCameraDialog.dismiss();
            }
        });

        btuomMethod();
    }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(AllocationCompleteActivity.this);
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

    //清空数据
    public void CLOER() {
        start_tiem_tv.setText("");
        end_tiem_tv.setText("");
        ru_tv.setText("");
        chu_tv.setText("");
        code = "";
        inOrgId = "";
        outOrgId = "";
        types = "";
        search_et.setText("");
        screen();
    }

}
