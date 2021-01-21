package com.example.bq.edmp.work.modelfield.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.GsonUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.detection.adapter.DetectionPzListAdp;
import com.example.bq.edmp.work.marketing.api.CustomerManagementApi;
import com.example.bq.edmp.work.marketing.bean.CityBean;
import com.example.bq.edmp.work.marketing.bean.CityModel;
import com.example.bq.edmp.work.modelfield.adapter.DemonstrationZWAdp;
import com.example.bq.edmp.work.modelfield.adapter.YearsListAdapter;
import com.example.bq.edmp.work.modelfield.api.DemonstrationApi;
import com.example.bq.edmp.work.modelfield.bean.YearsBean;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.OrderZuoWuBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.http.Field;

/*
 * 添加品种示范
 * */
public class AddDemonstrationActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.tv_zw_lx)
    TextView tv_zw_lx;
    @BindView(R.id.tv_pz_lx)
    TextView tv_pz_lx;
    @BindView(R.id.tv_qy_lx)
    TextView tv_qy_lx;
    @BindView(R.id.et_address_lx)
    EditText et_address_lx;
    @BindView(R.id.et_dw_lx)
    TextView et_dw_lx;
    @BindView(R.id.tv_nd_lx)
    TextView tv_nd_lx;
    @BindView(R.id.tv_submit)
    TextView tv_submit;


    private LoadingDialog loadingDialog;
    PopupWindow mTypePopuWindow;
    private String mCropId = "";//作物id
    private String mVarietyId = "";//品种id
    private String mRegionId = "";//区域id
    private ArrayList<CityModel> jsonBean;
    //省
    private List<CityModel> options1Items = new ArrayList<>();
    //  市
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    //  区
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_demonstration;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(AddDemonstrationActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("添加品种示范");

    }


    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        tv_zw_lx.setOnClickListener(this);
        tv_pz_lx.setOnClickListener(this);
        tv_qy_lx.setOnClickListener(this);
        tv_nd_lx.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.tv_zw_lx://作物
                gainZW();
                break;
            case R.id.tv_pz_lx://品种
                gainPZ();
                break;
            case R.id.tv_qy_lx://区域
                getAllpackageList();
                break;
            case R.id.tv_nd_lx://年度
                gainND();
                break;
            case R.id.tv_submit://确认添加
                if (tv_zw_lx.getText().equals("")) {
                    ToastUtil.setToast("请选择作物");
                    break;
                }
                if (tv_pz_lx.getText().equals("")) {
                    ToastUtil.setToast("请选择种植品种");
                    break;
                }
                if (tv_qy_lx.getText().equals("")) {
                    ToastUtil.setToast("请选择种植区域");
                    break;
                }
                if (et_dw_lx.getText().toString().equals("")) {
                    ToastUtil.setToast("请输入单位");
                    break;
                }
                if (tv_nd_lx.getText().equals("")) {
                    ToastUtil.setToast("请选择种植年度");
                    break;
                }
                gainSubimt();
                break;
        }
    }

    //确认添加
    private void gainSubimt() {

        String sign = MD5Util.encode("address=" + et_address_lx.getText().toString() + "&companyName=" + et_dw_lx.getText().toString() + "&cropId=" + mCropId + "&regionId=" + mRegionId
                + "&varietyId=" + mVarietyId + "&years=" + tv_nd_lx.getText().toString());

        RxHttpUtils.createApi(DemonstrationApi.class)
                .setSubimtData(et_address_lx.getText().toString(), et_dw_lx.getText().toString(), mCropId, mRegionId, mVarietyId, tv_nd_lx.getText().toString(), sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        int code = baseABean.getCode();
                        if (code == 200) {
                            ToastUtil.setToast("添加成功");
                            startActivity(new Intent(AddDemonstrationActivity.this, DemonstrationListActivity.class));
                            finish();
                        } else {
                            ToastUtil.setToast("添加失败");
                        }
                    }
                });

    }

    //年度
    private void gainND() {
        if (et_dw_lx.getText().equals("")) {
            ToastUtil.setToast("请输入示范单位名称");
            return;
        }
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
                tv_nd_lx.setText(strings.get(pos));
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

    //获取省市区列表
    private void getAllpackageList() {
        if (tv_pz_lx.getText().equals("")) {
            ToastUtil.setToast("请先选择品种！");
            return;
        }

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

    //品种
    private void gainPZ() {
        if (tv_zw_lx.getText().equals("")) {
            ToastUtil.setToast("请先选择作物！");
            return;
        }

        String sign = MD5Util.encode("cropId=" + mCropId);

        RxHttpUtils.createApi(DemonstrationApi.class)
                .getPZData(mCropId, sign)
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
                            getPzDataList(orderZuoWuBean);
                        } else {
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    //作物
    private void gainZW() {
        RxHttpUtils.createApi(DemonstrationApi.class)
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
                            getZWDataList(orderZuoWuBean);
                        } else {
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    //获取作物数据PopuWindow
    private void getZWDataList(final OrderZuoWuBean orderZuoWuBean) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        DemonstrationZWAdp demonstrationZWAdp = new DemonstrationZWAdp(orderZuoWuBean.getData());
        myRecyclerViewOne.setAdapter(demonstrationZWAdp);
        demonstrationZWAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mCropId = orderZuoWuBean.getData().get(position).getId();
                tv_zw_lx.setText(orderZuoWuBean.getData().get(position).getName());
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

    //获取品种数据PopuWindow
    private void getPzDataList(final OrderZuoWuBean orderZuoWuBean) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        DemonstrationZWAdp demonstrationZWAdp = new DemonstrationZWAdp(orderZuoWuBean.getData());
        myRecyclerViewOne.setAdapter(demonstrationZWAdp);
        demonstrationZWAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mVarietyId = orderZuoWuBean.getData().get(position).getId();
                tv_pz_lx.setText(orderZuoWuBean.getData().get(position).getName());
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
                tv_qy_lx.setText(jsonBean.get(options1).getName() + "-" + jsonBean.get(options1).getChildren().get(options2).getName() + "-" + jsonBean.get(options1).getChildren().get(options2).getChildren().get(options3).getName());
                mRegionId = jsonBean.get(options1).getChildren().get(options2).getChildren().get(options3).getId();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(AddDemonstrationActivity.this);
    }
}
