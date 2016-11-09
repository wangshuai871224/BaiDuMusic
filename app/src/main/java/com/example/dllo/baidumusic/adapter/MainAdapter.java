package com.example.dllo.baidumusic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class MainAdapter extends FragmentPagerAdapter{

    ArrayList<Fragment> fragmentBean;
    String[] title = {"我的", "音乐", "动态", "直播"};

    public void setFragmentBean(ArrayList<Fragment> fragmentBean) {
        this.fragmentBean = fragmentBean;
    }

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentBean.get(position);
    }

    @Override
    public int getCount() {
        return fragmentBean == null ? 0 : fragmentBean.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
