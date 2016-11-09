package com.example.dllo.baidumusic.music.childfragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.music.childfragment.childactivity.NewSongActivity;
import com.example.dllo.baidumusic.music.childfragment.adapter.ListAdapter;
import com.example.dllo.baidumusic.bean.ListBean;
import com.example.dllo.baidumusic.tools.GsonRequest;
import com.example.dllo.baidumusic.tools.URLValues;
import com.example.dllo.baidumusic.tools.VolleySingleton;

/**
 * Created by dllo on 16/10/24.
 */
public class ListFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ListAdapter adapter;
    private String url = URLValues.LIST_URL;


    @Override
    protected void initData() {

        GsonRequest<ListBean> gsonRequest = new GsonRequest<ListBean>(ListBean.class,
                url, new Response.Listener<ListBean>() {
            @Override
            public void onResponse(ListBean response) {

                adapter.setBean(response);
                listView.setAdapter(adapter);
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
        listView = bindView(R.id.list_listView);
        adapter = new ListAdapter(mContext);
        listView.setOnItemClickListener(this);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_list;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(getActivity(), NewSongActivity.class);
//        intent.putExtra("parameter", .getContent().get(position));
        getActivity().startActivity(intent);
    }
}
