package com.example.bq.edmp.work.marketingactivities.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.allen.library.observer.CommonObserver;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApprovalAdp;
import com.example.bq.edmp.activity.apply.GridImageAdapter;
import com.example.bq.edmp.activity.apply.PreviewImageAdapter;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.ActivityUtils;
import com.example.bq.edmp.utils.FromtUtil;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.finishedproduct.adapter.CardTypeAdp;
import com.example.bq.edmp.work.marketing.activity.AddCustomerActivity;
import com.example.bq.edmp.work.marketingactivities.adapter.EnclosureAdapter;
import com.example.bq.edmp.work.tracking.api.TrackingApi;
import com.example.bq.edmp.work.tracking.bean.TrackingDereBean;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/*
 * 活动详情
 * */
public class ActivitiesDetailsActivity extends BaseActivity {
    @BindView(R.id.approval_recyclerview)
    RecyclerView mApprovalRecyclerView;//审批状态适配器
    @BindView(R.id.pic_recyclerview)
    RecyclerView pic_recyclerview;//附件适配器
    @BindView(R.id.btn_complete)
    TextView mBtnComplete;
    @BindView(R.id.btn_del)
    TextView btn_del;
    @BindView(R.id.btn_submit)
    TextView btn_submit;

    @BindView(R.id.img_status)
    ImageView img_status;
    @BindView(R.id.dtj_tv)
    TextView dtj_tv;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;


    private ApprovalAdp mApprovalAdapter;
    private EnclosureAdapter adapter;
    private PopupWindow mTypePopuWindow;
    private GridImageAdapter mAdapter;
    private int chooseMode = PictureMimeType.ofAll();
    private int maxSelectNum = 9;
    private int themeId;
    private List<LocalMedia> selectList = new ArrayList<>();
    private String mid;
    private LoadingDialog loadingDialog;
    private String huodongid = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_activities_details;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(ActivitiesDetailsActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("活动详情");
        mid = getIntent().getStringExtra("id");

        List<PayInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PayInfoBean bean = new PayInfoBean();
            bean.setId(i + "");
            bean.setDesc("啊啊啊啊");
            list.add(bean);
        }
        List<LocalMedia> imglist = new ArrayList<LocalMedia>();
        for (int i = 0; i < 5; i++) {
            LocalMedia localMedia = new LocalMedia();
            localMedia.setFileType(1);
            imglist.add(localMedia);
        }
        FullyGridLayoutManager manager = new FullyGridLayoutManager(getApplicationContext(), 4, GridLayoutManager.VERTICAL, false);
        pic_recyclerview.setLayoutManager(manager);
        adapter = new EnclosureAdapter(getApplicationContext(), null);
        adapter.setList(imglist);
        pic_recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new EnclosureAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                ToastUtil.setToast("点击了" + position);
            }
        });
        mApprovalRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mApprovalAdapter = new ApprovalAdp(list);
        mApprovalRecyclerView.setAdapter(mApprovalAdapter);
    }

    @Override
    protected void initData() {
        String sign = MD5Util.encode("id=" + mid);

        RxHttpUtils.createApi(TrackingApi.class)
                .getTrackingDrainData(mid, sign)
                .compose(Transformer.<TrackingDereBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<TrackingDereBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(TrackingDereBean trackingDereBean) {
                        String code = trackingDereBean.getCode();
                        if (code.equals("200")) {
                            TrackingDereBean.DataBean data = trackingDereBean.getData();

                            initDataMethod(data);

                        } else {
                            ToastUtil.setToast("加载失败");
                        }
                    }
                });
    }

    @BindView(R.id.tv_company)
    TextView tv_company;
    @BindView(R.id.tv_dept)
    TextView tv_dept;
    @BindView(R.id.addtime_tv)
    TextView addtime_tv;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_stauts)
    TextView tv_stauts;
    @BindView(R.id.yusuan_tv)
    TextView yusuan_tv;
    @BindView(R.id.fuze_user_tv)
    TextView fuze_user_tv;
    @BindView(R.id.tracking_time_tv)
    TextView tracking_time_tv;
    @BindView(R.id.tracking_address_tv)
    TextView tracking_address_tv;
    @BindView(R.id.hezuo_user_tv)
    TextView hezuo_user_tv;
    @BindView(R.id.trscking_mudi_tv)
    TextView trscking_mudi_tv;

    //赋值数据
    private void initDataMethod(TrackingDereBean.DataBean data) {
        String id = data.getId();
        if (id != null && !id.equals("")) {
            huodongid = id;
        }

        //1待提交 2审批中 3审批通过 4审批拒绝 5已完成 -1已删除
        String status = data.getStatus();
        if (status != null) {
            if (status.equals("2")) {
                dtj_tv.setVisibility(View.VISIBLE);
                img_status.setVisibility(View.GONE);
                addtime_tv.setVisibility(View.VISIBLE);
                tv_stauts.setText("审批中");
            } else if (status.equals("3")) {
                dtj_tv.setVisibility(View.GONE);
                img_status.setVisibility(View.VISIBLE);
                addtime_tv.setVisibility(View.GONE);
                mBtnComplete.setVisibility(View.VISIBLE);
                img_status.setBackground(getResources().getDrawable(R.drawable.yitongyi));
                tv_stauts.setText("已同意");
            } else if (status.equals("4")) {
                dtj_tv.setVisibility(View.GONE);
                img_status.setVisibility(View.VISIBLE);
                addtime_tv.setVisibility(View.GONE);
                btn_del.setVisibility(View.VISIBLE);
                btn_submit.setVisibility(View.VISIBLE);
                img_status.setBackground(getResources().getDrawable(R.drawable.property_1yijujue));
                tv_stauts.setText("已拒绝");
            }
        }

        tv_title.setText(data.getName());
        tv_company.setText(data.getOrgName());
        tv_dept.setText(data.getAddedOperator());
        addtime_tv.setText(data.getAddedTime());
        yusuan_tv.setText("¥" + FromtUtil.getFromt(data.getAdvanceLoan()));
        fuze_user_tv.setText(data.getDeptName() + "-" + data.getResponsiblePeople());
        tracking_time_tv.setText(data.getStartTime() + "至" + data.getEndTime());
        hezuo_user_tv.setText(data.getCustomerName());
        trscking_mudi_tv.setText(data.getPurpose());

    }

    @Override
    protected void initListener() {
        mBtnComplete.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        return_img.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.btn_complete:
                DeliveryInfo();
                break;
            case R.id.btn_submit:
                ToastUtil.setToast("重新提交");
                break;
            case R.id.btn_del:
                ToastUtil.setToast("删除");
                DeletilsTracking();
                break;
        }
    }

    //活动删除
    private void DeletilsTracking() {
        String sign = MD5Util.encode("id=" + huodongid);
        RxHttpUtils.createApi(TrackingApi.class)
                .getDetails(huodongid, sign)
                .compose(Transformer.<BaseABean>switchSchedulers())
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        int code = bean.getCode();
                        if (code == 200) {
                            ToastUtil.setToast("活动删除成功");
                            finish();
                        } else {
                            ToastUtil.setToast("活动删除失败");
                        }
                    }
                });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = true;
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(ActivitiesDetailsActivity.this)
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
                        //TODO 上传图片接口
