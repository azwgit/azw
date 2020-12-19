package com.example.bq.edmp.work.detection;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
/*
 * 新增检测
 * */
import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.MainActivity;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.DialoggerFail;
import com.example.bq.edmp.utils.DialoggerOk;
import com.example.bq.edmp.utils.GridItemDecoration;
import com.example.bq.edmp.utils.JSONTool;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.detection.adapter.DetectionCkListAdp;
import com.example.bq.edmp.work.detection.adapter.DetectionContractorListAdp;
import com.example.bq.edmp.work.detection.adapter.DetectionDkListAdp;
import com.example.bq.edmp.work.detection.adapter.DetectionJianCeXiangListAdp;
import com.example.bq.edmp.work.detection.adapter.DetectionPzListAdp;
import com.example.bq.edmp.work.detection.api.DetectionApi;
import com.example.bq.edmp.work.detection.bean.DetectionCkBean;
import com.example.bq.edmp.work.detection.bean.DetectionDkBean;
import com.example.bq.edmp.work.detection.bean.DetectionPzBean;
import com.example.bq.edmp.work.detection.bean.DetectionTestingBean;
import com.example.bq.edmp.work.detection.bean.DetectonLxBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewDetectionActivity extends BaseActivity implements DetectionJianCeXiangListAdp.SaveEditListener {
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.tv_contractor_lx)
    TextView mTvContractor;
    @BindView(R.id.tv_varieties_dk)
    TextView mTvVarieties;
    @BindView(R.id.tv_warehouse_pz)
    TextView mTvWarehouse;
    @BindView(R.id.tv_jiance)
    TextView mTvJiance;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.return_img)
    ImageView return_img;


    PopupWindow mTypePopuWindow;
    private ILoadingView loading_dialog;
    private DetectionJianCeXiangListAdp detectionJianCeXiangListAdp;
    private List<DetectionTestingBean.DataBean> dataBeans = new ArrayList<DetectionTestingBean.DataBean>();
    private DetectonLxBean mdetectonLxBean;//类型数据源
    private DetectionPzBean mdetectionPzBean;//品种数据源
    private DetectionCkBean mdetectionCkBean;//仓库数据源
    private DetectionDkBean mdetectionDkBean;//地块数据源
    private String lxId = "";//所选类型人id
    private String pzId = "";//所选品种id
    private String ck_Id = "";//所选仓库id
    private String dk_Id = "";//所选地块id
    private String types = "";
    private String code = "";
    private DetectionDkListAdp detectionDkListAdp;
    private ArrayList<DetectionDkBean.DataBean> dk_dataBeans;
    private DialoggerOk dialogOK = null;
    private CountDownTimer timer;
    private int states = 0;
    private DialoggerFail dialoggerFail;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_detection;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(NewDetectionActivity.this);
        title_tv.setText("新增检测");
        loading_dialog = new LoadingDialog(this);
    }


    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
        mRecyclerView.addItemDecoration(gridItemDecoration);
        detectionJianCeXiangListAdp = new DetectionJianCeXiangListAdp(dataBeans);
        mRecyclerView.setAdapter(detectionJianCeXiangListAdp);
    }

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);
        mTvContractor.setOnClickListener(this);
        mTvVarieties.setOnClickListener(this);
        mTvWarehouse.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.tv_submit:
                getSubimt();
                break;
            case R.id.tv_contractor_lx://类型
                getContractor_lx_List();
                break;
            case R.id.tv_varieties_dk://地块/仓库
                code = "";
                getVarietiesList();
                break;
            case R.id.tv_warehouse_pz://品种
                getPzList();
                break;
        }
    }

    //提交确认
    private void getSubimt() {
        if (lxId.equals("")) {
            ToastUtil.setToast("请选择检测类型");
            return;
        }
        if (ck_Id.equals("") && dk_Id.equals("")) {
            ToastUtil.setToast("请选择仓库或地块");
            return;
        }
        if (pzId.equals("")) {
            ToastUtil.setToast("请选择检测品种");
            return;
        }
        for (int i = 0; i < dataBeans.size(); i++) {
            DetectionTestingBean.DataBean dataBean = dataBeans.get(i);
            String content = dataBean.getContent();
            if ("".equals(content) || content == null) {
                if (dataBean.getContent().equals("")) {
                    Toast.makeText(getApplicationContext(), "请输入" + dataBean.getName(), Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
        addAcquisition();
    }

    //提交
    private void addAcquisition() {

        List id = new ArrayList();
        List value = new ArrayList();
        for (int i = 0; i < dataBeans.size(); i++) {
            id.add(dataBeans.get(i).getId());
            value.add(dataBeans.get(i).getContent());
        }


        String sign = MD5Util.encode("farmLandId=" + dk_Id + "&testPlanId=" + lxId + "&testPlanItemId=" + dataBeans.get(0).getId()
                + "&testingItemValue=" + dataBeans.get(0).getContent()
                + "&varietyId=" + pzId + "&wareshouseId=" + ck_Id
        );


        RxHttpUtils.createApi(DetectionApi.class)
                .addAcquisitions(dk_Id, id, value, lxId, pzId, ck_Id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers())
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if (baseABean != null) {
                            if (baseABean.getCode() == 200) {
                                states = 1;
                                showOkDialog();
                                mTvContractor.setText("");
                                mTvVarieties.setText("");
                                mTvWarehouse.setText("");

                                dataBeans.clear();
                                detectionJianCeXiangListAdp.notifyDataSetChanged();
                                //隐藏检测信息
                                mTvJiance.setVisibility(View.GONE);

                            } else {
                                states = 0;
                                dieesOkDialog();
                                ToastUtil.setToast(baseABean.getMsg());
                            }
//                            showTimer();
                        }
                    }
                });


    }

    private void showTimer() {
        timer = new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                if (states==1) {
                    dialogOK.dismiss();
                }else {
                    dialoggerFail.dismiss();
                }
            }
        };
        //调用 CountDownTimer 对象的 start() 方法开始倒计时，也不涉及到线程处理
        timer.start();
    }

    public void showOkDialog() {
        dialogOK = DialoggerOk.Builder(this)
                .setTitle("添加成功")
                .setMessage("")
                .build()
                .shown();
    }

    public void dieesOkDialog() {
        dialoggerFail = DialoggerFail.Builder(this)
                .setTitle("添加失败")
                .setMessage("")
                .build()
                .shown();
    }

    //获取品种数据
    private void getPzList() {
        if (ck_Id.equals("") && dk_Id.equals("")) {
            ToastUtil.setToast("请先选择检测地块/仓库");
            return;
        }
        RxHttpUtils.createApi(DetectionApi.class)
                .getPzList()
                .compose(Transformer.<DetectionPzBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<DetectionPzBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DetectionPzBean detectionPzBean) {
                        if (detectionPzBean != null) {
                            mdetectionPzBean = detectionPzBean;
                            getPzDataList();
                        }

                    }
                });

    }

    //获取品种数据PopuWindow
    private void getPzDataList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        DetectionPzListAdp detectionPzListAdp = new DetectionPzListAdp(mdetectionPzBean.getData());
        myRecyclerViewOne.setAdapter(detectionPzListAdp);
        detectionPzListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                pzId = mdetectionPzBean.getData().get(position).getId();
                mTvWarehouse.setText(mdetectionPzBean.getData().get(position).getName());
                mTypePopuWindow.dismiss();
            }
        });
        mLyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTypePopuWindow.dismiss();
            }
        });
        mTypePopuWindow = new PopupWindow();
        mTypePopuWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mTypePopuWindow.setContentView(contentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        mTypePopuWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        mTypePopuWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        // 实例化一个ColorDrawable颜色为透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        mTypePopuWindow.setBackgroundDrawable(dw);
        // 设置SelectPicPopupWindow弹出窗体可点击
        mTypePopuWindow.setFocusable(true);
//        backgroundAlpha(0.4f);
        mTypePopuWindow.setOutsideTouchable(true);
        mTypePopuWindow.setClippingEnabled(false);
        mTypePopuWindow.showAtLocation(findViewById(R.id.rl_view), Gravity.BOTTOM, 0, 0);
    }

    //获取仓库/地块数据
    private void getVarietiesList() {
        if (lxId.equals("")) {
            ToastUtil.setToast("请先选择检测类型");
            return;
        }
        //1田间检验 2存货检验
        if (types.equals("1")) {//地块
            showDkDialog();
        } else if (types.equals("2")) {//仓库
            RxHttpUtils.createApi(DetectionApi.class)
                    .getCkList()
                    .compose(Transformer.<DetectionCkBean>switchSchedulers())
                    .subscribe(new NewCommonObserver<DetectionCkBean>() {
                        @Override
                        protected void onError(String errorMsg) {
                            ToastUtil.setToast(errorMsg);
                        }

                        @Override
                        protected void onSuccess(DetectionCkBean detectionCkBean) {
                            if (detectionCkBean != null) {
                                mdetectionCkBean = detectionCkBean;
                                getCkList();
                            }
                        }
                    });
        }

    }

    private void showDkDialog() {
        final Dialog mCameraDialog = new Dialog(view.getContext(), R.style.my_dialog);

        View root = LayoutInflater.from(view.getContext()).inflate(R.layout.sousuo_select_option_layout, null);

        mCameraDialog.setContentView(root);
        mCameraDialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.BottomDialog_Animation); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();

        final EditText user_search_et = root.findViewById(R.id.search_et);
        RecyclerView myRecyclerViewOne = root.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = root.findViewById(R.id.ly_view);


        dk_dataBeans = new ArrayList<>();
        detectionDkListAdp = new DetectionDkListAdp(dk_dataBeans);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(NewDetectionActivity.this));
        myRecyclerViewOne.setAdapter(detectionDkListAdp);
        detectionDkListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dk_Id = mdetectionDkBean.getData().get(position).getId();
                mTvVarieties.setText(mdetectionDkBean.getData().get(position).getCode());
                //显示检测信息
                mCameraDialog.dismiss();
            }
        });

        //搜索
        user_search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //此处做逻辑处理
                    code = textView.getText().toString();
                    hideKeyboard(user_search_et);
                    gainUserNameData();
                    return true;
                }
                return false;
            }
        });
        gainUserNameData();
    }

    //获取地块数据PopuWindow
    private void gainUserNameData() {
        String sign = MD5Util.encode("code=" + code);
        RxHttpUtils.createApi(DetectionApi.class)
                .getDkList(code, sign)
                .compose(Transformer.<DetectionDkBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<DetectionDkBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DetectionDkBean detectionDkBean) {
                        if (detectionDkBean != null) {
                            dk_dataBeans.addAll(detectionDkBean.getData());
                            detectionDkListAdp.notifyDataSetChanged();
                            mdetectionDkBean = detectionDkBean;
                        }
                    }
                });
    }

    //获取仓库数据PopuWindow
    private void getCkList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        DetectionCkListAdp detectionCkListAdp = new DetectionCkListAdp(mdetectionCkBean.getData());
        myRecyclerViewOne.setAdapter(detectionCkListAdp);
        detectionCkListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ck_Id = mdetectionCkBean.getData().get(position).getId();
                mTvVarieties.setText(mdetectionCkBean.getData().get(position).getName());
                //显示检测信息
                mTypePopuWindow.dismiss();
            }
        });
        mLyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTypePopuWindow.dismiss();
            }
        });
        mTypePopuWindow = new PopupWindow();
        mTypePopuWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mTypePopuWindow.setContentView(contentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        mTypePopuWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        mTypePopuWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        // 实例化一个ColorDrawable颜色为透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        mTypePopuWindow.setBackgroundDrawable(dw);
        // 设置SelectPicPopupWindow弹出窗体可点击
        mTypePopuWindow.setFocusable(true);
//        backgroundAlpha(0.4f);
        mTypePopuWindow.setOutsideTouchable(true);
        mTypePopuWindow.setClippingEnabled(false);
        mTypePopuWindow.showAtLocation(findViewById(R.id.rl_view), Gravity.BOTTOM, 0, 0);
    }

    //获取类型数据
    private void getContractor_lx_List() {
        RxHttpUtils.createApi(DetectionApi.class)
                .getContractorList()
                .compose(Transformer.<DetectonLxBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<DetectonLxBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DetectonLxBean detectonLxBean) {
                        mdetectonLxBean = detectonLxBean;
                        showContractorList();
                    }
                });
    }

    //类型列表PopuWindow
    private void showContractorList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.select_option_layout, null);
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.my_recycler_view_one);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        myRecyclerViewOne.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
//        gridItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
//        myRecyclerViewOne.addItemDecoration(gridItemDecoration);
        DetectionContractorListAdp detectionContractorListAdp = new DetectionContractorListAdp(mdetectonLxBean.getData());
        myRecyclerViewOne.setAdapter(detectionContractorListAdp);
        detectionContractorListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                lxId = mdetectonLxBean.getData().get(position).getId();
                mTvContractor.setText(mdetectonLxBean.getData().get(position).getTestName());
                //记录types
                types = mdetectonLxBean.getData().get(position).getTypes();
                //清空品种信息
                mTvVarieties.setText("");
                pzId = "";
                //清空仓库/地块信息
                mTvWarehouse.setText("");
                dk_Id = "";
                ck_Id = "";
                //获取检测项目
                dataBeans.clear();
                detectionJianCeXiangListAdp.addData(dataBeans);
                getTestingList();
                //隐藏检测信息
                mTvJiance.setVisibility(View.VISIBLE);
                mTypePopuWindow.dismiss();
            }
        });
        mLyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTypePopuWindow.dismiss();
            }
        });
        mTypePopuWindow = new PopupWindow();
        mTypePopuWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mTypePopuWindow.setContentView(contentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        mTypePopuWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        mTypePopuWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        // 实例化一个ColorDrawable颜色为透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        mTypePopuWindow.setBackgroundDrawable(dw);
        // 设置SelectPicPopupWindow弹出窗体可点击
        mTypePopuWindow.setFocusable(true);
//        backgroundAlpha(0.4f);
        mTypePopuWindow.setOutsideTouchable(true);
        mTypePopuWindow.setClippingEnabled(false);
        mTypePopuWindow.showAtLocation(findViewById(R.id.rl_view), Gravity.BOTTOM, 0, 0);
    }

    //获取检测项数据
    private void getTestingList() {

        String sign = MD5Util.encode("id=" + lxId);

        RxHttpUtils.createApi(DetectionApi.class)
                .getTestingList(lxId, sign)
                .compose(Transformer.<DetectionTestingBean>switchSchedulers())
                .subscribe(new NewCommonObserver<DetectionTestingBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DetectionTestingBean detectionTestingBean) {
                        if (detectionTestingBean != null) {
                            detectionJianCeXiangListAdp.setNewData(detectionTestingBean.getData());
                            dataBeans = detectionTestingBean.getData();
                        }
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        ProApplication.getinstance().finishActivity(NewDetectionActivity.this);
    }

    @Override
    public void SaveEdit(int position, String string) {
        dataBeans.get(position).setContent(string);
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
