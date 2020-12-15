package com.example.bq.edmp.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.login.LoginActivity;
import com.example.bq.edmp.utils.ProgressWebView;

import butterknife.BindView;

public class ServiceAndDemonstrateFieldWebViewActivity extends BaseActivity {

    //    @BindView(R.id.title_tv)
//    TextView title_tv;
//    @BindView(R.id.return_img)
//    ImageView return_img;
    @BindView(R.id.web_view)
    ProgressWebView mWebView;


    public static final int RE_LOAD_URL = 1;
    private final static int FILECHOOSER_RESULTCODE = 0;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private String url;
    private String cookieKey;
    private String title;
    private String mNewUrl;
    private ValueCallback<Uri> mUploadMessage;
    private int type = 0;
    /**
     * 视频全屏参数
     */
    protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private View customView;
    private FrameLayout fullscreenContainer;
    private WebChromeClient.CustomViewCallback customViewCallback;


    public static Intent newIntent(Context context, String url, String cookieKey) {
        Intent intent = new Intent(context, ServiceAndDemonstrateFieldWebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("cookieKey", cookieKey);
//        intent.putExtra("title", title);
        return intent;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
//        return_img.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        url = getIntent().getStringExtra("url");
        cookieKey = getIntent().getStringExtra("cookieKey");
//        title = getIntent().getStringExtra("title");

//        if (title == null) {
//            title = "";
//            title_tv.setText(title);
//        } else if (title.equals("")) {
//            title_tv.setText(title);
//        } else {
//            title_tv.setText(title);
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(true);//这句话必须保留。。否则无法播放优酷视频网页。。其他的可以
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //Log.e("TAG", url);
            }
        });
        mWebView.addJavascriptInterface(new JSInteface(), "mobile");
        mWebView.loadUrl(url);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_and_demonstrate_field_web_view;
    }

    @Override
    protected void otherViewClick(View view) {
//        switch (view.getId()) {
//            case R.id.return_img:
//                new Thread() {
//                    public void run() {
//                        SystemClock.sleep(10);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (mWebView.canGoBack()) {
//                                    mWebView.goBack();// 返回前一个页面
//                                } else {
//                                    ServiceAndDemonstrateFieldWebViewActivity.this.finish();
//                                }
//                            }
//                        });
//                    }
//                }.start();
//                break;
//        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
          /*  //获取webView的浏览记录
            WebBackForwardList mWebBackForwardList = mWebView.copyBackForwardList();
            //这里的判断是为了让页面在有上一个页面的情况下，跳转到上一个html页面，而不是退出当前activity
            if (mWebBackForwardList.getCurrentIndex() > 0) {
                String historyUrl = mWebBackForwardList.getItemAtIndex(mWebBackForwardList.getCurrentIndex() - 1).getUrl();
                if (!historyUrl.equals("http://test-xcx.51zhongzi.com:7000/serve/applogin?callback=http://test-xcx.51zhongzi.com:7000/serve/index.html")) {
                    mWebView.goBack();
                    return true;
                } else {
                    mWebView.goBackOrForward(-2);
                }
            }*/
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.resumeTimers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWebView.pauseTimers();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
        }
    }

    class JSInteface {

        /**
         * 返回前一个页面
         */
        @JavascriptInterface
        public void gotoFinish() {
            new Thread() {
                public void run() {
                    SystemClock.sleep(10);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mWebView.canGoBack()) {
                                mWebView.goBack();// 返回前一个页面
                            } else {
                                ServiceAndDemonstrateFieldWebViewActivity.this.finish();
                            }
                        }
                    });
                }
            }.start();
        }

        /**
         * 关闭这个webview
         */
        @JavascriptInterface
        public void gotoClose() {
            ServiceAndDemonstrateFieldWebViewActivity.this.finish();
        }

        /**
         * 跳转到浏览器播放视频
         *
         * @param url
         */
        @JavascriptInterface
        public void gotoBrowserPlayVideo(String url) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse(url);
            intent.setData(uri);
            ServiceAndDemonstrateFieldWebViewActivity.this.startActivity(intent);
        }

        /**
         * 跳转到任何页面（简易传参）
         * <p>
         * 网页端跳转样例
         * <a onclick="baobao.gotoAnyWhere('com.example.loveamall.MovieDetailActivity,iOS.MovieDetailViewController:movieId=(int)123')">gotoAnyWhere</a>
         *
         * @param url
         */
        @JavascriptInterface
        public void gotoAnyWhere(String url) {
            if (url == null)
                return;

            String pageName;//获取要跳转的页面名称
            int posPageName = url.indexOf(",");
            if (posPageName == -1) {
                pageName = url;
            } else {
                pageName = url.substring(0, posPageName);
            }

            if (pageName == null || pageName.trim() == "")
                return;
            Intent intent = new Intent();

            int pos = url.indexOf(":");
            if (pos > 0) {//设置跳转参数
                String strParams = url.substring(pos);
                String[] pairs = strParams.split("&");
                for (String strKeyAndValue : pairs) {
                    String[] arr = strKeyAndValue.split("=");
                    String key = arr[0];
                    String value = arr[1];
                    if (value.startsWith("(int)")) {
                        intent.putExtra(key,
                                Integer.valueOf(value.substring(5)));
                    } else if (value.startsWith("(Double)")) {
                        intent.putExtra(key,
                                Double.valueOf(value.substring(8)));
                    } else {
                        intent.putExtra(key, value);
                    }
                }
            }
            try {//反射设置跳转页面
                intent.setClass(ServiceAndDemonstrateFieldWebViewActivity.this, Class.forName(pageName));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            startActivity(intent);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RE_LOAD_URL && resultCode == RESULT_OK) {
            final CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.setAcceptCookie(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cookieManager.flush();
            } else {
                CookieSyncManager instance = CookieSyncManager.createInstance(ServiceAndDemonstrateFieldWebViewActivity.this);
                instance.sync();
            }
            mWebView.loadUrl(mNewUrl);
            //mWebView.clearHistory();
        } else if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage && null == mUploadCallbackAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (mUploadCallbackAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
        if (requestCode != FILECHOOSER_RESULTCODE
                || mUploadCallbackAboveL == null) {
            return;
        }

        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {

            } else {
                String dataString = data.getDataString();
                ClipData clipData = data.getClipData();

                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }

                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        mUploadCallbackAboveL.onReceiveValue(results);
        mUploadCallbackAboveL = null;
        return;
    }


    private class MyWebChromeClient extends WebChromeClient {
        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            ServiceAndDemonstrateFieldWebViewActivity.this.startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            ServiceAndDemonstrateFieldWebViewActivity.this.startActivityForResult(
                    Intent.createChooser(i, "File Browser"),
                    FILECHOOSER_RESULTCODE);
        }

        //For Android 4.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            ServiceAndDemonstrateFieldWebViewActivity.this.startActivityForResult(Intent.createChooser(i, "File Browser"), ServiceAndDemonstrateFieldWebViewActivity.FILECHOOSER_RESULTCODE);
        }

        // For Android 5.0+
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            mUploadCallbackAboveL = filePathCallback;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            ServiceAndDemonstrateFieldWebViewActivity.this.startActivityForResult(
                    Intent.createChooser(i, "File Browser"),
                    FILECHOOSER_RESULTCODE);
            return true;
        }


        /*** 视频播放相关的方法 **/

        @Override
        public View getVideoLoadingProgressView() {
            FrameLayout frameLayout = new FrameLayout(ServiceAndDemonstrateFieldWebViewActivity.this);
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            return frameLayout;
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            showCustomView(view, callback);
        }

        @Override
        public void onHideCustomView() {
            hideCustomView();
        }
    }

    /**
     * 视频播放全屏
     **/
    private void showCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        // if a view already exists then immediately terminate the new one
        if (customView != null) {
            callback.onCustomViewHidden();
            return;
        }

        ServiceAndDemonstrateFieldWebViewActivity.this.getWindow().getDecorView();

        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        fullscreenContainer = new FullscreenHolder(ServiceAndDemonstrateFieldWebViewActivity.this);
        fullscreenContainer.addView(view, COVER_SCREEN_PARAMS);
        decor.addView(fullscreenContainer, COVER_SCREEN_PARAMS);
        customView = view;
        setStatusBarVisibility(false);
        customViewCallback = callback;
    }

    /**
     * 隐藏视频全屏
     */
    private void hideCustomView() {
        if (customView == null) {
            return;
        }

        setStatusBarVisibility(true);
        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        decor.removeView(fullscreenContainer);
        fullscreenContainer = null;
        customView = null;
        customViewCallback.onCustomViewHidden();
        mWebView.setVisibility(View.VISIBLE);
    }

    private void setStatusBarVisibility(boolean visible) {
        int flag = visible ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    /**
     * 全屏容器界面
     */
    static class FullscreenHolder extends FrameLayout {

        public FullscreenHolder(Context ctx) {
            super(ctx);
            setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
        }

        @Override
        public boolean onTouchEvent(MotionEvent evt) {
            return true;
        }
    }

}
