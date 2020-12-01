package com.example.bq.edmp.work.grainmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.utils.Constant;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.grainmanagement.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.adapter.DetailsDetectionListAdp;
import com.example.bq.edmp.work.grainmanagement.adapter.WareHousingDetailsDetectionListAdp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 入库详情
 */
public class WarehousingOutDetailAct extends BaseTitleActivity {
    public static void newIntent(Context context, String id) {
        Intent intent = new Intent(context, WarehousingOutDetailAct.class);
        intent.putExtra(Constant.ID, id);
        context.startActivity(intent);
    }
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.ly_one)
    LinearLayout mLyOne;//检测信息父布局
    @BindView(R.id.ly_two)
    LinearLayout mLyTwo;//调拨信息父布局
    @BindView(R.id.tv_number)
    TextView mTvNumber;//收购单号
    @BindView(R.id.tv_status)
    TextView mTvStatus;//出库类型
    @BindView(R.id.tv_contractor)
    TextView mTvContractor;//分子公司名称
    @BindView(R.id.tv_warehouse)
    TextView mTvWarehouse;//出库仓库
    @BindView(R.id.tv_varieties)
    TextView mTvVarieties;//品种
    @BindView(R.id.tv_gross_weight)
    TextView mTvGrossWeight;//出库量
    @BindView(R.id.tv_time)
    TextView mTvTime;//出库日期
    @BindView(R.id.tv_transfer_warehouse)
    TextView mTvTransferWarehouse;//调入仓库
    @BindView(R.id.tv_transfer_reason)
    TextView mTvTransferReason;//调拨原因



    private WareHousingDetailsDetectionListAdp wareHousingDetailsDetectionListAdp;
    private String id="";
    private ILoadingView loading_dialog;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_warehousing_out_detail;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("出库详情");
        id=getIntent().getStringExtra(Constant.ID);
        if("".equals(id)){
            ToastUtil.setToast("数据出错请重试");
            return;
        }
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        List<PayInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PayInfoBean bean = new PayInfoBean();
            bean.setId(i + "");
            bean.setDesc("啊啊啊啊");
            list.add(bean);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        wareHousingDetailsDetectionListAdp = new WareHousingDetailsDetectionListAdp(list);
        mRecyclerView.setAdapter(wareHousingDetailsDetectionListAdp);
        getAcquisitionDetail();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void otherViewClick(View view) {

    }
    //获取入庫详情
    private void getAcquisitionDetail() {
        String sign = MD5Util.encode("id="+id);
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .getWareHousingOutDetail(id, sign)
                .compose(Transformer.<BaseABean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean loginBean) {
                        ToastUtil.setToast(loginBean.getMsg());
                    }
                });
    }
}