//                        new API(LeaveActivity.this, Base.getClassType()).uploadApprovalImg(list);
                        mAdapter.setList(selectList);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(ActivitiesDetailsActivity.this);
    }

    //选择发货方式PopuWindow
    private void DeliveryInfo() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.activities_info_layout, null);
        TextView mTvSend = contentView.findViewById(R.id.btn_submit);
        TextView mLyView = contentView.findViewById(R.id.btn_del);
        final EditText et_remark = contentView.findViewById(R.id.et_remark);
        RecyclerView mRecyclerView = contentView.findViewById(R.id.mRecyclerView);
        themeId = R.style.picture_QQ_style;
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
                            PictureSelector.create(ActivitiesDetailsActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(ActivitiesDetailsActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(ActivitiesDetailsActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(ActivitiesDetailsActivity.this);
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
        mTvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String remark_tx = et_remark.getText().toString();

                if (remark_tx.equals("")) {
                    ToastUtil.setToast("请输入文字描述总结现场活动效果");
                    return;
                }
                if (selectList.size() == 0) {
                    ToastUtil.setToast("请选择图片");
                    return;
                }
                PothMethod(remark_tx);
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

    //添加图片
    private void PothMethod(String remark_tx) {
        Map<String, Object> paramsMap = new HashMap<>();
        List<File> list = new ArrayList<>();
        String sign = MD5Util.encode("effect=" + remark_tx + "&id=" + huodongid);
        paramsMap = new HashMap<>();
        paramsMap.put("effect", remark_tx);
        paramsMap.put("id", huodongid);
        paramsMap.put("sign", sign);
        List<String> filePaths = new ArrayList<>();


        for (int i = 0; i < selectList.size(); i++) {
            filePaths.add(selectList.get(i).getPath());
        }
        uploadImgAndPar(BaseApi.base_url_marketing + "activity/finished", "activityAnnex", paramsMap, filePaths);
    }

    //图片上传接口
    private void uploadImgAndPar(String uploadUrl, String fileName, Map<String, Object> paramsMap, List<String> uploadPaths) {

        RxHttpUtils.uploadImagesAndParams(uploadUrl, fileName, paramsMap, uploadPaths)
                .compose(Transformer.<ResponseBody>switchSchedulers(loadingDialog))
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
                            mTypePopuWindow.dismiss();
                            finish();
                        } catch (Exception ex) {

                        }
                        if (-99 == obj.getCode()) {
                            ToastUtil.setToast("提交失败，请重新上传");
                            return;
                        }
                        selectList.clear();
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }
}