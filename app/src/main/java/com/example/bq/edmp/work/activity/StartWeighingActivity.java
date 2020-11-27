package com.example.bq.edmp.work.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.utils.Constant;

import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class StartWeighingActivity extends BaseTitleActivity implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.tv_start)
    TextView mTvStart;//开始扫描
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;

    public static void newIntent(Context context, String title) {
        Intent intent = new Intent(context, StartWeighingActivity.class);
        intent.putExtra(Constant.TITLE, title);
        context.startActivity(intent);
    }

    private String title = "称重";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start_weighing;
    }

    @Override
    protected void initView() {
        title = getIntent().getStringExtra(Constant.TITLE);
        txtTabTitle.setText(title);
        ProApplication.getinstance().addActivity(StartWeighingActivity.this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTvStart.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start:
                TestScanActivity.newIntent(StartWeighingActivity.this,title);
                finish();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
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
}