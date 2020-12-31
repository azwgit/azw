package com.example.bq.edmp.work.marketingactivities.activity;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.allen.library.RxHttpUtils;
import com.allen.library.download.DownloadObserver;
import com.allen.library.interfaces.ILoadingView;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.PreviewImageAdapter;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.OpenFiles;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.marketingactivities.adapter.EnclosureAdapter;
import com.example.bq.edmp.work.marketingactivities.adapter.HistoricalEnclosureAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActivityEnclosureActivity extends BaseTitleActivity {
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    private HistoricalEnclosureAdapter historicalEnclosureAdapter;
    List<LocalMedia> selectList = new ArrayList<LocalMedia>();
    private ILoadingView loading_dialog;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_enclosure;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("活动附件");
        for (int i = 0; i < 5; i++) {
            LocalMedia localMedia = new LocalMedia();
            localMedia.setFileType(1);
            selectList.add(localMedia);
        }
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
                        download(mediaType, "aaa.pdf", position, "http://192.168.0.188:8010/license/2020/1608884114661-2417.pdf");
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
                    }

                    @Override
                    protected void onSuccess(long bytesRead, long contentLength, float progress, boolean done, String filePath) {
                        if (done) {
                            if (loading_dialog != null) {
                                loading_dialog.hideLoadingView();
                            }
                            selectList.get(position).setPath(getApplicationContext().getExternalFilesDir(null).getPath() + "/" + fileName);
                            selectList.get(position).setFileType(1);
                            historicalEnclosureAdapter.notifyDataSetChanged();
                            startActivity(OpenFiles.getPdfFileIntent(getApplicationContext().getExternalFilesDir(null).getPath() + "/" + fileName));
                        }
                    }
                });
    }
}