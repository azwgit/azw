package com.example.bq.edmp.work.grainmanagement.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.GridItemDecoration;
import com.example.bq.edmp.work.grainmanagement.adapter.DetectionListAdp;
import com.example.bq.edmp.work.grainmanagement.adapter.DetectionSelectListAdp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewAcquisitionsActivity extends BaseTitleActivity implements DetectionListAdp.SaveEditListener {
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.tv_contractor)
    TextView mTvContractor;
    PopupWindow mTypePopuWindow;
    private DetectionListAdp detectionListAdp;
    private List<PayInfoBean> list;

    @Override
    protected void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PayInfoBean bean = new PayInfoBean();
            bean.setId(i + "");
            bean.setDesc("啊啊啊啊");
            list.add(bean);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
        mRecyclerView.addItemDecoration(gridItemDecoration);
        detectionListAdp = new DetectionListAdp(list);
        mRecyclerView.setAdapter(detectionListAdp);
    }

    @Override
    protected void initListener() {
        mTvSubmit.setOnClickListener(this);
        mTvContractor.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(NewAcquisitionsActivity.this);
        txtTabTitle.setText("新增收购");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_acquisitions;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
//                addAcquisitions();
//                showType();
                NewAcquisitionsSuccessActivity.newIntent(getApplicationContext(),"1");
                break;
            case R.id.tv_contractor:
                break;
        }
    }


    //选中授权类型
    private void showType() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        List<PayInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PayInfoBean bean = new PayInfoBean();
            bean.setId(i + "");
            bean.setDesc("啊啊啊啊");
            list.add(bean);
        }
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        DetectionSelectListAdp detectionListAdp = new DetectionSelectListAdp(list);
        myRecyclerViewOne.setAdapter(detectionListAdp);
        mLyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTypePopuWindow.dismiss();
            }
        });
        mTypePopuWindow = new PopupWindow();
        mTypePopuWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mTypePopuWindow.setContentView(contentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        mTypePopuWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        mTypePopuWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        // 实例化一个ColorDrawable颜色为透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        mTypePopuWindow.setBackgroundDrawable(dw);
        // 设置SelectPicPopupWindow弹出窗体可点击
        mTypePopuWindow.setFocusable(true);
//        backgroundAlpha(0.4f);
        mTypePopuWindow.setOutsideTouchable(true);
        mTypePopuWindow.setClippingEnabled(false);
        mTypePopuWindow.showAtLocation(findViewById(R.id.rl_view), Gravity.BOTTOM, 0, 0);
    }

    private void addAcquisitions() {

    }

    /***获取承包人列表***/
    private void alertContractorDialog() {

    }

    private void checkData() {
        for (int i = 0; i < list.size(); i++) {
            PayInfoBean payInfoBean = list.get(i);
            int index = Integer.parseInt(payInfoBean.getId());
            if (index == 0) {
                if (payInfoBean.getDesc().equals("")) {
                    Toast.makeText(getApplicationContext(), "请输入水份", Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (index == 1) {
                if (payInfoBean.getDesc().equals("")) {
                    Toast.makeText(getApplicationContext(), "请输入杂质", Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (index == 2) {
                if (payInfoBean.getDesc().equals("")) {
                    Toast.makeText(getApplicationContext(), "请输入容量", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }

    @Override
    public void SaveEdit(int position, String string) {
        list.get(position).setDesc(string);
    }
}