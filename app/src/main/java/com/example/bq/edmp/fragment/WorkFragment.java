package com.example.bq.edmp.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseFragment;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.activity.AuditActivity;
import com.example.bq.edmp.word.activity.SubmitActivity;
import com.example.bq.edmp.word.adapter.LeftAdapter;
import com.example.bq.edmp.word.adapter.RightAdapter;
import com.example.bq.edmp.word.api.WordListApi;
import com.example.bq.edmp.word.bean.FirstResult;
import com.example.bq.edmp.word.bean.SecondResult;
import com.example.bq.edmp.word.inventory.InventoryActivity;
import com.example.bq.edmp.word.purchase.PurchaseListActivity;
import com.example.bq.edmp.word.put_warehouse.Put_WarehouseActivity;
import com.example.bq.edmp.work.allocation.AllocationApplyActivity;
import com.example.bq.edmp.work.allocation.AllocationApprovalActivity;
import com.example.bq.edmp.work.allocation.AllocationCompleteActivity;
import com.example.bq.edmp.work.allocation.ApprovalInActivity;
import com.example.bq.edmp.work.detection.DetectionRecordListActivity;
import com.example.bq.edmp.work.detection.NewDetectionActivity;
import com.example.bq.edmp.work.finished.DmachineActivity;
import com.example.bq.edmp.work.finished.JmachineActivity;
import com.example.bq.edmp.work.finished.YmachineActivity;
import com.example.bq.edmp.work.grainmanagement.activity.NewAcquisitionsActivity;
import com.example.bq.edmp.work.grainmanagement.activity.StartWeighingActivity;
import com.example.bq.edmp.work.library.activity.ClibraryActivity;
import com.example.bq.edmp.work.library.activity.CxlibraryActivity;
import com.example.bq.edmp.work.library.activity.RlibraryActivity;
import com.example.bq.edmp.work.marketing.activity.CustomerAccountListActivity;
import com.example.bq.edmp.work.marketing.activity.CustomerInquirytListActivity;
import com.example.bq.edmp.work.marketing.activity.CustomerManagementListActivity;
import com.example.bq.edmp.work.marketingactivities.activity.HistoricalActivitiesListActivity;
import com.example.bq.edmp.work.marketingactivities.activity.MarketingActivityManagementListActivity;
import com.example.bq.edmp.work.order.activity.HistoryOrderActivity;
import com.example.bq.edmp.work.order.activity.OrderTrackingActivity;
import com.example.bq.edmp.work.order.activity.Order_GL_Activity;
import com.example.bq.edmp.work.shipments.DshipmentsActivity;
import com.example.bq.edmp.work.shipments.YshipmentsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 工作
 */
public class WorkFragment extends BaseFragment {


    @BindView(R.id.left_recycler)
    RecyclerView left_recycler;
    @BindView(R.id.right_recycler)
    RecyclerView right_recycler;

    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    private boolean a = false;

