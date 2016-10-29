package com.example.dllo.baidumusic.dynamicfragment;

import android.support.v4.view.ViewPager;
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
    private DynamicListViewAdapter adapter;
    private String url = "";
    private String[] urlImg = {"http://img.boqiicdn.com/Data/BK/A/1411/26/img77931416972193.jpg",
            "http://pic29.nipic.com/20130506/3822951_102005116000_2.jpg",
            "http://pic36.nipic.com/20131125/8821914_090743677000_2.jpg"};
    private ViewPager dynamicVP;
    private DynamicViewPagerAdapter adapterVP;

    @Override
    protected void initData() {
        GsonRequest<DynamicBean> gsonRequest = new GsonRequest<DynamicBean>(
                DynamicBean.class, url, new Response.Listener<DynamicBean>() {
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

//        adapterVP.setUrlImgs(urlImg);
//        dynamicVP.setAdapter(adapterVP);
    }

    @Override
    protected void initView() {
        dynamicLV = bindView(R.id.dynamic_list_view);
        adapter = new DynamicListViewAdapter(getActivity());
//        dynamicVP = bindView(R.id.dynamic_view_pager);
        adapterVP = new DynamicViewPagerAdapter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_dynamic;
    }
}
