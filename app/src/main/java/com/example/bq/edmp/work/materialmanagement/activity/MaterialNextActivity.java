package com.example.bq.edmp.work.materialmanagement.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.goodsgrainmanagement.api.GoodsSalesApi;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.SelecGoodsListBean;
import com.example.bq.edmp.work.materialmanagement.api.MaterialManagementApi;
import com.example.bq.edmp.work.materialmanagement.bean.UserInfoBean;

import java.util.List;

import butterknife.BindView;

public class MaterialNextActivity extends BaseTitleActivity {
    @BindView(R.id.tv_department)
    TextView mTvDepartment;//部门
    @BindView(R.id.tv_applicant)
    TextView mTvApplicant;//申请人
    @BindView(R.id.ed_content)
    EditText mEdContent;//申请原因
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;//下一步
    private LoadingDialog loading_dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_material_next;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("新增物料采购");
        ProApplication.getinstance().addActivity(MaterialNextActivity.this);
        loading_dialog = new LoadingDialog(this);
    }

    @Override
    protected void initData() {
        getUserInfo();
    }

    @Override
    protected void initListener() {
        mTvSubmit.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                if (!"".equals(mEdContent.getText().toString().trim())) {
                    nextMaterial(mEdContent.getText().toString().trim());
                } else {
                    ToastUtil.setToast("请输入采购原因");
                }
//                EditMaterialActivity.newIntent(getApplicationContext(), "2", "208");
                break;
        }
    }

    //获取当前用户信息
    private void getUserInfo() {
        RxHttpUtils.createApi(MaterialManagementApi.class)
                .getUserInfo()
                .compose(Transformer.<UserInfoBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<UserInfoBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(UserInfoBean goodsBean) {
                        if (goodsBean.getCode() == 200) {
                            setData(goodsBean.getData());
                        } else {
                            ToastUtil.setToast("个人信息获取失败");
                            finish();
                        }
                    }
                });
    }

    //部门 申请人 详情赋值
    private void setData(UserInfoBean.DataBean bean) {
        mTvDepartment.setText(bean.getDeptName());
        mTvApplicant.setText(bean.getUsername());
    }

    //添加商品
    private void nextMaterial(String remark) {
        String sign = MD5Util.encode("remark=" + remark);
        RxHttpUtils.createApi(MaterialManagementApi.class)
                .addMaterial(remark, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if (baseABean.getCode() == 200) {
                            ToastUtil.setToast("添加成功");
                            EditMaterialActivity.newIntent(getApplicationContext(), "1", baseABean.getData());
                            finish();
                        } else {
                            ToastUtil.setToast("添加失败");
                        }
                    }
                });
    }
}