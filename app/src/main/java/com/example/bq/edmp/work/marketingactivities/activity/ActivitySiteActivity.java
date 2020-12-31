package com.example.bq.edmp.work.marketingactivities.activity;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApplyPayAccountSecondAct;
import com.example.bq.edmp.activity.apply.PreviewImageAdapter;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.utils.FullyGridLayoutManager;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.marketingactivities.adapter.RoundRectImageViewAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActivitySiteActivity extends BaseTitleActivity {
    private int themeId;
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    private RoundRectImageViewAdapter adapter;
    List list = new ArrayList<LocalMedia>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_site;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("活动现场");
        for (int i = 0; i < 5; i++) {
            LocalMedia localMediall = new LocalMedia();
            localMediall.setPath("htt://jksdfk.jpg");
            list.add(localMediall);
        }
        themeId = R.style.picture_QQ_style;
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
}