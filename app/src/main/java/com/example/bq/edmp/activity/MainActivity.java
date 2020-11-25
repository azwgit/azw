package com.example.bq.edmp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bq.edmp.R;
import com.example.bq.edmp.activity.login.LoginActivity;
import com.example.bq.edmp.base.BaseActivity;
import com.example.bq.edmp.fragment.AddressBookFragment;
import com.example.bq.edmp.fragment.ConmmunityFragment;
import com.example.bq.edmp.fragment.HomeFragment;
import com.example.bq.edmp.fragment.MineFragment;
import com.example.bq.edmp.fragment.WorkFragment;
import com.example.bq.edmp.utils.NoSrcollViewPage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    NoSrcollViewPage viewPager;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.home_rb)
    RadioButton home_rb;
    @BindView(R.id.addressbook_rb)
    RadioButton addressbook_rb;
    @BindView(R.id.work_rb)
    RadioButton work_rb;
    @BindView(R.id.conmmunity_rb)
    RadioButton conmmunity_rb;
    @BindView(R.id.mine_rb)
    RadioButton mine_rb;

    private List<Fragment> fragmentList = new ArrayList<>();
    private HomeFragment homeFragment;
    private AddressBookFragment addressBookFragment;
    private WorkFragment workFragment;
    private ConmmunityFragment conmmunityFragment;
    private MineFragment mineFragment;
    long preTime;
    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initData() {
        int type = getIntent().getIntExtra("type", 0);
        homeFragment = new HomeFragment();
        addressBookFragment = new AddressBookFragment();
        workFragment = new WorkFragment();
        conmmunityFragment = new ConmmunityFragment();
        mineFragment = new MineFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(addressBookFragment);
        fragmentList.add(workFragment);
        fragmentList.add(conmmunityFragment);
        fragmentList.add(mineFragment);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        // ViewPager页面切换监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        radioGroup.check(R.id.home_rb);
                        break;
                    case 1:
                        radioGroup.check(R.id.addressbook_rb);
                        break;
                    case 2:
                        radioGroup.check(R.id.work_rb);
                        break;
                    case 3:
                        radioGroup.check(R.id.conmmunity_rb);
                        break;
                    case 4:
                        radioGroup.check(R.id.mine_rb);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(type);
        if (type == 0) {

            home_rb.setChecked(true);
        } else if (type == 1) {
            addressbook_rb.setChecked(true);
        } else if (type == 2) {
            work_rb.setChecked(true);
        } else if (type == 3) {
            conmmunity_rb.setChecked(true);
        } else if (type == 4) {
            mine_rb.setChecked(true);
        }

    }

    @Override
    protected void initListener() {
        home_rb.setOnClickListener(this);
        addressbook_rb.setOnClickListener(this);
        work_rb.setOnClickListener(this);
        conmmunity_rb.setOnClickListener(this);
        mine_rb.setOnClickListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.home_rb:
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.addressbook_rb:
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.work_rb:
                viewPager.setCurrentItem(2, false);
                break;
            case R.id.conmmunity_rb:
                viewPager.setCurrentItem(3, false);
                break;
            case R.id.mine_rb:
                viewPager.setCurrentItem(4, false);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long currentTime = new Date().getTime();
            // 如果时间间隔大于2秒，不处理
            if ((currentTime - preTime) > 2000) {
                // 显示消息
                Toast.makeText(this, "再按一次退出程序！", Toast.LENGTH_SHORT).show();
                //更新时间
                preTime = currentTime;
                //截获事件，不再处理
                return false;
            } else {
                application.exit();
            }
        }
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {

            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    public class MyFragmentPagerAdapter2 extends FragmentPagerAdapter {

        private List<Fragment> list2;
        private List<Fragment> list;

        public MyFragmentPagerAdapter2(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {

            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
