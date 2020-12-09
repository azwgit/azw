package com.example.bq.edmp.work.finishedproduct.activity;

import android.content.Context;
import android.content.Intent;
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
import android.widget.EditText;
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
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.DialoggerFail;
import com.example.bq.edmp.utils.DialoggerOk;
import com.example.bq.edmp.utils.GridItemDecoration;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.finishedproduct.adapter.CardTypeAdp;
import com.example.bq.edmp.work.finishedproduct.adapter.DeliverGoodsDetailsVarietiesListAdp;
import com.example.bq.edmp.work.finishedproduct.api.FinishedProductApi;
import com.example.bq.edmp.work.finishedproduct.bean.SendGoodsDetailsBean;
import com.example.bq.edmp.work.finishedproduct.bean.VechicleListBean;
import com.example.bq.edmp.work.grainmanagement.activity.AcquisitionDetailAct;
import com.example.bq.edmp.work.grainmanagement.adapter.ContractorListAdp;
import com.example.bq.edmp.work.grainmanagement.adapter.DetectionListAdp;
import com.example.bq.edmp.work.grainmanagement.api.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.bean.ContractorListBean;
import com.example.bq.edmp.work.grainmanagement.bean.TestingBeanList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DeliverGoodsDetailsActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, DeliverGoodsDetailsActivity.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

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
    @BindView(R.id.btn_submit)
    TextView mBtnSubmit;//确认发货按钮

    private TextView mTvCard, mTvTripartite, mTvOther;

    private DeliverGoodsDetailsVarietiesListAdp detectionListAdp;
    private List<TestingBeanList.DataBean.TestPlanItemsBean> testPlanItemsBeans = new ArrayList<TestingBeanList.DataBean.TestPlanItemsBean>();
    private ILoadingView loading_dialog;
    private DialoggerOk dialogOK = null;
    private DialoggerFail dialogFail = null;
    private PopupWindow mTypePopuWindow;
    private int deliveryType = 1;//发货方式  1车辆 2三方物流 3其他
    private String id = "";
    private List<ContractorListBean.DataBean> list = new ArrayList<ContractorListBean.DataBean>();
    private SendGoodsDetailsBean sendGoodsDetailsBean = null;//发货详情
    private VechicleListBean vechicleListBean = null;//车辆列表
    private int VechicleLsitposition = 0;//-1 未选中任何车辆
    private EditText mEtCompany;//物流公司名称
    private EditText mEtOrdernum;//物流单号
    private EditText mEtRemark;//发货信息备注

    @Override
    protected int getLayoutId() {
        return R.layout.activity_deliver_goods_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("订单详情");
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        detectionListAdp = new DeliverGoodsDetailsVarietiesListAdp(null);
        mRecyclerView.setAdapter(detectionListAdp);
        getSendGoodsDetails();
    }

    @Override
    protected void initListener() {
        mTvContractor.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                showMachiningTaskReport();
                break;

        }

    }

    //选择发货方式PopuWindow
    private void showMachiningTaskReport() {
        //初始化默认选择车辆
        deliveryType = 1;
        LayoutInflater inflater = (LayoutInflater) getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.delivery_method_layout, null);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        ImageView img = contentView.findViewById(R.id.img_cha);
        mTvCard = contentView.findViewById(R.id.tv_card);
        mTvCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliveryType = 1;
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
                deliveryType = 2;
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
                deliveryType = 3;
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
        TextView mTvOk = contentView.findViewById(R.id.tv_ok);
        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTypePopuWindow.dismiss();
                if (deliveryType == 1) {
                    getVehicleList();
                } else {
                    DeliveryInfo();
                }
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
        TextView mTvSend = contentView.findViewById(R.id.tv_send);
        TextView mTvTitle = contentView.findViewById(R.id.tv_title);
        LinearLayout mLyView = contentView.findViewById(R.id.ly_view);
        mEtCompany = contentView.findViewById(R.id.et_company);
        mEtOrdernum = contentView.findViewById(R.id.et_ordernum);
        mEtRemark = contentView.findViewById(R.id.et_remark);
        //物流方式
        LinearLayout mLyLogistics = contentView.findViewById(R.id.ly_logistics);
        //选择车辆方式
        RecyclerView myRecyclerViewOne = contentView.findViewById(R.id.recyclerview);
        //选择其他发货信息
        LinearLayout mLyRemark = contentView.findViewById(R.id.ly_remark);
        if (deliveryType == 1) {
            mTvTitle.setText("选择车辆信息");
            myRecyclerViewOne.setVisibility(View.VISIBLE);
            mLyLogistics.setVisibility(View.GONE);
            mLyRemark.setVisibility(View.GONE);
            myRecyclerViewOne.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
            final CardTypeAdp contractorListAdp = new CardTypeAdp(vechicleListBean.getData());
            //默认选中第一个
            contractorListAdp.ischeck = 0;
            VechicleLsitposition=0;
            myRecyclerViewOne.setAdapter(contractorListAdp);
            contractorListAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    VechicleLsitposition = position;
                    contractorListAdp.ischeck = position;
                    contractorListAdp.notifyDataSetChanged();
                }
            });
        } else if (deliveryType == 2) {
            mTvTitle.setText("填写第三方物流信息");
            myRecyclerViewOne.setVisibility(View.GONE);
            mLyLogistics.setVisibility(View.VISIBLE);
            mLyRemark.setVisibility(View.GONE);
        } else {
            mTvTitle.setText("其他发货信息");
            myRecyclerViewOne.setVisibility(View.GONE);
            mLyLogistics.setVisibility(View.GONE);
            mLyRemark.setVisibility(View.VISIBLE);
        }
        mTvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSendGoods();
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
    //获取发货详情
    private void getSendGoodsDetails() {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(FinishedProductApi.class)
                .getSendGoodsDetails(id, sign)
                .compose(Transformer.<SendGoodsDetailsBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<SendGoodsDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SendGoodsDetailsBean bean) {
                        sendGoodsDetailsBean = bean;
                        if (bean.getCode() == 200) {
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }

                    }
                });
    }
    //获取车辆列表
    private void getVehicleList() {
        RxHttpUtils.createApi(FinishedProductApi.class)
                .getVehicleList()
                .compose(Transformer.<VechicleListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<VechicleListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(VechicleListBean bean) {
                        vechicleListBean = bean;
                        if (bean.getCode() == 200) {
                            DeliveryInfo();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }
    //详情赋值
    private void setData(SendGoodsDetailsBean.DataBean bean) {
        mTvNumber.setText("订单号  " + bean.getCode());
        switch (bean.getStatus()) {
            case "5":
                mTvStatus.setText("待发货");
                break;
            case "6":
            case "7":
                mTvStatus.setText("已发货");
                //隐藏已发货信息
                mLyOne.setVisibility(View.GONE);
                //隐藏发货按钮
                mBtnSubmit.setVisibility(View.GONE);
                mTvDeliverGoodsTime.setText(bean.getOrderSendOut().getTimes());
                //1选择车辆发货 2 物流发货 3 其他
                if (bean.getOrderSendOut().getTypes() == 1) {
                    mTvDeliveryMethod.setText(bean.getOrderSendOut().getContacts() + "  " + bean.getOrderSendOut().getLicense());
                } else if (bean.getOrderSendOut().getTypes() == 2) {
                    mTvDeliveryMethod.setText(bean.getOrderSendOut().getLogisticsName() + "  " + bean.getOrderSendOut().getTplNo());
                } else {
                    mTvDeliveryMethod.setText(bean.getOrderSendOut().getRemark());
                }
                break;

        }
        mTvContractor.setText(bean.getCustomerName());
        mTvSalesman.setText(bean.getSalesman());
        mTvWarehouse.setText(bean.getWarehouseName());
        mTvCompany.setText(bean.getOrgName());
        mTvOrderTime.setText(bean.getAddedTime());
        detectionListAdp.setNewData(bean.getOrderItems());
    }
    //验证发货信息
    private void checkSendGoods() {
        String company=mEtCompany.getText().toString().trim();
        String ordernum=mEtOrdernum.getText().toString().trim();
        String remark=mEtRemark.getText().toString().trim();
        String truckId="";
        if (deliveryType == 1) {
            // 物流信息 备注信息放空
            company="";
            ordernum="";
            remark="";
            truckId=vechicleListBean.getData().get(VechicleLsitposition).getId()+"";
        } else if (deliveryType == 2) {
            if("".equals(company)){
                ToastUtil.setToast("请输入物流公司名称");
                return;
            }
            if("".equals(ordernum)){
                ToastUtil.setToast("请输入物流公单号");
                return;
            }
            //车辆信息  备注信息放空
            truckId="";
            remark="";
        } else {
            if("".equals(remark)){
                ToastUtil.setToast("请填写发货备注信息");
                return;
            }
            //车辆信息 物流信息放空
            truckId="";
            company="";
            ordernum="";

        }
        sendGoods(company,sendGoodsDetailsBean.getData().getId()+"",remark,ordernum,truckId,deliveryType+"");
    }
    //发货
    private void sendGoods(String logisticsName, String ordersId, String remark, String tplNo, String truck_id, String types) {
        String sign = MD5Util.encode("logisticsName=" + logisticsName + "&ordersId=" + ordersId + "&remark=" + remark + "&tplNo=" + tplNo + "&truckId=" + truck_id + "&types=" + types);
        RxHttpUtils.createApi(FinishedProductApi.class)
                .sendGoods(logisticsName, ordersId, remark, tplNo, truck_id, types, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean bean) {
                        mTypePopuWindow.dismiss();
                        if (bean.getCode() == 200) {
                            showOkDialog();
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                            showFailDialog();
                            finish();
                        }
                    }
                });
    }
}