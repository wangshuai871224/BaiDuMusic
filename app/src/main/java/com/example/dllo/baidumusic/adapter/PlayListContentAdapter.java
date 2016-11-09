package com.example.dllo.baidumusic.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.CommonVH;
import com.example.dllo.baidumusic.bean.ContentBean;
import com.example.dllo.baidumusic.bean.MusicItemBean;
import com.example.dllo.baidumusic.bean.PlayBean;
import com.example.dllo.baidumusic.tools.GsonRequest;
import com.example.dllo.baidumusic.tools.URLValues;
import com.example.dllo.baidumusic.tools.VolleySingleton;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/7.
 */
public class PlayListContentAdapter extends BaseAdapter {
    ArrayList<ContentBean> beans;
    private String url;

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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        CommonVH commonVH = CommonVH.getViewHolder(view, viewGroup, R.layout.content_list);
        commonVH.setText(R.id.content_title, beans.get(i).getTitle());
        commonVH.setText(R.id.content_author, beans.get(i).getAuthor());

        url = URLValues.MUSIC_PLAY_FRONT + beans.get(i).getSongId() + URLValues.MUSIC_PLAY_BEHIND;
//        Log.d("PlayListContent", url);
        commonVH.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "123", Toast.LENGTH_SHORT).show();
                getPlayUrl(url, i);
            }
        });

        return commonVH.getItemView();
    }


    public void getPlayUrl(String s, final int i) {
        GsonRequest<PlayBean> gsonRequest = new GsonRequest<PlayBean>(PlayBean.class, s, new Response.Listener<PlayBean>() {
            @Override
            public void onResponse(PlayBean response) {

                MusicItemBean bean = new MusicItemBean();
                bean.setSongName(response.getSonginfo().getTitle());
                bean.setAuthor(response.getSonginfo().getAuthor());
                bean.setLyrics(response.getSonginfo().getLrclink());
                bean.setSongUrl(response.getBitrate().getFile_link());
                bean.setImage(response.getSonginfo().getPic_small());

                EventBus.getDefault().post(bean);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

}
