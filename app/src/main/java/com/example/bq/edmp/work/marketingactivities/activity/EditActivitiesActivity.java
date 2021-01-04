package com.example.bq.edmp.work.marketingactivities.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.download.DownloadObserver;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.GsonUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.url.MimeType;
import com.example.bq.edmp.utils.ActivityUtils;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.DataUtils;
import com.example.bq.edmp.utils.DateUtils;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.OpenFiles;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.phoneUtils;
import com.example.bq.edmp.work.marketing.api.CustomerManagementApi;
import com.example.bq.edmp.work.marketing.bean.CityBean;
import com.example.bq.edmp.work.marketing.bean.CityModel;
import com.example.bq.edmp.work.marketing.bean.CustomerDetailsBean;
import com.example.bq.edmp.work.marketingactivities.adapter.CustomerListAdp;
import com.example.bq.edmp.work.marketingactivities.adapter.DepartmengListAdp;
import com.example.bq.edmp.work.marketingactivities.adapter.FileUploadGridImageAdapter;
import com.example.bq.edmp.work.marketingactivities.api.MarketingActivitiesApi;
import com.example.bq.edmp.work.marketingactivities.bean.CustomerListBean;
import com.example.bq.edmp.work.marketingactivities.bean.DepartmengListBean;
import com.example.bq.edmp.work.marketingactivities.bean.MarketingActivitiesDetailsBean;
import com.google.gson.Gson;
import com.joanzapata.pdfview.PDFView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.ResponseBody;

