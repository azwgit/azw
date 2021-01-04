package com.example.bq.edmp.work.marketingactivities.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApplyPayAccountSecondAct;
import com.example.bq.edmp.activity.apply.LocalNewMedia;
import com.example.bq.edmp.activity.apply.PreviewImageAdapter;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.marketingactivities.adapter.RoundRectImageViewAdapter;
import com.example.bq.edmp.work.marketingactivities.api.MarketingActivitiesApi;
import com.example.bq.edmp.work.marketingactivities.bean.ActivitySiteBean;
import com.example.bq.edmp.work.marketingactivities.bean.MarketingActivitiesDetailsBean;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActivitySiteActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, ActivitySiteActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    private int themeId;
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_content)
    TextView mTvContent;//活动目的
    private RoundRectImageViewAdapter adapter;
    List list = new ArrayList<LocalMedia>();
    private ILoadingView loading_dialog;
    private String id = "";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_site;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("活动现场");
        themeId = R.style.picture_QQ_style;
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        getMarketingActivitiesDetails("2");
//        FullyGridLayoutManager manager = new FullyGridLayoutManager(getApplicationContext(), 3, GridLayoutManager.VERTICAL, false);
//        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                int childAdapterPosition = parent.getChildAdapterPosition(view);
                switch (childAdapterPosition % 3) {
                    case 0:
                        outRect.left = 0;
                        outRect.right = 10;
                        break;
                    case 1:
                        outRect.left = 10;
                        outRect.right = 10;
                        break;
                    case 2:
                        outRect.left = 20;
                        outRect.right = 0;
                        break;
                    default:
                        break;
                }
            }
        });
        adapter = new RoundRectImageViewAdapter(getApplicationContext(), null);
        adapter.setList(list);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RoundRectImageViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                PictureSelector.create(ActivitySiteActivity.this).themeStyle(themeId).openExternalPreview(position, list);
            }

        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void otherViewClick(View view) {

    }

    //获取活动详情
    private void getMarketingActivitiesDetails(String type) {
        String sign = MD5Util.encode("id=" + id + "&type=" + type);
        RxHttpUtils.createApi(MarketingActivitiesApi.class)
                .getActivitySiteDetail(id, type, sign)
                .compose(Transformer.<ActivitySiteBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<ActivitySiteBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ActivitySiteBean bean) {
                        if (bean.getCode() == 200) {
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }

    //详情赋值
    private void setData(ActivitySiteBean.DataBean bean) {
        mTvContent.setText(bean.getPurpose());
        for (int i = 0; i < bean.getActivityItems().size(); i++) {
            LocalMedia localMedia = new LocalMedia();
            localMedia.setPath(BaseApi.activity_site_img_url + bean.getActivityItems().get(i).getUri());
            list.add(localMedia);
        }
        adapter.notifyDataSetChanged();
    }
}