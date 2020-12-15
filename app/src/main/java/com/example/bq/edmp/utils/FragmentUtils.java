package com.example.bq.edmp.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

public class FragmentUtils {
    //Fragment隐藏于显示的逻辑代,抽取方式
    public static void addFragment(FragmentManager manager, Class<? extends Fragment> zClass, int layoutID, Bundle args) {

        String tag = zClass.getName();

        Fragment fragment = manager.findFragmentByTag(tag);

        FragmentTransaction transaction = manager.beginTransaction();

        //第一次判断
        if (fragment == null) {
            try {

                //得到碎片实例
                fragment = zClass.newInstance();


                //使用Bundle方式传数据
                fragment.setArguments(args);

//                //添加动画
//                transaction.setCustomAnimations(baseFragment.enterAnim(),baseFragment.exitAnim(),
//                        baseFragment.popEnterAnim(),baseFragment.popExitAnim());

                transaction.add(layoutID, fragment);

                //隐藏碎片，除了当前碎片
                hideOtherFragment(manager, transaction, fragment);

                //设置回退站
                if (isNeedToBackStack()) {
                    transaction.addToBackStack(tag);
                }

                //提交事务
                transaction.commit();

            } catch (Exception e) {
                e.printStackTrace();
            }
            //第二次判断
        } else {
            //如果已经添加了，那么就有实例了。
            if (fragment.isAdded()) {
                //如果已经添加了，还有是已经隐藏了，所以就可以直接显示
                if (fragment.isHidden()) {

                    //使用Bundle方式传数据
                    fragment.setArguments(args);

                    //显示碎片
                    transaction.show(fragment);

                    //隐藏碎片，除了当前碎片
                    hideOtherFragment(manager, transaction, fragment);

                    //提交事务
                    transaction.commit();
                }
            } else {

                //使用Bundle方式传数据
                fragment.setArguments(args);

                //添加碎片
                transaction.add(layoutID, fragment);

                //隐藏碎片，除了当前碎片
                hideOtherFragment(manager, transaction, fragment);

                //提交事务
                transaction.commit();
            }
        }
    }

    //回退站
    protected static boolean isNeedToBackStack() {
        return false;
    }

    //隐藏碎片方法
    private static void hideOtherFragment(FragmentManager manager, FragmentTransaction transaction, Fragment willShowFragment) {

        //查询所有碎片
        List<Fragment> fragments = manager.getFragments();

        //循环遍历碎片
        for (Fragment fragment : fragments) {
            if (fragment != willShowFragment && !fragment.isHidden()) {
                //隐藏碎片
                transaction.hide(fragment);
            }
        }
    }
}
