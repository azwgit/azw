package com.example.bq.edmp.login;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.utils.SpUtils;
import com.example.bq.edmp.utils.ToastUtil;

import butterknife.BindView;

public class GestureoneActivity extends BaseActivity {

    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.text_one)
    TextView text_one;
    @BindView(R.id.text_two)
    TextView text_two;
    @BindView(R.id.switch_sw)
    Switch switch_sw;
    @BindView(R.id.stting_login)
    RelativeLayout stting_login;
    @BindView(R.id.ll)
    LinearLayout ll;
    private int type;//1 刷脸  2  手势


    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        stting_login.setOnClickListener(this);
        switch_sw.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        type = getIntent().getIntExtra("type", 0);
        title_tv.setText(getIntent().getStringExtra("text_title"));
        text_one.setText(getIntent().getStringExtra("text_one"));
        text_two.setText(getIntent().getStringExtra("text_two"));
        Boolean shualian = (Boolean) SpUtils.get("shualian", false);
        Boolean shoushi = (Boolean) SpUtils.get("shoushi", false);

        if (type == 1) {
            if (shualian) {
                switch_sw.setChecked(true);
                ll.setVisibility(ViewGroup.VISIBLE);
            } else {
                ll.setVisibility(ViewGroup.GONE);
            }
        } else {
            if (shoushi) {
                switch_sw.setChecked(true);
                ll.setVisibility(ViewGroup.VISIBLE);
            } else {
                ll.setVisibility(ViewGroup.GONE);
            }
        }

        switch_sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (type == 1) {//刷脸
                        SpUtils.put("shualian", true);
                        SpUtils.put("shoushi", false);
                        SpUtils.put("SHOUSHILOGIN", "");
                    } else {//手势
                        String shoushilogin = (String) SpUtils.get("SHOUSHILOGIN", "");
                        if (shoushilogin.equals("") || shoushilogin == null) {
                            switch_sw.setChecked(false);
                            Intent intent = new Intent(GestureoneActivity.this, GestureSettingLoginActivity.class);
                            startActivityForResult(intent, 250);
                        } else {
                            SpUtils.put("shoushi", true);
                            SpUtils.put("shualian", false);
                        }
                    }
                    ll.setVisibility(ViewGroup.VISIBLE);
                } else {
                    SpUtils.put("shualian", false);
                    SpUtils.put("shoushi", false);
                    ll.setVisibility(ViewGroup.GONE);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gestureone;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.stting_login:
                if (type == 1) {//刷脸
                    ToastUtil.setToast("刷脸登录修改未开通");
                } else {//手势
                    Intent intent = new Intent(GestureoneActivity.this, GestureSettingLoginActivity.class);
                    startActivityForResult(intent, 250);
                }
                break;
        }
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250 && resultCode == 100) {
            boolean ss = data.getBooleanExtra("ss", false);
            if (ss) {
                switch_sw.setChecked(true);
                ll.setVisibility(ViewGroup.VISIBLE);
                SpUtils.put("shoushi", true);
                SpUtils.put("shualian", false);
            } else {
                switch_sw.setChecked(false);
                ll.setVisibility(ViewGroup.GONE);
                SpUtils.put("shoushi", false);
                SpUtils.put("shualian", false);
            }
        }
    }
}