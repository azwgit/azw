package com.example.bq.edmp.mine.activty;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.mine.api.MineApi;
import com.example.bq.edmp.mine.bean.StateBean;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.StringUtils;
import com.example.bq.edmp.utils.ToastUtil;

import butterknife.BindView;

/*
*修改页面   手机号  座机  邮箱
* */
public class Amend_Activity extends BaseActivity {

    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.affirm_img)
    ImageView affirm_img;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.jiuphone_tv)
    TextView jiuphone_tv;
    @BindView(R.id.password_et)
    EditText password_et;
    @BindView(R.id.phone_et)
    EditText phone_et;
    @BindView(R.id.phone_tv)
    TextView phone_tv;
    @BindView(R.id.password_rl)
    RelativeLayout password_rl;

    private int TYPE = 0;//0 手机号 1 座机 2 邮箱
    private String NUMBER;
    private ILoadingView loading_dialog;


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        affirm_img.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        TYPE = getIntent().getIntExtra("type", 0);
        NUMBER = getIntent().getStringExtra("number");
        title_tv.setText("个人信息");
        jiuphone_tv.setText(NUMBER);
        if (TYPE == 1) {
            phone_tv.setText("新座机号");
            tv.setText("座机号");
            phone_et.setHint("请输入新座机号");
            password_rl.setVisibility(ViewGroup.GONE);
        } else if (TYPE == 2) {
            phone_tv.setText("新邮箱号");
            tv.setText("邮箱号");
            phone_et.setHint("请输入新邮箱号");
            password_rl.setVisibility(ViewGroup.GONE);
        }

        loading_dialog = new LoadingDialog(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_amend;
    }

    @Override
    protected void otherViewClick(View view) {
        String password = password_et.getText().toString();
        String phone = phone_et.getText().toString();
        switch (view.getId()) {
            case R.id.return_img://返回按键
                fund();
                break;
            case R.id.affirm_img://确认按钮
                if (TYPE == 0) {
                    if (password.isEmpty()) {
                        password_et.setError("请输入登录密码");
                        break;
                    }
                }
                if (phone.isEmpty()) {
                    if (TYPE == 0) {
                        phone_et.setError("请输入手机号");
                    }
                    if (TYPE == 1) {
                        phone_et.setError("请输入座机号");
                    } else {
                        phone_et.setError("请输入邮箱号");
                    }
                    break;
                } else {
                    if (TYPE == 0) {
                        if (!StringUtils.isPhone(phone)) {
                            phone_et.setError("请输入正确手机号");
                            break;
                        }
                    }
                    if (TYPE == 1) {
                        if (!StringUtils.IsTelephone(phone)) {
                            phone_et.setError("请输入正确座机号");
                            break;
                        }
                    }
                    if (TYPE == 2) {
                        if (!StringUtils.isEmail(phone)) {
                            phone_et.setError("请输入正确邮箱号");
                            break;
                        }
                    }
                }
                if (TYPE == 0) {
                    affirmPhone(phone, password);
                } else if (TYPE == 1) {
                    affirmSpecial(phone);
                } else {
                    affirmMail(phone);
                }
                break;
        }
    }

    //邮箱
    private void affirmMail(String phone) {
        String sign = MD5Util.encode("email=" + phone);

        RxHttpUtils.createApi(MineApi.class)
                .modificationMail(phone, sign)
                .compose(Transformer.<StateBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<StateBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(StateBean stateBean) {
                        if (stateBean.getCode().equals("200")) {
                            setResult(250);
                            fund();
                        } else {
                            ToastUtil.setToast("邮箱号修改失败，请重新提交");
                        }
                    }
                });
    }

    //座机
    private void affirmSpecial(String phone) {
        String sign = MD5Util.encode("officeTel=" + phone);

        RxHttpUtils.createApi(MineApi.class)
                .modificationSpecial(phone, sign)
                .compose(Transformer.<StateBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<StateBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(StateBean stateBean) {
                        if (stateBean.getCode().equals("200")) {
                            setResult(250);
                            fund();
                        } else {
                            ToastUtil.setToast("座机号修改失败，请重新提交");
                        }
                    }
                });

    }

    //手机
    private void affirmPhone(String phone, String password) {
        String sign = MD5Util.encode("mobTel=" + phone + "&password=" + password);

        RxHttpUtils.createApi(MineApi.class)
                .modificationPhone(phone, password, sign)
                .compose(Transformer.<StateBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<StateBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(StateBean stateBean) {
                        if (stateBean.getCode().equals("200")) {
                            setResult(250);
                            fund();
                        } else {
                            ToastUtil.setToast("手机号修改失败，请重新提交");
                        }
                    }
                });
    }
}
