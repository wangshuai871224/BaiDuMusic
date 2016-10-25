package com.example.dllo.baidumusic.livefragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class LiveFragment extends BaseFragment{

    private RecyclerView recyclerView;
    private LiveAdapter adapter;
    private ArrayList<String> arrayList;
    private GridLayoutManager manager;

    @Override
    protected void initData() {
        for (int i = 0; i < 100; i++) {
            arrayList.add("我是第"+ i + "个数据");
        }
        adapter.setStringBean(arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.live_recycler);
        adapter = new LiveAdapter(getActivity());
        arrayList = new ArrayList<>();
        manager = new GridLayoutManager(getActivity(), 2);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_live;
    }
}
