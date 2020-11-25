package com.example.bq.edmp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bq.edmp.ProApplication;
import com.youth.banner.loader.ImageLoader;

//自定义的图片加载器
    public class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(ProApplication.getmContext()).load((String) path).into(imageView);
        }

}