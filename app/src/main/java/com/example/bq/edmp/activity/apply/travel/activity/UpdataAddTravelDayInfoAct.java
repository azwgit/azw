package com.example.bq.edmp.activity.apply.travel.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.LocalNewMedia;
import com.example.bq.edmp.activity.apply.ReimbursementApi;
import com.example.bq.edmp.activity.apply.adapter.DeleteGridImageAdapter;
import com.example.bq.edmp.activity.apply.bean.AddApplyPayBean;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.activity.apply.travel.bean.TravelDetailsBean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.ActivityUtils;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.DataUtils;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
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
public class UpdataAddTravelDayInfoAct extends BaseTitleActivity {


    public static Intent newIntent(Context context, PayInfoBean payInfoBean) {
        Intent intent = new Intent(context, UpdataAddTravelDayInfoAct.class);
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
    private DeleteGridImageAdapter mAdapter;
    private int maxSelectNum = 9;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<LocalNewMedia> newList = new ArrayList<>();
    private TravelDetailsBean travelBean=null;
    private int chooseMode = PictureMimeType.ofAll();
    List<String> list = new ArrayList<>();
    private List<LocalMedia> newSelectList = new ArrayList<>();
    private int themeId;
    private PayInfoBean payInfoBean = null;
    private boolean ischeck=false;
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
        getTravelInfo(payInfoBean.getIdx(),payInfoBean.getId());
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new DeleteGridImageAdapter(getApplicationContext(), onAddPicClickListener);
        mAdapter.setList(newSelectList);
        mAdapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new DeleteGridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (newSelectList.size() > 0) {
                    LocalMedia media = newSelectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(ReleaseActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(UpdataAddTravelDayInfoAct.this).themeStyle(themeId).openExternalPreview(position, newSelectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(UpdataAddTravelDayInfoAct.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(UpdataAddTravelDayInfoAct.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
        mAdapter.setOnDelterImg(new DeleteGridImageAdapter.DeleteImg() {
            @Override
            public void deleteImgList(int postion) {
                ischeck=true;
                deleteImag(travelBean.getData().getReimburserItemBills().get(postion).getId()+"",postion,"2",payInfoBean.getId());
            }
        });
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(UpdataAddTravelDayInfoAct.this);
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
        mEtCarMoney.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        //设置字符过滤
        mEtCarMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                //删除“.”后面超过2位后的数据
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        mEtCarMoney.setText(s);
                        mEtCarMoney.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    mEtCarMoney.setText(s);
                    mEtCarMoney.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        mEtCarMoney.setText(s.subSequence(0, 1));
                        mEtCarMoney.setSelection(1);
                        return;
                    }
                }
                //包含. 查看. 前面是否有值
                if(s.toString().trim().contains(".")){
                    String  a=s.toString().trim().substring(0, s.toString().trim().indexOf("."));
                    if(a.length()<=0){
                        s = "0" + s;
                        mEtCarMoney.setText(s);
                        mEtCarMoney.setSelection(2);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
        mEtSubsidyMoney.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        //设置字符过滤
        mEtSubsidyMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                //删除“.”后面超过2位后的数据
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        mEtSubsidyMoney.setText(s);
                        mEtSubsidyMoney.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    mEtSubsidyMoney.setText(s);
                    mEtSubsidyMoney.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        mEtSubsidyMoney.setText(s.subSequence(0, 1));
                        mEtSubsidyMoney.setSelection(1);
                        return;
                    }
                }
                //包含. 查看. 前面是否有值
                if(s.toString().trim().contains(".")){
                    String  a=s.toString().trim().substring(0, s.toString().trim().indexOf("."));
                    if(a.length()<=0){
                        s = "0" + s;
                        mEtSubsidyMoney.setText(s);
                        mEtSubsidyMoney.setSelection(2);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
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
        ivBack.setOnClickListener(this);
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
        EndtTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
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
                break;
            case R.id.tv_start_time:
                StartTime.show();
                break;
            case R.id.tv_end_time:
                EndtTime.show();
                break;
            case R.id.txt_back:
                setData();
                break;
        }
    }

    //差旅报销修改日程信息验证
    private void checkAddData() {
        String startTime = mTvStartTime.getText().toString().trim();
        if ("".equals(startTime)) {
            ToastUtil.setToast("请选择开始时间");
            return;
        }
        String endTime = mTvEndTime.getText().toString().trim();
        if ("".equals(endTime)) {
            ToastUtil.setToast("请填写项目花费");
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
        updataTraveling(endCity, endTime, tvDays, payInfoBean.getIdx(),payInfoBean.getId() + "", startCity, startTime, subsidyMoney, transport, carMoney);
    }
    //修改差旅日程信息
    private void updataTraveling(String endCity, String endTime, String tvDays, String idx, String reimburserId, String startCity, String startTime, String subsidyMoney, final String transport, String carMoney) {
        String sign = MD5Util.encode("arriveRegion=" + endCity + "&arriveTime=" + endTime + "&days=" + tvDays +  "&idx=" +idx+"&reimburserId=" + reimburserId + "&setOutRegion=" + startCity + "&setOutTime=" + startTime + "&subsidy=" + subsidyMoney + "&transport=" + transport + "&transportFee=" + carMoney);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .UpdataTraveling(endCity, endTime,tvDays, idx,reimburserId, startCity, startTime,subsidyMoney,transport,carMoney,sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if (baseABean.getCode() == 200) {
                            //循环添加图片信息
                            for (int i = 0; i < newSelectList.size(); i++) {
                                LocalNewMedia localnewMedia = new LocalNewMedia();
                                localnewMedia.setPath(newSelectList.get(i).getPath());
                                newList.add(localnewMedia);
                            }
                            PayInfoBean bean = new PayInfoBean();
                            bean.setClickType(payInfoBean.getClickType());
                            bean.setPosition(payInfoBean.getPosition());
                            bean.setIdx(payInfoBean.getIdx() + "");
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
                        } else {
                            ToastUtil.setToast("修改失败" + baseABean.getMsg());
                        }
                    }
                });
    }
    //查询差旅日程详情
    private void getTravelInfo(String idx, String reimburserId) {
        String sign = MD5Util.encode("idx=" + idx + "&reimburserId=" + reimburserId);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .getTravelInfo(idx, reimburserId, sign)
                .compose(Transformer.<TravelDetailsBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<TravelDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
                    }

                    @Override
                    protected void onSuccess(TravelDetailsBean travelDetailsBean) {
                        if (travelDetailsBean.getCode() == 200) {
                            travelBean=travelDetailsBean;
                            initDetailsView(travelDetailsBean.getData());
                       } else {
                            ToastUtil.setToast("开支项详情查询失败请重试");
                            finish();
                        }
                    }
                });
    }
    //接口查询成功赋值
    private void initDetailsView(TravelDetailsBean.DataBean dataBean) {
        if("请选择出发时间".equals(mTvStartTime.getText().toString().trim())){
            mTvStartTime.setText(dataBean.getSetOutTime());
        }
        if("请选择到达时间".equals(mTvEndTime.getText().toString().trim())){
            mTvEndTime.setText(dataBean.getArriveTime());
        }
        if("".equals(mTvStartCity.getText().toString().trim())){
            mTvStartCity.setText(dataBean.getSetOutRegion());
        }
        if("".equals(mTvEndCity.getText().toString().trim())){
            mTvEndCity.setText(dataBean.getArriveRegion());
        }
        if("".equals(mEtCarMoney.getText().toString().trim())){
            mEtCarMoney.setText(MoneyUtils.formatMoney(dataBean.getTransportFee()));
        }
        if("".equals(mEtDay.getText().toString().trim())){
            mEtDay.setText(dataBean.getDays()+"");
        }
        if("".equals(mEtSubsidyMoney.getText().toString().trim())){
            mEtSubsidyMoney.setText(MoneyUtils.formatMoney(dataBean.getSubsidy()));
        }
        if(!ischeck){
            switch (dataBean.getTransport()){
                case "飞机":
                    transport="飞机";
                    mTvAircraft.setBackgroundResource(R.drawable.btn_red_shape_bg);
                    mTvAircraft.setTextColor(getResources().getColor(R.color.white));
                    break;
                case "火车":
                    transport="火车";
                    mTvTrain.setBackgroundResource(R.drawable.btn_red_shape_bg);
                    mTvTrain.setTextColor(getResources().getColor(R.color.white));
                    break;
                case "汽车":
                    transport="汽车";
                    mTvBus.setBackgroundResource(R.drawable.btn_red_shape_bg);
                    mTvBus.setTextColor(getResources().getColor(R.color.white));
                    break;
                case "私家车":
                    transport="私家车";
                    mTvCar.setBackgroundResource(R.drawable.btn_red_shape_bg);
                    mTvCar.setTextColor(getResources().getColor(R.color.white));
                    break;
            }
        }
        newSelectList.clear();
        for (int i = 0; i < dataBean.getReimburserItemBills().size(); i++) {
            LocalMedia localMedia = new LocalMedia();
            localMedia.setPath(BaseApi.base_img_url +dataBean.getReimburserItemBills().get(i).getUri());
            newSelectList.add(localMedia);
        }
        //保證每次添加按钮
        int maxNumber =dataBean.getReimburserItemBills().size()+1;
        //每次打开相册只能选一张
        maxSelectNum=1;
        mAdapter.setSelectMax(maxNumber);
        mAdapter.notifyDataSetChanged();
    }
    //删除单据
    private void deleteImag(String id, int position,String itemType,String reimburserId) {
        final int pos = position;
        String sign = MD5Util.encode("id=" + id+"&itemType="+itemType+"&reimburserId="+reimburserId);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .deleteIdBill(id, itemType,reimburserId,sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if(baseABean.getCode()==200){
                            ToastUtil.setToast("单据删除成功");
                            //刪除成功刷新頁面
                            getTravelInfo(payInfoBean.getIdx(), payInfoBean.getId());
                        }else{
                            ToastUtil.setToast(baseABean.getMsg());
                        }
                    }
                });
    }
    //上传参数处理
    private void uploadImg(String itemIdx, String itemType, String reimburserId) {
        Map<String, Object> paramsMap = new HashMap<>();
        List<File> list = new ArrayList<>();
        String sign = MD5Util.encode("itemIdx=" + itemIdx + "&itemType=" + itemType + "&reimburserId=" + reimburserId);
        paramsMap = new HashMap<>();
        paramsMap.put("itemIdx", itemIdx);
        paramsMap.put("itemType", itemType);
        paramsMap.put("reimburserId", reimburserId);
        paramsMap.put("sign", sign);
        List<String> filePaths = new ArrayList<>();
        for (int i = 0; i < selectList.size(); i++) {
            filePaths.add(selectList.get(i).getPath());
        }
        uploadImgAndPar("http://192.168.0.188:8080/reimburser/bill/save", "billFile", paramsMap, filePaths);
    }
    //上傳圖片到服务器
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
                        }catch (Exception ex){

                        }
                        if(obj.getCode()==200){
                            ToastUtil.setToast("单据上传成功");
                            //图片上传成功赋值给图片数据源
                            newSelectList.add(selectList.get(0));
                            mAdapter.setList(newSelectList);
                            mAdapter.notifyDataSetChanged();
                            selectList.clear();
                            getTravelInfo(payInfoBean.getIdx(), payInfoBean.getId());
                        }
                    }
                });
    }
    private DeleteGridImageAdapter.onAddPicClickListener onAddPicClickListener = new DeleteGridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = true;
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(UpdataAddTravelDayInfoAct.this)
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
                             uploadImg(payInfoBean.getIdx(),"2",payInfoBean.getId());
                        break;
                }
                break;
        }
    }
    //安卓重写返回键事件
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setData();
        }
        return true;
    }

    private void setData(){
        //循环添加图片信息
        for (int i = 0; i < newSelectList.size(); i++) {
            LocalNewMedia localnewMedia = new LocalNewMedia();
            localnewMedia.setPath(newSelectList.get(i).getPath());
            newList.add(localnewMedia);
        }
        PayInfoBean bean = new PayInfoBean();
        bean.setClickType(payInfoBean.getClickType());
        bean.setPosition(payInfoBean.getPosition());
        bean.setIdx(payInfoBean.getIdx() + "");
        bean.setId(payInfoBean.getId());
        bean.setArriveRegion(travelBean.getData().getArriveRegion());
        bean.setArriveTime(travelBean.getData().getArriveTime());
        bean.setDays(travelBean.getData().getDays()+"");
        bean.setSetOutRegion(travelBean.getData().getSetOutRegion());
        bean.setSetOutTime(travelBean.getData().getSetOutTime());
        bean.setSubsidy(travelBean.getData().getSubsidy()+"");
        bean.setTransport(transport);
        bean.setTransportFee(travelBean.getData().getTransportFee()+"");
        bean.setImg_list(newList);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("payInfo", bean);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
