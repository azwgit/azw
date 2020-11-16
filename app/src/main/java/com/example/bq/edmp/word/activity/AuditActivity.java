package com.example.bq.edmp.word.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.bean.LoginBean;
import com.example.bq.edmp.utils.BadgeView;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.adapter.AuditListAdapter;
import com.example.bq.edmp.word.adapter.SubmitListAdapter;
import com.example.bq.edmp.word.api.WordListApi;
import com.example.bq.edmp.word.bean.AuditDspNumberBean;
import com.example.bq.edmp.word.bean.AuditListBean;
import com.example.bq.edmp.word.bean.SubmitListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
* 审核管理
* */
public class AuditActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.wsj)
    TextView wsj;
    @BindView(R.id.return_img)
    ImageView return_img;
    @BindView(R.id.dsp_rb)
    RadioButton dsp_rb;
    @BindView(R.id.wsp_rb)
    RadioButton wsp_rb;
    @BindView(R.id.wfq_rb)
    RadioButton wfq_rb;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.xr)
    XRecyclerView mRecyclerView;

    private int TYPE = 1;
    private int currentPager = 1;
    private ArrayList<AuditListBean.RowsBean> rowsBeans;
    private AuditListAdapter auditListAdapter;
    private BadgeView badgeView;

    @Override
    protected void initListener() {
        return_img.setOnClickListener(this);
        rg.setOnClickListener(this);
        dsp_rb.setOnClickListener(this);
        wsp_rb.setOnClickListener(this);
        wfq_rb.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        title_tv.setText("审批管理");
        dsp_rb.setChecked(true);

        badgeView = new BadgeView(AuditActivity.this);

        rowsBeans = new ArrayList<>();
        auditListAdapter = new AuditListAdapter(rowsBeans);
        mRecyclerView.setAdapter(auditListAdapter);
        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(AuditActivity.this));
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                if (TYPE == 1) {
                    initData();
                } else if (TYPE == 2) {
                    gainWSP();
                } else if (TYPE == 3) {
                    gainWFQ();
                }
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++currentPager;
                initData2();
                mRecyclerView.loadMoreComplete();
            }
        });

        auditListAdapter.setOnInterface(new AuditListAdapter.OnInterface() {
            @Override
            public void OnCilkeface(AuditListBean.RowsBean rowsBean, int position) {
                ToastUtil.setToast(rowsBean.getPromoter());
            }
        });

    }

    //获取待审批数量
    private void gainDspNumber() {
        RxHttpUtils.createApi(WordListApi.class)
                .getDspNumber()
                .compose(Transformer.<AuditDspNumberBean>switchSchedulers())
                .subscribe(new CommonObserver<AuditDspNumberBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AuditDspNumberBean auditDspNumberBean) {
                        String data = auditDspNumberBean.getData();
                        if (!data.equals("") && data != null) {
                            badgeView.setBadgeCount(Integer.parseInt(data));
                            badgeView.setBadgeGravity(Gravity.RIGHT | Gravity.TOP);
                            badgeView.setTargetView(dsp_rb);
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_audit;
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                fund();
                break;
            case R.id.dsp_rb:
                TYPE = 1;
                wsp_rb.setChecked(false);
                wfq_rb.setChecked(false);
                initData();
                break;
            case R.id.wsp_rb:
                TYPE = 2;
                dsp_rb.setChecked(false);
                wfq_rb.setChecked(false);
                gainWSP();
                break;
            case R.id.wfq_rb:
                TYPE = 3;
                wsp_rb.setChecked(false);
                dsp_rb.setChecked(false);
                gainWFQ();
                break;
        }
    }

    //我发起的
    private void gainWFQ() {
        currentPager = 1;
        String sign = MD5Util.encode(
                "page=" + currentPager
                        + "&pagerow=" + 10);

        RxHttpUtils.createApi(WordListApi.class)
                .getWfq(currentPager, 10, sign)
                .compose(Transformer.<AuditListBean>switchSchedulers())
                .subscribe(new CommonObserver<AuditListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AuditListBean auditListBean) {
                        List<AuditListBean.RowsBean> rows = auditListBean.getRows();
                        if (rows != null && rows.size() != 0) {
                            rowsBeans.clear();
                            rowsBeans.addAll(rows);
                            auditListAdapter.notifyDataSetChanged();
                            mRecyclerView.setVisibility(ViewGroup.VISIBLE);
                            wsj.setVisibility(ViewGroup.GONE);
                        } else {
                            mRecyclerView.setVisibility(ViewGroup.GONE);
                            wsj.setVisibility(ViewGroup.VISIBLE);
                        }

                    }
                });
    }

    //我审批的
    private void gainWSP() {
        currentPager = 1;
        String sign = MD5Util.encode(
                "page=" + currentPager
                        + "&pagerow=" + 10);

        RxHttpUtils.createApi(WordListApi.class)
                .getWsp(currentPager, 10, sign)
                .compose(Transformer.<AuditListBean>switchSchedulers())
                .subscribe(new CommonObserver<AuditListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AuditListBean auditListBean) {
                        List<AuditListBean.RowsBean> rows = auditListBean.getRows();
                        if (rows != null && rows.size() != 0) {
                            rowsBeans.clear();
                            rowsBeans.addAll(rows);
                            auditListAdapter.notifyDataSetChanged();
                            mRecyclerView.setVisibility(ViewGroup.VISIBLE);
                            wsj.setVisibility(ViewGroup.GONE);
                        } else {
                            mRecyclerView.setVisibility(ViewGroup.GONE);
                            wsj.setVisibility(ViewGroup.VISIBLE);
                        }
                    }
                });
    }

    //待审批的
    private void gainDSP() {
        currentPager = 1;
        String sign = MD5Util.encode(
                "page=" + currentPager
                        + "&pagerow=" + 10);

        RxHttpUtils.createApi(WordListApi.class)
                .getDsp(currentPager, 10, sign)
                .compose(Transformer.<AuditListBean>switchSchedulers())
                .subscribe(new CommonObserver<AuditListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AuditListBean auditListBean) {
                        List<AuditListBean.RowsBean> rows = auditListBean.getRows();
                        if (rows != null && rows.size() != 0) {
                            rowsBeans.clear();
                            rowsBeans.addAll(rows);
                            auditListAdapter.notifyDataSetChanged();
                            mRecyclerView.setVisibility(ViewGroup.VISIBLE);
                            wsj.setVisibility(ViewGroup.GONE);
                        } else {
                            mRecyclerView.setVisibility(ViewGroup.GONE);
                            wsj.setVisibility(ViewGroup.VISIBLE);
                        }
                        //获取待审批数量
                        gainDspNumber();
                    }
                });
    }

    @Override
    protected void initData() {
        gainDSP();
    }

    private void initData2() {
        if (TYPE == 1) {
            getDSP2();
        } else if (TYPE == 2) {
            getWSP2();
        } else if (TYPE == 3) {
            getWFQ2();
        }
    }

    private void getWFQ2() {
        String sign = MD5Util.encode(
                "page=" + currentPager
                        + "&pagerow=" + 10);

        RxHttpUtils.createApi(WordListApi.class)
                .getWfq(currentPager, 10, sign)
                .compose(Transformer.<AuditListBean>switchSchedulers())
                .subscribe(new CommonObserver<AuditListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AuditListBean auditListBean) {
                        List<AuditListBean.RowsBean> rows = auditListBean.getRows();
                        if (rows != null && rows.size() != 0) {
                            rowsBeans.addAll(rows);
                            auditListAdapter.addMoreData(rowsBeans);
                        } else {
                            mRecyclerView.setNoMore(true);
                        }
                    }
                });
    }

    private void getWSP2() {
        String sign = MD5Util.encode(
                "page=" + currentPager
                        + "&pagerow=" + 10);

        RxHttpUtils.createApi(WordListApi.class)
                .getWsp(currentPager, 10, sign)
                .compose(Transformer.<AuditListBean>switchSchedulers())
                .subscribe(new CommonObserver<AuditListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AuditListBean auditListBean) {
                        List<AuditListBean.RowsBean> rows = auditListBean.getRows();
                        if (rows != null && rows.size() != 0) {
                            rowsBeans.addAll(rows);
                            auditListAdapter.addMoreData(rowsBeans);
                        } else {
                            mRecyclerView.setNoMore(true);
                        }
                    }
                });
    }

    private void getDSP2() {
        String sign = MD5Util.encode(
                "page=" + currentPager
                        + "&pagerow=" + 10);

        RxHttpUtils.createApi(WordListApi.class)
                .getDsp(currentPager, 10, sign)
                .compose(Transformer.<AuditListBean>switchSchedulers())
                .subscribe(new CommonObserver<AuditListBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(AuditListBean auditListBean) {
                        List<AuditListBean.RowsBean> rows = auditListBean.getRows();
                        if (rows != null && rows.size() != 0) {
                            rowsBeans.addAll(rows);
                            auditListAdapter.addMoreData(rowsBeans);
                        } else {
                            mRecyclerView.setNoMore(true);
                        }
                    }
                });
    }
}
