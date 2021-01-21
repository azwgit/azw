package com.example.bq.edmp.work.modelfield.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.adapter.DeleteGridImageAdapter;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.ActivityUtils;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.TurnImgStringUtils;
import com.example.bq.edmp.work.marketing.activity.EditCustomerDetailsActivity;
import com.example.bq.edmp.work.modelfield.adapter.DemonstionGridImageAdapter;
import com.example.bq.edmp.work.modelfield.api.DemonstrationApi;
import com.example.bq.edmp.work.modelfield.bean.DemonstrationDetailsBean;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;

/*
 * 修改品种示范
 * */
public class DemonstrationEetActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.pz_nd_tv)
    TextView pz_nd_tv;
    @BindView(R.id.address_tv)
    TextView address_tv;
    @BindView(R.id.compay_tv)
    TextView compay_tv;
    @BindView(R.id.titles_tv)
    EditText titles_et;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.pic_recyclerview)
    RecyclerView mRecyclerView;

    private LoadingDialog loadingDialog;
    private DemonstrationDetailsBean.DataBean dataBean;
    List<LocalMedia> selectList = new ArrayList<>();
    private int pos;
    private int themeId;
    private DemonstionGridImageAdapter mAdapter;
    private int maxSelectNum = 9;
    private int isUploadImage = 0;//未修改过图片
    private int chooseMode = PictureMimeType.ofAll();
    private ArrayList<String> tpid = new ArrayList<>();

    private DemonstrationDetailsBean.DataBean.DemonstrationItemBean demonstrationItemAnnexsBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demonstration_eet;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(DemonstrationEetActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("发布示范信息");
        dataBean = (DemonstrationDetailsBean.DataBean) getIntent().getSerializableExtra("data");
        demonstrationItemAnnexsBean = (DemonstrationDetailsBean.DataBean.DemonstrationItemBean) getIntent().getSerializableExtra("demonstrationItemAnnexsBean");
        pos = getIntent().getIntExtra("pos", 0);

    }

    @Override
    protected void initData() {
        pz_nd_tv.setText(dataBean.getVarietyName() + " 品种示范 " + dataBean.getYears() + "年");
        address_tv.setText(dataBean.getRegion());
        compay_tv.setText(dataBean.getCompanyName());
        titles_et.setText(dataBean.getDemonstrationItem().get(pos).getTitle());

        gainData();

        List<DemonstrationDetailsBean.DataBean.DemonstrationItemBean.DemonstrationItemAnnexsBean> demonstrationItemAnnexs = demonstrationItemAnnexsBean.getDemonstrationItemAnnexs();
        for (int i = 0; i < demonstrationItemAnnexs.size(); i++) {
            LocalMedia localMedia = new LocalMedia();
            localMedia.setPath("http://192.168.0.188:8010/demonstration_img" + TurnImgStringUtils.isImgPath(demonstrationItemAnnexs.get(i).getUri()));
            tpid.add(demonstrationItemAnnexs.get(i).getId());
            selectList.add(localMedia);
        }
        mAdapter.setList(selectList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                Intent intent = new Intent(DemonstrationEetActivity.this, DemonstrationDetailsActivity.class);
                setResult(350, intent);
                fund();
                break;
            case R.id.tv_submit://确认添加
                if (titles_et.getText().toString().equals("")) {
                    ToastUtil.setToast("请输入标题");
                    return;
                }
                /*if (selectList.size() == 0) {
                    ToastUtil.setToast("请选择图片");
                    return;
                }*/
                addPothMethod(titles_et.getText().toString());
                break;
        }
    }

    private void addPothMethod(String strings) {

        String sign = MD5Util.encode("demonstrationId=" + demonstrationItemAnnexsBean.getId().getDemonstrationId()
                + "&idx=" + demonstrationItemAnnexsBean.getId().getIdx() + "&title=" + strings);

        RxHttpUtils.createApi(DemonstrationApi.class)
                .getDeleteShiaFan(demonstrationItemAnnexsBean.getId().getDemonstrationId(), demonstrationItemAnnexsBean.getId().getIdx(), strings, sign)
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
                            ToastUtil.setToast("示范信息修改成功");
                            Intent intent = new Intent(DemonstrationEetActivity.this, DemonstrationDetailsActivity.class);
                            setResult(350, intent);
                            finish();
                        } else {
                            ToastUtil.setToast("示范信息修改失败");
                        }
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(DemonstrationEetActivity.this);
    }


    private void gainData() {
        themeId = R.style.picture_QQ_style;
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new DemonstionGridImageAdapter(getApplicationContext(), onAddPicClickListener);
        mAdapter.setList(selectList);
        mAdapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new DemonstionGridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v, int type) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(ReleaseActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(DemonstrationEetActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(DemonstrationEetActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(DemonstrationEetActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
        mAdapter.setOnDelterImg(new DemonstionGridImageAdapter.DeleteImg() {
            @Override
            public void deleteImgList(final int postion) {

                String sign = MD5Util.encode("id=" + tpid.get(postion));

                RxHttpUtils.createApi(DemonstrationApi.class)
                        .getDetailsImg(tpid.get(postion), sign)
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
                                    isUploadImage = 1;
                                    selectList.remove(postion);
                                    mAdapter.notifyDataSetChanged();
                                    ToastUtil.setToast("删除成功");
                                } else {
                                    ToastUtil.setToast("删除失败");
                                }
                            }
                        });

            }
        });
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(DemonstrationEetActivity.this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).

                subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            PictureFileUtils.deleteCacheDirFile(getApplicationContext());
                            ToastUtil.setToast(getString(R.string.picture_jurisdiction));
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

    private DemonstionGridImageAdapter.onAddPicClickListener onAddPicClickListener = new DemonstionGridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = true;
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(DemonstrationEetActivity.this)
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

    List<LocalMedia> XinselectList = new ArrayList<>();

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
//                        XinselectList = PictureSelector.obtainMultipleResult(data);
                        // 例如 LocalMedia 里面返回三种path
                        // 1.media.getPath(); 为原图path
                        // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                        // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                        // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                        //TODO 上传图片接口
