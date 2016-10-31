package com.example.dllo.baidumusic.livefragment;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.baidumusic.MainActivity;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseActivity;
import com.example.dllo.baidumusic.base.BaseFragment;

/**
 * Created by dllo on 16/10/29.
 */
public class LiveMoreFragment extends BaseFragment implements View.OnClickListener {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView back;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.live_more_tb);
        viewPager = bindView(R.id.live_more_vp);
        back = bindView(R.id.live_back);
        setClick(this, back);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_live_more;
    }

    @Override
    public void onClick(View view) {
        // 弹栈 返回时,返回到之前点击的页面页面**
        getFragmentManager().popBackStack();
    }
}
