package com.example.bq.edmp.base;

/**
 * Created by tl on 2018/6/14.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected View rootView;

    private Unbinder mUnbinder;
    //当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
    private boolean isFragmentVisible;
    //是否是第一次开启网络加载
    public boolean isFirst;
    protected Bundle mBundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(getLayoutResource(), container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        mBundle = savedInstanceState;
        initView();
        //可见，但是并没有加载过
        if (isFragmentVisible && !isFirst) {
            onFragmentVisibleChange(true);
        }
        return rootView;
    }


    //获取布局文件
    protected abstract int getLayoutResource();


    //初始化view
    protected abstract void initView();


    protected abstract void otherViewClick(View view);

    /**
     * 点击的事件的统一的处理
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                otherViewClick(view);
                break;
        }
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isFragmentVisible = true;
        }
        if (rootView == null) {
            return;
        }
        //可见，并且没有加载过
        if (!isFirst && isFragmentVisible) {
            onFragmentVisibleChange(true);
            return;
        }
        //由可见——>不可见 已经加载过
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }

    /**
     * 网络访问错误提醒
     */
    public void showNetErrorTip() {

    }

    public void showNetErrorTip(String error) {

    }

    public void showLoading() {

    }

    public void stopLoading() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作.
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

}
