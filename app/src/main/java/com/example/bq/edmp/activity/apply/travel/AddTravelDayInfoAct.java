package com.example.bq.edmp.activity.apply.travel;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.AddPayInfoAct;
import com.example.bq.edmp.activity.apply.GridImageAdapter;
import com.example.bq.edmp.activity.apply.LocalNewMedia;
import com.example.bq.edmp.activity.apply.activity.UpdatePayInfoAct;
import com.example.bq.edmp.activity.apply.bean.AddApplyPayBean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.ActivityUtils;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.DataUtils;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * 添加日程信息（差旅）
 */
public class AddTravelDayInfoAct extends BaseTitleActivity {

    public static Intent newIntent(Context context, PayInfoBean payInfoBean) {
        Intent intent = new Intent(context, AddTravelDayInfoAct.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.ID, payInfoBean);
        intent.putExtras(bundle);
        return intent;
    }

    @BindView(R.id.tv_start_time)
    TextView mTvStartTime;
    @BindView(R.id.tv_start_city)
    EditText mTvStartCity;
    @BindView(R.id.tv_end_time)
    TextView mTvEndTime;
    @BindView(R.id.tv_end_city)
    EditText mTvEndCity;
    @BindView(R.id.tv_aircraft)
    TextView mTvAircraft;
    @BindView(R.id.tv_train)
    TextView mTvTrain;
    @BindView(R.id.tv_bus)
    TextView mTvBus;
    @BindView(R.id.tv_car)
    TextView mTvCar;
    @BindView(R.id.tv_car_money)
    EditText mEtCarMoney;
    @BindView(R.id.tv_day)
    EditText mEtDay;
    @BindView(R.id.et_subsidy_money)
    EditText mEtSubsidyMoney;
    private TimePickerView StartTime;//开始时间选择器
    private TimePickerView EndtTime;//结束时间选择器

    @BindView(R.id.btn_sure)
    TextView mBtnSave;
    @BindView(R.id.pic_recyclerview)
    RecyclerView mRecyclerView;

    private String transport="";//交通工具
    private GridImageAdapter mAdapter;
    private int maxSelectNum = 9;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<LocalNewMedia> newList = new ArrayList<>();

