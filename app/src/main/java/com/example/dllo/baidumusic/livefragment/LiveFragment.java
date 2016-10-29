package com.example.dllo.baidumusic.livefragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.fragmentclass.LiveBean;
import com.example.dllo.baidumusic.musicfragment.childfragment.childragmentclass.ListBean;
import com.example.dllo.baidumusic.tools.GsonRequest;
import com.example.dllo.baidumusic.tools.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class LiveFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private LiveAdapter adapter;
    private GridLayoutManager manager;
    private RecyclerViewHeader header;
    private String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=huwei&operator=0&method=baidu.ting.show.live&page_no=1&page_size=40";
    private TextView more;

    @Override
    protected void initData() {
        GsonRequest<LiveBean> gsonRequest = new GsonRequest<LiveBean>(
                LiveBean.class, url, new Response.Listener<LiveBean>() {
            @Override
            public void onResponse(LiveBean response) {
                adapter.setBeans(response);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
                header.attachTo(recyclerView, true);
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
        more = bindView(R.id.live_more);
        recyclerView = bindView(R.id.live_recycler);
        adapter = new LiveAdapter(getActivity());
        manager = new GridLayoutManager(getActivity(), 2);
        header = bindView(R.id.header_recycler);

        setClick(this, more);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_live;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), LiveMoreActivity.class);
        startActivity(intent);
    }
}
