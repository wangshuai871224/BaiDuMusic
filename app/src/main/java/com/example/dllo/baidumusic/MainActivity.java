package com.example.dllo.baidumusic;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dllo.baidumusic.base.BaseActivity;
import com.example.dllo.baidumusic.dynamicfragment.DynamicFragment;
import com.example.dllo.baidumusic.livefragment.LiveFragment;
import com.example.dllo.baidumusic.minefragment.MineFragment;
import com.example.dllo.baidumusic.musicfragment.MusicFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private ImageView imageView;
    private ImageView set;
    private ImageView query;
    private ViewPager mainVP;
    private TabLayout mainTL;
    private ImageView play;
    private ImageView next;
    private ImageView playList;
    private LinearLayout mainLL;
    private ArrayList<Fragment> fragments;
    private MainAdapter adapter;

    @Override
    protected void initData() {

        adapter = new MainAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();

        fragments.add(new MineFragment());
        fragments.add(new MusicFragment());
        fragments.add(new DynamicFragment());
        fragments.add(new LiveFragment());

        adapter.setFragmentBean(fragments);
        mainVP.setAdapter(adapter);
        mainTL.setupWithViewPager(mainVP);

    }

    @Override
    protected void initViews() {

        mainLL = bindView(R.id.main_ll);
        mainVP = bindView(R.id.main_vp);
        mainTL = bindView(R.id.main_tb);

        set = bindView(R.id.main_set);
        query = bindView(R.id.main_query);
        imageView = bindView(R.id.main_image);
        play = bindView(R.id.main_play);
        next = bindView(R.id.main_next);
        playList = bindView(R.id.main_playList);

        setClick(this, mainLL, set, query, imageView, play, next, playList);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.main_set:
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.replace_fragment, new FragmentSet());
                transaction.commit();
                break;

            case R.id.main_query:
                break;
            case R.id.main_image:
                break;
            case R.id.main_play:
                break;
            case R.id.main_next:
                break;
            case R.id.main_playList:
                break;
            case R.id.main_ll:
                break;

        }
    }
}