    private int chooseMode = PictureMimeType.ofAll();
    List<String> list = new ArrayList<>();
    private int themeId;
    private PayInfoBean payInfoBean = null;
    private ILoadingView loading_dialog;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_add_travel_day_info;
    }

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        txtTabTitle.setText("添加日程信息");
        loading_dialog = new LoadingDialog(this);
    }

    @Override
    protected void initData() {
        themeId = R.style.picture_QQ_style;
        payInfoBean = (PayInfoBean) getIntent().getSerializableExtra(Constant.ID);
        if (null == payInfoBean) {
            ToastUtil.setToast("数据错误");
            finish();
        }
        setStartTime();
        setEndTime();
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new GridImageAdapter(getApplicationContext(), onAddPicClickListener);
        mAdapter.setList(selectList);
        mAdapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(ReleaseActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(AddTravelDayInfoAct.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(AddTravelDayInfoAct.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(AddTravelDayInfoAct.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(AddTravelDayInfoAct.this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(getApplicationContext());
                } else {
                    ToastUtil.setToast(getString(R.string.picture_jurisdiction));
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });


        chooseMode = PictureMimeType.ofImage();
    }

    @Override
    protected void initListener() {
        mTvAircraft.setOnClickListener(this);
        mTvTrain.setOnClickListener(this);
        mTvBus.setOnClickListener(this);
        mTvCar.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mTvStartTime.setOnClickListener(this);
        mTvEndTime.setOnClickListener(this);
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
                mTvStartTime.setText(DataUtils.getTime(date, "yyyy-MM-dd HH:mm"));
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
                .setRangDate(startDate, selectedDate)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "")
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
        EndtTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mTvEndTime.setText(DataUtils.getTime(date, "yyyy-MM-dd HH:mm"));
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
                .setRangDate(startDate, selectedDate)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "")
                .build();
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_aircraft:
                transport = "飞机";
                mTvAircraft.setBackgroundResource(R.drawable.btn_red_shape_bg);
                mTvTrain.setBackgroundResource(R.drawable.btn_red_gray_shape_bg);
                mTvBus.setBackgroundResource(R.drawable.btn_red_gray_shape_bg);
                mTvCar.setBackgroundResource(R.drawable.btn_red_gray_shape_bg);
                mTvAircraft.setTextColor(getResources().getColor(R.color.white));
                mTvTrain.setTextColor(getResources().getColor(R.color.colorf9));
                mTvBus.setTextColor(getResources().getColor(R.color.colorf9));
                mTvCar.setTextColor(getResources().getColor(R.color.colorf9));
                break;
            case R.id.tv_train:
                transport = "火车";
                mTvAircraft.setBackgroundResource(R.drawable.btn_red_gray_shape_bg);
                mTvTrain.setBackgroundResource(R.drawable.btn_red_shape_bg);
                mTvBus.setBackgroundResource(R.drawable.btn_red_gray_shape_bg);
                mTvCar.setBackgroundResource(R.drawable.btn_red_gray_shape_bg);
                mTvAircraft.setTextColor(getResources().getColor(R.color.colorf9));
                mTvTrain.setTextColor(getResources().getColor(R.color.white));
                mTvBus.setTextColor(getResources().getColor(R.color.colorf9));
                mTvCar.setTextColor(getResources().getColor(R.color.colorf9));
                break;
            case R.id.tv_bus:
                transport = "汽车";
                mTvAircraft.setBackgroundResource(R.drawable.btn_red_gray_shape_bg);
                mTvTrain.setBackgroundResource(R.drawable.btn_red_gray_shape_bg);
                mTvBus.setBackgroundResource(R.drawable.btn_red_shape_bg);
                mTvCar.setBackgroundResource(R.drawable.btn_red_gray_shape_bg);
                mTvAircraft.setTextColor(getResources().getColor(R.color.colorf9));
                mTvTrain.setTextColor(getResources().getColor(R.color.colorf9));
                mTvBus.setTextColor(getResources().getColor(R.color.white));
                mTvCar.setTextColor(getResources().getColor(R.color.colorf9));
                break;
            case R.id.tv_car:
                transport = "私家车";
                mTvAircraft.setBackgroundResource(R.drawable.btn_red_gray_shape_bg);
                mTvTrain.setBackgroundResource(R.drawable.btn_red_gray_shape_bg);
                mTvBus.setBackgroundResource(R.drawable.btn_red_gray_shape_bg);
                mTvCar.setBackgroundResource(R.drawable.btn_red_shape_bg);
                mTvAircraft.setTextColor(getResources().getColor(R.color.colorf9));
                mTvTrain.setTextColor(getResources().getColor(R.color.colorf9));
                mTvBus.setTextColor(getResources().getColor(R.color.colorf9));
                mTvCar.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.btn_sure:
                checkAddData();
//                PayInfoBean bean = new PayInfoBean();
//                bean.setDesc(mEtProDesc.getText().toString());
//                bean.setMoney(mEtProMoney.getText().toString());
//                bean.setId("1");
//                bean.setImg_list(newList);
//                Intent intent = new Intent();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("payInfo", bean);
//                intent.putExtras(bundle);
//                setResult(RESULT_OK, intent);
//                finish();
                break;
            case R.id.tv_start_time:
                StartTime.show();
                break;
            case R.id.tv_end_time:
                EndtTime.show();
                break;
        }
    }

    //差旅报销添加日程信息
    private void checkAddData() {
        String startTime = mTvStartTime.getText().toString().trim();
        if ("请选择出发时间".equals(startTime)) {
            ToastUtil.setToast("请选择出发时间");
            return;
        }
        String endTime = mTvEndTime.getText().toString().trim();
        if ("请选择到达时间".equals(endTime)) {
            ToastUtil.setToast("请选择到达时间");
            return;
        }
        String startCity = mTvStartCity.getText().toString().trim();
        if ("".equals(startCity)) {
            ToastUtil.setToast("请填写出发城市");
            return;
        }
        String endCity = mTvEndCity.getText().toString().trim();
        if ("".equals(endCity)) {
            ToastUtil.setToast("请填写到达城市");
            return;
        }
        if("".equals(transport)){
            ToastUtil.setToast("请选择交通工具");
            return;
        }
        String carMoney = mEtCarMoney.getText().toString().trim();
        if ("".equals(carMoney)) {
            ToastUtil.setToast("请填写交通费信息");
            return;
        }
        String tvDays = mEtDay.getText().toString().trim();
        if ("".equals(tvDays)) {
            ToastUtil.setToast("请填写出差天数");
            return;
        }
        if (Integer.parseInt(tvDays)<0.5) {
            ToastUtil.setToast("出差天数必须大于0.5天");
            return;
        }
        String subsidyMoney = mEtSubsidyMoney.getText().toString().trim();
        if ("".equals(subsidyMoney)) {
            ToastUtil.setToast("请填写出补贴金额");
            return;
        }
        if (selectList.size() <= 0) {
            ToastUtil.setToast("请上传单据");
            return;
        }
        initData(endCity, endTime, tvDays, payInfoBean.getId() + "", startCity, startTime, subsidyMoney, transport, carMoney);
    }

    //参数初始化
    private void initData(String endCity, String endTime, String tvDays, String reimburserId, String startCity, String startTime, String subsidyMoney, String transport, String carMoney) {
        Map<String, Object> paramsMap = new HashMap<>();
        List<File> list = new ArrayList<>();
        String sign = MD5Util.encode("arriveRegion=" + endCity + "&arriveTime=" + endTime + "&days=" + tvDays + "&reimburserId=" + reimburserId + "&setOutRegion=" + startCity + "&setOutTime=" + startTime + "&subsidy=" + subsidyMoney + "&transport=" + transport + "&transportFee=" + carMoney);
        paramsMap = new HashMap<>();
        paramsMap.put("arriveRegion", endCity);
        paramsMap.put("arriveTime", endTime);
        paramsMap.put("days", tvDays);
        paramsMap.put("reimburserId", reimburserId);
        paramsMap.put("setOutRegion", startCity);
        paramsMap.put("setOutTime", startTime);
        paramsMap.put("subsidy", subsidyMoney);
        paramsMap.put("transport", transport);
        paramsMap.put("transportFee", carMoney);
        paramsMap.put("sign", sign);
        List<String> filePaths = new ArrayList<>();
        for (int i = 0; i < selectList.size(); i++) {
            filePaths.add(selectList.get(i).getPath());
        }
        uploadImgAndPar("http://192.168.0.188:8080/reimburser/traveling/newsave", "billFile", paramsMap, filePaths);
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
                        Log.e("allen", "上传失败: " + errorMsg);
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
                    }

                    @Override
                    protected void onSuccess(ResponseBody responseBody) {
                        AddApplyPayBean obj = null;
                        try {
                            String json = new String(responseBody.bytes());
                            Gson gson = new Gson();
                            obj = gson.fromJson(json, AddApplyPayBean.class);
                        } catch (Exception ex) {

                        }
//                        //循环添加图片信息
                        for (int i = 0; i < selectList.size(); i++) {
                            LocalNewMedia localnewMedia = new LocalNewMedia();
                            localnewMedia.setChecked(selectList.get(i).isChecked());
                            localnewMedia.setCompressed(selectList.get(i).isCompressed());
                            localnewMedia.setCompressPath(selectList.get(i).getCompressPath());
                            localnewMedia.setCut(selectList.get(i).isCut());
                            localnewMedia.setCutPath(selectList.get(i).getCutPath());
                            localnewMedia.setDuration(selectList.get(i).getDuration());
                            localnewMedia.setHeight(selectList.get(i).getHeight());
                            localnewMedia.setMimeType(selectList.get(i).getMimeType());
                            localnewMedia.setNum(selectList.get(i).getNum());
                            localnewMedia.setPath(selectList.get(i).getPath());
                            localnewMedia.setPictureType(selectList.get(i).getPictureType());
                            localnewMedia.setPosition(selectList.get(i).getPosition());
                            localnewMedia.setWidth(selectList.get(i).getWidth());
                            newList.add(localnewMedia);
                        }
                        PayInfoBean bean = new PayInfoBean();
                        bean.setClickType(payInfoBean.getClickType());
                        bean.setIdx(obj.getData().getId().getIdx() + "");
                        bean.setId(payInfoBean.getId());
                        bean.setArriveRegion(mTvEndCity.getText().toString().trim());
                        bean.setArriveTime(mTvEndTime.getText().toString().trim());
                        bean.setDays(mEtDay.getText().toString().trim());
                        bean.setSetOutRegion(mTvStartCity.getText().toString().trim());
                        bean.setSetOutTime(mTvStartTime.getText().toString().trim());
                        bean.setSubsidy(mEtSubsidyMoney.getText().toString().trim());
                        bean.setTransport(transport);
                        bean.setTransportFee(mEtCarMoney.getText().toString());
                        bean.setImg_list(newList);
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("payInfo", bean);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = true;
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(AddTravelDayInfoAct.this)
                        .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(maxSelectNum)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数
                        .selectionMode(true ?
                                PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        .previewVideo(false)// 是否可预览视频
                        .enablePreviewAudio(false) // 是否可播放音频
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                        .enableCrop(false)// 是否裁剪
                        .compress(false)// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        //.compressSavePath(getPath())//压缩图片保存地址
                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(0, 0)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(false ? false : true)// 是否显示uCrop工具栏，默认不显示
                        .isGif(false)// 是否显示gif图片
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .circleDimmedLayer(false)// 是否圆形裁剪
                        .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(false)// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
                        //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled(true) // 裁剪是否可旋转图片
                        //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()//显示多少秒以内的视频or音频也可适用
                        //.recordVideoSecond()//录制视频秒数 默认60s
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case PictureConfig.CHOOSE_REQUEST:
                        // 图片选择结果回调
                        selectList = PictureSelector.obtainMultipleResult(data);
                        // 例如 LocalMedia 里面返回三种path
                        // 1.media.getPath(); 为原图path
                        // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                        // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                        // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                        list.clear();
                        for (LocalMedia media : selectList) {
                            Log.i("图片-----》", media.getPath());
                            list.add(media.getPath());
                        }
                        //TODO 上传图片接口
//                        new API(LeaveActivity.this, Base.getClassType()).uploadApprovalImg(list);
                        mAdapter.setList(selectList);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
                break;
        }
    }

}
