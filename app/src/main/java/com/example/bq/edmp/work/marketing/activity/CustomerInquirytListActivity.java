package com.example.bq.edmp.work.marketing.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View;
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
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.inventory.adapter.BaseCom_Ck_Adapter;
import com.example.bq.edmp.word.inventory.api.InventoryListApi;
import com.example.bq.edmp.word.inventory.bean.CompanyBean;
import com.example.bq.edmp.work.finishedproduct.activity.DeliverGoodsDetailsActivity;
import com.example.bq.edmp.work.inventorytransfer.activity.FinishedProductAllocationDetailsActivity;
import com.example.bq.edmp.work.marketing.adapter.CustomerManagementListAdp;
import com.example.bq.edmp.work.marketing.adapter.SelectProvinceAdapter;
import com.example.bq.edmp.work.marketing.api.CustomerManagementApi;
import com.example.bq.edmp.work.marketing.bean.CustomerManagementListBean;
import com.example.bq.edmp.work.marketing.bean.ProvinceAndCityListBean;
import com.example.bq.edmp.work.shipments.adapter.DshipmentsListAdapter;
import com.example.bq.edmp.work.shipments.adapter.UserNameListAdapter;
import com.example.bq.edmp.work.shipments.api.ShipmentsApi;
import com.example.bq.edmp.work.shipments.bean.DshipmentsListBean;
import com.example.bq.edmp.work.shipments.bean.UserNameListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 客户管理
 * */
