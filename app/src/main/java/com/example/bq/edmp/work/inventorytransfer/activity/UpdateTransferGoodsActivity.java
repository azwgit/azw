package com.example.bq.edmp.work.inventorytransfer.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MoneyUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.inventorytransfer.adapter.AllListPackageAdp;
import com.example.bq.edmp.work.inventorytransfer.adapter.VarietiesAdp;
import com.example.bq.edmp.work.inventorytransfer.api.AllocationApi;
import com.example.bq.edmp.work.inventorytransfer.bean.AllpackageListBean;
import com.example.bq.edmp.work.inventorytransfer.bean.GoodsDetailsBean;
import com.example.bq.edmp.work.inventorytransfer.bean.VarittiesListBean;

import butterknife.BindView;

public class UpdateTransferGoodsActivity extends BaseTitleActivity {
    public static void newIntent(Context context, String inItemId, String id) {
        Intent intent = new Intent(context, UpdateTransferGoodsActivity.class);
        intent.putExtra(Constant.CODE, inItemId);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.btn_ok)
    TextView mBtnOk;
    @BindView(R.id.btn_del)
    TextView mBtnDel;
    @BindView(R.id.tv_transfer_number)
    TextView mTvTransferNumber;
    @BindView(R.id.ed_number)
    EditText mEdNumber;

    private ILoadingView loading_dialog;
    private AllpackageListBean allpackageListBean;
    private VarittiesListBean varittiesListBean;
    PopupWindow mTypePopuWindow;
    private String inItemId = "";
    private String id = "";

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBtnOk.setOnClickListener(this);
        mBtnDel.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("修改调拨商品");
        inItemId = getIntent().getStringExtra(Constant.CODE);
        id = getIntent().getStringExtra(Constant.ID);
        if ("".equals(inItemId) || "".equals(id)) {
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        mTvTransferNumber.setTextColor(getResources().getColor(R.color.color33));
        mTvTransferNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        getGoodsDetails();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_transfer_goods;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                checkData();
                break;
            case R.id.btn_del:
                finish();
                break;
        }

    }

    //获取商品详情
    private void getGoodsDetails() {
        String sign = MD5Util.encode("inItemId=" +inItemId+"&stockAllotId=" + id);
        RxHttpUtils.createApi(AllocationApi.class)
                .getGoodsDetails(inItemId,id,sign)
                .compose(Transformer.<GoodsDetailsBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<GoodsDetailsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(GoodsDetailsBean bean) {
                        if (bean.getCode() == 200) {
                            setData(bean.getData());
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }
    //详情赋值
    private void setData(GoodsDetailsBean.DataBean bean){
        mTvTransferNumber.setText(bean.getVarietyName());
        mEdNumber.setText(MoneyUtils.formatMoney(bean.getQty()));
    }
    //验证是否添加重量
    private void checkData(){
        String number=mEdNumber.getText().toString().trim();
        if("".equals(number)){
            ToastUtil.setToast("请输入调拨数量");
            return;
        }
        addTransferGoods(number);
    }
    //修改调拨商品
    private void addTransferGoods(String number) {
        String sign = MD5Util.encode("inItemId=" + inItemId + "&qty=" + number+ "&stockAllotId=" + id);
        RxHttpUtils.createApi(AllocationApi.class)
                .updateTransferGoods(inItemId+"",number,id,sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }
                    @Override
                    protected void onSuccess(BaseABean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtil.setToast("修改成功");
                            finish();
                        } else {
                            ToastUtil.setToast(bean.getMsg());
                        }
                    }
                });
    }
}