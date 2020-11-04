package com.example.bq.edmp.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseFragment;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 工作
 */
public class WorkFragment extends BaseFragment {


    @BindView(R.id.spgl_ll)
    LinearLayout spgl_ll;
    @BindView(R.id.hygl_ll)
    LinearLayout hygl_ll;
    @BindView(R.id.gwgl_ll)
    LinearLayout gwgl_ll;
    @BindView(R.id.htgl_ll)
    LinearLayout htgl_ll;
    @BindView(R.id.wpgl_ll)
    LinearLayout wpgl_ll;
    @BindView(R.id.rcsq_ll)
    LinearLayout rcsq_ll;
    @BindView(R.id.bzgl_ll)
    LinearLayout bzgl_ll;
    @BindView(R.id.mlgl_ll)
    LinearLayout mlgl_ll;
    @BindView(R.id.cpgl_ll)
    LinearLayout cpgl_ll;
    @BindView(R.id.splgl_ll)
    LinearLayout splgl_ll;
    @BindView(R.id.kcgl_ll)
    LinearLayout kcgl_ll;
    @BindView(R.id.kcpd_ll)
    LinearLayout kcpd_ll;
    @BindView(R.id.zlgl_ll)
    LinearLayout zlgl_ll;
    @BindView(R.id.dfgl_ll)
    LinearLayout dfgl_ll;
    @BindView(R.id.wlgl_ll)
    LinearLayout wlgl_ll;
    @BindView(R.id.khgl_ll)
    LinearLayout khgl_ll;
    @BindView(R.id.ddgl_ll)
    LinearLayout ddgl_ll;
    @BindView(R.id.thgl_ll)
    LinearLayout thgl_ll;
    @BindView(R.id.hkgl_ll)
    LinearLayout hkgl_ll;
    @BindView(R.id.yxhd_ll)
    LinearLayout yxhd_ll;
    @BindView(R.id.pzsf_ll)
    LinearLayout pzsf_ll;

//    @BindView(R.id.hkgl_ll)
//    LinearLayout hkgl_ll;
//    @BindView(R.id.yxhd_ll)
//    LinearLayout yxhd_ll;
//    @BindView(R.id.pzsf_ll)
//    LinearLayout pzsf_ll;


    public WorkFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_word;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void otherViewClick(View view) {

    }

}
