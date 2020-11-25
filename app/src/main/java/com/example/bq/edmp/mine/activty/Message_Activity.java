package com.example.bq.edmp.mine.activty;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.allen.library.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.mine.api.MineApi;
import com.example.bq.edmp.mine.bean.MineUserInfoBean;
import com.example.bq.edmp.mine.bean.StateBean;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.PictureUtils;
import com.example.bq.edmp.utils.SetIconDialog;
import com.example.bq.edmp.utils.SharedPreferencesUtils;
import com.example.bq.edmp.utils.SpUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.TurnImgStringUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.functions.Action1;

/*
 * 个人信息
 * */
public class Message_Activity extends BaseActivity {

    private static final String TAG = "Message_Activity==";
    @BindView(R.id.head_rl)
    RelativeLayout head_rl;
    @BindView(R.id.head_img)
    ImageView head_img;
    @BindView(R.id.signature_img)
    ImageView signature_img;
    @BindView(R.id.phone_rl)
    RelativeLayout phone_rl;
    @BindView(R.id.special_rl)
    RelativeLayout special_rl;
    @BindView(R.id.mail_rl)
    RelativeLayout mail_rl;
    @BindView(R.id.user_name_tv)
    TextView user_name_tv;
    @BindView(R.id.section_tv)
    TextView section_tv;
    @BindView(R.id.post)
    TextView post;
    @BindView(R.id.phone_tv)
    TextView phone_tv;
    @BindView(R.id.special_tv)
    TextView special_tv;
    @BindView(R.id.mail_tv)
    TextView mail_tv;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.signature_rl)
    RelativeLayout signature_rl;


    private String USERID;
    private boolean mTakePicture = false;
    private boolean hasPermission;
    private static final String DIALOG_ICON = "CustomerProfileActivity.dialog_icon";
    private File outputPhotoFile;
    private static final int CROP_PHOTO = 0;
    private String TYPEA = "1";
    private ILoadingView loading_dialog;


    @Override
    protected void initData() {
        //获取详细基本信息
        gainData();

    }

    private void gainData() {
        String sign = MD5Util.encode("id=" + USERID);

        RxHttpUtils.createApi(MineApi.class)
                .getMineUserData(USERID, sign)
                .compose(Transformer.<MineUserInfoBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<MineUserInfoBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(MineUserInfoBean mineUserInfoBean) {
                        if (mineUserInfoBean.getCode() == 200) {
                            clearImageAllCache();
                            assignment(mineUserInfoBean.getData());
                        } else {
                            ToastUtil.setToast(mineUserInfoBean.getMsg());
                        }

                    }
                });
    }

    /**
     * 清除图片磁盘缓存
     */
    public void clearImageDiskCache() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(getApplicationContext()).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(getApplicationContext()).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片内存缓存
     */
    public void clearImageMemoryCache() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(getApplicationContext()).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片所有缓存
     */
    public void clearImageAllCache() {
        clearImageDiskCache();
        clearImageMemoryCache();
    }

    private void assignment(MineUserInfoBean.DataBean data) {
        Glide.with(Message_Activity.this)
                .load(BaseApi.head_img_url + TurnImgStringUtils.isImgPath(data.getHeadImg()))
                .apply(new RequestOptions()
                        .dontAnimate()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .circleCrop())
                .into(head_img);
        Glide.with(Message_Activity.this)
                .load(BaseApi.head_img_url + TurnImgStringUtils.isImgPath(data.getSignImg()))
                .apply(new RequestOptions()
                        .dontAnimate()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                )
                .into(signature_img);
        user_name_tv.setText(data.getName());
        section_tv.setText(data.getDeptName());
        post.setText(data.getTitle());
        if (data.getMobTel() != null && !data.getMobTel().equals("")) {
            phone_tv.setText(data.getMobTel());
        } else {
            phone_tv.setText("暂无手机号");
        }
        if (data.getOfficeTel() != null && !data.getOfficeTel().equals("")) {
            special_tv.setText(data.getOfficeTel());
        } else {
            special_tv.setText("暂无座机");
        }
        if (data.getEmail() != null && !data.getEmail().equals("")) {
            mail_tv.setText(data.getEmail());
        } else {
            mail_tv.setText("暂无邮箱");
        }

    }

    @Override
    protected void initListener() {
        head_rl.setOnClickListener(this);
        phone_rl.setOnClickListener(this);
        special_rl.setOnClickListener(this);
        signature_img.setOnClickListener(this);
        mail_rl.setOnClickListener(this);
        return_img.setOnClickListener(this);
        signature_rl.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        hasPermission = SharedPreferencesUtils.getBoolean(Message_Activity.this, "hasPermission", false);
        outputPhotoFile = new File(Environment.getExternalStorageDirectory(), "userIcon.jpg");

        try {
            if (outputPhotoFile.exists()) outputPhotoFile.delete();
            outputPhotoFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        USERID = (String) SpUtils.get("UserId", "");
        title_tv.setText("个人信息");
        loading_dialog = new LoadingDialog(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }


    @Override
    protected void otherViewClick(View view) {
        RxPermissions rxPermissions = new RxPermissions(Message_Activity.this);
        String phone = phone_tv.getText().toString();
        String special = special_tv.getText().toString();
        String mail = mail_tv.getText().toString();
        Intent intent = null;
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.phone_rl://手机号
                if (!phone.equals("") && phone != null) {
                    intent = new Intent(Message_Activity.this, Amend_Activity.class);
                    intent.putExtra("type", 0);
                    intent.putExtra("number", phone);
                    startActivityForResult(intent, 3);
                }
                break;
            case R.id.special_rl://座机
                if (!special.equals("") && special != null) {
                    intent = new Intent(Message_Activity.this, Amend_Activity.class);
                    intent.putExtra("type", 1);
                    intent.putExtra("number", special);
                    startActivityForResult(intent, 3);
                }
                break;
            case R.id.mail_rl://邮箱
                if (!mail.equals("") && mail != null) {
                    intent = new Intent(Message_Activity.this, Amend_Activity.class);
                    intent.putExtra("type", 2);
                    intent.putExtra("number", mail);
                    startActivityForResult(intent, 3);
                }
                break;
            case R.id.head_rl://头像
                TYPEA = "1";
                RxView.clicks(head_rl)
                        .compose(rxPermissions.ensure(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {
                                    if (mTakePicture || hasPermission) {
                                        FragmentManager fm = getSupportFragmentManager();
                                        SetIconDialog dialog = new SetIconDialog();
                                        dialog.show(fm, DIALOG_ICON);
                                    } else {
                                        mTakePicture = true;
                                    }
                                    SharedPreferencesUtils.putBoolean(Message_Activity.this, "hasPermission", true);
                                } else {
                                    ToastUtils.showToast("请在权限设置中打开相机权限和读取外部存储权限");
                                }
                            }
                        });
                break;
            case R.id.signature_rl://签名
                TYPEA = "2";
                RxView.clicks(signature_rl)
                        .compose(rxPermissions.ensure(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {
                                    if (mTakePicture || hasPermission) {
                                        FragmentManager fm = getSupportFragmentManager();
                                        SetIconDialog dialog = new SetIconDialog();
                                        dialog.show(fm, DIALOG_ICON);
                                    } else {
                                        mTakePicture = true;
                                    }
                                    SharedPreferencesUtils.putBoolean(Message_Activity.this, "hasPermission", true);
                                } else {
                                    ToastUtils.showToast("请在权限设置中打开相机权限和读取外部存储权限");
                                }
                            }
                        });
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mTakePicture) {
            FragmentManager fm = getSupportFragmentManager();
            SetIconDialog dialog = new SetIconDialog();
            dialog.show(fm, DIALOG_ICON);
            mTakePicture = false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && resultCode == 250) {
            ToastUtil.setToast("修改成功");
            //获取详细基本信息
            gainData();
        }
        Uri imageUri = Uri.fromFile(outputPhotoFile);
        if (requestCode == SetIconDialog.TAKE_PICTURE && resultCode == Activity.RESULT_OK) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(imageUri, "image/*");
            intent.putExtra("scale", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);// 将图片存至imageUri
            startActivityForResult(intent, CROP_PHOTO);
        } else if (requestCode == SetIconDialog.CHOOSE_PICTURE && resultCode == Activity.RESULT_OK) {
            Uri sourceUri = data.getData();// 获取选择图片的路径
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(sourceUri, "image/*");
            intent.putExtra("scale", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);// 将图片存至imageUri
            startActivityForResult(intent, CROP_PHOTO);
        } else if (requestCode == CROP_PHOTO && resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = PictureUtils.getScaledBitmap(outputPhotoFile.getPath());// 压缩图片

            try {
                FileOutputStream fos = new FileOutputStream(outputPhotoFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            uploadIcon();
        }
    }

    private HashMap<String, RequestBody> FileImgs = new HashMap<>();

    private void uploadIcon() {
        FileImgs.clear();
        String sign = MD5Util.encode("type=" + TYPEA);
        RequestBody photo = RequestBody.create(MediaType.parse("image/png"), outputPhotoFile);
        FileImgs.put("imgFile\"; filename=\"icon" + 0 + ".png", photo);

        RxHttpUtils.createApi(MineApi.class)
                .personImg(FileImgs, TYPEA, sign)
                .compose(Transformer.<StateBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<StateBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(StateBean stateBean) {
                        if (stateBean.getCode() != null && !stateBean.getCode().equals("")) {
                            if (TYPEA.equals("1")) {
                                ToastUtil.setToast("头像上传成功");
                            } else {
                                ToastUtil.setToast("签名图片上传成功");
                            }
                        } else {
                            if (TYPEA.equals("2")) {
                                ToastUtil.setToast("头像上传失败");
                            } else {
                                ToastUtil.setToast("签名图片上传失败");
                            }
                        }
                        //获取详细基本信息
                        gainData();
                    }
                });

    }


}