public class CustomerInquirytListActivity extends BaseActivity {
    public static void newIntent(Context context) {
        Intent intent = new Intent(context, CustomerInquirytListActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.tv_operation)
    TextView mTvOperation;
    @BindView(R.id.search_et)
    TextView search_et;
    @BindView(R.id.screen_img)
    ImageView screen_img;
    @BindView(R.id.xr)
    XRecyclerView xr;
    @BindView(R.id.linter_history)
    RelativeLayout linterHistoryConfirm;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.rl_sheng)
    RelativeLayout mRlSheng;//省点击布局
    @BindView(R.id.rl_shi)
    RelativeLayout mRlShi;//市点击布局
    @BindView(R.id.rl_qu)
    RelativeLayout mRlQu;//区点击布局
    @BindView(R.id.tv_sheng)
    TextView mTvSheng;//省
    @BindView(R.id.tv_shi)
    TextView mTvShi;//市
    @BindView(R.id.tv_qu)
    TextView mTvQu;//区
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.affirm_tv)
    TextView affirm_tv;
    @BindView(R.id.cz_tv)
    TextView cz_tv;
    private int currentPager = 1;
    private String shengId = "";//省id
    private String shiId = "";//市Id
    private String quId = "";//区Id
    //兼容 侧滑栏 不清空数据 和后台接收参数 省市区 传最后一级 其余放空
    private String newShengId = "";//省id
    private String newShiId = "";//市Id
    private String newQuId = "";//区Id
    private String name = "";//输入客户名称
    private int position = 0;
    private XRecyclerView buttom_rv;
    private TextView wsj_dial;
    private ArrayList<ProvinceAndCityListBean.DataBean> companyDataBeans;
    private SelectProvinceAdapter selectProvinceAdapter;//选择省适配器
    private ArrayList<CustomerManagementListBean.DataBean.RowsBean> rowsBeans;
    private CustomerManagementListAdp customerManagementListAdp;
    private List<ProvinceAndCityListBean.DataBean> data = null;
    private ILoadingView loading_dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_management_list;
    }

    @Override
    protected void initView() {
        title_tv.setText("客户查询");
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        //数据
        rowsBeans = new ArrayList<>();
        customerManagementListAdp = new CustomerManagementListAdp(rowsBeans);
        xr.setAdapter(customerManagementListAdp);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(true);
        xr.setLayoutManager(new LinearLayoutManager(CustomerInquirytListActivity.this));
        xr.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                gainData();
                xr.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                initData2();
                xr.loadMoreComplete();
            }
        });

        customerManagementListAdp.setOnItemClickListener(new CustomerManagementListAdp.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, CustomerManagementListBean.DataBean.RowsBean rowsBean) {
                CustomerDetailsActivity.newIntent(getApplicationContext(), rowsBean.getId() + "", "2");
            }
        });
        //筛选框状态
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
//                mTvSheng.setHint("请选择");
//                mTvSheng.setText("");
//                mTvShi.setHint("请选择");
//                mTvShi.setText("");
//                mTvQu.setHint("请选择");
//                mTvQu.setText("");
//                shengId = "";
//                shiId = "";
//                quId = "";
//                position = 0;
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
                    hideKeyboard(search_et);
                    name = textView.getText().toString();
                    gainData();
                    return true;
                }
                return false;

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        gainData();
    }

    @Override
    protected void initData() {

    }

    private void gainData() {
        currentPager = 1;
        if (!shengId.equals("")) {
            newShengId = shengId;
            newShiId = "";
            newQuId = "";
        }
        if (!shiId.equals("")) {
            newShiId = shiId;
            newShengId = "";
            newQuId = "";
        }
        if (!quId.equals("")) {
            newQuId = quId;
            newShengId = "";
            newShiId = "";
        }
        String sign = MD5Util.encode("cityId=" + newShiId + "&countyId=" + newQuId + "&name=" + name + "&page=" + currentPager +
                "&pagerow=" + 15 + "&provinceId=" + newShengId);

        RxHttpUtils.createApi(CustomerManagementApi.class)
                .getCustomerList(newShiId, newQuId, name, currentPager, 15, newShengId, sign)
                .compose(Transformer.<CustomerManagementListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<CustomerManagementListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CustomerManagementListBean bean) {
                        if (bean.getCode() == 200) {
                            List<CustomerManagementListBean.DataBean.RowsBean> rows = bean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                xr.setVisibility(View.VISIBLE);
                                wsj.setVisibility(View.GONE);
                                rowsBeans.clear();
                                rowsBeans.addAll(rows);
                                customerManagementListAdp.notifyDataSetChanged();
                            } else {
                                xr.setVisibility(View.GONE);
                                wsj.setVisibility(View.VISIBLE);

                                rowsBeans.clear();
                                customerManagementListAdp.notifyDataSetChanged();
                                ToastUtil.setToast("暂无数据");
                            }
                        } else {
                            xr.setVisibility(View.GONE);
                            wsj.setVisibility(View.VISIBLE);
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    private void initData2() {
        String sign = MD5Util.encode("cityId=" + newShiId + "&countyId=" + newQuId + "&name=" + name + "&page=" + currentPager +
                "&pagerow=" + 15 + "&provinceId=" + newShengId);

        RxHttpUtils.createApi(CustomerManagementApi.class)
                .getCustomerList(newShiId, newQuId, name, currentPager, 15, newShengId, sign)
                .compose(Transformer.<CustomerManagementListBean>switchSchedulers())
                .subscribe(new NewCommonObserver<CustomerManagementListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CustomerManagementListBean bean) {
                        if (bean.getCode() == 200) {
                            List<CustomerManagementListBean.DataBean.RowsBean> rows = bean.getData().getRows();
                            if (rows != null && rows.size() != 0) {
                                rowsBeans.addAll(rows);
                                customerManagementListAdp.addMoreData(rows);
                            } else {
                                xr.setNoMore(true);
                            }
                        } else {
                            ToastUtil.setToast("暂无数据");
                        }
                    }
                });
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        screen_img.setOnClickListener(this);
        mRlSheng.setOnClickListener(this);
        mRlShi.setOnClickListener(this);
        mRlQu.setOnClickListener(this);
        cz_tv.setOnClickListener(this);
        affirm_tv.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                finish();
                break;
            case R.id.screen_img:
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.rl_sheng:
                getProvinceAndCityList(1, view);
                break;
            case R.id.rl_shi:
                getProvinceAndCityList(2, view);
                break;
            case R.id.rl_qu:
                getProvinceAndCityList(3, view);
                break;
            case R.id.cz_tv:
                mTvSheng.setHint("请选择");
                mTvSheng.setText("");
                mTvShi.setHint("请选择");
                mTvShi.setText("");
                mTvQu.setHint("请选择");
                mTvQu.setText("");
                shengId = "";
                shiId = "";
                quId = "";
                position = 0;
                //需对接需求  是否时候需要再次点击筛选  条件不清空 功能
                newShengId="";
                newShiId="";
                newQuId="";

                break;
            case R.id.affirm_tv:
                gainData();
                if (linterHistoryConfirm.getVisibility() == View.VISIBLE) {
                    //当菜单栏是可见的，则关闭
                    drawerLayout.closeDrawer(linterHistoryConfirm);
                }
                break;
            case R.id.search_et:
                break;

        }
    }

    /*
     * 底部跳框
     * */
    private void showProvinceAndCityDialog(final int type, View view) {
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
                if (type == 1) {
                    //选择省 市区重置
                    mTvSheng.setText(data.get(position).getName());
                    mTvSheng.setTextColor(getResources().getColor(R.color.text_black));
                    shengId = data.get(position).getId();
                    mTvShi.setHint("请选择");
                    mTvShi.setText("");
                    shiId = "";
                    mTvQu.setHint("请选择");
                    mTvQu.setText("");
                    quId = "";
                } else if (type == 2) {
                    //选择市 区重置
                    mTvShi.setText(data.get(position).getName());
                    mTvShi.setTextColor(getResources().getColor(R.color.text_black));
                    shiId = data.get(position).getId();
                    mTvQu.setHint("请选择");
                    mTvQu.setText("");
                    quId = "";
                } else {
                    quId = data.get(position).getId();
                    mTvQu.setText(data.get(position).getName());
                    mTvQu.setTextColor(getResources().getColor(R.color.text_black));
                }
                mCameraDialog.dismiss();
            }
        });

        //筛选  分公司
        companyDataBeans = new ArrayList<>();
        selectProvinceAdapter = new SelectProvinceAdapter(companyDataBeans);
        buttom_rv.setLayoutManager(new LinearLayoutManager(CustomerInquirytListActivity.this));
        buttom_rv.setAdapter(selectProvinceAdapter);
        selectProvinceAdapter.setOnItemLeftClckListener(new SelectProvinceAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(ProvinceAndCityListBean.DataBean dataBean, int mPosition) {
                //当点击时显示当前条目的背景和文字的颜色
                for (int i = 0; i < companyDataBeans.size(); i++) {
                    if (mPosition == i) {
                        companyDataBeans.get(i).setSelected(true);
                    } else {
                        companyDataBeans.get(i).setSelected(false);
                    }
                }
                position = mPosition;
                selectProvinceAdapter.notifyDataSetChanged();
            }
        });


    }

    //获取省市区id
    private void getProvinceAndCityList(final int type, final View view) {
        String id = "";
        //type 1 查询省 2查询市 3查询区
        if (type == 1) {
            id = "0";
        } else if (type == 2) {
            if ("".equals(shengId)) {
                ToastUtil.setToast("请选择省");
                return;
            }
            id = shengId;
        } else {
            if ("".equals(shiId)) {
                ToastUtil.setToast("请选择市");
                return;
            }
            id = shiId;
        }
        String sign = MD5Util.encode("parentId=" + id);
        RxHttpUtils.createApi(CustomerManagementApi.class)
                .getProvinceAndCityList(id, sign)
                .compose(Transformer.<ProvinceAndCityListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<ProvinceAndCityListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ProvinceAndCityListBean companyBean) {
                        data = companyBean.getData();
                        if (data.size() != 0 && data != null) {
                            showProvinceAndCityDialog(type, view);
                            //选中位置为0
                            position = 0;
                            buttom_rv.setVisibility(View.VISIBLE);
                            wsj_dial.setVisibility(View.GONE);
                            companyDataBeans.clear();
                            data.get(0).setSelected(true);
                            companyDataBeans.addAll(data);
                            selectProvinceAdapter.notifyDataSetChanged();
                        } else {
                            buttom_rv.setVisibility(View.GONE);
                            wsj_dial.setVisibility(View.VISIBLE);
                            ToastUtil.setToast("暂无数据");
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
}