//                        new API(LeaveActivity.this, Base.getClassType()).uploadApprovalImg(list);
                        mAdapter.setList(selectList);
                        mAdapter.notifyDataSetChanged();
                        PothMethod();
                        break;
                }
                break;
        }
    }

    //添加图片
    private void PothMethod() {
        Map<String, Object> paramsMap = new HashMap<>();
        List<File> list = new ArrayList<>();
        String sign = MD5Util.encode("demonstrationId=" + demonstrationItemAnnexsBean.getId().getDemonstrationId() + "&itemIdx=" + demonstrationItemAnnexsBean.getId().getIdx());
        paramsMap = new HashMap<>();
        paramsMap.put("demonstrationId", demonstrationItemAnnexsBean.getId().getDemonstrationId());
        paramsMap.put("itemIdx", demonstrationItemAnnexsBean.getId().getIdx());
        paramsMap.put("sign", sign);
        List<String> filePaths = new ArrayList<>();

        for (int i = 0; i < selectList.size(); i++) {
            String path = selectList.get(i).getPath();
            if (path.indexOf("http") != -1) {
            }else {
                XinselectList.add(selectList.get(i));
            }
        }

        for (int i = 0; i < XinselectList.size(); i++) {
            filePaths.add(XinselectList.get(i).getPath());
        }
        uploadImgAndPar(BaseApi.base_url_marketing + "demonstration/info/addimg", "demonstrationImg", paramsMap, filePaths);
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
                        } catch (Exception ex) {

                        }
                        if (-99 == obj.getCode()) {
                            ToastUtil.setToast(obj.getMsg());
                            return;
                        }
                        XinselectList.clear();
                    }
                });
    }
}
