package com.example.bq.edmp.work.library.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.word.inventory.bean.SxPzBean;
import com.example.bq.edmp.work.baozhuang.BzBean;
import com.example.bq.edmp.work.baozhuang.adapter.BzAdapter;
import com.example.bq.edmp.work.finishedproduct.activity.FinishedWarehousingOutDetailActivity;
import com.example.bq.edmp.work.library.adapter.ChuLibraryListAdapter;
import com.example.bq.edmp.work.library.api.LibraryApi;
import com.example.bq.edmp.work.library.bean.ChuLibraryBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/*
* 出库
* */
public class ClibraryActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{

    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.scan_img)
    ImageView scan_img;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.search_et)
    EditText search_et;
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
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.cz_tv)
    TextView cz_tv;
    @BindView(R.id.bz_rv)
    RecyclerView bz_rv;
    @BindView(R.id.bz_wsj)
    TextView bz_wsj;


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
    private ArrayList<ChuLibraryBean.DataBean.RowsBean> rowsBeans;
    private ChuLibraryListAdapter chulibraryListAdapter;
    private ArrayList<CompanyBean.DataBean> companyDataBeans;
    private BaseCom_Ck_Adapter baseCom_ck_adapter;
    private ArrayList<SxPzBean.DataBean> dataBeans;
    private List<SxPzBean.DataBean> data;
    private PzAdapter pzAdapter;
    private ArrayList<BzBean.DataBean> bzdataBeans;
    private List<BzBean.DataBean> bzdata;
    private BzAdapter bzAdapter;
    private boolean Fund = false;//判断返回顺序
    private TextView wsj_dial;
    private XRecyclerView buttom_rv;
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    private String TYPE = "2";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clibrary;
    }

    @Override
    protected void initView() {

        title_tv.setText("出库查询");

        //数据
        rowsBeans = new ArrayList<>();
        chulibraryListAdapter = new ChuLibraryListAdapter(rowsBeans,TYPE);
        xr.setAdapter(chulibraryListAdapter);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(ClibraryActivity.this));
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
        chulibraryListAdapter.setOnItemClickListener(new ChuLibraryListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, ChuLibraryBean.DataBean.RowsBean rowsBean) {
                FinishedWarehousingOutDetailActivity.newIntent(getApplicationContext(),rowsBean.getId());
            }
        });
        //筛选  品种适配器
        dataBeans = new ArrayList<>();
        pzAdapter = new PzAdapter(dataBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(ClibraryActivity.this, 3));
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

        //筛选  包装适配器
        bzdataBeans = new ArrayList<>();
        bzAdapter = new BzAdapter(bzdataBeans);
        bz_rv.setLayoutManager(new GridLayoutManager(ClibraryActivity.this, 2));
        bz_rv.setAdapter(bzAdapter);
        bzAdapter.setOnItemLeftClckListener(new BzAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(BzBean.DataBean dataBean, int mPosition) {
                //当点击时显示当前条目的背景和文字的颜色
                for (int i = 0; i < bzdataBeans.size(); i++) {
                    if (mPosition == i) {
                        bzdataBeans.get(i).setSelected(true);
                    } else {
                        bzdataBeans.get(i).setSelected(false);
                    }
                }
                bzAdapter.notifyDataSetChanged();
                if (!dataBean.getId().equals("") && dataBean.getId() != null) {
                    packagingId = dataBean.getId();
                } else {
                    packagingId = "";
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

        String sign = MD5Util.encode("code=" + code + "&orgId=" + orgIds + "&packagingId=" + packagingId + "&page=" + currentPager +
                "&pagerow=" + 15 + "&type2=" + type2 + "&varietyId=" + varietyId + "&warehouseId=" + warehouseId);

        RxHttpUtils.createApi(LibraryApi.class)
                .getClibraryData(code, orgIds, packagingId, currentPager, 15, type2, varietyId, warehouseId, sign)
                .compose(Transformer.<ChuLibraryBean>switchSchedulers())
                .subscribe(new NewCommonObserver<ChuLibraryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ChuLibraryBean chuLibraryBean) {
                        List<ChuLibraryBean.DataBean.RowsBean> rows = chuLibraryBean.getData().getRows();
                        if (rows != null && rows.size() != 0) {
                            xr.setVisibility(ViewGroup.VISIBLE);
                            wsj.setVisibility(ViewGroup.GONE);

                            rowsBeans.clear();
                            rowsBeans.addAll(rows);
                            chulibraryListAdapter.notifyDataSetChanged();
                        } else {
                            xr.setVisibility(ViewGroup.GONE);
                            wsj.setVisibility(ViewGroup.VISIBLE);

                            rowsBeans.clear();
                            chulibraryListAdapter.notifyDataSetChanged();
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("code=" + code + "&orgId=" + orgIds + "&packagingId=" + packagingId + "&page=" + currentPager +
                "&pagerow=" + 15 + "&type2=" + type2 + "&varietyId=" + varietyId + "&warehouseId=" + warehouseId);

        RxHttpUtils.createApi(LibraryApi.class)
                .getClibraryData(code, orgIds, packagingId, currentPager, 15, type2, varietyId, warehouseId, sign)
                .compose(Transformer.<ChuLibraryBean>switchSchedulers())
                .subscribe(new NewCommonObserver<ChuLibraryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ChuLibraryBean chuLibraryBean) {
                        List<ChuLibraryBean.DataBean.RowsBean> rows = chuLibraryBean.getData().getRows();
                        if (rows != null && rows.size() != 0) {
                            rowsBeans.addAll(rows);
                            chulibraryListAdapter.addMoreData(rows);
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
        fen_company_rl.setOnClickListener(this);
        ck_rl.setOnClickListener(this);
        cz_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
        scan_img.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                if (Fund) {
                    orgIds = "";//公司id
                    varietyId = "";//品种id
                    packagingId = "";//包装id
                    warehouseId = "";//仓库id
                    code = "";//单号
                    type2 = "";//入库类型
                    Fund = false;
                    gainData();
                } else {
                    fund();
                }
                break;
            case R.id.scan_img://扫描
                requestCodeQRCodePermissions();
                break;
            case R.id.screen_img://筛选
                sxMethodData();
                break;
            case R.id.cz_tv://筛选 重置
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
                sxMethodData();
                break;
            case R.id.affirm_tv://筛选   确定
                Fund = true;
                gainData();
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
                break;
            case R.id.fen_company_rl://筛选   分公司
                type = 1;
                findContentViews2(view);
                break;
            case R.id.ck_rl://筛选   仓库
                if (fen_company_tv.getText().toString().equals("") || fen_company_tv.getText().toString().equals("所有分子公司")) {
                    ToastUtil.setToast("请先选择所公司");
                    break;
                }
                type = 2;
                findContentViews2(view);
                break;


        }
    }

    /*
     * 分公司与仓库
     * */
    private void findContentViews2(View view) {
        final Dialog mCameraDialog = new Dialog(view.getContext(), R.style.my_dialog);

        View root = LayoutInflater.from(view.getContext()).inflate(R.layout.inventory_sx_buttom_item, null);

        mCameraDialog.setContentView(root);
        mCameraDialog.setCanceledOnTouchOutside(false);

        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.BottomDialog_Animation); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//      lp.alpha = 9f; // 透明度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();

        buttom_rv = root.findViewById(R.id.buttom_rv);
        TextView no_tv = root.findViewById(R.id.no_tv);
        TextView yes_tv = root.findViewById(R.id.yes_tv);
        wsj_dial = root.findViewById(R.id.wsj);

        no_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {//分公司
                    virtual_orgIds = "";
                    org_name = "";
                } else if (type == 2) {//仓库
                    virtual_warehouseId = "";
                    warehouse_name = "";
                }
                mCameraDialog.dismiss();
            }
        });

        yes_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {//分公司
                    orgIds = virtual_orgIds;
                    fen_company_tv.setText(org_name);
                } else if (type == 2) {//仓库
                    warehouseId = virtual_warehouseId;
                    ck_tv.setText(warehouse_name);
                }
                mCameraDialog.dismiss();
            }
        });

        //筛选  分公司，仓库适配器
        companyDataBeans = new ArrayList<>();
        baseCom_ck_adapter = new BaseCom_Ck_Adapter(companyDataBeans);
        buttom_rv.setLayoutManager(new LinearLayoutManager(ClibraryActivity.this));
        buttom_rv.setAdapter(baseCom_ck_adapter);
        baseCom_ck_adapter.setOnItemLeftClckListener(new BaseCom_Ck_Adapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(CompanyBean.DataBean dataBean, int mPosition) {
                //当点击时显示当前条目的背景和文字的颜色
                for (int i = 0; i < companyDataBeans.size(); i++) {
                    if (mPosition == i) {
                        companyDataBeans.get(i).setSelected(true);
                    } else {
                        companyDataBeans.get(i).setSelected(false);
                    }
                }
                baseCom_ck_adapter.notifyDataSetChanged();

                if (type == 1) {//分公司
                    virtual_orgIds = dataBean.getId();
                    org_name = dataBean.getName();
                } else if (type == 2) {//仓库
                    virtual_warehouseId = dataBean.getId();
                    warehouse_name = dataBean.getName();
                }

            }
        });

        btuomMethod();
    }

    private void btuomMethod() {
        if (type == 1) {
            RxHttpUtils.createApi(InventoryListApi.class)
                    .getCompanyData()
                    .compose(Transformer.<CompanyBean>switchSchedulers())
                    .subscribe(new NewCommonObserver<CompanyBean>() {
                        @Override
                        protected void onError(String errorMsg) {
                            ToastUtil.setToast(errorMsg);
                        }

                        @Override
                        protected void onSuccess(CompanyBean companyBean) {
                            List<CompanyBean.DataBean> data = companyBean.getData();
                            if (data != null && data.size() != 0) {
                                buttom_rv.setVisibility(ViewGroup.VISIBLE);
                                wsj_dial.setVisibility(ViewGroup.GONE);

                                companyDataBeans.clear();
                                data.get(0).setSelected(true);
                                virtual_orgIds = data.get(0).getId();
                                org_name = data.get(0).getName();
                                companyDataBeans.addAll(data);
                                baseCom_ck_adapter.notifyDataSetChanged();
                            } else {
                                buttom_rv.setVisibility(ViewGroup.GONE);
                                wsj_dial.setVisibility(ViewGroup.VISIBLE);
                                ToastUtil.setToast("暂无数据");
                            }
                        }
                    });
        } else {
            RxHttpUtils.createApi(InventoryListApi.class)
                    .getCkData()
                    .compose(Transformer.<CompanyBean>switchSchedulers())
                    .subscribe(new NewCommonObserver<CompanyBean>() {
                        @Override
                        protected void onError(String errorMsg) {
                            ToastUtil.setToast(errorMsg);
                        }

                        @Override
                        protected void onSuccess(CompanyBean companyBean) {
                            List<CompanyBean.DataBean> data = companyBean.getData();
                            if (data != null && data.size() != 0) {
                                buttom_rv.setVisibility(ViewGroup.VISIBLE);
                                wsj_dial.setVisibility(ViewGroup.GONE);

                                companyDataBeans.clear();
                                data.get(0).setSelected(true);
                                virtual_warehouseId = data.get(0).getId();
                                warehouse_name = data.get(0).getName();
                                companyDataBeans.addAll(data);
                                baseCom_ck_adapter.notifyDataSetChanged();
                            } else {
                                buttom_rv.setVisibility(ViewGroup.GONE);
                                wsj_dial.setVisibility(ViewGroup.VISIBLE);
                                ToastUtil.setToast("暂无数据");
                            }
                        }
                    });
        }
    }

    //品种数据
    private void sxMethodData() {
        RxHttpUtils.createApi(InventoryListApi.class)
                .getPzData()
                .compose(Transformer.<SxPzBean>switchSchedulers())
                .subscribe(new NewCommonObserver<SxPzBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SxPzBean sxPzBean) {
                        data = sxPzBean.getData();
                        if (data.size() != 0 && data != null) {
                            recyclerView.setVisibility(ViewGroup.VISIBLE);
                            shaixuan_wsj.setVisibility(ViewGroup.GONE);

                            dataBeans.clear();
                            SxPzBean.DataBean dataBean = new SxPzBean.DataBean();
                            dataBean.setSelected(true);
                            dataBean.setId("");
                            dataBean.setName("全部");
                            dataBeans.add(dataBean);
                            dataBeans.addAll(data);
                            pzAdapter.notifyDataSetChanged();
                        } else {
                            recyclerView.setVisibility(ViewGroup.GONE);
                            shaixuan_wsj.setVisibility(ViewGroup.VISIBLE);
                        }
                    }
                });
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.openDrawer(Gravity.RIGHT);
        BzMethodData();
    }

    //包装数据
    private void BzMethodData() {
        RxHttpUtils.createApi(InventoryListApi.class)
                .getBzIdData()
                .compose(Transformer.<BzBean>switchSchedulers())
                .subscribe(new NewCommonObserver<BzBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BzBean bzBean) {
                        bzdata = bzBean.getData();
                        if (bzdata.size() != 0 && bzdata != null) {
                            bz_rv.setVisibility(ViewGroup.VISIBLE);
                            bz_wsj.setVisibility(ViewGroup.GONE);

                            bzdataBeans.clear();
                            BzBean.DataBean dataBean = new BzBean.DataBean();
                            dataBean.setSelected(true);
                            dataBean.setId("");
                            dataBean.setVarietyPackagingName("全部");
                            bzdataBeans.add(dataBean);
                            bzdataBeans.addAll(bzdata);
                            bzAdapter.notifyDataSetChanged();
                        } else {
                            bz_rv.setVisibility(ViewGroup.GONE);
                            bz_wsj.setVisibility(ViewGroup.VISIBLE);
                        }
                    }
                });
    }

    //手机返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                //当菜单栏是可见的，则关闭
                drawerLayout.closeDrawer(linterHistoryConfirm);
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
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
            Intent intent = new Intent(ClibraryActivity.this, LibraryListScanActivity.class);
            intent.putExtra("title", "出库查询");
            startActivityForResult(intent, 250);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250 && resultCode == 550) {
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