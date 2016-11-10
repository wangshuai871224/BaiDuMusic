package com.example.dllo.baidumusic.fragment;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.dllo.baidumusic.MainActivity;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.adapter.PlayListAdapter;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.bean.BeanArrayList;
import com.example.dllo.baidumusic.bean.ContentBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

        EventBus.getDefault().register(this);
//        ArrayList<String> arrayList = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            arrayList.add("歌手" + i);
//        }
//        adapter.setArrayList(arrayList);
//        playList.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        playList = bindView(R.id.play_list_lv);
        frameLayout = bindView(R.id.back);
        adapter = new PlayListAdapter();
        setClick(this, frameLayout);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getBeanArrayList(BeanArrayList arrayList){
        adapter.setArrayList(arrayList.getBeanArrayList());
        playList.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
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
