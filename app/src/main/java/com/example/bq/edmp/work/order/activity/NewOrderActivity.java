package com.example.bq.edmp.work.order.activity;

import android.content.Intent;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.FromtUtil;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.StringUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.order.api.OrderApi;
import com.example.bq.edmp.work.order.bean.CustomerBean;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.http.Field;

/*
 * 新增订单
 * */
public class NewOrderActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.username_tv)
    TextView username_tv;
    @BindView(R.id.choose_user_tv)
    TextView choose_user_tv;
    @BindView(R.id.next_tv)
    TextView next_tv;
    @BindView(R.id.contactname_et)
    EditText contactname_et;
    @BindView(R.id.contact_phone_et)
    EditText contact_phone_et;
    @BindView(R.id.cargo_address_et)
    EditText cargo_address_et;

    private LoadingDialog loadingDialog;
    private String customerId = "";//客户id
    private String regionId = "";//区域id

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_order;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(NewOrderActivity.this);
        loadingDialog = new LoadingDialog(this);
        title_tv.setText("新增订单");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        next_tv.setOnClickListener(this);
        choose_user_tv.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.next_tv:
                if (Judge()) {
                    NextData();
                }
                break;
            case R.id.choose_user_tv://选择客户
                Intent intent = new Intent(NewOrderActivity.this, ChooseCustomerActivity.class);
                startActivityForResult(intent, 250);
                break;
        }
    }

    //下一步
    private void NextData() {

        String sign = MD5Util.encode("address=" + cargo_address_et.getText().toString() + "&contacts=" + contactname_et.getText().toString()
                + "&customerId=" + customerId + "&mobTel=" + contact_phone_et.getText().toString() + "&region=" + regionId);

        RxHttpUtils.createApi(OrderApi.class)
                .getNewsave(cargo_address_et.getText().toString(), contactname_et.getText().toString(), customerId, contact_phone_et.getText().toString(), regionId, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loadingDialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if (baseABean.getCode() == 200) {
                            Intent intent = new Intent(NewOrderActivity.this, ModifyOrderActivity.class);
                            intent.putExtra("orderid", baseABean.getData());
                            startActivity(intent);
                            finish();
                        } else {
                            ToastUtil.setToast("提交失败，请重新提交");
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(NewOrderActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250 && resultCode == 350) {
            CustomerBean.DataBean.RowsBean rowsbean = (CustomerBean.DataBean.RowsBean) data.getSerializableExtra("rowsbean");
            if (rowsbean != null) {
                username_tv.setText(rowsbean.getName());
                contactname_et.setText(rowsbean.getContacts());
                FromtUtil.setEditTextCursorLocation(contactname_et);
                contact_phone_et.setText(rowsbean.getMobTel());
                FromtUtil.setEditTextCursorLocation(contact_phone_et);
                cargo_address_et.setText(rowsbean.getRegion());
                FromtUtil.setEditTextCursorLocation(cargo_address_et);
                customerId = rowsbean.getId();//客户id
                regionId = rowsbean.getRegionId();//区域id
            }
        }
    }

    public boolean Judge() {
        if (username_tv.getText().toString().equals("")) {
            ToastUtil.setToast("请选择客户");
            return false;
        }
        if (contactname_et.getText().toString().equals("")) {
            ToastUtil.setToast("请输入联系人");
            return false;
        }
        if (contact_phone_et.getText().toString().equals("")) {
            ToastUtil.setToast("请输入联系人电话号");
            return false;
        }
//        } else if (!StringUtils.isPhone(contact_phone_et.toString())) {
//            ToastUtil.setToast("请输入正确电话号");
//            return false;
//        }
        if (cargo_address_et.getText().toString().equals("")) {
            ToastUtil.setToast("请输入送货地址");
            return false;
        }
        return true;
    }
}
