package com.example.bq.edmp.work.marketingactivities.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.download.DownloadObserver;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.PreviewImageAdapter;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.OpenFiles;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.marketingactivities.adapter.EnclosureAdapter;
import com.example.bq.edmp.work.marketingactivities.adapter.HistoricalEnclosureAdapter;
import com.example.bq.edmp.work.marketingactivities.api.MarketingActivitiesApi;
import com.example.bq.edmp.work.marketingactivities.bean.MarketingActivitiesDetailsBean;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActivityEnclosureActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, ActivityEnclosureActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    private HistoricalEnclosureAdapter historicalEnclosureAdapter;
    List<LocalMedia> selectList = new ArrayList<LocalMedia>();
    private ILoadingView loading_dialog;
    private String id = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enclosure;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("活动附件");
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        getMarketingActivitiesDetails("1");
//        FullyGridLayoutManager manager = new FullyGridLayoutManager(getApplicationContext(), 3, GridLayoutManager.VERTICAL, false);
//        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                int childAdapterPosition = parent.getChildAdapterPosition(view);
                switch (childAdapterPosition % 3) {
                    case 0:
                        outRect.left = 0;
                        outRect.right = 10;
                        break;
                    case 1:
                        outRect.left = 10;
                        outRect.right = 10;
                        break;
                    case 2:
                        outRect.left = 10;
                        outRect.right = 0;
                        break;
                    default:
                        break;
                }
            }
        });
        historicalEnclosureAdapter = new HistoricalEnclosureAdapter(getApplicationContext(), null);
        historicalEnclosureAdapter.setList(selectList);
        mRecyclerView.setAdapter(historicalEnclosureAdapter);
        historicalEnclosureAdapter.setOnItemClickListener(new HistoricalEnclosureAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    int mediaType = media.getFileType();
                    String path = media.getPath();
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
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void otherViewClick(View view) {

    }

    //文件下载
    private void download(final String fileName, final int position, String url) {
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
                    }

                    @Override
                    protected void onSuccess(long bytesRead, long contentLength, float progress, boolean done, String filePath) {
                        if (done) {
                            if (loading_dialog != null) {
                                loading_dialog.hideLoadingView();
                            }
                            selectList.get(position).setPath(getApplicationContext().getExternalFilesDir(null).getPath() + "/" + fileName);
                            historicalEnclosureAdapter.notifyDataSetChanged();
                            startActivity(OpenFiles.getPdfFileIntent(getApplicationContext().getExternalFilesDir(null).getPath() + "/" + fileName));
                        }
                    }
                });
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
                        if (bean.getCode() == 200) {
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //详情赋值
    private void setData(MarketingActivitiesDetailsBean.DataBean bean) {
        mTvContent.setText(bean.getPurpose());
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
            selectList.add(localMedia);
        }
        historicalEnclosureAdapter.notifyDataSetChanged();
    }
}