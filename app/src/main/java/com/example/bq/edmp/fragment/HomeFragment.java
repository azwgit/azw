package com.example.bq.edmp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.example.bq.edmp.R;
import com.example.bq.edmp.base.BaseFragment;
import com.example.bq.edmp.base.ServiceAndDemonstrateFieldWebViewActivity;
import com.example.bq.edmp.home.activity.MessageNotificationListActivity;
import com.example.bq.edmp.home.activity.UploadImageActivity;
import com.example.bq.edmp.home.adapter.HomeJournalismAdapter;
import com.example.bq.edmp.home.api.HomeApi;
import com.example.bq.edmp.home.bean.HomeBean;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.LogUtils;
import com.example.bq.edmp.utils.MD5Util;
import com.example.bq.edmp.utils.MyLoader;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.TurnImgStringUtils;
import com.example.bq.edmp.work.messagenotification.activity.MessageManagementListActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 首页
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.home_banner)
    Banner home_banner;
    @BindView(R.id.r_c_tv)
    TextView r_c_tv;
    @BindView(R.id.s_x_tv)
    TextView s_x_tv;
    @BindView(R.id.t_z_tv)
    TextView t_z_tv;
    @BindView(R.id.gd_tv)
    TextView gd_tv;
    @BindView(R.id.home_xr)
    RecyclerView home_xr;
    @BindView(R.id.one_img)
    ImageView one_img;
    @BindView(R.id.two_img)
    ImageView two_img;
    @BindView(R.id.three_img)
    ImageView three_img;
    @BindView(R.id.four_img)
    ImageView four_img;
    @BindView(R.id.five_img)
    ImageView five_img;
    @BindView(R.id.one_tv)
    TextView one_tv;
    @BindView(R.id.two_tv)
    TextView two_tv;
    @BindView(R.id.three_tv)
    TextView three_tv;
    @BindView(R.id.four_tv)
    TextView four_tv;
    @BindView(R.id.five_tv)
    TextView five_tv;
    @BindView(R.id.ly_message)
    LinearLayout ly_message;


    private LoadingDialog loading_dialog;
    private List<HomeBean.DataBean.ArticlesBean> articlesBeans;
    private HomeJournalismAdapter homeJournalismAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        loading_dialog = new LoadingDialog(getActivity());

        articlesBeans = new ArrayList<>();
        homeJournalismAdapter = new HomeJournalismAdapter(articlesBeans);
        //禁止垂直滑动
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        home_xr.setLayoutManager(layoutManager);
        home_xr.setAdapter(homeJournalismAdapter);
        homeJournalismAdapter.setOnItemLeftClckListener(new HomeJournalismAdapter.OnItemLeftClckListener() {
            @Override
            public void onItemLeftClck(HomeBean.DataBean.ArticlesBean articlesBean, int mPosition) {
                Intent intent = ServiceAndDemonstrateFieldWebViewActivity.newIntent(getActivity(), "http://192.168.0.188:8010/dist/index.html#/Detail?id=" + articlesBean.getId(), "");
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    protected void initListener() {
        gd_tv.setOnClickListener(this);
        ly_message.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        gainData();
    }

    private void gainData() {

        RxHttpUtils.createApi(HomeApi.class)
                .getHomeData()
                .compose(Transformer.<HomeBean>switchSchedulers(loading_dialog))
                .subscribe(new NewCommonObserver<HomeBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(HomeBean homeBean) {
                        if (homeBean.getCode() == 200) {
                            assignment(homeBean.getData());
                        } else {
                            ToastUtil.setToast(homeBean.getMsg());
                        }
                    }
                });
    }

    private void assignment(HomeBean.DataBean data) {
        //轮播
        List<HomeBean.DataBean.BannersBean> banners = data.getBanners();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < banners.size(); i++) {
            list.add(BaseApi.banner_img_url + TurnImgStringUtils.isImgPath(banners.get(i).getImgUri()));
        }

        if (home_banner != null) {
            home_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置图片加载器
            home_banner.setImageLoader(new MyLoader());
            //设置图片网址或地址的集合
            home_banner.setImages(list);
            //设置轮播图的标题集合
            //homeBanner.setBannerTitles(list_title);
            //设置轮播间隔时间
            home_banner.setDelayTime(3000);
            //设置是否为自动轮播，默认是“是”。
            home_banner.isAutoPlay(true);
            //设置指示器的位置，小点点，左中右。
            home_banner.setIndicatorGravity(BannerConfig.RIGHT)
                    //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {

                        }
                    })
                    //必须最后调用的方法，启动轮播图。
                    .start();
        }

        //今日日程
        if (data.getTodayCalendar() != null && !data.getTodayCalendar().equals("")) {
            r_c_tv.setText(data.getTodayCalendar());
        } else {
            r_c_tv.setText("0");
        }
        //待办事项
        if (data.getBacklogCount() != null && !data.getBacklogCount().equals("")) {
            s_x_tv.setText(data.getBacklogCount());
        } else {
            s_x_tv.setText("0");
        }
        //消息通知
        if (data.getMessageCount() != null && !data.getMessageCount().equals("")) {
            t_z_tv.setText(data.getMessageCount());
        } else {
            t_z_tv.setText("0");
        }

        //底部新闻
        List<HomeBean.DataBean.ArticlesBean> articles = data.getArticles();
        if (articles.size() > 0) {
            articlesBeans.addAll(articles);
            homeJournalismAdapter.notifyDataSetChanged();
        }

        //常用功能
        List<HomeBean.DataBean.FunctionsBean> functions = data.getFunctions();
        if (functions != null && functions.size() > 0) {
            if (functions.get(0).getName() != null && !functions.get(0).getName().equals("")) {
                one_tv.setText(functions.get(0).getName());
            }
            if (functions.get(1).getName() != null && !functions.get(1).getName().equals("")) {
                two_tv.setText(functions.get(1).getName());
            }
            if (functions.get(2).getName() != null && !functions.get(2).getName().equals("")) {
                three_tv.setText(functions.get(2).getName());
            }
            if (functions.get(3).getName() != null && !functions.get(3).getName().equals("")) {
                four_tv.setText(functions.get(3).getName());
            }
            if (functions.get(4).getName() != null && !functions.get(4).getName().equals("")) {
                five_tv.setText(functions.get(4).getName());
            }
        }

    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.gd_tv:
                Intent intent = ServiceAndDemonstrateFieldWebViewActivity.newIntent(getActivity(), "http://192.168.0.188:8010/dist/index.html#/Party", "");
                getActivity().startActivity(intent);
                break;
            case R.id.ly_message:
                startActivity(new Intent(getContext(), MessageManagementListActivity.class));
                break;


        }
    }

}
