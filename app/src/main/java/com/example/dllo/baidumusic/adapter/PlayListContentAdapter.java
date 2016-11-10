package com.example.dllo.baidumusic.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.CommonVH;
import com.example.dllo.baidumusic.bean.BeanArrayList;
import com.example.dllo.baidumusic.bean.ContentBean;
import com.example.dllo.baidumusic.bean.MusicItemBean;
import com.example.dllo.baidumusic.bean.PlayBean;
import com.example.dllo.baidumusic.tools.GsonRequest;
import com.example.dllo.baidumusic.tools.URLValues;
import com.example.dllo.baidumusic.tools.VolleySingleton;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dllo on 16/11/7.
 */
public class PlayListContentAdapter extends BaseAdapter {

    private ArrayList<ContentBean> beans;

    public void setBeans(ArrayList<ContentBean> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beans == null ? 0 : beans.size();
    }

    @Override
    public Object getItem(int i) {
        return beans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonVH commonVH = CommonVH.getViewHolder(view, viewGroup, R.layout.content_list);
        commonVH.setText(R.id.content_title, beans.get(i).getTitle());
        commonVH.setText(R.id.content_author, beans.get(i).getAuthor());

        final String url = URLValues.MUSIC_PLAY_FRONT + beans.get(i).getSongId() + URLValues.MUSIC_PLAY_BEHIND;
//        Log.d("PlayListContent", url);
        commonVH.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 把集合放到一个类里在用EventBus传送
                BeanArrayList array = new BeanArrayList();
                array.setBeanArrayList(beans);
                EventBus.getDefault().postSticky(array);
                getPlayUrl(url);
            }
        });

        return commonVH.getItemView();
    }


    public void getPlayUrl(String s) {
        GsonRequest<PlayBean> gsonRequest = new GsonRequest<PlayBean>(PlayBean.class, s, new Response.Listener<PlayBean>() {
            @Override
            public void onResponse(PlayBean response) {

                MusicItemBean itemBean = new MusicItemBean();
                itemBean.setSongName(response.getSonginfo().getTitle());
                itemBean.setAuthor(response.getSonginfo().getAuthor());
                itemBean.setLyrics(response.getSonginfo().getLrclink());
                itemBean.setImage(response.getSonginfo().getPic_small());
                itemBean.setSongUrl(response.getBitrate().getFile_link());

                EventBus.getDefault().post(itemBean);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

}
