package com.example.bq.edmp.work.marketing.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.GridImageAdapter;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.ActivityUtils;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.TurnImgStringUtils;
import com.example.bq.edmp.utils.phoneUtils;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.marketing.CustomerManagementApi;
import com.example.bq.edmp.work.marketing.bean.CustomerDetailsBean;
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

public class EditCustomerDetailsActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, EditCustomerDetailsActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.tv_submit)
    TextView mTvSubmit;//提交按钮
    @BindView(R.id.pic_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_name)
    EditText mTvName;//客户名称
    @BindView(R.id.tv_distribution_area)
    TextView mTvDistributionArea;//经销区域
    @BindView(R.id.tv_contacts)
    EditText mTvContacts;//联系人
    @BindView(R.id.tv_contact_information)
    EditText mTvContactInformation;//联系方式
    @BindView(R.id.tv_contact_address)
    EditText mTvContactAddress;//联系地址
    @BindView(R.id.tv_remarks)
    EditText mTvRemarks;//备注
    @BindView(R.id.tv_license_number)
    EditText mTvLicenseNumber;//执照编号
    private GridImageAdapter mAdapter;
    private int chooseMode = PictureMimeType.ofAll();
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 1;
    private int themeId;
    private String id = "";
    private ILoadingView loading_dialog;
    private int isUploadImage = 0;//未修改过图片
    private CustomerDetailsBean customerDetailsBean = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_customer_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("修改");
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        loading_dialog = new LoadingDialog(this);
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
                            PictureSelector.create(EditCustomerDetailsActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(EditCustomerDetailsActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(EditCustomerDetailsActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(EditCustomerDetailsActivity.this);
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
        getCustomerDetails();
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = true;
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(EditCustomerDetailsActivity.this)
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
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTvSubmit.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()){
            case R.id.tv_submit:
                checkAddData();
                break;
        }
    }

    //获取客户详情
    private void getCustomerDetails() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(CustomerManagementApi.class)
                .getCustomerDetails(id, sign)
                .compose(Transformer.<CustomerDetailsBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<CustomerDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CustomerDetailsBean bean) {
                        if (bean.getCode() == 200) {
                            customerDetailsBean = bean;
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            finish();
                        }
                    }
                });
    }

    //详情赋值
    private void setData(CustomerDetailsBean.DataBean bean) {
        mTvName.setText(bean.getName());
        mTvDistributionArea.setText(bean.getRegion());
        mTvContacts.setText(bean.getContacts());
        mTvContactInformation.setText(bean.getMobTel());
        mTvContactAddress.setText(bean.getContactAddress());
        mTvRemarks.setText(bean.getRemark());
        mTvLicenseNumber.setText(bean.getBusinessLicenseNumber());
        LocalMedia localMedia = new LocalMedia();
        localMedia.setPath(BaseApi.marketing_img_url + TurnImgStringUtils.isImgPath(bean.getBusinessLicense()));
        selectList.add(localMedia);
        mAdapter.setList(selectList);
        mAdapter.notifyDataSetChanged();
    }

    //验证客户添加数据
    private void checkAddData() {
        //客户名称
        String name = mTvName.getText().toString().trim();
        if (name.isEmpty()) {
            ToastUtil.setToast("请添加客户名称");
            return;
        }
        //联系人
        String contacts = mTvContacts.getText().toString().trim();
        if (contacts.isEmpty()) {
            ToastUtil.setToast("请添加联系人");
            return;
        }
        //联系方式
        String contactInformation = mTvContactInformation.getText().toString().trim();
        if (contactInformation.isEmpty()) {
            ToastUtil.setToast("请添加联系方式");
            return;
        }
        //联系地址
        String contachAddress = mTvContactAddress.getText().toString().trim();
        if (contachAddress.isEmpty()) {
            ToastUtil.setToast("请添加联系地址");
            return;
        }
        //备注
        String remarks = mTvRemarks.getText().toString().trim();
        //执照编号
        String licenseNumber = mTvLicenseNumber.getText().toString().trim();
        if (licenseNumber.isEmpty()) {
            ToastUtil.setToast("请添加执照编号");
            return;
        }
        if (isUploadImage == 1 && selectList.size() <= 0) {
            ToastUtil.setToast("请添加营业执照");
            return;
        }
        if(!phoneUtils.isMobileNO(contactInformation)){
            ToastUtil.setToast("请输入正确的手机号");
            return;
        }
        initData(customerDetailsBean.getData().getBusinessLicense(), licenseNumber, contachAddress, contacts, customerDetailsBean.getData().getId() + "", contactInformation, name, remarks);
    }

    //参数初始化
    private void initData(String businessLicense, String businessLicenseNumber, String contactAddress, String contacts, String id, String mobTel, String name, String remark) {
        Map<String, Object> paramsMap = new HashMap<>();
        List<File> list = new ArrayList<>();
        String sign = MD5Util.encode("businessLicense=" + businessLicense + "&businessLicenseNumber=" + businessLicenseNumber + "&contactAddress=" + contactAddress + "&contacts=" + contacts + "&id=" + id + "&mobTel=" + mobTel + "&name=" + name + "&remark=" + remark);
        paramsMap = new HashMap<>();
        paramsMap.put("businessLicense", businessLicense);
        paramsMap.put("businessLicenseNumber", businessLicenseNumber);
        paramsMap.put("contactAddress", contactAddress);
        paramsMap.put("contacts", contacts);
        paramsMap.put("id", id);
        paramsMap.put("mobTel", mobTel);
        paramsMap.put("name", name);
        paramsMap.put("remark", remark);
        paramsMap.put("sign", sign);
        List<String> filePaths = new ArrayList<>();
        if(isUploadImage==1){
            for (int i = 0; i < selectList.size(); i++) {
                filePaths.add(selectList.get(i).getPath());
            }
        }
        uploadImgAndPar("http://192.168.0.188:8089/customer/save", "businessLicenseImg", paramsMap, filePaths);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case PictureConfig.CHOOSE_REQUEST:
                        //修改过图片
                        isUploadImage = 1;
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
}