package com.example.dllo.baidumusic.musicfragment.childfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.musicfragment.childfragment.childfragmentadapter.SongMenuAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class SongMenuFragment extends BaseFragment{

    private TextView hot;
    private TextView news;
    private RecyclerView songRecycler;
    private SongMenuAdapter adapter;
    private ArrayList<String> beans;
    private LinearLayout songList;
    private GridLayoutManager manager;

    @Override
    protected void initData() {
        for (int i = 0; i < 50; i++) {
            beans.add("歌单" + i);
        }
         adapter.setArrayList(beans);
         songRecycler.setAdapter(adapter);
         songRecycler.setLayoutManager(manager);

    }

    @Override
    protected void initView() {

        songList = bindView(R.id.song_list);
        hot = bindView(R.id.tv_hot);
        news = bindView(R.id.tv_new);
        songRecycler = bindView(R.id.song_recycler);
        adapter = new SongMenuAdapter(getActivity());
        manager = new GridLayoutManager(mContext, 2);
        beans = new ArrayList<>();

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_songmenu;
    }
}
