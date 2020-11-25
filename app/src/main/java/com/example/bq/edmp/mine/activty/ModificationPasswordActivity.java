package com.example.bq.edmp.mine.activty;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.mine.api.MineApi;
import com.example.bq.edmp.mine.bean.StateBean;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.SpUtils;
import com.example.bq.edmp.utils.StringUtils;
import com.example.bq.edmp.utils.ToastUtil;

import butterknife.BindView;

public class ModificationPasswordActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.affirm_img)
    ImageView affirm_img;
    @BindView(R.id.phone_tv)
    TextView phone_tv;
    @BindView(R.id.jiu_password_et)
    EditText jiu_password_et;
    @BindView(R.id.new_password_et)
    EditText new_password_et;
    @BindView(R.id.queren_new_password_et)
    EditText queren_new_password_et;

    private LoadingDialog loading_dialog;


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        affirm_img.setOnClickListener(this);
        return_img.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        title_tv.setText("修改密码");
        String userPhone = (String) SpUtils.get("UserPhone", "");
        phone_tv.setText(userPhone);

        loading_dialog = new LoadingDialog(this);


        jiu_password_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = jiu_password_et.getText();
                int len = editable.length();
                if (len > 18) {
                    ToastUtil.setToast("密码长度最大为18");
                    int selEndIndex = Selection.getSelectionEnd(editable);
                    String str = editable.toString();
                    //截取新字符串
                    String newStr = str.substring(0, 18);
                    jiu_password_et.setText(newStr);
                    editable = jiu_password_et.getText();

                    //新字符串的长度
                    int newLen = editable.length();
                    //旧光标位置超过字符串长度
                    if (selEndIndex > newLen) {
                        selEndIndex = editable.length();
                    }
                    //设置新光标所在的位置
                    Selection.setSelection(editable, selEndIndex);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        new_password_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = new_password_et.getText();
                int len = editable.length();
                if (len > 18) {
                    ToastUtil.setToast("密码长度最大为18");
                    int selEndIndex = Selection.getSelectionEnd(editable);
                    String str = editable.toString();
                    //截取新字符串
                    String newStr = str.substring(0, 18);
                    new_password_et.setText(newStr);
                    editable = new_password_et.getText();

                    //新字符串的长度
                    int newLen = editable.length();
                    //旧光标位置超过字符串长度
                    if (selEndIndex > newLen) {
                        selEndIndex = editable.length();
                    }
                    //设置新光标所在的位置
                    Selection.setSelection(editable, selEndIndex);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        queren_new_password_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = queren_new_password_et.getText();
                int len = editable.length();
                if (len > 18) {
                    ToastUtil.setToast("密码长度最大为18");
                    int selEndIndex = Selection.getSelectionEnd(editable);
                    String str = editable.toString();
                    //截取新字符串
                    String newStr = str.substring(0, 18);
                    queren_new_password_et.setText(newStr);
                    editable = queren_new_password_et.getText();

                    //新字符串的长度
                    int newLen = editable.length();
                    //旧光标位置超过字符串长度
                    if (selEndIndex > newLen) {
                        selEndIndex = editable.length();
                    }
                    //设置新光标所在的位置
                    Selection.setSelection(editable, selEndIndex);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modification_password;
    }

    @Override
    protected void otherViewClick(View view) {
        String jiu_password = jiu_password_et.getText().toString();
        String new_password = new_password_et.getText().toString();
        String queren_new_password = queren_new_password_et.getText().toString();
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.affirm_img:
                if (jiu_password.isEmpty()) {
                    jiu_password_et.setError("请输入旧密码");
                    ToastUtil.setToast("请输入旧密码");
                    break;
                } else if (!StringUtils.compileExChar(jiu_password)) {
                    jiu_password_et.setError("请输入正确的旧密码");
                    ToastUtil.setToast("请输入正确的旧密码");
                    break;
                }
                if (new_password.isEmpty()) {
                    new_password_et.setError("请输入新密码");
                    ToastUtil.setToast("请输入新密码");
                    break;
                } else if (!StringUtils.compileExChar(new_password)) {
                    new_password_et.setError("请输入正确的密码");
                    ToastUtil.setToast("请输入正确的密码");
                    break;
                }
                if (queren_new_password.isEmpty()) {
                    queren_new_password_et.setError("请确认密码");
                    ToastUtil.setToast("请确认密码");
                    break;
                } else if (!StringUtils.compileExChar(queren_new_password)) {
                    queren_new_password_et.setError("请输入正确的密码");
                    ToastUtil.setToast("请输入正确的密码");
                    break;
                } else if (!new_password.equals(queren_new_password)) {
                    queren_new_password_et.setError("");
                    ToastUtil.setToast("密码不一致，请重新输入");
                    break;
                }

                gainModificationPassword(jiu_password, queren_new_password);
                break;
        }
    }

    private void gainModificationPassword(String jiu_password, String queren_new_password) {
        String sign = MD5Util.encode("newPassword=" + queren_new_password + "&oldPassword=" + jiu_password);

        RxHttpUtils.createApi(MineApi.class)
                .modificationPassword(queren_new_password, jiu_password, sign)
                .compose(Transformer.<StateBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<StateBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(StateBean stateBean) {
                        if (stateBean.getCode().equals("200")) {
                            fund();
                        } else {
                            ToastUtil.setToast(stateBean.getMsg());
                            jiu_password_et.setText("");
                            new_password_et.setText("");
                            queren_new_password_et.setText("");
                        }
                    }
                });
    }
}
