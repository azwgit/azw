package com.example.bq.edmp.home.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.allen.library.RxHttpUtils;
import com.allen.library.factory.ApiFactory;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.allen.library.upload.UploadFileApi;
import com.allen.library.upload.UploadHelper;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.MainActivity;
import com.example.bq.edmp.bean.AddressBean;
import com.example.bq.edmp.bean.LoginBean;
import com.example.bq.edmp.home.MessageApi;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.SpUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class UploadImageActivity extends TakePhotoActivity {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private TakePhoto takePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        initData();
    }

    /**
     * 上传文件及参数
     */
    private void sendMultipart() {
        File sdcache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        //设置超时时间及缓存
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        OkHttpClient mOkHttpClient = builder.build();

        MultipartBody.Builder mbody = new MultipartBody.Builder().setType(MultipartBody.FORM);

        List<File> fileList = new ArrayList<File>();
        File img1 = new File("/storage/sdcard0/Download/storage/sdcard0/nv2345/cache/image/5.png.jpg");
        fileList.add(img1);
        File img2 = new File("/storage/sdcard0/Download/storage/sdcard0/nv2345/cache/image/1.png.jpg");
        fileList.add(img2);
        int i = 0;
        for (File file : fileList) {
            if (file.exists()) {
                Log.i("imageName:", file.getName());//经过测试，此处的名称不能相同，如果相同，只能保存最后一个图片，不知道那些同名的大神是怎么成功保存图片的。
                mbody.addFormDataPart("reimburserItemBills" + i, file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
                i++;
            }
        }
        String sign = MD5Util.encode("amount=" + "50" + "&name=" + "測試" + "&reimburserId=" + "109");
        mbody.addFormDataPart("amount", "50");
        mbody.addFormDataPart("reimburserId", "109");
        mbody.addFormDataPart("name", "測試");
        mbody.addFormDataPart("sign", sign);
        RequestBody requestBody = mbody.build();
        Request request = new Request.Builder()
                .header("Access-Token", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0Njk2MzY1MSIsInN1YiI6IjE2LOadjuWbmywxLDYiLCJpc3MiOiJFRFAiLCJpYXQiOjE2MDQ2NDM3MjAsImV4cCI6MTYwNDczMDEyMH0._shdWW6VJmYSep0mrAtwgEEi5Xq2qvjZvFMZwqy8lnc")
                .url("http://192.168.0.188:8081/reimburser/item/newsave")
                .post(requestBody)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("InfoMSG", response.body().string());
            }
        });
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
//        String a=result;
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;

    }

    private void initData() {
        Map<String, Object> paramsMap=new HashMap<>();
        List<File> list=new ArrayList<>();
        String sign = MD5Util.encode("amount=" + "50"  +  "&name=" + "測試"+"&reimburserId=" + "109");
        paramsMap=new HashMap<>();
        paramsMap.put("amount", "50");
//        paramsMap.put("billFile", new File("/storage/sdcard0/Download/storage/sdcard0/nv2345/cache/image/5.png.jpg"));
//        paramsMap.put("billFile", new File("/storage/sdcard0/Download/storage/sdcard0/nv2345/cache/image/1.png.jpg"));
        paramsMap.put("name", "測試");
        paramsMap.put("reimburserId", "109");
        paramsMap.put("sign", sign);
        List<String> filePaths=new ArrayList<>();
//        filePaths.add("/storage/sdcard0/Download/storage/sdcard0/nv2345/cache/image/5.png.jpg");
        filePaths.add("/storage/sdcard0/Download/storage/sdcard0/nv2345/cache/image/1.png.jpg");
        uploadImgAndPar("http://192.168.0.188:8080/reimburser/item/newsave","billFile",paramsMap,filePaths);
    }

    private void uploadImgAndPar(String uploadUrl,String fileName, Map<String, Object> paramsMap,  List<String> uploadPaths) {

        RxHttpUtils.uploadImagesAndParams(uploadUrl, fileName, paramsMap, uploadPaths)
                .compose(Transformer.<ResponseBody>switchSchedulers())
                .subscribe(new CommonObserver<ResponseBody>() {

                    @Override
                    protected String setTag() {
                        return "uploadImg";
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        Log.e("allen", "上传失败: " + errorMsg);
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ResponseBody responseBody) {
                        try {
                            String msg = responseBody.string();
                            Log.e("allen", "上传完毕: " + msg);
                            ToastUtil.setToast(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}