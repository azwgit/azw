package com.example.bq.edmp.work.finishedproduct.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.DialoggerFail;
import com.example.bq.edmp.utils.DialoggerOk;
import com.example.bq.edmp.utils.GridItemDecoration;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.finishedproduct.adapter.CardTypeAdp;
import com.example.bq.edmp.work.finishedproduct.adapter.DeliverGoodsDetailsVarietiesListAdp;
import com.example.bq.edmp.work.grainmanagement.adapter.ContractorListAdp;
import com.example.bq.edmp.work.grainmanagement.adapter.DetectionListAdp;
import com.example.bq.edmp.work.grainmanagement.api.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.bean.ContractorListBean;
import com.example.bq.edmp.work.grainmanagement.bean.TestingBeanList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DeliverGoodsDetailsActivity extends BaseTitleActivity {
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_number)
    TextView mTvNumber;//订单号
    @BindView(R.id.tv_status)
    TextView mTvStatus;//订单状态
    @BindView(R.id.tv_contractor)
    TextView mTvContractor;//客户姓名
    @BindView(R.id.tv_salesman)
    TextView mTvSalesman;//业务员
    @BindView(R.id.tv_warehouse)
    TextView mTvWarehouse;//仓库
    @BindView(R.id.tv_company)
    TextView mTvCompany;//分子公司
    @BindView(R.id.tv_order_time)
    TextView mTvOrderTime;//下单时间
    @BindView(R.id.tv_deliver_goods_time)
    TextView mTvDeliverGoodsTime;//发货时间
    @BindView(R.id.tv_delivery_method)
    TextView mTvDeliveryMethod;//发货方式
    @BindView(R.id.ly_one)
    LinearLayout mLyOne;//发货父布局
    private TextView mTvCard, mTvTripartite, mTvOther;

    private DeliverGoodsDetailsVarietiesListAdp detectionListAdp;
    private List<TestingBeanList.DataBean.TestPlanItemsBean> testPlanItemsBeans = new ArrayList<TestingBeanList.DataBean.TestPlanItemsBean>();
    private ILoadingView loading_dialog;
    private DialoggerOk dialogOK = null;
    private DialoggerFail dialogFail = null;
    PopupWindow mTypePopuWindow;
    private List<ContractorListBean.DataBean> list=new ArrayList<ContractorListBean.DataBean>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_deliver_goods_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("订单详情");
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        detectionListAdp = new DeliverGoodsDetailsVarietiesListAdp(testPlanItemsBeans);
        mRecyclerView.setAdapter(detectionListAdp);
        getTestingList();
    }

    @Override
    protected void initListener() {
        mTvContractor.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_contractor:
               // showMachiningTaskReport();
                DeliveryInfo();
                break;
        }

    }

    //选择发货方式PopuWindow
    private void showMachiningTaskReport() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.delivery_method_layout, null);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        ImageView img = contentView.findViewById(R.id.img_cha);
        mTvCard = contentView.findViewById(R.id.tv_card);
        mTvCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvCard.setBackground(getResources().getDrawable(R.drawable.tv_text_red_shape));
                mTvCard.setTextColor(getResources().getColor(R.color.colorf9));
                mTvTripartite.setBackground(getResources().getDrawable(R.drawable.tv_text_grey_shape));
                mTvTripartite.setTextColor(getResources().getColor(R.color.color_85));
                mTvOther.setBackground(getResources().getDrawable(R.drawable.tv_text_grey_shape));
                mTvOther.setTextColor(getResources().getColor(R.color.color_85));
            }
        });
        mTvTripartite = contentView.findViewById(R.id.tv_tripartite);
        mTvTripartite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvTripartite.setBackground(getResources().getDrawable(R.drawable.tv_text_red_shape));
                mTvTripartite.setTextColor(getResources().getColor(R.color.colorf9));
                mTvCard.setBackground(getResources().getDrawable(R.drawable.tv_text_grey_shape));
                mTvCard.setTextColor(getResources().getColor(R.color.color_85));
                mTvOther.setBackground(getResources().getDrawable(R.drawable.tv_text_grey_shape));
                mTvOther.setTextColor(getResources().getColor(R.color.color_85));
            }
        });
        mTvOther = contentView.findViewById(R.id.tv_other);
        mTvOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvOther.setBackground(getResources().getDrawable(R.drawable.tv_text_red_shape));
                mTvOther.setTextColor(getResources().getColor(R.color.colorf9));
                mTvTripartite.setBackground(getResources().getDrawable(R.drawable.tv_text_grey_shape));
                mTvTripartite.setTextColor(getResources().getColor(R.color.color_85));
                mTvCard.setBackground(getResources().getDrawable(R.drawable.tv_text_grey_shape));
                mTvCard.setTextColor(getResources().getColor(R.color.color_85));
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTypePopuWindow.dismiss();
            }
        });
        TextView mTvOk= contentView.findViewById(R.id.tv_ok);
        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.setToast("发货成功");
                mTypePopuWindow.dismiss();
            }
        });
//        mLyView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTypePopuWindow.dismiss();
//            }
//        });
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

    //选择发货方式PopuWindow
    private void DeliveryInfo() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.delivery_info_layout, null);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
//        mLyView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTypePopuWindow.dismiss();
//            }
//        });
        for(int i=0;i<10;i++){
            ContractorListBean.DataBean bean=new ContractorListBean.DataBean();
            bean.setName("嘻嘻哈哈");
            list.add(bean);
        }
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.recyclerview);
        myRecyclerViewOne.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        final CardTypeAdp contractorListAdp = new CardTypeAdp(list);
        myRecyclerViewOne.setAdapter(contractorListAdp);
        contractorListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                contractorListAdp.ischeck=position;
                contractorListAdp.notifyDataSetChanged();
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

    //发货成功
    public void showOkDialog() {
        dialogOK = DialoggerOk.Builder(this)
                .setTitle("发货成功")
                .setMessage("")
                .build()
                .shown();
    }

    //发货失败
    public void showFailDialog() {
        dialogFail = DialoggerFail.Builder(this)
                .setTitle("发货失败")
                .setMessage("")
                .build()
                .shown();
    }

    //获取检测信息
    private void getTestingList() {
        String sign = MD5Util.encode("id=" + "2");
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .getTestingList("2" + "", sign)
                .compose(Transformer.<TestingBeanList>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<TestingBeanList>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(TestingBeanList bean) {
                        detectionListAdp.setNewData(bean.getData().get(0).getTestPlanItems());
                    }
                });
    }
}