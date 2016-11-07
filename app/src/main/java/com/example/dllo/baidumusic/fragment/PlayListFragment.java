package com.example.dllo.baidumusic.fragment;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.dllo.baidumusic.MainActivity;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.adapter.PlayListAdapter;
import com.example.dllo.baidumusic.base.BaseFragment;

import java.util.ArrayList;


/**
 * Created by dllo on 16/11/3.
 */
public class PlayListFragment extends BaseFragment implements View.OnClickListener {
    FrameLayout frameLayout;
    private ListView playList;
    private PlayListAdapter adapter;

    @Override
    protected void initData() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            arrayList.add("歌手" + i);
        }
        adapter.setArrayList(arrayList);
        playList.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        playList = bindView(R.id.play_list_lv);
        frameLayout = bindView(R.id.back);
        adapter = new PlayListAdapter();
        setClick(this, frameLayout);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_play_list;
    }

    @Override
    public void onClick(View view) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.playList();
    }
}
