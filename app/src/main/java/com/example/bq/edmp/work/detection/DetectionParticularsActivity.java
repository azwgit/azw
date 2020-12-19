package com.example.bq.edmp.work.detection;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.detection.adapter.DetectionParticularsJianAdapter;
import com.example.bq.edmp.work.detection.api.DetectionApi;
import com.example.bq.edmp.work.detection.bean.DetectionParticularsBean;

import java.util.List;

import butterknife.BindView;

/*
 * 检测详情
 * */
public class DetectionParticularsActivity extends BaseActivity {

    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.tv_code)
    TextView tv_code;
    @BindView(R.id.hg_tv)
    TextView hg_tv;
    @BindView(R.id.tv_pz)
    TextView pz_tv;
    @BindView(R.id.tv_company)
    TextView tv_company;
    @BindView(R.id.tv_ck_dk)
    TextView tv_ck_dk;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String mId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detection_particulars;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(DetectionParticularsActivity.this);
        title_tv.setText("检测详情");

        mId = getIntent().getStringExtra("ID");

    }


    @Override
    protected void initData() {
        gainData();
    }

    private void gainData() {
        String sign = MD5Util.encode("id=" + mId);

        RxHttpUtils.createApi(DetectionApi.class)
                .getPartiularsList(mId, sign)
                .compose(Transformer.<DetectionParticularsBean>switchSchedulers())
                .subscribe(new NewCommonObserver<DetectionParticularsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DetectionParticularsBean detectionParticularsBean) {
                        String code = detectionParticularsBean.getCode();
                        if (code.equals("200")) {
                            showData(detectionParticularsBean.getData());
                        } else {
                            ToastUtil.setToast("加载失败");
                        }
                    }
                });
    }

    private void showData(DetectionParticularsBean.DataBean data) {
        //1合格 -1不合格 0—
        String results = data.getResults();
        if (results != null) {
            if (results.equals("1")) {
                hg_tv.setText("合格");
                hg_tv.setBackground(getResources().getDrawable(R.drawable.detection_yes_shape));
            } else if (results.equals("-1")) {
                hg_tv.setText("不合格");
                hg_tv.setBackground(getResources().getDrawable(R.drawable.detection_no_shape));
            }
        } else {
            hg_tv.setText("暂无");
        }

        tv_code.setText(data.getCode());
        pz_tv.setText(data.getVarietyName());
        tv_company.setText(data.getOrgName());
        tv_ck_dk.setText(data.getFarmLandCode() + data.getWareshouseName());
        tv_time.setText(data.getAddedTime());
        tv_username.setText(data.getTesterName());

        List<DetectionParticularsBean.DataBean.TestPlanItemBean> testPlanItem = data.getTestPlanItem();
        if (testPlanItem != null && testPlanItem.size() != 0) {
            DetectionParticularsJianAdapter detectionParticularsJianAdapter = new DetectionParticularsJianAdapter(testPlanItem);
            recyclerView.setLayoutManager(new LinearLayoutManager(DetectionParticularsActivity.this));
            recyclerView.setAdapter(detectionParticularsJianAdapter);
        } else {

        }

    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(DetectionParticularsActivity.this);
    }
}
