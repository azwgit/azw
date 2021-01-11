package com.example.bq.edmp.work.returnsmanagement.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApprovalAdp;
import com.example.bq.edmp.activity.apply.GridImageAdapter;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.marketingactivities.activity.HistoricalActivitiesDetailsActivity;
import com.example.bq.edmp.work.marketingactivities.adapter.EnclosureAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ReturnsGoodsDetailsActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, ReturnsGoodsDetailsActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }
    @BindView(R.id.approval_recyclerview)
    RecyclerView mApprovalRecyclerView;//审批状态适配器
    private ApprovalAdp mApprovalAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_returns_goods_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("退货详情");
        List<PayInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PayInfoBean bean = new PayInfoBean();
            bean.setId(i + "");
            bean.setDesc("啊啊啊啊");
            list.add(bean);
        }
        mApprovalRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mApprovalAdapter = new ApprovalAdp(list);
        mApprovalRecyclerView.setAdapter(mApprovalAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
        }
    }

}