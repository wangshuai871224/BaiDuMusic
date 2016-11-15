package com.example.dllo.baidumusic.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.CommonVH;
import com.example.dllo.baidumusic.bean.ContentBean;
import com.example.dllo.baidumusic.tools.URLValues;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/3.
 */
public class PlayListAdapter extends BaseAdapter{

//    ArrayList<String> arrayList;
    private ArrayList<ContentBean> arrayList;
    private String url;

    public void setArrayList(ArrayList<ContentBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonVH commonVH = CommonVH.getViewHolder(view, viewGroup, R.layout.item_paly_list);
        commonVH.setText(R.id.play_list_name, arrayList.get(i).getTitle())
                 .setText(R.id.paly_list_people,arrayList.get(i).getAuthor());

        url = URLValues.MUSIC_PLAY_FRONT + arrayList.get(i).getSongId() + URLValues.MUSIC_PLAY_BEHIND;

        commonVH.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 把该音乐给播放器赋值(图片, 歌词等)

            }
        });

        return commonVH.getItemView();
    }
}
