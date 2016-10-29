package com.example.dllo.baidumusic.livefragment;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseActivity;

/**
 * Created by dllo on 16/10/29.
 */
public class LiveMoreActivity extends BaseActivity{


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        tabLayout = bindView(R.id.live_more_tb);
        viewPager = bindView(R.id.live_more_vp);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_live_more;
    }
}
