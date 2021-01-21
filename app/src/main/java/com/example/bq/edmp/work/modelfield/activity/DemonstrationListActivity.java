package com.example.bq.edmp.work.modelfield.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.GsonUtils;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.inventory.adapter.PzAdapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.work.detection.DetectionRecordListActivity;
import com.example.bq.edmp.work.marketing.bean.CityBean;
import com.example.bq.edmp.work.marketing.bean.CityModel;
import com.example.bq.edmp.work.modelfield.adapter.DemonstrationListAdapter;
import com.example.bq.edmp.work.modelfield.adapter.YearsListAdapter;
import com.example.bq.edmp.work.modelfield.api.DemonstrationApi;
import com.example.bq.edmp.work.modelfield.bean.DemonstrationListBean;
import com.example.bq.edmp.work.modelfield.bean.YearsBean;
import com.example.bq.edmp.work.modelfield.fragment.DemonstrationListFragment;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.OrderZuoWuBean;
import com.example.bq.edmp.work.order.fragment.OrderFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 品种示范列表
 * */
public class DemonstrationListActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.layout_tab)
    TabLayout layout_tab;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.rr)
    RelativeLayout rr;
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    @BindView(R.id.screen_img)
    ImageView screen_img;


    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.qy_rl)
    RelativeLayout qy_rl;
    @BindView(R.id.qy_tv)
    TextView qy_tv;
    @BindView(R.id.nd_rl)
    RelativeLayout nd_rl;
    @BindView(R.id.nd_tv)
    TextView nd_tv;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.chong_tv)
    TextView chong_tv;
    @BindView(R.id.shaixuan_wsj)
    TextView shaixuan_wsj;
    @BindView(R.id.pz_rv)
    RecyclerView recyclerView;

    PopupWindow mTypePopuWindow;
    private boolean Fund = false;
    private List<SxPzBean.DataBean> data;
    private ArrayList<SxPzBean.DataBean> dataBeans;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private LoadingDialog loadingDialog;
    private PzAdapter pzAdapter;

    private int currentPager = 1;
    private String mID = "0";//作物id
    private String mYears = "";//年度
    private String regionId = "";//区域id
    private String varietyId = "";//品种id
    private ArrayList<DemonstrationListBean.DataBean.RowsBean> rowsBeans;
    private DemonstrationListAdapter demonstrationListAdapter;
    private ArrayList<CityModel> jsonBean;
    //省
    private List<CityModel> options1Items = new ArrayList<>();
    //  市
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    //  区
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demonstration_list;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(DemonstrationListActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("品种示范");


        rowsBeans = new ArrayList<>();
        demonstrationListAdapter = new DemonstrationListAdapter(rowsBeans);
        xRecyclerView.setAdapter(demonstrationListAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(DemonstrationListActivity.this));
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
        demonstrationListAdapter.setOnItemClickListener(new DemonstrationListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, DemonstrationListBean.DataBean.RowsBean rowsBean) {
                Intent intent = new Intent(DemonstrationListActivity.this, DemonstrationDetailsActivity.class);
                intent.putExtra("id", rowsBean.getId());
                startActivity(intent);
            }
        });

        //筛选  品种适配器
        dataBeans = new ArrayList<>();
        pzAdapter = new PzAdapter(dataBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(DemonstrationListActivity.this, 3));
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
        RxHttpUtils.createApi(OrderApi.class)
                .getZuoWuData()
                .compose(Transformer.<OrderZuoWuBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<OrderZuoWuBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(OrderZuoWuBean orderZuoWuBean) {
                        String code = orderZuoWuBean.getCode();
                        if (code.equals("200")) {
                            List<OrderZuoWuBean.DataBean> data = orderZuoWuBean.getData();
                            gainFragment(data);
                        } else {
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void gainFragment(List<OrderZuoWuBean.DataBean> data) {


        ArrayList<String> IdString = new ArrayList<>();
        ArrayList<String> TabNameString = new ArrayList<>();
        IdString.clear();
        IdString.add("0");
        TabNameString.clear();
        TabNameString.add("全部");
        if (data != null && data.size() != 0) {
            for (int i = 0; i < data.size(); i++) {
                IdString.add(data.get(i).getId());
                TabNameString.add(data.get(i).getName());
            }
        }

        layout_tab.removeAllTabs();
        if (IdString != null && IdString.size() != 0) {
            for (int i = 0; i < IdString.size(); i++) {
                fragmentList.add(new DemonstrationListFragment(IdString.get(i)));
                layout_tab.addTab(layout_tab.newTab().setText(TabNameString.get(i)));
            }
        }
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager(), fragmentList);
        view_pager.setAdapter(adapter);
        layout_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view_pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layout_tab));
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        screen_img.setOnClickListener(this);
        qy_rl.setOnClickListener(this);
        nd_rl.setOnClickListener(this);
        chong_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                if (Fund) {
                    ll.setVisibility(View.VISIBLE);
                    rr.setVisibility(View.GONE);
                    String mID = "0";//作物id
                    String mYears = "";//年度
                    String regionId = "";//区域id
                    String varietyId = "";//品种id
                    qy_tv.setHint("请选择种植区域");
                    nd_tv.setHint("请选择种植年度");

                    Fund = false;
                } else {
                    fund();
                }
                break;
            case R.id.screen_img://筛选
                sxMethodData();
                break;
            case R.id.qy_rl://区域
                getAllpackageList();
                break;
            case R.id.nd_rl://年度
                gainND();
                break;
            case R.id.chong_tv://重置
                String mID = "0";//作物id
                String regionId = "";//区域id
                String varietyId = "";//品种id
                sxMethodData();
                break;
            case R.id.affirm_tv://确定
                if (qy_tv.getText().equals("")) {
                    ToastUtil.setToast("请选择种植区域");
                    break;
                }
                if (nd_tv.getText().equals("")) {
                    ToastUtil.setToast("请选择种植年度");
                    break;
                }
                Fund = true;
                gainData();
                ll.setVisibility(View.GONE);
                rr.setVisibility(View.VISIBLE);
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
                break;
        }
    }


    //年度
    private void gainND() {
        RxHttpUtils.createApi(DemonstrationApi.class)
                .getYearsList()
                .compose(Transformer.<YearsBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<YearsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(YearsBean yearsBean) {
                        String code = yearsBean.getCode();
                        if (code.equals("200")) {
                            ArrayList<String> strings = new ArrayList<>();
                            for (int i = 0; i < yearsBean.getData().size(); i++) {
                                strings.add(yearsBean.getData().get(i));
                            }
                            getYearsDataList(strings);
                        } else {
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }


    //年度
    private void getYearsDataList(final ArrayList<String> strings) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);


        ArrayList<String> yearsBeans = new ArrayList<>();
        yearsBeans.addAll(strings);
        YearsListAdapter yearsListAdapter = new YearsListAdapter(yearsBeans);
        myRecyclerViewOne.setAdapter(yearsListAdapter);
        yearsListAdapter.setOnItemClickListener(new YearsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                nd_tv.setText(strings.get(pos));
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
        mTypePopuWindow.showAtLocation(findViewById(R.id.drawerlayout), Gravity.BOTTOM, 0, 0);
    }


    //品种
    private void sxMethodData() {
        RxHttpUtils.createApi(InventoryListApi.class)
                .getPzData()
                .compose(Transformer.<SxPzBean>switchSchedulers(loadingDialog))
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


    private void gainData() {
        currentPager = 1;

        String sign = MD5Util.encode("cropId=" + mID + "&page=" + currentPager + "&pagerow=" + 15 + "&regionId=" + regionId + "&varietyId=" + varietyId + "&years=" + nd_tv.getText().toString());

        RxHttpUtils.createApi(DemonstrationApi.class)
                .getListData(mID, currentPager, 15, regionId, varietyId, nd_tv.getText().toString(), sign)
                .compose(Transformer.<DemonstrationListBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<DemonstrationListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DemonstrationListBean demonstrationListBean) {
                        String code = demonstrationListBean.getCode();
                        if (code.equals("200")) {
                            List<DemonstrationListBean.DataBean.RowsBean> data = demonstrationListBean.getData().getRows();
                            if (data != null && data.size() != 0) {
                                xRecyclerView.setVisibility(View.VISIBLE);
                                wsj.setVisibility(View.GONE);
                                rowsBeans.clear();
                                rowsBeans.addAll(data);
                                demonstrationListAdapter.notifyDataSetChanged();
                            } else {
                                xRecyclerView.setVisibility(View.GONE);
                                wsj.setVisibility(View.VISIBLE);
                                ToastUtil.setToast("暂无数据");
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
        String sign = MD5Util.encode("cropId=" + mID + "&page=" + currentPager + "&pagerow=" + 15 + "&regionId=" + regionId + "&varietyId=" + varietyId + "&years=" + nd_tv.getText().toString());


        RxHttpUtils.createApi(DemonstrationApi.class)
                .getListData(mID, currentPager, 15, regionId, varietyId,nd_tv.getText().toString(), sign)
                .compose(Transformer.<DemonstrationListBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<DemonstrationListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DemonstrationListBean demonstrationListBean) {
                        String code = demonstrationListBean.getCode();
                        if (code.equals("200")) {
                            List<DemonstrationListBean.DataBean.RowsBean> data = demonstrationListBean.getData().getRows();
                            rowsBeans.addAll(data);
                            demonstrationListAdapter.addMoreData(data);
                        } else {
                            xRecyclerView.setNoMore(true);
                            ToastUtil.setToast("最后数据");
                        }
                    }
                });
    }

    //获取省市区列表
    private void getAllpackageList() {

        RxHttpUtils.createApi(DemonstrationApi.class)
                .getProvinceList()
                .compose(Transformer.<String>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<String>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(String bean) {
                        CityBean cityBean = GsonUtils.fromJson(bean, CityBean.class);
                        if (cityBean == null || cityBean.getData() == null || cityBean.getData().isEmpty()) {
                            return;
                        }
                        if (cityBean.getCode() == 200) {
                            setPickViewData(bean);
                        }
                    }
                });
    }

    //省市区数据拼接
    private void setPickViewData(String json) {

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
//        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        jsonBean = (ArrayList<CityModel>) parseData(json);//用Gson 转成实体
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            List<CityModel> twoCity = jsonBean.get(i).getChildren();
            if (twoCity != null && twoCity.size() > 0) {
                for (int c = 0; c < jsonBean.get(i).getChildren().size(); c++) {//遍历该省份的所有城市
                    String cityName = jsonBean.get(i).getChildren().get(c).getName();
                    cityList.add(cityName);//添加城市

                    ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表
                    List<CityModel> threeCity = jsonBean.get(i).getChildren().get(c).getChildren();
                    if (threeCity != null && threeCity.size() > 0) {
                        for (int j = 0; j < threeCity.size(); j++) {
                            city_AreaList.add(threeCity.get(j).getName());
                        }
                    }
                    //city_AreaList.addAll( jsonBean.get(i).getChildren().get(c));
                    province_AreaList.add(city_AreaList);//添加该省所有地区数据
                }
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }
        showPickerView();
    }

    //省市区三级联动控件
    private void showPickerView() {
        // 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                String tx = opt1tx + opt2tx + opt3tx;
                Log.i("bbbb", jsonBean.get(options1).getId() + "-" + jsonBean.get(options1).getChildren().get(options2).getId() + "-" + jsonBean.get(options1).getChildren().get(options2).getChildren().get(options3).getId());
                qy_tv.setText(jsonBean.get(options1).getName() + "-" + jsonBean.get(options1).getChildren().get(options2).getName() + "-" + jsonBean.get(options1).getChildren().get(options2).getChildren().get(options3).getName());
                regionId = jsonBean.get(options1).getChildren().get(options2).getChildren().get(options3).getId();
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    //gson 解析
    public List<CityModel> parseData(String result) {//Gson 解析
        CityBean cityBean = GsonUtils.fromJson(result, CityBean.class);
        if (cityBean == null || cityBean.getData() == null || cityBean.getData().isEmpty()) {
            return null;
        }
        return cityBean.getData();
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(DemonstrationListActivity.this);
    }
}
