package com.example.bq.edmp.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseFragment;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.word.activity.AuditActivity;
import com.example.bq.edmp.word.activity.SubmitActivity;
import com.example.bq.edmp.word.adapter.LeftAdapter;
import com.example.bq.edmp.word.adapter.RightAdapter;
import com.example.bq.edmp.word.api.WordListApi;
import com.example.bq.edmp.word.bean.FirstResult;
import com.example.bq.edmp.word.bean.SecondResult;

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
                .subscribe(new CommonObserver<FirstResult>() {
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
                .subscribe(new CommonObserver<SecondResult>() {
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
                                right_recycler.setAdapter(rightAdapter);
                                rightAdapter.setList(secondResult.getData());

                                rightAdapter.setOnInterface(new RightAdapter.OnInterface() {
                                    @Override
                                    public void OnCilkeface(SecondResult.DataBean.SubtBean subtBean, int position) {
                                        if (subtBean.getName().equals("审批管理")) {
                                            startActivity(new Intent(getActivity(), AuditActivity.class));
                                        } else if (subtBean.getName().equals("报账管理")) {
                                            startActivity(new Intent(getActivity(), SubmitActivity.class));
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
