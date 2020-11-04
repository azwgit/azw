package com.example.bq.edmp.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.bq.edmp.R;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * 社区
 */
public class ConmmunityFragment extends Fragment {

    private String url = "http://192.168.0.188:8080/article/Alist?name=&author=&type=&pageNumber=1&pageSize=20&sign=8bdeca11ba59b0ef6d5793be1c60abdf";
    private String Access = "1212";

    public ConmmunityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_conmmunity, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {

        WebView wb = inflate.findViewById(R.id.wb);

        if (Build.VERSION.SDK_INT > 21) {
            wb.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setBlockNetworkImage(false);
        wb.setHorizontalScrollBarEnabled(false);
        wb.getSettings().setLoadWithOverviewMode(true);
        CookieManager.getInstance().setCookie(url, Access);
//        2. 如何给url添加头，需要使用loadUrl(String url, Map < String, String > additionalHttpHeaders) 这个方法。
//        使用方法：
        Map<String, String> map = new HashMap<>();
        map.put("Access-Token", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0Njk2MzY1MSIsInN1YiI6IjE2LOadjuWbmywxLDYiLCJpc3MiOiJFRFAiLCJpYXQiOjE2MDQwMzg5MzcsImV4cCI6MTYwNDEyNTMzN30.Nhu_qfCdtSr1sfG5wOMHtFzCFxe84bgv6QN5Ak90Z8k");
        wb.loadUrl(url, map);

//        wb.loadUrl("http://192.168.0.188:8080/article/Alist?name=&author=&type=&pageNumber=1&pageSize=20&sign=8bdeca11ba59b0ef6d5793be1c60abdf");//显示远程网页
    }

}
