package com.example.bq.edmp.work.allocation;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.allen.library.interfaces.ILoadingView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.inventory.adapter.BaseCom_Ck_Adapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.word.put_warehouse.Put_WarehouseActivity;
import com.example.bq.edmp.work.allocation.adapter.AllocationApplyListAdapter;
import com.example.bq.edmp.work.allocation.adapter.AllocationApprovalListAdapter;
import com.example.bq.edmp.work.allocation.api.AllocationApi;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.inventorytransfer.activity.FinishedProductAllocationDetailsActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.http.Field;

/*
 * 调拨   审批中
 * */
public class AllocationApprovalActivity extends BaseActivity {

    @BindView(R.id.search_et)
    EditText search_et;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.screen_img)
    ImageView screen_img;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;

    //筛选
    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.chu_rl)
    RelativeLayout chu_rl;
    @BindView(R.id.chu_tv)
    TextView chu_tv;
    @BindView(R.id.ru_rl)
    RelativeLayout ru_rl;
    @BindView(R.id.ru_tv)
    TextView ru_tv;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.chong_tv)
    TextView chong_tv;
    private TextView wsj_dial;
    private XRecyclerView buttom_rv;

    private ArrayList<CompanyBean.DataBean> companyDataBeans;
    private BaseCom_Ck_Adapter baseCom_ck_adapter;
    private ILoadingView loading_dialog;
    private AllocationApprovalListAdapter allocationApprovalListAdapter;
    private ArrayList<BaseAllocationBeam.DataBean.RowsBean> rowsBeans;

    private int currentPager = 1;
    private String code = "";
    private String inOrgId = "";//调入分公司id
    private String outOrgId = "";//调出分公司id
    private boolean Fund = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_allocation_approval;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(AllocationApprovalActivity.this);
        title_tv.setText("审批中");
        loading_dialog = new LoadingDialog(this);

        rowsBeans = new ArrayList<>();
        allocationApprovalListAdapter = new AllocationApprovalListAdapter(rowsBeans);
        xRecyclerView.setAdapter(allocationApprovalListAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(AllocationApprovalActivity.this));
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                initData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                initData2();
                xRecyclerView.loadMoreComplete();
            }
        });
        allocationApprovalListAdapter.setOnItemClickListener(new AllocationApprovalListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, BaseAllocationBeam.DataBean.RowsBean rowsBean) {
                FinishedProductAllocationDetailsActivity.newIntent(getApplicationContext(),rowsBean.getId());
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

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                inOrgId = "";
                outOrgId = "";
                code = "";
                chu_tv.setText("");
                ru_tv.setText("");
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

    //获取审批中数据
    private void gainData() {
        currentPager = 1;

        String sign = MD5Util.encode("code=" + code + "&inOrgId=" + inOrgId + "&outOrgId=" + outOrgId + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(AllocationApi.class)
                .getAllocationApprovalData(code, inOrgId, outOrgId, currentPager, 15, sign)
                .compose(Transformer.<BaseAllocationBeam>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseAllocationBeam>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseAllocationBeam baseAllocationBeam) {
                        String code = baseAllocationBeam.getCode();
                        if (code.equals("200")) {
                            List<BaseAllocationBeam.DataBean.RowsBean> rows = baseAllocationBeam.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                wsj.setVisibility(ViewGroup.GONE);
                                xRecyclerView.setVisibility(ViewGroup.VISIBLE);
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                allocationApprovalListAdapter.notifyDataSetChanged();
                            } else {
                                wsj.setVisibility(ViewGroup.VISIBLE);
                                xRecyclerView.setVisibility(ViewGroup.GONE);
                                rowsBeans.clear();
                                allocationApprovalListAdapter.notifyDataSetChanged();
                                ToastUtil.setToast("暂无数据");
                            }

                        } else {
                            wsj.setVisibility(ViewGroup.VISIBLE);
                            xRecyclerView.setVisibility(ViewGroup.GONE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    //获取审批中更多数据
    private void initData2() {
        String sign = MD5Util.encode("code=" + code + "&inOrgId=" + inOrgId + "&outOrgId=" + outOrgId + "&page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(AllocationApi.class)
                .getAllocationApprovalData(code, inOrgId, outOrgId, currentPager, 15, sign)
                .compose(Transformer.<BaseAllocationBeam>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseAllocationBeam>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseAllocationBeam baseAllocationBeam) {
                        String code = baseAllocationBeam.getCode();
                        if (code.equals("200")) {
                            List<BaseAllocationBeam.DataBean.RowsBean> rows = baseAllocationBeam.getData().getRows();
                            rowsBeans.addAll(rows);
                            allocationApprovalListAdapter.addMoreData(rows);
                        } else {
                            xRecyclerView.setNoMore(true);
                            ToastUtil.setToast("最后页");
                        }
                    }
                });
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        screen_img.setOnClickListener(this);
        ru_rl.setOnClickListener(this);
        chu_rl.setOnClickListener(this);
        chong_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                if (Fund) {
                    code = "";
                    inOrgId = "";
                    outOrgId = "";
                    search_et.setText("");
                    Fund = false;
                    gainData();
                } else {
                    fund();
                }
                break;
            case R.id.screen_img:
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.chu_rl://调出
                findContentViews(view, 1);
                break;
            case R.id.ru_rl://调入
                findContentViews(view, 2);
                break;
            case R.id.chong_tv://筛选   重置
                chu_tv.setText("");
                ru_tv.setText("");
                inOrgId = "";
                outOrgId = "";
                break;
            case R.id.affirm_tv://筛选   确定
                if (inOrgId.equals("")) {
                    ToastUtil.setToast("请选择调入分公司");
                    break;
                }
                if (outOrgId.equals("")) {
                    ToastUtil.setToast("请选择调出分公司");
                    break;
                }
                Fund = true;
                gainData();
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
                break;
        }
    }


    /*
     * 调出入
     * */
    private void findContentViews(View view, final int type) {
        final Dialog mCameraDialog = new Dialog(view.getContext(), R.style.my_dialog);

        View root = LayoutInflater.from(view.getContext()).inflate(R.layout.allocation_sx_buttom_item, null);

        mCameraDialog.setContentView(root);
        mCameraDialog.setCanceledOnTouchOutside(true);

        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.BottomDialog_Animation); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        //lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
        //lp.alpha = 9f; // 透明度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();

        buttom_rv = root.findViewById(R.id.buttom_rv);
        wsj_dial = root.findViewById(R.id.wsj);

        //筛选  分公司，仓库适配器
        companyDataBeans = new ArrayList<>();
        baseCom_ck_adapter = new BaseCom_Ck_Adapter(companyDataBeans);
        buttom_rv.setLayoutManager(new LinearLayoutManager(AllocationApprovalActivity.this));
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
                if (type == 1) {//调出
                    outOrgId = dataBean.getId();
                    chu_tv.setText(dataBean.getName());
                } else if (type == 2) {//调入
                    inOrgId = dataBean.getId();
                    ru_tv.setText(dataBean.getName());
                }
                mCameraDialog.dismiss();
            }
        });

        btuomMethod();
    }

    private void btuomMethod() {

        RxHttpUtils.createApi(InventoryListApi.class)
                .getCompanyData()
                .compose(Transformer.<CompanyBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<CompanyBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CompanyBean companyBean) {
                        List<CompanyBean.DataBean> data = companyBean.getData();
                        if (data != null && data.size() != 0) {
                            buttom_rv.setVisibility(View.VISIBLE);
                            wsj_dial.setVisibility(View.GONE);

                            companyDataBeans.clear();
                            companyDataBeans.addAll(data);
                            baseCom_ck_adapter.notifyDataSetChanged();
                        } else {
                            buttom_rv.setVisibility(View.GONE);
                            wsj_dial.setVisibility(View.VISIBLE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
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
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(AllocationApprovalActivity.this);
    }
}
