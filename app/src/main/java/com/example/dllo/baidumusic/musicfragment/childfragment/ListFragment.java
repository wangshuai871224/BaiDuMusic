package com.example.dllo.baidumusic.musicfragment.childfragment;

import android.widget.ListView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.musicfragment.childfragment.childfragmentadapter.ListAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class ListFragment extends BaseFragment{

    private ListView listView;
    private ArrayList<String> arrayList;
    private ListAdapter adapter;


    @Override
    protected void initData() {
        for (int i = 0; i < 50; i++) {
            arrayList.add("我是" + i);
        }
        adapter.setStrings(arrayList);

        listView.setAdapter(adapter);

    }

    @Override
    protected void initView() {
        listView = bindView(R.id.list_listView);
        arrayList = new ArrayList<>();
        adapter = new ListAdapter(getActivity());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_list;
    }
}
