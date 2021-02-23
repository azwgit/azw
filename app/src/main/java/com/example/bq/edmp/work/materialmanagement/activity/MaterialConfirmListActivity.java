package com.example.bq.edmp.work.materialmanagement.activity;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.goodsgrainmanagement.adapter.SelectSubsidiaryCompanyAdapter;
import com.example.bq.edmp.work.goodsgrainmanagement.api.GoodsSalesApi;
import com.example.bq.edmp.work.goodsgrainmanagement.bean.VarietiesBean;
import com.example.bq.edmp.work.inventorytransfer.bean.SubsidiaryCompanyBean;
import com.example.bq.edmp.work.materialmanagement.adapter.FragmentProcurementTrackingListAdapter;
import com.example.bq.edmp.work.materialmanagement.adapter.MaterialConfirmListAdapter;
import com.example.bq.edmp.work.materialmanagement.adapter.MaterialGoodsListAdapter;
import com.example.bq.edmp.work.materialmanagement.api.MaterialManagementApi;
import com.example.bq.edmp.work.materialmanagement.bean.ProcurementTrackingListFragmentBean;
import com.example.bq.edmp.work.materialmanagement.fragment.ProcurementTrackingListFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 退单跟踪
 * */
public class MaterialConfirmListActivity extends BaseActivity {
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    //筛选
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.shai_rv)
    RecyclerView recyclerView;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.chong_tv)
    TextView chong_tv;
    @BindView(R.id.chu_tv)
    TextView chu_tv;
    @BindView(R.id.chu_rl)
    RelativeLayout chu_rl;
    @BindView(R.id.screen_img)
    ImageView screen_img;
    private MaterialGoodsListAdapter pzAdapter;
    private ArrayList<VarietiesBean.DataBean> dataBeans;
    private List<VarietiesBean.DataBean> data;
    private String varietyId = "";//品种id
    private String companyId = "";//分公司id
    private int currentPager = 1;
    private XRecyclerView buttom_rv;
    private MaterialConfirmListAdapter goodsSalesConfirmListAdapter;
    private ArrayList<ProcurementTrackingListFragmentBean.DataBean.RowsBean> rowsBeans;
    private ProcurementTrackingListFragment goodsSalesTrackingListFragment;
    private ILoadingView loading_dialog;
    private int position = 0;
    private ArrayList<SubsidiaryCompanyBean.DataBean> companyDataBeans;
    private SelectSubsidiaryCompanyAdapter selectProvinceAdapter;//选择省适配器
    private TextView wsj_dial;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_material_history_list;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(MaterialConfirmListActivity.this);
        title_tv.setText("采购确认");
        loading_dialog = new LoadingDialog(this);
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                varietyId = "";
                companyId = "";
                chu_tv.setText("所有分子公司");
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        //筛选  品种适配器
        dataBeans = new ArrayList<>();
        pzAdapter = new MaterialGoodsListAdapter(dataBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(MaterialConfirmListActivity.this, 3));
        recyclerView.setAdapter(pzAdapter);
        pzAdapter.setOnItemLeftClckListener(new MaterialGoodsListAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(VarietiesBean.DataBean dataBean, int mPosition) {
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
        rowsBeans = new ArrayList<>();
        goodsSalesConfirmListAdapter = new MaterialConfirmListAdapter(rowsBeans);
        xRecyclerView.setAdapter(goodsSalesConfirmListAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                gainData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                initData2();
                xRecyclerView.loadMoreComplete();
            }
        });
        goodsSalesConfirmListAdapter.setOnItemClickListener(new MaterialConfirmListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, ProcurementTrackingListFragmentBean.DataBean.RowsBean rowsBean) {
                MaterialConfirmDetailsActivity.newIntent(getApplicationContext(), rowsBean.getId() + "");
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        chong_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
        chu_rl.setOnClickListener(this);
        screen_img.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                finish();
                break;
            case R.id.screen_img:
                sxMethodData();
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.chong_tv://筛选 重置
                sxMethodData();
                companyId = "";
                varietyId = "";
                chu_tv.setText("所有分子公司");
                break;
            case R.id.affirm_tv://筛选   确定
                rl.setVisibility(ViewGroup.VISIBLE);
                gainData();
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
                break;
            case R.id.chu_rl:
                getSubsidiaryCompanyList(view);
                break;
        }
    }

    //品种数据
    private void sxMethodData() {
        String sign = MD5Util.encode("categoryFullId=" + 4);
        RxHttpUtils.createApi(MaterialManagementApi.class)
                .getSelectQueryAllitem("4", sign)
                .compose(Transformer.<VarietiesBean>switchSchedulers())
                .subscribe(new NewCommonObserver<VarietiesBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(VarietiesBean sxPzBean) {
                        data = sxPzBean.getData();
                        if (data.size() != 0 && data != null) {
                            recyclerView.setVisibility(ViewGroup.VISIBLE);
//                            shaixuan_wsj.setVisibility(ViewGroup.GONE);
                            dataBeans.clear();
                            VarietiesBean.DataBean dataBean = new VarietiesBean.DataBean();
                            dataBean.setSelected(true);
                            dataBean.setVarietyId("");
                            dataBean.setId("");
                            dataBean.setName("全部");
                            dataBeans.add(dataBean);
                            dataBeans.addAll(data);
                            pzAdapter.notifyDataSetChanged();
                        } else {
                            recyclerView.setVisibility(ViewGroup.GONE);
//                            shaixuan_wsj.setVisibility(ViewGroup.VISIBLE);
                        }
                    }
                });
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(MaterialConfirmListActivity.this);
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

    //获取列表数据
    private void gainData() {
        currentPager = 1;
        String sign = MD5Util.encode("itemId=" + varietyId + "&orgId=" + companyId + "&page=" + currentPager + "&pagerow=" + 15);
        RxHttpUtils.createApi(MaterialManagementApi.class)
                .getMaterialConfirmList(varietyId, companyId, currentPager, 15, sign)
                .compose(Transformer.<ProcurementTrackingListFragmentBean>switchSchedulers())
                .subscribe(new NewCommonObserver<ProcurementTrackingListFragmentBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ProcurementTrackingListFragmentBean orderTJBean) {
                        if (orderTJBean.getCode() == 200) {
                            List<ProcurementTrackingListFragmentBean.DataBean.RowsBean> rows = orderTJBean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                xRecyclerView.setVisibility(View.VISIBLE);
                                wsj.setVisibility(View.GONE);
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                goodsSalesConfirmListAdapter.notifyDataSetChanged();
                            } else {
                                xRecyclerView.setVisibility(View.GONE);
                                wsj.setVisibility(View.VISIBLE);
                                rowsBeans.clear();
                                goodsSalesConfirmListAdapter.notifyDataSetChanged();
                            }
                        } else {
                            xRecyclerView.setVisibility(View.GONE);
                            wsj.setVisibility(View.VISIBLE);
//                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    //获取列表更多数据
    private void initData2() {
        String sign = MD5Util.encode("itemId=" + varietyId + "&orgId=" + companyId + "&page=" + currentPager + "&pagerow=" + 15);
        RxHttpUtils.createApi(MaterialManagementApi.class)
                .getMaterialConfirmList(varietyId, companyId, currentPager, 15, sign)
                .compose(Transformer.<ProcurementTrackingListFragmentBean>switchSchedulers())
                .subscribe(new NewCommonObserver<ProcurementTrackingListFragmentBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ProcurementTrackingListFragmentBean orderTJBean) {
                        if (orderTJBean.getCode() == 200) {
                            rowsBeans.addAll(orderTJBean.getData().getRows());
                            goodsSalesConfirmListAdapter.addMoreData(orderTJBean.getData().getRows());
                        } else {
                            xRecyclerView.setNoMore(true);
                            ToastUtil.setToast("最后页");
                        }
                    }

                });
    }

    /*
     * adapter
     * */
    public class VPAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> list;

        public VPAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    //分子公司 弹框
    private void showProvinceAndCityDialog(View view) {
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
                mCameraDialog.dismiss();
            }
        });
        yes_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyId = companyDataBeans.get(position).getId() + "";
                chu_tv.setText(companyDataBeans.get(position).getName());
                mCameraDialog.dismiss();
            }
        });
        //筛选  分公司
        companyDataBeans = new ArrayList<>();
        selectProvinceAdapter = new SelectSubsidiaryCompanyAdapter(companyDataBeans);
        buttom_rv.setLayoutManager(new LinearLayoutManager(MaterialConfirmListActivity.this));
        buttom_rv.setAdapter(selectProvinceAdapter);
        selectProvinceAdapter.setOnItemLeftClckListener(new SelectSubsidiaryCompanyAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(SubsidiaryCompanyBean.DataBean dataBean, int mPosition) {
                //当点击时显示当前条目的背景和文字的颜色
                for (int i = 0; i < companyDataBeans.size(); i++) {
                    if (mPosition == i) {
                        companyDataBeans.get(i).setSelected(true);
                    } else {
                        companyDataBeans.get(i).setSelected(false);
                    }
                }
                position = mPosition;
//                chu_tv.setText(companyDataBeans.get(position).getName());
                selectProvinceAdapter.notifyDataSetChanged();
            }
        });


    }

    //获取分子公司
    private void getSubsidiaryCompanyList(final View view) {
        RxHttpUtils.createApi(GoodsSalesApi.class)
                .getSubsidiaryCompanyList()
                .compose(Transformer.<SubsidiaryCompanyBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<SubsidiaryCompanyBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SubsidiaryCompanyBean companyBean) {
                        if (companyBean.getData().size() != 0 && companyBean != null) {
                            showProvinceAndCityDialog(view);
                            //选中位置为0
                            position = 0;
                            buttom_rv.setVisibility(View.VISIBLE);
                            companyDataBeans.clear();
                            companyBean.getData().get(0).setSelected(true);
                            wsj_dial.setVisibility(View.GONE);
                            companyDataBeans.addAll(companyBean.getData());
                            selectProvinceAdapter.notifyDataSetChanged();
                        } else {
                            buttom_rv.setVisibility(View.GONE);
                            wsj_dial.setVisibility(View.VISIBLE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        gainData();
    }
}
