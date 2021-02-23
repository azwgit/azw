package com.example.bq.edmp.work.materialmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.download.DownloadObserver;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApprovalAdp;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.FromtUtil;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.OpenFiles;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.marketingactivities.adapter.EnclosureAdapter;
import com.example.bq.edmp.work.materialmanagement.adapter.MaterielDetailsListAdp;
import com.example.bq.edmp.work.materialmanagement.api.MaterialManagementApi;
import com.example.bq.edmp.work.materialmanagement.bean.ProcurementDetailsBean;
import com.example.bq.edmp.work.returnsmanagement.eventbus.CloseActivity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 采购详情
 * */
public class MaterialConfirmDetailsActivity extends BaseActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, MaterialConfirmDetailsActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.approval_recyclerview)
    RecyclerView mApprovalRecyclerView;//审批状态适配器
    @BindView(R.id.procurement_recyclerview)
    RecyclerView procurement_recyclerview;//审批状态适配器
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
    @BindView(R.id.jine_tv)
    TextView jine_tv;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.ly_shenpi)
    LinearLayout mLyShenPi;//审批父布局
    @BindView(R.id.ly_ok)
    LinearLayout mLyOk;//已完成父布局
    @BindView(R.id.tv_time)
    TextView mTvTime;//完成时间
    @BindView(R.id.tv_subsidiary_company)
    TextView mTvSubsidiaryCompany;//分子公司
    @BindView(R.id.tv_warehouse)
    TextView mTvWarehouse;//仓库
    @BindView(R.id.tv_operator)
    TextView mTvOperator;//操作人
    @BindView(R.id.ly_bottom)
    LinearLayout mLyBottom;//底部操作按钮
    @BindView(R.id.tv_enclosure_number)
    TextView mTvEnclosureNumber;//附件数量
    private ApprovalAdp mApprovalAdapter;
    private MaterielDetailsListAdp materielDetailsListAdp;
    private EnclosureAdapter adapter;
    private PopupWindow mTypePopuWindow;
    private int chooseMode = PictureMimeType.ofAll();
    private int maxSelectNum = 9;
    private int themeId;
    private List<LocalMedia> selectList = new ArrayList<>();
    private String mid;
    private LoadingDialog loadingDialog;
    private String huodongid = "";
    List<LocalMedia> imglist = new ArrayList<LocalMedia>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_procurement_details;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(MaterialConfirmDetailsActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("采购详情");
        themeId = R.style.picture_QQ_style;
        mid = getIntent().getStringExtra(Constant.ID);
        List<PayInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PayInfoBean bean = new PayInfoBean();
            bean.setId(i + "");
            bean.setDesc("啊啊啊啊");
            list.add(bean);
        }
        FullyGridLayoutManager manager = new FullyGridLayoutManager(getApplicationContext(), 4, GridLayoutManager.VERTICAL, false);
        pic_recyclerview.setLayoutManager(manager);
        adapter = new EnclosureAdapter(getApplicationContext(), null);
        adapter.setList(imglist);
        pic_recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new EnclosureAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (imglist.size() > 0) {
                    LocalMedia media = imglist.get(position);
                    int mediaType = media.getFileType();
                    String path = media.getPath();
                    //图片预览
                    if (mediaType == 6) {
                        List list = new ArrayList<LocalMedia>();
                        LocalMedia localMedia = new LocalMedia();
                        localMedia.setPath(media.getDownLoadUrl());
                        list.add(localMedia);
                        PictureSelector.create(MaterialConfirmDetailsActivity.this).themeStyle(themeId).openExternalPreview(0, list);
                        return;
                    }
                    File file = new File(path);
                    if (!file.exists()) {
                        download(media.getFileName(), position, media.getDownLoadUrl());
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
        mApprovalRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mApprovalAdapter = new ApprovalAdp(list);
        mApprovalRecyclerView.setAdapter(mApprovalAdapter);
        procurement_recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        materielDetailsListAdp = new MaterielDetailsListAdp(null);
        procurement_recyclerview.setAdapter(materielDetailsListAdp);

    }

    @Override
    protected void initData() {
        String sign = MD5Util.encode("id=" + mid);

        RxHttpUtils.createApi(MaterialManagementApi.class)
                .getMaterialDetail(mid, sign)
                .compose(Transformer.<ProcurementDetailsBean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<ProcurementDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ProcurementDetailsBean trackingDereBean) {
                        if (trackingDereBean.getCode() == 200) {
                            ProcurementDetailsBean.DataBean data = trackingDereBean.getData();
                            initDataMethod(data);

                        } else {
                            ToastUtil.setToast("加载失败");
                        }
                    }
                });
    }


    //赋值数据
    private void initDataMethod(ProcurementDetailsBean.DataBean data) {
        String id = data.getId() + "";
        if (id != null && !id.equals("")) {
            huodongid = id;
        }
        //2审批中3审批拒绝4待完成 5 已完成
        String status = data.getStatus() + "";
        if (status != null) {
            if (status.equals("2")) {
                dtj_tv.setText("待审批");
                mLyBottom.setVisibility(View.GONE);
            } else if (status.equals("3")) {
                dtj_tv.setText("审批拒绝");
                mLyBottom.setVisibility(View.VISIBLE);
            } else if (status.equals("4")) {
                dtj_tv.setText("待完成");
                mLyBottom.setVisibility(View.VISIBLE);
                mBtnComplete.setVisibility(View.VISIBLE);
                btn_del.setVisibility(View.GONE);
                btn_submit.setVisibility(View.GONE);
            } else if (status.equals("5")) {
                dtj_tv.setText("已完成");
                btn_del.setVisibility(View.GONE);
                btn_submit.setVisibility(View.GONE);
                mLyOk.setVisibility(View.VISIBLE);
                mTvTime.setText(data.getFinishedTime());
                mTvSubsidiaryCompany.setText(data.getDeptName());
                mTvWarehouse.setText(data.getWarehouseName());
                mTvOperator.setText(data.getFinishedOperator());
                mLyBottom.setVisibility(View.GONE);
            }
        }
        tv_title.setText("采购详情 " + data.getCode());
        tv_company.setText(data.getDeptName());
        tv_dept.setText(data.getAddedOperator());
        jine_tv.setText("¥" + FromtUtil.getFromt(data.getAmount()));
        tv_number.setText(data.getApprovalFlow().getId() + "");
        String stautsString = "";
        switch (data.getApprovalFlow().getApproveStatus()) {
            case 1:
                stautsString = "审批中";
                break;
            case 2:
                stautsString = "通过";
                break;
            case 3:
                for (int i = 0; i < data.getApprovalFlow().getApprovalFlowLevels().size(); i++) {
                    if (data.getApprovalFlow().getApprovalFlowLevels().get(i).getApprovedStatus() == 3) {
                        stautsString = "拒绝 原因：" + data.getApprovalFlow().getApprovalFlowLevels().get(i).getRemark();
                    }
                }
                break;
            case -1:
                stautsString = "撤回";
                break;
        }
        tv_stauts.setText(stautsString);
        for (int i = 0; i < data.getMaterialPurchaseAnnexs().size(); i++) {
            LocalMedia localMedia = new LocalMedia();
            if (data.getMaterialPurchaseAnnexs().get(i).getUri().endsWith(".ppt")) {
                localMedia.setFileType(1);
            } else if (data.getMaterialPurchaseAnnexs().get(i).getUri().endsWith(".pdf")) {
                localMedia.setFileType(2);
            } else if (data.getMaterialPurchaseAnnexs().get(i).getUri().endsWith(".docx")) {
                localMedia.setFileType(3);
            } else if (data.getMaterialPurchaseAnnexs().get(i).getUri().endsWith(".xls")) {
                localMedia.setFileType(4);
            } else if (data.getMaterialPurchaseAnnexs().get(i).getUri().endsWith(".doc")) {
                localMedia.setFileType(5);
            } else if (data.getMaterialPurchaseAnnexs().get(i).getUri().endsWith(".jpg") || data.getMaterialPurchaseAnnexs().get(i).getUri().endsWith(".png")) {
                localMedia.setFileType(6);
            } else {
                localMedia.setFileType(1);
            }
            String path = data.getMaterialPurchaseAnnexs().get(i).getUri().substring(data.getMaterialPurchaseAnnexs().get(i).getUri().lastIndexOf("/") + 1);
            localMedia.setPath(getApplicationContext().getExternalFilesDir(null).getPath() + "/" + path);
            localMedia.setFileName(path);
            localMedia.setDownLoadUrl(BaseApi.material_img_url + data.getMaterialPurchaseAnnexs().get(i).getUri());
            imglist.add(localMedia);
        }
        adapter.notifyDataSetChanged();
        if (data.getMaterialPurchaseItems().size() > 0) {
            int number = data.getMaterialPurchaseAnnexs().size();
            mTvEnclosureNumber.setText("附件(" + number + ")");
        } else {
            mTvEnclosureNumber.setText("附件(无)");
        }
        materielDetailsListAdp.setNewData(data.getMaterialPurchaseItems());
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
                break;
            case R.id.btn_submit:
                Reapply();
                break;
            case R.id.btn_del:
                deleteMaterial();
                break;
        }
    }

    //重新提交
    private void Reapply() {
        String sign = MD5Util.encode("id=" + huodongid);
        RxHttpUtils.createApi(MaterialManagementApi.class)
                .reapplyMaterial(huodongid, sign)
                .compose(Transformer.<BaseABean>switchSchedulers())
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean trackingDereBean) {
                        if (trackingDereBean.getCode() == 200) {
                            ToastUtil.setToast("提交成功");
                            EditMaterialActivity.newIntent(getApplicationContext(), "2", trackingDereBean.getData());
                            finish();
                        } else {
                            ToastUtil.setToast("提交失败");
                        }
                    }
                });
    }

    //删除采购
    private void deleteMaterial() {
        String sign = MD5Util.encode("id=" + huodongid);
        RxHttpUtils.createApi(MaterialManagementApi.class)
                .deleteMaterial(huodongid, sign)
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
                            EventBus.getDefault().post(new CloseActivity());
                            finish();
                        } else {
                            ToastUtil.setToast("活动删除失败");
                        }
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(MaterialConfirmDetailsActivity.this);
    }

    //文件下载
    private void download(final String fileName, final int position, String url) {
        if (loadingDialog != null) {
            loadingDialog.showLoadingView();
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
                        ToastUtil.setToast(errorMsg);
                        if (loadingDialog != null) {
                            loadingDialog.hideLoadingView();
                        }
                    }

                    @Override
                    protected void onSuccess(long bytesRead, long contentLength, float progress, boolean done, String filePath) {
                        if (done) {
                            if (loadingDialog != null) {
                                loadingDialog.hideLoadingView();
                            }
                            imglist.get(position).setPath(getApplicationContext().getExternalFilesDir(null).getPath() + "/" + fileName);
                            adapter.notifyDataSetChanged();
                            startActivity(OpenFiles.getPdfFileIntent(getApplicationContext().getExternalFilesDir(null).getPath() + "/" + fileName));
                        }
                    }
                });
    }

}