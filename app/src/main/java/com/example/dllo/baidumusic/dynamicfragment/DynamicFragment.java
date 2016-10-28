package com.example.dllo.baidumusic.dynamicfragment;

import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.fragmentclass.DynamicBean;
import com.example.dllo.baidumusic.tools.GsonRequest;
import com.example.dllo.baidumusic.tools.VolleySingleton;

/**
 * Created by dllo on 16/10/24.
 */
public class DynamicFragment extends BaseFragment{

    private ListView dynamicLV;
    private DynamicAdapter adapter;
    private String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=huwei&operator=0&method=baidu.ting.show.live&page_no=1&page_size=40";

    @Override
    protected void initData() {
        GsonRequest<DynamicBean> gsonRequest = new GsonRequest<DynamicBean>(DynamicBean.class, url, new Response.Listener<DynamicBean>() {
            @Override
            public void onResponse(DynamicBean response) {
                   adapter.setBeans(response);
                   dynamicLV.setAdapter(adapter);
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
        dynamicLV = bindView(R.id.dynamic_list_view);
        adapter = new DynamicAdapter(getActivity());

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_dynamic;
    }
}
