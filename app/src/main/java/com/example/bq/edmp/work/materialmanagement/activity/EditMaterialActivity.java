package com.example.bq.edmp.work.materialmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.download.DownloadObserver;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.url.MimeType;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.OpenFiles;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.inventorytransfer.activity.AddTransferGoodsActivity;
import com.example.bq.edmp.work.inventorytransfer.activity.UpdateTransferGoodsActivity;
import com.example.bq.edmp.work.inventorytransfer.adapter.CommodityListAdp;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.inventorytransfer.bean.EditFinishedProductAllocationBean;
import com.example.bq.edmp.work.marketingactivities.activity.ActivitySiteActivity;
import com.example.bq.edmp.work.marketingactivities.adapter.FileUploadGridImageAdapter;
import com.example.bq.edmp.work.marketingactivities.api.MarketingActivitiesApi;
import com.example.bq.edmp.work.materialmanagement.adapter.GoodsListAdp;
import com.example.bq.edmp.work.materialmanagement.adapter.MaterialFileUploadGridImageAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EditMaterialActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String type, String id) {
        Intent intent = new Intent(context, EditMaterialActivity.class);
        intent.putExtra(Constant.TYPE, type);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.pic_recyclerview)
    RecyclerView pic_recyclerview;

    @BindView(R.id.btn_add_info)
    LinearLayout mBtnAddInfo;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.btn_del)
    TextView mBtnDel;
    @BindView(R.id.tv_content)
    TextView mTvContent;//调拨原因
    private String type = "";//1原粮进入 2成品进入
    private String id = "";
    private GoodsListAdp mAdapter;
    private ILoadingView loading_dialog;
    private EditFinishedProductAllocationBean editFinishedProductAllocationBean;
    private MaterialFileUploadGridImageAdapter mImageAdapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 9;
    public final static int CHOOSE_FILE_CODE = 1000;
    private int themeId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_material;
    }

    @Override
    protected void initView() {
        type = getIntent().getStringExtra(Constant.TYPE);
        id = getIntent().getStringExtra(Constant.ID);
        themeId = R.style.picture_QQ_style;
        if ("".equals(type) || "".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        if ("1".equals(type)) {
            txtTabTitle.setText("新增物料采购");
        } else {
            txtTabTitle.setText("修改采购单");
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new GoodsListAdp(null);
        mRecyclerView.setAdapter(mAdapter);
        //删除
        mAdapter.setOnItemDelListener(new GoodsListAdp.OnItemDelListener() {
            @Override
            public void onItemDelClick(int position, EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean bean) {
                deleteGoods(bean.getInItemId() + "");
            }
        });

        //编辑
        mAdapter.setOnItemEditLisenter(new GoodsListAdp.OnItemEditLisenter() {
            @Override
            public void onItemEditClick(int pos, EditFinishedProductAllocationBean.DataBean.StockAllotItemsBean bean) {
                UpdateTransferGoodsActivity.newIntent(getApplicationContext(), bean.getInItemId() + "", id);
            }
        });
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        pic_recyclerview.setLayoutManager(manager);
        mImageAdapter = new MaterialFileUploadGridImageAdapter(getApplicationContext(), onAddPicClickListener);
        mImageAdapter.setList(selectList);
        mImageAdapter.setSelectMax(maxSelectNum);
        pic_recyclerview.setAdapter(mImageAdapter);
        mImageAdapter.setOnItemClickListener(new MaterialFileUploadGridImageAdapter.OnItemClickListener() {
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
                        case 6:
                            List list = new ArrayList<LocalMedia>();
                            LocalMedia localMedia = new LocalMedia();
                            localMedia.setPath(path);
                            list.add(localMedia);
                            PictureSelector.create(EditMaterialActivity.this).themeStyle(themeId).openExternalPreview(position, list);
                            break;
                    }
                }
            }
        });
        mImageAdapter.setOnDelterImg(new MaterialFileUploadGridImageAdapter.DeleteImg() {
            @Override
            public void deleteImgList(int postion) {
                if (null == selectList.get(postion).getDownLoadUrl()) {
                    selectList.remove(postion);
                    mImageAdapter.notifyDataSetChanged();
                } else {
                    deleteAttachment("ID", postion);
                }
            }
        });
    }

    private MaterialFileUploadGridImageAdapter.onAddPicClickListener onAddPicClickListener = new MaterialFileUploadGridImageAdapter.onAddPicClickListener() {
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
                intent.setType("application/msword|application/vnd.openxmlformats-officedocument.wordprocessingml.document|application/pdf|application/msword|application/vnd.ms-powerpoint|application/vnd.ms-excel|image/*");
                //在API>=19之后设置多个类型采用以下方式，setType不再支持多个类型
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{MimeType.DOC, MimeType.DOCX, MimeType.PDF, MimeType.IMAGE});
                }
                startActivityForResult(intent, CHOOSE_FILE_CODE);
            }
        }

    };

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

    @Override
    protected void onResume() {
        super.onResume();
        getProudctAllocationDetails();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBtnAddInfo.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);
        mBtnDel.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_info:
                AddPurchaseGoodsActivity.newIntent(getApplicationContext(), type, editFinishedProductAllocationBean.getData().getId() + "");
                break;
            case R.id.tv_submit:
                submitAllot();
                break;
            case R.id.btn_del:
                deleteAllot();
                break;


        }
    }

    //获取调拨详情
    private void getProudctAllocationDetails() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(AllocationApi.class)
                .getProudctAllocationDetails(id, sign)
                .compose(Transformer.<EditFinishedProductAllocationBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<EditFinishedProductAllocationBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(EditFinishedProductAllocationBean bean) {
                        editFinishedProductAllocationBean = bean;
                        if (bean.getCode() == 200) {
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            finish();
                        }
                    }
                });
    }

    //详情赋值
    private void setData(EditFinishedProductAllocationBean.DataBean bean) {
        mTvContent.setText(bean.getReason());
        if (bean.getStockAllotItems().size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.GONE);
        }
        mAdapter.setNewData(bean.getStockAllotItems());
    }

    //删除调拨
    private void deleteAllot() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(AllocationApi.class)
                .deleteAllot(id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("删除成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //调拨提交
    private void submitAllot() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(AllocationApi.class)
                .submitAllot(id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("提交成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //刪除调拨商品
    private void deleteGoods(String inItemId) {
        String sign = MD5Util.encode("inItemId=" + inItemId + "&stockAllotId=" + id);
        RxHttpUtils.createApi(AllocationApi.class)
                .deleteGoods(inItemId, id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("商品删除成功");
                            getProudctAllocationDetails();
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
                        mImageAdapter.setList(selectList);
                        mImageAdapter.notifyDataSetChanged();
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
                        if (null == img_path) {
                            ToastUtil.setToast("请选择文件文件管理内图片");
                            return;
                        }
                        localMedia.setOperationType(1);
                        if (img_path.endsWith(".ppt")) {
                            localMedia.setFileType(1);
                            selectList.add(localMedia);
                            mImageAdapter.notifyDataSetChanged();
                        } else if (img_path.endsWith(".pdf")) {
                            localMedia.setFileType(2);
                            selectList.add(localMedia);
                            mImageAdapter.notifyDataSetChanged();
                        } else if (img_path.endsWith(".docx")) {
                            localMedia.setFileType(3);
                            selectList.add(localMedia);
                            mImageAdapter.notifyDataSetChanged();
                        } else if (img_path.endsWith(".xls")) {
                            localMedia.setFileType(4);
                            selectList.add(localMedia);
                            mImageAdapter.notifyDataSetChanged();
                        } else if (img_path.endsWith(".doc")) {
                            localMedia.setFileType(5);
                            selectList.add(localMedia);
                            mImageAdapter.notifyDataSetChanged();
                        } else if (img_path.endsWith(".jpg") || img_path.endsWith(".png")) {
                            localMedia.setFileType(6);
                            selectList.add(localMedia);
                            mImageAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.setToast("格式错误");
                        }
                }
                break;
        }
    }
}