package com.example.bq.edmp.work.marketingactivities.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ActivitiesListFragment extends BaseFragment {

    private String mID = "0";

    @SuppressLint("ValidFragment")
    public ActivitiesListFragment(String s) {
        // Required empty public constructor
        mID = s;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_activities_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void otherViewClick(View view) {

    }
}
