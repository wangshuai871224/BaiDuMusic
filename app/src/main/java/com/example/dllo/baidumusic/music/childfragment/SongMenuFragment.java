package com.example.dllo.baidumusic.music.childfragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.music.childfragment.adapter.SongMenuAdapter;
import com.example.dllo.baidumusic.bean.SongMenuBean;
import com.example.dllo.baidumusic.listener.EndLessOnScrollListener;
import com.example.dllo.baidumusic.tools.GsonRequest;
import com.example.dllo.baidumusic.tools.URLValues;
import com.example.dllo.baidumusic.tools.VolleySingleton;

/**
 * Created by dllo on 16/10/24.
 */
public class SongMenuFragment extends BaseFragment {

    private TextView hot;
    private TextView news;
    private RecyclerView songRecycler;
    private SongMenuAdapter adapter;
    private LinearLayout songList;
    private RecyclerView.LayoutManager manager;
    private String url;
    private SwipeRefreshLayout songRefresh;
    int i = 0;

    @Override
    protected void initData() {

        // 第一次加载都是新数据, 给true
        gsonRequest(url, true);
        songRecycler.setAdapter(adapter);
        songRecycler.setLayoutManager(manager);
        songRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                songRefresh.setRefreshing(false);
            }
        });

        songRecycler.addOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int currentPage) {
                // 每次加载都是一个新的url, 然后在adapter中加入到集合里
                if (i < URLValues.SONG_MENU_URL.length - 1) {
                    gsonRequest(URLValues.SONG_MENU_URL[i + 1], false);
                    i++;
                }
            }
        });
    }

    @Override
    protected void initView() {

        songList = bindView(R.id.song_list);
        hot = bindView(R.id.tv_hot);
        news = bindView(R.id.tv_new);
        songRecycler = bindView(R.id.song_recycler);
        songRefresh = bindView(R.id.song_refresh);
        adapter = new SongMenuAdapter(getActivity());
        manager = new GridLayoutManager(mContext, 2);
        url = URLValues.SONG_MENU_URL[0];

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_songmenu;
    }

    public void gsonRequest(String url, final boolean isRefresh) {

        GsonRequest<SongMenuBean> gsonRequest = new GsonRequest<SongMenuBean>(SongMenuBean.class, url, new Response.Listener<SongMenuBean>() {
            @Override
            public void onResponse(SongMenuBean response) {

                adapter.setBean(response, isRefresh);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}
