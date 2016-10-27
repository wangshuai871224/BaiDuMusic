package com.example.dllo.baidumusic.musicfragment.childfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.musicfragment.childfragment.childfragmentadapter.SongMenuAdapter;
import com.example.dllo.baidumusic.musicfragment.childfragment.childragmentclass.SongMenuBean;
import com.example.dllo.baidumusic.tools.GsonRequest;
import com.example.dllo.baidumusic.tools.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class SongMenuFragment extends BaseFragment{

    private TextView hot;
    private TextView news;
    private RecyclerView songRecycler;
    private SongMenuAdapter adapter;
    private LinearLayout songList;
    private GridLayoutManager manager;
    private String url;

    @Override
    protected void initData() {

        GsonRequest<SongMenuBean> gsonRequest = new GsonRequest<SongMenuBean>(SongMenuBean.class, url, new Response.Listener<SongMenuBean>() {
            @Override
            public void onResponse(SongMenuBean response) {

                adapter.setBean(response);
                songRecycler.setAdapter(adapter);
                songRecycler.setLayoutManager(manager);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance().addRequest(gsonRequest);

    }

    @Override
    protected void initView() {

        songList = bindView(R.id.song_list);
        hot = bindView(R.id.tv_hot);
        news = bindView(R.id.tv_new);
        songRecycler = bindView(R.id.song_recycler);
        adapter = new SongMenuAdapter(getActivity());
        manager = new GridLayoutManager(mContext, 2);
        url = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=huwei&operator=0&method=baidu.ting.ugcdiy.getChanneldiy&param=zAR3AtUclOQ0EXJqn2NjoTsvlF2fNfoJ%2BA328%2Fjq24dX8vwkHVTOtCDtHfbTu6jByVf1Jzg0o3XAKo6r8MdNaEmTVsm4c36AC8f%2FbdwzSl2fJiQQ6m82IIVHdAojOc55&timestamp=1477530674&sign=b1bebdbb76b2088e511b3b16994826d6";

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_songmenu;
    }
}
