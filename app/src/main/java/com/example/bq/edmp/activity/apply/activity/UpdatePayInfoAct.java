package com.example.bq.edmp.activity.apply.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
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
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.LocalNewMedia;
import com.example.bq.edmp.activity.apply.ReimbursementApi;
import com.example.bq.edmp.activity.apply.adapter.DeleteGridImageAdapter;
import com.example.bq.edmp.activity.apply.bean.AddApplyPayBean;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.activity.apply.bean.SelectReimbursementDetailsBean;
import com.example.bq.edmp.activity.apply.bean.UpdateRembursemenBean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.ActivityUtils;
import com.example.bq.edmp.utils.Constant;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

/**
 * 添加开支项
 */
public class UpdatePayInfoAct extends BaseTitleActivity {

    public static Intent newIntent(Context context, PayInfoBean payInfoBean) {
        Intent intent = new Intent(context, UpdatePayInfoAct.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.ID, payInfoBean);
        intent.putExtras(bundle);
        return intent;
    }

    @BindView(R.id.et_pro_desc)
    EditText mEtProDesc;//项目描述
    @BindView(R.id.et_pro_money)
    EditText mEtProMoney;//项目花费
    @BindView(R.id.btn_sure)
    TextView mBtnSure;
    @BindView(R.id.tick_recyclerview)
    RecyclerView mTickRecyclerView;
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private DeleteGridImageAdapter mAdapter;
    private int maxSelectNum = 1;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<LocalMedia> newSelectList = new ArrayList<>();
    private List<LocalNewMedia> newList = new ArrayList<>();
    private int chooseMode = PictureMimeType.ofAll();
    List<String> list = new ArrayList<>();
    private int themeId;
    private PayInfoBean payInfoBean = null;
    private ILoadingView loading_dialog;
    //开支项详情
    private SelectReimbursementDetailsBean detailsBean=null;
    @Override
    protected void initListener() {
        mBtnSure.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        txtTabTitle.setText("添加开支项");
        loading_dialog = new LoadingDialog(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_add_pay_info;
    }

    @Override
    protected void initData() {
        themeId = R.style.picture_QQ_style;
        payInfoBean = (PayInfoBean) getIntent().getSerializableExtra(Constant.ID);
        if (null == payInfoBean) {
            ToastUtil.setToast("数据错误");
            finish();
        }
        selectReimurser(payInfoBean.getIdx(), payInfoBean.getId());
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        mTickRecyclerView.setLayoutManager(manager);
        mAdapter = new DeleteGridImageAdapter(getApplicationContext(), onAddPicClickListener);
        mAdapter.setList(newSelectList);
        mAdapter.setSelectMax(maxSelectNum);
        mTickRecyclerView.setAdapter(mAdapter);
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
                            PictureSelector.create(UpdatePayInfoAct.this).themeStyle(themeId).openExternalPreview(position, newSelectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(UpdatePayInfoAct.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(UpdatePayInfoAct.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
        mAdapter.setOnDelterImg(new DeleteGridImageAdapter.DeleteImg() {
            @Override
            public void deleteImgList(int postion) {
                deleteImag(detailsBean.getData().getReimburserItemBills().get(postion).getId()+"",postion,"1",payInfoBean.getId());
            }
        });
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(UpdatePayInfoAct.this);
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
        mEtProMoney.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        //设置字符过滤
        mEtProMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                //删除“.”后面超过2位后的数据
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        mEtProMoney.setText(s);
                        mEtProMoney.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    mEtProMoney.setText(s);
                    mEtProMoney.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        mEtProMoney.setText(s.subSequence(0, 1));
                        mEtProMoney.setSelection(1);
                        return;
                    }
                }
                //包含. 查看. 前面是否有值
                if(s.toString().trim().contains(".")){
                    String  a=s.toString().trim().substring(0, s.toString().trim().indexOf("."));
                    if(a.length()<=0){
                        s = "0" + s;
                        mEtProMoney.setText(s);
                        mEtProMoney.setSelection(2);
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
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sure:
                checkAddData();
                break;
            case R.id.txt_back:
                setData();
                break;
        }
    }

    //接口查询成功赋值
    private void initDetailsView(SelectReimbursementDetailsBean.DataBean dataBean) {
        if("".equals(mEtProDesc.getText().toString().trim())){
            mEtProDesc.setText(dataBean.getName());
        }
        if("".equals(mEtProMoney.getText().toString().trim())){
            mEtProMoney.setText(MoneyUtils.formatMoney(dataBean.getAmount()));
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

    //验证开支选项
    private void checkAddData() {
        String proDesc = mEtProDesc.getText().toString().trim();
        if ("".equals(proDesc)) {
            ToastUtil.setToast("请填写项目描述");
            return;
        }
        String mProMoney = mEtProMoney.getText().toString().trim();
        if ("".equals(mProMoney)) {
            ToastUtil.setToast("请填写项目花费");
            return;
        }
        if (newSelectList.size() <= 0) {
            ToastUtil.setToast("请上传单据");
            return;
        }
        updateReimbursement(mProMoney, payInfoBean.getIdx(), proDesc, payInfoBean.getId());
    }

    //修改开支项
    private void updateReimbursement(String mEtMoney, String idx, String name, String reimburserId) {
        String sign = MD5Util.encode("amount=" + mEtMoney + "&idx=" + idx + "&name=" + name + "&reimburserId=" + reimburserId);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .updateReimbursement(mEtMoney, idx, name, reimburserId, sign)
                .compose(Transformer.<UpdateRembursemenBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<UpdateRembursemenBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
                    }

                    @Override
                    protected void onSuccess(UpdateRembursemenBean updateRembursemenBean) {
                        if (updateRembursemenBean.getCode() == 200) {
                            //循环添加图片信息
                            for (int i = 0; i < newSelectList.size(); i++) {
                                LocalNewMedia localnewMedia = new LocalNewMedia();
                                localnewMedia.setPath(newSelectList.get(i).getPath());
                                newList.add(localnewMedia);
                            }
                            PayInfoBean bean = new PayInfoBean();
                            bean.setDesc(mEtProDesc.getText().toString());
                            bean.setMoney(mEtProMoney.getText().toString());
                            bean.setClickType(payInfoBean.getClickType());
                            bean.setPosition(payInfoBean.getPosition());
                            bean.setIdx(payInfoBean.getIdx());
                            bean.setId(payInfoBean.getId());
                            bean.setImg_list(newList);
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("payInfo", bean);
                            intent.putExtras(bundle);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            ToastUtil.setToast("修改失败" + updateRembursemenBean.getMsg());
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
        uploadImgAndPar(BaseApi.base_url_mdffx+"reimburser/bill/save", "billFile", paramsMap, filePaths);
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
                            selectReimurser(payInfoBean.getIdx(), payInfoBean.getId());
                        }
                    }
                });
    }

    //相册参数
    private DeleteGridImageAdapter.onAddPicClickListener onAddPicClickListener = new DeleteGridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = true;
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(UpdatePayInfoAct.this)
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
                        .isCamera(false)// 是否显示拍照按钮
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


    //查询开支选项 详情
    private void selectReimurser(String idx, String reimburserId) {
        String sign = MD5Util.encode("idx=" + idx + "&reimburserId=" + reimburserId);
        RxHttpUtils.createApi(ReimbursementApi.class)
                .selectReimbureser(idx, reimburserId, sign)
                .compose(Transformer.<SelectReimbursementDetailsBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<SelectReimbursementDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                         ActivityUtils.getMsg(errorMsg,getApplicationContext());;
                    }

                    @Override
                    protected void onSuccess(SelectReimbursementDetailsBean selectReimbursementDetailsBean) {
                        if (selectReimbursementDetailsBean.getCode() == 200) {
                             detailsBean=selectReimbursementDetailsBean;
                            initDetailsView(selectReimbursementDetailsBean.getData());
                        } else {
                            ToastUtil.setToast("开支项详情查询失败请重试");
                            finish();
                        }
                    }
                });
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
                            selectReimurser(payInfoBean.getIdx(), payInfoBean.getId());
                        }else{
                            ToastUtil.setToast(baseABean.getMsg());
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
                        uploadImg(payInfoBean.getIdx(),"1",payInfoBean.getId());
                        //TODO 上传图片接口
//                        new API(LeaveActivity.this, Base.getClassType()).uploadApprovalImg(list);
                        break;
                }
                break;
        }

    }
    @Override
    //安卓重写返回键事件
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setData();
        }
        return true;
    }
    //回退键赋值 代码混乱 后期优化
    private void setData(){
        for (int i = 0; i < newSelectList.size(); i++) {
            LocalNewMedia localnewMedia = new LocalNewMedia();
            localnewMedia.setPath(newSelectList.get(i).getPath());
            newList.add(localnewMedia);
        }
        PayInfoBean bean = new PayInfoBean();
        bean.setDesc(detailsBean.getData().getName());
        bean.setMoney(detailsBean.getData().getAmount()+"");
        bean.setClickType(payInfoBean.getClickType());
        bean.setPosition(payInfoBean.getPosition());
        bean.setIdx(payInfoBean.getIdx());
        bean.setId(payInfoBean.getId());
        bean.setImg_list(newList);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("payInfo", bean);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
