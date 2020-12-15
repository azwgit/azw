package com.example.bq.edmp.work.library.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.inventory.adapter.BaseCom_Ck_Adapter;
import com.example.bq.edmp.word.inventory.adapter.PzAdapter;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.work.library.adapter.CxLibraryListAdapter;
import com.example.bq.edmp.work.library.api.LibraryApi;
import com.example.bq.edmp.work.library.bean.CxLibraryBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/*
 * 库存查询
 * */
public class CxlibraryActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.screen_img)
    ImageView screen_img;
    @BindView(R.id.xr)
    XRecyclerView xr;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fen_company_rl)
    RelativeLayout fen_company_rl;
    @BindView(R.id.fen_company_tv)
    TextView fen_company_tv;
    @BindView(R.id.ck_rl)
    RelativeLayout ck_rl;
    @BindView(R.id.ck_tv)
    TextView ck_tv;
    @BindView(R.id.shai_rv)
    RecyclerView recyclerView;
    @BindView(R.id.shaixuan_wsj)
    TextView shaixuan_wsj;
    @BindView(R.id.start_time_tv)
    TextView start_time_tv;
    @BindView(R.id.end_time_tv)
    TextView end_time_tv;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.cz_tv)
    TextView cz_tv;

    private int currentPager = 1;
    private String orgIds = "";//公司id
    private String org_name = "";//公司名字
    private String virtual_orgIds = "";//公司id
    private String varietyId = "";//品种id
    private String packagingId = "";//包装id
    private String warehouseId = "";//仓库id
    private String warehouse_name = "";//仓库名字
    private String virtual_warehouseId = "";//仓库id
    private String beginTime = "";//开始时间
    private String endTime = "";//结束时间
    private String code = "";//单号
    private String type2 = "";//入库类型
    private int type = 1;
    private ArrayList<CxLibraryBean.DataBean.RowsBean> rowsBeans;
    private CxLibraryListAdapter cxLibraryListAdapter;
    private ArrayList<CompanyBean.DataBean> companyDataBeans;
    private BaseCom_Ck_Adapter baseCom_ck_adapter;
    private ArrayList<SxPzBean.DataBean> dataBeans;
    private List<SxPzBean.DataBean> data;
    private PzAdapter pzAdapter;
    private boolean Fund = false;//判断返回顺序
    private TextView wsj_dial;
    private XRecyclerView buttom_rv;
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_cxlibrary;
    }

    @Override
    protected void initView() {

        title_tv.setText("库存查询");

        //数据
        rowsBeans = new ArrayList<>();
        cxLibraryListAdapter = new CxLibraryListAdapter(rowsBeans);
        xr.setAdapter(cxLibraryListAdapter);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(CxlibraryActivity.this));
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
        cxLibraryListAdapter.setOnItemClickListener(new CxLibraryListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, CxLibraryBean.DataBean.RowsBean rowsBean) {
                ToastUtil.setToast(rowsBean.getOrgName());
            }
        });
        //筛选  品种适配器
        dataBeans = new ArrayList<>();
        pzAdapter = new PzAdapter(dataBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(CxlibraryActivity.this, 3));
        recyclerView.setAdapter(pzAdapter);
        pzAdapter.setOnItemLeftClckListener(new PzAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(SxPzBean.DataBean dataBean, int mPosition) {
                //当点击时显示当前条目的背景和文字的颜色
                for (int i = 0; i < dataBeans.size(); i++) {
                    if (mPosition == i) {
                        dataBeans.get(i).setSelected(true);
                    } else {
                        dataBeans.get(i).setSelected(false);
                    }
                }
                pzAdapter.notifyDataSetChanged();
                if (!dataBean.getId().equals("") && dataBean.getId() != null) {
                    varietyId = dataBean.getId();
                } else {
                    varietyId = "";
                }
            }
        });
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                start_time_tv.setHint("请选择开始时间");
                start_time_tv.setText("");
                end_time_tv.setHint("请选择结束时间");
                end_time_tv.setText("");
                fen_company_tv.setHint("所有分子公司");
                fen_company_tv.setText("");
                ck_tv.setHint("所有仓库");
                ck_tv.setText("");
                virtual_orgIds = "";
                orgIds = "";
                org_name = "";
                varietyId = "";
                virtual_orgIds = "";
                virtual_warehouseId = "";
                warehouse_name = "";
                warehouseId = "";
                beginTime = "";
                endTime = "";
                code = "";
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }

    @Override
    protected void initData() {
        gainData();
    }

    private void gainData() {
        currentPager = 1;


        String sign = MD5Util.encode("orgId=" + orgIds + "&packagingId=" + packagingId + "&page=" + currentPager +
                "&pagerow=" + 15 + "&varietyId=" + varietyId + "&warehouseId=" + warehouseId);

        RxHttpUtils.createApi(LibraryApi.class)
                .getCxlibraryData(orgIds, packagingId, currentPager, 15, varietyId, warehouseId, sign)
                .compose(Transformer.<CxLibraryBean>switchSchedulers())
                .subscribe(new NewCommonObserver<CxLibraryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CxLibraryBean cxLibraryBean) {
                        List<CxLibraryBean.DataBean.RowsBean> rows = cxLibraryBean.getData().getRows();
                        if (rows != null && rows.size() != 0) {
                            xr.setVisibility(ViewGroup.VISIBLE);
                            wsj.setVisibility(ViewGroup.GONE);

                            rowsBeans.clear();
                            rowsBeans.addAll(rows);
                            cxLibraryListAdapter.notifyDataSetChanged();
                        } else {
                            xr.setVisibility(ViewGroup.GONE);
                            wsj.setVisibility(ViewGroup.VISIBLE);

                            rowsBeans.clear();
                            cxLibraryListAdapter.notifyDataSetChanged();
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("orgId=" + orgIds + "&packagingId=" + packagingId + "&page=" + currentPager +
                "&pagerow=" + 15 + "&varietyId=" + varietyId + "&warehouseId=" + warehouseId);

        RxHttpUtils.createApi(LibraryApi.class)
                .getCxlibraryData(orgIds, packagingId, currentPager, 15, varietyId, warehouseId, sign)
                .compose(Transformer.<CxLibraryBean>switchSchedulers())
                .subscribe(new NewCommonObserver<CxLibraryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CxLibraryBean cxLibraryBean) {
                        List<CxLibraryBean.DataBean.RowsBean> rows = cxLibraryBean.getData().getRows();
                        if (rows != null && rows.size() != 0) {
                            rowsBeans.addAll(rows);
                            cxLibraryListAdapter.addMoreData(rows);
                        } else {
                            xr.setNoMore(true);
                        }
                    }
                });
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        screen_img.setOnClickListener(this);
        start_time_tv.setOnClickListener(this);
        end_time_tv.setOnClickListener(this);
        fen_company_rl.setOnClickListener(this);
        ck_rl.setOnClickListener(this);
        cz_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img://返回  按钮
                fund();
                break;
            case R.id.screen_img://筛选  按钮
                break;
            case R.id.start_time_tv://开始时间
                break;
            case R.id.end_time_tv://结束时间
                break;
            case R.id.fen_company_rl://所有分公司
                break;
            case R.id.ck_rl://所有仓库
                break;
            case R.id.cz_tv://重置
                break;
            case R.id.affirm_tv://筛选   确定
                break;
        }
    }


    /**
     * 隐藏软键盘
     *
     * @param :上下文环境，一般为Activity实例
     * @param view                 :一般为EditText
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
            code = "";
            orgIds = "";
            varietyId = "";
            Intent intent = new Intent(CxlibraryActivity.this, LibraryListScanActivity.class);
            intent.putExtra("title", "入库查询");
            startActivityForResult(intent, 250);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250 && resultCode == 450) {
            String result = data.getStringExtra("result");
            code = result;
//            if (result != null && !result.equals("")) {
//                search_et.setSelection(result.length());//将光标移至文字末尾
//            }
            Fund = true;
            gainData();
        }
    }
}