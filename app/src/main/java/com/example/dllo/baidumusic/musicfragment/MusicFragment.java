package com.example.dllo.baidumusic.musicfragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.musicfragment.childfragment.KsongFragment;
import com.example.dllo.baidumusic.musicfragment.childfragment.ListFragment;
import com.example.dllo.baidumusic.musicfragment.childfragment.RecommendFragment;
import com.example.dllo.baidumusic.musicfragment.childfragment.SongMenuFragment;
import com.example.dllo.baidumusic.musicfragment.childfragment.VideoFragment;

import java.util.ArrayList;

/**
 * Created by WangShuai on 16/10/24.
 */
public class MusicFragment extends BaseFragment{

    private TabLayout music_tb;
    private ViewPager music_vp;
    private ArrayList<Fragment> fragments;
    private MusicFragmentAdapter adapter;

    @Override
    protected void initData() {

        adapter = new MusicFragmentAdapter(getChildFragmentManager());
        fragments = new ArrayList<>();

        fragments.add(new RecommendFragment());
        fragments.add(new SongMenuFragment());
        fragments.add(new ListFragment());
        fragments.add(new VideoFragment());
        fragments.add(new KsongFragment());

        adapter.setFragmentArrayList(fragments);
        music_vp.setAdapter(adapter);
        music_tb.setupWithViewPager(music_vp);

    }

    @Override
    protected void initView() {
        music_tb = bindView(R.id.music_tb);
        music_vp = bindView(R.id.music_vp);
    }

    @Override
    protected int getLayout() {
        return R.layout.music_fragment;
    }
}
