package com.example.bq.edmp.word.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.TableLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.bumptech.glide.Glide;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.activity.ApplyPayAccountAct;
import com.example.bq.edmp.activity.apply.travel.activity.ApplyTravelAccountAct;
import com.example.bq.edmp.activity.login.Gestures_login_Activity;
import com.example.bq.edmp.activity.login.LoginActivity;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.dateselector.CustomDatePicker;
import com.example.bq.edmp.utils.dateselector.DateFormatUtils;
import com.example.bq.edmp.word.adapter.AuditChAdapter;
import com.example.bq.edmp.word.adapter.LeftAdapter;
import com.example.bq.edmp.word.adapter.SubmitListAdapter;
import com.example.bq.edmp.word.api.WordListApi;
import com.example.bq.edmp.word.bean.AuditChBean;
import com.example.bq.edmp.word.bean.SubmitListBean;
import com.example.bq.edmp.word.fragment.SubmitFragment;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private CustomDatePicker mStartDatePicker, mEndDatePicker;
    private String start_time;
    private String end_time;

    @Override
    protected void initData() {
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
                screen();
                break;
            case R.id.apply_tv:
                findContentViews2(view);
                break;
            case R.id.text_history_start_time:
                initStartDatePicker();
                mStartDatePicker.show(text_history_start_time.getText().toString());
                break;
            case R.id.text_history_end_time:
                if (text_history_start_time.getText().toString().isEmpty()) {
                    ToastUtil.setToast("请选择开始时间");
                    break;
                }
                initEndDatePicker();
                mEndDatePicker.show(text_history_end_time.getText().toString());
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

                text_history_start_time.setText("选择开始时间");
                text_history_end_time.setText("选择结束时间");

                break;
            case R.id.affirm_tv://确认
                if (start_time.equals("选择开始时间")) {
                    ToastUtil.setToast("请选择开始时间");
                    break;
                } else if (start_time.equals("")) {
                    ToastUtil.setToast("请选择开始时间");
                    break;
                }
                if (end_time.equals("选择结束时间")) {
                    ToastUtil.setToast("请选择结束时间");
                    break;
                } else if (end_time.equals("")) {
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
                text_history_start_time.setText("选择开始时间");
                text_history_end_time.setText("选择结束时间");
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
                Intent intent=new Intent(getApplicationContext(), ApplyTravelAccountAct.class);
                startActivity(intent);
                mCameraDialog.dismiss();
            }
        });
        //支出报账
        sllogin_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ApplyPayAccountAct.class);
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

    private void initStartDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2009-05-01", false);
        long endTimestamp = System.currentTimeMillis();

        text_history_start_time.setText(DateFormatUtils.long2Str(endTimestamp, false));

        // 通过时间戳初始化日期，毫秒级别
        mStartDatePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                text_history_start_time.setText(DateFormatUtils.long2Str(timestamp, false));
            }
        }, beginTimestamp, endTimestamp);
        // 不允许点击屏幕或物理返回键关闭
        mStartDatePicker.setCancelable(false);
        // 不显示时和分
        mStartDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mStartDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mStartDatePicker.setCanShowAnim(false);

    }

    private void initEndDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2009-05-01", false);
        long endTimestamp = System.currentTimeMillis();

        text_history_end_time.setText(DateFormatUtils.long2Str(endTimestamp, false));

        // 通过时间戳初始化日期，毫秒级别
        mEndDatePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                boolean dateOneBigger = isDateOneBigger(start_time, DateFormatUtils.long2Str(timestamp, false));

                if (dateOneBigger) {
                    ToastUtil.setToast("结束时间不能比开始时间早");
                } else {
                    text_history_end_time.setText(DateFormatUtils.long2Str(timestamp, false));
                }
            }
        }, beginTimestamp, endTimestamp);
        // 不允许点击屏幕或物理返回键关闭
        mEndDatePicker.setCancelable(false);
        // 不显示时和分
        mEndDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mEndDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mEndDatePicker.setCanShowAnim(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mStartDatePicker != null) {
            mStartDatePicker.onDestroy();
        }
        if (mEndDatePicker != null) {
            mEndDatePicker.onDestroy();
        }
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

}
