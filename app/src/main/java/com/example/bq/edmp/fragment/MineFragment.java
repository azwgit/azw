package com.example.bq.edmp.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.interfaces.ILoadingView;
import com.allen.library.observer.CommonObserver;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.bq.edmp.ProApplication;
import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.apply.bean.BaseABean;
import com.example.bq.edmp.http.NewCommonObserver;
import com.example.bq.edmp.login.Control_Login_Activity;
import com.example.bq.edmp.base.BaseFragment;
import com.example.bq.edmp.login.LoginActivity;
import com.example.bq.edmp.login.LoginApi;
import com.example.bq.edmp.mine.activty.Message_Activity;
import com.example.bq.edmp.mine.api.MineApi;
import com.example.bq.edmp.mine.bean.MineUserBean;
import com.example.bq.edmp.url.BaseApi;
import com.example.bq.edmp.utils.LoadingDialog;
import com.example.bq.edmp.utils.SpUtils;
import com.example.bq.edmp.utils.ToastUtil;
import com.example.bq.edmp.utils.TurnImgStringUtils;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 我的baiquan白泉
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.dl_rl)
    RelativeLayout dl_rl;
    @BindView(R.id.gy_rl)
    RelativeLayout gy_rl;
    @BindView(R.id.edit_tv)
    TextView edit_tv;
    @BindView(R.id.user_name_tv)
    TextView user_name_tv;
    @BindView(R.id.user_phone)
    TextView user_phone;
    @BindView(R.id.user_identity_tv)
    TextView user_identity_tv;
    @BindView(R.id.mine_head_img)
    ImageView mine_head_img;
    @BindView(R.id.mine_login_out_img)
    ImageView mine_login_out_img;

    private ILoadingView loading_dialog;


    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        loading_dialog = new LoadingDialog(getActivity());

    }

    @Override
    protected void initData() {
        //获取用户基本信息
        gainUserInformationData();
    }

    private void gainUserInformationData() {
        RxHttpUtils.createApi(MineApi.class)
                .getUserData()
                .compose(Transformer.<MineUserBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<MineUserBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(MineUserBean mineUserBean) {
                        if (mineUserBean.getCode() == 200) {
                            SpUtils.put("UserId", mineUserBean.getData().getId());
                            SpUtils.put("UserPhone", mineUserBean.getData().getMobTel());
                            assignment(mineUserBean.getData());
                        } else {
                            ToastUtil.setToast(mineUserBean.getMsg());
                        }
                    }
                });
    }

    private void assignment(MineUserBean.DataBean data) {
        Glide.with(getActivity())
                .load(BaseApi.head_img_url + TurnImgStringUtils.isImgPath(data.getHeadImg()))
                .apply(new RequestOptions()
                        .dontAnimate()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .circleCrop())
                .into(mine_head_img);
        if (data.getName() != null && !data.getName().equals("")) {
            user_name_tv.setText(data.getName());
        } else {
            user_name_tv.setText("暂无名称");
        }
        if (data.getMobTel() != null && !data.getMobTel().equals("")) {
            user_phone.setText(data.getMobTel());
        } else {
            user_phone.setText("暂无手机号");
        }
        if (data.getTitle() != null && !data.getTitle().equals("")) {
            user_identity_tv.setText(data.getTitle());
        } else {
            user_identity_tv.setText("暂无职位信息");
        }


    }

    @Override
    protected void initListener() {
        edit_tv.setOnClickListener(this);
        dl_rl.setOnClickListener(this);
        gy_rl.setOnClickListener(this);
        mine_login_out_img.setOnClickListener(this);
    }

    @Override
    protected void otherViewClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.edit_tv://编辑功能
                intent = new Intent(ProApplication.getmContext(), Message_Activity.class);
                startActivityForResult(intent, 250);
                break;
            case R.id.dl_rl://登录管理
                startActivity(new Intent(getContext(), Control_Login_Activity.class));
                break;
            case R.id.gy_rl://关于我们
                ToastUtil.setToast("暂无开通!");
                break;
            case R.id.mine_login_out_img://退出登录
                putLogin();
                break;
        }
    }

    //退出登录
    private void putLogin() {
        RxHttpUtils.createApi(LoginApi.class)
                .putLogin()
                .compose(Transformer.<BaseABean>switchSchedulers())
                .subscribe(new NewCommonObserver<BaseABean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.setToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(BaseABean baseABean) {
                        if (baseABean.getCode() == 200) {
                            ToastUtil.setToast("退出登录成功");
                            SpUtils.put("UserInfo", "");
                            SpUtils.put("SHOUSHILOGIN", "");
                            SpUtils.put("shoushi", false);
                            SpUtils.put("shualian", false);
                            ProApplication.getinstance().closeAllActiivty();
                            LoginActivity.start(ProApplication.getmContext());
                        } else {
                            ToastUtil.setToast("退出登录失败");
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 250) {
            gainUserInformationData();
        }
    }
}


