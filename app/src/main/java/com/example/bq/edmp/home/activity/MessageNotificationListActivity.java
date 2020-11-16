package com.example.bq.edmp.home.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.activity.login.LoginApi;
import com.example.bq.edmp.home.adapter.MessageListAdapter;
import com.example.bq.edmp.home.bean.MessageBean;
import com.example.bq.edmp.utils.SpUtils;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.LoginBean;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

public class MessageNotificationListActivity extends BaseTitleActivity implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerview;
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

        String password = "1234";
        String username = "12346963651";
        String sign = MD5Util.encode("password=" + password + "&username=" + username);
        RxHttpUtils.createApi(LoginApi.class)
                .login(password, username, sign)
                .compose(Transformer.<LoginBean>switchSchedulers())
                .subscribe(new CommonObserver<LoginBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(LoginBean loginBean) {
                        SpUtils.put("UserInfo", loginBean.getData());
                        ToastUtil.setToast("成功");
                    }
                });

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


        /**
         * 上传文件及参数
         */
        private void sendMultipart(){
            File sdcache = getExternalCacheDir();
            int cacheSize = 10 * 1024 * 1024;
            //设置超时时间及缓存
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
            OkHttpClient mOkHttpClient=builder.build();

            MultipartBody.Builder mbody=new MultipartBody.Builder().setType(MultipartBody.FORM);

            List<File> fileList=new ArrayList<File>();
            File img1=new File("/sdcard/wangshu.jpg");
            fileList.add(img1);
            File img2=new File("/sdcard/123.jpg");
            fileList.add(img2);
            int i=0;
            for(File file:fileList){
                if(file.exists()){
                    Log.i("imageName:",file.getName());//经过测试，此处的名称不能相同，如果相同，只能保存最后一个图片，不知道那些同名的大神是怎么成功保存图片的。
                    mbody.addFormDataPart("image"+i,file.getName(),RequestBody.create(MEDIA_TYPE_PNG,file));
                    i++;
                }
            }

            RequestBody requestBody =mbody.build();
            Request request = new Request.Builder()
                    .header("Authorization", "Client-ID " + "...")
                    .url("http://192.168.1.105/interface/index.php?action=sendMultipart")
                    .post(requestBody)
                    .build();

            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.i("InfoMSG", response.body().string());
                }
            });
        }

    }