    public WorkFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_word;
    }

    @Override
    protected void initView() {

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initData() {

        getFirstCategory();//获取以及分类
    }

    private void getFirstCategory() {
        RxHttpUtils.createApi(WordListApi.class)
                .getFirst()
                .compose(Transformer.<FirstResult>switchSchedulers())
                .subscribe(new NewCommonObserver<FirstResult>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(FirstResult firstResult) {
                        final List<FirstResult.DataBean> data = firstResult.getData();
                        if (firstResult.getCode() == 200) {
                            leftAdapter = new LeftAdapter(getActivity());
                            //布局
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            left_recycler.setLayoutManager(linearLayoutManager);
                            left_recycler.setAdapter(leftAdapter);

                            data.get(0).setSelected(true);//默认第一个选中
                            leftAdapter.setList(data);

                            //点击左侧实现右侧商品信息展示
                            leftAdapter.setOnItemLeftClckListener(new LeftAdapter.OnItemLeftClckListener() {
                                @Override
                                public void onItemLeftClck(int position) {
                                    //当点击时显示当前条目的背景和文字的颜色
                                    for (int i = 0; i < data.size(); i++) {
                                        if (position == i) {
                                            data.get(i).setSelected(true);
                                        } else {
                                            data.get(i).setSelected(false);
                                        }
                                    }
                                    initData2(data.get(position).getId());
                                    //更新视图(必须有)
                                    leftAdapter.notifyDataSetChanged();
                                }
                            });

                            if (!a) {
                                initData2(data.get(0).getId());
                            }

                        } else {

                        }
                    }
                });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {

        }
    }

    private void initData2(String id) {
        String sign = MD5Util.encode("id=" + id);
        RxHttpUtils.createApi(WordListApi.class)
                .getSecond(id, sign)
                .compose(Transformer.<SecondResult>switchSchedulers())
                .subscribe(new NewCommonObserver<SecondResult>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SecondResult secondResult) {
                        int code = secondResult.getCode();
                        if (code == 200) {
                            if (secondResult.getData() != null && secondResult.getData().size() != 0) {
                                rightAdapter = new RightAdapter(getActivity());
                                //布局
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                right_recycler.setLayoutManager(linearLayoutManager);
                                for (int i = 0; i < secondResult.getData().size(); i++) {
                                    if (null == secondResult.getData().get(i) || secondResult.getData().get(i).getSubt().size() <= 0) {
                                        secondResult.getData().remove(i);
                                        i--;
                                    }
                                }
                                right_recycler.setAdapter(rightAdapter);
                                rightAdapter.setList(secondResult.getData());
                                rightAdapter.setOnInterface(new RightAdapter.OnInterface() {
                                    @Override
                                    public void OnCilkeface(SecondResult.DataBean.SubtBean subtBean, int position) {
                                        if (subtBean.getAccessUri() != null && !subtBean.getAccessUri().equals("")) {
                                            if (subtBean.getAccessUri().equals("/approvals")) {//审批管理
                                                startActivity(new Intent(getActivity(), AuditActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/reimburser")) {//报账管理
                                                startActivity(new Intent(getActivity(), SubmitActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/grain/purchase/addnew")) { //新增收购
                                                startActivity(new Intent(getActivity(), NewAcquisitionsActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/grain/purchase/grossweight")) {//称重（毛重）
                                                StartWeighingActivity.newIntent(getActivity(), "称重毛重");
                                            } else if (subtBean.getAccessUri().equals("/grain/purchase/checked")) {//卸货验证
                                                StartWeighingActivity.newIntent(getActivity(), "卸货验证");
                                            } else if (subtBean.getAccessUri().equals("/grain/purchase/tare")) {//称重（皮重）
                                                StartWeighingActivity.newIntent(getActivity(), "称重皮重");
                                            } else if (subtBean.getAccessUri().equals("/grain/purchase")) {//收购记录
                                                startActivity(new Intent(getActivity(), PurchaseListActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/grain/purchase/statistics")) {//统计
                                                ToastUtil.setToast("统计,暂未开通");
                                            } else if (subtBean.getAccessUri().equals("/grain/addstock")) {//原粮管理====入库
                                                Intent intent = new Intent(getActivity(), Put_WarehouseActivity.class);
                                                intent.putExtra("WarehouseType", 1);
                                                startActivity(intent);
                                            } else if (subtBean.getAccessUri().equals("/grain/substock")) {//原粮管理====出库
                                                Intent intent = new Intent(getActivity(), Put_WarehouseActivity.class);
                                                intent.putExtra("WarehouseType", 2);
                                                startActivity(intent);
                                            } else if (subtBean.getAccessUri().equals("/grain/stock")) {//原粮管理====库存查询
                                                startActivity(new Intent(getActivity(), InventoryActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/product/process/accept")) {//成品管理====任务接受
                                                startActivity(new Intent(getActivity(), DmachineActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/product/processing")) {//成品管理====加工中
                                                startActivity(new Intent(getActivity(), JmachineActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/product/process/finished")) {//成品管理====加工完成
                                                startActivity(new Intent(getActivity(), YmachineActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/product/process/record")) {//成品管理====加工记录
                                                ToastUtil.setToast("加工记录,暂未开通");
                                            } else if (subtBean.getAccessUri().equals("/product/order/sendout")) {//发货管理====发货
                                                startActivity(new Intent(getActivity(), DshipmentsActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/product/order/sendout/record")) {//发货管理====发货记录
                                                startActivity(new Intent(getActivity(), YshipmentsActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/product/addstock")) {//成品管理====入库
                                                startActivity(new Intent(getActivity(), RlibraryActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/product/substock")) {//成品管理====出库
                                                startActivity(new Intent(getActivity(), ClibraryActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/product/stock")) {//成品管理====库存查询
                                                startActivity(new Intent(getActivity(), CxlibraryActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/allot/tosubmit")) {//库存调拨====申请调拨
                                                startActivity(new Intent(getActivity(), AllocationApplyActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/allot/accomplish")) {//库存调拨====已完成
                                                startActivity(new Intent(getActivity(), AllocationCompleteActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/allot/approval")) {//库存调拨====审批中
                                                startActivity(new Intent(getActivity(), AllocationApprovalActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/allot/allotcentre")) {//库存调拨====调拨中
                                                startActivity(new Intent(getActivity(), ApprovalInActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/testing/newsave")) {//质量管理====新增检测
                                                startActivity(new Intent(getActivity(), NewDetectionActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/testing/list")) {//质量管理====检测记录
                                                startActivity(new Intent(getActivity(), DetectionRecordListActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/customer/newsave")) {//营销管理====客户管理
                                                startActivity(new Intent(getActivity(), CustomerManagementListActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/customer/list")) {//营销管理====客户查询
                                                startActivity(new Intent(getActivity(), CustomerInquirytListActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/customer/account/list")) {//营销管理====账户查询
                                                startActivity(new Intent(getActivity(), CustomerAccountListActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/order/submitlist")) {//订单管理
                                                startActivity(new Intent(getActivity(), Order_GL_Activity.class));
                                            } else if (subtBean.getAccessUri().equals("/order/tracklist")) {//订单跟踪
                                                startActivity(new Intent(getActivity(), OrderTrackingActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/order/historylist")) {//历史订单
                                                startActivity(new Intent(getActivity(), HistoryOrderActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/activity/newsave")) {//活动申请
                                                startActivity(new Intent(getActivity(), MarketingActivityManagementListActivity.class));
                                            } else if (subtBean.getAccessUri().equals("/activity/history")) {//历史活动
                                                startActivity(new Intent(getActivity(), HistoricalActivitiesListActivity.class));
                                            } else {
                                                ToastUtil.setToast("暂未开通");
                                            }
                                        } else {
                                            ToastUtil.setToast("点击错误");
                                        }
                                    }
                                });
                            }
                        } else {

                        }
                    }
                });
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
