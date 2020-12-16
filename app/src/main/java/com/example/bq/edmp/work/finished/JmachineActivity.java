package com.example.bq.edmp.work.finished;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.finished.adapter.JmachineListAdapter;
import com.example.bq.edmp.work.finished.api.MachineApi;
import com.example.bq.edmp.work.finished.bean.MachineListBean;
import com.example.bq.edmp.work.finishedproduct.activity.MachiningTaskDetailsActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/*
 * 加工记录    加工中
 * */
public class JmachineActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{

    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.screen_img)
    ImageView screen_img;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.xr)
    XRecyclerView xr;

    private int currentPager = 1;
    private String code = "";
    private boolean Fund = false;
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;

    private ArrayList<MachineListBean.DataBean.RowsBean> rowsBeans;
    private JmachineListAdapter jachineListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_machine;
    }

    @Override
    protected void initView() {
        title_tv.setText("加工中");

        //数据
        rowsBeans = new ArrayList<>();
        jachineListAdapter = new JmachineListAdapter(rowsBeans);
        xr.setAdapter(jachineListAdapter);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(JmachineActivity.this));
        xr.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                initData();
                xr.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                initData2();
                xr.loadMoreComplete();
            }
        });

        jachineListAdapter.setOnItemClickListener(new JmachineListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, MachineListBean.DataBean.RowsBean rowsBean) {
                MachiningTaskDetailsActivity.newIntent(getApplicationContext(),rowsBean.getCode(),rowsBean.getPackagingId());
            }
        });

        //搜索
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //此处做逻辑处理
                    Fund = true;
                    code = textView.getText().toString();
                    hideKeyboard(search_et);
                    gainData();
                    return true;
                }
                return false;

            }
        });
    }


    @Override
    protected void initData() {
        gainData();
    }

    private void gainData() {
        currentPager = 1;

        String sign = MD5Util.encode("code=" + code + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(MachineApi.class)
                .getJmachineData(code, currentPager, 15, sign)
                .compose(Transformer.<MachineListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<MachineListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(MachineListBean machineListBean) {
                        if (machineListBean.getCode().equals("200")) {
                            wsj.setVisibility(ViewGroup.GONE);
                            xr.setVisibility(ViewGroup.VISIBLE);
                            List<MachineListBean.DataBean.RowsBean> rows = machineListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                jachineListAdapter.notifyDataSetChanged();
                            }else {
                                wsj.setVisibility(ViewGroup.VISIBLE);
                                xr.setVisibility(ViewGroup.GONE);

                                rowsBeans.clear();
                                jachineListAdapter.notifyDataSetChanged();

                                ToastUtil.setToast("暂无数据");
                            }
                        } else {
                            wsj.setVisibility(ViewGroup.VISIBLE);
                            xr.setVisibility(ViewGroup.GONE);

                            rowsBeans.clear();
                            jachineListAdapter.notifyDataSetChanged();

                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("code=" + code + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(MachineApi.class)
                .getJmachineData(code, currentPager, 15, sign)
                .compose(Transformer.<MachineListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<MachineListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(MachineListBean machineListBean) {
                        if (machineListBean.getCode().equals("200")) {
                            List<MachineListBean.DataBean.RowsBean> rows = machineListBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.addAll(rows);
                                jachineListAdapter.addMoreData(rows);
                            }
                        } else {
                            xr.setNoMore(true);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        screen_img.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                if (Fund) {
                    Fund = false;
                    code = "";
                    search_et.setText("");
                    search_et.setHint("请输入加工单号查找");
                    gainData();
                }else {
                    fund();
                }
                break;
            case R.id.screen_img:
                requestCodeQRCodePermissions();
                break;
        }

    }

    /**
     * 隐藏软键盘
     *
     * @param context :上下文环境，一般为Activity实例
     * @param view    :一般为EditText
     */
    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        } else {
            Intent intent = new Intent(JmachineActivity.this, MachineScanActivity.class);
            intent.putExtra("title", "加工中");
            startActivityForResult(intent, 250);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250 && resultCode == 450) {
            String result = data.getStringExtra("result");
            code = result;
            search_et.setText(result);
//            if (result != null && !result.equals("")) {
//                search_et.setSelection(result.length());//将光标移至文字末尾
//            }
            Fund = true;
            gainData();
        }
    }

}