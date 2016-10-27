package com.example.dllo.baidumusic.musicfragment.childfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.base.MyApp;
import com.example.dllo.baidumusic.musicfragment.childfragment.childfragmentadapter.VideoAdapter;
import com.example.dllo.baidumusic.musicfragment.childfragment.childragmentclass.VideoBean;
import com.example.dllo.baidumusic.tools.GsonRequest;
import com.example.dllo.baidumusic.tools.VolleySingleton;

/**
 * Created by dllo on 16/10/24.
 */
public class VideoFragment extends BaseFragment{

    private RecyclerView recyclerView;
    private VideoAdapter adapter;
    private String url;
    private GridLayoutManager manager;

    @Override
    protected void initData() {

        GsonRequest<VideoBean> gsonRequest = new GsonRequest<VideoBean>(VideoBean.class, url,
                new Response.Listener<VideoBean>() {
                    @Override
                    public void onResponse(VideoBean response) {
                          adapter.setBean(response);
                          recyclerView.setAdapter(adapter);
                          recyclerView.setLayoutManager(manager);

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

        recyclerView = bindView(R.id.video_recycler);
        adapter = new VideoAdapter(getActivity());
        manager = new GridLayoutManager(mContext, 2);
        url = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=huwei&operator=0&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_video;
    }
}
