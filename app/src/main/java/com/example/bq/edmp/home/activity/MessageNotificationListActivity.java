package com.example.bq.edmp.home.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.work.activity.TestScanActivity;
import com.example.bq.edmp.home.adapter.MessageListAdapter;
import com.example.bq.edmp.home.bean.MessageBean;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import okhttp3.MediaType;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static java.lang.String.valueOf;

public class MessageNotificationListActivity extends BaseTitleActivity implements OnRefreshListener, OnLoadmoreListener , EasyPermissions.PermissionCallbacks{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerview;
    @BindView(R.id.img_ma)
    ImageView mImgMa;
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public static void start(Context context) {
        Intent intent = new Intent(context, MessageNotificationListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        List list=new ArrayList();

        for(int i=0;i<5;i++){
            MessageBean bean=new MessageBean();
            bean.setTitle("嘻嘻哈哈"+i);
            bean.setContent("乐乐呵呵"+i);
            list.add(bean);
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        MessageListAdapter messageListAdapter = new MessageListAdapter(list);
        recyclerview.setAdapter(messageListAdapter);
        messageListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(MessageNotificationListActivity.this, TestScanActivity.class);
                startActivity(intent);
            }
        });
        createBarcodeWithoutContent();

    }
    private void createBarcodeWithoutContent() {
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                int width = BGAQRCodeUtil.dp2px(MessageNotificationListActivity.this, 150);
                int height = BGAQRCodeUtil.dp2px(MessageNotificationListActivity.this, 70);
                return QRCodeEncoder.syncEncodeBarcode("bingoogolapple123", width, height, 0);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    mImgMa.setImageBitmap(bitmap);
//                    saveBitmap(bitmap);
                } else {
                    Toast.makeText(MessageNotificationListActivity.this, "生成条底部不带文字形码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
    @Override
    protected void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        txtTabTitle.setText("消息通知");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_notification;
    }

    @Override
    protected void otherViewClick(View view) {

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }
    public void saveBitmap(Bitmap bitmap) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(),"zxing_image");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "zxing_image" + ".png";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(this.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 通知图库更新
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + "/sdcard/namecard/")));
    }
    }
