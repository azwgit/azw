package com.example.bq.edmp.work.inventorytransfer.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.ApprovalAdp;
import com.example.bq.edmp.base.BaseTitleActivity;
import com.example.bq.edmp.bean.PayInfoBean;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.grainmanagement.api.RawGrainManagementApi;
import com.example.bq.edmp.work.grainmanagement.bean.ContractorListBean;
import com.example.bq.edmp.work.inventorytransfer.adapter.VarietiesNameListAdp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FinishedProductAllocationDetailsActivity extends BaseTitleActivity {

    @BindView(R.id.approval_recyclerview)
    RecyclerView mApprovalRecyclerView;//审批状态适配器
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;//调拨商品适配器
    @BindView(R.id.tv_title)
    TextView mTvTitle;//调拨单号
    @BindView(R.id.tv_company)
    TextView mTvCompany;//公司名称
    @BindView(R.id.tv_stuats)
    TextView mTvStuats;//调拨单状态
    @BindView(R.id.tv_name)
    TextView mTvName;//操作人
    @BindView(R.id.tv_time)
    TextView mTvTime;//调拨人
    @BindView(R.id.tv_transfer_out)
    TextView mTvTransferOut;//调出信息
    @BindView(R.id.tv_transfer_in)
    TextView mTvTransferIn;//调入信息
    @BindView(R.id.tv_out_order_no)
    TextView mTvDeliveryOrderNo;//出库单号
    @BindView(R.id.tv_in_order_no)
    TextView mTvInOrderNo;//入库单号
    @BindView(R.id.tv_completion_time)
    TextView mTvCompletionTime;//完成时间
    @BindView(R.id.tv_content)
    TextView mTvContent;//调拨原因

    private ILoadingView loading_dialog;
    private String id="";
    private ContractorListBean contractorListBean;//承包人数据源
    private ApprovalAdp mApprovalAdapter;
    private VarietiesNameListAdp varietiesNameListAdp;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_finished_product_allocation_details;
    }

    @Override
    protected void initView() {
        txtTabTitle.setText("成品调拨单详情");
        ProApplication.getinstance().addActivity(this);
        loading_dialog = new LoadingDialog(this);
        List<PayInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PayInfoBean bean = new PayInfoBean();
            bean.setId(i + "");
            bean.setDesc("啊啊啊啊");
            list.add(bean);
        }

        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        varietiesNameListAdp = new VarietiesNameListAdp(null);
        recycler_view.setAdapter(varietiesNameListAdp);
        mApprovalRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mApprovalAdapter = new ApprovalAdp(list);
        mApprovalRecyclerView.setAdapter(mApprovalAdapter);
        getContractorList();
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
    //获取承包人列表
    private void getContractorList() {
        RxHttpUtils.createApi(RawGrainManagementApi.class)
                .getContractorList()
                .compose(Transformer.<ContractorListBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<ContractorListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(ContractorListBean bean) {
                        List <ContractorListBean.DataBean> list=new ArrayList<ContractorListBean.DataBean>();
                        for(int i=0;i<3;i++){
                            ContractorListBean.DataBean dataBean=new ContractorListBean.DataBean();
                            dataBean.setName("测试");
                            list.add(dataBean);
                        }
                        varietiesNameListAdp.setNewData(list);
                    }
                });
    }
}