public class EditActivitiesActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, EditActivitiesActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.btn_submit)
    TextView mBtnSubmit;//提交按钮
    @BindView(R.id.btn_save)
    TextView mBtnSave;//保存按钮
    @BindView(R.id.btn_del)
    TextView mBtnDel;//删除按钮

    @BindView(R.id.pic_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_name)
    EditText mTvName;//活动名称
    @BindView(R.id.tv_distribution_area)
    TextView mTvDistributionArea;//活动地点省市区
    @BindView(R.id.tv_detailed_address)
    EditText mTvDetailedAddress;//详细地址
    @BindView(R.id.tv_start_time)
    TextView mTvStartTime;//开始时间
    @BindView(R.id.tv_end_time)
    TextView mTvEndTime;//结束时间
    @BindView(R.id.tv_cooperative_customers)
    TextView mTvCooperativeCustomers;//合作客户
    @BindView(R.id.tv_purpose)
    EditText mTvPurpose;//活动目的
    @BindView(R.id.tv_department)
    TextView mTvDepartment;//实施部门
    @BindView(R.id.tv_person)
    EditText mTvPerson;//负责人
    @BindView(R.id.tv_money)
    EditText mTvMoney;//活动经费
    private FileUploadGridImageAdapter mAdapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 9;
    private ILoadingView loading_dialog;
    private CustomerDetailsBean customerDetailsBean = null;
    public final static int CHOOSE_FILE_CODE = 1000;
    private String distributionAreaId = "";//活动地点id
    private TimePickerView StartTime;//时间选择器开始时间
    private TimePickerView EndTime;//时间选择器结束时间
    private String id = "";
    private PopupWindow mTypePopuWindow;
    private CustomerListBean customerListBean;//承包人数据源
    private DepartmengListBean departmengListBean;//部门据源
    private int DepartmentId = 0;//部门id
    private int CooperativeCustomersId = 0;//合伙人id
    private MarketingActivitiesDetailsBean marketingActivitiesDetailsBean = null;
    private ArrayList<CityModel> jsonBean;
    //省
    private List<CityModel> options1Items = new ArrayList<>();
    //  市
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    //  区
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_editactivitys;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("修改");
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new FileUploadGridImageAdapter(getApplicationContext(), onAddPicClickListener);
        mAdapter.setList(selectList);
        mAdapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new FileUploadGridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    int mediaType = media.getFileType();
                    String path = media.getPath();
                    File file = new File(path);
                    if (!file.exists()) {
                        download(mediaType, media.getFileName(), position, media.getDownLoadUrl());
                        return;
                    }
                    switch (mediaType) {
                        case 1:
                            startActivity(OpenFiles.getPPTFileIntent(path));
                            break;
                        case 2:
                            startActivity(OpenFiles.getPdfFileIntent(path));
                            break;
                        case 3:
                            startActivity(OpenFiles.getWordFileIntent(path));
                            break;
                        case 4:
                            startActivity(OpenFiles.getExcelFileIntent(path));
                            break;
                        case 5:
                            startActivity(OpenFiles.getWordFileIntent(path));
                            break;
                    }
                }
            }
        });
        mAdapter.setOnDelterImg(new FileUploadGridImageAdapter.DeleteImg() {
            @Override
            public void deleteImgList(int postion) {
                deleteAttachment(marketingActivitiesDetailsBean.getData().getActivityItems().get(postion).getId() + "", postion);
            }
        });
        getMarketingActivitiesDetails("");
    }

    private FileUploadGridImageAdapter.onAddPicClickListener onAddPicClickListener = new FileUploadGridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = true;
            if (mode) {
                //跳转系统文件
                List mimeTypes = new ArrayList<String>();
                mimeTypes.add(MimeType.DOC);
                mimeTypes.add(MimeType.DOCX);
                mimeTypes.add(MimeType.PDF);
                mimeTypes.add(MimeType.PPT);
                mimeTypes.add(MimeType.XLS);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                //设置doc,docx,pdf,ppt,xls 5种类型
                intent.setType("application/msword|application/vnd.openxmlformats-officedocument.wordprocessingml.document|application/pdf|application/msword|application/vnd.ms-powerpoint|application/vnd.ms-excel");
                //在API>=19之后设置多个类型采用以下方式，setType不再支持多个类型
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{MimeType.DOC, MimeType.DOCX, MimeType.PDF});
                }
                startActivityForResult(intent, CHOOSE_FILE_CODE);
            }
        }

    };

    @Override
    protected void initData() {
        setStartTime();
        setEndTime();
    }

    @Override
    protected void initListener() {
        mBtnSubmit.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mTvDistributionArea.setOnClickListener(this);
        mTvStartTime.setOnClickListener(this);
        mTvEndTime.setOnClickListener(this);
        mBtnDel.setOnClickListener(this);
        mTvDepartment.setOnClickListener(this);
        mTvCooperativeCustomers.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                checkAddData(2);
                break;
            case R.id.btn_save:
                checkAddData(1);
                break;
            case R.id.btn_del:
                deleteActivities(id);
                break;
            case R.id.tv_start_time:
                StartTime.show();
                break;
            case R.id.tv_end_time:
                EndTime.show();
                break;
            case R.id.tv_distribution_area:
                getAllpackageList();
                break;
            case R.id.tv_department:
                getDepartmentList();
                break;
            case R.id.tv_cooperative_customers:
                getCooperativeCustomersList();
                break;

        }
    }

    //验证活动数据
    private void checkAddData(int type) {
        //活动名称
        String name = mTvName.getText().toString().trim();
        if (name.isEmpty()) {
            ToastUtil.setToast("请输入活动名称");
            return;
        }
        //活动区域
        if ("".equals(distributionAreaId)) {
            ToastUtil.setToast("请选择活动区域");
            return;
        }
        //详细地址
        String DetailedAddress = mTvDetailedAddress.getText().toString();
        //开始时间
        String StartTime = mTvStartTime.getText().toString();
        if ("".equals(StartTime)) {
            ToastUtil.setToast("请选择开始时间");
            return;
        }
        //结束时间
        String EndtTime = mTvEndTime.getText().toString();
        if ("".equals(EndtTime)) {
            ToastUtil.setToast("请选择结束时间");
            return;
        }
        //合作客户
        String CooperativeCustomers = mTvCooperativeCustomers.getText().toString();
        if ("".equals(CooperativeCustomers)) {
            ToastUtil.setToast("请选择合作客户");
            return;
        }
        //活动目的
        String Purpose = mTvPurpose.getText().toString().trim();
        if ("".equals(Purpose)) {
            ToastUtil.setToast("请输入活动目的");
            return;
        }
        //实施部门
        String Department = mTvDepartment.getText().toString().trim();
        if ("".equals(Department)) {
            ToastUtil.setToast("请选择实施部门");
            return;
        }
        //负责人
        String Person = mTvPerson.getText().toString().trim();
        if ("".equals(Person)) {
            ToastUtil.setToast("请输入活动负责人");
            return;
        }
        //活动经费
        String Money = mTvMoney.getText().toString().trim();
        if ("".equals(Money)) {
            ToastUtil.setToast("请输入活动经费");
            return;
        }
        if (DateUtils.timeToStamp(StartTime) > DateUtils.timeToStamp(EndtTime)) {
            ToastUtil.setToast("开始时间不能大于结束时间");
            return;
        }
        if (type == 2) {
            if (selectList.size() <= 0) {
                ToastUtil.setToast("请上传附件");
                return;
            }
        }
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < selectList.size(); i++) {
            //1 新添加 附件
            if (selectList.get(i).getOperationType() == 1) {
                list.add(selectList.get(i).getPath());
            }
        }
        initData(DetailedAddress, Money, CooperativeCustomersId + "", DepartmentId + "", EndtTime, id, name, Purpose, distributionAreaId, Person, StartTime, list,type+"");
    }

    //参数初始化
    private void initData(String address, String advanceLoan, String customerId, String deptId, String endTime, String id, String name, String purpose, String region, String responsiblePeople, String startTime, List<String> list,String type) {
        Map<String, Object> paramsMap = new HashMap<>();
        String sign = MD5Util.encode("address=" + address + "&advanceLoan=" + advanceLoan + "&customerId=" + customerId + "&deptId=" + deptId + "&endTime=" + endTime + "&id=" + id + "&name=" + name + "&purpose=" + purpose + "&region=" + region + "&responsiblePeople=" + responsiblePeople + "&startTime=" + startTime+ "&types=" + type);
        paramsMap = new HashMap<>();
        paramsMap.put("address", address);
        paramsMap.put("advanceLoan", advanceLoan);
        paramsMap.put("customerId", customerId);
        paramsMap.put("deptId", deptId);
        paramsMap.put("endTime", endTime);
        paramsMap.put("id", id);
        paramsMap.put("name", name);
        paramsMap.put("purpose", purpose);
        paramsMap.put("region", region);
        paramsMap.put("responsiblePeople", responsiblePeople);
        paramsMap.put("startTime", startTime);
        paramsMap.put("types", type);
        paramsMap.put("sign", sign);
        List<String> filePaths = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            filePaths.add(list.get(i));
        }
        uploadImgAndPar(BaseApi.base_url_marketing + "activity/save", "activityAnnex", paramsMap, filePaths);
    }

    //图片上传接口
    private void uploadImgAndPar(String uploadUrl, String fileName, Map<String, Object> paramsMap, List<String> uploadPaths) {

        RxHttpUtils.uploadImagesAndParams(uploadUrl, fileName, paramsMap, uploadPaths)
                .compose(Transformer.<ResponseBody>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<ResponseBody>() {

                    @Override
                    protected String setTag() {
                        return "uploadImg";
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        ActivityUtils.getMsg(errorMsg, getApplicationContext());
                    }

                    @Override
                    protected void onSuccess(ResponseBody responseBody) {
                        BaseABean obj = null;
                        try {
                            String json = new String(responseBody.bytes());
                            Gson gson = new Gson();
                            obj = gson.fromJson(json, BaseABean.class);
                        } catch (Exception ex) {

                        }
                        if (-99 == obj.getCode()) {
                            ToastUtil.setToast(obj.getMsg());
                            return;
                        }
                        finish();

                    }
                });
    }

    //获取省市区列表
    private void getAllpackageList() {
        RxHttpUtils.createApi(CustomerManagementApi.class)
                .getProvinceList()
                .compose(Transformer.<String>switchSchedulers(loading_dialog))
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
                mTvDistributionArea.setText(jsonBean.get(options1).getName() + "-" + jsonBean.get(options1).getChildren().get(options2).getName() + "-" + jsonBean.get(options1).getChildren().get(options2).getChildren().get(options3).getName());
                distributionAreaId = jsonBean.get(options1).getChildren().get(options2).getChildren().get(options3).getId();
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

    //开始时间
    private void setStartTime() {
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
        selectedDate.set(2200, 12, 31);
        //时间选择器 ，自定义布局
        StartTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mTvStartTime.setText(DataUtils.getTime(date, "yyyy-MM-dd"));
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
                .setDate(startDate)
//                .setRangDate(startDate, selectedDate)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .build();
    }

    //结束时间
    private void setEndTime() {
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
        selectedDate.set(2200, 12, 31);
        //时间选择器 ，自定义布局
        EndTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mTvEndTime.setText(DataUtils.getTime(date, "yyyy-MM-dd"));
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
                .setDate(startDate)
//                .setRangDate(startDate, selectedDate)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .build();
    }

    //文件下载
    private void download(final int type, final String fileName, final int position, String url) {
        if (loading_dialog != null) {
            loading_dialog.showLoadingView();
        }
        RxHttpUtils
                .downloadFile(url)
                .subscribe(new DownloadObserver(fileName, getApplicationContext().getExternalFilesDir(null).getPath()) {
                    //可以通过配置tag用于取消下载请求
                    @Override
                    protected String setTag() {
                        return "download";
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        if (loading_dialog != null) {
                            loading_dialog.hideLoadingView();
                        }
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(long bytesRead, long contentLength, float progress, boolean done, String filePath) {
                        if (done) {
                            if (loading_dialog != null) {
                                loading_dialog.hideLoadingView();
                            }
                            selectList.get(position).setPath(getApplicationContext().getExternalFilesDir(null).getPath() + "/" + fileName);
                            selectList.get(position).setFileType(type);
                            mAdapter.notifyDataSetChanged();
                            startActivity(OpenFiles.getPdfFileIntent(getApplicationContext().getExternalFilesDir(null).getPath() + "/" + fileName));
                        }
                    }
                });
    }

    //获取合作客户列表
    private void getCooperativeCustomersList() {
        RxHttpUtils.createApi(MarketingActivitiesApi.class)
                .getCustomerList()
                .compose(Transformer.<CustomerListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<CustomerListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CustomerListBean bean) {
                        if (bean.getCode() == 200) {
                            customerListBean = bean;
                            showCustomerLis();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }

                    }
                });
    }

    //合作客户列表PopuWindow
    private void showCustomerLis() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        CustomerListAdp contractorListAdp = new CustomerListAdp(customerListBean.getData());
        myRecyclerViewOne.setAdapter(contractorListAdp);
        contractorListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CooperativeCustomersId = customerListBean.getData().get(position).getId();
                mTvCooperativeCustomers.setText(customerListBean.getData().get(position).getName());
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

    //获取部门列表
    private void getDepartmentList() {
        RxHttpUtils.createApi(MarketingActivitiesApi.class)
                .getDepartmentList()
                .compose(Transformer.<DepartmengListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<DepartmengListBean>() {
                    @Override
                    protected void onError(String errorMsg) {

                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DepartmengListBean bean) {
                        if (bean.getCode() == 200) {
                            departmengListBean = bean;
                            showDepartmentList();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //部门列表PopuWindow
    private void showDepartmentList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        DepartmengListAdp contractorListAdp = new DepartmengListAdp(departmengListBean.getData());
        myRecyclerViewOne.setAdapter(contractorListAdp);
        contractorListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DepartmentId = departmengListBean.getData().get(position).getId();
                mTvDepartment.setText(departmengListBean.getData().get(position).getName());
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

    //获取活动详情
    private void getMarketingActivitiesDetails(String type) {
        String sign = MD5Util.encode("id=" + id + "&type=" + type);
        RxHttpUtils.createApi(MarketingActivitiesApi.class)
                .getActivitieDetails(id, type, sign)
                .compose(Transformer.<MarketingActivitiesDetailsBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<MarketingActivitiesDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(MarketingActivitiesDetailsBean bean) {
                        marketingActivitiesDetailsBean = bean;
                        setData(bean.getData());
                    }
                });
    }

    //详情赋值
    private void setData(MarketingActivitiesDetailsBean.DataBean bean) {
        mTvName.setText(bean.getName());
        mTvDistributionArea.setText(bean.getRegion());
        mTvDetailedAddress.setText(bean.getAddress());
        mTvStartTime.setText(bean.getStartTime());
        mTvEndTime.setText(bean.getEndTime());
        mTvCooperativeCustomers.setText(bean.getCustomerName());
        mTvPurpose.setText(bean.getPurpose());
        mTvDepartment.setText(bean.getDeptName());
        mTvPerson.setText(bean.getResponsiblePeople());
        mTvMoney.setText("￥" + MoneyUtils.formatMoney(bean.getAdvanceLoan()));
        DepartmentId = bean.getDeptId();
        CooperativeCustomersId = bean.getCustomerId();//合伙人id
        distributionAreaId = bean.getRegion();
        for (int i = 0; i < bean.getActivityItems().size(); i++) {
            LocalMedia localMedia = new LocalMedia();
            if (bean.getActivityItems().get(i).getUri().endsWith(".ppt")) {
                localMedia.setFileType(1);
            } else if (bean.getActivityItems().get(i).getUri().endsWith(".pdf")) {
                localMedia.setFileType(2);
            } else if (bean.getActivityItems().get(i).getUri().endsWith(".docx")) {
                localMedia.setFileType(3);
            } else if (bean.getActivityItems().get(i).getUri().endsWith(".xls")) {
                localMedia.setFileType(4);
            } else if (bean.getActivityItems().get(i).getUri().endsWith(".doc")) {
                localMedia.setFileType(5);
            } else {
                localMedia.setFileType(1);
            }
            String path = bean.getActivityItems().get(i).getUri().substring(bean.getActivityItems().get(i).getUri().lastIndexOf("/") + 1);
            localMedia.setPath(getApplicationContext().getExternalFilesDir(null).getPath() + "/" + path);
            localMedia.setFileName(path);
            localMedia.setDownLoadUrl(BaseApi.activity_img_url + bean.getActivityItems().get(i).getUri());
            localMedia.setOperationType(2);
            selectList.add(localMedia);
        }
        mAdapter.notifyDataSetChanged();
    }

    //删除附件
    private void deleteAttachment(String id, final int position) {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(MarketingActivitiesApi.class)
                .deleteAttachment(id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            selectList.remove(position);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //删除活动
    private void deleteActivities(String id) {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(MarketingActivitiesApi.class)
                .deleteActivities(id, sign)
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case PictureConfig.CHOOSE_REQUEST:
                        selectList = PictureSelector.obtainMultipleResult(data);
                        mAdapter.setList(selectList);
                        mAdapter.notifyDataSetChanged();
                        break;
                    case CHOOSE_FILE_CODE:
                        Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
                        String[] proj = new String[]{MediaStore.Images.Media.DATA};
                        Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
                        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        actualimagecursor.moveToFirst();
                        String img_path = actualimagecursor.getString(actual_image_column_index);
                        LocalMedia localMedia = new LocalMedia();
                        localMedia.setPath(img_path);
                        localMedia.setOperationType(1);
                        if (img_path.endsWith(".ppt")) {
                            localMedia.setFileType(1);
                            selectList.add(localMedia);
                            mAdapter.notifyDataSetChanged();
                        } else if (img_path.endsWith(".pdf")) {
                            localMedia.setFileType(2);
                            selectList.add(localMedia);
                            mAdapter.notifyDataSetChanged();
                        } else if (img_path.endsWith(".docx")) {
                            localMedia.setFileType(3);
                            selectList.add(localMedia);
                            mAdapter.notifyDataSetChanged();
                        } else if (img_path.endsWith(".xls")) {
                            localMedia.setFileType(4);
                            selectList.add(localMedia);
                            mAdapter.notifyDataSetChanged();
                        } else if (img_path.endsWith(".doc")) {
                            localMedia.setFileType(5);
                            selectList.add(localMedia);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.setToast("格式错误");
                        }
                }
                break;
        }
    }
}