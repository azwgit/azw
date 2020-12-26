package com.example.bq.edmp.work.marketingactivities.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.url.MimeType;
import com.example.bq.edmp.utils.ActivityUtils;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.marketing.bean.CustomerDetailsBean;
import com.example.bq.edmp.work.marketingactivities.adapter.FileUploadGridImageAdapter;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
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
    @BindView(R.id.pic_recyclerview)
    RecyclerView mRecyclerView;
//    @BindView(R.id.tv_name)
//    EditText mTvName;//客户名称
//    @BindView(R.id.tv_distribution_area)
//    TextView mTvDistributionArea;//经销区域
//    @BindView(R.id.tv_contacts)
//    EditText mTvContacts;//联系人
//    @BindView(R.id.tv_contact_information)
//    EditText mTvContactInformation;//联系方式
//    @BindView(R.id.tv_contact_address)
//    EditText mTvContactAddress;//联系地址
//    @BindView(R.id.tv_remarks)
//    EditText mTvRemarks;//备注
//    @BindView(R.id.tv_license_number)
//    EditText mTvLicenseNumber;//执照编号
//    @BindView(R.id.pdfView)
//    PDFView pdfView;//执照编号

    private FileUploadGridImageAdapter mAdapter;
    private int chooseMode = PictureMimeType.ofAll();
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 9;
    private int themeId;
    private String id = "";
    private ILoadingView loading_dialog;
    private CustomerDetailsBean customerDetailsBean = null;
    public final static int CHOOSE_FILE_CODE = 1000;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_editactivitys;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("修改");
//        id = getIntent().getStringExtra(Constant.ID);
//        if ("".equals(id)) {
//            ToastUtil.setToast("数据出错请重试");
//            return;
//        }
        for(int i=0;i<5;i++){
            LocalMedia localMedia=new LocalMedia();
            localMedia.setFileType(1);
            selectList.add(localMedia);
        }

        loading_dialog = new LoadingDialog(this);
        themeId = R.style.picture_QQ_style;
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
                    switch (mediaType) {
                        case 2:
                            try {
                                File file = new File(media.getPath());
                                Intent intent = new Intent("android.intent.action.VIEW");
                                intent.addCategory("android.intent.category.DEFAULT");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                Uri uri;
                                if (Build.VERSION.SDK_INT >= 24) {
                                    uri = FileProvider.getUriForFile(getApplicationContext(), "com.example.bq.edmp.fileprovider", file);
                                } else {
                                    uri = Uri.fromFile(file);
                                }
                                intent.setDataAndType(uri, "application/pdf");
                                startActivity(intent);
                            } catch (Exception e) {
                                ToastUtil.setToast("未找到打开pdf 三方软件");
                            }

                            break;
                    }
                }
            }
        });
        mAdapter.setOnDelterImg(new FileUploadGridImageAdapter.DeleteImg() {
            @Override
            public void deleteImgList(int postion) {
                selectList.remove(postion);
                mAdapter.notifyDataSetChanged();
            }
        });
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

    }

    @Override
    protected void initListener() {
        mBtnSubmit.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
//                checkAddData();
//                File file = new File("/storage/emulated/0/Android/data/com.tencent.mm/MicroMsg/588172ec197489dbc28fec10132dca84/attachment/企课网介绍.pdf");
//                pdfView.fromFile(file)   //设置pdf文件地址
//                        .defaultPage(6)         //设置默认显示第1页
//                        .showMinimap(false)     //pdf放大的时候，是否在屏幕的右上角生成小地图
//                        .swipeVertical(false)  //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
//                        .enableSwipe(true)   //是否允许翻页，默认是允许翻
//                        // .pages( 2 ，5  )  //把2  5 过滤掉
//                        .load();
                break;
        }
    }

    //验证客户添加数据
//    private void checkAddData() {
//        //客户名称
//        String name = mTvName.getText().toString().trim();
//        if (name.isEmpty()) {
//            ToastUtil.setToast("请添加客户名称");
//            return;
//        }
//        //联系人
//        String contacts = mTvContacts.getText().toString().trim();
//        if (contacts.isEmpty()) {
//            ToastUtil.setToast("请添加联系人");
//            return;
//        }
//        //联系方式
//        String contactInformation = mTvContactInformation.getText().toString().trim();
//        if (contactInformation.isEmpty()) {
//            ToastUtil.setToast("请添加联系方式");
//            return;
//        }
//        //联系地址
//        String contachAddress = mTvContactAddress.getText().toString().trim();
//        if (contachAddress.isEmpty()) {
//            ToastUtil.setToast("请添加联系地址");
//            return;
//        }
//        //备注
//        String remarks = mTvRemarks.getText().toString().trim();
//        //执照编号
//        String licenseNumber = mTvLicenseNumber.getText().toString().trim();
//        if (licenseNumber.isEmpty()) {
//            ToastUtil.setToast("请添加执照编号");
//            return;
//        }
//        if (!phoneUtils.isMobileNO(contactInformation)) {
//            ToastUtil.setToast("请输入正确的手机号");
//            return;
//        }
//        initData(customerDetailsBean.getData().getBusinessLicense(), licenseNumber, contachAddress, contacts, customerDetailsBean.getData().getId() + "", contactInformation, name, remarks);
//    }

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
        uploadImgAndPar(BaseApi.base_url_marketing + "customer/save", "businessLicenseImg", paramsMap, filePaths);
    }

    //图片上传接口
//    private void uploadImgAndPar(String uploadUrl, String fileName, Map<String, Object> paramsMap, List<String> uploadPaths) {
//
//        RxHttpUtils.uploadImagesAndParams(uploadUrl, fileName, paramsMap, uploadPaths)
//                .compose(Transformer.<ResponseBody>switchSchedulers(loading_dialog))
//                .subscribe(new CommonObserver<ResponseBody>() {
//
//                    @Override
//                    protected String setTag() {
//                        return "uploadImg";
//                    }
//
//                    @Override
//                    protected void onError(String errorMsg) {
//                        ActivityUtils.getMsg(errorMsg, getApplicationContext());
//                    }
//
//                    @Override
//                    protected void onSuccess(ResponseBody responseBody) {
//                        BaseABean obj = null;
//                        try {
//                            String json = new String(responseBody.bytes());
//                            Gson gson = new Gson();
//                            obj = gson.fromJson(json, BaseABean.class);
//                        } catch (Exception ex) {
//
//                        }
//                        if (-99 == obj.getCode()) {
//                            ToastUtil.setToast(obj.getMsg());
//                            return;
//                        }
//                        finish();
//
//                    }
//                });
//    }

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
                        break;
                }
                break;
        }
    }

    //参数初始化
    private void initData(String name) {
        Map<String, Object> paramsMap = new HashMap<>();
        List<File> list = new ArrayList<>();
        String sign = MD5Util.encode("name=" + name);
        paramsMap = new HashMap<>();
        paramsMap.put("name", name);
        paramsMap.put("sign", sign);
        List<String> filePaths = new ArrayList<>();
        for (int i = 0; i < selectList.size(); i++) {
            filePaths.add(selectList.get(i).getPath());
        }
        uploadImgAndPar(BaseApi.base_url_marketing + "customer/file", "businessLicenseImg", paramsMap, filePaths);
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
}