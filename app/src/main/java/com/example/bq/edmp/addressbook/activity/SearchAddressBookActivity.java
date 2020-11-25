package com.example.bq.edmp.addressbook.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.R;
import com.example.bq.edmp.addressbook.adapter.AddressBookSearchAdapter;
import com.example.bq.edmp.addressbook.api.AddressBookApi;
import com.example.bq.edmp.addressbook.bean.AddressBookSearchBean;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchAddressBookActivity extends BaseActivity {

    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.cancel_tv)
    TextView cancel_tv;
    @BindView(R.id.one_tv)
    TextView one_tv;
    @BindView(R.id.two_tv)
    TextView two_tv;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.search_rv)
    RecyclerView search_rv;
    @BindView(R.id.return_img)
    ImageView return_img;
    private List<AddressBookSearchBean.DataBean> dataBeans;
    private AddressBookSearchAdapter addressBookSearchAdapter;


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        cancel_tv.setOnClickListener(this);
        return_img.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        String officetext = getIntent().getStringExtra("officetext");
        if (!officetext.equals("") && officetext != null) {
            one_tv.setText(officetext);
        }

        dataBeans = new ArrayList<>();
        addressBookSearchAdapter = new AddressBookSearchAdapter(dataBeans);
        search_rv.setLayoutManager(new LinearLayoutManager(SearchAddressBookActivity.this));
        search_rv.setAdapter(addressBookSearchAdapter);
        addressBookSearchAdapter.setOnItemLeftClckListener(new AddressBookSearchAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(AddressBookSearchBean.DataBean dataBean, int mPosition) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + dataBean.getMobTel());
                intent.setData(data);
                startActivity(intent);
            }
        });


        search_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                if (null != s1 && !s1.equals("")) {
                    initGainData(s.toString());
                }
            }
        });

        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //此处做逻辑处理
                    String search_titles = search_et.getText().toString();
                    if (search_titles != null && !search_titles.equals("")) {
                        initGainData(search_titles);
                    } else {
                        ToastUtil.setToast("请输入搜索内容！");
                    }
                    return true;
                }
                return false;
            }
        });


    }

    private void initGainData(String search_titles) {

        //去除空格
        String search_title = search_titles.replaceAll(" ", "");
        String sign = MD5Util.encode("name=" + search_title);
        RxHttpUtils.createApi(AddressBookApi.class)
                .searchaddressbook(search_title, sign)
                .compose(Transformer.<AddressBookSearchBean>switchSchedulers())
                .subscribe(new CommonObserver<AddressBookSearchBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AddressBookSearchBean addressBookSearchBean) {
                        if (addressBookSearchBean.getCode().equals("200")) {
                            wsj.setVisibility(ViewGroup.GONE);
                            search_rv.setVisibility(ViewGroup.VISIBLE);

                            dataBeans.clear();
                            dataBeans.addAll(addressBookSearchBean.getData());
                            addressBookSearchAdapter.notifyDataSetChanged();

                        } else {
                            wsj.setVisibility(ViewGroup.VISIBLE);
                            search_rv.setVisibility(ViewGroup.GONE);
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_address_book;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_tv:
                search_et.setText("");
                dataBeans.clear();
                addressBookSearchAdapter.notifyDataSetChanged();
                break;
            case R.id.return_img:
                fund();
                break;
        }
    }
}
