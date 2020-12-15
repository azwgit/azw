package com.example.bq.edmp.word.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.activity.ApplyPayAccountAct;
import com.example.bq.edmp.activity.apply.travel.activity.ApplyTravelAccountAct;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.utils.DataUtils;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.adapter.AuditChAdapter;
import com.example.bq.edmp.word.adapter.SubmitListAdapter;
import com.example.bq.edmp.word.api.WordListApi;
import com.example.bq.edmp.word.bean.AuditChBean;
import com.example.bq.edmp.word.bean.SubmitListBean;
import com.example.bq.edmp.word.fragment.SubmitFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/*
 * 报账管理
 * */
public class SubmitActivity extends BaseActivity {

    @BindView(R.id.layout_tab)
    TabLayout mLayout_tab;
    @BindView(R.id.view_pager)
    ViewPager mView_pager;
    @BindView(R.id.apply_tv)
    TextView apply_tv;
    @BindView(R.id.text_history_start_time)
    TextView text_history_start_time;
    @BindView(R.id.text_history_end_time)
    TextView text_history_end_time;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.screen_img)
    ImageView screen_img;
    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.cz_tv)
    TextView cz_tv;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;

    /*
     * 筛选功能
     * */
    @BindView(R.id.qb_ll)
    LinearLayout qb_ll;
    @BindView(R.id.sx_rl)
    RelativeLayout sx_rl;
    @BindView(R.id.shaixuan_tj_tv)
    TextView shaixuan_tj_tv;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.shaixuan_img)
    ImageView shaixuan_img;
    @BindView(R.id.xr)
    XRecyclerView mXRecyclerView;

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    ArrayList<String> tablist = new ArrayList<>();
    ArrayList<String> chnamelist = new ArrayList<>();
    ArrayList<Integer> integers = new ArrayList<>();
    private SubmitListAdapter submitListAdapter;
    private ArrayList<SubmitListBean.RowsBean> rowsBeans;

    private ArrayList<AuditChBean.DataBean> data;
    private ArrayList<AuditChBean.DataBean> data2;
    private AuditChAdapter auditChAdapter;
    private int ScreenCode = 0;
    private int currentPager = 1;

    private String start_time;
    private String end_time;

    @Override
    protected void initData() {

        mLayout_tab.removeAllTabs();
        for (int i = 0; i < integers.size(); i++) {
            fragmentList.add(new SubmitFragment(integers.get(i)));
            mLayout_tab.addTab(mLayout_tab.newTab().setText(tablist.get(i)));
        }
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager(), fragmentList);
        mView_pager.setAdapter(adapter);
        mLayout_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mView_pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mView_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mLayout_tab));

    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        screen_img.setOnClickListener(this);
        apply_tv.setOnClickListener(this);
        linterHistoryConfirm.setOnClickListener(this);
        drawerLayout.setOnClickListener(this);
        text_history_start_time.setOnClickListener(this);
        text_history_end_time.setOnClickListener(this);
        cz_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
        shaixuan_img.setOnClickListener(this);
    }

    @Override
    protected void initView() {

        tablist.clear();
        tablist.add("全部");
        tablist.add("待提交");
        tablist.add("待审批");
        tablist.add("已审批");
        tablist.add("已拒绝");
        tablist.add("已完成");
        tablist.add("已撤销");

        integers.clear();
        integers.add(0);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(-2);
        integers.add(4);
        integers.add(-3);


        data2 = new ArrayList<>();
        auditChAdapter = new AuditChAdapter(data2);
        rv.setLayoutManager(new GridLayoutManager(SubmitActivity.this, 2));
        rv.setAdapter(auditChAdapter);

        //点击左侧实现右侧商品信息展示
        auditChAdapter.setOnItemLeftClckListener(new AuditChAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(AuditChBean.DataBean dataBean, int position) {
                //当点击时显示当前条目的背景和文字的颜色
                for (int i = 0; i < data.size(); i++) {
                    if (position == i) {
                        data.get(i).setSelected(true);
                    } else {
                        data.get(i).setSelected(false);
                    }
                }
                auditChAdapter.notifyDataSetChanged();

                //状态码
                ScreenCode = dataBean.getId();
            }
        });


        rowsBeans = new ArrayList<>();

        submitListAdapter = new SubmitListAdapter(rowsBeans);
        mXRecyclerView.setAdapter(submitListAdapter);
        mXRecyclerView.setPullRefreshEnabled(true);
        mXRecyclerView.setLoadingMoreEnabled(true);
        mXRecyclerView.setLayoutManager(new LinearLayoutManager(SubmitActivity.this));
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                gainScreenData();
                mXRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                gainScreenData2();
                mXRecyclerView.loadMoreComplete();
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
                text_history_start_time.setHint("请选择开始时间");
                text_history_start_time.setText("");
                text_history_end_time.setHint("请选择结束时间");
                text_history_end_time.setText("");
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit;
    }

    @Override
    protected void otherViewClick(View view) {
        start_time = text_history_start_time.getText().toString();
        end_time = text_history_end_time.getText().toString();
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.screen_img://筛选
                mView_pager.setCurrentItem(0);
                screen();
                break;
            case R.id.apply_tv:
                findContentViews2(view);
                break;
            case R.id.text_history_start_time://开始时间
                initStartDatePicker(text_history_start_time);
                break;
            case R.id.text_history_end_time://结束时间
                if (text_history_start_time.getText().toString().isEmpty()) {
                    ToastUtil.setToast("请选择开始时间");
                    break;
                }
                initStartDatePicker(text_history_end_time);
                break;
            case R.id.cz_tv://重置
                for (int i = 0; i < data.size(); i++) {
                    if (i == 0) {
                        data.get(i).setSelected(true);
                    } else {
                        data.get(i).setSelected(false);
                    }
                }
                ScreenCode = 0;
                auditChAdapter.notifyDataSetChanged();
                text_history_start_time.setHint("选择开始时间");
                text_history_start_time.setText("");
                text_history_end_time.setHint("选择结束时间");
                text_history_end_time.setText("");
                break;
            case R.id.affirm_tv://确认
                if (text_history_start_time.getText().toString().equals("")) {
                    ToastUtil.setToast("请选择开始时间");
                    break;
                }
                if (text_history_end_time.getText().toString().equals("")) {
                    ToastUtil.setToast("请选择结束时间");
                    break;
                }

                qb_ll.setVisibility(ViewGroup.GONE);
                sx_rl.setVisibility(ViewGroup.VISIBLE);

                if (ScreenCode == 0) {
                    shaixuan_tj_tv.setText("筛选条件：" + start_time + "-" + end_time + " 全部");
                } else if (ScreenCode == 2) {
                    shaixuan_tj_tv.setText("筛选条件：" + start_time + "-" + end_time + " 待审批");
                } else if (ScreenCode == 3) {
                    shaixuan_tj_tv.setText("筛选条件：" + start_time + "-" + end_time + " 已审批");
                } else if (ScreenCode == -2) {
                    shaixuan_tj_tv.setText("筛选条件：" + start_time + "-" + end_time + " 已拒绝");
                } else if (ScreenCode == 4) {
                    shaixuan_tj_tv.setText("筛选条件：" + start_time + "-" + end_time + " 已完成");
                } else if (ScreenCode == -3) {
                    shaixuan_tj_tv.setText("筛选条件：" + start_time + "-" + end_time + " 已撤销");
                }
                gainScreenData();
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
                break;
            case R.id.shaixuan_img:
                qb_ll.setVisibility(ViewGroup.VISIBLE);
                sx_rl.setVisibility(ViewGroup.GONE);
                ScreenCode = 0;
                text_history_start_time.setText("");
                text_history_start_time.setHint("选择开始时间");
                text_history_end_time.setText("");
                text_history_end_time.setHint("选择结束时间");
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
                initData();
                break;
        }
    }

    //请求筛选数据
    private void gainScreenData() {

        currentPager = 1;
        String sign = MD5Util.encode(
                "firstTime=" + start_time
                        + "&lastTime=" + end_time
                        + "&page=" + currentPager
                        + "&pagerow=" + 10
                        + "&status=" + ScreenCode);

        RxHttpUtils.createApi(WordListApi.class)
                .getScreenData(start_time, end_time, currentPager, 10, ScreenCode, sign)
                .compose(Transformer.<SubmitListBean>switchSchedulers())
                .subscribe(new CommonObserver<SubmitListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SubmitListBean submitListBean) {
                        List<SubmitListBean.RowsBean> rows = submitListBean.getRows();
                        if (rows != null && rows.size() != 0) {
                            rowsBeans.clear();
                            rowsBeans.addAll(rows);
                            submitListAdapter.notifyDataSetChanged();
                            mXRecyclerView.setVisibility(ViewGroup.VISIBLE);
                            wsj.setVisibility(ViewGroup.GONE);
                        } else {
                            mXRecyclerView.setVisibility(ViewGroup.GONE);
                            wsj.setVisibility(ViewGroup.VISIBLE);
                        }
                    }
                });


    }

    private void gainScreenData2() {
        String sign = MD5Util.encode(
                "firstTime=" + start_time
                        + "&lastTime=" + end_time
                        + "&page=" + currentPager
                        + "&pagerow=" + 10
                        + "&status=" + ScreenCode);

        RxHttpUtils.createApi(WordListApi.class)
                .getScreenData(start_time, end_time, currentPager, 10, ScreenCode, sign)
                .compose(Transformer.<SubmitListBean>switchSchedulers())
                .subscribe(new CommonObserver<SubmitListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SubmitListBean submitListBean) {
                        List<SubmitListBean.RowsBean> rows = submitListBean.getRows();
                        if (rows != null && rows.size() != 0) {
                            rowsBeans.addAll(rows);
                            submitListAdapter.addMoreData(rowsBeans);
                        } else {
                            mXRecyclerView.setNoMore(true);
                        }
                    }
                });
    }

    //筛选按钮
    private void screen() {

        chnamelist.clear();
        chnamelist.add("全部");//0
        chnamelist.add("待审批");//2
        chnamelist.add("已审批");//3
        chnamelist.add("已拒绝");//-2
        chnamelist.add("已完成");//4
        chnamelist.add("已撤销");//-3

        data = new ArrayList<>();
        data.clear();
        for (int i = 0; i < chnamelist.size(); i++) {
            AuditChBean.DataBean dataBean1 = new AuditChBean.DataBean();
            if (i == 3) {
                dataBean1.setId(-2);
            } else if (i == 5) {
                dataBean1.setId(-3);
            } else if (i == 1) {
                dataBean1.setId(2);
            } else if (i == 2) {
                dataBean1.setId(3);
            } else {
                dataBean1.setId(i);
            }
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

    //手机返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                //当菜单栏是可见的，则关闭
                drawerLayout.closeDrawer(linterHistoryConfirm);
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /*
     * adapter
     * */
    public class VPAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> list;

        public VPAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    /*
     * 底部跳框
     * */
    private void findContentViews2(View view) {
        final Dialog mCameraDialog = new Dialog(view.getContext(), R.style.my_dialog);

        View root = LayoutInflater.from(view.getContext()).inflate(R.layout.gestures_dialog, null);

        mCameraDialog.setContentView(root);
        mCameraDialog.setCanceledOnTouchOutside(true);

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

        TextView passwordlogin_tv = root.findViewById(R.id.passwordlogin_tv);
        TextView authcodelogin_tv = root.findViewById(R.id.authcodelogin_tv);
        TextView sllogin_tv = root.findViewById(R.id.sllogin_tv);
        TextView cancel_tv = root.findViewById(R.id.cancel_tv);


        passwordlogin_tv.setText("申请报账类型");
        TextPaint paint = passwordlogin_tv.getPaint();
        paint.setFakeBoldText(true);
        authcodelogin_tv.setText("差旅报账");
        sllogin_tv.setText("支出报账");


        //差旅报账
        authcodelogin_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ApplyTravelAccountAct.class);
                startActivity(intent);
                mCameraDialog.dismiss();
            }
        });
        //支出报账
        sllogin_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ApplyPayAccountAct.class);
                startActivity(intent);
                mCameraDialog.dismiss();
            }
        });
        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCameraDialog.dismiss();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static boolean isDateOneBigger(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);

            LogUtils.e("ScreenCode==", dt1 + "===" + dt2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = true;
        } else if (dt1.getTime() < dt2.getTime()) {
            isBigger = false;
        }
        return isBigger;
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
}
