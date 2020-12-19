package com.example.bq.edmp.work.allocation;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.activity.ApplyPayAccountAct;
import com.example.bq.edmp.activity.apply.travel.activity.ApplyTravelAccountAct;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.work.allocation.adapter.AllocationApplyListAdapter;
import com.example.bq.edmp.work.allocation.api.AllocationApi;
import com.example.bq.edmp.work.allocation.bean.BaseAllocationBeam;
import com.example.bq.edmp.work.inventorytransfer.activity.AddFinishedProductAllocationActivity;
import com.example.bq.edmp.work.inventorytransfer.activity.EditFinishedProductAllocationActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 申请调拨
 * */
public class AllocationApplyActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.apply_button_tv)
    TextView apply_button_tv;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.xr)
    XRecyclerView xRecyclerView;


    private ILoadingView loading_dialog;
    private ArrayList<BaseAllocationBeam.DataBean.RowsBean> rowsBeans;
    private AllocationApplyListAdapter allocationApplyListAdapter;

    private int currentPager = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_allocation_apply;
    }

    @Override
    protected void initView() {
        ProApplication.getinstance().addActivity(AllocationApplyActivity.this);
        title_tv.setText("调拨申请");
        loading_dialog = new LoadingDialog(this);

        rowsBeans = new ArrayList<>();
        allocationApplyListAdapter = new AllocationApplyListAdapter(rowsBeans);
        xRecyclerView.setAdapter(allocationApplyListAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(AllocationApplyActivity.this));
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
        allocationApplyListAdapter.setOnItemClickListener(new AllocationApplyListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, BaseAllocationBeam.DataBean.RowsBean rowsBean) {
                // 1原粮  2  成品
                if("1".equals(rowsBean.getTypes())){
                    EditFinishedProductAllocationActivity.newIntent(getApplicationContext(),"1",rowsBean.getId());
                }else{
                    EditFinishedProductAllocationActivity.newIntent(getApplicationContext(),"2",rowsBean.getId());
                }
            }
        });

    }

    @Override
    protected void initData() {
        gainData();
    }

    //获取列表数据
    private void gainData() {

        currentPager = 1;

        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(AllocationApi.class)
                .getAllocationDTJ(currentPager, 15, sign)
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
                                allocationApplyListAdapter.notifyDataSetChanged();
                            } else {
                                wsj.setVisibility(ViewGroup.VISIBLE);
                                xRecyclerView.setVisibility(ViewGroup.GONE);
                                rowsBeans.clear();
                                allocationApplyListAdapter.notifyDataSetChanged();
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

    //获取列表更多数据
    private void initData2() {
        String sign = MD5Util.encode("page=" + currentPager + "&pagerow=" + 15);

        RxHttpUtils.createApi(AllocationApi.class)
                .getAllocationDTJ(currentPager, 15, sign)
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
                            allocationApplyListAdapter.addMoreData(rows);
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
        apply_button_tv.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.apply_button_tv:
                showAllocationApplyDialog();
                break;
        }
    }

    private void showAllocationApplyDialog() {
            final Dialog mCameraDialog = new Dialog(view.getContext(), R.style.my_dialog);

            View root = LayoutInflater.from(view.getContext()).inflate(R.layout.gestures_dialog, null);

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
            lp.alpha = 2f; // 透明度
            dialogWindow.setAttributes(lp);
            mCameraDialog.show();

            TextView passwordlogin_tv = root.findViewById(R.id.passwordlogin_tv);
            TextView authcodelogin_tv = root.findViewById(R.id.authcodelogin_tv);
            TextView sllogin_tv = root.findViewById(R.id.sllogin_tv);
            TextView cancel_tv = root.findViewById(R.id.cancel_tv);


            passwordlogin_tv.setText("申请调拨类型");
            TextPaint paint = passwordlogin_tv.getPaint();
            paint.setFakeBoldText(true);
            authcodelogin_tv.setText("成品调拨");
            sllogin_tv.setText("原粮调拨");


            //成品调拨
            authcodelogin_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddFinishedProductAllocationActivity.newIntent(getApplicationContext(),"2");
                    mCameraDialog.dismiss();
                }
            });
            //原粮调拨
            sllogin_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddFinishedProductAllocationActivity.newIntent(getApplicationContext(),"1");
                    mCameraDialog.dismiss();
                }
            });
            cancel_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCameraDialog.dismiss();
                }
            });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProApplication.getinstance().finishActivity(AllocationApplyActivity.this);
    }
}
